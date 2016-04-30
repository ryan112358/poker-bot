package pokerbot.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import pokerbot.gamestate.AbstractPokerHand;
import pokerbot.gamestate.AbstractPokerHand.Street;
import pokerbot.gamestate.BoardConfiguration;
import pokerbot.gamestate.games.NineHandedHand;
import pokerbot.parser.Utils;
import pokerbot.parser.abstractions.GameWindow;
import pokerbot.parser.bovada.NineHandedTable;

/**
 * @author Ryan McKenna
 * 
 */
public class PokerbotApplication {

	public static void main(String[] args) {
		System.out.println("Application started");
		
		commandLine();
	}
	
	static void commandLine() {
		Utils.sleep(2500);
		List<GameWindow> windows = new ArrayList<GameWindow>();
		Map<GameWindow, AbstractPokerHand> map = new HashMap<GameWindow, AbstractPokerHand>();
		Scanner console = new Scanner(System.in);
		String line;
		while(! (line = console.next()).equals("exit")) {
			switch(line) {
			case "register":
				Utils.sleep(2500);
				windows.add(NineHandedTable.getForegroundTable());
				map.put(windows.get(windows.size()-1), new NineHandedHand());
				System.out.println(windows.get(windows.size()-1).getCaption());
				break;
			case "screenshot":
				Utils.writeToDesktop(windows.get(console.nextInt()).getScreenShot(), "output.png");	
				break;
			case "wait-for-turn":
				GameWindow gw = windows.get(console.nextInt());
				while(! gw.isMyTurn())
					Utils.sleep(1000);
				System.out.println("It's my turn!");
				break;
			case "focus":
				windows.get(console.nextInt()).bringToFront();
				break;
			case "isfront":
				System.out.println(windows.get(console.nextInt()).isForegroundWindow());
				break;
			case "parse":
				GameWindow window = windows.get(console.nextInt());
				map.get(window).updateGameState(window.getBoardConfig());
				System.out.println(map.get(window));
				break;
			case "caption":
				System.out.println(windows.get(console.nextInt()).getCaption());
				break;
			case "close":
				windows.get(console.nextInt()).close();
				break;
			case "cascade":
				windows.get(console.nextInt()).cascade();
				break;
			case "ai-play":
				GameWindow w = windows.get(console.nextInt());
				aiPlay(w, map.get(w));
				break;
			case "deduct":
				testDeduction(windows.get(console.nextInt()));
				break;
			}
		}
		console.close();
	}
	
	static void testDeduction(GameWindow window) {
		int n = 1;
		while(true) {
			while(! window.isMyTurn())
				Utils.sleep(1000);
			Utils.writeToDesktop(window.getScreenShot(), n++ + ".png");
			while(window.isMyTurn())
				Utils.sleep(1000);
		}
	}
	
	static void aiPlay(GameWindow window, AbstractPokerHand hand) {
		while(true) {
			window.getBoardConfig();
			while(! window.isNewHand())
				Utils.sleep(3333);
			System.out.println("starting new hand");
			BoardConfiguration b = window.getBoardConfig();
			hand.updateGameState(b);
			while(! window.hasDealt())
				Utils.sleep(1000);
			System.out.println("hand has been dealt");
			b.setHoleCards(window.getBoardConfig().getHoleCards());
			b.setStreet(Street.INITIAL);
			while(! window.isMyTurn())
				Utils.sleep(1000);
			System.out.println("it's my turn");
			hand.updateGameState(window.getBoardConfig());
			System.out.println(hand.getActions(Street.PREFLOP));
			while(window.isMyTurn())
				Utils.sleep(1000);
			while(! window.isMyTurn())
				Utils.sleep(1000);
			System.out.println("it's my turn");
			hand.updateGameState(window.getBoardConfig());
			System.out.println(hand.getActions(Street.PREFLOP));
			System.out.println(hand.getActions(Street.FLOP));

			
			
//			System.out.println("It's my turn! Making move now...");
//			Utils.sleep(100,3000);
//			window.fold();
//			Utils.sleep(1000);
		}
	}

}
