import java.util.ArrayList;

/**
 * @author zhangboqing
 * @date 2019/12/1
 */
public class AVLTree<K extends Comparable<K>, V> {

    private class Node {
        public K key;
        public V value;
        public Node left, right;
        public int height;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            this.height = 1;
        }
    }

    private Node root;
    private int size;

    public AVLTree() {
        root = null;
        size = 0;
    }

    public boolean isBST() {
        ArrayList<K> array = new ArrayList<>();
        inOrder(root, array);

        for (int i = 0; i < array.size() - 1; i++) {
            if (array.get(i).compareTo(array.get(i + 1)) > 0) {
                return false;
            }
        }
        return true;
    }

    private void inOrder(Node root, ArrayList<K> array) {
        if (root == null) {
            return;
        }
        inOrder(root.left, array);
        array.add(root.key);
        inOrder(root.right, array);
    }


    public boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(Node node) {
        if (node == null) {
            return true;
        }
        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1) {
            return false;
        }
        return isBalanced(node.left) && isBalanced(node.right);
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    public int getBalanceFactor(Node node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    // 对节点y进行向右旋转操作，返回旋转后新的根节点x
    //        y                              x
    //       / \                           /   \
    //      x   T4     向右旋转 (y)        z     y
    //     / \       - - - - - - - ->    / \   / \
    //    z   T3                       T1  T2 T3 T4
    //   / \
    // T1   T2
    private Node rightRotate(Node node) {
        Node left = node.left;
        Node lRight = left.right;
        node.left = lRight;
        left.right = node;

        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        left.height = Math.max(getHeight(left.left), getHeight(left.right)) + 1;
        return left;
    }

    private Node leftRotate(Node node) {
        Node right = node.right;
        Node rLeft = right.left;
        right.left = node;
        node.right = rLeft;

        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        right.height = Math.max(getHeight(right.left), getHeight(right.right)) + 1;
        return right;
    }

    public void add(K key, V value) {
        root = add(root, key, value);
    }

    private Node add(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }

        if (key.compareTo(node.left.key) < 0) {
            node.left = add(node.left, key, value);
        } else if (key.compareTo(node.right.key) > 0) {
            node.right = add(node.right, key, value);
        } else {
            node.value = value;
        }

        int height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        node.height = height;

        int balanceFactor = getBalanceFactor(node);
        // LL
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0) {
            rightRotate(node);
        }

        // RR
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0) {
            leftRotate(node);
        }
        // LR
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            node.left = leftRotate(node.left);
            rightRotate(node);
        }
        // RL
        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
            node.right = rightRotate(node.right);
            leftRotate(node);
        }


        return node;
    }

    private Node getNode(Node node, K key) {
        if (node == null || key == null) {
            return null;
        }

        if (node.key.compareTo(key) > 0) {
            return getNode(node.left, key);
        } else if (node.key.compareTo(key) < 0) {
            return getNode(node.right, key);
        }

        return node;
    }

    public boolean contains(K key) {
        return getNode(root, key) == null;
    }

    public V get(K key) {
        Node node = getNode(root, key);
        if (node == null) {
            return null;
        }
        return node.value;
    }

    public void set(K key, V newValue) {
        Node node = getNode(root, key);
        if (node != null) {
            node.value = newValue;
        }
    }

    private Node minimun(Node node) {
        if (node == null) {
            return null;
        }
        if (node.left == null) {
            return node;
        }

        return minimun(node.left);
    }

    private Node removeMin(Node node) {
        if (node == null) {
            return null;
        }

        if (node.left == null) {
            Node right = node.right;
            node.right = null;
            size--;
            return right;
        }

        node.left = removeMin(node.left);
        return node;
    }

    public V remove(K key) {
        Node node = getNode(root, key);
        if (node == null) {
            return null;
        }
        root = remove(root, key);
        return node.value;
    }

    private Node remove(Node node, K key) {
        if (node == null || key == null) {
            return null;
        }
        if (node.key.compareTo(key) < 0) {
            node.right = remove(node.right, key);
        } else if (node.key.compareTo(key) > 0) {
            node.left = remove(node.left, key);
        } else {

            if (node.left == null) {
                Node right = node.right;
                node.right = null;
                size--;
                node = right;
            } else if (node.right == null) {
                Node left = node.left;
                node.left = null;
                size--;
                node = left;
            } else {
                Node minimun = minimun(node.right);
                removeMin(node.right);
                minimun.left = node.left;
                minimun.right = node.right;
                node.left = null;
                node.right = null;
                node = minimun;
            }

        }

        if (node == null) {
            return null;
        }

        int height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        node.height = height;

        int balanceFactor = getBalanceFactor(node);
        // LL
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0) {
            rightRotate(node);
        }

        // RR
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0) {
            leftRotate(node);
        }
        // LR
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            node.left = leftRotate(node.left);
            rightRotate(node);
        }
        // RL
        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
            node.right = rightRotate(node.right);
            leftRotate(node);
        }

        return node;
    }
}

