package com.jwhi.interactivefiction.game;

public class Properties {
	private int ScreenResWidth = 854;
	private int ScreenResHeight = 480;
	private boolean Fullscreen = false;
	private boolean VSync = true;
	private int FPSLimit = 60;
	
	public Properties() {
		ScreenResWidth = 854;
		ScreenResHeight = 480;
		Fullscreen = false;
		VSync = true;
		FPSLimit = 60;
	}
	
	public Properties(int ScreenWidth, int ScreenHeight, boolean fullscreen, boolean vsync) {
		ScreenResWidth = ScreenWidth;
		ScreenResHeight = ScreenHeight;
		Fullscreen = fullscreen;
		VSync = vsync;
		FPSLimit = 60;
	}
	
	public int getScreenResWidth() {
		return ScreenResWidth;
	}
	
	public void setScreenResWidth(int width) {
		ScreenResWidth = width;
	}
	
	public int getScreenResHeight() {
		return ScreenResHeight;
	}
	
	public void setScreenResHeight(int height) {
		ScreenResHeight = height;
	}
	
	public boolean getFullscreen() {
		return Fullscreen;
	}
	
	public void setFullscreen(boolean fullscreen) {
		Fullscreen = fullscreen;
	}
	
	public boolean getVSync() {
		return VSync;
	}
	
	public void setVSync(boolean vsync) {
		VSync = vsync;
	}
	
	public int getFPSLimit() {
		return FPSLimit;
	}
	
	public void setFPSLimit(int limit) {
		FPSLimit = limit;
	}
}
