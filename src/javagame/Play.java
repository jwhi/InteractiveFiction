package javagame;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.TextField;

import scenes.*;

public class Play extends BasicGameState {

	private static int SceneX = 558;
	private static int SceneY = 48;
	private static int SceneWidth = 248;
	private static int SceneHeight = 383;
	
	private Image Background;
	private Color BackgroundColor;
	private Color BorderColor;
	private Color TextBorderColor;
	private Color TextBackgroundColor;
	private BasicScene scene;
	private String ActionText;
	private boolean AllowClick = true;
	
	private Color FontColor;
	private TrueTypeFont GameFont;
	private Color CursorColor;
	private TrueTypeFont CursorFont;
	private TextField tf;
	private boolean ShowFPS = true;
	private boolean AllowESC = true;
	
	public Play(int state) {
		
	}
	
	/**
	 * Initializes all the images and animations for the Play state.
	 * Background is the  entire game screens background image.
	 * BackgroundColor is the color that is used for the background image.
	 * sceneBackground is the background of the scene portion of the game screen.
	 * sceneProps is the image used to be in the foreground of the scene. This scene is a cabin and a farm.
	 * sceneActors is the image for the NPCS in the scene. This scene has a sole farmer.
	 * 
	 * @param gc	The container holding the game
	 * @param sbg	The game holding the state
	 */
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
//		Background = new Image("res/backgrounds/Pipes.png");
		Background = new Image("res/backgrounds/Sky.png");
		BackgroundColor = new Color(32, 97, 244); // A nice blue background used for the sky
		BorderColor = Color.black;
		TextBackgroundColor = Color.lightGray;
		TextBorderColor = Color.gray;
		scene = new FarmScene();
		ActionText = "";
		FontColor = Color.white;
		
		// Courier 12 is 7 pixels wide
		// Use the Courier font for the game text. A nice monospaced font for now.
		java.awt.Font awtFont = new java.awt.Font("Courier", java.awt.Font.PLAIN, 12);
		GameFont = new TrueTypeFont((java.awt.Font) awtFont, false);
		
		// Color and style of the font that is used for entering text.
		CursorColor = Color.white;
		awtFont = new java.awt.Font("Courier", java.awt.Font.BOLD, 12);
		CursorFont = new TrueTypeFont((java.awt.Font) awtFont, false);
		
		// Text field for entering text
		tf = new TextField(gc, CursorFont, 76, 348, 420, 24, new ComponentListener() {
			public void componentActivated(AbstractComponent source) {
				if (tf.getText().length() > 0) {
					ActionText = scene.executeCommand(tf.getText());
					tf.setText("");
				}
			}
		});
		
		// Make text box blend in with the text background
		tf.setBackgroundColor(TextBackgroundColor);
		tf.setBorderColor(TextBackgroundColor);
		// Limit the text box to the width of the text background
		tf.setMaxLength(60);
		// Allows user to enter text
		tf.setFocus(true);
		
		// Render the game even window is not active. Noticed weird graphic glitches when window wasn't focused.
		gc.setAlwaysRender(true);
		
		// This will print out all the available fonts for the system
//		java.awt.GraphicsEnvironment gw = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment();
//		FontFamilies = gw.getAvailableFontFamilyNames();
//		for (String s : FontFamilies) {
//			System.out.println(s);
//		}
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		// Color of border
		g.setBackground(BorderColor);
		
		// Color used for the background image
		g.setColor(BackgroundColor);
		
		// Rectangle of the background color used to colorize the black and white background image.
		g.fillRect(11, 16, Background.getWidth(), Background.getHeight());
		g.drawImage(Background, 11, 16);
		
		// Border around text background
		g.setColor(TextBorderColor);
		g.fillRect(32, 32, 494, 416);
		
		// Text Background
		g.setColor(TextBackgroundColor);
		g.fillRect(48, 48, 462, 384);
		
		// Border around animated scene
		g.setColor(TextBorderColor);
		g.fillRect(542, 32, 280, 415);
		
		// Background that will be covered up by the animated scene
		g.setColor(TextBackgroundColor);
		g.fillRect(SceneX, SceneY, SceneWidth, SceneHeight);
		
		// Render the scene
		scene.render(g);
		
		// Draw game text
		drawGameText(g);
		
		// Renders the text box used to enter commands
		tf.render(gc, g);
		
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		if (ActionText == "Exiting...") {
			// If the exit command is used, InvalidCommandText becomes equal to Exiting...
			// When this happens, call the game container's exit function
			gc.exit();
		}
		
		// The text field would no longer be focus if the window was clicked or switched to another window
		// Wasn't able to click in the text field to focus, this allows the user to always be able to enter text.
		tf.setFocus(true);
		
		Input input = gc.getInput();
		
		// This will allow the animation to play for the proper duration even if framerate changes
		scene.updateAnimations(delta);
		
		// Escape key shows and hides the fps counter
		if (input.isKeyDown(Input.KEY_ESCAPE) && AllowESC) {
//			sbg.enterState(0); Would enter the game state with the id 0, currently the loading
			ShowFPS = !ShowFPS;
			gc.setShowFPS(ShowFPS);
			AllowESC = false;
		}
		
		// Makes sure the command is executed only once for each press of escape
		if (!input.isKeyDown(Input.KEY_ESCAPE)) {
			AllowESC = true;
		}
		
		// The coordinates for the mouse that contains the camp fire.
		// AllowClick makes sure the left mouse button is released before running this code again 
		if (Mouse.isButtonDown(0) && AllowClick)
		{
			scene.sceneCliked(Mouse.getX(), Mouse.getY());
			AllowClick = false;
		}
		if (!Mouse.isButtonDown(0)) {
			AllowClick = true;
		}	
	}
	
	public void drawGameText(Graphics g) throws SlickException {
		g.setColor(FontColor);
		// Want to read these descriptions from a file in the future.
		GameFont.drawString(62, 60, "This takes up the width since the backgrounds fit 60 chars!!");
		GameFont.drawString(62, 72, "Hello, World!");
		GameFont.drawString(62, 84, "There is a cabin here with a old farmer.");
		GameFont.drawString(62, 96, "There is a pile of wood next to the cabin ready to be lit.");
		
		// After the description for the scene, writes the text used for invalid commands as well as the exiting... text
		if (ActionText.length() > 0) {
			GameFont.drawString(62, 300, ActionText);
		}
		
		// Draws the text that is displayed before the text box
		g.setColor(CursorColor);
		CursorFont.drawString(62, 348, "> ");
	}

	
	public int getID() {
		return 1;
	}
}
