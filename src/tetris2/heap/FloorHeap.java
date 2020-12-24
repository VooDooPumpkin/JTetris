package tetris2.heap;

import tetris2.heap.Heap;

public class FloorHeap
extends Heap {
    public int numCellsWithY(int y) {
        return this.getHeapByY(y).size();
    }

    public void removeAllCellByY(int y) {
        this.remove(this.getHeapByY(y));
    }
}

