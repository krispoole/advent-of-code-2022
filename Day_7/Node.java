package Day_7;

import java.util.ArrayList;

public class Node<T> {

    private final T root;
    private Node<T> parent;
    private ArrayList<Node<T>> children;

    public Node(T root) {
        this.root = root;
        this.children = new ArrayList<>();
    }

    public Node<T> addChild(T child) {
        Node<T> childNode = new Node<>(child);
        childNode.setParent(this);
        this.children.add(childNode);
        return childNode;
    }

    public T getRoot() {
        return root;
    }

    public Node<T> getParent() {
        return parent;
    }

    public void setParent(Node<T> parent) {
        this.parent = parent;
    }

    public ArrayList<Node<T>> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Node<T>> children) {
        this.children = children;
    }

}
