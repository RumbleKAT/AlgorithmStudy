import java.io.*;
import java.util.*;

class Main {
	
	static int N,M;
	static long [] seg;	
	static long [] arr;
	
	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("./sample.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(token.nextToken());
		M = Integer.parseInt(token.nextToken());
		
		arr = new long[N+2];
		seg = new long[N*4];
		token = new StringTokenizer(br.readLine());
		
		for(int i = 1;i<=N;i++){
			arr[i] = Long.parseLong(token.nextToken());
		}

		init(1, 1, N);
		
		for(int i=1;i<=M;i++){
			token = new StringTokenizer(br.readLine());
			int order = Integer.parseInt(token.nextToken());
			int s = Integer.parseInt(token.nextToken());
			int e = Integer.parseInt(token.nextToken());

			if(order == 1){
				System.out.println(query(1, s, e, 1, N));
			}else{
				update(1, 1, N, s, e);
			}

		}
		
	}
	static long query(int node, int left, int right, int start, int end){
		if(start > right || end < left) return 0;
		if(left <= start && end <= right){
			return seg[node];
		}else{
			int mid = (start+end)/2;
			return query(node*2, left, right, start, mid) + query(node*2+1, left, right, mid+1, end);
		}
	}

	static long init(int node, int left, int right){
		if(left == right){
			return seg[node] = arr[left];
		}
		int mid = (left+right)/2;
		return seg[node] = init(node*2, left, mid) + init(node*2+1, mid+1, right);
	}
	static long update(int node, int left, int right, int index, long val){
		if(index < left || index > right) return seg[node];
		if(left == right){
			return seg[node] = val;
		}else{
			int mid = (left+right)/2;
			return seg[node] = update(node*2, left, mid, index, val) + update(node*2+1, mid+1, right, index, val);
		}
	}
}