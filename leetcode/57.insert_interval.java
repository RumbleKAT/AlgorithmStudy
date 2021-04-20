class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList();
        int i=0, n = intervals.length;
        
        while(i<n && intervals[i][1] < newInterval[0]) result.add(intervals[i++]);
        
        int [] mI = newInterval;
        
        while(i<n && intervals[i][0] <= newInterval[1]){
            mI[0] = Math.min(mI[0], intervals[i][0]);
            mI[1] = Math.max(mI[1], intervals[i++][1]);
        }
        
        result.add(mI);
        
        while(i<n) result.add(intervals[i++]);
        
        return result.toArray(new int[result.size()][2]);
        
    }
}