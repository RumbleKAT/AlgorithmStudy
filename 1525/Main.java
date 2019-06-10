import java.util.*;
import java.io.*;

class Main{

    static int [][] map;
    static int [][] answer;
    static int [][] dir = {{0,1},{0,-1},{1,0},{-1,0}};
    static ArrayList<Node> vlist;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token;

        map = new int [4][4];
        answer = new int [4][4];
        vlist = new ArrayList<>();

        for(int i = 1;i<=3;i++){
            token = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(token.nextToken());
            int b = Integer.parseInt(token.nextToken()); 
            int c = Integer.parseInt(token.nextToken());

            map[i][1] = a;
            map[i][2] = b;
            map[i][3] = c;
        }

        answer = new int [][] {{0,0,0,0},{0,1,2,3},{0,8,0,4},{0,7,6,5}};
        Node current = new Node(map, answer, 0);
        
        PriorityQueue <Node> pq = new PriorityQueue<>();
        pq.add(current);
        vlist.add(current);

        
        printMap(current.map);
        System.out.println();

        while(!pq.isEmpty()){
            Node temp = pq.poll();

            System.out.println();
            printMap(temp.answer);
            System.out.println();
            //방문 했었던 애들을 가지고 있을 필요가 있을까?

            if(ansCheck(temp.map, temp.answer)){
                System.out.println("!!");
                break;
            }

            int zero_y = -1;
            int zero_x = -1;

            //search x ,y
            
            Outter : for(int i = 1;i<=3;i++){
                for(int j =1;j<=3;j++){
                    if(temp.map[i][j] == 0){
                        zero_x = j;
                        zero_y = i;
                        break Outter;
                    }
                }
            }

            for(int i =0;i<dir.length;i++){
                int next_y = zero_y + dir[i][0];
                int next_x = zero_x + dir[i][1];

                if(rangeCheck(next_y, next_x)){
                    System.out.println();
                    int [][] nextMap = new int [4][4];

                    nextMap = copy(temp.map);
                    nextMap = swap(nextMap, zero_x,zero_y,nextMap[zero_y][zero_x], next_x, next_y, nextMap[next_y][next_x]);

                    Node next = new Node(nextMap, temp.answer, temp.f+1);
                    
                    if(vListCheck(next.map)){
                        System.out.println("방문 안한 노드");
                        printMap(next.map);
                        pq.add(next);
                        vlist.add(next);
                    }
                }
            }

        }
    }

    static boolean ansCheck(int [][] temp, int [][] answer){
        boolean result = false;
        int cnt = 0;

        for(int i =1;i<=3;i++){
            for(int j=1;j<=3;j++){
                if(temp[i][j] == answer[i][j]){
                    cnt++;
                }
            }
        }
        if(cnt == 9){
            result = true;
        }

        return result;
    }

    static boolean vListCheck(int [][] temp){
        boolean result = false;
        
        for(Node cur : vlist){
            int count = 0;
            for(int i =1;i<=3;i++){
                for(int j=1;j<=3;j++){
                    if(cur.map[i][j] == temp[i][j]){
                        count++;
                    }
                }
            }
            if(count == 9){
                result = true;
                return result;
            }
        }
        return result;
    }

    static int [][] copy(int [][] result){
        int [][] param = new int[4][4];
        for(int i =1;i<=3;i++){
            for(int j =1;j<=3;j++){
                param[i][j] = result[i][j];
            }
        }
        return param;
    }

    
    static int [][] swap(int [][] param ,int x, int y, int temp, int x1, int y1, int temp1){
        param[y][x] = temp1;
        param[y1][x1] = temp;

        return param;
    }

    static void printMap(int [][] temp){
        for(int i = 1;i<=3;i++){
            for(int j=1;j<=3;j++){
                System.out.print(temp[i][j] + " ");
            }System.out.println();
        }
    }

    static boolean rangeCheck(int x, int y){
        if(x >= 1 && x <= 3 && y>=1 && y<= 3){
            return true;
        }else{
            return false;
        }
    }

}

class Node implements Comparable<Node>{
    public int [][] map;
    public int [][] answer;

    public int f; 
    public int h; //
    public int result;

    Node(int [][] before, int [][] answer, int moves){
        this.map = new int[4][4];
        this.answer = new int[4][4];

        this.map = copyMap(before);
        this.answer = copyMap(answer);
        this.f = moves;
        this.h = getH();
        this.result = this.f + this.h;
    }

    int getH(){
        int count = 0;

        //printMap(this.map);
        //printMap(this.answer);

        for(int i = 1;i<=3;i++){
            for(int j =1;j<=3;j++){
                if(this.map[i][j] == 0){
                    continue;
                }else{
                    if(this.map[i][j] != this.answer[i][j]){
                        count++;
                    }
                }
            }
        }
        return count;
    }

    int [][] copyMap(int [][] param){
        int [][] current = new int [param.length][param.length];
        for(int i =1;i<=3;i++){
            for(int j=1;j<=3;j++){
                current[i][j] = param[i][j];
            }
        }
        return current;
    }

    void printMap(int [][] temp){
        for(int i = 1;i<=3;i++){
            for(int j=1;j<=3;j++){
                System.out.print(temp[i][j] + " ");
            }System.out.println();
        }
        System.out.println();
    }

    @Override
    public int compareTo(Node target) {
        return this.result >= target.result ? 1 : - 1;
    }

}