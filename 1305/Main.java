import java.util.*;
import java.io.*;

public class Main {

	static int n;
	static int [] pi;
	static String s;

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("/Users/songmyeongjin/eclipse-workspace/Solution/src/sample.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token = new StringTokenizer(br.readLine());

		pi = new int [1000001];
		n = Integer.parseInt(token.nextToken());

		token = new StringTokenizer(br.readLine());
		s = token.nextToken();

		int j = 0;
		for(int i=1;i<n;i++){
			while(s.charAt(i) != s.charAt(j) && j > 0) j = pi[j-1];
			if(s.charAt(i) == s.charAt(j))pi[i] = ++j;
		}

		System.out.println(n-pi[n-1]);
	}

}