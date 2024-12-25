/*515. Find Largest Value in Each Tree Row
Given the root of a binary tree, return an array of the largest value in each row of the tree (0-indexed).

Example 1:
Input: root = [1,3,2,5,3,null,9]
Output: [1,3,9]

Example 2:
Input: root = [1,2,3]
Output: [1,3]

Constraints:
The number of nodes in the tree will be in the range [0, 104].
-2^31 <= Node.val <= 2^31 - 1*/

// BFS Approach
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
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null){
            return res;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root); // insert the root to the queue

        while(!queue.isEmpty()){ // BFS search for maxElement in each level
            int size = queue.size();
            int maxElement = Integer.MIN_VALUE;

            for(int i=0;i<size;i++){
                TreeNode node = queue.poll(); // remove the node from the queue
                maxElement = Math.max(maxElement, node.val); // find maxElement

                if(node.left != null){ // insert the node's left child to the queue if present
                    queue.offer(node.left);
                }
                if(node.right != null){ // insert the node's right child to the queue if present
                    queue.offer(node.right);
                }
            }
            res.add(maxElement); // add the maxElement to the result list
        }
        return res;
    }
}

// DFS Approach
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
    private List<Integer> res = new ArrayList<>(); // to store result

    public List<Integer> largestValues(TreeNode root) { // return the result list
        dfs(root, 0);
        return res;
    }

    private void dfs(TreeNode root, int depth){ // dfs approach
        if(root == null){ // if root is null then there is no node to process
            return;
        }
        if(depth == res.size()){ // if we haev not visited the depth
            res.add(root.val);
        }else{ // if we have already visited the depth then find max of that depth's previous value and current node value
            res.set(depth, Math.max(res.get(depth), root.val));
        }

        dfs(root.left, depth + 1);
        dfs(root.right, depth + 1);
    }
}
