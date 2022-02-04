package com.company;

import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static char [][] map;
    static int max = 0;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("/Users/songmyeongjin/eclipse-workspace/2503/src/com/company/sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());

        map = new char[N][N];
        for(int i=0;i<N;i++){
            token = new StringTokenizer(br.readLine());
            String row = token.nextToken();
            for(int j=0;j<N;j++){
                map[i][j] = row.charAt(j);
            }
        }

        //가로로 바꾸기
        for(int i=0;i<N;i++){
            for(int j=0;j<N-1;j++){
                char temp = map[i][j];
                map[i][j] = map[i][j+1];
                map[i][j+1] = temp;

                //check Arr
                checkArr(map);

                temp = map[i][j];
                map[i][j] = map[i][j+1];
                map[i][j+1] = temp;
            }
        }

//        //세로로 바꾸기
        for(int i=0;i<N;i++){
            for(int j=0;j<N-1;j++){
                char temp = map[j][i];
                map[j][i] = map[j+1][i];
                map[j+1][i] = temp;

                //check Arr
                checkArr(map);

                temp = map[j][i];
                map[j][i] = map[j+1][i];
                map[j+1][i] = temp;
            }
        }

        System.out.println(max);

    }
    static int checkArr(char [][] map){
        for(int i=0;i<N;i++){
            int rowCnt = 1;
            for(int j=0;j<N-1;j++){
                if(map[i][j] == map[i][j+1]){
                    rowCnt++;
                }else{
                    rowCnt = 1;
                }
                max = Math.max(max,rowCnt);
            }
        }


        for(int i=0;i<N;i++){
            int colCnt = 1;
            for(int j=0;j<N-1;j++){
                if(map[j][i] == map[j+1][i]){
                    colCnt++;
                }else{
                    colCnt = 1;
                }
                max = Math.max(max,colCnt);
            }
        }

        return max;
    }

    static void printMap(char [][] map){
        System.out.println();
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[i].length;j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}
