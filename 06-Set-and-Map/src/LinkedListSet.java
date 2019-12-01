/**
 * @author zhangboqing
 * @date 2019/11/30
 */
public class LinkedListSet<E> implements Set<E> {

    private LinkedList<E> list;


    public LinkedListSet() {
        list = new LinkedList<>();
    }

    @Override
    public void add(E e) {
        if (!list.contants(e)) {
            list.addLast(e);
        }
    }

    @Override
    public void remove(E e) {
        list.removeElement(e);
    }

    @Override
    public boolean contains(E e) {
        return list.contants(e);
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }
}
