package pokerbot.domain;

import java.util.ArrayList;
import java.util.List;

import pokerbot.gamestate.AbstractPokerHand;
/**
 * This class represents a poker user.  It contains the hand history for the user 
 * and it is persisted to the database
 *
 */
public class PokerUser {
	
	private long id;

	private String username;
	private List<AbstractPokerHand> handHistory;
	
	public static PokerUser get(String username) {
		//if user is in database, return it
		return new PokerUser(username);
	}
	
	private PokerUser(String username) {
		this.username = username;
		this.handHistory = new ArrayList<>();
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<AbstractPokerHand> getHandHistory() {
		return handHistory;
	}

	public void setHandHistory(List<AbstractPokerHand> handHistory) {
		this.handHistory = handHistory;
	}

}
