package com.jwhi.interactivefiction.game;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.newdawn.slick.*;

public class TextHandler {
	private TrueTypeFont GameFont;
	private TrueTypeFont LinkFont;
//	private TrueTypeFont HeaderFont;
	private Color FontColor;
	private Color LinkColor;
	private int MouseMinX;
	private int MouseMaxX;
	private int MouseMinY;
	private int MouseMaxY;
	private String FontName = "Courier New";
//	private int HeaderSize = 5;
	
	public TextHandler () {
		java.awt.Font awtFont = new java.awt.Font(FontName, java.awt.Font.PLAIN, Properties.FontSize);
		GameFont = new TrueTypeFont((java.awt.Font) awtFont, false);
		
		awtFont = new java.awt.Font(FontName, java.awt.Font.BOLD, Properties.FontSize);
		LinkFont = new TrueTypeFont((java.awt.Font) awtFont, false);
		
		FontColor = Color.white;
		LinkColor = Color.cyan;
	}
	
	public void setGameFont (TrueTypeFont font) {
		GameFont = font;
	}
	
	public TrueTypeFont getGameFont () {
		return GameFont;
	}

	
	public void setFontColor (Color color) {
		FontColor = color;
	}
	
	public Color getFontColor () {
		return FontColor;
	}
	
	public void setLinkColor (Color color) {
		LinkColor = color;
	}
	
	public void drawHeader(String[] strings) {
		
	}
	
	public void drawStrings(String[] strings) {
		int lineX = 10;
		int lineY = 30;
		for (int i = 0; i < strings.length; i++) {
			if (strings[i].contains("[[") && strings[i].contains("]]")) {
				String s1 = strings[i].substring(0, strings[i].indexOf('['));
				Pattern p = Pattern.compile("\\[\\[(.*?)\\]\\]");
				Matcher m = p.matcher(strings[i]);
				
				String s2 = "";
				while(m.find()) {
				    s2 = (m.group(1));
				}
				String s3 = strings[i].substring(strings[i].indexOf(']')+2);
				GameFont.drawString(lineX, lineY, s1, FontColor);
				lineX += GameFont.getWidth(s1);
				LinkFont.drawString(lineX, lineY, s2, LinkColor);
				MouseMinX = lineX;
				MouseMaxX = lineX + LinkFont.getWidth(s2);
				MouseMinY = lineY;
				MouseMaxY = lineY + LinkFont.getLineHeight(); 
				lineX += LinkFont.getWidth(s2);
				GameFont.drawString(lineX, lineY, s3, FontColor);
			} else {
				GameFont.drawString(lineX, lineY, strings[i]);
			}
			lineY = lineY + LinkFont.getLineHeight() + (i*GameFont.getLineHeight());
		}
	}
	
	public boolean textClicked(int MouseX, int MouseY) {
		if ((MouseX > MouseMinX) && (MouseX < MouseMaxX) && (MouseY > MouseMinY) && (MouseY < MouseMaxY)) {
			System.out.println("Link is clicked for book!");
			return true;
		}
		
		return false;
	}
	
	public void updateFontSize() {
		java.awt.Font awtFont = new java.awt.Font(FontName, java.awt.Font.PLAIN, Properties.FontSize);
		GameFont = new TrueTypeFont((java.awt.Font) awtFont, false);
		
		awtFont = new java.awt.Font(FontName, java.awt.Font.BOLD, Properties.FontSize);
		LinkFont = new TrueTypeFont((java.awt.Font) awtFont, false);
	}
}
