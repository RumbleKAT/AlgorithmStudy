import java.util.HashMap;
import java.util.Map;
import java.util.*;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String [] word = {"frodo", "front", "frost", "frozen", "frame", "kakao"};
		String [] queries = {"fro??", "????o", "fr???", "fro???", "pro?"};
		System.out.println(Arrays.toString(solution(word,queries)));
		
	}

	public static int[] solution(String[] words, String[] queries) {
		int[] answer = new int [queries.length];
		
		//prefix
		Trie [] prefixTries = new Trie[10001];
		
		//postfix
		Trie [] postfixTries = new Trie[10001];
		
		for(String word : words) {
			int size = word.length();
			
			if(prefixTries[size] == null) {
				prefixTries[size] = new Trie();
			}
			prefixTries[size].insert(word.toCharArray());
			
			word = (new StringBuffer(word)).reverse().toString();
			if(postfixTries[size] == null) {
				postfixTries[size] = new Trie();
			}
			postfixTries[size].insert(word.toCharArray());	
		}
		int idx = 0;
		for(int i=0;i<queries.length;i++) {
			String query = queries[i];
			if(query.indexOf('?') == 0) {
				//prefix
				try {
					query = (new StringBuffer(query)).reverse().toString();
					answer[i] = postfixTries[query.length()].search(query.toCharArray());
				}catch(Exception e) {
					continue;
				}
			}else {
				//postfix
				try {
					answer[i] = prefixTries[query.length()].search(query.toCharArray());		
				}catch(Exception e){
					continue;
				}
				
			}
		}
		
		
		return answer;
	}

}
class Trie{
	int count;
	Trie [] child;
	
	Trie() {
		child = new Trie[26];
		count = 0;
	}
	
	public void insert(char [] word) {
		Trie current = this;
		for(char no : word) {
			++current.count;
			if(current.child[no-'a'] == null) {
				current.child[no-'a'] = new Trie();
			}
			current = current.child[no-'a'];
		}
	}
	
	public int search(char [] query) {
		Trie current = this;
		for(char no : query) {
			if(no == '?') {
				return current.count;
			}
			if(current.child[no-'a'] != null) {
				current = current.child[no-'a'];
			}else {
				return 0;
			}
		}
		return current.count;
	}
}