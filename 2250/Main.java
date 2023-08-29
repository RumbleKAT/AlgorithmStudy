import java.util.*;
import java.io.*;


class Main{

    static int N;
    static int[] levelMin;
    static int[] levelMax;
    static int maxLevel = 0;
    static int point = 1; 
    static int rootIndex = 0;
    static Map<Integer, Node> nodeMap;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());
        levelMin = new int[N+1];
        levelMax = new int[N+1];
        
        for(int i=1;i<=N;i++){
            levelMin[i] = N;
            levelMax[i] = 0;
        }

        nodeMap = new HashMap<>();
        
        for(int i=1;i<=N;i++){
            token = new StringTokenizer(br.readLine());
            int data = Integer.parseInt(token.nextToken());
            int left = Integer.parseInt(token.nextToken());
            int right = Integer.parseInt(token.nextToken());

            Node currentNode = nodeMap.getOrDefault(data, new Node(data));
            nodeMap.put(data, currentNode);

            if (left != -1) {
                Node leftNode = nodeMap.getOrDefault(left, new Node(left));
                currentNode.left = leftNode;
                nodeMap.put(left, leftNode);
            }
            if (right != -1) {
                Node rightNode = nodeMap.getOrDefault(right, new Node(right));
                currentNode.right = rightNode;
                nodeMap.put(right, rightNode);
            }

            if(left != -1)  nodeMap.get(left).parent = data;
            if(right != -1) nodeMap.get(right).parent = data;
        }
        for(int i = 1; i<=N; i++) {
            if(nodeMap.get(i).parent == -1) {
                rootIndex = i;
                break;
            }
        }
        inOrder(rootIndex,1);
        
        int answerLevel = 1;
        int answerWidth = levelMax[1] - levelMin[1] + 1;
        for (int i = 2; i<= maxLevel; i++) {
            int width = levelMax[i] - levelMin[i] + 1;
            if(answerWidth < width) {
                answerLevel = i;
                answerWidth = width;
            }
        }
        System.out.println(answerLevel + " " + answerWidth);

    }

    public static void inOrder(int rootIndex, int level) {
        Node root = nodeMap.get(rootIndex);
        if(maxLevel < level) maxLevel = level;
        if(root.left != null && root.left.data != -1) {
            inOrder(root.left.data, level + 1);
        }
        levelMin[level] = Math.min(levelMin[level], point);
        levelMax[level] = point;
        point++;
        if(root.right != null && root.right.data != -1) {
            inOrder(root.right.data, level + 1);
        }
    }
}
class Node {
    int parent;
    int data;
    Node left, right;

    public Node(int data) {
        this.parent = -1;
        this.data = data;
        this.left = null;
        this.right = null;
    }
}
