package com.jwhi.interactivefiction.game;

import java.io.File;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Game extends StateBasedGame {
	
	public static final String gamename = "Text Adventure";
	public static final int loading = 0;
	public static final int play = 1;
	public static String OS = null;
	public static Properties properties;
	
	public Game(String gamename) {
		super(gamename);
		// Properties for the game. Constructor takes in the width, height, fullscreen, and vsync
		properties = new Properties(854, 480, false, true);
		this.addState(new Loading(loading));
//		this.addState(new Play(play));
	}
	
	public void initStatesList(GameContainer gc) throws SlickException {
		this.getState(loading).init(gc, this);
//		this.getState(play).init(gc, this);
		this.enterState(loading);
	}
	
	public static void main(String[] args) {
		OS = System.getProperty("os.name").toLowerCase();
		if (OS.indexOf("win") >= 0) {
			OS = "windows";
		} else if (OS.indexOf("mac") >= 0) {
			OS = "macosx";
		} else if (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 ) {
			OS = "linux";
		} else if (OS.indexOf("sunos") >= 0) {
			OS = "solaris";
		}
		System.setProperty("org.lwjgl.librarypath",System.getProperty("user.dir") + File.separator + "res" + File.separator + "natives" + File.separator + OS);
		
		AppGameContainer appgc;
		try {
			appgc = new AppGameContainer(new Game(gamename));
			// Create a display using the properties created earlier
			appgc.setDisplayMode(properties.getScreenResWidth(), properties.getScreenResHeight(), properties.getFullscreen());
			// Decides whether or not that vsync is enabled based on the games properties
			appgc.setVSync(properties.getVSync());
			// If vsync is disabled, can specify target framerate instead
//			appgc.setTargetFrameRate(properties.getFPSLimit());
			// Set the icons used for the application
			appgc.setIcons(new String[] {"res/icons/Icon-16x16.png","res/icons/Icon-24x24.png","res/icons/Icon-32x32.png"});
			
			//Disable FPS Counter
			appgc.setShowFPS(false);
			appgc.start();
		} catch(SlickException e) {
			e.printStackTrace();
		}
	}

}
