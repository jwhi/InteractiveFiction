package javagame;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.newdawn.slick.*;

public class TextHandler {
	private TrueTypeFont GameFont;
	private TrueTypeFont LinkFont;
	private Color FontColor;
	private Color LinkColor;
	private int MouseMinX;
	private int MouseMaxX;
	private int MouseMinY;
	private int MouseMaxY;
	
	public TextHandler () {
		java.awt.Font awtFont = new java.awt.Font("Courier", java.awt.Font.PLAIN, 12);
		GameFont = new TrueTypeFont((java.awt.Font) awtFont, false);
		
		awtFont = new java.awt.Font("Courier", java.awt.Font.BOLD, 12);
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
	
	public void setGameFont (String fontName, boolean bold, int size) {
		java.awt.Font awtFont = new java.awt.Font(fontName, (bold ? java.awt.Font.BOLD : java.awt.Font.PLAIN), size);
		GameFont = new TrueTypeFont((java.awt.Font)awtFont, false);
	}
	
	public void setLinkFont (TrueTypeFont font) {
		LinkFont = font;
	}
	
	public void setLinkFont (String fontName, boolean bold, int size) {
		java.awt.Font awtFont = new java.awt.Font(fontName, (bold ? java.awt.Font.BOLD : java.awt.Font.PLAIN), size);
		LinkFont = new TrueTypeFont((java.awt.Font)awtFont, false);
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
	
	public void drawStrings(String[] strings) {
		for (int i = 0; i < strings.length; i++) {

			int lineX = 62;
			int lineY = 60+(i*GameFont.getLineHeight());
			
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
		}
	}
	
	public void textClicked(int MouseX, int MouseY) {
		if ((MouseX > MouseMinX) && (MouseX < MouseMaxX) && (MouseY > MouseMinY) && (MouseY < MouseMaxY)) {
			System.out.println("Link is clicked for book!");
		}
	}
}
