package tetris2.game.events;

import java.util.EventObject;
import tetris2.figure.Figure;
import tetris2.heap.Heap;

public class FigureEvent
extends EventObject {
    private Figure figure;
    private Heap heap;

    public FigureEvent(Object source) {
        super(source);
    }

    public Heap getHeap() {
        return this.heap;
    }

    public void setHeap(Heap heap) {
        this.heap = heap;
    }

    public Figure getFigure() {
        return this.figure;
    }

    public void setFigure(Figure figure) {
        this.figure = figure;
    }
}

