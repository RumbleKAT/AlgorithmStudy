import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int d = scanner.nextInt();
        int k = scanner.nextInt();
        int c = scanner.nextInt();

        int[] dish = new int[N];
        for(int i = 0; i < N; i++) {
            dish[i] = scanner.nextInt();
        }

        int[] sushi = new int[3001];
        Deque<Integer> dq = new ArrayDeque<>();
        int result = 0; 
        int cnt = 0;
        
        for(int i = 0; i < k; i++){
            int val = 0;
            dq.addLast(dish[i]);
            if(sushi[dish[i]] == 0){
                cnt++; 
            }
            sushi[dish[i]]++;
        }
        result = cnt;

        for(int i = 0; i < dish.length; i++) {
            int del = dq.removeFirst(); 
            sushi[del]--; 
            if(sushi[del] == 0) cnt--;

            dq.addLast(dish[(i+k)%N]); 
            if(sushi[dish[(i+k)%N]] == 0) cnt++; 
            sushi[dish[(i+k)%N]]++;

            if(sushi[c] == 0) {
                result = Math.max(result, cnt+1);
            }
            else result = Math.max(result, cnt);
        }
        System.out.println(result);
    }
}