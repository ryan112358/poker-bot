package pokerbot.parser.bovada.properties;

import java.awt.Rectangle;

import pokerbot.parser.abstractions.TableProperties;

public class SixHandedProperties extends TableProperties {
	
	public SixHandedProperties() {
		super.WINDOW_WIDTH = 808;
		super.WINDOW_HEIHGT = 644;
		
		super.FOLD_BUTTON = new Rectangle(418,551,112,27);
		super.CHECK_BUTTON = new Rectangle(548,551,112,27);
		super.CALL_BUTTON = new Rectangle(548,551,112,27);
		super.BET_BUTTON = new Rectangle(678,551,112,27);
		super.RAISE_BUTTON = new Rectangle(678,551,112,27);
		
		super.BET_BOX = new Rectangle(677,592,114,14);
		super.CHAT_BOX = new Rectangle(16,618,374,11);
	
		int CARD_WIDTH = 21;
		int CARD_HEIGHT = 36;
		Rectangle P0_HOLE1 = new Rectangle(355,429,CARD_WIDTH,CARD_HEIGHT);
		Rectangle P0_HOLE2 = new Rectangle(405,429,CARD_WIDTH,CARD_HEIGHT);	
		Rectangle P1_HOLE1 = new Rectangle(53,355,CARD_WIDTH,CARD_HEIGHT);
		Rectangle P1_HOLE2 = new Rectangle(103,355,CARD_WIDTH,CARD_HEIGHT);
		Rectangle P2_HOLE1 = new Rectangle(53,142,CARD_WIDTH,CARD_HEIGHT);
		Rectangle P2_HOLE2 = new Rectangle(103,142,CARD_WIDTH,CARD_HEIGHT);
		Rectangle P3_HOLE1 = new Rectangle(355,68,CARD_WIDTH,CARD_HEIGHT);
		Rectangle P3_HOLE2 = new Rectangle(405,68,CARD_WIDTH,CARD_HEIGHT);
		Rectangle P4_HOLE1 = new Rectangle(655,142,CARD_WIDTH,CARD_HEIGHT);
		Rectangle P4_HOLE2 = new Rectangle(705,142,CARD_WIDTH,CARD_HEIGHT);
		Rectangle P5_HOLE1 = new Rectangle(655,355,CARD_WIDTH,CARD_HEIGHT);
		Rectangle P5_HOLE2 = new Rectangle(705,355,CARD_WIDTH,CARD_HEIGHT);
		super.HOLECARD_LOCATIONS = new Rectangle[][]
			{ { P0_HOLE1, P0_HOLE2 }, { P1_HOLE1, P1_HOLE2 }, { P2_HOLE1, P2_HOLE2 },
			{ P3_HOLE1, P3_HOLE2 }, { P4_HOLE1, P4_HOLE2 }, { P5_HOLE1, P5_HOLE2 } };	
		super.FLOP1 = new Rectangle(271,243,CARD_WIDTH,CARD_HEIGHT);
		super.FLOP2 = new Rectangle(325,243,CARD_WIDTH,CARD_HEIGHT);
		super.FLOP3 = new Rectangle(379,243,CARD_WIDTH,CARD_HEIGHT);
		super.TURN  = new Rectangle(433,243,CARD_WIDTH,CARD_HEIGHT);
		super.RIVER = new Rectangle(487,243,CARD_WIDTH,CARD_HEIGHT);
		super.COMMCARD_LOCATIONS = new Rectangle[] { FLOP1, FLOP2, FLOP3, TURN, RIVER };
		
		//Button rectangles should surround the letter 'D' by one pixel on each side
		int BUTTON_WIDTH = 12;
		int BUTTON_HEIGHT = 12;
		Rectangle P0_BUTTON = new Rectangle(263,416,BUTTON_WIDTH,BUTTON_HEIGHT); 
		Rectangle P1_BUTTON = new Rectangle(135,298,BUTTON_WIDTH,BUTTON_HEIGHT); 
		Rectangle P2_BUTTON = new Rectangle(229,161,BUTTON_WIDTH,BUTTON_HEIGHT); 
		Rectangle P3_BUTTON = new Rectangle(536,150,BUTTON_WIDTH,BUTTON_HEIGHT); 
		Rectangle P4_BUTTON = new Rectangle(669,269,BUTTON_WIDTH,BUTTON_HEIGHT); 
		Rectangle P5_BUTTON = new Rectangle(568,405,BUTTON_WIDTH,BUTTON_HEIGHT); 
		super.BUTTON_LOCATIONS = new Rectangle[]
			{ P0_BUTTON, P1_BUTTON, P2_BUTTON, P3_BUTTON, P4_BUTTON, P5_BUTTON };
	}
	
}
