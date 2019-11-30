/**
 * @author zhangboqing
 * @date 2019/11/30
 */
public class LinkedListMap<K, V> implements Map<K, V> {

    private class Node {
        public K key;
        public V value;
        public Node next;

        public Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Node(K key, V value) {
            this(key, value, null);
        }

        public Node() {
            this(null, null, null);
        }

        @Override
        public String toString() {
            return (key == null ? "null" : key.toString()) + ":" + (value == null ? "null" : value.toString());
        }
    }

    private Node dummyHead;
    private int size;


    public LinkedListMap() {
        dummyHead = new Node();
        size = 0;
    }

    @Override
    public void add(K key, V value) {
        Node node = getNode(key);
        if (node != null) {
            node.value = value;
        } else {
            dummyHead.next = new Node(key, value, dummyHead.next);
        }
    }

    private Node getNode(K key) {
        Node cur = dummyHead.next;
        while (cur != null) {
            if (cur.key.equals(key)) {
                return cur;
            }
            cur = cur.next;
        }
        return null;
    }

    @Override
    public V remove(K key) {

        Node pre = dummyHead;
        while (pre.next != null) {
            if (pre.next.key.equals(key)) {
                break;
            }
            pre = pre.next;
        }

        if (pre != null) {
            Node cur = pre.next;
            pre.next = cur.next;
            cur.next = null;
            size--;
            return cur.value;
        }
        return null;
    }

    @Override
    public boolean contains(K key) {
        return getNode(key) != null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(key);
        return node == null ? null : node.value;
    }

    @Override
    public void set(K key, V newValue) {
        Node node = getNode(key);
        if (node == null) {
            throw new IllegalArgumentException(key + "dosen`t exist!");
        }
        node.value = newValue;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

}
