import java.io.*;
import java.util.*;

public class Main {

    static int N,M;
    static int [] arr;

    public static void main(String[] args) throws Exception {
	  //  System.setIn(new FileInputStream("C:\\Users\\reki3\\IdeaProjects\\10816\\src\\com\\company\\sample.txt"));
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	    StringTokenizer token = new StringTokenizer(br.readLine());

	    N = Integer.parseInt(token.nextToken());
	    arr = new int[N];
	    token = new StringTokenizer(br.readLine());
	    for(int i=0;i<N;i++){
	        arr[i] = Integer.parseInt(token.nextToken());
        }
        Arrays.sort(arr);
	    token = new StringTokenizer(br.readLine());
	    M = Integer.parseInt(token.nextToken());

        token = new StringTokenizer(br.readLine());
        for(int i=0;i<M;i++){
	        int num = Integer.parseInt(token.nextToken());
	        bw.write(upperBound(num) - lowerBound(num) + " ");
        }
        bw.flush();
    }
    public static int lowerBound(int target){
        int l = 0;
        int r = arr.length;
        while(l < r){
            int mid = (l+r)/2;
            if(target <= arr[mid]){
                r = mid;
            }else{
                l = mid + 1;
            }
        }
        return l;
    }
    public static int upperBound(int target){
        int l = 0;
        int r = arr.length;
        while(l < r){
            int mid = (l+r)/2;
            if(target >= arr[mid]){
                l = mid + 1;
            }else{
                r = mid;
            }
        }
        return l;
    }


}
