import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static long [] arr = new long [1000001];
	static int N,M;
	
	public static void main(String [] args) throws Exception {
		System.setIn(new FileInputStream("./sample.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token = new StringTokenizer(br.readLine());
		N = Integer.parseInt(token.nextToken());
		M = Integer.parseInt(token.nextToken());
		
		for(int i = 1;i<=N;i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		SegmentTree st = new SegmentTree(N,arr);

		long result = 0;
	
		for(int i = 0;i<M;i++) {
			token = new StringTokenizer(br.readLine());
			int left = Integer.parseInt(token.nextToken());
			int right = Integer.parseInt(token.nextToken());
		
			result = st.get(1,1,N,left, right);
			
			System.out.println(result);
		}
		
	}
}

class SegmentTree{
	static int size;
	static long [] arr;
	static long [] args;
	
	public SegmentTree(int size, long[] arr2) {
        this.size = size;
		arr = new long[this.size*4];
		this.args = arr2;
		init(1,1,size);
	}
	
	long init(int index, int start, int end) {
		if(start == end) {
			return arr[index] = args[start];
		}
		return arr[index] = Math.min(init(index*2,start,(start+end)/2) , init(index*2+1,(start+end)/2+1, end));		
	}
	
	public long get(int index, int start, int end, int left, int right) {
		if(start > right || end < left) {
			return Integer.MAX_VALUE;
		}
		if(left <= start && end <= right) {
			return arr[index];
		}
		return Math.min(get(index*2,start, (start+end)/2, left, right), get(index*2+1,(start+end)/2+1,end,left,right));
	}
}
