package Tree;

/**
 * @author Luis
 */
public class Node<Key, Value> {

    private int balanceFactor;
    private Key key;
    private Value value;
    private Node<Key, Value> father;
    private Node<Key, Value> leftChildren;
    private Node<Key, Value> rightChildren;

    public Key getKey() {
        return key;
    }

    public Node<Key, Value> getFather() {
        return father;
    }

    public Node<Key, Value> getLeftChildren() {
        return leftChildren;
    }

    public Node<Key, Value> getRightChildren() {
        return rightChildren;
    }
    
    public void AddFather(Node<Key, Value> father){
        this.father = father;
    }
    
    public void AddLeftChildren(Node<Key, Value> children) {
        if (children != null) {
            children.AddFather(this);
        }
        this.leftChildren = children;
    }

    public void AddRightChildren(Node<Key, Value> children) {
        if (children != null) {
            children.AddFather(this);
        }
        this.rightChildren = children;
    }
}