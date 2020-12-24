package tetris2.views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import tetris2.figure.Figure;

public final class PainNextFigure
extends JPanel {
    private static final int PANEL_WIDTH = 150;
    private static final int PANEL_HEIGHT = 100;
    private Figure figure;
    private int widthSquare;
    private int _width;
    private int _height;

    public PainNextFigure() {
        this.setSize(PANEL_WIDTH, PANEL_HEIGHT);
    }

    /**
     *Установаить фигуру
     */
    public void setFigure(Figure figure) {
        this.figure = figure;
        this._width = figure.getHeap().maxX() - figure.getHeap().minX() + 3;
        this._height = figure.getHeap().maxY() - figure.getHeap().minY() + 3;
        int w = this.getWidth() / this._width;
        int h = this.getHeight() / this._height;
        int minX = figure.getHeap().minX();
        int minY = figure.getHeap().minY();
        this.figure.setPosCenterCell(1 - minX, 1 - minY);
        this.widthSquare = Math.min(w, h);
    }

    /**
     *Нарисовать квадрат
     */
    private void drawSquare(Graphics g, int x, int y, Color color) {
        g.setColor(color);
        g.fillRect(x + 1, y + 1, this.widthSquare - 2, this.widthSquare - 2);
        g.setColor(color.brighter());
        g.drawLine(x, y + this.widthSquare - 1, x, y);
        g.drawLine(x, y, x + this.widthSquare - 1, y);
        g.setColor(color.darker());
        g.drawLine(x + 1, y + this.widthSquare - 1, x + this.widthSquare - 1, y + this.widthSquare - 1);
        g.drawLine(x + this.widthSquare - 1, y + this.widthSquare - 1, x + this.widthSquare - 1, y + 1);
    }

    /**
     *Рисовать
     */
    public void doDrawing(Graphics g) throws CloneNotSupportedException {
        int i;
        Graphics2D g2d = (Graphics2D)g;
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHints(rh);
        g2d.setColor(new Color(144, 247, 235));
        int tempX = (this.getWidth() - this._width * this.widthSquare) / 2;
        int tempY = (this.getHeight() - this._height * this.widthSquare) / 2;
        if (this.figure != null) {
            for (i = 0; i < this.figure.getHeap().size(); ++i) {
                this.drawSquare(g2d, this.figure.getHeap().get((int)i).getPos().x * this.widthSquare + tempX, this.figure.getHeap().get((int)i).getPos().y * this.widthSquare + tempY, this.figure.getHeap().get(i).getColor());
            }
        }
    }

    @Override
    /**
     *Нарисовать компонент
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            this.doDrawing(g);
        }
        catch (CloneNotSupportedException ex) {
            Logger.getLogger(PainNextFigure.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

