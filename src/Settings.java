

import java.awt.*;
import java.util.*;
import java.util.List;

public class Settings {
// Define getters and setters to update game parameters.
    login user =new login();
    private int colId = 4;
    private int rowId = 3;
    private int diffLevel = 1000;
    private int timeInfo = 60;
    public String playerName = user.getUsername();
    private String cardTheme = "classes";
    private Color backgroundColor = Color.white;
    private boolean singlePlayer;
    private List<String> multiplePlayerName = Arrays.asList("Player 1 ");
    
    public int getDiffLevel() {
        return this.diffLevel;
    }
    public void setDiffLevel(int level) {
        this.diffLevel = level;
        System.out.println("Setting is ok " + level);
    }
    
    public String getCardTheme() {

        return this.cardTheme;
    }
    public void setCardTheme(String theme) {
        this.cardTheme = theme;
        System.out.println("Setting is ok " + theme);
    }
    
    public Color getBackColor() {
        return this.backgroundColor;
    }
   public void setBackColor(Color color) {
        this.backgroundColor = color;
        System.out.println("Setting is ok " + color);
    }
   
   
   
    public void setrowId(int id) {
        this.rowId = id;
    }
    public int getrowId() {
        return this.rowId;
    }
    
    
    
    public void setcolId(int id) {
        this.colId = id;
    }
    public int getcolId() {
        return this.colId;
    }
    
    
    
    public int getTimeInfo() {

        return this.timeInfo;
    }
    public void setTimeInfo(int time) {

        this.timeInfo = time;
    }
    
    
    public String setSingleName(String name) {
        return this.playerName = name;
    }
    public boolean setSinglePlayer(boolean name) {

        if (name) {
            return this.singlePlayer = true;
        } else {
            return this.singlePlayer = false;
        }
    }
    public boolean getSinglePlayer() {
        return this.singlePlayer;
    }
    public String getSingleName() {
        return this.playerName;
    }
    public List<String> getPlayersName() {
        return multiplePlayerName;
    }
    public void setPlayersName(List<String> tmpList) {
        this.multiplePlayerName = tmpList;
    }
}

