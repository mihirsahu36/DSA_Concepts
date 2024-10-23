/*2641. Cousins in Binary Tree II
Given the root of a binary tree, replace the value of each node in the tree with the sum of all its cousins' values.
Two nodes of a binary tree are cousins if they have the same depth with different parents.
Return the root of the modified tree.
Note that the depth of a node is the number of edges in the path from the root node to it.

Example 1:
Input: root = [5,4,9,1,10,null,7]
Output: [0,0,0,7,7,null,11]
Explanation: The diagram above shows the initial binary tree and the binary tree after changing the value of each node.
- Node with value 5 does not have any cousins so its sum is 0.
- Node with value 4 does not have any cousins so its sum is 0.
- Node with value 9 does not have any cousins so its sum is 0.
- Node with value 1 has a cousin with value 7 so its sum is 7.
- Node with value 10 has a cousin with value 7 so its sum is 7.
- Node with value 7 has cousins with values 1 and 10 so its sum is 11.

Example 2:
Input: root = [3,1,2]
Output: [0,0,0]
Explanation: The diagram above shows the initial binary tree and the binary tree after changing the value of each node.
- Node with value 3 does not have any cousins so its sum is 0.
- Node with value 1 does not have any cousins so its sum is 0.
- Node with value 2 does not have any cousins so its sum is 0.
 
Constraints:
The number of nodes in the tree is in the range [1, 105].
1 <= Node.val <= 104*/


//METHOD 1 WITH 2 PASS

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
    public TreeNode replaceValueInTree(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        ArrayList<Integer> levelSum = new ArrayList<>();
        //pass1
        while(!queue.isEmpty()){
            int size = queue.size();
            int sum = 0;
            for(int i=0;i<size;i++){
                TreeNode node = queue.poll();
                sum += node.val;
                if(node.left != null){
                    queue.offer(node.left); //storing node in queue
                }
                if(node.right != null){
                    queue.offer(node.right); //storing node in queue
                }
            }
            levelSum.add(sum); // storing totalSum of the level
        }
        //pass2
        int level = 0;
        queue.offer(root);
        root.val = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0;i<size;i++){
                TreeNode node = queue.poll();
                int sum = 0;
                if(node.left != null){
                    sum += node.left.val; // storing left sibling sum
                }
                if(node.right != null){
                    sum += node.right.val; // storing right sibling sum
                }
                //sum += node.val;
                if(node.left != null){
                    node.left.val = levelSum.get(level + 1) - sum; //totalSum of level - siblingSum of level
                    queue.offer(node.left);
                }
                if(node.right != null){
                    node.right.val = levelSum.get(level + 1) - sum; //totalSum of level - siblingSum of level
                    queue.offer(node.right);
                }
            }
            level++; // to maintain the level of next level
        }
        return root;
    }
}



//METHOD 2 WITH 1 PASS

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
    public TreeNode replaceValueInTree(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int prevSum = root.val; //totalSum
        while(!queue.isEmpty()){
            int size = queue.size();
            int currSum = 0;
            for(int i=0;i<size;i++){
                TreeNode node = queue.poll();
                node.val = prevSum - node.val; // totalSum - siblingSum
                int sibSum = 0;
                if(node.left != null){
                    sibSum += node.left.val; // storing left sibling sum
                }
                if(node.right != null){
                    sibSum += node.right.val; // storing right sibling sum
                }
                //sum += node.val;
                if(node.left != null){
                    currSum += node.left.val; // storing next level sum
                    node.left.val = sibSum; // storing siblingSum
                    queue.offer(node.left);
                }
                if(node.right != null){
                    currSum += node.right.val; // storing next level sum
                    node.right.val = sibSum; // storing siblingSum
                    queue.offer(node.right);
                }
            }
            prevSum = currSum;
        }
        return root;
    }
}


