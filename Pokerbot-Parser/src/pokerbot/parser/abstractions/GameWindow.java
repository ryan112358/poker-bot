package pokerbot.parser.abstractions;

import java.awt.image.BufferedImage;
import java.util.Arrays;

import pokerbot.gamestate.BoardConfiguration;
import pokerbot.gamestate.Card;
import pokerbot.parser.Utils;
/**
 * This class was designed to be an abstraction for a GameWindow that can be extended
 * by other GameWindow classes for many different poker softwares.  This class handles 
 * most of the parsing logic, but the methods can be overridden if there is a special
 * case that needs to be considered.
 *
 */
public abstract class GameWindow extends Window {
	
	protected TableProperties properties;
	protected BufferedImage handID;
	protected Classifier classifier;
	
	protected GameWindow(TableProperties properties) {
		super(User32.INSTANCE.GetForegroundWindow());
		this.properties = properties;
		this.classifier = new Classifier(properties.RESOURCES);
	}
	/**
	 * @return a Float[] containing the chip amounts for every player at the table
	 */
	protected Float[] parseChips() {
		return Arrays.stream(properties.CHIPS_LOCATIONS).
						map(r -> classifier.getValue(super.getScreenShot(r))).
						toArray(Float[]::new);
	}
	
	protected int findButton() {
		int i=0;
		while(! classifier.isButton(super.getScreenShot(properties.BUTTON_LOCATIONS[i])))
			i++;
		return i;
	}
	
	protected Card[] parseCommCards() {
		return Arrays.stream(properties.COMMCARD_LOCATIONS)
						.map(r -> classifier.classifyCard(super.getScreenShot(r)))
						.toArray(Card[]::new);
	}
	
	protected Card[][] parseHoleCards() {
		return Arrays.stream(properties.HOLECARD_LOCATIONS)
						.map(r -> new Card[] { classifier.classifyCard(super.getScreenShot(r[0])),
												classifier.classifyCard(super.getScreenShot(r[1])) })
						.toArray(Card[][]::new);
	}
	
	public BoardConfiguration getBoardConfig() {
		handID = fetchHandID();
		BoardConfiguration ans = new BoardConfiguration();
		ans.setHoleCards(parseHoleCards());
		ans.setCommCards(parseCommCards());
		ans.setChips(parseChips());
		ans.setButton(findButton());
		return ans;
	}
	
	private BufferedImage fetchHandID() {
		return super.getScreenShot(properties.HAND_ID);
	}
	
	public boolean isNewHand() {
		return ! Utils.samePic(handID, fetchHandID());
	}
	
	public boolean hasDealt() {
		return classifier.classifyCard(super.getScreenShot(properties.HOLECARD_LOCATIONS[0][0])) != null;
	}
	 
	/**
	 * A method that parses a piece of the window looking for an indicator to see if it is my turn
	 * @return true if it is my turn to act, false otherwise
	 */
	public boolean isMyTurn() {
		return Utils.samePic(super.getScreenShot(properties.TURN_INDICATOR),
							 properties.RESOURCES.TURN_INDICATOR_IMG);
	} 
	/**
	 * Tells the robot to bet the given amount
	 * @param amt The amount to bet
	 */
	public void bet(float amt) {
		super.doubleClick(properties.BET_BOX);
		super.type(String.valueOf(amt));
		super.click(properties.BET_BUTTON);	
	}
	/**
	 * Tells the robot to call the incoming bet
	 */
	public void call() {
		super.click(properties.CALL_BUTTON);
	}
	/**
	 * Tells the robot to raise the bet to the given amount
	 * @param amt The amount to raise to
	 */
	public void raise(float amt) {
		super.doubleClick(properties.BET_BOX);
		super.type(String.valueOf(amt));
		super.click(properties.RAISE_BUTTON);
	}
	/**
	 * Tells the robot to check
	 */
	public void check() {
		super.click(properties.CHECK_BUTTON);
	}
	/**
	 * Tells the robot to fold
	 */
	public void fold() {
		super.click(properties.FOLD_BUTTON);
	}
	/**
	 * Tells the robot to send a message in the chat box
	 * @param message The message to send
	 */
	public void chat(String message) {
		super.doubleClick(properties.CHAT_BOX);
		super.type(message);
		super.type("\n"); //Enter
	}
	
	public void bringToFront() {
		super.click(properties.HEADER);
	}
	public void close() {
		super.click(properties.CLOSE);
	}
	public void cascade() {
		super.click(properties.CASCADE);
	}

}
