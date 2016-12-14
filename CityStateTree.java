




import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;



/**
 * Created by mcclarci on 10/29/2016.
 * Connor McClaran Homework #4
 */
class Node {
    int key;
    String val;
    Node left, right;
    int height = 0;

    public Node(){
        left = null;
        right = null;

    }


    public Node(int key, String val) {
        left = null;
        right = null;
        this.key = key;
        this.val = val;
    }
}

public class CityStateTree {
    private Node root;

    public CityStateTree()
    {
        root = null;
    }

    public boolean search(int key) {
        Node current = root;
        while (current != null) {
            if (current.key == key) {
                return true;
            } else if (current.key > key) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return false;
    }

    public void insert(int key, String val) {
        Node newNode = new Node(key, val);
        if (root == null) {
            root = newNode;
            return;
        }
        Node current = root;
        Node parent = null;
        while (true) {
            parent = current;
            if (key < current.key) {
                current = current.left;
                if (current == null) {
                    parent.left = newNode;
                    return;
                }
            } else {
                current = current.right;
                if (current == null) {
                    parent.right = newNode;
                    return;
                }
            }

        }
    }

    public boolean delete(int key) {
        Node current = root;
        Node parent = root;
        boolean isLeftChild = false;
        /** iterate through tree to find item */
        while (current.key != key) {
            parent = current;
            if (current.key > key) {
                isLeftChild = true;
                current = current.left;
            } else {
                isLeftChild = false;
                current = current.right;
            }
            if (current == null) {
                return false;
            }
        }

        /** key has been found */
        /** Case 1: Node to be deleted has no children*/
        if (current.right == null && current.left == null) {
            if (current == root) {
                root = null;
            }
            if (isLeftChild = true) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        }

        /** Case 2: Node to be deleted has one child*/
        else if (current.right == null) {
            if (current == root) {
                root = current.left;
            } else if (isLeftChild == true) {
                parent.left = current.left;
            } else {
                parent.right = current.left;
            }
        } else if (current.left == null) {
            if (current == root) {
                root = current.right;
            } else if (isLeftChild == true) {
                parent.left = current.right;
            } else {
                parent.right = current.right;
            }
        }/** Node to be deleted has multiple children*/
        else if (current.right != null && current.left != null) {
            /** Sucesor is minimum value in right subtree of value to be deleted*/
            Node successor = getSuccessor(current);
            if (current == root) {
                root = successor;
            } else if (isLeftChild == true) {
                parent.left = successor;
            } else {
                parent.right = successor;
            }
            successor.left = current.left;
        }
        return true;
    }

    public Node getSuccessor(Node deleleNode) {
        Node successsor = null;
        Node successsorParent = null;
        Node current = deleleNode.right;
        while (current != null) {
            successsorParent = successsor;
            successsor = current;
            current = current.left;
        }

        if (successsor != deleleNode.right) {
            successsorParent.left = successsor.right;
            successsor.right = deleleNode.right;
        }
        return successsor;
    }
    public void preorder(Node root) {
        if(root !=  null) {

            System.out.printf("%d ",root.val);
            preorder(root.left);
            preorder(root.right);
        }
    }


    public class Balance{

    }



    public static void main(String[] args){
        long x;
        long y;
        long totaltime;
        String fileName = "dma.txt";



        String line = null;
        try {
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            x = System.nanoTime();


            while ((line = BufferedReader.readLine()) != null) {

                int Areacode = line.substring(0, 3);
                String City = line.substring(4);
                insert(Areacode, City);


            }
            y = System.nanoTime();
        } catch (FileNotFoundException ex) {
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");

        }
        totaltime = x + y;




        }

    }













