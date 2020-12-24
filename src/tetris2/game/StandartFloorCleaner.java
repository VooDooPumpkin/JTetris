/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris2.game;

import tetris2.heap.Cell;

/**
 *
 * @author SamWe
 */
public class StandartFloorCleaner
extends TFloorCleaner{
    
    private static final int SCORE_PER_CELL = 10;
    public static final String NAME = "Стандартная";

    public StandartFloorCleaner() {
        super();
    }
    
    /**
     *Убрать упакованные ряды
     */
    @Override
    public int removeFullRows() {
        int score = 0;
        for (int i = 0; i < this.getFloor().getGlass()._getHeight(); ++i) {
            if (this.getFloor().getCells().numCellsWithY(i) != this.getFloor().getGlass()._getWidth()){
                continue;
            }else{               
                    score += this.getFloor().getGlass()._getWidth() * SCORE_PER_CELL;
                }            
            this.getFloor().getCells().removeAllCellByY(i);
            this.getFloor().moveDownOneRowAboveY(i);
        }
        return score;
    }
    
}
