import java.util.*;
import java.io.*;


class Main{
    static int N;

    static PriorityQueue <Integer> pq;

    public static void main(String [] args) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int i=0;i<N;i++){
            token = new StringTokenizer(br.readLine());
            int currentNum = Integer.parseInt(token.nextToken());

            if(currentNum == 0){
                if(pq.isEmpty()){
                    bw.write('0');
                }else{
                    bw.write(pq.poll().toString());
                }
                bw.newLine();
                bw.flush();
            }else{
                pq.add(currentNum);               
            }
        }
    }
}