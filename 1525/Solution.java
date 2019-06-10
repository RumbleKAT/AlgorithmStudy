import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


class Solution{

    static int [] dx = {1,-1,0,0};
    static int [] dy = {0,0, 1,-1};
    static Queue<Integer> que;
    static int [] dist;
    static final int MAX = 10001;
    static String ans;
    static HashMap<Integer, Integer> map;

   public static void main(String [] args){
        Scanner scan = new Scanner(System.in);
        int start = 0;
        for(int i = 0;i<3;i++){
            for(int j=0;j<3;j++){
                int tmp = scan.nextInt();
                if(tmp == 0) tmp = 9;
                start = start * 10 + tmp;
            }
        }
        
        que = new LinkedList<>();
        map = new HashMap<Integer, Integer>();

        que.add(start);
        map.put(start, 0);

        while(!que.isEmpty()){
            int current = que.poll();
            String now = String.valueOf(current);
            int nine = now.indexOf('9');
            int x = nine / 3;
            int y = nine % 3;

            for(int i =0;i<4;i++){
                int nx = x+dx[i];
                int ny = y+dy[i];
                if((nx>=0&&nx<3)&&(ny>=0&&ny<3)){
                    StringBuilder next = new StringBuilder(now);
                    char temp = next.charAt(x*3+y);
                    next.setCharAt(x*3+y, (char)next.charAt(nx*3+ny));
                    next.setCharAt(nx*3+ny, temp);

                    int num = Integer.parseInt(next.toString());
                    if(!map.containsKey(num)){
                        map.put(num, map.get(current)+1);
                        que.add(num);
                    }
                }
            }
        }

        if(map.containsKey(123456789)){
            System.out.println(map.get(123456789));
        }else{
            System.out.println("-1");
        }

   } 
}