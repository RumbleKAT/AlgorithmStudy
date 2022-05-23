import java.util.*;
import java.io.*;


public class Main {

	static int N,M;
	static int [] arr;
	static int sum;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./sample.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token = new StringTokenizer(br.readLine());

		N = Integer.parseInt(token.nextToken());
		M = Integer.parseInt(token.nextToken());

		arr = new int[100001];
		token = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++){
			arr[i] = Integer.parseInt(token.nextToken());
		}

		sum = 0;
		int left = 0, right = 0;
		int cnt = 0;

		while(right <= N){
            if(sum >= M) sum -= arr[left++];
            else if(sum < M) sum += arr[right++];
            if(sum == M) cnt++;
        }
        System.out.println(cnt);
	}

}
