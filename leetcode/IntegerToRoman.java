import java.util.*;

class Solution {
    public String intToRoman(int num) {
        String str = "";

        ArrayList<Node> nodes = new ArrayList<>();
        nodes.add(new Node("I",1));
        nodes.add(new Node("IV",4));
        nodes.add(new Node("V",5));
        nodes.add(new Node("IX",9));
        nodes.add(new Node("X",10));
        nodes.add(new Node("XL",40));
        nodes.add(new Node("L",50));
        nodes.add(new Node("XC",90));
        nodes.add(new Node("C",100));
        nodes.add(new Node("CD",400));
        nodes.add(new Node("D",500));
        nodes.add(new Node("CM",900));
        nodes.add(new Node("M",1000)); 

        Collections.sort(nodes,new Comparator<Node>(){
            @Override
            public int compare(Node n1, Node n2){
                return n2.idx - n1.idx;
            }
        });
        
        // for(Node current : nodes){
        //     System.out.println(current.c + " " + current.idx);
        // }
        
        while(num != 0){
            for(Node current : nodes){
                // System.out.println(current.c + " " + current.idx);
                if(num - current.idx >= 0){
                    str += current.c;
                    num -= current.idx;
                    break;
                }
            }
        }
        
        return str;        
        
        //divide 10 , 5 
    }
}
class Node{
    public int idx;
    public String c;
    
    Node(String c, int idx){
        this.idx = idx;
        this.c = c;
    }
}