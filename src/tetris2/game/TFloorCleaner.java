/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris2.game;

/**
 *
 * @author SamWe
 */
public abstract class TFloorCleaner {
    private GlassFloor floor;
    public static final String NAME = "Абстрактная стратегия упаковки";
    
    public TFloorCleaner(){
    }
    
    /**
     *Получить дно стакана, которому принадлежит упаковщик
     */
    public GlassFloor getFloor(){
        return this.floor;
    }
    
    /**
     *Установить дно стакана, которому принадлежит упаковщик
     */
    public void setFloor(GlassFloor floor){
        this.floor = floor;
    }
    
    /**
     *Убрать упакованные ряды
     */
    abstract public int removeFullRows();
}
