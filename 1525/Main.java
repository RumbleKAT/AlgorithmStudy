import java.util.*;
import java.io.*;

class Main{

    static int [][] map;
    static int [][] answer;
    static int [][] dir = {{0,1},{0,-1},{1,0},{-1,0}};

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token;

        map = new int [4][4];
        answer = new int [4][4];

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

class Node{
    static int [][] map;
    static int [][] answer;

    static int f; 
    static int h; //
    static int result;

    Node(int [][] before, int [][] answer, int moves){
        this.map = new int[4][4];
        this.answer = new int[4][4];

        copyMap(map, before);
        copyMap(this.answer, answer);
        this.f = moves;
        this.h = getH();
        this.result = this.f + this.h;
        
        System.out.println(this.f + " " + this.h);
    }

    int getH(){
        int count = 0;

        printMap(this.map);
        printMap(this.answer);

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

    void copyMap(int [][] current, int [][] param){
        for(int i =1;i<=3;i++){
            for(int j=1;j<=3;j++){
                current[i][j] = param[i][j];
            }
        }
    }

    void printMap(int [][] temp){
        for(int i = 1;i<=3;i++){
            for(int j=1;j<=3;j++){
                System.out.print(temp[i][j] + " ");
            }System.out.println();
        }
        System.out.println();
    }

}