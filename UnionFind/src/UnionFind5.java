/**
 * @author zhangboqing
 * @date 2019/11/30
 * 路径压缩优化
 */
public class UnionFind5 implements UF {

    int[] parent;
    int[] rank; //树的高度

    public UnionFind5(int size) {
        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 1;
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
        if (rank[pRoot] < rank[qRoot]) {
            parent[pRoot] = qRoot;
        } else if (rank[pRoot] > rank[qRoot]){
            parent[qRoot] = pRoot;
        } else {
            // 相等时
            parent[pRoot] = qRoot;
            rank[qRoot] += 1;
        }
    }

    // O(h)
    public int find(int p) {
        if (p < 0 || p > parent.length) {
            throw new IllegalArgumentException("p is out of bound");
        }
        while (p != parent[p]) {
            // 路径压缩，注意这里不对rank进行维护，出于性能的考虑不影响结果
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return parent[p];
    }
}
