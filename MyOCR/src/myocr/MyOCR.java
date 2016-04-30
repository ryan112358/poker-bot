package myocr;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.RasterFormatException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class MyOCR {

    static BufferedImage[] knownImages;
    static BufferedImage pt, comma;
    
    public static void setup(String path) {
        try {
            knownImages = new BufferedImage[10];
            for (int i = 0; i < 9; i++) {
                knownImages[i] = ImageIO.read(new File(path + i + ".png"));
            }
            pt = ImageIO.read(new File(path+"pt.png"));
            comma = ImageIO.read(new File(path+"comma.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    public static double getValue(BufferedImage img) {
        try {
            BufferedImage[] part = partition(img);
            String result = "";
            for (BufferedImage part1 : part)
                result += classify2(part1);
            return Double.parseDouble(result);
        } catch(Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    private static double classify(BufferedImage img) throws Exception {
        String result = "";
        for (int x = 1; x < img.getWidth(); x++) {
            for (int y = 0; y < img.getHeight() && x < img.getWidth(); y++) {
                if(img.getRGB(x-1,y) != img.getRGB(x,y)) {
                    Rectangle rect = getBounds(img, x, y);
                    result += classify2(img.getSubimage(rect.x, rect.y, rect.width, rect.height));
                    System.out.println(result);
                    ImageIO.write(img.getSubimage(rect.x, rect.y, rect.width, rect.height), "png", new File("C:/Users/Student/Desktop/result"+x+".png"));
                    x += rect.width;
                    y = 0;
                }
            }
        }
        return Double.parseDouble(result);
    }
    
    private static int colorDist(int rgb1, int rgb2) {
        Color c1 = new Color(rgb1), c2 = new Color(rgb2);
        int dr = c1.getRed() - c2.getRed();
        int dg = c1.getGreen() - c2.getGreen();
        int db = c1.getBlue() - c2.getBlue();
        return (int) Math.sqrt(dr*dr + dg*dg + db*db);
    }
    
    private static BufferedImage compact(BufferedImage img) {
        int x1=0,y1=0,x2=img.getWidth()-1,y2=img.getHeight()-1;
        loop1:
        for(int y = 0; y < img.getHeight(); y++) {
            for(int x = 1; x < img.getWidth(); x++) {
                if(colorDist(img.getRGB(x-1,y),img.getRGB(x,y)) > 50) {
                    break loop1;
                }
            }
            y1 = y;
        }
        loop2:
        for(int y = img.getHeight()-1; y >=0; y--) {
            for(int x = 1; x < img.getWidth(); x++) {
                if(colorDist(img.getRGB(x-1,y),img.getRGB(x,y)) > 50) {
                    break loop2;
                }
            }
            y2 = y;
        }
        loop3:
        for(int x = 0; x < img.getWidth(); x++) {
            for(int y = 1; y < img.getHeight(); y++) {
                if(colorDist(img.getRGB(x,y-1),img.getRGB(x,y)) > 50) {
                    break loop3;
                }
            }
            x1 = x;
        }
        loop4:
        for(int x = img.getWidth()-1; x >= 0; x--) {
            for(int y = 1; y < img.getHeight(); y++) {
                if(colorDist(img.getRGB(x,y-1),img.getRGB(x,y)) > 50) {
                    break loop4;
                }
            }
            x2=x;
        }
        try {
            return img.getSubimage(x1+1,y1+1,x2-x1,y2-y1-1);
        } catch(RasterFormatException e) {
            System.out.println(y1 + " " + y2);
            return null;
        }
    }
    
    private static BufferedImage[] partition(BufferedImage img) throws Exception {
        boolean looking = false;
        ArrayList<Integer> partition = new ArrayList<>();
        partition.add(0);
        for(int x = 0; x < img.getWidth(); x++) {
            if(looking) {
                boolean found = true;
                for(int y = 1; y < img.getHeight(); y++) {
                    found = found && colorDist(img.getRGB(x,y-1),img.getRGB(x,y)) < 50;
                }
                if(found) {
                    looking = false;
                    partition.add(x);
                }
            } else {
                for(int y=1; y < img.getHeight(); y++) {
                    if(colorDist(img.getRGB(x,y-1),img.getRGB(x,y)) > 50) {
                        looking = true;
                        break;
                    }
                }
            }
        }
        BufferedImage[] ans = new BufferedImage[partition.size()-1];
        for(int i=0; i < ans.length; i++) {
            ans[i] = compact(img.getSubimage(partition.get(i), 0, partition.get(i+1)-partition.get(i), img.getHeight()));
            ImageIO.write(ans[i],"png",new File("C:/Users/Student/Desktop/result"+i+".png"));
        }
        return ans;
    }

    private static Rectangle getBounds(BufferedImage img, int x, int y) {
        Rectangle rect = new Rectangle(img.getWidth(), img.getHeight(), 0, 0);
        getBounds(img, x, y, new boolean[img.getWidth()][img.getHeight()], rect);
        rect.width = rect.width - rect.x + 3;
        rect.height = rect.height - rect.y + 3;
        rect.x -= 1;
        rect.y -= 1;
        return rect;
    }

    private static void getBounds(BufferedImage img, int x, int y, boolean[][] visited, Rectangle ans) {
        if (x < 1 || x >= img.getWidth() -1 || y < 1 || y >= img.getHeight() - 1 || visited[x][y])
            return;
        visited[x][y] = true;
        if (x < ans.x)
            ans.x = x;
        if (y < ans.y)
            ans.y = y;
        if (x > ans.width)
            ans.width = x;
        if (y > ans.height)
            ans.height = y;
        if (img.getRGB(x, y) == img.getRGB(x + 1, y + 1))
            getBounds(img, x + 1, y + 1, visited, ans);
        if (img.getRGB(x, y) == img.getRGB(x + 1, y))
            getBounds(img, x + 1, y, visited, ans);
        if (img.getRGB(x, y) == img.getRGB(x + 1, y - 1))
            getBounds(img, x + 1, y - 1, visited, ans);
        if (img.getRGB(x, y) == img.getRGB(x, y + 1))
            getBounds(img, x, y + 1, visited, ans);
        if (img.getRGB(x, y) == img.getRGB(x, y - 1))
            getBounds(img, x, y - 1, visited, ans);
        if (img.getRGB(x, y) == img.getRGB(x - 1, y + 1))
            getBounds(img, x - 1, y + 1, visited, ans);
        if (img.getRGB(x, y) == img.getRGB(x - 1, y))
            getBounds(img, x - 1, y, visited, ans);
        if (img.getRGB(x, y) == img.getRGB(x - 1, y - 1))
            getBounds(img, x - 1, y - 1, visited, ans);
    }

    private static String classify2(BufferedImage img) throws Exception {
        for (int i = 0; i < 9; i++)
            if (bufferedImagesEqual(img, knownImages[i]))
                return "" + i;
        if (bufferedImagesEqual(img, pt))
            return ".";
        ImageIO.write(knownImages[1], "png",new File("C:/Users/Student/Desktop/a.png"));
        System.out.println(bufferedImagesEqual(img, knownImages[1]));
        return "";
    }

    private static boolean bufferedImagesEqual(BufferedImage img1, BufferedImage img2) {
        if (img1.getWidth() == img2.getWidth() && img1.getHeight() == img2.getHeight()) {
            for (int x = 0; x < img1.getWidth(); x++) {
                for (int y = 0; y < img1.getHeight(); y++) {
                    if (colorDist(img1.getRGB(x, y), img2.getRGB(x, y)) > 250)
                        return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }
}
