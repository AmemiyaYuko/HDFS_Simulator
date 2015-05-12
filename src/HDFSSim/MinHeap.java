package hdfssim;

/**
 * Created by Amemiya on 5/13/15.
 */
public class MinHeap {
    private int size;
    private double[] heap=new double[100000];


    public MinHeap() {
        heap[0] = Integer.MIN_VALUE;
    }

    private int leftChild(int pos) {
        return 2 * pos;
    }

    private int rightChild(int pos) {
        return 2 * pos + 1;
    }


    private int parent(int pos) {
        return pos / 2;
    }

    private boolean isLeaf(int pos) {
        return ((pos > size / 2) && (pos <= size));
    }

    private void swap(int pos1, int pos2) {
        double tmp;
        tmp = heap[pos1];
        heap[pos1] = heap[pos2];
        heap[pos2] = tmp;
    }

    public void insert(double elem) {
        size++;
        heap[size] = elem;
        int current = size;
        while (heap[current] < heap[parent(current)]) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    public void print() {
        int i;
        for (i = 1; i <= size; i++)
            System.out.print(heap[i] + " ");
        System.out.println();
    }

    public double removeRoot() {
        swap(1, size);
        size--;
        if (size != 0)
            pushDown(1);
        return heap[size + 1];
    }

    private void pushDown(int position) {
        int smallestChild;
        while (!isLeaf(position)) {
            smallestChild = leftChild(position);
            if ((smallestChild < size)
                    && (heap[smallestChild] > heap[smallestChild + 1]))
                smallestChild = smallestChild + 1;
            if (heap[position] <= heap[smallestChild])
                return;
            swap(position, smallestChild);
            position = smallestChild;
        }
    }

    public static void main(String args[])
    {
        MinHeap mh=new MinHeap();
        for (int i=0;i<15;i++){
            mh.insert(Math.random()*1000);
        }
        for (int i=0;i<15;i++){
            System.out.println(mh.removeRoot());
        }
    }
}