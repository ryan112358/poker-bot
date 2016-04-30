package pokerbot.gamestate;

import java.util.ArrayList;
import java.util.List;
/**
 * This class manages hand histories
 *
 * @param <T> The type of hand that is being tracked
 */
public class PokerSession<T extends AbstractPokerHand> {
	
	protected List<T> history = new ArrayList<T>();
	
	public void addHand(T hand) {
		history.add(hand);
	}
	public List<T> getHistory() {
		return history;
	}

}
