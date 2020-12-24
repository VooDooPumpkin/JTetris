package tetris2.figure.factory;

import java.util.Random;
import tetris2.figure.BoxFigure;
import tetris2.figure.Figure;
import tetris2.figure.GFigure;
import tetris2.figure.IFigure;
import tetris2.figure.LFIgure;
import tetris2.figure.SFigure;
import tetris2.figure.TFigure;
import tetris2.figure.ZFigure;

public class FigureFactory {
    private Random rand = new Random();
    private static int maxFigureId = 0;

    /**
     *Создать фигуру
     */
    public Figure createFigure() {
        Figure figure = null;
        int next = this.rand.nextInt(7);
        switch (next) {
            case 0: {
                figure = new BoxFigure(0, 0, getNewFigureId());
                break;
            }
            case 1: {
                figure = new GFigure(0, 0, getNewFigureId());
                break;
            }
            case 2: {
                figure = new IFigure(0, 0, getNewFigureId());
                break;
            }
            case 3: {
                figure = new LFIgure(0, 0, getNewFigureId());
                break;
            }
            case 4: {
                figure = new SFigure(0, 0, getNewFigureId());
                break;
            }
            case 5: {
                figure = new TFigure(0, 0, getNewFigureId());
                break;
            }
            case 6: {
                figure = new ZFigure(0, 0, getNewFigureId());
            }
        }
        return figure;
    }
    
    /**
     *Получить новый айди фигуры
     */
    private int getNewFigureId(){
        FigureFactory.maxFigureId += 1;
        return FigureFactory.maxFigureId;
    }

}

