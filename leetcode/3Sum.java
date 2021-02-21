import java.util.*;

public class Solution {
    public static void main(String [] args) throws Exception{
        int [] param = {-1,0,1,2,-1,-4};
        threeSum(param);
    }
    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<List<Integer>>();

        for(int i=0;i<nums.length;i++){
            if(i >0 && nums[i] == nums[i-1]) continue;

            int left = i+1;
            int right = nums.length-1;

            while(left < right){
                int sum = nums[left] + nums[right] + nums[i];
                if(sum > 0) right--;
                else if(sum < 0) left++;
                else{
                    ArrayList<Integer> ar = new ArrayList<>();
                    ar.add(nums[left]);
                    ar.add(nums[right]);
                    ar.add(nums[i]);
                    ans.add(ar);
                    System.out.println(Arrays.toString(ar.toArray()));

                    while(nums.length > left+1 && nums[left] == nums[left+1])left++;
                    while(0 <= right-1 && nums[right] == nums[right-1]) right--;
                    left++;
                    right--;
                }
            }
        }

        return ans;
    }
}
