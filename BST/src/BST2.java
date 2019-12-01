import java.util.LinkedList;
import java.util.Queue;

/**
 * @author zhangboqing
 * @date 2019-11-23
 */
public class BST2<K extends Comparable<K>,V> {

    private class Node {
        public K key;
        public V value;
        private Node left, right;

        public Node(K key,V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BST2() {
        root = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(K key,V value) {
        root = add(root, key,value);
    }

    private Node add(Node node, K key,V value) {
        if (node == null) {
            node = new Node(key,value);
            size++;
            return node;
        }

        if (node.key.compareTo(key) > 0) {
            node.left = add(node.left, key,value);
        } else {
            node.right = add(node.right, key,value);
        }

        return node;
    }

    public boolean contains(K key) {
        return contains(root, key);
    }

    private boolean contains(Node node, K key) {
        if (node == null) {
            return false;
        }

        if (node.key.equals(key)) {
            return true;
        }
        if (node.key.compareTo(key) > 0) {
            return contains(node.left, key);
        }
        if (node.key.compareTo(key) < 0) {
            return contains(node.right, key);
        }

        return false;
    }

    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(Node node) {
        if (node == null) {
            return;
        }

        System.out.println(node.key);
        preOrder(node.left);
        preOrder(node.right);
    }

    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node node) {
        if (node == null) {
            return;
        }

        inOrder(node.left);
        System.out.println(node.key);
        inOrder(node.right);
    }

    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(Node node) {
        if (node == null) {
            return;
        }

        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.key);
    }

    public void levelOrder() {
        if (root == null) {
            return;
        }

        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);
        if (!queue.isEmpty()) {
            Node node = queue.remove();
            System.out.println(node.key);
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }

    public K mininum() {
        if (size == 0) {
            throw new IllegalArgumentException("BST is empty");
        }
        return mininum(root).key;
    }

    private Node mininum(Node node) {
        if (node.left == null) {
            return node;
        }
        return mininum(node.left);
    }

    public K maxinum() {
        if (size == 0) {
            throw new IllegalArgumentException("BST is empty");
        }
        return maxinum(root).key;
    }

    private Node maxinum(Node node) {
        if (node.left == null) {
            return node;
        }
        return maxinum(node.left);
    }

    public K removeMin() {
        K ret = mininum();
        root = removeMin(root);
        return ret;
    }

    private Node removeMin(Node node) {
        if (node.left == null) {
            Node right = node.right;
            node.right = null;
            size--;
            return right;
        }
        node.left = removeMin(node.left);
        return node;
    }

    public K removeMax() {
        K ret = maxinum();
        root = removeMax(root);
        return ret;
    }

    private Node removeMax(Node node) {
        if (node.right == null) {
            Node left = node.left;
            node.left = null;
            size--;
            return left;
        }
        node.right = removeMin(node.right);
        return node;
    }

    public void remove(K key) {
        root = remove(root, key);
    }

    private Node remove(Node node, K key) {
        if (node == null) {
            return null;
        }
        if (node.key.compareTo(key) > 0) {
            remove(node.left, key);
        } else if (node.key.compareTo(key) < 0) {
            remove(node.right, key);
        } else {
            if (node.left == null) {
                Node right = node.right;
                node.right = null;
                size--;
                return right;
            }
            if (node.right == null) {
                Node left = node.left;
                node.left = null;
                size--;
                return left;
            }

            Node minNode = removeMin(node.right);
            minNode.right = node.right;
            minNode.left = node.left;
            node.left = null;
            node.right = null;
            return minNode;
        }
        return node;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        generateRSTString(root, 0, res);
        return res.toString();
    }

    private void generateRSTString(Node node, int depth, StringBuilder res) {
        if (node == null) {
            res.append(generateDepthString(depth) + "null" + "\n");
        }
        res.append(generateDepthString(depth) + node.key + "\n");
        generateRSTString(node.left, depth + 1, res);
        generateRSTString(node.right, depth + 1, res);

    }

    private String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            res.append("--");
        }
        return res.toString();
    }

}
