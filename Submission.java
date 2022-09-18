import java.util.*;
class Submission {

    static Node root;
    Submission() {
        root = null;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int flag;
        while (scanner.hasNextInt()) {
            flag = scanner.nextInt();
            if (flag == 1) {
                insert(scanner.nextInt());
            }
            if (flag == 2) {
                search(scanner.nextInt());
            }
            if (flag == 3) {
                int value = max(root);
                search(value);
            }
            if (flag == 4) {
                int value = min(root);
                search(value);
            }
            if (flag == 5) {
                preOrder(root);
            }
            if (flag == 6) {
                postOrder(root);
            }
            if (flag == 7) {
                inOrder(root);
            }
            if (flag == 8) {
                delete(scanner.nextInt());
            }
            if (flag == 0) {
                break;
            }
        }
    }

    //Method to insert node to the BST.
    public static void insert(int value) {
        root = recursiveInsert(root, value);
    }

    private static Node recursiveInsert(Node root, int key) {
        if (root == null) {
            root = new Node(key);
            return root;
        }
        else if (key == root.data) {
            root.counter++;
            return root;
        }
        else if (key < root.data) {
            root.left = recursiveInsert(root.left, key);
        }
        else {
            root.right = recursiveInsert(root.right, key);
        }
        return root;
    }

    //Method to search for nodes in the BST.
    public static void search(int key) {
        recursiveSearch(root, key);
    }

    private static void recursiveSearch(Node root, int key) {
        if (root == null) {
            System.out.print(key + "(0)");
            return;
        }
        if (root.data == key) {
            System.out.print(root.data + "(" + root.counter + ")");
        }
        else if (root.data < key) {
            recursiveSearch(root.right, key);
        }
        else {
            recursiveSearch(root.left, key);
        }
    }

    public static int max(Node root) {
        
        if (root == null) {
            //System.out.print("0(0)");   
            return 0;
        }
        while (root.right != null) {
            root = root.right;
        }
        return root.data;
    }

    public static int min(Node root) {
        if (root == null) {
            return 0;
            //System.out.print("0(0)");   
        }
        while (root.left != null) {
            root = root.left;
        }
        return root.data;
    }

    public static void preOrder(Node root) {
        if (root != null) {
            search(root.data);
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    public static void postOrder(Node root) {
        if (root != null) {
            postOrder(root.left);
            postOrder(root.right);
            search(root.data);
        }
    }

    public static void inOrder(Node root) {
        if (root != null) {
            inOrder(root.left);
            search(root.data);
            inOrder(root.right);
        }
    }

    public static void delete(int key) {
        root = recursiveDelete(root, key);
    }
    
    private static Node recursiveDelete(Node root, int key) {
        if (root == null) {
            return root;   
        }
        if (key < root.data) {
            root.left = recursiveDelete(root.left, key);
        }
        else if (key > root.data) {
            root.right = recursiveDelete(root.right, key);   
        }
        else if (root.data == key && root.counter > 1) {
            root.counter--;
            return root;
            }
        else {
            if (root.left == null) {
                return root.right;   
            }
            else if (root.right == null) {
                return root.left;   
            }
            else {
                root.data = max(root.left);
                root.left = recursiveDelete(root.left, root.data);
                //root.counter--;
                return root;
            }
        }
        return root;
    }

    //Node class
    static class Node {
        int data;
        int counter;
        Node left, right;

        public Node(int item) {
            data = item;
            counter = 1;
            left = right = null;
        }
    }
}