package pokerbot.config;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import javax.imageio.ImageIO;

public class TableProperties {

    private Rectangle hole1;
    private int hole_dx;
    private int hole_dy;

    private Rectangle dealer1;
    private int dealer_dx;
    private int dealer_dy;

    public Rectangle bet1;
    public Rectangle bet5;
    public Rectangle bet25;
    public Rectangle bet100;
    public Rectangle bet500;

    public Rectangle join_ok;
    public Rectangle join_amt;

    public Rectangle rebet;
    public Rectangle hit;
    public Rectangle stand;
    public Rectangle split;
    public Rectangle doubledown;
    public Rectangle surrender;
    public Rectangle placebet;
    public Rectangle no_insurance;
    public Rectangle rebet_text;
    public BufferedImage rebet_text_img;

    private int split_hole_dy;
    private int split_dy;
    private int split_dx;

    private final SoftwareProperties sp;

    public TableProperties(SoftwareProperties sp) {
        this.sp = sp;
        parseProperties("config/bovada/bj.properties");
    }
    
    public Rectangle getSplitHole(int index, int num) {
        Rectangle ans = new Rectangle(hole1);
        ans.y += split_hole_dy;
        ans.y += split_dy * index;
        ans.x += split_dx * index;
        ans.x += num * hole_dx;
        ans.y += num * hole_dy;
        ans.width = sp.card_width;
        ans.height = sp.card_height;
        return ans;
    }

    public Rectangle getHole(int num) {
        Rectangle ans = new Rectangle(hole1);
        ans.x += num * hole_dx;
        ans.y += num * hole_dy;
        ans.width = sp.card_width;
        ans.height = sp.card_height;
        return ans;
    }

    public Rectangle getDealer(int num) {
        Rectangle ans = new Rectangle(dealer1);
        ans.x += num * dealer_dx;
        ans.y += num * dealer_dy;
        ans.width = sp.card_width;
        ans.height = sp.card_height;
        return ans;
    }

    private void parseProperties(String file) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            HashMap<String, String> map = new HashMap<>();
            String line;
            while ((line = br.readLine()) != null) {
                String[] m = PropsParser.parseByEqual(line);
                map.put(m[0], m[1]);
            }
            hole1 = new Rectangle(PropsParser.parsePoints(map.get("hole1"))[0]);
            dealer1 = new Rectangle(PropsParser.parsePoints(map.get("dealer1"))[0]);
            hole_dx = Integer.parseInt(map.get("hole_dx"));
            hole_dy = Integer.parseInt(map.get("hole_dy"));

            dealer_dx = Integer.parseInt(map.get("dealer_dx"));
            dealer_dy = Integer.parseInt(map.get("dealer_dy"));

            bet1 = PropsParser.parseToRect(map.get("bet1"));
            bet5 = PropsParser.parseToRect(map.get("bet5"));
            bet25 = PropsParser.parseToRect(map.get("bet25"));
            bet100 = PropsParser.parseToRect(map.get("bet100"));
            bet500 = PropsParser.parseToRect(map.get("bet500"));

            join_ok = PropsParser.parseToRect(map.get("join-ok"));
            join_amt = PropsParser.parseToRect(map.get("join-amt"));

            rebet = PropsParser.parseToRect(map.get("rebet"));
            hit = PropsParser.parseToRect(map.get("hit"));
            stand = PropsParser.parseToRect(map.get("stand"));
            split = PropsParser.parseToRect(map.get("split"));
            doubledown = PropsParser.parseToRect(map.get("doubledown"));
            surrender = PropsParser.parseToRect(map.get("surrender"));
            placebet = PropsParser.parseToRect(map.get("placebet"));
            no_insurance = PropsParser.parseToRect(map.get("no-insurance"));
            rebet_text = PropsParser.parseToRect(map.get("rebet-text"));
            rebet_text_img = ImageIO.read(new File("assets/bovada/other/rebet.png"));

            split_hole_dy = Integer.parseInt(map.get("split_hole_dy"));
            split_dy = Integer.parseInt(map.get("split_dy"));
            split_dx = Integer.parseInt(map.get("split_dx"));
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
