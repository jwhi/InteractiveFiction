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
	
	public void testPage () {
		Contents.add("This is a test page.");
		Contents.add("This currently does not have any images. Maybe in");
		Contents.add("the near future, it will be added.");
		Contents.add(" ");
		Contents.add("Lorem ipsum dolor sit amet, consectetur");
		Contents.add("adipiscing elit. Ut viverra scelerisque");
		Contents.add("condimentum. Morbi nisi ipsum, venenatis");
		Contents.add("vitae felis ac, faucibus dapibus sapien.");
		Contents.add("Etiam at elit pulvinar, varius felis");
		Contents.add("egestas, semper lorem.");
	}
	
	public ArrayList<String> getContents() {
		return Contents;
	}
}
