package pokerbot.main;

import java.util.HashMap;
import pokerbot.ai.AIInterface;
import pokerbot.config.PropsParser;
import pokerbot.config.SoftwareProperties;
import pokerbot.robot.img.CardImage;
import pokerbot.robot.img.GameWindow;
import pokerbot.robot.img.MainWindow;
import pokerbot.utils.Utils;

public class ResourceManager {
    
    private AIInterface aiplayer;
    private SoftwareProperties sp;
    
    private long start;
    private long end;
    private int buyin;
    
    public ResourceManager() {
        try {
            HashMap<String,String> map = PropsParser.parseFile("config/mainconfig.properties");
            start = Long.parseLong(map.get("start"));
            end = Long.parseLong(map.get("end"));
            buyin = Integer.parseInt(map.get("buyin"));
            sp = new SoftwareProperties();
            
            CardImage.loadKnownCards(sp.imageKey);
            
        } catch(NumberFormatException e) {
            e.printStackTrace();
        }
    }
   
    public void manage() {
        MainWindow main = new MainWindow(sp);
        main.login();
        GameWindow gameWindow = main.joinGame();
        if(gameWindow == null) {
            System.out.println("No suitable game was found... terminating bot");
            return;
        }
        //GameWindow gameWindow = new GameWindow(sp);
        Utils.sleep(2500);
        aiplayer = new AIInterface(gameWindow);
        aiplayer.automatePlay(250);
        
        
    }
    
}
