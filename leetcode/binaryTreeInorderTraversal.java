/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    List<Integer> alist = new LinkedList<>();
    
    public List<Integer> inorderTraversal(TreeNode root) {
        dfs(root);
        return alist;
    }
    public void dfs(TreeNode root){
        if(root == null) return;
        if(root.left != null) dfs(root.left);
        System.out.println(root.val);
        alist.add(root.val);
        if(root.right != null) dfs(root.right);
        
    }
}