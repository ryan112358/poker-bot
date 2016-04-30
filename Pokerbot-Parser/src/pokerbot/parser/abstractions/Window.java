package pokerbot.parser.abstractions;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef.HWND;

import java.awt.AWTException;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;

import pokerbot.parser.Utils;


/**
 * This class directly interfaces with the User32 class and provides 
 * clean and easy functions to be called from gameWindow
 */
public abstract class Window {
    
    private HWND hwnd;
    private static Robot robot; 
    static {
        try { robot = new Robot(); } 
        catch (AWTException ex) { 
        	Logger.getLogger(Window.class.getName()).log(Level.SEVERE, "Cannot Instantiate java.awt.Robot", ex);
        	System.exit(1);
        }
    }
    
    protected Window(HWND hwnd) {
        this.hwnd = hwnd;
    }
    
    public BufferedImage getScreenShot() {
    	return robot.createScreenCapture(getScreenRect());
    }
    
    public BufferedImage getScreenShot(Rectangle rect) {
    	return robot.createScreenCapture(combine(rect));
    }
    /* This code cannot call the other click method because the combine() method would be called twice */
    public void click(Rectangle r) {
        r = combine(r);
        Point pt = new Point(r.x + (int) (Math.random() * r.width), r.y + (int) (Math.random() * r.height));
        robot.mouseMove(pt.x, pt.y);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    public void click(Point pt) {
        pt = combine(pt);
        robot.mouseMove(pt.x, pt.y);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }
    /* This code cannot call the other doubleClick method because the combine() method would be called twice */
    public void doubleClick(Rectangle r) {
        r = combine(r);
        Point pt = new Point(r.x + (int) (Math.random() * r.width), r.y + (int) (Math.random() * r.height));
        robot.mouseMove(pt.x, pt.y);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    public void doubleClick(Point pt) {
        pt = combine(pt);
        robot.mouseMove(pt.x, pt.y);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    public void move(Point p) {
        p = combine(p);
        robot.mouseMove(p.x, p.y);
    }

    public void move(Rectangle r) {
        r = combine(r);
        Point p = new Point(r.x + (int) (Math.random() * r.width), r.y + (int) (Math.random() * r.height));
        robot.mouseMove(p.x, p.y);
    }

    public void scroll(int amt) {
        for (int i = 0; i < amt; i++) {
            robot.mouseWheel(1);
            Utils.sleep(15,75);
        }
    }
    
    public void type(String str) {
        for (int i = 0; i < str.length(); i++)
            type(str.charAt(i));
    }

    private void doType(int... key) {
        for (int k : key)
            robot.keyPress(k);
        for (int k : key)
            robot.keyRelease(k);
    }

    private void type(char character) {
        switch (character) {
            case 'a':
                doType(KeyEvent.VK_A);
                break;
            case 'b':
                doType(KeyEvent.VK_B);
                break;
            case 'c':
                doType(KeyEvent.VK_C);
                break;
            case 'd':
                doType(KeyEvent.VK_D);
                break;
            case 'e':
                doType(KeyEvent.VK_E);
                break;
            case 'f':
                doType(KeyEvent.VK_F);
                break;
            case 'g':
                doType(KeyEvent.VK_G);
                break;
            case 'h':
                doType(KeyEvent.VK_H);
                break;
            case 'i':
                doType(KeyEvent.VK_I);
                break;
            case 'j':
                doType(KeyEvent.VK_J);
                break;
            case 'k':
                doType(KeyEvent.VK_K);
                break;
            case 'l':
                doType(KeyEvent.VK_L);
                break;
            case 'm':
                doType(KeyEvent.VK_M);
                break;
            case 'n':
                doType(KeyEvent.VK_N);
                break;
            case 'o':
                doType(KeyEvent.VK_O);
                break;
            case 'p':
                doType(KeyEvent.VK_P);
                break;
            case 'q':
                doType(KeyEvent.VK_Q);
                break;
            case 'r':
                doType(KeyEvent.VK_R);
                break;
            case 's':
                doType(KeyEvent.VK_S);
                break;
            case 't':
                doType(KeyEvent.VK_T);
                break;
            case 'u':
                doType(KeyEvent.VK_U);
                break;
            case 'v':
                doType(KeyEvent.VK_V);
                break;
            case 'w':
                doType(KeyEvent.VK_W);
                break;
            case 'x':
                doType(KeyEvent.VK_X);
                break;
            case 'y':
                doType(KeyEvent.VK_Y);
                break;
            case 'z':
                doType(KeyEvent.VK_Z);
                break;
            case 'A':
                doType(KeyEvent.VK_SHIFT, KeyEvent.VK_A);
                break;
            case 'B':
                doType(KeyEvent.VK_SHIFT, KeyEvent.VK_B);
                break;
            case 'C':
                doType(KeyEvent.VK_SHIFT, KeyEvent.VK_C);
                break;
            case 'D':
                doType(KeyEvent.VK_SHIFT, KeyEvent.VK_D);
                break;
            case 'E':
                doType(KeyEvent.VK_SHIFT, KeyEvent.VK_E);
                break;
            case 'F':
                doType(KeyEvent.VK_SHIFT, KeyEvent.VK_F);
                break;
            case 'G':
                doType(KeyEvent.VK_SHIFT, KeyEvent.VK_G);
                break;
            case 'H':
                doType(KeyEvent.VK_SHIFT, KeyEvent.VK_H);
                break;
            case 'I':
                doType(KeyEvent.VK_SHIFT, KeyEvent.VK_I);
                break;
            case 'J':
                doType(KeyEvent.VK_SHIFT, KeyEvent.VK_J);
                break;
            case 'K':
                doType(KeyEvent.VK_SHIFT, KeyEvent.VK_K);
                break;
            case 'L':
                doType(KeyEvent.VK_SHIFT, KeyEvent.VK_L);
                break;
            case 'M':
                doType(KeyEvent.VK_SHIFT, KeyEvent.VK_M);
                break;
            case 'N':
                doType(KeyEvent.VK_SHIFT, KeyEvent.VK_N);
                break;
            case 'O':
                doType(KeyEvent.VK_SHIFT, KeyEvent.VK_O);
                break;
            case 'P':
                doType(KeyEvent.VK_SHIFT, KeyEvent.VK_P);
                break;
            case 'Q':
                doType(KeyEvent.VK_SHIFT, KeyEvent.VK_Q);
                break;
            case 'R':
                doType(KeyEvent.VK_SHIFT, KeyEvent.VK_R);
                break;
            case 'S':
                doType(KeyEvent.VK_SHIFT, KeyEvent.VK_S);
                break;
            case 'T':
                doType(KeyEvent.VK_SHIFT, KeyEvent.VK_T);
                break;
            case 'U':
                doType(KeyEvent.VK_SHIFT, KeyEvent.VK_U);
                break;
            case 'V':
                doType(KeyEvent.VK_SHIFT, KeyEvent.VK_V);
                break;
            case 'W':
                doType(KeyEvent.VK_SHIFT, KeyEvent.VK_W);
                break;
            case 'X':
                doType(KeyEvent.VK_SHIFT, KeyEvent.VK_X);
                break;
            case 'Y':
                doType(KeyEvent.VK_SHIFT, KeyEvent.VK_Y);
                break;
            case 'Z':
                doType(KeyEvent.VK_SHIFT, KeyEvent.VK_Z);
                break;
            case '`':
                doType(KeyEvent.VK_BACK_QUOTE);
                break;
            case '0':
                doType(KeyEvent.VK_0);
                break;
            case '1':
                doType(KeyEvent.VK_1);
                break;
            case '2':
                doType(KeyEvent.VK_2);
                break;
            case '3':
                doType(KeyEvent.VK_3);
                break;
            case '4':
                doType(KeyEvent.VK_4);
                break;
            case '5':
                doType(KeyEvent.VK_5);
                break;
            case '6':
                doType(KeyEvent.VK_6);
                break;
            case '7':
                doType(KeyEvent.VK_7);
                break;
            case '8':
                doType(KeyEvent.VK_8);
                break;
            case '9':
                doType(KeyEvent.VK_9);
                break;
            case '-':
                doType(KeyEvent.VK_MINUS);
                break;
            case '=':
                doType(KeyEvent.VK_EQUALS);
                break;
            case '~':
                doType(KeyEvent.VK_SHIFT, KeyEvent.VK_BACK_QUOTE);
                break;
            case '!':
                doType(KeyEvent.VK_EXCLAMATION_MARK);
                break;
            case '@':
                doType(KeyEvent.VK_SHIFT, KeyEvent.VK_2);
                break;
            case '#':
                doType(KeyEvent.VK_NUMBER_SIGN);
                break;
            case '$':
                doType(KeyEvent.VK_DOLLAR);
                break;
            case '%':
                doType(KeyEvent.VK_SHIFT, KeyEvent.VK_5);
                break;
            case '^':
                doType(KeyEvent.VK_CIRCUMFLEX);
                break;
            case '&':
                doType(KeyEvent.VK_AMPERSAND);
                break;
            case '*':
                doType(KeyEvent.VK_ASTERISK);
                break;
            case '(':
                doType(KeyEvent.VK_LEFT_PARENTHESIS);
                break;
            case ')':
                doType(KeyEvent.VK_RIGHT_PARENTHESIS);
                break;
            case '_':
                doType(KeyEvent.VK_UNDERSCORE);
                break;
            case '+':
                doType(KeyEvent.VK_PLUS);
                break;
            case '\t':
                doType(KeyEvent.VK_TAB);
                break;
            case '\n':
                doType(KeyEvent.VK_ENTER);
                break;
            case '[':
                doType(KeyEvent.VK_OPEN_BRACKET);
                break;
            case ']':
                doType(KeyEvent.VK_CLOSE_BRACKET);
                break;
            case '\\':
                doType(KeyEvent.VK_BACK_SLASH);
                break;
            case '{':
                doType(KeyEvent.VK_SHIFT, KeyEvent.VK_OPEN_BRACKET);
                break;
            case '}':
                doType(KeyEvent.VK_SHIFT, KeyEvent.VK_CLOSE_BRACKET);
                break;
            case '|':
                doType(KeyEvent.VK_SHIFT, KeyEvent.VK_BACK_SLASH);
                break;
            case ';':
                doType(KeyEvent.VK_SEMICOLON);
                break;
            case ':':
                doType(KeyEvent.VK_COLON);
                break;
            case '\'':
                doType(KeyEvent.VK_QUOTE);
                break;
            case '"':
                doType(KeyEvent.VK_QUOTEDBL);
                break;
            case ',':
                doType(KeyEvent.VK_COMMA);
                break;
            case '<':
                doType(KeyEvent.VK_LESS);
                break;
            case '.':
                doType(KeyEvent.VK_PERIOD);
                break;
            case '>':
                doType(KeyEvent.VK_GREATER);
                break;
            case '/':
                doType(KeyEvent.VK_SLASH);
                break;
            case '?':
                doType(KeyEvent.VK_SHIFT, KeyEvent.VK_SLASH);
                break;
            case ' ':
                doType(KeyEvent.VK_SPACE);
                break;
            default:
                System.out.println(character);
                throw new IllegalArgumentException("Cannot type character " + character);
        }
    }

    
    public boolean isForegroundWindow() {
        return User32.INSTANCE.GetForegroundWindow().equals(hwnd);
    }
    /**
     * Brings this window to the front by clicking on it's header.
     */
    public abstract void bringToFront();
    /**
     * Closes this window by clicking on the close button in the corner.
     */
    public abstract void close();
    /**
     * Tells the poker software to cascade this window with the other windows so
     * bringToFront() and close() are guaranteed to work.
     */
    public abstract void cascade();
    
    public String getCaption() {
    	char[] caption = new char[512];
    	User32.INSTANCE.GetWindowText(hwnd,caption,512);
    	return Native.toString(caption);
    }
    
    private Rectangle getScreenRect() {
        int[] r = new int[4];
        User32.INSTANCE.GetWindowRect(hwnd, r);
        return new Rectangle(r[0],r[1],r[2]-r[0],r[3]-r[1]);
    }
    
    private Rectangle combine(Rectangle relative) {
        Rectangle screen = getScreenRect();
        return new Rectangle(screen.x + relative.x, screen.y + relative.y, relative.width, relative.height);
    }
    private Point combine(Point p) {
       Rectangle screen = getScreenRect();
       return new Point(p.x+screen.x,p.y+screen.y);
    }
    
}