import java.util.*;
import java.io.*;


class Main{

    static Node [] preOrder;
    static int [] postOrder;
    static int MAX = 1000001;


    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Node tree = new Node(Integer.parseInt(br.readLine()));
        String s = "";
        while ((s = br.readLine()) != null && s.length() != 0) {    //EOF까지 입력받음
            tree = tree.addTree(tree, Integer.parseInt(s));
        }
        postOrder(tree);
    }
    static void postOrder(Node node){
        if(node.left != null) postOrder(node.left);
        if(node.right != null) postOrder(node.right);
        System.out.println(node.data);
    }
}
class Node{
    public Node left;
    public Node right;
    public int data;

    Node(int data){
        this.left = null;
        this.right = null;
        this.data = data;
    }

    public Node addTree(Node tree, int val){
        Node tmp = null;
        if(tree == null) return new Node(val);
        if(tree.data > val){
            tmp = addTree(tree.left, val);
            tree.left = tmp;
        }else if(tree.data < val){
            tmp = addTree(tree.right, val);
            tree.right = tmp;
        }
        return tree;
    }
}