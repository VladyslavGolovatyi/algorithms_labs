// Implementing Red-Black Tree delete in Java

class Node {
    int data;
    Node parent;
    Node left;
    Node right;
    int color;

    public Node(int data, Node parent, Node left, Node right, int color) {
        this.data = data;
        this.parent = parent;
        this.left = left;
        this.right = right;
        this.color = color;
    }

    public Node (){}
}

public class RedBlackTree {
    private Node root;
    private final Node NIL;

    public RedBlackTree() {
        NIL = new Node();
        NIL.color = 0;
        NIL.left = null;
        NIL.right = null;
        Node node32 = new Node(32, NIL, NIL, NIL,1);
        Node node50 = new Node(50, NIL, NIL, NIL,0);
        Node node35 = new Node(35, NIL,node32, NIL,0);
        Node node15 = new Node(15, NIL, NIL, NIL,0);
        Node node40 = new Node(40, NIL,node35,node50,1);
        root = new Node(30, NIL,node15,node40,0);
        node15.parent = root;
        node40.parent = root;
        node32.parent = node35;
        node50.parent = node40;
        node35.parent = node40;

    }

    // Balance the tree after deletion of a node
    private void fixAfterDelete(Node x) {
        Node s;
        while (x != root && x.color == 0) {
            if (x == x.parent.left) {
                s = x.parent.right;
                if (s.color == 1) {
                    s.color = 0;
                    x.parent.color = 1;
                    leftRotate(x.parent);
                    s = x.parent.right;
                }

                if (s.left.color == 0 && s.right.color == 0) {
                    s.color = 1;
                    x = x.parent;
                } else {
                    if (s.right.color == 0) {
                        s.left.color = 0;
                        s.color = 1;
                        rightRotate(s);
                        s = x.parent.right;
                    }

                    s.color = x.parent.color;
                    x.parent.color = 0;
                    s.right.color = 0;
                    leftRotate(x.parent);
                    x = root;
                }
            } else {
                s = x.parent.left;
                if (s.color == 1) {
                    s.color = 0;
                    x.parent.color = 1;
                    rightRotate(x.parent);
                    s = x.parent.left;
                }

                if (s.left.color == 0 && s.right.color == 0) {
                    s.color = 1;
                    x = x.parent;
                } else {
                    if (s.left.color == 0) {
                        s.right.color = 0;
                        s.color = 1;
                        leftRotate(s);
                        s = x.parent.left;
                    }

                    s.color = x.parent.color;
                    x.parent.color = 0;
                    s.left.color = 0;
                    rightRotate(x.parent);
                    x = root;
                }
            }
        }
        x.color = 0;
    }

    public void leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;
        if (y.left != NIL) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }

    public void rightRotate(Node x) {
        Node y = x.left;
        x.left = y.right;
        if (y.right != NIL) {
            y.right.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.right) {
            x.parent.right = y;
        } else {
            x.parent.left = y;
        }
        y.right = x;
        x.parent = y;
    }

    private void deleteNode(int key) {
        Node node = this.root;
        Node nodeToDelete = NIL;
        Node x, y;
        while (node != NIL) {
            if (node.data == key) {
                nodeToDelete = node;
            }
            if (node.data <= key) {
                node = node.right;
            } else {
                node = node.left;
            }
        }

        if (nodeToDelete == NIL) {
            System.out.println("Couldn't find key in the tree");
            return;
        }

        y = nodeToDelete;
        int yOriginalColor = y.color;
        if (nodeToDelete.left == NIL) {
            x = nodeToDelete.right;
            transplant(nodeToDelete, nodeToDelete.right);
        } else if (nodeToDelete.right == NIL) {
            x = nodeToDelete.left;
            transplant(nodeToDelete, nodeToDelete.left);
        } else {
            y = min(nodeToDelete.right);
            yOriginalColor = y.color;
            x = y.right;
            if (y.parent == nodeToDelete) {
                x.parent = y;
            } else {
                transplant(y, y.right);
                y.right = nodeToDelete.right;
                y.right.parent = y;
            }

            transplant(nodeToDelete, y);
            y.left = nodeToDelete.left;
            y.left.parent = y;
            y.color = nodeToDelete.color;
        }
        if (yOriginalColor == 0) {
            fixAfterDelete(x);
        }
    }

    private void transplant(Node u, Node v) {
        if (u.parent == null) {
            root = v;
        } else if (u == u.parent.left) {
            u.parent.left = v;
        } else {
            u.parent.right = v;
        }
        v.parent = u.parent;
    }

    public Node min(Node node) {
        while (node.left != NIL) {
            node = node.left;
        }
        return node;
    }

    public void printTree() {
        printTree(this.root, "", true);
    }

    private void printTree(Node root, String indent, boolean last) {
        if (root != NIL) {
            System.out.print(indent);
            if (last) {
                System.out.print("R----");
                indent += "   ";
            } else {
                System.out.print("L----");
                indent += "|  ";
            }

            String sColor = root.color == 1 ? "RED" : "BLACK";
            System.out.println(root.data + "(" + sColor + ")");
            printTree(root.left, indent, false);
            printTree(root.right, indent, true);
        }
    }

    public static void main(String[] args) {
        RedBlackTree bst = new RedBlackTree();
        System.out.println("\nBefore deleting:");
        bst.printTree();

        System.out.println("\nAfter deleting:");
        bst.deleteNode(40);
        bst.printTree();
    }
}