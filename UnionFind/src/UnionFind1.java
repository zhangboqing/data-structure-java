/**
 * @author zhangboqing
 * @date 2019/11/30
 */
public class UnionFind1 implements UF {

    int[] id;

    public UnionFind1(int size) {
        id = new int[size];
        for (int i = 0; i < size; i++) {
            id[i] = i;
        }
    }

    @Override
    public int getSize() {
        return id.length;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    // O(n)
    @Override
    public void union(int p, int q) {
        int pId = find(p);
        int qId = find(q);

        if (pId == qId) {
            return;
        }

        for (int i = 0; i < id.length; i++) {
            if (id[i] == qId) {
                id[i] = pId;
            }
        }
    }

    // O(1)
    public int find(int p) {
        if (p < 0 || p > id.length) {
            throw new IllegalArgumentException("p is out of bound");
        }

        return id[p];
    }
}
