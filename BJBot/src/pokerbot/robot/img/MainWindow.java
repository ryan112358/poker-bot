package pokerbot.robot.img;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import pokerbot.config.SoftwareProperties;
import pokerbot.robot.AbstractRobot;
import pokerbot.utils.Utils;

public class MainWindow {

    Window window;
    AbstractRobot robot;
    SoftwareProperties sp;

    public MainWindow(SoftwareProperties sp) {
        this.sp = sp;
        //check to see if it exists already...
        if (User32.INSTANCE.FindWindow(null, sp.lobby_name) == null) {
            try {
                new ProcessBuilder(sp.exe_path).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Utils.sleep(45000);
        }
        window = new Window(User32.INSTANCE.FindWindow(null, sp.lobby_name));
        window.setForegroundWindow();
        robot = new AbstractRobot(window);
    }

    /**
     * Logs you into the software using information from the properties file
     */
    public void login() {
        window.setForegroundWindow();
        Utils.sleep(2500);
        if (ImageUtils.samePic(robot.screenShot(sp.login_check), sp.login_check_img)) {
            robot.doubleClick(sp.login_username);
            robot.type(sp.username);
            robot.doubleClick(sp.login_password);
            robot.type(sp.password);
            robot.click(sp.login);
            Utils.sleep(3500);
        }
    }

    public void screenshot(String file) {
        ImageUtils.write(robot.screenShot(), "C:/Users/Student/Desktop/" + file + ".png");
    }

    public GameWindow joinGame() {
        window.setForegroundWindow();
        Utils.sleep(3000);
        BufferedImage key = sp.preferred_game;
        robot.click(sp.bj_button);
        Utils.sleep(2500);
        Rectangle rect = new Rectangle(sp.entry_corner);
        rect.width = sp.entry_width;
        rect.height = sp.num_entries * sp.entry_height;
        robot.move(rect);
        Utils.sleep(5000);
        int index = findGame(key);
        if (index != -1) {
            Rectangle r = new Rectangle(sp.entry_corner);
            r.width = sp.entry_width;
            r.height = sp.entry_height;
            r.y += r.height * index;
            robot.doubleClick(new Point(r.x + r.width / 2, r.y + r.height / 2));
            
            GameWindow table = new GameWindow(sp);
            table.join();
            
            return table;
        }
        Utils.sleep(1000);
        return null;
    }

    /**
     * checks to see if preferred game is on screen and returns the index if it is return -1 if there is no such index (In which case you need to scroll down)
     */
    private int findGame(BufferedImage preferredGame) {
        BufferedImage lobby = robot.screenShot();
//        Utils.writeToBin(lobby);
        for (int i = 0; i < sp.num_entries; i++) {
            Rectangle r = new Rectangle(sp.entry_corner);
            r.width = sp.entry_width;
            r.height = sp.entry_height;
            r.y += r.height * i;
            //Utils.writeToBin(lobby.getSubimage(r.x, r.y, r.width, r.height));
            if (entryEquals(preferredGame, lobby.getSubimage(r.x, r.y, r.width, r.height))) {
                return i;
            }
            //ImageUtils.write(lobby.getSubimage(r.x, r.y, r.width, r.height), "C:/Users/Student/Desktop/img"+i+".png");
        }
        System.out.println("Could not find suitable match");
        return -1;
    }

    private boolean entryEquals(BufferedImage a, BufferedImage b) {
        Color ignore = new Color(255, 60, 255); //ignore bright pink
        for (int x = 0; x < sp.entry_width; x++) {
            for (int y = 0; y < sp.entry_height; y++) {
                if (a.getRGB(x, y) != ignore.getRGB()) {
                    if (ImageUtils.colorDist(a.getRGB(x, y), b.getRGB(x, y)) > 50) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

}
