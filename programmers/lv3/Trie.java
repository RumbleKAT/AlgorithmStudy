import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
	// write your code here
        String [] arr = {"123","456","789"};
        System.out.println(solution(arr));
    }

    static boolean solution(String [] arr){
        Arrays.sort(arr);
        Trie trie = new Trie();
        for(String temp : arr){
            if(!trie.contains(temp)){
                trie.insertNode(temp);
            }else{
                return false;
            }
        }

        return true;
    }
}

class TriNode{
    public boolean lastCheck;
    public HashMap<Character, TriNode> map;

    public TriNode(){
        this.lastCheck = false;
        map = new HashMap<>();
    }

    public HashMap<Character, TriNode> getMap(){
        return this.map;
    }

    public boolean isLastCheck(){
        return this.lastCheck;
    }

    public void setLastCheck(boolean lastCheck){
        this.lastCheck = lastCheck;
    }
}

class Trie{
    Trie(){
        rootNode = new TriNode();
    }
    private TriNode rootNode;

    public void insertNode(String word){
        TriNode current = this.rootNode;
        for(int i =0;i<word.length();i++){
            current = current.getMap().computeIfAbsent(word.charAt(i), character -> new TriNode());
        }
        current.setLastCheck(true);
    }

    //contains
    //1. 루트노드부터 순서대로 찾았을 때 있을때
    //2. 마지막 글자가 true일 때

    public boolean contains(String word){
       TriNode current = this.rootNode;
       for(int i =0;i<word.length();i++){
           char character = word.charAt(i);
           TriNode cur = current.getMap().get(character);
           if(cur == null){
               break;
           }
           current = cur;
       }
       return current.isLastCheck();
    }

}