package pokerbot.config;

import java.awt.Point;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class PropsParser {
    
    public static HashMap<String,String> parseFile(String file) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            HashMap<String,String> map = new HashMap<>();
            String line;
            while((line = br.readLine()) != null) {
                String[] s= parseByEqual(line);
                map.put(s[0],s[1]);
            }
            return map;
        } catch(IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static String[] parseByEqual(String s) {
        String ans = "";
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '=') {
                return new String[] { s.substring(0,i), ans };
            } else {
                ans = s.charAt(i) + ans;
            }
        }
        System.out.println("Error with the config file!");
        return null;
    }
    protected static Point[] parsePoints(String s) {
        ArrayList<String> points = getPoints(s);
        Point[] ans = new Point[points.size()];
        for(int j=0; j<ans.length; j++) {
            String pt = points.get(j);
            String x = "", y = "";
            for(int i=1; pt.charAt(i) != ','; i++)
                x += pt.charAt(i);
            for(int i=pt.length() - 2; pt.charAt(i) != ','; i--)
                y = pt.charAt(i) + y;
            ans[j] = new Point(Integer.parseInt(x),Integer.parseInt(y));
        }
        return ans;
    }
    private static ArrayList<String> getPoints(String s) {
        ArrayList<String> ans = new ArrayList<>();
        String word = "";
        for (int i=0; i<s.length(); i++) {
            word += s.charAt(i);
            if(s.charAt(i) == ')') {
                ans.add(word);
                word = "";
            }
        }
        return ans;
    }
    protected static Rectangle parseToRect(String s) {
        Point[] p = parsePoints(s);
        return new Rectangle(p[0].x,p[0].y,p[1].x-p[0].x,p[1].y-p[0].y);
    }
    
}
