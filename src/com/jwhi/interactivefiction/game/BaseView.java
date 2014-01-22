package com.jwhi.interactivefiction.game;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class BaseView extends BasicGameState implements KeyListener {
	protected int StateID;
	protected int PreviousState = Constants.Play;
	protected boolean KeyPressed = false;
	protected boolean KeyHeld = false;
	protected int Key;
	protected boolean ClickAllowed;

	public BaseView() {

	}

	public BaseView(int state) {
		StateID = state;
		Keyboard.enableRepeatEvents(true);
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {

	}
	
	@Override
	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
		clearInput();
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
	}

	 @Override
    public void keyPressed(int key, char c) {
		 if(Keyboard.isRepeatEvent()) {
         	// Triggered when a key is held down.
			 KeyHeld = true;
         }
         else {
         	Key = key;
         	KeyPressed = true;
         	KeyHeld = false;
        }
    }
	 
	 @Override
	 public void keyReleased(int key, char c) {
		 KeyHeld = false;
		 KeyPressed = false;
	 }
	 
	 public void clearInput() {
		 KeyPressed = false;
	 }
	
	public void setPreviousState(int prevstate) {
		PreviousState = prevstate;
	}

	@Override
	public int getID() {
		return StateID;
	}
}
