package pokerbot.gamestate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class handles AbstractPokerHand information for a single hand
 *
 * @see SixHandedHand
 * @see NineHandedHand
 */
public abstract class AbstractPokerHand {
	
	public static class Street { 
		public static final int INITIAL  =-1;
		public static final int PREFLOP  = 0;
		public static final int FLOP 	 = 1;
		public static final int TURN 	 = 2;
		public static final int RIVER 	 = 3;
		public static final int SHOWDOWN = 4;
	};
	
	private long timestamp;
	private float sb, bb;
	
	protected PokerPlayer[] players;
	
	protected BoardConfiguration board;
	
	protected List<Action> preflopAction;
	protected List<Action> flopAction;
	protected List<Action> turnAction;
	protected List<Action> riverAction;
	
	protected float potSize;
	
	protected AbstractPokerHand(int players) {
		this.players = new PokerPlayer[players];
		for(byte seat=0; seat < players; seat++) {
			this.players[seat] = new PokerPlayer(String.valueOf(seat), seat, 0);
			this.players[seat].setHand(this);
		}
		timestamp = System.currentTimeMillis();
		preflopAction = new ArrayList<Action>();
		flopAction = new ArrayList<Action>();
		turnAction = new ArrayList<Action>();
		riverAction = new ArrayList<Action>();
	}
	/**
	 * Updates the GameState from the given board configuration.
	 * Uses laws of deduction to determine the Actions for each player.
	 * NOTE: In order to ensure proper behavior, this method must be called
	 * when it is my turn.
	 * @param newBoard The BoardConfiguration to update the GameState with
	 */
	public void updateGameState(BoardConfiguration newBoard) {
		assert isWellFormedBoard(newBoard)==0 : "The new board is malformed.  Error code " + isWellFormedBoard(newBoard);
		if(board == null) { setBoard(newBoard); return; }
		//if this street > last street
			//last street: 1 <= i <= button (or bb)
			//this street: sb <= i <= players
		//else: this street = last street
			//1 <= i <= players
		if(board.getStreet() == newBoard.getStreet()) {
			assert board.getStreet()!=Street.INITIAL : "Invalid BoardConfiguration: " + board.getStreet();
			for(int i=1; i < players.length; i++)
				if(isActive(i))
					players[i].deduceAndMakeAction(newBoard.getChips(i), board.getStreet());
		} else {
			assert board.getStreet()+1==newBoard.getStreet() : "Invalid BoardConfiguration: " + board.getStreet();
			if(board.getStreet()==Street.INITIAL) {
				for(int i = (this.getDealerButton()+1)%players.length; i < players.length; i++)
					if(isActive(i))
						players[i].deduceAndMakeAction(newBoard.getChips(i), Street.PREFLOP);
			} else if(board.getStreet() == Street.PREFLOP) { //newBoard = FLOP
				int last = getLastPlayerToAct(Street.PREFLOP);
				int bb = preflopAction.get(1).getPlayer().getSeat();
				for(int i=1; i <= last; i++)
					if(isActive(i))
						players[i].deduceAndMakeAction(newBoard.getChips(i), Street.PREFLOP);
				for(int i=bb+1; i<players.length; i++)
					if(isActive(i))
						players[i].deduceAndMakeAction(newBoard.getChips(i), Street.FLOP);
			} else {
				int last = getLastPlayerToAct(board.getStreet());
				int db = board.button;
				for(int i=1; i <= last; i++)
					if(isActive(i))
						players[i].deduceAndMakeAction(newBoard.getChips(i), board.getStreet());
				for(int i=db+1; i<players.length; i++)
					if(isActive(i))
						players[i].deduceAndMakeAction(newBoard.getChips(i), newBoard.getStreet());
			}
		}
		setBoard(newBoard);
	}
	
	private int getLastPlayerToAct(int street) {
		List<Action> actions = getActions(street);
		for(int k=actions.size()-1; k > 1; k--)
			if(actions.get(k) instanceof Bet || actions.get(k) instanceof Raise)
				return actions.get(k-1).getPlayer().getSeat();
		if(street == Street.PREFLOP)
			return preflopAction.get(1).getPlayer().getSeat();
		else
			return board.button;
	}
	
	private void setBoard(BoardConfiguration board) {
		this.board = board; 
		for(int i=1; i < players.length; i++)
			players[i].setChips(board.getChips(i));
	}
	
	private int isWellFormedBoard(BoardConfiguration newBoard) {
		if(board == null)
			return newBoard.getStreet()==Street.INITIAL ? 0 : 1;
		if(board.getStreet() != Street.INITIAL && board.button != newBoard.button)
			return 2;
		for(int i=0; i < 5; i++)
			if(newBoard.commCards[i] == null && board.commCards[i] != null)
				return 3;
		for(int i=1; i < players.length; i++)
			if(board.getStreet() != Street.INITIAL && newBoard.holeCards[i][0] != null && board.holeCards[i][0] == null)
				return 4;
			else if(isActive(i) && newBoard.chips[i] > board.chips[i])
				return 5;
		return 0;
	}
	
	public PokerPlayer getPlayer(int i) {
		return players[i];
	}
	
	public int getDealerButton() {
		return board.button;
	}
//	/**
//	 * Returns a list of players that are still in the hand.  
//	 * The list is ordered such that the players will act
//	 * starting from the player seating behind the button.
//	 * @return a List of active players seat numbers
//	 */
//	public List<Integer> getActivePlayers() {
//		List<Integer> ans = new ArrayList<Integer>();
//		for(int i=(getDealerButton()+1)%players.length; i!=getDealerButton(); i=(i+1)%players.length)
//			if(isActive(i))
//				ans.add(i);
//		return ans;
//	}
	
	public List<Integer> getActivePlayers() {
		List<Integer> ans = new ArrayList<Integer>();
		for(int i=0; i < players.length; i++)
			if(isActive(i))
				ans.add(i);
		return ans;
	}
	
	private boolean isActive(int player) {
		return getHoleCards()[player][0] != null;
	}
	public void setStakes(float sb, float bb) {
		this.bb = bb;
		this.sb = sb;
	}
	public float getSB() {
		return sb;
	}
	public float getBB() {
		return bb;
	}
	
	public int getStreet() {
		return board.getStreet();
	}
	public List<Action> getActions(int street) {
		switch(street) {
		case Street.PREFLOP: return preflopAction;
		case Street.FLOP: return flopAction;
		case Street.TURN: return turnAction;
		case Street.RIVER: return riverAction;
		default: throw new IllegalArgumentException();
		}
	}
	public void addAction(int street, Action action) {
		getActions(street).add(action);
	}
	
	public Card[] getMyHoleCards() {
		return board.holeCards[0];
	}
	public Card[][] getHoleCards() {
		return board.holeCards;
	}
	public Card[] getFlopCards() {
		return new Card[] { board.commCards[0], board.commCards[1], board.commCards[2] };
	}
	public Card getTurnCard() {
		return board.commCards[3];
	}
	public Card getRiverCard() {
		return board.commCards[4];
	}
	public long getTimestamp() {
		return timestamp;
	}

	@Override
	public String toString() {
		String result = "Button: " + board.button + "\n";
		result += "Hole Cards: " + Arrays.toString(getMyHoleCards()) + "\n";
		result += "Community Cards: " + Arrays.toString(board.commCards) + "\n";
		result += "Active Players:  " + getActivePlayers() + "\n";
		result += "Pot Size: " + potSize + "\n";
		result += "Chips: " + Arrays.toString(board.chips);
		return result;
	}

}
