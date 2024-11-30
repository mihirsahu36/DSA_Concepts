/*2097. Valid Arrangement of Pairs
You are given a 0-indexed 2D integer array pairs where pairs[i] = [starti, endi]. An arrangement of pairs is valid if for every index i where 1 <= i < pairs.length, we have endi-1 == starti.
Return any valid arrangement of pairs.
Note: The inputs will be generated such that there exists a valid arrangement of pairs.

Example 1:
Input: pairs = [[5,1],[4,5],[11,9],[9,4]]
Output: [[11,9],[9,4],[4,5],[5,1]]
Explanation:
This is a valid arrangement since endi-1 always equals starti.
end0 = 9 == 9 = start1 
end1 = 4 == 4 = start2
end2 = 5 == 5 = start3

Example 2:
Input: pairs = [[1,3],[3,2],[2,1]]
Output: [[1,3],[3,2],[2,1]]
Explanation:
This is a valid arrangement since endi-1 always equals starti.
end0 = 3 == 3 = start1
end1 = 2 == 2 = start2
The arrangements [[2,1],[1,3],[3,2]] and [[3,2],[2,1],[1,3]] are also valid.

Example 3:
Input: pairs = [[1,2],[1,3],[2,1]]
Output: [[1,2],[2,1],[1,3]]
Explanation:
This is a valid arrangement since endi-1 always equals starti.
end0 = 2 == 2 = start1
end1 = 1 == 1 = start2

Constraints:
1 <= pairs.length <= 10^5
pairs[i].length == 2
0 <= starti, endi <= 10^9
starti != endi
No two pairs are exactly the same.
There exists a valid arrangement of pairs.*/

class Solution {
    public int[][] validArrangement(int[][] pairs) {
        Map<Integer, List<Integer>> adj = new HashMap<>(); // adjacency list
        Map<Integer, Integer> indegree = new HashMap<>(); // indegree
        Map<Integer, Integer> outdegree = new HashMap<>(); // outdegree

        for(int []edge : pairs){ // [u,v] u->v
            int u = edge[0];
            int v = edge[1];

            adj.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
            outdegree.put(u, outdegree.getOrDefault(u, 0) + 1); // calculating the outdegree
            indegree.put(v, indegree.getOrDefault(v, 0) + 1); // calculating the indegree
        }
        int startNode = pairs[0][0];
        for(int node : adj.keySet()){ // starting node of euler path, if outdegree - indegree == 1 then that node is starting node and if indegree - outdegree == 1 then that is the ending node
            if(outdegree.getOrDefault(node, 0) - indegree.getOrDefault(node, 0) == 1){
                startNode = node;
                break;
            }
        }
        List<Integer> eulerPath = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(startNode);

        while(!stack.isEmpty()){ // add the neighbour to the list and remove it from the adjacency list
            int curr = stack.peek();
            if(adj.containsKey(curr) && !adj.get(curr).isEmpty()){
                int neighbour = adj.get(curr).remove(adj.get(curr).size() - 1);
                stack.push(neighbour);
            }else{
                eulerPath.add(curr);
                stack.pop();
            }
        }

        Collections.reverse(eulerPath); // reversing the list as the element are in reverse order as we pop it from top of the stack
        int [][]res = new int [eulerPath.size()-1][2];
        for(int i=0;i<eulerPath.size()-1;i++){ // making the list into the pair
            res[i][0] = eulerPath.get(i);
            res[i][1] = eulerPath.get(i+1);
        }
        return res;
    }
}
