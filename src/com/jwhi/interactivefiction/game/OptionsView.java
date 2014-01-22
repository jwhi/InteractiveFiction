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
			case (Input.KEY_UP):
				Properties.BackgroundTransparency += 10;
				break;
			case (Input.KEY_DOWN):
				Properties.BackgroundTransparency -= 10;
				break;
			case (Input.KEY_LEFT):
				Properties.BackgroundTransparency -= 5;
				break;
			case (Input.KEY_RIGHT):
				Properties.BackgroundTransparency += 5;
				break;
			case (Input.KEY_SPACE):
				System.out.println(Properties.BackgroundTransparency);
				break;
			}
		}
		clearInput();
		
		int yLoc = StartY + OptionsFont.getLineHeight() + (SpaceBetweenLines*2);
		
		if (allowClick && Mouse.isButtonDown(0)) {
			if (((gc.getHeight() - Mouse.getY()) > yLoc) &&
					((gc.getHeight() - Mouse.getY()) < (yLoc + OptionsFont.getLineHeight()))) {
				if (Mouse.getX() > 120 && Mouse.getX() < 130) {
					if (Properties.FontSize > 11) {
						Properties.FontSize -= 2;
						Font awtFont = new java.awt.Font(Constants.DefaultFont, java.awt.Font.PLAIN, Properties.FontSize);
						TestFont = new TrueTypeFont((java.awt.Font) awtFont, false);
					}
				} else if (Mouse.getX() > 280 && Mouse.getX() < 290) {
					if (Properties.FontSize < 39) {
						Properties.FontSize += 2;
						Font awtFont = new java.awt.Font(Constants.DefaultFont, java.awt.Font.PLAIN, Properties.FontSize);
						TestFont = new TrueTypeFont((java.awt.Font) awtFont, false);
					}
				} else if (Mouse.getX() > 300 && Mouse.getX() < (300 + OptionsFont.getWidth("Default"))) {
					Properties.FontSize = Constants.DefaultFontSize;
					Font awtFont = new java.awt.Font(Constants.DefaultFont, java.awt.Font.PLAIN, Properties.FontSize);
					TestFont = new TrueTypeFont((java.awt.Font) awtFont, false);
				} else if (Mouse.getX() > 260 && Mouse.getX() < 270) {
					if (Properties.BackgroundTransparency > 5) {
						Properties.BackgroundTransparency -= 10;
					}
				}
			}
			
			yLoc += OptionsFont.getLineHeight() + SpaceBetweenLines;
			if (((gc.getHeight() - Mouse.getY()) > yLoc) &&
					((gc.getHeight() - Mouse.getY()) < (yLoc + OptionsFont.getLineHeight()))) {
				if (Mouse.getX() > 530 && Mouse.getX() < 540) {
					if (Properties.BackgroundTransparency < 255) {
						Properties.BackgroundTransparency += 10;
					}
				} else if (Mouse.getX() > 550 && Mouse.getX() < (550 + OptionsFont.getWidth("Default"))) {
					Properties.BackgroundTransparency = Constants.DefaultBackgroundTransparency;
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
