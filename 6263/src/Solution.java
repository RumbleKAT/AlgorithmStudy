import java.util.*;
import java.io.*;

public class Solution {

	static int N,M;
	static long [] arr;
	
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("C:\\Users\\root\\eclipse-workspace\\6263\\sample.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(token.nextToken());
		M = Integer.parseInt(token.nextToken());
		
		arr = new long[N+1];
		
		long sum = 0;
		
		for(int i=1;i<=N;i++) {
			token = new StringTokenizer(br.readLine());
			arr[i] = Long.parseLong(token.nextToken());
			sum += arr[i];
		}
		
		System.out.println(sum);
		
		long low =1;
		long high= sum;
		long ans= 0;
		
		while(low<=high) {
			long mid = (low+high)/2;
			if(check(mid)) {
				ans= mid;
				high = mid -1;
			}else {
				low = mid +1;
			}
		}
		
		System.out.println(ans);
		
	}
	static boolean check(long mid) {
		long num = 1;
		long sum = mid; 
		
		for(int i=1;i<=N;i++) {
			if(mid < arr[i]) {
				return false;
			}
			if(sum - arr[i] < 0) {
				sum = mid;
				num++;
			}
			sum -= arr[i];
		}
		return M >= num;
	}

}
