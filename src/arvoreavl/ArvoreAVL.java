package arvoreavl;

import Tree.AvlTree;
import Tree.Node;

/**
 * @author Luis
 */
public class ArvoreAVL {

    public static void main(String[] args) {

        Node<Integer, Integer> nodes[] = new Node[13];
        nodes[0] = new Node<>(48, 48);
        nodes[1] = new Node<>(33, 33);
        nodes[2] = new Node<>(50, 50);
        nodes[3] = new Node<>(59, 59);
        nodes[4] = new Node<>(55, 55);
        nodes[5] = new Node<>(10, 10);
        nodes[6] = new Node<>(49, 49);
        nodes[7] = new Node<>(35, 35);
        nodes[8] = new Node<>(7, 7);
        nodes[9] = new Node<>(23, 23);
        nodes[10] = new Node<>(51, 51);
        nodes[11] = new Node<>(53, 53);

        AvlTree tree = new AvlTree();
        tree.Add(nodes[0]);
        tree.Add(nodes[1]);
        tree.Add(nodes[2]);
        tree.Add(nodes[3]);
        tree.Add(nodes[4]);
        tree.Add(nodes[5]);
        tree.Add(nodes[6]);
        tree.Add(nodes[7]);
        tree.Add(nodes[8]);
        tree.Add(nodes[9]);
        tree.Add(nodes[10]);
        tree.Add(nodes[11]);
        
        
    }
}
