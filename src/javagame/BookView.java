package javagame;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import books.Book;
import books.Page;

public class BookView extends BasicGameState {

	Book Reading;
	TrueTypeFont BookFont;
	TrueTypeFont BookFontBold;
	int buttonMinX = 0;
	int buttonMaxX = 0;
	int buttonMinY = 0;
	int buttonMaxY = 0;
	Page FirstPage;
	Page SecondPage;
	int currentPageView = 0;
	
	public BookView (int state) {
		java.awt.Font awtFont = new java.awt.Font("Courier", java.awt.Font.PLAIN, 14);
		BookFont = new TrueTypeFont((java.awt.Font) awtFont, false);
		awtFont = new java.awt.Font("Courier", java.awt.Font.BOLD, 16);
		BookFontBold = new TrueTypeFont((java.awt.Font) awtFont, false);
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		Reading = new Book(100000);
		
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		int x = 24;
		int y = 24;
		if (Reading != null) {
			Image background;
			if (Reading.getBackgroundColor() != null) {
				background = new Image("res/backgrounds/books/" + Reading.getBackgroundColor() + ".png");
			} else {
				background = new Image("res/backgrounds/books/Black.png");
			}
			background.draw(0, 0);
			for (Page p : Reading.getPages()) {
				for (String s : p.getContents()) {
					BookFont.drawString(x, y, s, Color.black);
					y += BookFont.getLineHeight();
				}
				x = 319;
				y = 24;
			}
		}
		
		g.setColor(Color.white);
		x = 723-((BookFontBold.getWidth(Reading.getTitle())+10)/2);
		y = 80;
		BookFontBold.drawString(x, y, Reading.getTitle());
		x = 723-((BookFont.getWidth(Reading.getAuthor())+10)/2);
		y = y + (BookFont.getLineHeight()) + 5;
		BookFont.drawString(x, y, Reading.getAuthor());
		x = 723-((BookFont.getWidth("Return")+10)/2);
		y = 416;
		buttonMinX = x;
		buttonMaxX = x + BookFont.getWidth("Return") + 10;
		buttonMinY = y;
		buttonMaxY = y + BookFont.getLineHeight() + 10;
		g.drawRect(x, y, BookFont.getWidth("Return")+10, BookFont.getLineHeight()+10);
		BookFont.drawString(x+5, y+5, "Return", Color.white);
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		if (Mouse.isButtonDown(0)) {
			if ((Mouse.getX() > buttonMinX) && (Mouse.getX() < buttonMaxX)
					&& (gc.getHeight() - Mouse.getY() > buttonMinY) && (gc.getHeight() - Mouse.getY() < buttonMaxY)) {
				sbg.enterState(1);
			}
		}
		
		
	}
	
	public void setID(int id) {
		Reading = new Book(id);
	}

	public int getID() {
		return 2;
	}
}
