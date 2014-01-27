package com.jwhi.interactivefiction.game;

import java.awt.Font;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;


public class OptionsView extends BaseView {
	private Image BackgroundImage;
	private Color BackgroundOverlay = new Color(0,0,0,120);	
	private Color BackgroundColor = Color.black;
	private TrueTypeFont OptionsFont;
	private TrueTypeFont TestFont;
	private Color FontColor = Color.white;
	private int StartX = 10;
	private int StartY = 30;
	private int SpaceBetweenLines = 20;
	private boolean allowClick = false;
	
	public OptionsView (int state) {
		super(state);
		Font awtFont = new java.awt.Font(Constants.DefaultFont, java.awt.Font.PLAIN, 17);
		OptionsFont = new TrueTypeFont((java.awt.Font) awtFont, false);
		
		awtFont = new java.awt.Font(Constants.DefaultFont, java.awt.Font.PLAIN, Properties.FontSize);
		TestFont = new TrueTypeFont((java.awt.Font) awtFont, false);
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
		g.setBackground(BackgroundColor);
		if (BackgroundImage != null) {
			BackgroundOverlay = new Color(0,0,0,Properties.BackgroundTransparency);
			BackgroundImage.draw(0, 0);
			g.setColor(BackgroundOverlay);
			g.fillRect(0, 0, gc.getWidth(), gc.getHeight());
		}
		
		TestFont.drawString(StartX, StartY, "Text used in the game screens.");

		int currentY = StartY + OptionsFont.getLineHeight() + (SpaceBetweenLines*2);
		
		
		OptionsFont.drawString(StartX, currentY, "Font Size: ", FontColor);
		int currentX = StartX + OptionsFont.getWidth("Font Size: ");
		
		// Draw the left arrow that will be used as the button to lower the setting
		// Add the coordinates of the arrow to the array used to keep track of the buttons
		OptionsFont.drawString(currentX, currentY, "<", FontColor);
		
		currentX += OptionsFont.getWidth("<");
		// Draw the slider
		for (int i = 0; i < (Properties.FontSize-Constants.MinimumFontSize)/2; i++) {
			OptionsFont.drawString(currentX, currentY, "-");
			currentX += OptionsFont.getWidth("-");
		}
		OptionsFont.drawString(currentX, currentY, "x");
		currentX += OptionsFont.getWidth("x");
		for (int i = 0; i < (14 - (Properties.FontSize-Constants.MinimumFontSize)/2); i++) {
			OptionsFont.drawString(currentX, currentY, "-");
			currentX += OptionsFont.getWidth("-");
		}
		OptionsFont.drawString(currentX, currentY, "> Default");
		currentX += OptionsFont.getWidth("> Default");
		
		
		
		currentY = currentY + OptionsFont.getLineHeight() + SpaceBetweenLines;
		OptionsFont.drawString(StartX, currentY, "Background Transparency: ", FontColor);
		currentX = StartX + OptionsFont.getWidth("Background Transparency: ");
		OptionsFont.drawString(currentX, currentY, "<", FontColor);
		currentX += OptionsFont.getWidth("<");
		// Draw the slider
		for (int i = 0; i < (Properties.BackgroundTransparency-Constants.MinimumTransparency)/10; i++) {
			OptionsFont.drawString(currentX, currentY, "-");
			currentX += OptionsFont.getWidth("-");
		}
		OptionsFont.drawString(currentX, currentY, "x");
		currentX += OptionsFont.getWidth("x");
		for (int i = 0; i < (25 - (Properties.BackgroundTransparency-Constants.MinimumTransparency)/10); i++) {
			OptionsFont.drawString(currentX, currentY, "-");
			currentX += OptionsFont.getWidth("-");
		}
		OptionsFont.drawString(currentX, currentY, "> Default");
		currentX += OptionsFont.getWidth("> Default");
		
		
		
		currentY = currentY + OptionsFont.getLineHeight() + (SpaceBetweenLines*2);
		OptionsFont.drawString(StartX, currentY, "Return", FontColor);
		
		currentY = currentY + OptionsFont.getLineHeight() + (SpaceBetweenLines*2);
		OptionsFont.drawString(StartX, currentY, "Exit Game", FontColor);
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		if (KeyPressed) {
			switch(Key) {
			case (Input.KEY_ESCAPE):
				sbg.enterState(PreviousState);
				break;
			}
		}
		clearInput();
		
		int yLoc = StartY + OptionsFont.getLineHeight() + (SpaceBetweenLines*2);
		
		if (allowClick && Mouse.isButtonDown(0)) {
			
			int xLoc = StartX + OptionsFont.getWidth("Font Size: ");
			
			if (((gc.getHeight() - Mouse.getY()) > yLoc) &&
					((gc.getHeight() - Mouse.getY()) < (yLoc + OptionsFont.getLineHeight()))) {
				if (Mouse.getX() > xLoc && Mouse.getX() < xLoc + OptionsFont.getWidth("<")) {
					if (Properties.FontSize > 11) {
						Properties.FontSize -= 2;
						Font awtFont = new java.awt.Font(Constants.DefaultFont, java.awt.Font.PLAIN, Properties.FontSize);
						TestFont = new TrueTypeFont((java.awt.Font) awtFont, false);
					}
				}
				
				xLoc += OptionsFont.getWidth("<-X-------------");
				if (Mouse.getX() > xLoc && Mouse.getX() < xLoc + OptionsFont.getWidth(">")) {
					if (Properties.FontSize < 39) {
						Properties.FontSize += 2;
						Font awtFont = new java.awt.Font(Constants.DefaultFont, java.awt.Font.PLAIN, Properties.FontSize);
						TestFont = new TrueTypeFont((java.awt.Font) awtFont, false);
					}
				}
				
				xLoc += OptionsFont.getWidth("> ");
				if (Mouse.getX() > xLoc && Mouse.getX() < (xLoc + OptionsFont.getWidth("Default"))) {
					Properties.FontSize = Constants.DefaultFontSize;
					Font awtFont = new java.awt.Font(Constants.DefaultFont, java.awt.Font.PLAIN, Properties.FontSize);
					TestFont = new TrueTypeFont((java.awt.Font) awtFont, false);
				}
			}
			xLoc = StartX + OptionsFont.getWidth("Background Transparency: ");
			yLoc += OptionsFont.getLineHeight() + SpaceBetweenLines;
			if (((gc.getHeight() - Mouse.getY()) > yLoc) &&
					((gc.getHeight() - Mouse.getY()) < (yLoc + OptionsFont.getLineHeight()))) {
				if (Mouse.getX() > xLoc && Mouse.getX() < xLoc + OptionsFont.getWidth("<")) {
					if (Properties.BackgroundTransparency > 5) {
						Properties.BackgroundTransparency -= 10;
					}
				}
				
				xLoc += OptionsFont.getWidth("<------X-------------------");
				if (Mouse.getX() > xLoc && Mouse.getX() < xLoc + OptionsFont.getWidth(">")) {
					if (Properties.BackgroundTransparency < 255) {
						Properties.BackgroundTransparency += 10;
					}
				}
				
				xLoc += OptionsFont.getWidth("> ");
				if (Mouse.getX() > xLoc && Mouse.getX() < (xLoc + OptionsFont.getWidth("Default"))) {
					Properties.BackgroundTransparency = Constants.DefaultBackgroundTransparency;
				}
			}
			
			xLoc = StartX;
			yLoc += OptionsFont.getLineHeight() + (SpaceBetweenLines*2);
			if (((gc.getHeight() - Mouse.getY()) > yLoc) &&
					((gc.getHeight() - Mouse.getY()) < (yLoc + OptionsFont.getLineHeight()))) {
				if (Mouse.getX() > xLoc && Mouse.getX() < xLoc + OptionsFont.getWidth("Return")) {
					sbg.enterState(PreviousState);
				}
			}
		
			yLoc += OptionsFont.getLineHeight() + (SpaceBetweenLines*2);
			if (((gc.getHeight() - Mouse.getY()) > yLoc) &&
					((gc.getHeight() - Mouse.getY()) < (yLoc + OptionsFont.getLineHeight()))) {
				if (Mouse.getX() > xLoc && Mouse.getX() < xLoc + OptionsFont.getWidth("Exit Game")) {
					gc.exit();
				}
			}
			
			allowClick = false;
		}
		
		if (!allowClick && !Mouse.isButtonDown(0)) {
			allowClick = true;
		}
		
		clearInput();
	}
	
	public void setBackgroundImage(Image image) {
		BackgroundImage = image;
	}
}
