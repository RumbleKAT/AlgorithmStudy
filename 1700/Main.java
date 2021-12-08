package com.company;

import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int K;
    static int [] arr;
    static int [] plugged;

    /*
    * K번 반복문을 돌리는데,
    * 1. 해당 기기가 플러그에 꽂혀있는가 - continue로 다음 기기 선택
    * 2. 플러그에 빈 곳이 있는가 - continue로 다음 기기 선택
    * 3. 플러그에 빈 곳이 없는 경우 - 콘센트를 빼야된다. (단한번 쓰지 않을 기기를 빼거나 제일 마지막에 쓰일 기기를 뺸다.)
    *
    *
    * */

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("/Users/songmyeongjin/eclipse-workspace/2021finalTest/src/com/company/sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token =  new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());
        K = Integer.parseInt(token.nextToken());
        arr = new int[K+1];
        plugged = new int[K+1];
        token =  new StringTokenizer(br.readLine());

        for(int i=1;i<=K;i++){
            arr[i] = Integer.parseInt(token.nextToken());
        }

        int result = 0;

        for(int i=1;i<=K;i++){
            boolean pluggedIn = false;
            //해당 기기가 꽂혀있다면, 넘어간다.
            for(int j=1;j<=N;j++){
                if(plugged[j] == arr[i]){
                    pluggedIn = true;
                    break;
                }
            }
            if(pluggedIn){
                continue;
            }

            for(int j=1;j<=N;j++){
                if(plugged[j] == 0){
                    plugged[j] = arr[i];
                    pluggedIn = true;
                    break;
                }
            }
            if(pluggedIn)
                continue;

            //가장 나중에 다시 사용되거나, 앞으로 사용되지 않는 기기 찾는다.
            int idx = 0,deviceIdx = -1;
            for(int j=1;j<=N;j++){
                int lastIdx = 0;
                for(int k=i+1;k<=K;k++){
                    if(plugged[j] == arr[k]){
                        break;
                    }
                    lastIdx++;
                }
                if(lastIdx > deviceIdx){
                    idx = j;
                    deviceIdx = lastIdx;
                }
            }
            result++;
            plugged[idx] = arr[i];
        }
        System.out.println(result);
    }
}
