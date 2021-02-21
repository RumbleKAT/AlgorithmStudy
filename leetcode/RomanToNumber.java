class Solution {
    public int romanToInt(String s) {
        ArrayList<Node> alist = new ArrayList<>();
        alist.add(new Node("I",1));
        alist.add(new Node("IV",4));
        alist.add(new Node("V",5));
        alist.add(new Node("IX",9));
        alist.add(new Node("X",10));
        alist.add(new Node("XL",40));
        alist.add(new Node("L",50));
        alist.add(new Node("XC",90));
        alist.add(new Node("C",100));
        alist.add(new Node("CD",400));
        alist.add(new Node("D",500));
        alist.add(new Node("CM",900));
        alist.add(new Node("M",1000));

        Collections.sort(alist);
        int ans = 0;
        
        while(true){
            if(s.equals("")){
                break;
            }
            
            for(Node node : alist){
                if(s.startsWith(node.s)){
                    ans += node.val;
                    s = s.substring(s.indexOf(node.s)+node.s.length(),s.length());       
                }
            }
            System.out.println(s);
        }
        System.out.println(ans);        
        
        return ans;
    }
}
class Node implements Comparable<Node>{
    public String s;
    public int val;
    
    Node(String s, int val){
        this.s = s;
        this.val = val;
    }    
    @Override
    public int compareTo(Node n1){
        return n1.val - this.val;
    }
}
