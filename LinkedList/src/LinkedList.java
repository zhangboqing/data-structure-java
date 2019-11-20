/**
 * @author zhangboqing
 * @date 2019-11-20
 */
public class LinkedList<E> {

    private class Node {
        public E e;
        public Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return e != null ? e.toString() : "null";
        }
    }


    private Node dummyHead;
    private int size;

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public LinkedList() {
        dummyHead = new Node();
        size = 0;
    }

    public void add(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("index param fail");
        }

        Node pre = dummyHead;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }

        pre.next = new Node(e, pre.next);
        size++;
    }

    public void addFirst(E e) {
        add(0, e);
    }

    public void addLast(E e) {
        add(size - 1, e);
    }


    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("index param fail");
        }

        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }

        return cur.e;
    }

    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(size - 1);
    }

    public void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("index param fail");
        }

        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        cur.e = e;
    }


    public boolean contants(E e) {

//        Node cur = dummyHead.next;
//        while (cur != null) {
//            if (cur.e.equals(e)) {
//                return true;
//            }
//            cur = cur.next;
//        }

        for (Node cur = dummyHead.next; cur != null; cur = cur.next) {
            if (cur.e.equals(e)) {
                return true;
            }
        }

        return false;
    }

    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("index param fail");
        }

        Node pre = dummyHead;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }

        Node retNode = pre.next;
        pre.next = retNode.next;
        retNode.next = null;
        size--;
        return retNode.e;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node cur = dummyHead.next;
        while (cur != null) {
            sb.append(cur.e).append("->");
            cur = cur.next;
        }
        sb.append("NULL");

        return sb.toString();
    }
}
