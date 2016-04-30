package pokerbot.gamestate;

public class Action {
	
	protected PokerPlayer player;

	protected Action(PokerPlayer player) {
		this.player = player;
	}
	
	public PokerPlayer getPlayer() {
		return player;
	}
	
}

class PostSB extends Action {
	public PostSB(PokerPlayer player) {
		super(player);
	}
	public String toString() {
		return player + " posts small blind";
	}
}

class PostBB extends Action {
	public PostBB(PokerPlayer player) {
		super(player);
	}
	public String toString() {
		return player + " posts big blind";
	}
}

class Fold extends Action {
	public Fold(PokerPlayer player) {
		super(player);
	}
	public String toString() {
		return player + " folds";
	}
}
class Check extends Action {
	public Check(PokerPlayer player) {
		super(player);
	}
	public String toString() {
		return player + " checks";
	}
}
class Call extends Action {
	public Call(PokerPlayer player) {
		super(player);
	}
	public String toString() {
		return player + " calls";
	}
}
class Bet extends Action {
	private float amount;
	public Bet(PokerPlayer player, float amount) {
		super(player);
		this.amount = amount;
	}
	public float getAmount() {
		return amount;
	}
	public String toString() {
		return player + " bets " + amount;
	}
}
class Raise extends Action {
	private float amount;
	public Raise(PokerPlayer player, float amount) {
		super(player);
		this.amount = amount;
	}
	public float getAmount() {
		return amount;
	}
	public String toString() {
		return player + " raises to " + amount;
	}
}