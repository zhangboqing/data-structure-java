/**
 * @author zhangboqing
 * @date 2019-11-19
 */
public interface Stack<E> {
    void push(E e);
    E pop();
    E peek();
    int getSize();
    boolean isEmpty();
}
