//package com.company;
import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int [][] map;
    static boolean [][] check;
    static int [][] dir = {{0,1},{1,0},{0,-1},{-1,0}};

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("C:\\Users\\reki3\\IdeaProjects\\2468\\src\\input.txt"));
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());
        map = new int[N+1][N+1];
        check = new boolean[N+1][N+1];

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for(int i=1;i<=N;i++){
            StringTokenizer token2 = new StringTokenizer(br.readLine());
            for(int j=1;j<=N;j++){
                map[i][j] = Integer.parseInt(token2.nextToken());
                min = Math.min(min, map[i][j]);
                max = Math.max(max, map[i][j]);
            }
        }
//        System.out.println(min + " " + max);
        int maxCnt = 0;
        int level = 0;
        if(min == max){
            System.out.println(1);
            return;
        }
        for(int i=min;i<=max;i++){
            colorMap(i);
            //printMap(check);
            //System.out.println();
            //grouping
            int curCnt = 0;
            for(int a=1;a<=N;a++){
                for(int b=1;b<=N;b++){
                    if(!check[a][b]){
                        dfs(a,b);
                        curCnt++;
                    }
                }
            }
            if(maxCnt < curCnt){
                level = i;
                maxCnt = curCnt;
            }
            initCheck();
        }

        System.out.println(maxCnt);
    }
    static void dfs(int y, int x){
        for(int i=0;i<dir.length;i++){
            int nextY = dir[i][0] + y;
            int nextX = dir[i][1] + x;

            if(nextX >= 1  && nextX<= N && nextY >= 1 && nextY <= N){
                if(!check[nextY][nextX]){
                    check[nextY][nextX] = true;
                    dfs(nextY,nextX);
                }
            }
        }
    }

    static void initCheck(){
        for(int i=0;i<=N;i++){
            for(int j=0;j<=N;j++){
                check[i][j] = false;
            }
        }
    }
    static void colorMap(int param){
        for(int i=1;i<map.length;i++){
            for(int j=1;j<map[0].length;j++){
                if(map[i][j] <= param){
                    check[i][j] = true;
                }
            }
        }
    }
    static void printMap(boolean[][] param){
        for(int i=1;i<param.length;i++){
            for(int j=1;j<param.length;j++){
                System.out.print(param[i][j] + " ");
            }System.out.println();
        }
    }
}
