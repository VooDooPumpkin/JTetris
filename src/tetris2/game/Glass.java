/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris2.game;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import tetris2.figure.Figure;
import tetris2.game.events.FigureEvent;
import tetris2.game.events.FigureListener;
import tetris2.game.events.GlassFloorListener;
import tetris2.game.events.GlassListener;
import tetris2.game.events.GlassViewListener;
import tetris2.heap.Heap;

/**
 *
 * @author SamWe
 */
public class Glass {
    private Figure controllingFigure;
    private int _width;
    private int _height;
    private GlassFloor floor;
    private GlassListener glassListener;
    private GlassViewListener glassViewListener;
    
    public Glass(int widht, int height, TFloorCleaner floorCleaner){
        this._width = widht;
        this._height = height;
        this.floor = new GlassFloor(this, floorCleaner);
    }
    
    /**
     *Установать фигуру котрая будет управляться
     */
    public void setControllingFigure(Figure figure) throws CloneNotSupportedException {
        this.controllingFigure = figure;
        this.controllingFigure.setPosCenterCell(this._width / 2, -2);
        this.controllingFigure.addFigureListener(new FigureObserver());
        this.controllingFigure.setIsControlling(true);
        if (!this.controllingFigure.askToMoveDown()) {
            this.fireGameOver();
            return;
        }
        this.controllingFigure.startFalling();
    }
    
    /**
    *Очистить стакан
    */
    public void clear() {
        this.controllingFigure = null;
        this.floor.clear();
    }
    
    /**
     *Получить все клетки стакана
     */
    public Heap getAllCells() {
        Heap heap = new Heap();
        heap.add(this.floor.getCells());
        heap.add(this.controllingFigure.getHeap());
        return heap;
    }

    /**
     *Изменить размер стакана
     */
    public void _reSize(int width, int height) {
        this.clear();
        this._width = width;
        this._height = height;
    }

    /**
     *Получить ширину
     */
    public int _getWidth() {
        return this._width;
    }
    
    

    /**
     *Получить высоту
     */
    public int _getHeight() {
        return this._height;
    }
    
    /**
     *Получить дно
     */
    public GlassFloor getGlassFloor() {
        return this.floor;
    }
    
    /**
     *Изменить стратегию упаковки рядов
     */
    public void changeFloorCleaner(TFloorCleaner floorCleaner) {
        this.floor.setFloorCleaner(floorCleaner);
    }
    
    

    /**
     *Проверить, что фигура внутри стакана
     */
    private boolean belongToGlass(Figure figure) {
        for (int i = 0; i < figure.getHeap().size(); ++i) {
            Point point = figure.getHeap().get(i).getPos();
            if (point.x >= 0 && point.x < this._width && point.y < this._height) continue;
            return false;
        }
        return true;
    }

    /**
     *Проверить, что место для фигуры свободно
     */
    public boolean isPlaceFree(Figure figure) throws CloneNotSupportedException {
        return this.belongToGlass(figure) && !this.floor.getCells().hasCommonPart(figure.getHeap());
    }

    /**
     *Добавить слушатель стакана
     */
    public void addGlassListener(GlassListener listener) {
        this.glassListener = listener;
    }
    
    /**
     *Добавить слушатель отображения стакана
     */
    public void addGlassViewListener(GlassViewListener listener) {
        this.glassViewListener = listener;
    }
    
    /**
     * Создать сигнал, что стакан изменился
     */
    public void fireGlassChanged(){
        this.glassViewListener.glassChanged();
    }

    /**
     *Создать сигнал окончания игры
     */
    public void fireGameOver() {
        if (this.controllingFigure.isOnFloor()) {
            this.glassListener.gameOver();
        }
    }
    
    /**
     *Слушать кнопки
     */
    public void myKeyListener(KeyEvent e) throws CloneNotSupportedException {
        int keycode = e.getKeyCode();
        if (this.controllingFigure == null) {
            return;
        }
        switch (keycode) {
            case 37: {
                this.controllingFigure.askToMoveLeft();
                this.fireGlassChanged();
                break;
            }
            case 39: {
                this.controllingFigure.askToMoveRight();
                this.fireGlassChanged();
                break;
            }
            case 40: {
                this.controllingFigure.askToMoveDown();
                this.fireGlassChanged();
                break;
            }
            case 88: {
                this.controllingFigure.askToTurnClockwise();
                this.fireGlassChanged();
                break;
            }
            case 90: {
                this.controllingFigure.askToTurnOtherClockwise();
                this.fireGlassChanged();
            }
        }
    }
    
    
    

    class FigureObserver
    implements FigureListener {
        FigureObserver() {
        }

        @Override
        public void figureMoved() {
            Glass.this.fireGlassChanged();
        }

        @Override
        public void figureLanded(FigureEvent event) {
            Glass.this.floor.addFigureToGlassFloor(event.getFigure());
            Glass.this.glassListener.goNextFigure();
        }

        @Override
        public boolean askForPlace(FigureEvent event) {
            try {
                return Glass.this.isPlaceFree(event.getFigure());
            }
            catch (CloneNotSupportedException ex) {
                Logger.getLogger(Glass.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
    }

}
