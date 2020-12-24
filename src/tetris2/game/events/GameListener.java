/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris2.game.events;

/**
 *
 * @author SamWe
 */
public interface GameListener {
    public void newNextFigure();
    public void scoreChanged();
    public void gameOver();
}
