import java.util.*;

class Solution {
    static Deque<Character> tmp;
    static ArrayList<String> res;
    static HashMap<Character, Integer> sH;
    int len;

    public void dfs(){
        if(tmp.size() == len){
            String ts = "";
            for(char x: tmp) ts += x;
            res.add(ts);
            return;
        }
        else{
            for(char key: sH.keySet()){
                if(sH.get(key) == 0) continue;
                tmp.addFirst(key);
                tmp.addLast(key);
                sH.put(key, sH.get(key)-2);
                dfs();
                tmp.pollFirst();
                tmp.pollLast();
                sH.put(key, sH.get(key)+2);
            }
        }
    }


	public String[] solution(String s){
        tmp = new LinkedList<>();
        res = new ArrayList<>();
        sH = new HashMap<>();
        len = s.length();
        for(char x: s.toCharArray()){
            sH.put(x, sH.getOrDefault(x, 0)+1);
        }
        int odd = 0;
        char mid = '#';
        for(char key: sH.keySet()){
            if(sH.get(key) % 2 == 1){
                mid = key;
                odd++;
            }
        }
        if(odd>1) return new String[]{};
        if(mid != '#'){
            tmp.add(mid);
            sH.put(mid, sH.get(mid)-1);
        }
        dfs();
        String [] answer = new String[res.size()];
        for(int i=0;i<res.size();i++) answer[i] = res.get(i);
        return answer;
    }

	public static void main(String[] args){
		Solution T = new Solution();
		System.out.println(Arrays.toString(T.solution("aaaabb")));	
		System.out.println(Arrays.toString(T.solution("abbcc")));
		System.out.println(Arrays.toString(T.solution("abbccee")));
		System.out.println(Arrays.toString(T.solution("abbcceee")));
		System.out.println(Arrays.toString(T.solution("ffeffaae")));
	}
}