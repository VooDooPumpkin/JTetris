/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris2.game;

import java.util.logging.Level;
import java.util.logging.Logger;
import tetris2.figure.Figure;
import tetris2.figure.factory.FigureFactory;
import tetris2.game.events.GameListener;
import tetris2.game.events.GlassEvent;
import tetris2.game.events.GlassFloorEvent;
import tetris2.game.events.GlassFloorListener;
import tetris2.game.events.GlassListener;

/**
 *
 * @author SamWe
 */
public class Game {
    private Figure nextFigure;
    private int score = 0;
    private boolean game_over = false;
    private GameListener gameListener;
    private FigureFactory figureFactory = new FigureFactory();
    private Glass glass;

    public Game(int glassWidth, int glassHeight, TFloorCleaner floorCleaner) throws CloneNotSupportedException {
        this.nextFigure = this.figureFactory.createFigure();
        this.glass = new Glass(glassWidth, glassHeight, floorCleaner);
        this.glass.addGlassListener(new GlassObserver());
        this.glass.getGlassFloor().addGlassFloorListener(new GlassFloorObserver());
    }
    /**
     *Получить счёт
     */
    public int getScore(){
        return this.score;
    }
    
    /**
     *Получить стакан
     */
    public Glass getGlass(){
        return this.glass;
    }
    
    /**
     *Добавить очки к счёту
     */
    public void addScore(int scorePoints){
        this.score += scorePoints;
    }
    
    /**
     *Изменить стратегию компановки рядов
     */
    public void changeFloorCleaner(TFloorCleaner floorCleaner){
        this.glass.changeFloorCleaner(floorCleaner);
    }
    
    /**
     *Изменить размер стакана
     */
    public void resizeGlass(int width, int height){
        this.glass._reSize(width, height);
    }
    
    /**
     *Добавить слушатель игры
     */
    public void addGameListener(GameListener listener) {
        this.gameListener = listener;
    }
    

    /**
     *Начать игру
     */
    public void start() throws CloneNotSupportedException {
        this.setNextFigure();
    }
    
    /**
     *Получить следующую фигуру
     */
    public Figure getNextFigure() throws CloneNotSupportedException {
        return this.nextFigure;
    }

    /**
     *Установить следующую фигуру
     */
    public void setNextFigure() throws CloneNotSupportedException {
        if (this.game_over) {
            return;
        }
        this.glass.setControllingFigure(this.nextFigure);
        this.nextFigure = this.figureFactory.createFigure();
        this.fireNewNextFigure();
    }
    
    /**
     *Закончить игру
     */
    public void finishGame(){
        this.game_over = true;
    }
    
    /**
     *Создать сигнал, что следушая фигура изменилась
     */
    public void fireNewNextFigure() {
        this.gameListener.newNextFigure();
    }
    
    /**
     *Создать сигнал, что счёт изменился
     */
    public void fireScoreChanged() {
        this.gameListener.scoreChanged();
    }
    
    /**
     *Создать сигнал, что игра окончена
     */
    public void fireGameOver() {
        this.gameListener.gameOver();
    }
    

    class GlassObserver
    implements GlassListener {
        GlassObserver() {
        }

        @Override
        public void gameOver() {
            if (!Game.this.game_over) {
                Game.this.game_over = true;
                Game.this.fireGameOver();
            }
        }

        @Override
        public void goNextFigure() {
            if (!Game.this.game_over) {
                try {
                    Game.this.setNextFigure();
                }
                catch (CloneNotSupportedException ex) {
                    Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }


    class GlassFloorObserver
        implements GlassFloorListener {

        public GlassFloorObserver(){
        }

        @Override
        public void addScorePoints(GlassFloorEvent event) {
            Game.this.addScore(event.getAddScore());
            Game.this.fireScoreChanged();
        }

    }
}
