package javagame;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import books.Book;

public class BookView extends BasicGameState {

	Book testBook;
	TrueTypeFont BookFont;
	int buttonMinX = 0;
	int buttonMaxX = 0;
	int buttonMinY = 0;
	int buttonMaxY = 0;
	
	public BookView (int state) {
		java.awt.Font awtFont = new java.awt.Font("Courier", java.awt.Font.PLAIN, 14);
		BookFont = new TrueTypeFont((java.awt.Font) awtFont, false);
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		testBook = new Book(0);
		
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		int x = 10;
		int y = 10;
		if (testBook != null) {
			for (String s : testBook.getPageText(1)) {
				BookFont.drawString(x, y, s);
				y += BookFont.getLineHeight();
			}
		}
		
		y += (3*BookFont.getLineHeight());
		buttonMinX = x;
		buttonMaxX = x + BookFont.getWidth("Return") + 10;
		buttonMinY = y;
		buttonMaxY = y + BookFont.getLineHeight() + 10;
		g.drawRect(x, y, BookFont.getWidth("Return")+10, BookFont.getLineHeight()+10);
		BookFont.drawString(x+5, y+5, "Return");
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
		testBook = new Book(id);
	}

	public int getID() {
		return 2;
	}
}
