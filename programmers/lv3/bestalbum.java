import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        int[] answer = {};
        HashMap<String,Integer> hashmap = new HashMap<>();
        for(int i=0;i<genres.length;i++){
            hashmap.put(genres[i], hashmap.getOrDefault(genres[i],0)+plays[i]);
        }        

        ArrayList<String> alist = new ArrayList<>();
        while(hashmap.size() != 0){
            int max = -1;
            String maxKey = "";
            for(String key : hashmap.keySet()){
                int tmp_cnt = hashmap.get(key);
                if(max < tmp_cnt){
                    max = tmp_cnt;
                    maxKey = key;
                }
            }
            alist.add(maxKey);
            hashmap.remove(maxKey);        
        }

        ArrayList<Integer> result = new ArrayList<>();
        for(String genre: alist){
            PriorityQueue<Node> pq = new PriorityQueue<>();   
            
            for(int i = 0;i<genres.length;i++){
                if(genres[i].equals(genre)){
                    pq.add(new Node(i,plays[i]));
                }
            }
            
            result.add(pq.poll().idx);
            if(!pq.isEmpty()) result.add(pq.poll().idx);
            
        }
        answer = new int[result.size()];
        
        int idx = 0;
        for(int res : result){
            answer[idx] = res;
            idx++;
        }
    
        return answer;
    }
}
class Node implements Comparable<Node>{
    public int idx;
    public int val;
    
    Node(int idx, int val){
        this.idx = idx;
        this.val = val;
    }
    
    @Override
    public int compareTo(Node n1){
        return n1.val - this.val;
    }
}