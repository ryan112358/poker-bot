package pokerbot.gamestate;

public class Player {

	private String username;
	private float chips;
	
	public Player(String username, float chips) {
		this.username = username;
		this.chips = chips;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public float getChips() {
		return chips;
	}

	public void setChips(float chips) {
		this.chips = chips;
	}

}
