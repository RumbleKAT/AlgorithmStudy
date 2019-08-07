import java.util.*;
import java.io.*;

class Main{

    /*
    #1 3 4 4 
    #2 2 2 4 7 2 
    #3 0 0 
    */
    static int TC,N,Q;
    static long [] seg;
    static Person [] person;
    static Person [] person_2;
    static PriorityQueue <Person> pq;
    static long ans;

    public static void main(String [] args ) throws Exception{
        System.setIn(new FileInputStream("./sample.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer token = new StringTokenizer(br.readLine());

        TC = Integer.parseInt(token.nextToken());

        for(int t=1;t<=TC;t++){
            
            token = new StringTokenizer(br.readLine());
            N = Integer.parseInt(token.nextToken());
            Q = Integer.parseInt(token.nextToken());
            ans = 0;
            person = new Person[N+1];
            person_2 = new Person[N+1];

            token = new StringTokenizer(br.readLine());
            for(int i = 1;i<=N;i++){
                long tmp = Long.parseLong(token.nextToken());
                person[i] = new Person(i,tmp);
                person_2[i] = new Person(i,tmp);
            }
            
            int H = 1;
            while(H <= N) H *= 2;
            seg = new long [H*2];

            // System.out.println(" H : " + H);
            //내림차순 정렬
            Arrays.sort(person, 1, N+1, new Comparator<Person>(){
                @Override
                public int compare(Person p1, Person p2){
                    if(p1.val > p2.val){
                        return -1;
                    }else if(p1.val == p2.val){
                        return 0;
                    }else return 1;
                }
            });

            Query [] queries = new Query[Q+1];
            Query [] queries_2 = new Query[Q+1];

            int s = -1;
            int e = -1;

            // System.out.println("Q : " + Q);

            for(int i = 1;i<=Q;i++){
                ans = 0;
                token = new StringTokenizer(br.readLine());
                s = Integer.parseInt(token.nextToken());
                e = Integer.parseInt(token.nextToken());
                long val = Long.parseLong(token.nextToken());

                queries[i] = new Query(s, e, val,i);
                queries_2[i] = queries[i];                
            }

            Arrays.sort(queries,1,Q+1,new Comparator<Query>(){
                @Override
                public int compare(Query q1 , Query q2){
                    if(q1.val > q2.val){
                        return -1;
                    }else if(q1.val == q2.val) return 0;
                    else return 1;
                }
            });

            //System.out.println("---------------");

            int idx = 1;
            for(int i =1;i<=Q;i++){
                // System.out.println(idx + " " + queries[i].val);
                for(int j=idx;j<=N;j++){
                    if(person[j].val > queries[i].val){
                        // System.out.println(person[j].idx + " " + person[j].val);
                        // System.out.println(idx + " " + person[j].idx + " " +person[j].val);
                        // System.out.println(person[j].val);
                        update(person[j].idx, 1, 1, 1, H);
                        idx++;
                    }else{
                        break;
                    }
                }
                // System.out.println(queries[i].s + " "+ queries[i].e + " " + queries[i].val);
                        
                // for(int x = 1;x<=H;x++){
                //     System.out.print(seg[x] + " ");
                // }System.out.println();
                queries[i].result = sum(1, 1, H, queries[i].s, queries[i].e);
                
                // System.out.println("ans : " +sum(1, 1, H, queries[i].s, queries[i].e) + " ");
                //System.out.println("idx : " + idx);
            }

            Arrays.sort(queries,1,Q+1,new Comparator<Query>() {
                @Override
                public int compare(Query q1 ,Query q2){
                    if(q1.idx > q2.idx){
                        return 1;
                    }else if(q1.idx == q2.idx) return 0;
                    else return -1;
                }
            });

            //System.out.print("#"+t+ " ");
            bw.write("#"+t+ " ");

            for(int i = 1;i<=Q;i++){
                bw.write(queries[i].result + " ");
            }

            bw.newLine();
            bw.flush();

/*

     7
   5   2
  3 2 2 0
1 2 1 1 2 0
170 171 

*/

            // for(int i = 1;i<=H;i++){
            //     System.out.print(seg[i] + " ");
            // }System.out.println();

            // for(int i =1;i<=Q;i++){
            //     //System.out.println(queries_2[i].s + " " +queries_2[i].e + " " +queries_2[i].val);
            //     System.out.print(sum(1, 1, H, queries[i].s, queries[i].e) + " ");
            // }System.out.println();

        }
    }

    static long sum(int node, int start, int end, int left, int right){
        if(start > right || end < left) return 0;
        if(left <= start && end <= right) return seg[node];
        int mid = (start+end)/2;
        return sum(node*2, start, mid, left, right)+sum(node*2+1, mid+1, end, left, right);
    }

    static long update(int index, int cnt, int node, int left, int right){
        if(index < left || index >  right) return seg[node];
        if(left == right) return seg[node] += cnt;
        int mid = (left+right)/2;
        return seg[node] = update(index, cnt, node*2,left, mid) + update(index, cnt, node*2+1,mid+1, right);
    }

}

class Query{
    public int s;
    public int e;
    public long val;
    public int idx = 0;
    public long result = 0;

    Query(int s, int e, long val, int idx){
        this.s = s;
        this.e = e;
        this.val = val;
        this.idx = idx;
    }
}

class Person implements Comparable <Person>{
    public int idx;
    public long val;
    
    Person(int idx, long val){
        this.idx = idx;
        this.val = val;
    }

    @Override //키에 대해서 내림차순
    public int compareTo(Person p1){
        if(this.val > p1.val){
            return -1;
        }else if(this.val == p1.val) return 0;
        else return 1;
    }
}