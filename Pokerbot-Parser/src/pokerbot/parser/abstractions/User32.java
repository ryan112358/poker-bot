package pokerbot.parser.abstractions;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIOptions;

public interface User32 extends StdCallLibrary {

    User32 INSTANCE = (User32) Native.loadLibrary("user32", User32.class,
            W32APIOptions.DEFAULT_OPTIONS);

    WinDef.HWND FindWindow(String lpClassName, String lpWindowName);
    int GetWindowRect(WinDef.HWND handle, int[] rect);
    boolean SetForegroundWindow(WinDef.HWND hWnd);
    void SwitchToThisWindow(WinDef.HWND hWnd, boolean b);
    boolean MoveWindow(WinDef.HWND hWnd, int x, int y, int w, int h, boolean b);
    WinDef.HWND GetForegroundWindow();
    boolean SetWindowText(WinDef.HWND hwnd, String text);
    boolean CloseWindow(WinDef.HWND hwnd);
    boolean ShowWindow(WinDef.HWND hwnd, int type);
    boolean DestroyWindow(WinDef.HWND hwnd);
    int GetWindowText(WinDef.HWND hwnd, char[] lpString, int nMaxCount);
}
