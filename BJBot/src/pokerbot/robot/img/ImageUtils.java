package pokerbot.robot.img;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

class ImageUtils {
    
    protected static int colorDist(int rgb1, int rgb2) {
        Color c1 = new Color(rgb1), c2 = new Color(rgb2);
        int dr = c1.getRed() - c2.getRed();
        int dg = c1.getGreen() - c2.getGreen();
        int db = c1.getBlue() - c2.getBlue();
        return (int) Math.sqrt(dr*dr + dg*dg + db*db);
    }
    
    /** 
     * Determines if img1 and img2 are the same image.  If they are of different sizes, it only checks from up to the width and height of img1.
     * Thus, if img1 is a subset of img2, this will return true.
     * @return 
     */
    public static boolean samePic(BufferedImage img1, BufferedImage img2) {
        int w = img1.getWidth();
        int h = img1.getHeight();
        int colordist = 0;
        for(int i=0; i<w; i++)
            for(int j=0; j<h; j++)
                colordist += ImageUtils.colorDist(img1.getRGB(i,j), img2.getRGB(i,j));
        return colordist < 500;
    }
    
    public static void writeToDesktop(BufferedImage b, String filename) {
        write(b, "C:/Users/Student/Desktop/"+filename);
    }
    
    public static void write(BufferedImage b, String file) {
        try {
            ImageIO.write(b, "png", new File(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
