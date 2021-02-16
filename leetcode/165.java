class Solution {
    public static int compareVersion(String version1, String version2) {
        String [] str = version1.split("\\.");
        String [] str2 = version2.split("\\.");

        int lenX = str.length;
        int lenY = str2.length;

        int max = Math.max(lenX, lenY);

        String [] ans = new String[max];
        String [] ans2 = new String[max];

        for(int i=0;i<max;i++){
            if(str.length <= i){
                ans[i] = "0";
            }else{
                ans[i] = str[i];
            }
        }
        for(int i=0;i<max;i++){
            if(str2.length <= i){
                ans2[i] = "0";
            }else{
                ans2[i] = str2[i];
            }
        }
        int cnt = 0;

        for(int i=0;i<max;i++){
            int x = Integer.parseInt(ans[i]);
            int y = Integer.parseInt(ans2[i]);

            if(x != y){
                if(x-y > 0){
                    cnt = 1;
                }else{
                    cnt = -1;
                }
               break;
            }
        }

        return cnt;
    }
}