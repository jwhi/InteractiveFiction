package books;

import java.util.ArrayList;

public class Book {
	// The book's id will be a unique id number that will work as a way
	// to identify the book and will also provide a way to sort by type of book
	// by using different groups of numbers for different types of written media;
	private int ID;
	private String Title;
	private String Author;
	ArrayList<Page> Pages;
	
	public Book(int id) {
		ID = id;
		Pages = new ArrayList<Page>();
		// Use id to read from a file and then find the additional information about the book.
		Title = "Test Book";
		Author = "Unknown";
		Page test = new Page();
		test.testPage();
		Pages.add(test);
	}

	public String getTitle() {
		return Title;
	}

	public String getAuthor() {
		return Author;
	}
	
	public int getID() {
		return ID;
	}
	
	public ArrayList<Page> getPages() {
		return Pages;
	}
	
	public Page getPage(int pageNumber) {
		return getPages().get(pageNumber);
	}
	
	public ArrayList<String> getPageText(int pageNumber) {
		return getPages().get(pageNumber-1).getContents();
	}
}
