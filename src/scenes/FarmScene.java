package scenes;

import java.io.IOException;
import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class FarmScene extends BasicScene {
	
	private static Image FarmHouse;
	private static Image Farmer;
	private static Image FireStart;
	private static Image FireAshes;
	private static Animation FireAnimation;
	private static Animation SmokeAnimation;
	private static int FireStatus = 0; //(0 = Pile of wood) (1 = Fire animation) (2 = Burnt out fire)
	
	public FarmScene() throws SlickException {
		super.setBackground("res/scenes/backgrounds/Sky.png");
		FarmHouse =  new Image("res/scenes/props/Cabin+Farm.png");
		Farmer = new Image("res/scenes/actors/Farmer.png");
		FireStart = new Image("res/scenes/props/FireStart.png");
		FireAshes = new Image("res/scenes/props/FireAshes.png");
		SpriteSheet FireSheet = new SpriteSheet("res/animations/fire.png", 23, 28);
		FireAnimation = new Animation(FireSheet, 250);
		
		SpriteSheet SmokeSheet = new SpriteSheet("res/animations/smoke.png", 29, 67);
		SmokeAnimation = new Animation(SmokeSheet, 750);
	}
	
	public void updateAnimations(int delta) {
		FireAnimation.update(delta);
		SmokeAnimation.update(delta);
	}
	
	public void render(Graphics g) {
		// Draw background of the scene in the area designated for the scene in the game window
		g.drawImage(getBackground(), getX(), getY());
		
		// Draw the farm house and the yard.
		g.drawImage(FarmHouse, getX(), getY());
		
		// Draw the farmer at 78,248 with 0,0 being the top right corner of the scene
		g.drawImage(Farmer, getX()+78, getY()+248);
		
		// Draw the animation of the chimney smoke at 155,129
		SmokeAnimation.draw(getX()+155,getY()+129);
		
		// Draw the fire based on the current state of the fire
		switch (FireStatus) {
		case 0:
			g.drawImage(FireStart, getX()+184, getY()+295);
			break;
		case 1:
			FireAnimation.draw(getX()+184,getY()+295);
			break;
		case 2:
			g.drawImage(FireAshes, getX()+184, getY()+295);
			break;
		}
	}
	
	public void sceneCliked(int x, int y) {
		if ((x > 742 && x < 760) && (y > 110 && y < 120)) {
			// Cycles the camp fire between the three stages.
			// 0 = Regular logs
			// 1 = Fire animation
			// 2 = Ashes on the camp fire
			FireStatus += 1;
			if (FireStatus > 2) { FireStatus = 0;}
		}
		
	}
	
	// Executes a command for the scene and returns action or error text
	public String executeCommand(String cmd) {
		String actionText = "";
		if (cmd.equalsIgnoreCase("quit") || (cmd.equalsIgnoreCase("exit"))) {
			return "Exiting...";
		}
		try {
			System.out.println(getCommands().getTags(cmd));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<String> nouns = getCommands().getNouns(cmd);
		ArrayList<String> verbs = getCommands().getVerbs(cmd);
		ArrayList<String> adverbs = getCommands().getAdverbs(cmd);
		ArrayList<String> particles = getCommands().getParticles(cmd);
		String verb = "";
		
		if (!verbs.isEmpty()) {
			verb = verbs.get(0);
		} else {
			return "Error: Need a verb if you want to do anything.";
		}
		
		switch (verb) {
		case "light":
		case "fire":
		case "ignite":
		case "start":
		case "burn":
		case "erupt":
		case "kindle":
		case "combust":
			verb = "light";
			break;
		case "set":
			verb = "set";
			break;
		case "put":
			verb = "put";
			break;
		case "extinguish":
		case "stop":
		case "destroy":
		case "destruct":
		case "end":
		case "decimate":
			verb = "stop";
			break;
		case "clear":
		case "clean":
		case "remove":
			verb = "clear";
			break;
		}
		
		if  (!nouns.isEmpty()) {
			switch (nouns.get(0)) {
			case "fire":
			case "campfire":
			case "logs":
				switch (verb) {
				case "light":
					actionText = startFire();
					break;
				case "set":
					if (!adverbs.isEmpty()) {
						if (adverbs.get(0).equalsIgnoreCase("aflame")) {
							actionText = startFire();
						} else {
							actionText = "Error: Unable to set the " + nouns.get(0) + " " + adverbs.get(0) + ".";
						}
					} else {
						actionText = "How would you like to set the " + nouns.get(0) + "?";
					}
					break;
				case "stop":
					actionText = extinguishFire();
					break;
				case "clear":
					actionText = clearAshes();
					break;
				case "put":
					if (!particles.isEmpty()) {
						if (particles.get(0).equalsIgnoreCase("out")) {
							actionText = extinguishFire();
						} else {
							actionText = "Error: Unable to " + verbs.get(0) + " the " + nouns.get(0) + " " + particles.get(0);
						}
					} else {
						actionText = "You just want to put the " + nouns.get(0) + "? Nothing else?";
					}
					break;
				}
				break;
			case "ashes":
			case "dust":
			case "clinker":
				switch (verb) {
				case "clear":
					actionText = clearAshes();
					break;
				default:
					actionText = "Error: Unable to " + verbs.get(0) + " the " + nouns.get(0) + ".";
				}
			break;
			}
		} else {
			return "Error: What do you want to " + verbs.get(0) + "?";
		}
		
		if (actionText == "") {
			actionText = super.executeCommand(cmd);
		}
		return actionText;
	}
	
	public String startFire() {
		String actionText = "";
		switch(FireStatus) {
		case 0:
			FireStatus = 1;
			actionText = "Fire is now lit.";
			break;
		case 1:
			actionText = "You cannot do that. Fire is already lit.";
			break;
		case 2:
			actionText = "The campfire is filled with ashes.";
			break;
		}
		
		return actionText;
	}
	
	public String extinguishFire() {
		String actionText = "";
		switch(FireStatus) {
		case 0:
			actionText = "You can\'t put out a nonexistant fire.";
			break;
		case 1:
			FireStatus = 2;
			actionText = "The campfire is now reduced to a pile of ashes.";
			break;
		case 2:
			actionText = "The fire is already out.";
			break;
		}
		
		return actionText;
	}
	
	public String clearAshes() {
		String actionText = "";
		switch(FireStatus) {
		case 0:
			actionText = "There are no ashes to be cleared.";
			break;
		case 1:
			actionText = "You would burn your hands if you tried to clear the ashes.";
			break;
		case 2:
			FireStatus = 0;
			actionText = "The ashes are cleared leaving a nice pile of wood.";
			break;
		}
		
		return actionText;
	}
}
