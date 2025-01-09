class Solution {

    static int [] visited; 
    static List<List<Integer>> answer;
    static int size;

    public static List<List<Integer>> permute(int[] nums) {
        answer = new ArrayList<>();
        size = nums.length;
        visited = new int[nums.length];
        Arrays.fill(visited,-11);
        dfs(0, nums);
        return answer;
    }
    static void dfs(int index, int [] nums){
        if(visited[index] != -11){
            int count = 0;
            List<Integer> currentOrder = new ArrayList<>();
            for(int i=0;i<visited.length;i++){
                if(visited[i] != -11){
                    currentOrder.add(visited[i]);
                    count++;
                }
            }
            if(count == visited.length){
               answer.add(currentOrder);
            }
            return;
        }

        for(int i=index;i<nums.length;i++){
            if(visited[i] != -11){
                visited[i] = nums[i];
                dfs(i, nums);
                visited[i] = -11;
            }
        }
    }
    public static void main(String[] args) {
        permute([1,2,3]);
    }
}