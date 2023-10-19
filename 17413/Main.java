import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();
        StringBuilder sb = new StringBuilder();
        StringBuilder temp = new StringBuilder();

        int i = 0;
        while (i < input.length()) {
            if (input.charAt(i) == '<') {
                if (temp.length() > 0) {
                    sb.append(temp.reverse());
                    temp.setLength(0);
                }
                while (i < input.length() && input.charAt(i) != '>') {
                    temp.append(input.charAt(i));
                    i++;
                }
                temp.append(input.charAt(i));
                sb.append(temp);
                temp.setLength(0);
                i++;
            } else if (input.charAt(i) == ' ' || i == input.length() - 1) {
                if (i == input.length() - 1 && input.charAt(i) != ' ')
                    temp.append(input.charAt(i));
                sb.append(temp.reverse()).append(" ");
                temp.setLength(0);
                i++;
            } else {
                temp.append(input.charAt(i));
                i++;
            }
        }

        System.out.println(sb.toString().trim());
    }
}
