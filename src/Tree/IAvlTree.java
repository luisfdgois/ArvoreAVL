package Tree;

/**
 * @author Luis
 */
public interface IAvlTree<Key, Value> {

    public void Add(Node<Key, Value> node);

    public void Delete(Key key);

    public Node<Key, Value> GetByKey(Key key);

    public void CleanTree();
}
