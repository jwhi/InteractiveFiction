package scenes;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import commandhandler.CommandSet;

public class BasicScene {
	private static Image Background;
	private static CommandSet Commands;
	private static int SceneX = 558;
	private static int SceneY = 48;
	
	public BasicScene() {
		Commands = new CommandSet();
	}
	
	public BasicScene(Image background) {
		Background = background;
		Commands = new CommandSet();
	}

	public static void setBackground (Image background) {
		Background = background;
	}
	
	// If setting background succeeds, return true
	public static void setBackground (String imageLocation) throws SlickException {
		Background = new Image(imageLocation);
	}
	
	public String executeCommand(String cmd) {
		if (cmd.equalsIgnoreCase("exit") || cmd.equalsIgnoreCase("quit")) {
			return "Exiting...";
		}
		return cmd + " is not a valid command.";
	}
	
	public static Image getBackground() {
		return Background;
	}
	
	public static int getX() {
		return SceneX;
	}
	
	public static int getY() {
		return SceneY;
	}

	public static CommandSet getCommands() {
		return Commands;
	}
	
	public void render(Graphics g) {
		
	}
	
	public void updateAnimations(int delta) {
		
	}
	
	public void sceneCliked(int x, int y) {
		
	}
}
