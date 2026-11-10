package universityEducation.dataStructures.BinarySearchTree;
import java.util.*;

public class BinarySearchTree<K extends Comparable<K>, V> implements Iterable<K>{
    private class Node {
        K key;
        V value;

        Node left;
        Node right;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node root;
    private int size;


    public V get(K key){
        Node current = this.root;

        while (current != null) {
            int compareResult = key.compareTo(current.key);

            if (compareResult < 0) {
                current = current.left;
            }

            else {
                if (compareResult > 0) {
                    current = current.right;
                }

                else {
                    return current.value;
                }
            }
        }

        return null;
    }


    public void put(K key, V value) {
        if (this.root == null) {
            this.root = new Node(key, value);
            this.size++;
            return;
        }

        Node current = this.root;
        Node parent = null;

        while (current != null) {
            parent = current;

            int compareResult = key.compareTo(current.key);

            if (compareResult < 0) {
                current = current.left;
            }

            else{
                if (compareResult > 0) {
                    current = current.right;
                }

                else {
                    current.value = value;
                    return;
                }
            }
        }

        Node newNode = new Node(key, value);

        if (key.compareTo(parent.key) < 0) {
            parent.left = newNode;
            this.size++;
            return;
        }

        parent.right = newNode;
        this.size++;
    }

    public void putAtRoot(K key, V value) {
        this.root = this.putAtRoot(this.root, key, value);
    }

    private Node putAtRoot(Node node, K key, V value) {
        if (node == null) {
            this.size++;
            return new Node(key, value);
        }

        int compareResult = key.compareTo(node.key);

        if (compareResult < 0) {
            node.left = this.putAtRoot(node.left, key, value);
            return this.rotateRight(node);
        }

        else {
            if (compareResult > 0) {
                node.right = this.putAtRoot(node.right, key, value);
                return this.rotateLeft(node);
            }

            else {
                node.value = value;
                return node;
            }
        }
    }

    private Node rotateRight(Node node) {
        if (node == null || node.left == null) {
            return node;
        }

        Node newRoot = node.left;
        node.left = newRoot.right;
        newRoot.right = node;

        return newRoot;
    }

    private Node rotateLeft(Node node) {
        if (node == null || node.right == null) {
            return node;
        }

        Node newRoot = node.right;
        node.right = newRoot.left;
        newRoot.left = node;

        return newRoot;
    }

    public boolean contains(K key) {
        return this.contains(this.root, key);
    }

    private boolean contains(Node node, K key) {
        if (node == null) {
            return false;
        }

        int compareResult = key.compareTo(node.key);

        if (compareResult < 0) {
            this.contains(node.left, key);
        }

        else {
            if (compareResult > 0) {
                this.contains(node.right, key);
            }

        }

        return true;
    }


    public K min() {
        if (this.root == null) {
            return null;
        }

        Node current = this.root;

        while (current.left != null) {
            current = current.left;
        }

        return current.key;
    }

    public K max() {
        if (this.root == null) {
            return null;
        }

        Node current = this.root;

        while (current.right != null) {
            current = current.right;
        }

        return current.key;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public V remove(K key) {
        Node current = this.root;
        Node parent = null;

        if (current == null) {
            return null;
        }

        while (current != null) {
            int compareResult = key.compareTo(current.key);

            if (compareResult < 0) {
                parent = current;
                current = current.left;
            }

            else {
                if (compareResult > 0) {
                    parent = current;
                    current = current.right;
                }

                else {
                    break;
                }
            }
        }

        V removedValue = current.value;


        if (current.left == null || current.right == null) {
            Node child = (current.left != null) ? current.left : current.right;

            if (parent == null) {
                this.root = child;
            }

            else {
                if (parent.left == current) {
                    parent.left = child;
                }

                else {
                    parent.right = child;
                }
            }
        }

        else {
            Node successorParent = current;
            Node successor = current.right;

            while (successor.left != null) {
                successorParent = successor.left;
                successor = successor.left;
            }

            current.key = successor.key;
            current.value = successor.value;

            if (successorParent.left == successor) {
                successorParent.left = successor.right;
            }

            else {
                successorParent.right = successor.right;
            }
        }

        this.size--;
        return removedValue;
    }


    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {

            private final Deque<Node> stack = new ArrayDeque<>();

            {
                this.pushLeft(root);
            }

            private void pushLeft(Node node) {
                while (node != null) {
                    this.stack.push(node);
                    node = node.left;
                }
            }

            @Override
            public boolean hasNext() {
                return !this.stack.isEmpty();
            }

            @Override
            public K next() {
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }

                Node node = this.stack.pop();

                this.pushLeft(node.right);

                return node.key;
            }
        };
    }
}
