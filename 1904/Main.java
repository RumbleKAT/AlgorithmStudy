//package com.company;

import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int [] dp;
    static final int MOD = 15746;

    public static void main(String[] args) throws Exception {
       // System.setIn(new FileInputStream("/Users/songmyeongjin/eclipse-workspace/2512/src/com/company/sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());

        dp = new int[N+1];
        if(N == 1){
            System.out.println(1);
            return;
        }
        dp[1] = 1;
        if(N == 2){
            System.out.println(2);
            return;
        }
        dp[2] = 2;

        for(int i=3;i<=N;i++){
            dp[i] = (dp[i-2] + dp[i-1]) % MOD;
        }
        System.out.println(dp[N]);
    }

  }