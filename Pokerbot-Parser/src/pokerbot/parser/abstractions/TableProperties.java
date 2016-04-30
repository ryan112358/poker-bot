package pokerbot.parser.abstractions;

import java.awt.Rectangle;

import pokerbot.parser.bovada.properties.NineHandedProperties;
import pokerbot.parser.bovada.properties.SixHandedProperties;

public class TableProperties {
	
	public static final SixHandedProperties BOVADA_SIXHANDED = new SixHandedProperties();
	public static final NineHandedProperties BOVADA_NINEHANDED = new NineHandedProperties();
	
	public int WINDOW_WIDTH;
	public int WINDOW_HEIHGT;
	
	public Rectangle HEADER;
	public Rectangle CLOSE;
	public Rectangle CASCADE;
	public Rectangle HAND_ID;
	
	public Rectangle FOLD_BUTTON;
	public Rectangle CHECK_BUTTON;
	public Rectangle CALL_BUTTON;
	public Rectangle BET_BUTTON;
	public Rectangle RAISE_BUTTON;
	
	public Rectangle BET_BOX;
	public Rectangle CHAT_BOX;

	public Rectangle[][] HOLECARD_LOCATIONS;
	
	public Rectangle FLOP1;
	public Rectangle FLOP2;
	public Rectangle FLOP3;
	public Rectangle TURN;
	public Rectangle RIVER;
	public Rectangle[] COMMCARD_LOCATIONS;
	/** Location of area on screen that can be used to determine if it's my turn */
	public Rectangle TURN_INDICATOR;
	
	public Rectangle[] BUTTON_LOCATIONS;
	
	public Rectangle[] CHIPS_LOCATIONS;
	
	public Resources RESOURCES;
	
	public Rectangle[] ACTION_BAR;

}
