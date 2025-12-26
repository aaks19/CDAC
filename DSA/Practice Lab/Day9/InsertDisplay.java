class Node {
    int data;
    Node right;
    Node left;

    public Node(int data) {
        this.data = data;
        right = null;
        left = null;
    }
}

class InsertDisplay {
    static Node root;

    public void Insert(int data) {
        Node newNode = new Node(data);

        if (root == null) {
            root = newNode;
        } else {
            Node temp = root;
            while (true) {
                if (data < temp.data) {
                    if (temp.left == null) {
                        temp.left = newNode;
                        break;
                    } else {
                        temp = temp.left;
                    }
                } else {
                    if (temp.right == null) {
                        temp.right = newNode;
                        break;
                    } else {
                        temp = temp.right;
                    }
                }
            }
        }
    }

    public void Search(int data) {
        if (root.data == data) {
            System.out.println("Found At Root...");
        } else {
            Node temp = root;
            while (true) {
                if (data < temp.data) {
                    if (temp.left == null) {
                        System.out.println("Not Found...");
                        break;
                    } else if (temp.left.data == data) {
                        System.out.println("Found...");
                        break;
                    } else {
                        temp = temp.left;
                    }
                } else {
                    if (temp.right == null) {
                        System.out.println("Not found...");
                        break;
                    } else if (temp.right.data == data) {
                        System.out.println("Found...");
                        break;
                    } else {
                        temp = temp.right;
                    }
                }
            }
        }
    }

    public void InOrder(Node root) {
        if (root == null) {
            return;
        }

        InOrder(root.left);
        System.out.print(root.data + " ");
        InOrder(root.right);
    }

    public void PreOrder(Node root) {
        if (root == null) {
            return;
        }

        System.out.print(root.data + " ");
        PreOrder(root.left);
        PreOrder(root.right);
    }

    public void PostOrder(Node root) {
        if (root == null) {
            return;
        }

        PostOrder(root.left);
        PostOrder(root.right);
        System.out.print(root.data + " ");
    }

    public static void main(String[] args) {
        InsertDisplay tr = new InsertDisplay();

        tr.Insert(30);
        tr.Insert(50);
        tr.Insert(20);
        tr.Insert(10);
        tr.Insert(90);
        tr.Insert(80);

        tr.Search(80);

        // System.out.print("InOrder: ");
        // tr.InOrder(root);
        // System.out.print("\n");

        // System.out.print("PreOrder: ");
        // tr.PreOrder(root);
        // System.out.print("\n");

        // System.out.print("PostOrder: ");
        // tr.PostOrder(root);
    }
}
