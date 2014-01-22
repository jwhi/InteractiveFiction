package com.jwhi.interactivefiction.game;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import com.jwhi.interactivefiction.books.Book;


public class BookView extends BaseView {
	Book Reading;
	TrueTypeFont BookFont;
	TrueTypeFont BookFontBold;
	Color UIColor = Color.white;
	private boolean allowClick = true;
	int buttonMinX = 0;
	int buttonMaxX = 0;
	int buttonMinY = 0;
	int buttonMaxY = 0;
	int currentPage = 0;	// currentPage is used to determine which two pages to display. Multiplied by 2 to figure out the page displayed on the left side of book.
	
	public BookView (int state) {
		super(state);
		java.awt.Font awtFont = new java.awt.Font("Courier", java.awt.Font.PLAIN, 14);
		BookFont = new TrueTypeFont((java.awt.Font) awtFont, false);
		awtFont = new java.awt.Font("Courier", java.awt.Font.BOLD, 16);
		BookFontBold = new TrueTypeFont((java.awt.Font) awtFont, false);
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		Reading = new Book(100000);
	}
	
	// Sets the current book to be displayed. ID it uses is the same as the XML of the desired book.
	// Returns true if the XML file exists for the given id, false if no XML is found.
	public boolean setBook(int id) {
		if (Book.isValidBook(id)) {
			Reading = new Book(id);
			return true;
		} else {
			return false;
		}
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
			
			// If the book has pages, display them two at a time.
			if (!Reading.getPages().isEmpty()) {
				for (String s : Reading.getPage(currentPage*2).getContents()) {
					BookFont.drawString(x, y, s, Color.black);
					y += BookFont.getLineHeight();
				}
				// First page starts at X = 18. Width of first page is 291 pixels. Half of 291 pixels for the center of the page is 163. Add the 18 pixels of the start of the page to find the center of the page's x coordinate.
				// Subtract the width of the page number divided by two to make sure the page number is centered.
				BookFont.drawString(163-(BookFont.getWidth(((Integer)((currentPage*2)+1)).toString())/2), 451-BookFont.getLineHeight(), ((Integer)((currentPage*2)+1)).toString(),Color.black);	//Print first page number
				// If 2 * currentPage is larger than the number of pages in the book, the second page won't be displayed
				if ((currentPage*2)+1 < Reading.getPages().size()) {
					x = 319;
					y = 24;
					for (String s : Reading.getPage((currentPage*2)+1).getContents()) {
						BookFont.drawString(x, y, s, Color.black);
						y += BookFont.getLineHeight();
					}
					// Second page starts at X = 313. Width of the second page is 273. Half of the width is 136. Add that to the start of the page to find the center of the second page's x coordinate.
					// Subtract the width of the page number divided by two to make sure the page number is centered.
					BookFont.drawString(449-(BookFont.getWidth(((Integer)((currentPage*2)+2)).toString())/2), 451-BookFont.getLineHeight(), ((Integer)((currentPage*2)+2)).toString(),Color.black);	//Print second page number
				}
			}
		}
		g.setColor(UIColor);
		x = 723-((BookFontBold.getWidth(Reading.getTitle())+10)/2);
		y = 80;
		BookFontBold.drawString(x, y, Reading.getTitle(), UIColor);
		x = 723-((BookFont.getWidth(Reading.getAuthor())+10)/2);
		y = y + (BookFont.getLineHeight()) + 5;
		BookFont.drawString(x, y, Reading.getAuthor(), UIColor);
		x = 723-((BookFont.getWidth("Return")+10)/2);
		y = 416;
		buttonMinX = x;
		buttonMaxX = x + BookFont.getWidth("Return") + 10;
		buttonMinY = y;
		buttonMaxY = y + BookFont.getLineHeight() + 10;
		// Draws the buttons for the Return, Next, and Previous buttons
		g.drawRect(x, y, BookFont.getWidth("Return")+10, BookFont.getLineHeight()+10);
		BookFont.drawString(x+5, y+5, "Return", UIColor);
		if ((currentPage*2)+2 < Reading.getPages().size()) {
			g.drawRect(x+BookFont.getWidth("Return")+20, y, BookFont.getWidth("Next")+10, BookFont.getLineHeight()+10);
			BookFont.drawString(x+BookFont.getWidth("Return")+25, y+5, "Next", UIColor);
		}
		if (currentPage != 0) {
			g.drawRect(x-BookFont.getWidth("Prev")-20, y, BookFont.getWidth("Prev")+10, BookFont.getLineHeight()+10);
			BookFont.drawString(x-BookFont.getWidth("Prev")-15, y+5, "Prev", UIColor);
		}
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		if (Mouse.isButtonDown(0) && allowClick) {
			if ((Mouse.getX() > buttonMinX) && (Mouse.getX() < buttonMaxX)
					&& (gc.getHeight() - Mouse.getY() > buttonMinY) && (gc.getHeight() - Mouse.getY() < buttonMaxY)) {
				// Need to reset the current page and return to previous screen
				currentPage = 0;
//				((Play) sbg.getState(1)).clearTextField();
				sbg.enterState(1);
			} else if ((Mouse.getX() > buttonMaxX+10) && (Mouse.getX() < buttonMaxX+BookFont.getWidth("Next")+20)
					&& (gc.getHeight() - Mouse.getY() > buttonMinY) && (gc.getHeight() - Mouse.getY() < buttonMaxY)
					&& ((currentPage*2)+2 < Reading.getPages().size())) {
				currentPage += 1;
			} else if ((Mouse.getX() > buttonMinX-BookFont.getWidth("Prev")-20) && (Mouse.getX() < buttonMinX-10)
					&& (gc.getHeight() - Mouse.getY() > buttonMinY) && (gc.getHeight() - Mouse.getY() < buttonMaxY)
					&& (currentPage != 0)) {
				currentPage -= 1;
			}
			allowClick = false;
		}
		
		// Keys for the next page are period, right bracket, right arrow key.
		// Previous page keys are comma, left bracket, left arrow key.
		// Return to scene keys are space, x, escape.
		if (KeyPressed) {
			if ((currentPage*2)+2 < Reading.getPages().size()) {
				switch (Key) {
				case (Input.KEY_PERIOD):
				case (Input.KEY_RBRACKET):
				case (Input.KEY_RIGHT):
					currentPage += 1;
					break;
				}
			}
			if (currentPage != 0) {
				switch (Key) {
				case (Input.KEY_COMMA):
				case (Input.KEY_LBRACKET):
				case (Input.KEY_LEFT):
					currentPage -= 1;
					break;
				}
			}
			switch(Key) {
			case (Input.KEY_SPACE):
			case (Input.KEY_X):
			case (Input.KEY_ESCAPE):
				currentPage = 0;
				sbg.enterState(PreviousState);
			}
		}
		
		clearInput();
		
		if (!Mouse.isButtonDown(0) && !allowClick) {
			allowClick = true;
		}
		
		
	}
	
	public void setID(int id) {
		Reading = new Book(id);
	}
}
