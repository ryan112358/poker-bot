package pokerbot.ai;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import pokerbot.game.BlackjackHand;

public class BlackjackAI {
    public static enum Option { HIT, STAND, DOUBLE_DOWN, SPLIT, SURRENDER }

    private HashMap<String, Option[]> bestOptions1;
    private HashMap<String, Option[]> bestOptions2;
    private BlackjackHand game;

    public BlackjackAI() {
        importOptimalStrategy();
    }

    public void setGame(BlackjackHand game) {
        this.game = game;
    }

    public Option bestChoice() {
        if (game.getNumDealerCards() != 1) {
            System.out.println("We have an error in BlackjackAI.bestChoice()");
            System.out.println(game);
        }
        String key = game.getMyHandKey();
        int key2 = game.getDealerHandKey();
        if (game.getNumCards() == 2)
            return bestOptions1.get(key)[key2];
        else
            return bestOptions2.get(key)[key2];
    }

    private void importOptimalStrategy() {
        Scanner in;
        try {
            in = new Scanner(new File("assets/bovada/other/strategy1.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        bestOptions1 = new HashMap<>();
        while (in.hasNextLine()) {
            String[] line = in.nextLine().split(",", 11);
            Option[] ops = new Option[12];
            for (int i = 2; i <= 11; i++) {
                ops[i] = stringToOption(line[i - 1]);
            }
            bestOptions1.put(line[0], ops);
        }
        in.close();
        try {
            in = new Scanner(new File("assets/bovada/other/strategy2.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        bestOptions2 = new HashMap<>();
        while (in.hasNextLine()) {
            String[] line = in.nextLine().split(",", 11);
            Option[] ops = new Option[12];
            for (int i = 2; i <= 11; i++) {
                ops[i] = stringToOption(line[i - 1]);
            }
            bestOptions2.put(line[0], ops);
        }
        in.close();
    }

    private static Option stringToOption(String s) {
        switch (s) {
            case "H":
                return Option.HIT;
            case "S":
                return Option.STAND;
            case "DD":
                return Option.DOUBLE_DOWN;
            case "P":
                return Option.SPLIT; //dont know how to handle split yet
            case "L":
                return Option.SURRENDER;
            default:
                return null;
        }
    }

}
