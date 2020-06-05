package Tree;

/**
 * @author Luis
 */
public class AvlTree<Key extends Comparable, Value> implements IAvlTree<Key, Value> {

    private Node<Key, Value> root;

    @Override
    public void Add(Node<Key, Value> node) {
        if (root == null) {
            root = node;
        } else {
            add(this.root, node);
            CalculateBalanceFactor(node);
        }
    }

    private void add(Node<Key, Value> _root, Node<Key, Value> node) {
        if (node.getKey().compareTo(_root.getKey()) > 0) {
            if (_root.getRightChildren() == null) {
                _root.AddRightChildren(node);
                return;
            } else {
                add(_root.getRightChildren(), node);
            }
        } else if (node.getKey().compareTo(_root.getKey()) < 0) {
            if (_root.getLeftChildren() == null) {
                _root.AddLeftChildren(node);
                return;
            } else {
                add(_root.getLeftChildren(), node);
            }
        }
    }

    private void CalculateBalanceFactor(Node<Key, Value> node) {

        Node<Key, Value> father = node.getFather();

        if (father.getLeftChildren() != null && father.getRightChildren() != null) {
            if ((father.getLeftChildren().getKey().compareTo(node.getKey()) == 0)) {
                father.IncrementBalance();
            } else if (father.getRightChildren().getKey().compareTo(node.getKey()) == 0) {
                father.DecrementBalance();
            }
        } else {
            DifferentLevels(node);
        }
    }

    private void DifferentLevels(Node<Key, Value> node) {

        if (node.getFather() != null) {
            Node<Key, Value> father = node.getFather();

            if ((father.getLeftChildren() != null) && (father.getLeftChildren().getKey().compareTo(node.getKey()) == 0)) {
                father.IncrementBalance();
            } else if ((father.getRightChildren() != null) && (father.getRightChildren().getKey().compareTo(node.getKey()) == 0)) {
                father.DecrementBalance();
            }

            DifferentLevels(father);
        }
    }

    private void Rotate(Node<Key, Value> pivot) {
        /*
            Rotação simples para a direita
            Fator de balanceamento do pivô é 2 e do filho esquerdo é 1
         */
        if (pivot.getBalanceFactor() == 2 && pivot.getLeftChildren().getBalanceFactor() == 1) {
            
        }/*
            Rotação simples para a esquerda
            Fator de balanceamento do pivô é -2 e do filho direito é -1
         */ else if (pivot.getBalanceFactor() == -2 && pivot.getRightChildren().getBalanceFactor() == -1) {

        }/*
            Rotação dupla para a direita
            Fator de balanceamento do pivô é 2 e do filho esquerdo é -1
         */ else if (pivot.getBalanceFactor() == 2 && pivot.getLeftChildren().getBalanceFactor() == -1) {

        }/*
            Rotação dupla para a esquerda
            Fator de balanceamento do pivô é -2 e do filho da direito é 1
         */ else if (pivot.getBalanceFactor() == -2 && pivot.getRightChildren().getBalanceFactor() == -1) {

        }
    }

    private void RotateLeft(Node<Key, Value> pivot) {

        Node<Key, Value> father = pivot.getFather();
        Node<Key, Value> rightChildren = pivot.getRightChildren();

        if ((father.getLeftChildren() != null) && (father.getLeftChildren().getKey().compareTo(pivot.getKey()) == 0)) {
            father.AddLeftChildren(rightChildren);
        } else {
            father.AddRightChildren(rightChildren);
        }

        if (rightChildren.getLeftChildren() != null) {
            Node<Key, Value> lefttChildren = rightChildren.getLeftChildren();
            pivot.AddRightChildren(lefttChildren);
        }

        rightChildren.AddLeftChildren(pivot);
    }

    private void RotateRight(Node<Key, Value> pivot) {

        Node<Key, Value> father = pivot.getFather();
        Node<Key, Value> leftChildren = pivot.getLeftChildren();

        if ((father.getLeftChildren() != null) && (father.getLeftChildren().getKey().compareTo(pivot.getKey()) == 0)) {
            father.AddLeftChildren(leftChildren);
        } else {
            father.AddRightChildren(leftChildren);
        }

        if (leftChildren.getRightChildren() != null) {
            Node<Key, Value> rightChildren = leftChildren.getRightChildren();
            pivot.AddLeftChildren(rightChildren);
        }

        leftChildren.AddRightChildren(pivot);
    }

    private Node<Key, Value> GetPivot(Node<Key, Value> node) {

        if (node != null) {
            if (node.getBalanceFactor() == 2 || node.getBalanceFactor() == -2) {
                return node;
            } else {
                GetPivot(node.getFather());
            }
        }
        return null;
    }

    @Override
    public void Delete(Key key) {
        Node<Key, Value> node = GetByKey(key);

        if (node != null && node.getKey().compareTo(key) == 0) {
            // 1- Nó folha
            if (node.getLeftChildren() == null && node.getRightChildren() == null) {
                if (node.getKey().compareTo(this.root.getKey()) == 0) {
                    this.root = null;
                } else if (node.getFather().getKey().compareTo(node.getKey()) > 0) {
                    node.getFather().AddLeftChildren(null);
                } else {
                    node.getFather().AddRightChildren(null);
                }
            } // 2- Nó possui uma subárvore
            // 2.1- Na direita
            else if (node.getLeftChildren() == null && node.getRightChildren() != null) {
                if (node.getKey().compareTo(this.root.getKey()) == 0) {
                    node.getRightChildren().AddFather(null);
                    this.root = node.getRightChildren();
                } else if (node.getFather().getKey().compareTo(node.getKey()) > 0) {
                    node.getFather().AddLeftChildren(node.getRightChildren());
                } else {
                    node.getFather().AddRightChildren(node.getRightChildren());
                }
            } // 2.2- Na esquerda
            else if (node.getLeftChildren() != null && node.getRightChildren() == null) {
                if (node.getKey().compareTo(this.root.getKey()) == 0) {
                    node.getLeftChildren().AddFather(null);
                    this.root = node.getLeftChildren();
                } else if (node.getFather().getKey().compareTo(node.getKey()) > 0) {
                    node.getFather().AddLeftChildren(node.getLeftChildren());
                } else {
                    node.getFather().AddRightChildren(node.getLeftChildren());
                }
            } // 3- Nó possui duas árvores
            else {
                // Obter o sucessor:
                Node<Key, Value> successor = GetSuccessor(node);
                // 3.1- Sucessor é folha
                if (successor.getRightChildren() == null) {
                    // Removendo a referência que o pai do sucessor tem para ele
                    if (successor.getFather().getKey().compareTo(successor.getKey()) > 0) {
                        successor.getFather().AddLeftChildren(null);
                    } else {
                        successor.getFather().AddRightChildren(null);
                    }
                    successor.AddLeftChildren(node.getLeftChildren());
                    successor.AddRightChildren(node.getRightChildren());

                    if (node.getFather() != null) {
                        // Adicionando a referência do pai do nó removido para o sucessor
                        if (node.getFather().getKey().compareTo(node.getKey()) > 0) {
                            node.getFather().AddLeftChildren(successor);
                        } else {
                            node.getFather().AddRightChildren(successor);
                        }
                    } else {
                        successor.AddFather(null);
                    }
                } // 3.2- Sucessor possui subárvore à direita
                else {
                    // 3.2.1 - Pai do sucessor é o nó que será removido
                    if (successor.getFather().getKey().compareTo(node.getKey()) == 0) {
                        successor.AddLeftChildren(node.getLeftChildren());
                    } // 3.2.2 - Se não
                    else {
                        successor.getFather().AddLeftChildren(successor.getRightChildren());
                        successor.AddLeftChildren(node.getLeftChildren());
                        successor.AddRightChildren(node.getRightChildren());

                    }
                    if (node.getFather() != null) {
                        if (node.getFather().getKey().compareTo(node.getKey()) > 0) {
                            node.getFather().AddLeftChildren(successor);
                        } else {
                            node.getFather().AddRightChildren(successor);
                        }
                    } else {
                        successor.AddFather(null);
                    }
                }

                if (node.getKey().compareTo(this.root.getKey()) == 0) {
                    this.root = successor;
                }
            }
        }
    }

    private Node<Key, Value> GetSuccessor(Node<Key, Value> node) {

        node = node.getRightChildren();

        while (node.getLeftChildren() != null) {
            node = node.getLeftChildren();
        }

        return node;
    }

    @Override
    public Node<Key, Value> GetByKey(Key key) {
        return getByKey(this.root, key);
    }

    private Node<Key, Value> getByKey(Node<Key, Value> _root, Key key) {

        if (key.compareTo(_root.getKey()) > 0) {
            getByKey(_root.getRightChildren(), key);
        }
        if (key.compareTo(_root.getKey()) < 0) {
            getByKey(_root.getLeftChildren(), key);
        }
        if (key.compareTo(_root.getKey()) == 0) {
            return _root;
        } else {
            return null;
        }
    }

    @Override
    public void CleanTree() {
        this.root = null;
    }

}
