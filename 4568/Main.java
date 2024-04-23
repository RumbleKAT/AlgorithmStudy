import java.util.*;
import java.io.*;
public class Main {

    static LinkedHashMap<Character, Boolean> cache = new LinkedHashMap<>();
    static int capacity = 0;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int idx = 1;
        
        while (true) {
            String line = br.readLine();
            if (line == null || line.isEmpty()) break;

            String[] parts = line.split(" ");
            capacity = Integer.parseInt(parts[0]);
            if (capacity == 0) break;

            cache.clear();
            System.out.println("Simulation " + idx);

            String operations = parts[1];
            for (char op : operations.toCharArray()) {
                if (op == '!') {
                    printCache();
                } else {
                    accessCache(op);
                }
            }
            idx++;
        }
    }

    public static void accessCache(char data) {
        if (cache.containsKey(data)) {
            cache.remove(data);
        }
        else if (cache.size() == capacity) {
            char oldest = cache.keySet().iterator().next();
            cache.remove(oldest);
        }
        cache.put(data, true);
    }

    public static void printCache() {
        for (char c : cache.keySet()) {
            System.out.print(c);
        }
        System.out.println();
    }
}