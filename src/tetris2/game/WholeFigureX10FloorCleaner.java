/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris2.game;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import tetris2.heap.Cell;

/**
 *
 * @author SamWe
 */
public class WholeFigureX10FloorCleaner
extends TFloorCleaner{
    
    private static final int SCORE_PER_CELL = 10;
    private static final int SCORE_MULTIPLYER = 10;
    public static final String NAME = "Целая фигура X10";

    public WholeFigureX10FloorCleaner() {
        super();
    }
    
    /**
     *Убрать упакованные ряды
     */
    @Override
    public int removeFullRows() {
    int score = 0;
        Map<Integer, Integer> cellsToDel = new HashMap<Integer, Integer>();
        for (int i = 0; i < this.getFloor().getGlass()._getHeight(); ++i) {
            if (this.getFloor().getCells().numCellsWithY(i) != this.getFloor().getGlass()._getWidth()){
                continue;
            }else{
                for (Cell cell : this.getFloor().getCells().getHeapByY(i).getAll()){
                    if( cellsToDel.get(cell.getFigureId()) == null){
                        cellsToDel.put(cell.getFigureId(), 1);
                    }else{
                        cellsToDel.put(cell.getFigureId(), cellsToDel.get(cell.getFigureId()) + 1);
                    }                    
                }
            }            
            this.getFloor().getCells().removeAllCellByY(i);
            this.getFloor().moveDownOneRowAboveY(i);
        }
        Iterator<Map.Entry<Integer, Integer>> it = cellsToDel.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, Integer> pair = it.next();
            if (pair.getValue() == 4){
                score += pair.getValue() * SCORE_MULTIPLYER * SCORE_PER_CELL;
            }
            else{
                score += pair.getValue() * SCORE_PER_CELL;
            }
        }
        return score;
    }
}
