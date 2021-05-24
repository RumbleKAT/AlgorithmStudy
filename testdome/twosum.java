package com.company;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        int[] indices = findTwoSum(new int[] { 3, 1, 5, 7, 5, 9 }, 10);
        if(indices != null) {
            System.out.println(indices[0] + " " + indices[1]);
        }
    }

    public static int[] findTwoSum(int[] list, int sum) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int [] ans = new int[2];
        int cnt = 0;
        for(int i =0;i<list.length;i++){
            if(map.get(sum - list[i]) != null){
                int a = map.get(sum-list[i]);
                int b = i;
                map.remove(sum - list[i]);
//                System.out.println((sum-list[i]) + " " + list[i]);
                ans[0] = a;
                ans[1] = b;
                return ans;
//                cnt++;
//                continue;
            }
            map.put(list[i], i);
        }
        if(cnt == 0) return null;
        return null;
    }
}