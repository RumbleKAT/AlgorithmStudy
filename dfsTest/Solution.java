import java.util.*;
import java.io.*;

class Solution{

    static int N;
    static int sizes;
    static int [] seg;
    static int [] arr = {1,2,3,4,5};
    static int [] segIdx;

    public static void main(String [] args){
        N = 5;

        sizes = 1;
        while(sizes < N){
            sizes *= 2;
        }
        System.out.println(sizes);
        int ss = sizes;
        sizes *= 2;

        System.out.println(sizes);
        seg = new int[sizes];
        segIdx = new int[sizes];

        init(1, 0, N-1);

        System.out.println(Arrays.toString(seg));
        System.out.println(Arrays.toString(segIdx));

        System.out.println(seg[ss + 1 - 1] + " " + (ss));
    }
    static int init(int node, int left, int right){
        if(left == right){
            segIdx[left] = node;
            return seg[node] = arr[left];
        }
        int mid = (left + right)/2;
        return seg[node] = init(node*2,left ,mid) + init(node*2+1, mid+1, right);
    }

    static int update(int idx, int newValue, int node, int left, int right){
        if(left > idx || right < idx) return seg[node];
        if(left == right) return seg[node] = newValue;
        int mid = (left+right)/2;
        return seg[node] = update(idx, newValue, node*2, left, mid) + update(idx, newValue, node*2+1, mid+1, right);
    }

    static int sum(int node, int start, int end, int left, int right){
      if(start > right || end < left) return 0;
      if(left<=start && end <= right) return seg[node];
      int mid = (start+end)/2;
      return sum(node*2, start, mid, left, right)+sum(node*2+1, mid+1, end, left, right);
    }

}