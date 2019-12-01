import java.util.LinkedList;
import java.util.Queue;

/**
 * @author zhangboqing
 * @date 2019-11-23
 */
public class BST<E extends Comparable<E>> {

    private class Node {
        public E e;
        private Node left, right;

        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BST() {
        root = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(E e) {
        root = add(root, e);
    }

    private Node add(Node node, E e) {
        if (node == null) {
            node = new Node(e);
            size++;
            return node;
        }

        if (node.e.compareTo(e) > 0) {
            node.left = add(node.left, e);
        } else {
            node.right = add(node.right, e);
        }

        return node;
    }

    public boolean contains(E e) {
        return contains(root, e);
    }

    private boolean contains(Node node, E e) {
        if (node == null) {
            return false;
        }

        if (node.e.equals(e)) {
            return true;
        }
        if (node.e.compareTo(e) > 0) {
            return contains(node.left, e);
        }
        if (node.e.compareTo(e) < 0) {
            return contains(node.right, e);
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

        System.out.println(node.e);
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
        System.out.println(node.e);
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
        System.out.println(node.e);
    }

    public void levelOrder() {
        if (root == null) {
            return;
        }

        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);
        if (!queue.isEmpty()) {
            Node node = queue.remove();
            System.out.println(node.e);
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }

    public E mininum() {
        if (size == 0) {
            throw new IllegalArgumentException("BST is empty");
        }
        return mininum(root).e;
    }

    private Node mininum(Node node) {
        if (node.left == null) {
            return node;
        }
        return mininum(node.left);
    }

    public E maxinum() {
        if (size == 0) {
            throw new IllegalArgumentException("BST is empty");
        }
        return maxinum(root).e;
    }

    private Node maxinum(Node node) {
        if (node.left == null) {
            return node;
        }
        return maxinum(node.left);
    }

    public E removeMin() {
        E ret = mininum();
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

    public E removeMax() {
        E ret = maxinum();
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

    public void remove(E e) {
        root = remove(root, e);
    }

    private Node remove(Node node, E e) {
        if (node == null) {
            return null;
        }
        if (node.e.compareTo(e) > 0) {
            remove(node.left, e);
        } else if (node.e.compareTo(e) < 0) {
            remove(node.right, e);
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
        res.append(generateDepthString(depth) + node.e + "\n");
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
