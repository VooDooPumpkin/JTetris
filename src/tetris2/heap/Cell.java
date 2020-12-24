package tetris2.heap;

import java.awt.Color;
import java.awt.Point;

public class Cell {
    private Point pos;
    private Color color;
    private int figureId;

    public Cell() {
    }

    public Cell(Point pos, Color color, int figureId) {
        this.pos = pos;
        this.color = color;
        this.figureId = figureId;
    }

    /**
     *Получить позицию
     */
    public Point getPos() {
        return this.pos;
    }

    /**
     *Установаить позицию
     */
    public void setPos(Point pos) {
        this.pos = pos;
    }

    /**
     *Установаить позицию
     */
    public void setPos(int x, int y) {
        this.pos.x = x;
        this.pos.y = y;
    }

    /**
     *Получить цвет
     */
    public Color getColor() {
        return this.color;
    }

    /**
     *Установить цвет
     */
    public void setColor(Color color) {
        this.color = color;
    }
    
    /**
     *Получить  айди фигуры, которой принадлежат
     */
    public int getFigureId() {
        return this.figureId;
    }

    /**
     *Установить  айди фигуры, которой принадлежат
     */
    public void setFigureId(int figureId) {
        this.figureId = figureId;
    }

    /**
     *Создать копию клетки
     */
    public Cell clone() throws CloneNotSupportedException {
        return new Cell((Point)this.pos.clone(), this.color, this.figureId);
    }
}

