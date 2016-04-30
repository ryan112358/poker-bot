package pokerbot.gamestate;

import java.util.Arrays;

/** 
 * This is a read-only class for Card objects
 */
public class Card {
	
	private int rank;
	private int suit;
	
	public static Card UNKNOWN = new Card(-1,-1);
	public static Card[] CARDS = new Card[52];
	static {
		Arrays.setAll(CARDS, v -> new Card(v%13+2,v/13));
	}
	
	private Card(int rank, int suit) {
		this.rank = rank;
		this.suit = suit;
	}
	/**
	 * Factory method constructor for Card
	 * @param rank
	 * @param suit
	 * @return The Card object with the given rank and suit
	 */
	public static Card valueOf(int rank, int suit) {
		return CARDS[suit*13 + rank-2];
	}
	/**
	 * Factory method constructor for Card
	 * @param value integer value of the card such that 0 <= value < 52
	 * @return The Card object with the given value
	 */
	public static Card valueOf(int value) {
		return CARDS[value];
	}
	/**
	 * @return The index in the Card.CARDS array that this card is in.
	 */
	public int getValue() {
		return suit*13 + rank-2;
	}
	public int getRank() {
		return rank;
	}
	public int getSuit() {
		return suit;
	}
	public String toString() {
		if(this == UNKNOWN) return "Unknown";
		return Rank.RANKS[rank] + " of " + Suit.SUITS[suit];
	}
	public String fileName() {
		if(this == UNKNOWN) return "unknown.png";
		String rk = Rank.RANKS[rank];
		if(rank > 10) rk = String.valueOf((char)(rk.charAt(0)+'a'-'A'));
		return rk + (char)(Suit.SUITS[suit].charAt(0)+'a'-'A') + ".png";
	}
	
	public static class Suit {
		public static final int HEARTS = 0;
		public static final int DIAMONDS = 1;
		public static final int CLUBS = 2;
		public static final int SPADES = 3;
		
		public static final String[] SUITS = { "Hearts", "Diamonds", "Clubs", "Spades" };
	}
	
	public static class Rank {
		public static final int TWO = 2;
		public static final int THREE = 3;
		public static final int FOUR = 4;
		public static final int FIVE = 5;
		public static final int SIX = 6;
		public static final int SEVEN = 7;
		public static final int EIGHT = 8;
		public static final int NINE = 9;
		public static final int TEN = 10;
		public static final int JACK = 11;
		public static final int QUEEN = 12;
		public static final int KING = 13;
		public static final int ACE = 14;
		
		public static final String[] RANKS = { null, null, "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace" };
	}

}
