import java.util.*;
import java.io.*;

public class Main {
 	
	static int N,M;
	static Node [] arr;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

 	public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token = new StringTokenizer(br.readLine());
	
		N = Integer.parseInt(token.nextToken());		
        M = Integer.parseInt(token.nextToken());	
		arr = new Node[N+1];
		
		token = new StringTokenizer(br.readLine());
		for(int i =1;i<=N;i++){
			int tmp = Integer.parseInt(token.nextToken());
			arr[i] = new Node(i, tmp);
        }
        		
		Arrays.sort(arr,1,N+1,new Comparator<Node>(){
			@Override
			public int compare(Node n1, Node n2){
				return n1.num - n2.num;
			}
		});
		
		for(int i =0;i<M;i++){
			token = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(token.nextToken());
			int b = Integer.parseInt(token.nextToken());
			int k = Integer.parseInt(token.nextToken());
			
			query(a,b,k);
		}
		
 	}
	static void query(int start, int end, int kth){
		int count = 0;
		
        for(int i =1;i<=N;i++){
            if(start<= arr[i].index && arr[i].index <= end){
                count++;
            }
            if(count == kth){
                System.out.println(arr[i].num);
                return;
            }
        }
	} 
 	
}

class Node {
	public int index;
	public int num;

	Node(int index, int num){
		this.index = index;
		this.num = num;
	}	
	/*
	@Override
	public int compareTo(Node o){
		return this.num - o.num;
	}*/
}