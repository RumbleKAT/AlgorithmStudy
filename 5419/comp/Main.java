import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.io.*;

public class Main {

	static int n, tn;
	static ArrayList<P> p = new ArrayList<P>();
	static int[] segTree;
	
	public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./sample.txt"));

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.valueOf(st.nextToken());//테스트 케이스
		for(int t=1; t<=T; t++){
			p.clear();
			st = new StringTokenizer(br.readLine());
			n = Integer.valueOf(st.nextToken());
			int x, y;
			//N개의 섬 좌표 정보 입력
			for(int i=0; i<n; i++){
				st = new StringTokenizer(br.readLine());
				x = Integer.valueOf(st.nextToken());
				y = Integer.valueOf(st.nextToken());
				p.add(new P(x, y));
			}
			//y좌표 오름차순으로 정렬
			Collections.sort(p, new Comparator<P>() {
				@Override
				public int compare(P o1, P o2) {
					return o1.y - o2.y;
				}
			});
			
			//좌표압축(입력 개수에 비해 좌표 범위가 크기 때문에 y좌표값을 순위로 갈음)
			int rank = 1;
			p.get(0).r = rank;
			for(int i=1; i<n; i++){
				if(p.get(i-1).y != p.get(i).y) rank++;
				p.get(i).r = rank;
			}
            
            System.out.println(rank);

            
			//리프 노드 하나가 y좌표 하나를 표현하게 됨
			tn = 1;
			while(tn < rank){
				tn = tn << 1;
			}
			segTree = new int[tn*2];
			
			Collections.sort(p);//x좌표 오름차순, y좌표 내림차순(rank로 대체)
			
			long ans = 0;
			for(int i=0; i<n; i++){
				ans += search(p.get(i).r, rank);//내 앞에 있는 섬들중 y좌표값이 내 y좌표값과 같거나 큰 것들
				update(p.get(i).r, 1);
			}
			bw.write(ans+"\n");;
			bw.flush();
		}
	}
	
	private static void update(int pos, int val){
		pos = pos + tn - 1;
		while(pos > 0){
			segTree[pos] += 1;
			pos /= 2;
		}
	}
	
	private static long search(int s, int e){
		long ans = 0;
		s = s + tn - 1;
		e = e + tn - 1;
		while(s<=e){
			if(s%2==1) ans += segTree[s++];
			if(e%2==0) ans += segTree[e--];
			s /= 2;
			e /= 2;
		}
		return ans;
	}
	
	private static class P implements Comparable<P>{
		int x, y, r;
		public P (int x, int y){
			this.x = x;
			this.y = y;
		}
		@Override
		public int compareTo(P o) {
			if(x == o.x){
				return o.r - r;
			}else{
				return x - o.x;
			}
		}
	}

}