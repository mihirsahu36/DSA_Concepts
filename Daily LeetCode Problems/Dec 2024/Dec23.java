/*2471. Minimum Number of Operations to Sort a Binary Tree by Level
You are given the root of a binary tree with unique values.
In one operation, you can choose any two nodes at the same level and swap their values.
Return the minimum number of operations needed to make the values at each level sorted in a strictly increasing order.
The level of a node is the number of edges along the path between it and the root node.

Example 1:
Input: root = [1,4,3,7,6,8,5,null,null,null,null,9,null,10]
Output: 3
Explanation:
- Swap 4 and 3. The 2nd level becomes [3,4].
- Swap 7 and 5. The 3rd level becomes [5,6,8,7].
- Swap 8 and 7. The 3rd level becomes [5,6,7,8].
We used 3 operations so return 3.
It can be proven that 3 is the minimum number of operations needed.

Example 2:
Input: root = [1,3,2,7,6,5,4]
Output: 3
Explanation:
- Swap 3 and 2. The 2nd level becomes [2,3].
- Swap 7 and 4. The 3rd level becomes [4,6,5,7].
- Swap 6 and 5. The 3rd level becomes [4,5,6,7].
We used 3 operations so return 3.
It can be proven that 3 is the minimum number of operations needed.

Example 3:
Input: root = [1,2,3,4,5,6]
Output: 0
Explanation: Each level is already sorted in increasing order so return 0.

Constraints:
The number of nodes in the tree is in the range [1, 10^5].
1 <= Node.val <= 10^5
All the values of the tree are unique.*/

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
    public int minimumOperations(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int res = 0;
        while(!queue.isEmpty()){
            int n = queue.size();
            List<Integer> vec = new ArrayList<>();

            for(int i=0;i<n;i++){
                TreeNode temp = queue.poll();
                vec.add(temp.val);

                if(temp.left != null){ // add left child to the queue if exists
                    queue.add(temp.left);
                }

                if(temp.right != null){ // add right child to the queue if exists
                    queue.add(temp.right);
                }
            }
            res += countMinSwap(vec);  // count the minimum swaps to sort the current level
        }
        return res;
    }
    public int countMinSwap(List<Integer> vec){
        int swaps = 0;
        List<Integer> sortedVec = new ArrayList<>(vec); // make a copy of vec
        Collections.sort(sortedVec); // sorted copy of vec

        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0;i<vec.size();i++){ // store the element with its index
            map.put(vec.get(i), i);
        }
        for(int i=0;i<vec.size();i++){ 
            if(vec.get(i).equals(sortedVec.get(i))){ // element at ith index is same at vec and sortedVec
                continue;
            }
            // if the element at index ith is not equal in vec and sortedVec
            int currIdx = map.get(sortedVec.get(i));
            map.put(vec.get(i), currIdx);
            map.put(vec.get(currIdx), i);
            Collections.swap(vec, currIdx, i);
            swaps++;
        }
        return swaps;
    }
}
