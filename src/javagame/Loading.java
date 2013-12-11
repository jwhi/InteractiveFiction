package javagame;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
public class Loading extends BasicGameState {

	private Image loadingImage;
	private int step = 0;
	private int play = 1;
	
	public Loading(int state) {
		
	}
	
	// Loads the image for the loadscreen
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		loadingImage = new Image("res/backgrounds/EmeraldGhostWizard.png");
	}

	// Draws the loading screen
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawImage(loadingImage, 0, 0);
		
	}

	// Play needs to load the tagger which can take some time, so first display an image
	// to let user know the game is loading, then load in the assets that take time.
	// After the play state is loaded, switch to it
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		switch (step) {
		case 0:
			break;
		case 1:
			sbg.addState(new Play(play));
			sbg.getState(play).init(gc, sbg);
			break;
		case 2:
			sbg.enterState(play);
			break;
		}
		
		step += 1;
	}

	public int getID() {
		return 0;
	}

}