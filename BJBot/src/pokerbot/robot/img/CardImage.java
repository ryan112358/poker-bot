package pokerbot.robot.img;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;
import pokerbot.game.Card;

public class CardImage {
    
    private static HashMap<BufferedImage, Card> cardKey;
    
    public static void loadKnownCards(String path) {
        cardKey = new HashMap<>();
        String[] suits = { "h", "s", "d", "c" };
        String[] ranks = { null, "a","2","3","4","5","6","7","8","9","10","j","q","k", "a" };
        for(int suit = 0; suit < 4; suit++)
            for(int rank = 2; rank <= 14; rank++) {
                try { cardKey.put(ImageIO.read(new File(path+ranks[rank]+suits[suit]+".png")), new Card(rank, suit)); }
                catch(IOException e) { e.printStackTrace(); }
            }
    }
    /** 
     * Consumes an image of a card and produces the corresponding card object
     * @param img
     * @return 
     */
    public static Card classify(BufferedImage img) {
        for(BufferedImage b : cardKey.keySet())
            if(similarity(img, b) < 2500)
                return cardKey.get(b);
        return null;
    }
    /** 
     * Determines if img1 and img2 are the same image.  If they are of different sizes, it only checks from up to the width and height of img1.
     * Thus, if img1 is a subset of img2, this will return true.
     * @return 
     */
    private static int similarity(BufferedImage img1, BufferedImage img2) {
        int w = img1.getWidth();
        int h = img1.getHeight();
        int colordist = 0;
        for(int i=2; i<w; i++)
            for(int j=2; j<h; j++)
                colordist += ImageUtils.colorDist(img1.getRGB(i,j), img2.getRGB(i,j));
        return colordist;
    }
    
}
