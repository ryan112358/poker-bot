package pokerbot.config;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;

public class SoftwareProperties {

    public String exe_path;
    public String imageKey;

    protected int window_width;
    protected int window_height;
    protected int card_width;
    protected int card_height;
   
    public Rectangle login_check;
    public BufferedImage login_check_img;
    public Rectangle login_username;
    public Rectangle login_password;
    public Rectangle login;
    public Rectangle bj_button;
    
    public BufferedImage preferred_game;
    public Point entry_corner;
    public int entry_width;
    public int entry_height;
    public int num_entries = 6;
    
    public String lobby_name;
    public String username;
    public String password;

    public SoftwareProperties() {
        parseConfig("config/bovada/config.properties");
    }

    private void parseConfig(String file) {
        try {
            HashMap<String,String> map = PropsParser.parseFile(file);
            exe_path = map.get("program-path");
            imageKey = map.get("known-images-path");
            window_width = Integer.parseInt(map.get("window-width"));
            window_height = Integer.parseInt(map.get("window-height"));
            card_width = Integer.parseInt(map.get("card-width"));
            card_height = Integer.parseInt(map.get("card-height"));
            
            login_check = PropsParser.parseToRect(map.get("login-check"));
            login_check_img = ImageIO.read(new File(map.get("login-check-img")));
            login_username = PropsParser.parseToRect(map.get("login-user"));
            login_password = PropsParser.parseToRect(map.get("login-pass"));
            login = PropsParser.parseToRect(map.get("sign-in"));
            username = map.get("username");
            password = map.get("password");
            lobby_name = map.get("lobby-name");
            
            bj_button = PropsParser.parseToRect(map.get("bj-button"));
            
            entry_corner=PropsParser.parsePoints(map.get("entry-corner-bj"))[0];
            entry_width = Integer.parseInt(map.get("entry-width"));
            entry_height = Integer.parseInt(map.get("entry-height"));
            preferred_game = ImageIO.read(new File(map.get("preferred-game")));
          
            
            
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        String result = "";
        result += "Executable Path: " + exe_path.toString() + "\n";
        result += "Window Size: " + window_width + " x " + window_height + "\n";
        result += "Card Size: " + card_width + " x " + card_height + "\n";
        return result;
    }

}
