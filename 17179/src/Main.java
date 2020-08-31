import java.util.*;
import java.io.*;

public class Main {

	static int N,M,L;
	static int [] arr;
	static int [] cnt;
	static int K;
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("C:\\Users\\root\\eclipse-workspace\\17179\\sample.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(token.nextToken());
		M = Integer.parseInt(token.nextToken());
		L = Integer.parseInt(token.nextToken());
		
		arr = new int [M+2];
		cnt = new int [N+1];
		
		int max = 0;
		for(int i=1;i<=M;i++) {
			token = new StringTokenizer(br.readLine());
			arr[i] = Integer.parseInt(token.nextToken());
		}
		arr[M+1]= L;
		
		for(int i=1;i<=N;i++) {
			token = new StringTokenizer(br.readLine());
			K = Integer.parseInt(token.nextToken());
		
			int low = 0;
			int high = L;
			int ans = 0;
			
			while(low<=high) {
				int mid = (low + high)/2;
				if(check(mid,i)) {
					ans = Math.max(ans, mid);
					low = mid + 1;
				}else {
					high = mid - 1;
				}
			}
			
			System.out.println(ans);
		}
		
		
	}
	static boolean check(long mid,int idx) {
		int count = K+1;
		long m = 0;
		for(int i=1;i<=M+1;i++) {
			if(arr[i] - m >= mid) {
				count -=1;
				m = arr[i];
			}
		}
		return count <= 0;	
	}

}
