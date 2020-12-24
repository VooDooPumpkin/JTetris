package tetris2.heap;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import tetris2.heap.Cell;

public class Heap {
    protected ArrayList<Cell> Cells = new ArrayList();

    public Heap() {
    }

    public Heap(Cell first, Cell second) {
        int minX = Math.min(first.getPos().x, second.getPos().x);
        int minY = Math.min(first.getPos().y, second.getPos().y);
        int maxX = Math.max(first.getPos().x, second.getPos().x);
        int maxY = Math.max(first.getPos().y, second.getPos().y);
        
        for (int i = minX; i <= maxX; ++i) {
            for (int j = minY; j <= maxY; ++j) {
                this.Cells.add(new Cell(new Point(i, j), first.getColor(), first.getFigureId()));
            }
        }
    }

    /**
     *Добавить клетку
     */
    public void add(Cell cell) {
        this.Cells.add(cell);
    }

    /**
     *Добавить клетку
     */
    public void add(ArrayList<Cell> cell) {
        this.Cells.addAll(cell);
    }

    /**
     *Получить клетку
     */
    public Cell get(int i) {
        return this.Cells.get(i);
    }
    
    /**
     *Получить все клетки
     */
    public ArrayList<Cell> getAll() {
        return this.Cells;
    }
    
    /**
     *Получить количество клеток в куче
     */
    public int size() {
        return this.Cells.size();
    }

    /**
     *Очистить кучу
     */
    public void clear() {
        this.Cells.clear();
    }

    /**
     *Добавить другую кучу
     */
    public void add(Heap heap) {
        for (int i = 0; i < heap.size(); ++i) {
            this.Cells.add(heap.get(i));
        }
    }

    /**
     *Получить кучу клеток в строке Y
     */
    public Heap getHeapByY(int y) {
        Heap heap = new Heap();
        for (int i = 0; i < this.size(); ++i) {
            if (this.Cells.get((int)i).getPos().y != y) continue;
            heap.add(this.Cells.get(i));
        }
        return heap;
    }

    /**
     *Получить кучу клеток в столбце X
     */
    public Heap getHeapByX(int x) {
        Heap heap = new Heap();
        for (int i = 0; i < this.size(); ++i) {
            if (this.Cells.get((int)i).getPos().x != x) continue;
            heap.add(this.Cells.get(i));
        }
        return heap;
    }

    /**
     *Удалить клетку
     */
    public void remove(Cell cell) {
        this.Cells.remove(cell);
    }

    /**
     *Удалить кучу
     */
    public void remove(Heap heap) {
        for (int i = 0; i < heap.size(); ++i) {
            this.Cells.remove(heap.get(i));
        }
    }

    /**
     *Имеет общую часть с другой кучей
     */
    public boolean hasCommonPart(Heap heap) {
        for (int i = 0; i < heap.size(); ++i) {
            for (int j = 0; j < this.size(); ++j) {
                if (!this.get(j).getPos().equals(heap.get(i).getPos())) continue;
                return true;
            }
        }
        return false;
    }

    /**
     *Проверить, содержит ли клетку
     */
    public boolean contains(Cell cell) {
        for (Cell tmp : this.Cells) {
            if (!cell.getPos().equals(tmp.getPos()) || !cell.getColor().equals(tmp.getColor())) continue;
            return true;
        }
        return false;
    }
}

