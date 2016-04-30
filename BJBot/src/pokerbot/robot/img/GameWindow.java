package pokerbot.robot.img;

import com.sun.jna.platform.win32.WinDef.HWND;
import java.util.ArrayList;
import java.util.List;
import pokerbot.config.SoftwareProperties;
import pokerbot.config.TableProperties;
import pokerbot.game.Card;
import pokerbot.robot.AbstractRobot;
import pokerbot.utils.Utils;

/**
 * This class provides an interface for reading stuff from the screen
 */
public class GameWindow {

    private final AbstractRobot robot;
    private final Window window;
    private final TableProperties tp;

    public GameWindow(SoftwareProperties sp) {
        HWND hwnd;
        while ((hwnd = User32.INSTANCE.FindWindow(null, "Blackjack - Play: $1.00/$250.00")) == null)
            Utils.sleep(250);
        window = new Window(hwnd);

        robot = new AbstractRobot(window);
        this.tp = new TableProperties(sp);
        Utils.sleep(4000);
    }

    public void join() {
        //robot.click(sp.join_amt); highlighted by default... no need for this
        robot.type("1000");
        Utils.sleep(750);
        robot.click(tp.join_ok);
        Utils.sleep(1000);
        ImageUtils.write(robot.screenShot(), "C:/Users/Student/Desktop/table.png");
    }

    public void bet(int amt) {
        window.setForegroundWindow();
        while (amt > 0) {
            if (amt >= 500) {
                robot.click(tp.bet500);
                amt -= 500;
            } else if (amt >= 100) {
                robot.click(tp.bet100);
                amt -= 100;
            } else if (amt >= 25) {
                robot.click(tp.bet25);
                amt -= 25;
            } else if (amt >= 5) {
                robot.click(tp.bet5);
                amt -= 5;
            } else if (amt >= 1) {
                robot.click(tp.bet1);
                amt -= 1;
            }
            robot.click(tp.placebet);
        }
        Utils.sleep(2000);
        robot.click(tp.rebet);
    }

    /**
     * Rebets when possible. No guarantee on the amount of time it will take
     */
    public void rebet() {
        while (!ImageUtils.samePic(robot.screenShot(tp.rebet_text), tp.rebet_text_img)) {
            Utils.sleep(250);
        }
        Utils.sleep(333);
        robot.click(tp.rebet);
    }

    public void hit() {
        robot.click(tp.hit);
    }

    public void stand() {
        robot.click(tp.stand);
    }

    public void doubledown() {
        robot.click(tp.doubledown);
    }

    public void split() {
        robot.click(tp.split);
    }

    public void surrender() {
        robot.click(tp.surrender);
    }

    public void noInsurance() {
        robot.click(tp.no_insurance);
    }

    public List<Card> captureMyCards() {
        List<Card> ans = new ArrayList<>();
        Card c;
        for (int i = 0; (c = CardImage.classify(robot.screenShot(tp.getHole(i)))) != null; i++) {
            ans.add(c);
        }
        return ans;
    }

    public List<Card> captureDealerCards() {
        List<Card> ans = new ArrayList<>();
        Card c;
        for (int i = 0; (c = CardImage.classify(robot.screenShot(tp.getDealer(i)))) != null; i++) {
            ans.add(c);
        }
        return ans;
    }
    //index = 0 or 1 for upper or lower hand
    public List<Card> captureMyCards_split(int index) {
        List<Card> ans = new ArrayList<>();
        Card c;
        for (int i = 0; (c = CardImage.classify(robot.screenShot(tp.getSplitHole(index, i)))) != null; i++)
            ans.add(c);
        return ans;
            
    }

}
