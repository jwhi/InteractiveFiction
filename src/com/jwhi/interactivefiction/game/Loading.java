package com.jwhi.interactivefiction.game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
public class Loading extends BasicGameState {

	private Image loadingImage;
	private int step = 0;
//	private int play = Constants.Play;
	private int book = Constants.Book;
	private int isometric = Constants.Isometric;
	private int options = Constants.Options;
	private int stateID;
	
	public Loading(int state, Properties props) {
		stateID = state;
	}
	
	// Loads the image for the loadscreen
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		loadingImage = new Image("res/backgrounds/LoadingScreen1280x720.png");
	}

	// Draws the loading screen
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
//		g.drawImage(loadingImage, 0, 0);
		loadingImage.draw(0, 0, (float)gc.getWidth()/(float)Constants.MaxResourceWidth);
		
	}

	// Play needs to load the tagger which can take some time, so first display an image
	// to let user know the game is loading, then load in the assets that take time.
	// After the play state is loaded, switch to it
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		switch (step) {
		case 0:
			break;
		case 1:
			//sbg.addState(new Play(play));
			sbg.addState(new BookView(book));
			sbg.addState(new IsometricView(isometric));
			sbg.addState(new OptionsView(options));
			//sbg.getState(play).init(gc, sbg);
			sbg.getState(book).init(gc, sbg);
			sbg.getState(isometric).init(gc, sbg);
			sbg.getState(options).init(gc, sbg);
			break;
		case 2:
			sbg.enterState(isometric);
			break;
		}
		
		step += 1;
	}

	public int getID() {
		return stateID;
	}

}