package Tree;

/**
 * @author Luis
 */
public class Node<Key, Value> {

    private int Height;
    private Key key;
    private Value value;
    private Node<Key, Value> father;
    private Node<Key, Value> leftChildren;
    private Node<Key, Value> rightChildren;

    public Node(Key key, Value value) {
        this.key = key;
        this.value = value;
    }

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

    public void AddFather(Node<Key, Value> father) {
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

    public int getBalanceFactor() {
        if (this.leftChildren == null & this.rightChildren == null) {
            return 0;
        } else if (this.leftChildren != null && this.rightChildren == null) {
            return this.leftChildren.getHeight() - 0;
        } else if (this.rightChildren != null && this.leftChildren == null) {
            return 0 - this.rightChildren.getHeight();
        } else {
            return this.leftChildren.getHeight() - this.rightChildren.getHeight();
        }
    }

    public int getHeight() {
        return this.Height;
    }

    public int CalculateHeight() {
        this.Height = 0;

        if (this.leftChildren == null && this.rightChildren == null) {
            return this.Height = 1;
        } else if (this.leftChildren != null && this.rightChildren == null) {
            return this.Height = 1 + this.leftChildren.CalculateHeight();
        } else if (this.rightChildren != null && this.leftChildren == null) {
            return this.Height = 1 + this.rightChildren.CalculateHeight();
        } else {
            return this.Height = 1 + Math.max(this.leftChildren.CalculateHeight(), this.rightChildren.CalculateHeight());
        }
    }
}
