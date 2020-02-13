import java.util.*;
import java.io.*;

class Main{
    
    static int TC;
    static int n;
    static int res;
    
    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        TC = Integer.parseInt(token.nextToken());

        for(int t=1;t<=TC;t++){
            token = new StringTokenizer(br.readLine());
            n = Integer.parseInt(token.nextToken());

            Node root = new Node('r',0);
            for(int i =1;i<=n;i++){
                Node node = root;
                String str = br.readLine();

                for(int j=0;j< str.length();j++){
                    char c = str.charAt(j);
                    if(node.next[c-'0'] == null){
                        node.cnt++; //한글자씩 연결될때마다 cnt = 1
                        node.next[c-'0'] = new Node(c,0);
                    }  
                    node = node.next[c-'0'];
                }
            }
            res = 0;
            search(root);
            if(res != n){
                System.out.println("NO");
            }else{
                System.out.println("YES");
            }
        }
    }
    static void search(Node node){
        if(node.cnt == 0){
            res++;
            return;
        }
        for(int i =0;i<10;i++){
            if(node.next[i] != null) search(node.next[i]);
        }
    }
}

class Node{
    public char data;
    public int cnt;
    Node [] next = new Node[10];

    public Node(char data, int cnt){
        this.data = data;
        this.cnt = cnt;
    }
}