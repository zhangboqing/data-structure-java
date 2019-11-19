/**
 * @author zhangboqing
 * @date 2019-11-19
 */
public interface Queue<E> {

    void enqueue(E e);
    E dequeue();
    E getFront();
    boolean isEmpty();
    int getSize();
}
