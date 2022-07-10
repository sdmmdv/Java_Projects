/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

public class HighScore {
    
    private final String name;
    private final int score;
    private final String mode;
    private final int level;
    private String ts;

    public HighScore(String ts,String name, int score, String mode, int level) {
        this.ts = ts;
        this.name = name;
        this.score = score;
        this.mode = mode;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public String getMode() {
        return mode;
    }

    public int getLevel() {
        return level;
    }

    public String getTs() {
        return ts;
    }
    
    
            
    @Override
    public String toString() {
        return "HighScore{" + "ts=" + ts + ", name=" + name + ", score=" + score + ", mode=" +
                mode + ", level=" + level + "}";
    }
    

}
