package pokerbot.robot.img;

import com.sun.jna.platform.win32.WinDef.HWND;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * This class directly interfaces with the User32 class and provides 
 * clean and easy functions to be called from gameWindow
 */
public class Window {
    
    HWND hwnd;
    
    public Window(HWND hwnd) {
        this.hwnd = hwnd;
    }
    
    public Rectangle getScreenRect() {
        int[] r = new int[4];
        User32.INSTANCE.GetWindowRect(hwnd, r);
        return new Rectangle(r[0],r[1],r[2]-r[0],r[3]-r[1]);
    }
    public boolean isForegroundWindow() {
        return User32.INSTANCE.GetForegroundWindow().equals(hwnd);
    }
    public void setForegroundWindow() {
        User32.INSTANCE.SetForegroundWindow(hwnd);
    }
    
    public Rectangle combine(Rectangle relative) {
        Rectangle screen = getScreenRect();
        return new Rectangle(screen.x + relative.x, screen.y + relative.y, relative.width, relative.height);
    }
    public Point combine(Point p) {
       Rectangle screen = getScreenRect();
       return new Point(p.x+screen.x,p.y+screen.y);
    }
    
}
