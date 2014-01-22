package com.jwhi.interactivefiction.game;

public class Properties {
	protected static int ScreenResWidth = 1280;
	protected static int ScreenResHeight = 720;
	protected boolean Fullscreen = false;
	protected boolean VSync = true;
	protected int FPSLimit = 60;
	protected float ResolutionScale = (float) ScreenResWidth / 1280;
	protected static int FontSize = Constants.DefaultFontSize;
	protected static int BackgroundTransparency = Constants.DefaultBackgroundTransparency;
	
	public Properties() {
		ResolutionScale = (float) ScreenResWidth / 1280;
	}
	
	public Properties(int ScreenWidth, int ScreenHeight, boolean fullscreen, boolean vsync) {
		ScreenResWidth = ScreenWidth;
		ScreenResHeight = ScreenHeight;
		Fullscreen = fullscreen;
		VSync = vsync;
		FPSLimit = 60;
		ResolutionScale = (float) ScreenResWidth / 1280;
	}
	
	public void setScreenResWidth(int width) {
		ScreenResWidth = width;
	}
	
	public void setScreenResHeight(int height) {
		ScreenResHeight = height;
	}
	
	public void setFullscreen(boolean fullscreen) {
		Fullscreen = fullscreen;
	}
	
	public void setVSync(boolean vsync) {
		VSync = vsync;
	}
	
	public void setFPSLimit(int limit) {
		FPSLimit = limit;
	}
}
