import java.util.*;
import java.io.*;

class Main{

    static ArrayList<Integer> [] arr;
    static boolean [] visited;

    public static void main(String [] args) throws Exception{
        
        arr = new ArrayList [10];
        visited = new boolean [10];
        for(int i = 0;i < arr.length;i++){
            arr[i] = new ArrayList<>();
        }

        arr[7].add(1);
        arr[1].add(2);
        arr[2].add(3);
        arr[7].add(4);
        arr[4].add(5);
        arr[5].add(6);
        arr[6].add(8);



        Stack<Integer> st = new Stack<>();

        st.push(7);

        while(!st.isEmpty()){
            int current = st.pop();

            if(visited[current]){
                continue;
            }
            visited[current] = true;
            System.out.println("current : "  + current);

            for(int nextNode : arr[current]){
                if(!visited[nextNode]){
                    st.push(nextNode);
                }                
            }

        }
    }
}