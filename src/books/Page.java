package books;

import java.util.ArrayList;

import org.newdawn.slick.Image;

public class Page {
	ArrayList<String> Contents;
	Image Picture;
	
	public Page () {
		Contents = new ArrayList<String>();
	}
	
	public void addText (String text) {
		Contents.add(text);
	}
	public void addImage (Image picture) {
		Picture = picture;
		// When displaying book text, renderer will see this and replace text with picture
		Contents.add("{{image}}");
	}
	
	public ArrayList<String> getContents() {
		return Contents;
	}
	
	public void addContents (String str) {
		Contents.add(str);
	}
}
