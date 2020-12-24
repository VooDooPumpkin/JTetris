package tetris2.views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import tetris2.heap.Heap;
import tetris2.game.Glass;
import tetris2.game.events.GlassViewListener;

public class GlassPanel
extends JPanel {
    public static final int CELL_SIZE = 25;
    private Glass glass;

    public GlassPanel(Glass glass) {
        this.glass = glass;
        this.glass.addGlassViewListener(new GlassViewObserver());
    }
    
    /**
     *Нарисовать квадрат
     */
    private void drawSquare(Graphics g, int x, int y, Color color) {
        g.setColor(color);
        g.fillRect(x + 1, y + 1, CELL_SIZE - 2, CELL_SIZE - 2);
        g.setColor(color.brighter());
        g.drawLine(x, y + CELL_SIZE - 1, x, y);
        g.drawLine(x, y, x + CELL_SIZE - 1, y);
        g.setColor(color.darker());
        g.drawLine(x + 1, y + CELL_SIZE - 1, x + CELL_SIZE - 1, y + CELL_SIZE - 1);
        g.drawLine(x + CELL_SIZE - 1, y + CELL_SIZE - 1, x + CELL_SIZE - 1, y + 1);
    }

    /**
     *Рисовать стакан
     */
    public void doDrawing(Graphics g) throws CloneNotSupportedException {
        Graphics2D g2d = (Graphics2D)g;
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHints(rh);
        g2d.setColor(new Color(20, 26, 69));
        g2d.fillRect(0, 0, this.glass._getWidth() * CELL_SIZE,
                this.glass._getHeight() * CELL_SIZE);
        Heap myHeap = this.glass.getAllCells();
        if (myHeap != null) {
            for (int i = 0; i < myHeap.size(); ++i) {
                this.drawSquare(g2d, myHeap.get((int)i).getPos().x * CELL_SIZE, myHeap.get((int)i).getPos().y * CELL_SIZE, myHeap.get(i).getColor());
            }
        }
    }

    /**
     *Нарисвоать компонент
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            this.doDrawing(g);
        }
        catch (CloneNotSupportedException ex) {
            Logger.getLogger(GlassPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    class GlassViewObserver
        implements GlassViewListener {

        public GlassViewObserver(){
        }

        @Override
        public void glassChanged() {
            GlassPanel.this.repaint();
        }

    }

}

