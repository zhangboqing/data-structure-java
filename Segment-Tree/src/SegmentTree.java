/**
 * @author zhangboqing
 * @date 2019/11/30
 */
public class SegmentTree<E> {

    E[] tree;
    E[] data;
    Merger<E> merger;

    public SegmentTree(E[] arr, Merger<E> merger) {
        data = (E[]) new Object[arr.length];
        for (int i = 0; i < data.length; i++) {
            data[i] = arr[i];
        }
        this.merger = merger;

        tree = (E[]) new Object[4 * arr.length];
        buildSegmentTree(0, 0, data.length - 1);
    }

    // 在treeIndex的位置创建表示区间[l...r]的线段树
    public void buildSegmentTree(int index, int l, int r) {
        if (l == r) {
            tree[index] = data[l];
            return;
        }

        int leftIndex = leftChild(index);
        int rightIndex = rightChild(index);

        int mid = l + (r - l) / 2;
        buildSegmentTree(leftIndex, l, mid);
        buildSegmentTree(rightIndex, mid + 1, r);

        tree[index] = merger.mere(tree[leftIndex], tree[rightIndex]);
    }

    public int getSize() {
        return data.length;
    }

    public E get(int index) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("Index is illegal.");
        }
        return data[index];
    }

    private int leftChild(int index) {
        return index * 2 + 1;
    }

    private int rightChild(int index) {
        return index * 2 + 2;
    }

    public E query(int queryLeft, int queryRight) {
        if (queryLeft < 0 || queryLeft >= data.length ||
                queryRight < 0 || queryRight >= data.length || queryLeft > queryRight) {
            throw new IllegalArgumentException("Index is illegal.");
        }

        return query(0, 0, data.length - 1, queryLeft, queryRight);
    }

    private E query(int index, int l, int r, int queryLeft, int queryRight) {
        if (queryLeft == l && queryRight == r) {
            return tree[index];
        }
        int mid = l + (r - l) / 2;
        if (queryLeft > mid) {
            return query(rightChild(index), mid + 1, r, queryLeft, queryRight);
        }
        if (queryRight < mid + 1) {
            return query(leftChild(index), l, mid + 1, queryLeft, queryRight);
        }

        E leftResult = query(leftChild(index), queryLeft, mid, queryLeft, mid);
        E rightResult = query(rightChild(index), mid + 1, queryRight, mid + 1, queryRight);
        return merger.mere(leftResult, rightResult);
    }

    public void set(int index, E e) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("Index is illegal");
        }
        set(0, 0, data.length - 1, index, e);
    }

    private void set(int treeIndex, int l, int r, int index, E e) {
        if (l == r) {
            tree[treeIndex] = e;
            return;
        }

        int mid = l + (r - l) / 2;
        if (index > mid) {
            set(rightChild(treeIndex),mid+1,r,index,e);
        } else  {
            set(leftChild(treeIndex),l,mid,index,e);
        }

        tree[treeIndex] = merger.mere(tree[leftChild(treeIndex)],tree[rightChild(treeIndex)]);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append('[');
        for (int i = 0; i < tree.length; i++) {
            if (tree[i] != null) {
                res.append(tree[i]);
            } else {
                res.append("null");
            }

            if (i != tree.length - 1) {
                res.append(", ");
            }
        }
        res.append(']');
        return res.toString();
    }
}
