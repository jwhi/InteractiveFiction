package com.jwhi.interactivefiction.game;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.StateBasedGame;

public class IsometricView extends BaseView {
	private SpriteSheet Tiles;
	private int tile_map[][];
	private int object_map[][];
	private static int tile_width = 64;
	private static int tile_height = 32;
	private boolean HideOverlay = false;
	private boolean AllowClick = true;
	private Color backgroundColor;
	private String[] sceneDescription;
	private TextHandler textHandle;
	private Image imagePreview;
	private boolean updatePreview = true;
	
	public IsometricView (int state) throws SlickException {
		super(state);
		Tiles = new SpriteSheet(new Image("res/backgrounds/GrasslandTiles.png"),64,32);
		backgroundColor = new Color(0,0,0,120);		
		textHandle= new TextHandler();
		imagePreview = new Image(Properties.ScreenResWidth,Properties.ScreenResHeight);
		tile_map = new int[][]
				{{  0,  5,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
				{   0,  0,  0,  0,  0,  0,  0,  0,  0,  2,  0,  0,  0,  5,  0,  0,  0,  0,  0,  0},
				{   0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
				{   0,  0,  0,  0,  0,  0,  0,  0,  0,  6,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
				{   0,  0,  0,  0,  0,  0,  0,  0,  0,  4,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
				{   0,  0,  2,  0,  0,  0,  0,  0,  5,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
				{   1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
				{   0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
				{   0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
				{   0,  0,  0,  1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
				{   0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
				{   0,  0,  0,  0,  0,  0, 22, 23, 19, 26,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
				{   0,  0,  0,  0,  0,  0,  0,  0,  0, 21,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
				{   0,  0,  0,  0,  0,  0,  0,  0,  0, 20, 23,  0,  0,  0,  0,  0,  0,  0,  0,  0},
				{   0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
				{   0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
				{   0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
				{   0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
				{   0,  0,  0,  0,  3,  0,  0,  0,  5,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
				{   0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0}};
		
		// 94 might be a good transparent sprite
		object_map = new int[][]
				{{ 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94},
				{  94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94},
				{  94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94},
				{  94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94},
				{  94, 94, 94, 94, 94, 94, 94, 94, 94,504, 94, 94, 94, 94, 94, 94},
				{  94, 94, 94, 94, 94, 94, 94, 94,520, 94, 94, 94, 94,210, 94, 94},
				{  94, 94, 94, 94, 94, 94, 94,536, 94,521, 94, 94,226, 94, 94, 94},
				{  94, 94, 94, 94, 94, 94,552, 94,537, 94, 94, 94, 94, 94, 94, 94},
				{  94, 94, 94, 94, 94,568, 94,553, 94, 94, 94, 94, 94, 94, 94, 94},
				{  94, 94, 94, 94,584, 94,569, 94, 94, 94, 94, 94, 94, 94, 94, 94},
				{  94, 94, 94, 88, 94,585, 94, 94, 94, 94, 94, 94, 94,372, 94, 94},
				{  94,111,104,105, 94, 94, 94, 94, 94, 94,147, 94,388,373,358, 94},
				{  94,120,121, 90, 94, 94, 94, 94, 94, 94, 94,404,389,374, 94, 94},
				{  94, 94, 94, 94, 94, 94,578, 94, 94, 94,420,405,390,391, 94, 94},
				{  94, 94, 94, 94, 94,594, 94,579, 94, 94,421,406,407, 94, 94, 94},
				{  94, 94, 94, 94,610, 94,595, 94, 94, 94,422,423, 94, 94, 94, 94},
				{  94, 94, 94,626, 94,611, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94},
				{  94, 94,642, 94,627, 94, 94, 94, 94, 94, 94, 94, 94, 94,217, 94},
				{  94,658, 94,643, 94, 94, 94, 94, 94, 94, 94, 94, 94,233, 94, 94},
				{  94, 94,659, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94}};
		
		sceneDescription = new String[] {
				"This is a test of a possible new screen layout.",
				"I do not know how long this will last, but it is neat!",
				"Good trial. Does [[book]] work? Can't say..."
		};
		
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
	}
	
	@Override
	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
		AllowClick = false;
		textHandle.updateFontSize();
		super.enter(gc, sbg);
		backgroundColor = new Color(0,0,0,Properties.BackgroundTransparency);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		for (int i = 0; i < tile_map.length; i++) {
			for (int j = tile_map[i].length-1; j >= 0; j--) {
				int x = (int)(((j * tile_width / 2) + (i * tile_width / 2)) * Properties.ResolutionScale);
				int y = (int)(((i * tile_height / 2) - (j * tile_height / 2) + 360) * Properties.ResolutionScale);
				getSpriteImage(tile_map[i][j]).draw(x, y, Properties.ResolutionScale);
			}
		}
		
		for (int i = 0; i < object_map.length; i++) {
			for (int j = object_map[i].length-1; j >= 0; j--) {
				int x = (int)(((j * tile_width / 2) + (i * tile_width / 2)) * Properties.ResolutionScale);
				int y = (int)(((i * tile_height / 2) - (j * tile_height / 2) + 360) * Properties.ResolutionScale);
				getSpriteImage(object_map[i][j]).draw(x, y, Properties.ResolutionScale);
			}
		}
		
		
		if (updatePreview) {
			g.copyArea(imagePreview, 0, 0);
			updatePreview = false;
		}
		if (!HideOverlay) {
			g.setColor(backgroundColor);
			g.fillRect(0, 0, 1280*Properties.ResolutionScale, 720*Properties.ResolutionScale);
			textHandle.drawStrings(sceneDescription);
		}
	}
	
	public Image getSpriteImage (int imageNumber) {
		int x = imageNumber % 16;
		int y = imageNumber / 16;
		return Tiles.getSprite(x, y);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		
		if (KeyPressed || KeyHeld) {
			if (Key == Input.KEY_TAB) {
				HideOverlay = true;
			} else {
				HideOverlay = false;
			}
		}
		
		if (KeyPressed) {
			switch(Key) {
			case (Input.KEY_ESCAPE):
				((OptionsView)sbg.getState(Constants.Options)).setBackgroundImage(imagePreview);
				((BaseView)sbg.getState(Constants.Options)).setPreviousState(Constants.Isometric);
				sbg.enterState(Constants.Options);
				break;
			}
		} else {
			HideOverlay = false;
		}
		
		if (Mouse.isButtonDown(0) && AllowClick)
		{
			if (textHandle.textClicked(Mouse.getX(), gc.getHeight() - Mouse.getY())) {
				((BaseView)sbg.getState(Constants.Book)).PreviousState = Constants.Isometric;
				sbg.enterState(Constants.Book);
			}
			AllowClick = false;
		}
		
		if (!AllowClick) {
			if (!Mouse.isButtonDown(0)) {
				AllowClick = true;
			}	
		}
	}
}
