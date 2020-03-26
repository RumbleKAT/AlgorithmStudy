import java.util.*;
import java.io.*;


public class Main{
	
	static int N = 200001;
	static long M = 1000000001;
	static long [] arr = { 120, 110, 140, 150 };

    public static void main(String args[]) throws Exception{
        // System.setIn(new FileInputStream("./sample.txt"));
		// BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// StringTokenizer token = new StringTokenizer(br.readLine());
              
       	long maxValue = 0;
		long minValue = Integer.MAX_VALUE;
	//    token = new StringTokenizer(br.readLine());
	   for(int i=0;i<arr.length;i++){
	     	// arr[i] = Long.parseLong(token.nextToken());
			maxValue = Math.max(maxValue,arr[i]);
			minValue = Math.min(minValue,arr[i]);
       }
		   M = 485;
		   
		long left = minValue;
		long right = maxValue;
		long total = 0;
		long ans = 0;
		long mid = 0;
		
		while(left<=right){
			mid = (left+right)/2;
			total = 0;
			
			for(int i=0;i<arr.length;i++){
				if(mid > arr[i]){
					total += arr[i];
				}else{
					total += mid;
				}
			}

			if(total > M){
				right = mid - 1;
			}else{
				ans = Math.max(ans, mid);
				left = mid + 1;
			}

		}	
		
		System.out.println(ans);	
    }
}




