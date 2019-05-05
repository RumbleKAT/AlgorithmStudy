package com.company;

import java.util.*;
import java.io.*;

class Main{

    static int V,E;
    static Node [] arr;
    static int [] parent;
    static int cost;

    public static void main(String  [] args) throws Exception{
        System.setIn(new FileInputStream("/Users/songmyeongjin/IdeaProjects/1197/src/sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        V = Integer.parseInt(token.nextToken());
        E = Integer.parseInt(token.nextToken());

        cost = 0;
        parent = new int[V+1];
        int max_len = Math.max(V+1,E+1);
        arr = new Node[max_len];

        for(int i =0;i<=V;i++){
            parent[i] = i;
            arr[i] = new Node();
        }

        for(int i =1;i<=E;i++){
            token = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(token.nextToken());
            int next = Integer.parseInt(token.nextToken());
            int value = Integer.parseInt(token.nextToken());

            arr[i] = new Node(v,next,value);
        }

        Arrays.sort(arr,1,E);

        for(int i =1;i<=E;i++) {
            int aRoot = find(arr[i].current);
            int bRoot = find(arr[i].next);

            if (aRoot == bRoot) {
                continue;
            } else {
                parent[aRoot] = bRoot;
                cost += arr[i].value;
            }
        }

        System.out.println(cost);
    }

    static void display(Node [] arr){
        for(int i=1;i<=V;i++){
            System.out.println(arr[i].current + " " + arr[i].next + " " + arr[i].value);
        }
    }



    static int find(int a){
        if(parent[a] == a){
            return a;
        }else{
            return parent[a] = find(parent[a]);
        }
    }
}

class Node implements Comparable<Node> {

    public int value;
    public int current;
    public int next;

    public Node(){
        this.current = -1;
        this.next = -1;
        this.value = Integer.MAX_VALUE;
    }

    public Node(int current, int next, int value){
        this.current = current;
        this.next = next;
        this.value = value;
    }

    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }
}