/**
 * @author zhangboqing
 * @date 2019/11/30
 */
public interface UF {
    int getSize();
    boolean isConnected(int p,int q);
    void  union(int p,int q);
}
