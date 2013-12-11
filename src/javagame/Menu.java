package javagame;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Menu extends BasicGameState {
	Image pipesBackground;
	//String mouseCoords = "No data";
	//854 480
	public Menu(int state) {
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		pipesBackground = new Image("res/backgrounds/Pipes.png");
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.setBackground(Color.black);
		g.setColor(Color.gray);		
		g.fillRect(11, 16, pipesBackground.getWidth(), pipesBackground.getHeight());
		g.drawImage(pipesBackground, 11, 16);
		
		g.setColor(Color.lightGray);
		g.fillRect(32, 32, (pipesBackground.getWidth()-42), (pipesBackground.getHeight()-32));
		
		g.setColor(Color.blue);
		g.fillRect(48, 48, 100, 50);
		g.setColor(Color.black);
		g.drawString("Play Now", 53, 60);
		
		//g.setColor(Color.white);
		//g.drawString(mouseCoords, 200, 200);
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput();
		int xpos = Mouse.getX();
		int ypos = Mouse.getY();
		
		//mouseCoords = "(" + xpos + "," + ypos + ")";
		if ((xpos > 48 && xpos < 148)&&(ypos > 382 && ypos < 432)) {
			if (input.isMouseButtonDown(0)) {
				sbg.enterState(1);
			}
		}
		
		
	}
	
	public int getID() {
		return 0;
	}
}
