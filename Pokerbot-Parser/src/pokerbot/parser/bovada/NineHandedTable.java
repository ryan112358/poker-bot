package pokerbot.parser.bovada;
import pokerbot.parser.abstractions.GameWindow;
import pokerbot.parser.abstractions.TableProperties;

public class NineHandedTable extends GameWindow {
	
	/**
	 * Constructs a new NineHandedTable object by getting the foreground window
	 * from the screen
	 */
	private NineHandedTable() {
		super(TableProperties.BOVADA_NINEHANDED);
	}
	/**
	 * This is a factory method constructor for a NineHandedTable.
	 * @return A new instance of a NineHandedTable from the Foreground window
	 */
	public static NineHandedTable getForegroundTable() {
		return new NineHandedTable();
	}

}
