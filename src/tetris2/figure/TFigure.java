package tetris2.figure;

import java.awt.Color;
import java.awt.Point;
import tetris2.figure.Figure;
import tetris2.heap.Cell;
import tetris2.heap.FigureHeap;

public class TFigure
extends Figure {
    public TFigure(int xCenter, int yCenter, int figureId) {
        super();
        this.color = new Color(186, 13, 186);
        this.figureId = figureId;
        this.cells.add(new Cell(new Point(xCenter - 1, yCenter), this.color, this.figureId));
        this.cells.add(new Cell(new Point(xCenter, yCenter), this.color, this.figureId));
        this.cells.add(new Cell(new Point(xCenter + 1, yCenter), this.color, this.figureId));
        this.cells.add(new Cell(new Point(xCenter, yCenter - 1), this.color, this.figureId));
        this.turningCell = this.cells.get(1);
        this.name = "TFigure";
        this.rotateRandomTimes();
    }
}

