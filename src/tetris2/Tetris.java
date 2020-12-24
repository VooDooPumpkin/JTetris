/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris2;

import tetris2.views.Menu;

/**
 *
 * @author SamWe
 */
public class Tetris {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            Menu menu = new Menu();
            Menu.main(args);
        }
            catch (CloneNotSupportedException e){
            }
    }
    
}
