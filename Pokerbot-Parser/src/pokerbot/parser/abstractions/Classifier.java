package pokerbot.parser.abstractions;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import pokerbot.gamestate.Card;
import pokerbot.parser.Utils;

public class Classifier {
	
	private Resources resources;
	
	public Classifier(Resources resources) {
		this.resources = resources;
	}
	
	/**
	 * @param img The image that needs to be classified
	 * @return The Card object corresponding to the given image or null if no card can be found
	 */
	protected Card classifyCard(BufferedImage img) {
		for(Card card : Card.CARDS)
			if(Utils.samePic(img, resources.CARD_IMAGES[card.getValue()]))
				return card;
		if(Utils.samePic(img, resources.CARD_UNKNOWN))
			return Card.UNKNOWN;
		return null;
	}
	
	public boolean isButton(BufferedImage img) {
		return Utils.samePic(img, resources.BUTTON);
	}
	
	//OCR
	
    public float getValue(BufferedImage img) {
        try {
            BufferedImage[] part = partition(img);
            String result = "";
            for (BufferedImage part1 : part)
                result += classify2(part1);
            if(result.length()==0) return -1;
            return Float.parseFloat(result);
        } catch(Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    
    private BufferedImage compact(BufferedImage img) {
        int x1=0,y1=0,x2=img.getWidth()-1,y2=img.getHeight()-1;
        loop1:
        for(int y = 0; y < img.getHeight(); y++) {
            for(int x = 1; x < img.getWidth(); x++) {
                if(Utils.colorDist(img.getRGB(x-1,y),img.getRGB(x,y)) > 50) {
                    break loop1;
                }
            }
            y1 = y;
        }
        loop2:
        for(int y = img.getHeight()-1; y >=0; y--) {
            for(int x = 1; x < img.getWidth(); x++) {
                if(Utils.colorDist(img.getRGB(x-1,y),img.getRGB(x,y)) > 50) {
                    break loop2;
                }
            }
            y2 = y;
        }
        loop3:
        for(int x = 0; x < img.getWidth(); x++) {
            for(int y = 1; y < img.getHeight(); y++) {
                if(Utils.colorDist(img.getRGB(x,y-1),img.getRGB(x,y)) > 50) {
                    break loop3;
                }
            }
            x1 = x;
        }
        loop4:
        for(int x = img.getWidth()-1; x >= 0; x--) {
            for(int y = 1; y < img.getHeight(); y++) {
                if(Utils.colorDist(img.getRGB(x,y-1),img.getRGB(x,y)) > 50) {
                    break loop4;
                }
            }
            x2=x;
        }
        return img.getSubimage(x1+1,y1+1,x2-x1,y2-y1-1);
    }
    
    private BufferedImage[] partition(BufferedImage img) throws Exception {
        boolean looking = false;
        ArrayList<Integer> partition = new ArrayList<>();
        partition.add(0);
        for(int x = 0; x < img.getWidth(); x++) {
            if(looking) {
                boolean found = true;
                for(int y = 1; y < img.getHeight(); y++) {
                    found = found && Utils.colorDist(img.getRGB(x,y-1),img.getRGB(x,y)) < 50;
                }
                if(found) {
                    looking = false;
                    partition.add(x);
                }
            } else {
                for(int y=1; y < img.getHeight(); y++) {
                    if(Utils.colorDist(img.getRGB(x,y-1),img.getRGB(x,y)) > 50) {
                        looking = true;
                        break;
                    }
                }
            }
        }
        BufferedImage[] ans = new BufferedImage[partition.size()-1];
        for(int i=0; i < ans.length; i++) {
            ans[i] = compact(img.getSubimage(partition.get(i), 0, partition.get(i+1)-partition.get(i), img.getHeight()));
        }
        return ans;
    }

    private String classify2(BufferedImage img) throws Exception {
        for (int i = 0; i < 10; i++)
            if (bufferedImagesEqual(img, resources.DIGIT_IMAGES[i]))
                return "" + i;
        if (bufferedImagesEqual(img, resources.POINT))
            return ".";
        return "";
    }

    private boolean bufferedImagesEqual(BufferedImage img1, BufferedImage img2) {
        if (img1.getWidth() == img2.getWidth() && img1.getHeight() == img2.getHeight()) {
            for (int x = 0; x < img1.getWidth(); x++) {
                for (int y = 0; y < img1.getHeight(); y++) {
                    if (Utils.colorDist(img1.getRGB(x, y), img2.getRGB(x, y)) > 200)
                        return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

}
