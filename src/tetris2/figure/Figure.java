package tetris2.figure;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;
import tetris2.game.events.FigureEvent;
import tetris2.game.events.FigureListener;
import tetris2.heap.Cell;
import tetris2.heap.FigureHeap;
import tetris2.heap.Heap;

public class Figure
implements ActionListener {
    private static final int DOWN_TIMER = 500;
    protected FigureHeap cells = new FigureHeap();
    protected Cell turningCell;
    protected Timer timer;
    protected Color color;
    protected int figureId;
    protected FigureListener figureListener;
    protected String name;
    private boolean isControlling = false;
    private boolean isOnFloor = false;

    /**
     *Получить имя
     */
    public String getName() {
        return this.name;
    }
    
    /**
     *Является ли фигура контролируемой
     */
    public boolean isControlling() {
        return this.isControlling;
    }
    
    /**
     *Установить флаг контроля
     */
    public void setIsControlling(boolean value) {
        this.isControlling = value;
    }
    
    /**
     *Находится ли фигура на дне
     */
    public boolean isOnFloor() {
        return this.isOnFloor;
    }
    
    /**
     *Получить набор клеток фигуры
     */
    public FigureHeap getHeap() {
        return this.cells;
    }
    
    /**
     *Установить набор клеток фигуры
     */
    private void setHeap(FigureHeap heap) {
        this.cells = heap;
    }

    public Figure() {
        this.timer = new Timer(DOWN_TIMER, this);
    }
    /**
     *Повернуть фигуру случайное кол-во раз
     */
    protected void rotateRandomTimes() {
        int rand = new Random().nextInt(4);
        for (int i = 0; i < rand; ++i) {
            this.turnClockwise();
        }
    }
    
    /**
     *Установить центр фигуры в указанную позицию
     */
    public void setPosCenterCell(int newX, int newY) {
        int x = this.turningCell.getPos().x;
        int y = this.turningCell.getPos().y;
        for (int i = 0; i < this.cells.size(); ++i) {
            Point point = this.cells.get(i).getPos();
            this.cells.get(i).setPos(point.x - x + newX, point.y - y + newY);
        }
    }
    
    /**
     *Попробовать подвинуть фигуру вниз
     */
    public boolean askToMoveDown() throws CloneNotSupportedException {
        FigureEvent event = new FigureEvent(this);
        Figure temp = this.clone();
        temp.moveDown();
        event.setFigure(temp);
        this.figureListener.askForPlace(event);
        if (this.figureListener.askForPlace(event)) {
            this.moveDown();
            this.fireMoved();
            return true;
        }
        else {
            this.isOnFloor = true;
            event.setFigure(this);
            this.fireLocatedOnFloor(event);
        }
        return false;
    }
    
    /**
     *Подвинуть фигуру вниз
     */
    protected void moveDown() {
        for (int i = 0; i < this.cells.size(); ++i) {
            this.cells.get(i).getPos().translate(0, 1);
        }
    }
    
    /**
     *Попробовать подвинуть фигуру влево
     */
    public boolean askToMoveLeft() throws CloneNotSupportedException {
        FigureEvent event = new FigureEvent(this);
        Figure temp = this.clone();
        temp.moveLeft();
        event.setHeap(temp.getHeap());
        event.setFigure(temp);
        if (!this.isOnFloor && this.figureListener.askForPlace(event)) {
            this.moveLeft();
            this.fireMoved();
            return true;
        }
        return false;
    }
    
    /**
     *Подвинуть фигуру влево
     */
    protected void moveLeft() {
        for (int i = 0; i < this.cells.size(); ++i) {
            this.cells.get(i).getPos().translate(-1, 0);
        }
    }
    
    /**
     *Попробовать подвинуть фигуру вправо
     */
    public boolean askToMoveRight() throws CloneNotSupportedException {
        FigureEvent event = new FigureEvent(this);
        Figure temp = this.clone();
        temp.moveRight();
        event.setHeap(temp.getHeap());
        event.setFigure(temp);
        if (!this.isOnFloor && this.figureListener.askForPlace(event)) {
            this.moveRight();
            this.fireMoved();
            return true;
        }
        return false;
    }

    /**
     *Подвинуть фигуру вправо
     */
    protected void moveRight() {
        for (int i = 0; i < this.cells.size(); ++i) {
            this.cells.get(i).getPos().translate(1, 0);
        }
    }

    /**
     *Попробовать повернуть фигуру по часовой стрелке
     */
    public boolean askToTurnClockwise() throws CloneNotSupportedException {
        FigureEvent event = new FigureEvent(this);
        FigureHeap heap = new FigureHeap();
        for (int i = 0; i < this.cells.size(); ++i) {
            Cell first = this.cells.get(i);
            int x = first.getPos().x - this.turningCell.getPos().x;
            int y = first.getPos().y - this.turningCell.getPos().y;
            Cell second = new Cell(new Point(this.turningCell.getPos().x + y, this.turningCell.getPos().y - x), first.getColor(), first.getFigureId());
            Heap tmpHeap = new Heap(first, second);
            for (int j = 0; j < tmpHeap.size(); ++j) {
                if (heap.contains(tmpHeap.get(j))) continue;
                heap.add(tmpHeap.get(j));
            }
        }
        Figure temp = this.clone();
        temp.setHeap(heap);
        event.setHeap(heap);
        event.setFigure(temp);
        if (!this.isOnFloor && this.figureListener.askForPlace(event)) {
            this.turnClockwise();
            this.fireMoved();
            return true;
        }
        return false;
    }
    
    /**
     *Повернуть фигуру по часовой стрелке
     */
    protected void turnClockwise() {
        for (int i = 0; i < this.cells.size(); ++i) {
            Point point = this.cells.get(i).getPos();
            int x = point.x - this.turningCell.getPos().x;
            int y = point.y - this.turningCell.getPos().y;
            this.cells.get(i).setPos(this.turningCell.getPos().x + y, this.turningCell.getPos().y - x);
        }
    }
    
    /**
     *Попробовать повернуть фигуру против часовой стрелки
     */
    public boolean askToTurnOtherClockwise() throws CloneNotSupportedException {
        FigureEvent event = new FigureEvent(this);
        event.setFigure(this);
        FigureHeap heap = new FigureHeap();
        for (int i = 0; i < this.cells.size(); ++i) {
            Cell first = this.cells.get(i);
            int x = first.getPos().x - this.turningCell.getPos().x;
            int y = first.getPos().y - this.turningCell.getPos().y;
            Cell second = new Cell(new Point(this.turningCell.getPos().x - y, this.turningCell.getPos().y + x), first.getColor(), first.getFigureId());
            Heap tmpHeap = new Heap(first, second);
            for (int j = 0; j < tmpHeap.size(); ++j) {
                Cell cell = tmpHeap.get(j);
                if (heap.contains(cell)) continue;
                heap.add(cell);
            }
        }
        Figure temp = this.clone();
        temp.setHeap(heap);
        event.setHeap(heap);
        event.setFigure(temp);
        if (!this.isOnFloor && this.figureListener.askForPlace(event)) {
            this.turnOtherClockwise();
            this.fireMoved();
            return true;
        }
        return false;
    }

    /**
     *Повернуть фигуру против часовой стрелки
     */
    protected void turnOtherClockwise() {
        for (int i = 0; i < this.cells.size(); ++i) {
            Point point = this.cells.get(i).getPos();
            int x = point.x - this.turningCell.getPos().x;
            int y = point.y - this.turningCell.getPos().y;
            this.cells.get(i).setPos(this.turningCell.getPos().x - y, this.turningCell.getPos().y + x);
        }
    }

    /**
     *Уничтожить фигуру
     */
    public void destroy() {
        this.timer.stop();
        this.cells.clear();
    }

    /**
     *Начать падение фигуры
     */
    public void startFalling() {
        this.timer.start();
    }

    /**
     *Добавить слушатель фигуры
     */
    public void addFigureListener(FigureListener listener) {
        this.figureListener = listener;
    }
    
    /**
     *Создать сигнал, что фигура сдвинулась
     */
    public void fireMoved() {
        this.figureListener.figureMoved();
    }

    /**
     *Создать сигнал, что фигура на дне
     */
    public void fireLocatedOnFloor(FigureEvent event) {
        this.figureListener.figureLanded(event);
    }

    
    @Override
    /**
     *Выполнено действие
     */
    public void actionPerformed(ActionEvent e) {
        try {
            this.askToMoveDown();
        }
        catch (CloneNotSupportedException ex) {
            Logger.getLogger(Figure.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     *Создать копию фигуры
     */
    public Figure clone() throws CloneNotSupportedException {
        Figure temp = new Figure();
        temp.cells = this.cells.clone();
        temp.turningCell = this.turningCell.clone();
        temp.color = this.color;
        temp.isControlling = this.isControlling;
        return temp;
    }
}

