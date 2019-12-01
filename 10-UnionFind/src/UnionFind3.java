/**
 * @author zhangboqing
 * @date 2019/11/30
 */
public class UnionFind3 implements UF {

    int[] parent;
    int[] sz;   // 树的节点数

    public UnionFind3(int size) {
        parent = new int[size];
        sz = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            sz[i] = 1;
        }
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    // O(h),h为树的高度
    @Override
    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            return;
        }
        if (sz[pRoot] < sz[qRoot]) {
            parent[pRoot] = qRoot;
            sz[qRoot] += 1;
        } else {
            parent[qRoot] = pRoot;
            sz[pRoot] += 1;
        }
    }

    // O(h)
    public int find(int p) {
        if (p < 0 || p > parent.length) {
            throw new IllegalArgumentException("p is out of bound");
        }
        while (p != parent[p]) {
            p = parent[p];
        }
        return parent[p];
    }
}
