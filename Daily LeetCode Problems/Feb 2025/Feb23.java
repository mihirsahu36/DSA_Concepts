/*889. Construct Binary Tree from Preorder and Postorder Traversal
Given two integer arrays, preorder and postorder where preorder is the preorder traversal of a binary tree
of distinct values and postorder is the postorder traversal of the same tree, reconstruct and return the binary tree.
If there exist multiple answers, you can return any of them.

Example 1:
Input: preorder = [1,2,4,5,3,6,7], postorder = [4,5,2,6,7,3,1]
Output: [1,2,3,4,5,6,7]

Example 2:
Input: preorder = [1], postorder = [1]
Output: [1]

Constraints:
1 <= preorder.length <= 30
1 <= preorder[i] <= preorder.length
All the values of preorder are unique.
postorder.length == preorder.length
1 <= postorder[i] <= postorder.length
All the values of postorder are unique.
It is guaranteed that preorder and postorder are the preorder traversal and postorder traversal of the same binary tree.*/

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
    public TreeNode solve(int prestart, int poststart, int preend, int []preorder, int []postorder, HashMap<Integer, Integer> map){
        if(prestart > preend){
            return null;
        }

        TreeNode root = new TreeNode(preorder[prestart]);
        if(prestart == preend){
            return root;
        }

        int nextNode = preorder[prestart + 1];
        int j = map.get(nextNode);
        int num = j - poststart + 1;

        root.left = solve(prestart + 1, poststart, prestart + num, preorder, postorder, map);
        root.right = solve(prestart + num + 1, j + 1, preend, preorder, postorder, map);

        return root;
    }
    
    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        int n = preorder.length;
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int i=0;i<n;i++){
            map.put(postorder[i], i);
        }

        return solve(0, 0, n - 1, preorder, postorder, map);
    }
}
