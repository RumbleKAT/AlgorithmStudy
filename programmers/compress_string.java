public class Main {

    public static void main(String[] args) {
        System.out.println(solution("abcabcabcabcdededededede"));
    }
    public static int solution(String s){
        int answer = Integer.MAX_VALUE;
        int len = s.length();

        if(s.length()==1) return 1;

        for(int r=1;r<=len/2;r++){
            String pattern = s.substring(0,r);
            int compLen = 0;
            int cnt = 1;
            String reStr = "";
            for(int i=r;i<=s.length()-r;i+=r){
                if(pattern.equals(s.substring(i,i+r))){
                    cnt++;
                }else{
                    if(cnt > 1){
                        reStr += cnt+"";
                    }
                    reStr += pattern;
                    pattern = s.substring(i,i+r);
                    cnt = 1;
                }
            }
            if(cnt > 1){
                reStr += ""+cnt;
            }
            reStr += pattern;
            int div = s.length() % r;
            answer = Math.min(answer, reStr.length()+div);
        }
        return answer;
    }
}
