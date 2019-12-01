import java.util.TreeMap;

/**
 * @author zhangboqing
 * @date 2019-11-25
 */
public class Trie {

    private class Node {
        public boolean isWorkd;
        public TreeMap<Character, Node> next;

        public Node(boolean isWorkd) {
            this.isWorkd = isWorkd;
            next = new TreeMap<>();
        }

        public Node() {
            this(false);
        }
    }

    public Node root;
    public int size;

    public Trie() {
        root = new Node();
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public void add(String word) {
        if (word == null) {
            return;
        }

        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null) {
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }

        if (!cur.isWorkd) {
            cur.isWorkd = true;
            size++;
        }
    }

    public boolean contains(String word) {
        if (word == null) {
            return false;
        }

        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
        }

        return cur.isWorkd;
    }

    public boolean isPrefix(String prefix) {
        if (prefix == null) {
            return false;
        }

        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
        }

        return true;
    }


}
