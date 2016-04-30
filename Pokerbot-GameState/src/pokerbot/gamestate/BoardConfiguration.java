package pokerbot.gamestate;

import pokerbot.gamestate.AbstractPokerHand.Street;

public class BoardConfiguration {
	
	protected Float[] chips;
	protected int button;
	
	protected Card[][] holeCards;
	
	protected Card[] commCards;
	
	protected int street = -2;
	
	public void setHoleCards(Card[][] holeCards) {
		this.holeCards = holeCards;
		inferStreet();
	}
	
	public void setCommCards(Card[] commCards) {
		this.commCards = commCards;
		inferStreet();
	}
	
	private void inferStreet() {
		if(holeCards == null || commCards == null)
			return;
		if(holeCards[0][0] == null)
			street = Street.INITIAL;
		else if(commCards[0] == null)
			street = Street.PREFLOP;
		else if(commCards[3] == null)
			street = Street.FLOP;
		else if(commCards[4] == null)
			street = Street.TURN;
		else 
			street = Street.RIVER;
	}
	
	public void setChips(Float[] chips) {
		this.chips = chips;
	}
	
	public void setButton(int button) {
		this.button = button;
	}
	public int getStreet() {
		return street;
	}
	public void setStreet(int street) {
		this.street = street;
	}
	
	public float getChips(int player) {
		return chips[player];
	}
	
	public Card[][] getHoleCards() {
		return holeCards;
	}

}
