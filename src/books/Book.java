package books;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import nu.xom.*;

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
//		Page test = new Page();
//		test.testPage();
//		Pages.add(test);
		ReadXML(id);
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
	
	public void ReadXML(int id) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("res/books/100000.xml"));
			Builder parser = new Builder();
			Document doc = parser.build(reader);
			Element root = doc.getRootElement();
			listChildren(root, 0);
		} catch (ParsingException ex) {
			System.err.println("Book XML is malformed.");
			ex.printStackTrace();
		} catch (IOException ex) {
			System.err.println("Could not read from file.");
		} catch(Exception e) {	
			e.printStackTrace();
		}
	}
	
	public void listChildren(Node current, int depth) {
		for (int i = 0; i < depth; i++) {
			System.out.print(" ");
		}
		String data = "";
		
		if (current instanceof Element) {
			Element temp = (Element) current;
			data = ": " + temp.getQualifiedName();
			if (temp.getQualifiedName().compareTo("title") == 0) {
				Title = temp.getChild(0).getValue();
				System.out.println("Title: " + Title);
			} else if (temp.getQualifiedName().compareTo("author") == 0) {
				Author = temp.getChild(0).getValue();
				System.out.println("Author: " + Author);
			} else if (temp.getQualifiedName().compareTo("pages") == 0) {
				Page p = new Page();
				for (int i = 0; i < temp.getChildCount(); i++) {
					Node pagesChild = temp.getChild(i);
					if (pagesChild instanceof Element) {
						if (((Element) pagesChild).getQualifiedName().compareTo("page") == 0) {
							for (int j = 0; j < pagesChild.getChildCount(); j++) {
								Node pageContent = pagesChild.getChild(j);
								if (pageContent instanceof Element) {
									if (((Element) pageContent).getQualifiedName().compareTo("text") == 0) {
										p.addText(pageContent.getChild(0).getValue());
									}
								}
							}
						}
					}
				}
				
				Pages.add(p);
			}
		} else if (current instanceof ProcessingInstruction) {
			ProcessingInstruction temp = (ProcessingInstruction) current;
			data = ": " + temp.getTarget();
		} else if (current instanceof DocType) {
			DocType temp = (DocType) current;
			data = ": " + temp.getRootElementName();
		} else if (current instanceof Text || current instanceof Comment) {
			String value = current.getValue();
			value = value.replace('\n', ' ').trim();
			if (value.length() <= 20) data = ": " + value;
			else data = ": " + current.getValue().substring(0, 17) + "...";
		}
		// Attributes are never returned by getChild()
		// System.out.println(current.getClass().getName() + data);
		for (int i = 0; i < current.getChildCount(); i++) {
			listChildren(current.getChild(i), depth+1);
		}
	}
}
