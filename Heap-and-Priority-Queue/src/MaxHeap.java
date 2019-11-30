/**
 * @author zhangboqing
 * @date 2019/11/30
 */
public class MaxHeap<E extends Comparable<E>> {

    private Array<E> data;

    public MaxHeap(int capacity) {
        data = new Array<>(capacity);
    }

    public MaxHeap() {
        data = new Array<>();
    }

    public int getSize() {
        return data.getSize();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    private int parent(int index) {
        if (index == 0) {
            throw new IllegalArgumentException("index-0 doesn`t have parent.");
        }
        return (index - 1) / 2;
    }

    private int leftChild(int index) {
        return index * 2 + 1;
    }

    private int rightChild(int index) {
        return index * 2 + 2;
    }

    public void add(E e) {
        data.addLast(e);
        siftUp(data.getSize() - 1);
    }

    private void siftUp(int k) {
        while (k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0) {
            data.swap(k, parent(k));
            k = parent(k);
        }
    }

    public E findMax() {
        if (data.isEmpty()) {
            return null;
        }
        return data.getFirst();
    }

    public E extractMax() {
        E max = findMax();
        if (max == null) {
            return max;
        }

        data.swap(0, data.getSize() - 1);
        data.removeLast();
        siftDown(0);
        return max;
    }

    private void siftDown(int k) {

        if (k >= data.getSize() - 1) {
            return;
        }

        int i = leftChild(k);
        if ((i+1) < data.getSize() -1 && data.get(i).compareTo(data.get(i+1)) < 0) {
            data.swap(k,i+1);
            siftDown(i+1);
            return;
        }

        data.swap(k,i);
        siftDown(i);
    }

    // 取出堆中的最大元素，并且替换成元素e
    public E replace(E e) {
        E max = findMax();
        if (max == null) {
            return max;
        }

        data.set(0,e);
        siftDown(0);
        return max;
    }
}
