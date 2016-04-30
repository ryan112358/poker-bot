package pokerbot.utils;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Utils {
    
    public static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void sleep(long tmin, long tmax) {
        try {
            Thread.sleep(tmin + (int) (Math.random() * (tmax - tmin)));
        } catch (InterruptedException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
