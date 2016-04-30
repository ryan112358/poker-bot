package pokerbot.parser.bovada.properties;

import pokerbot.gamestate.Card;
import pokerbot.parser.Utils;
import pokerbot.parser.abstractions.Resources;

public class BovadaResources extends Resources {
	
	private static final String folder = "../Pokerbot-Parser/res/bovada/";
	
	public BovadaResources() {
		TURN_INDICATOR_IMG = Utils.readImage(folder + "other/turn-indicator.png");
		for(Card card : Card.CARDS)
			CARD_IMAGES[card.getValue()] = Utils.readImage(folder + "cards/" + card.fileName());
		CARD_UNKNOWN = Utils.readImage(folder + "cards/" + Card.UNKNOWN.fileName());
		for(int i=0; i < 10; i++)
			DIGIT_IMAGES[i] = Utils.readImage(folder + "txt/"+i+".png");
		COMMA = Utils.readImage(folder + "txt/comma.png");
		POINT = Utils.readImage(folder + "txt/pt.png");
		BUTTON = Utils.readImage(folder + "other/button.png");
	}

}
