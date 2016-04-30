package pokerbot.parser.bovada.properties;

import java.awt.Rectangle;

import pokerbot.parser.abstractions.Resources;
import pokerbot.parser.abstractions.TableProperties;

public class NineHandedProperties extends TableProperties {
	
	public NineHandedProperties() {
		super.WINDOW_WIDTH = 808;
		super.WINDOW_HEIHGT = 644;
		super.HAND_ID = new Rectangle(87,64,74,15);

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
		Rectangle P1_HOLE1 = new Rectangle(147,414,CARD_WIDTH,CARD_HEIGHT);
		Rectangle P1_HOLE2 = new Rectangle(197,414,CARD_WIDTH,CARD_HEIGHT);
		Rectangle P2_HOLE1 = new Rectangle(43,294,CARD_WIDTH,CARD_HEIGHT);
		Rectangle P2_HOLE2 = new Rectangle(93,294,CARD_WIDTH,CARD_HEIGHT);
		Rectangle P3_HOLE1 = new Rectangle(53,158,CARD_WIDTH,CARD_HEIGHT);
		Rectangle P3_HOLE2 = new Rectangle(103,158,CARD_WIDTH,CARD_HEIGHT);
		Rectangle P4_HOLE1 = new Rectangle(241,68,CARD_WIDTH,CARD_HEIGHT);
		Rectangle P4_HOLE2 = new Rectangle(291,68,CARD_WIDTH,CARD_HEIGHT);
		Rectangle P5_HOLE1 = new Rectangle(462,68,CARD_WIDTH,CARD_HEIGHT);
		Rectangle P5_HOLE2 = new Rectangle(512,68,CARD_WIDTH,CARD_HEIGHT);
		Rectangle P6_HOLE1 = new Rectangle(655,158,CARD_WIDTH,CARD_HEIGHT);
		Rectangle P6_HOLE2 = new Rectangle(705,158,CARD_WIDTH,CARD_HEIGHT);
		Rectangle P7_HOLE1 = new Rectangle(665,294,CARD_WIDTH,CARD_HEIGHT);
		Rectangle P7_HOLE2 = new Rectangle(715,294,CARD_WIDTH,CARD_HEIGHT);
		Rectangle P8_HOLE1 = new Rectangle(550,408,CARD_WIDTH,CARD_HEIGHT);
		Rectangle P8_HOLE2 = new Rectangle(600,408,CARD_WIDTH,CARD_HEIGHT);
		//PokerUser 8 might be different
		super.HOLECARD_LOCATIONS = new Rectangle[][]
			{ { P0_HOLE1, P0_HOLE2 }, { P1_HOLE1, P1_HOLE2 }, { P2_HOLE1, P2_HOLE2 },
			{ P3_HOLE1, P3_HOLE2 }, { P4_HOLE1, P4_HOLE2 }, { P5_HOLE1, P5_HOLE2 },
			{ P6_HOLE1, P6_HOLE2 }, { P7_HOLE1, P7_HOLE2 }, { P8_HOLE1, P8_HOLE2 } };
		
		super.FLOP1 = new Rectangle(271,243,CARD_WIDTH,CARD_HEIGHT);
		super.FLOP2 = new Rectangle(325,243,CARD_WIDTH,CARD_HEIGHT);
		super.FLOP3 = new Rectangle(379,243,CARD_WIDTH,CARD_HEIGHT);
		super.TURN  = new Rectangle(433,243,CARD_WIDTH,CARD_HEIGHT);
		super.RIVER = new Rectangle(487,243,CARD_WIDTH,CARD_HEIGHT);
		super.COMMCARD_LOCATIONS = new Rectangle[] { FLOP1, FLOP2, FLOP3, TURN, RIVER };
		//Location of area on screen that can be used to determine if it's my turn
		super.TURN_INDICATOR = new Rectangle(353,502,18,14);
		super.RESOURCES = Resources.BOVADA_RESOURCES;
		
		/*
		    chips1=(441,110)(541,135)
			chips2=(632,200)(732,225)
			chips3=(643,337)(743,362)
			chips4=(540,458)(640,483)
			chips5=(333,470)(433,495)
			chips6=(170,458)(270,483)
			chips7=(67,337)(167,362)
			chips8=(77,200)(177,225)
			chips9=(265,111)(365,136)
		 */
		int CHIPS_WIDTH = 100;
		int CHIPS_HEIGHT = 25;
		super.CHIPS_LOCATIONS = new Rectangle[] {
				new Rectangle(333,470,CHIPS_WIDTH,CHIPS_HEIGHT),
				new Rectangle(170,458,CHIPS_WIDTH,CHIPS_HEIGHT),
				new Rectangle(67,337,CHIPS_WIDTH,CHIPS_HEIGHT),
				new Rectangle(77,200,CHIPS_WIDTH,CHIPS_HEIGHT),
				new Rectangle(265,111,CHIPS_WIDTH,CHIPS_HEIGHT),
				new Rectangle(441,110,CHIPS_WIDTH,CHIPS_HEIGHT),
				new Rectangle(632,200,CHIPS_WIDTH,CHIPS_HEIGHT),
				new Rectangle(643,337,CHIPS_WIDTH,CHIPS_HEIGHT),
				new Rectangle(540,458,CHIPS_WIDTH,CHIPS_HEIGHT)
		};
		
		int BUTTON_WIDTH = 12;
		int BUTTON_HEIGHT = 12;
		super.BUTTON_LOCATIONS = new Rectangle[] {
				new Rectangle(317,419,BUTTON_WIDTH,BUTTON_HEIGHT),
				new Rectangle(190,392,BUTTON_WIDTH,BUTTON_HEIGHT),
				new Rectangle(159,282,BUTTON_WIDTH,BUTTON_HEIGHT),
				new Rectangle(194,179,BUTTON_WIDTH,BUTTON_HEIGHT),
				new Rectangle(381,150,BUTTON_WIDTH,BUTTON_HEIGHT),
				new Rectangle(574,165,BUTTON_WIDTH,BUTTON_HEIGHT),
				new Rectangle(633,253,BUTTON_WIDTH,BUTTON_HEIGHT),
				new Rectangle(620,368,BUTTON_WIDTH,BUTTON_HEIGHT),
				new Rectangle(516,416,BUTTON_WIDTH,BUTTON_HEIGHT),
		};
		
		super.HEADER = new Rectangle(5,5,600,30);
		super.CLOSE = new Rectangle(755,5,40,10);
		super.CASCADE = new Rectangle(680,5,15,10);
	}

}
