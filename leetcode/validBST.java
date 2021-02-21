class Solution{    
    public boolean isValidBST(TreeNode root) {
        return isValid(root,Long.MIN_VALUE, Long.MAX_VALUE);
    }
    
    public boolean isValid(TreeNode root, long left, long right){
        if(root == null) return true;
        long cur = (long) root.val;
        
        if(cur <= left || cur >= right) return false;
        
        return isValid(root.left, left, cur) && isValid(root.right,cur,right);
    }
}