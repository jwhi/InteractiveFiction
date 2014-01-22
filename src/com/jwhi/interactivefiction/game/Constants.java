package com.jwhi.interactivefiction.game;

public class Constants {
	// The highest resolution of the resources is currently 1280x720
	protected static final int MaxResourceWidth = 1280;
	
	// State IDs for all the states in the game.
	protected static final int Loading = 0;
	protected static final int Play = 1;
	protected static final int Book = 2;
	protected static final int Isometric = 1;
	protected static final int Options = 3;
	
	// Default values for variables
	protected static final int MinimumFontSize = 11;
	protected static final int DefaultFontSize = 19;
	protected static final int MaximumFontSize = 39;
	protected static final String DefaultFont = "Courier New";
	protected static final int MinimumTransparency = 5;
	protected static final int DefaultBackgroundTransparency = 125;
	protected static final int MaximumTransparency = 255;
}
