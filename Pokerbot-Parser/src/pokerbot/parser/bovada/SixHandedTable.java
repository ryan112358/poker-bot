package pokerbot.parser.bovada;

import pokerbot.parser.abstractions.GameWindow;
import pokerbot.parser.abstractions.TableProperties;

public class SixHandedTable extends GameWindow {
	
	/**
	 * Constructs a new SixHandedTable object by getting the foreground window
	 * from the screen
	 */
	private SixHandedTable() {
		super(TableProperties.BOVADA_SIXHANDED);
	}
	/**
	 * This is a factory method constructor for a SixHandedTable.
	 * @return A new instance of a SixHandedTable from the Foreground window
	 */
	public static SixHandedTable getForegroundTable() {
		return new SixHandedTable();
	}

}
