package pokerbot.parser.abstractions;

import java.awt.image.BufferedImage;

import pokerbot.parser.bovada.properties.BovadaResources;

public class Resources {
	
	public static final BovadaResources BOVADA_RESOURCES = new BovadaResources();
	
	public BufferedImage TURN_INDICATOR_IMG;
	public BufferedImage[] CARD_IMAGES = new BufferedImage[52];
	public BufferedImage CARD_UNKNOWN;
	public BufferedImage[] DIGIT_IMAGES = new BufferedImage[10];
	public BufferedImage COMMA, POINT;
	public BufferedImage BUTTON;

}
