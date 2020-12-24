/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris2.game;

import tetris2.figure.Figure;
import tetris2.game.events.GlassFloorEvent;
import tetris2.game.events.GlassFloorListener;
import tetris2.heap.FloorHeap;
import tetris2.heap.Heap;

/**
 *
 * @author SamWe
 */
class GlassFloor {
    private Glass glass;
    private FloorHeap cells;
    private TFloorCleaner floorCleaner;
    private GlassFloorListener glassFloorListener;
    
    GlassFloor(Glass glass, TFloorCleaner floorCleaner)
    {
        this.glass = glass;
        this.cells = new FloorHeap();
        this.floorCleaner = floorCleaner;
        this.floorCleaner.setFloor(this);
    }
    
    /**
    *Очистить дно стакана
    */
    public void clear() {
        this.cells.clear();
    }

    /**
     *Получить клетки из которых состоит дно стакана
     */
    public FloorHeap getCells() {
        return this.cells;
    }
    
    /**
     *Получить стакан, которому принадлежит дно
     */
    public Glass getGlass() {
        return this.glass;
    }
    
    /**
     *Установить стратегию упаковки рядов
     */
    public void setFloorCleaner(TFloorCleaner floorCleaner) {
        if (this.floorCleaner != null){
            this.floorCleaner.setFloor(null);
        }
        this.floorCleaner = floorCleaner;
        this.floorCleaner.setFloor(this);
    }
    
    /**
     *Сдвинуть на одну строку вниз все строки выше Y
     */
    public void moveDownOneRowAboveY(int y) {
        for (int i = 0; i < this.cells.size(); ++i) {
            if (this.cells.get((int)i).getPos().y >= y) continue;
            this.cells.get(i).getPos().translate(0, 1);
        }
    }
    
    /**
     *Создать сигнал, о добавлении счёта
     */
    private void fireAddScore(GlassFloorEvent event){
        this.glassFloorListener.addScorePoints(event);
    }
    
    /**
     *Добавить слушатель дна стакана
     */
    public void addGlassFloorListener(GlassFloorListener listener) {
        this.glassFloorListener = listener;
    }
    
    /**
     *Добавить фигуру ко дну стакана
     */
    public void addFigureToGlassFloor(Figure figure) {
        GlassFloorEvent event = new GlassFloorEvent(this);
        int scorePoints = 0;
        this.cells.add(figure.getHeap());
        figure.destroy();
        scorePoints = this.floorCleaner.removeFullRows();
        event.setAddScore(scorePoints);
        this.fireAddScore(event);
    }
}
