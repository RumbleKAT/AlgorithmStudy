import java.util.*;
import java.io.*;

public class Main {

	static int N;
	static long M;
	static int [] arr;
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("C:\\Users\\root\\eclipse-workspace\\2805\\sample.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token = new StringTokenizer(br.readLine());

		N = Integer.parseInt(token.nextToken());
		M = Long.parseLong(token.nextToken());
		
		arr = new int[N+1];
		token = new StringTokenizer(br.readLine());
		
		long max = 0;
		for(int i=1;i<=N;i++) {
			arr[i] = Integer.parseInt(token.nextToken());
			max = Math.max(max, arr[i]);
		}
		
		long left = 1;
		long right = max;
		long ans = 0;
		long res = 0;
		
		while(left<=right) {
			long mid = (left+right)/2;
			
			long sum = 0;
			
			for(int i=1;i<=N;i++) {
				if(arr[i] > mid) {
					sum += arr[i] - mid;
				}
			}
			
			if(sum >= M) {
				res = Math.max(res, mid);
				left = mid + 1;
			}else {
				right = mid -1;
			}
		}
		
		System.out.println(res);
		
		
	}

}
