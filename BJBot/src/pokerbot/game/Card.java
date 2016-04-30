package pokerbot.game;

public class Card {
    
    public static final int HEARTS = 0;
    public static final int SPADES = 1;
    public static final int DIAMONDS = 2;
    public static final int CLUBS = 3;
    
    public static final int LOW_ACE = 1;
    public static final int JACK = 11;
    public static final int QUEEN = 12;
    public static final int KING = 13;
    public static final int HIGH_ACE = 14;
    
    private static final String[] suits = { "Hearts", "Spades", "Diamonds", "Clubs" };
    private static final String[] ranks = { null, "Ace","2","3","4","5","6","7","8","9","10","Jack","Queen","King", "Ace" };
    
    private final int suit;
    private final int rank;
    
    public Card(int rank, int suit) {
        this.rank = rank;
        this.suit = suit;
    }
    
    public int getValue() {
        if(rank == 14 || rank == 1)
            return 11;
        else if(rank >= 10)
            return 10;
        else
            return rank;
    }
    
    @Override
    public String toString() {
        return ranks[rank] + " of " + suits[suit];
    }
    
    
}
