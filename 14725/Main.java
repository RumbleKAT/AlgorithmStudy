import java.io.*;
import java.util.*;

class TrieNode {
    HashMap<String, TrieNode> children = new HashMap<>();
    boolean isEndOfWord;

    public HashMap<String, TrieNode> getChildren() {
        return children;
    }

    public boolean isEndOfWord() {
        return isEndOfWord;
    }

    public void setEndOfWord(boolean endOfWord) {
        isEndOfWord = endOfWord;
    }
}


public class Main {
    public static TrieNode root;

    public Main() {
        root = new TrieNode();
    }

    public void insert(String[] words) {
        TrieNode node = root;
        for (String word : words) {
            if (!node.getChildren().containsKey(word)) {
                node.getChildren().put(word, new TrieNode());
            }
            node = node.getChildren().get(word);
        }
        node.setEndOfWord(true);
    }
    

    public void displayWords(TrieNode node, String prefix) {
        if (node == null) return;
        
        List<String> sortedKeys = new ArrayList<>(node.getChildren().keySet());
        Collections.sort(sortedKeys);
        
        for (String childKey : sortedKeys) {
            System.out.println(prefix + childKey);
            displayWords(node.getChildren().get(childKey), prefix + "--");
        }
    }
    
    
    static int N;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());
        Main trie = new Main();
        
        for(int i = 1; i <= N; i++) {
            token = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(token.nextToken());
            String[] words = new String[cnt];
            for(int j = 0; j < cnt; j++) {
                words[j] = token.nextToken();
            }
            trie.insert(words);
        }
        trie.displayWords(root,"");
    }
}
