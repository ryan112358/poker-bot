package pokerbot.gamestate;

import pokerbot.domain.PokerUser;
import pokerbot.gamestate.AbstractPokerHand.Street;
/**
 * The PokerPlayer class is a representation of a 1 player at 1 table.
 */
public class PokerPlayer {
	
	private PokerUser user;
	private byte seat;
	private float chips;
	
	private AbstractPokerHand hand;
	
	public PokerPlayer(String username, byte seat, float chips) {
		this.user = PokerUser.get(username);
		this.seat = seat;
		this.chips = chips;
	}
	
	public void setHand(AbstractPokerHand hand) {
		this.hand = hand;
	}
	public void deduceAndMakeAction(float chips, int street) {
		assert chips <= this.chips : chips + ", " + this.chips;
		float money_in_pot = 0;
		float aggresive_play = 0;
		for(Action action : hand.getActions(street)) {
			if(action instanceof PostSB)
				aggresive_play = 200;
			else if(action instanceof PostBB)
				aggresive_play = 400; //fix this
			else if(action instanceof Bet)
				aggresive_play = ((Bet) action).getAmount();
			else if(action instanceof Raise)
				aggresive_play = ((Raise) action).getAmount();
			if(action.getPlayer() == this) {
				//cannot be fold (either it was a bet or a raise, or a call to a bet or a raise)
				money_in_pot += aggresive_play;
			}
		}
		Action action = null;
		if(hand.getActions(Street.PREFLOP).size()==0)
			action = new PostSB(this);
		else if(hand.getActions(Street.PREFLOP).size()==1)
			action = new PostBB(this);
		else if(chips == this.chips)
			if(aggresive_play == money_in_pot)
				action = new Check(this);
			else
				action = new Fold(this);
		else
			if(aggresive_play == money_in_pot + this.chips - chips)
				action = new Call(this);
			else if(aggresive_play == money_in_pot)
				action = new Bet(this, money_in_pot + this.chips - chips);
			else 
				action = new Raise(this, money_in_pot + this.chips - chips);
		assert action != null;
		
		hand.addAction(street,action);
		
		//System.out.println("Action:" + action);
		
		setChips(chips);
	}
	public void postSB() {
		assert hand.getStreet() == Street.INITIAL;
		hand.addAction(Street.PREFLOP, new PostSB(this));
	}
	public void postBB() {
		assert hand.getStreet() == Street.INITIAL;
		hand.addAction(Street.PREFLOP, new PostBB(this));
	}
	public void fold() {
		hand.addAction(hand.getStreet(), new Fold(this));
	}
	public void check() {
		hand.addAction(hand.getStreet(), new Check(this));
	}
	public void call() {
		hand.addAction(hand.getStreet(), new Call(this));
	}
	public void bet(float amt) {
		hand.addAction(hand.getStreet(), new Bet(this, amt));
	}
	public void raise(float amt) {
		hand.addAction(hand.getStreet(), new Raise(this, amt));
	}
	
	public PokerUser getUser() {
		return user;
	}
	public void setUser(PokerUser user) {
		this.user = user;
	}
	public byte getSeat() {
		return seat;
	}
	public void setSeat(byte seat) {
		this.seat = seat;
	}
	public float getChips() {
		return chips;
	}
	public void setChips(float chips) {
		this.chips = chips;
	}
	
	public String toString() {
		return "Player " + seat;
	}
}
