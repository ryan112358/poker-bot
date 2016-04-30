package pokerbot.ai;

import pokerbot.ai.BlackjackAI.Option;
import pokerbot.game.BlackjackHand;
import pokerbot.robot.img.GameWindow;
import pokerbot.utils.Utils;

/**
 * This class provides an interface between an AIPlayer and a GameRobot
 */
public class AIInterface {

    private final BlackjackAI ai;
    private final GameWindow window;
    private BlackjackHand game;

    public AIInterface(GameWindow window) {
        this.window = window;
        this.ai = new BlackjackAI();
        this.game = new BlackjackHand();
        ai.setGame(this.game);
    }
    /*
     * Parses the game board, if it's our turn, makes a play
     */

    public void automatePlay(int hands) {
        window.bet(25);
        for (int i = 1; i < hands; i++) {
            playHand();
            game.log();
            window.rebet();
        }
        playHand();
        game.log();
    }

    public void playHand() {
        deal();
        checkInsurance();
        if (dealerHasBJ() || iHaveBJ())
            return;
        if (!makeDecision())
            return; //a bit hacky, but convenient
        waitForHandToEnd();
    }

    private boolean makeDecision() {
        Option o = ai.bestChoice();
        if (o == Option.SURRENDER) {
            surrender();
            return false;
        }
        if (o == Option.DOUBLE_DOWN)
            double_down();
        if (o == Option.SPLIT)
            split();
        while (o == Option.HIT) {
            hit();
            if (game.iBusted())
                break;
            o = ai.bestChoice();
        }
        if (o == Option.STAND)
            stand();
        return true;
    }

    private void deal() {
        game.reset();
        while (!window.captureMyCards().isEmpty() || !window.captureDealerCards().isEmpty())
            Utils.sleep(250);
        do {
            Utils.sleep(250);
            updateGameState();
        } while (!game.hasDealt());
        Utils.sleep(1222);
    }

    private boolean dealerHasBJ() {
        if (game.getNumDealerCards() == 1 && game.getDealerTotal() < 10)
            return false;
        Utils.sleep(876);
        updateGameState();
        return game.getNumDealerCards() == 2 && game.getDealerTotal() == 21;
    }

    private boolean iHaveBJ() {
        return game.iHaveBlackjack();
    }

    private void checkInsurance() {
        if (game.canInsure()) {
            window.noInsurance();
            Utils.sleep(876); //ideally, all hardcoded times could be removed.
        }
    }

    private void updateGameState() {
        game.setMyCards(window.captureMyCards());
        game.setDealerCards(window.captureDealerCards());
    }

    private void hit() {
        int ct = game.getNumCards();
        window.hit();
        while (game.getNumCards() != ct + 1) {
            game.setMyCards(window.captureMyCards());
            Utils.sleep(250);
        }
        Utils.sleep(222);
    }

    private void stand() {
        window.stand();
        Utils.sleep(444);
    }

    private void double_down() {
        int ct = game.getNumCards();
        window.doubledown();
        while (game.getNumCards() != ct + 1) {
            game.setMyCards(window.captureMyCards());
            Utils.sleep(250);
        }
    }

    private void surrender() {
        window.surrender();
        Utils.sleep(444);
    }

    private void waitForHandToEnd() {
        updateGameState();
        if (game.iBusted() || game.iHaveBlackjack())
            return;
        while (!game.dealerDone()) {
            Utils.sleep(250);
            game.setDealerCards(window.captureDealerCards());
        }
        Utils.sleep(456);
    }

    private void split() {
        window.split();
        Utils.sleep(3000);
        game.reset();
        game.setDealerCards(window.captureDealerCards());
        game.setMyCards(window.captureMyCards_split(0));
        split1(0);
        Utils.sleep(2000);
        game.log();
        game.reset();
        game.setDealerCards(window.captureDealerCards());
        game.setMyCards(window.captureMyCards_split(1));
        split1(1);
    }

    private void split1(int index) {
        if (game.iHaveBlackjack())
            return;
        Option o = ai.bestChoice();
        if (o == Option.SURRENDER) {
            surrender();
            return;
        }
        if (o == Option.DOUBLE_DOWN) {
            int ct = game.getNumCards();
            window.doubledown();
            while (game.getNumCards() != ct + 1) {
                game.setMyCards(window.captureMyCards_split(index));
                Utils.sleep(250);
            }
        }
        if (o == Option.SPLIT)
            stand();
        while (o == Option.HIT) {
            int ct = game.getNumCards();
            window.hit();
            while (game.getNumCards() != ct + 1) {
                game.setMyCards(window.captureMyCards_split(index));
                Utils.sleep(250);
            }
            Utils.sleep(222);
            if (game.iBusted())
                break;
            o = ai.bestChoice();
        }
        if (o == Option.STAND)
            stand();

    }

}
