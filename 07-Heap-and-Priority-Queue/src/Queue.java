/**
 * @author zhangboqing
 * @date 2019/11/30
 */
 interface Queue<E> {
     int getSize();
     boolean isEmpty();
     void enqueue(E e);
     E dequeue();
     E getFront();
}
