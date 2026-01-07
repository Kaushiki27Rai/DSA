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
    
    private static final int MOD = 1_000_000_007;
    private List<Long> subtreeSums = new ArrayList<>();

    public int maxProduct(TreeNode root) {
        long totalSum = dfs(root);

        long maxProduct = 0;
        for (long sum : subtreeSums) {
            maxProduct = Math.max(maxProduct, sum * (totalSum - sum));
        }

        return (int) (maxProduct % MOD);
    }

    private long dfs(TreeNode node) {
        if (node == null) return 0;

        long left = dfs(node.left);
        long right = dfs(node.right);

        long currentSum = node.val + left + right;
        subtreeSums.add(currentSum);

        return currentSum;
    }

}