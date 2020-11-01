import java.util.*;
import java.io.*;

public class Main {

	static int N,K;
	static long [] seg;
	static int [] arr;
	static int H;
	static int size = 250000;
	static int max = 65536;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("C:\\Users\\root\\eclipse-workspace\\1572\\sample.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token = new StringTokenizer(br.readLine());

		N = Integer.parseInt(token.nextToken());
		K = Integer.parseInt(token.nextToken());
		
		H = 1;
		while(H<=N) {
			H *=2;
		}
		arr = new int[N+1];
		seg = new long[size*4];
		long sum = 0;
		
        int cnt = 0;
        
        //배열의 중앙값 구하기
		for(int i=1;i<=N;i++) {
			token = new StringTokenizer(br.readLine());
			int temp = Integer.parseInt(token.nextToken());
			arr[i] = temp;
			update(1, arr[i], 0, max, 1);

			if(i >= K) {
				if(i != K) update(1, arr[i-K], 0, max, -1);
				long ans = query(1, 0, max, (K+1)/2);
				sum += ans;
			}			
		}
		System.out.println(sum);
		
	}
	static long query(int node, int start, int end, long K) {
		if(start == end) return start;
		int mid = (start+end)/2;
		if(K <= seg[node*2]) return query(node*2, start, mid, K);
		else return query(node*2+1, mid+1, end, K-seg[node*2]);
	}
//	static long query(int node, int start, int end, int left, int right) {
//		if(start > right || end < left) return 0;
//		if(left <= start && end <= right) return seg[node];
//		int mid = (start+end)/2;
//		return query(node*2, start, mid, left, right) + query(node*2+1, mid+1, end, left, right); 
//	}
	static long update(int node, int index, int left, int right, int param) {
		if(index < left || index > right) return seg[node];
		if(left == right) {
			return	seg[node] += param;
		}
		int mid = (left+right)/2;
		return seg[node] = update(node*2, index, left, mid, param) + update(node*2+1, index, mid+1, right, param);
	}


}
