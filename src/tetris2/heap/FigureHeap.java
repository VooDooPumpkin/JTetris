package tetris2.heap;

import java.awt.Point;
import java.util.ArrayList;
import tetris2.heap.Cell;
import tetris2.heap.Heap;

public class FigureHeap
extends Heap {
    public Cell leftTopCell() {
        Heap heap = this.getHeapByX(this.minX());
        return this.getCellMaxY(heap);
    }

    public Cell rightTopCell() {
        Heap heap = this.getHeapByX(this.maxX());
        return this.getCellMaxY(heap);
    }

    public Cell topRightCell() {
        Heap heap = this.getHeapByY(this.maxY());
        return this.getCellMaxX(heap);
    }

    private Cell getCellMinY(Heap heap) {
        if (heap.size() == 0) {
            return null;
        }
        int min = 0;
        for (int i = 1; i < heap.size(); ++i) {
            if (heap.Cells.get((int)i).getPos().y >= heap.Cells.get((int)min).getPos().y) continue;
            min = i;
        }
        return heap.Cells.get(min);
    }

    private Cell getCellMaxY(Heap heap) {
        if (heap.size() == 0) {
            return null;
        }
        int max = 0;
        for (int i = 1; i < heap.size(); ++i) {
            if (heap.Cells.get((int)i).getPos().y <= heap.Cells.get((int)max).getPos().y) continue;
            max = i;
        }
        return heap.Cells.get(max);
    }

    private Cell getCellMaxX(Heap heap) {
        if (heap.size() == 0) {
            return null;
        }
        int max = 0;
        for (int i = 1; i < heap.size(); ++i) {
            if (heap.Cells.get((int)i).getPos().x <= heap.Cells.get((int)max).getPos().x) continue;
            max = i;
        }
        return heap.Cells.get(max);
    }

    public int minY() {
        if (this.size() == 0) {
            return 0;
        }
        int min = ((Cell)this.Cells.get((int)0)).getPos().y;
        for (int i = 1; i < this.size(); ++i) {
            if (((Cell)this.Cells.get((int)i)).getPos().y >= min) continue;
            min = ((Cell)this.Cells.get((int)i)).getPos().y;
        }
        return min;
    }

    public int maxY() {
        if (this.size() == 0) {
            return 0;
        }
        int max = ((Cell)this.Cells.get((int)0)).getPos().y;
        for (int i = 1; i < this.size(); ++i) {
            if (((Cell)this.Cells.get((int)i)).getPos().y <= max) continue;
            max = ((Cell)this.Cells.get((int)i)).getPos().y;
        }
        return max;
    }

    public int minX() {
        if (this.size() == 0) {
            return 0;
        }
        int min = ((Cell)this.Cells.get((int)0)).getPos().x;
        for (int i = 1; i < this.size(); ++i) {
            if (((Cell)this.Cells.get((int)i)).getPos().x >= min) continue;
            min = ((Cell)this.Cells.get((int)i)).getPos().x;
        }
        return min;
    }

    public int maxX() {
        if (this.size() == 0) {
            return 0;
        }
        int max = ((Cell)this.Cells.get((int)0)).getPos().x;
        for (int i = 1; i < this.size(); ++i) {
            if (((Cell)this.Cells.get((int)i)).getPos().x <= max) continue;
            max = ((Cell)this.Cells.get((int)i)).getPos().x;
        }
        return max;
    }

    public FigureHeap clone() throws CloneNotSupportedException {
        FigureHeap temp = new FigureHeap();
        for (Cell Cell1 : this.Cells) {
            temp.add(Cell1.clone());
        }
        return temp;
    }
}

