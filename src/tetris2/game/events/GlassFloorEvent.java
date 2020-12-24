/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris2.game.events;

import java.util.EventObject;

/**
 *
 * @author SamWe
 */
public class GlassFloorEvent 
extends EventObject {
    private int addScore = 0;

    public GlassFloorEvent(Object source) {
        super(source);
    }

    public void setAddScore(int score) {
        this.addScore = score;
    }
    
    public int getAddScore() {
        return this.addScore;
    }
}
