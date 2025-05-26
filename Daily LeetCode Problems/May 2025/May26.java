/*1857. Largest Color Value in a Directed Graph
There is a directed graph of n colored nodes and m edges. The nodes are numbered from 0 to n - 1.
You are given a string colors where colors[i] is a lowercase English letter representing the color of the ith node in this graph (0-indexed).
You are also given a 2D array edges where edges[j] = [aj, bj] indicates that there is a directed edge from node aj to node bj.
A valid path in the graph is a sequence of nodes x1 -> x2 -> x3 -> ... -> xk such that there is a directed edge from xi to xi+1 for every 1 <= i < k.
The color value of the path is the number of nodes that are colored the most frequently occurring color along that path.
Return the largest color value of any valid path in the given graph, or -1 if the graph contains a cycle.

Example 1:
Input: colors = "abaca", edges = [[0,1],[0,2],[2,3],[3,4]]
Output: 3
Explanation: The path 0 -> 2 -> 3 -> 4 contains 3 nodes that are colored "a" (red in the above image).

Example 2:
Input: colors = "a", edges = [[0,0]]
Output: -1
Explanation: There is a cycle from 0 to 0.

Constraints:
n == colors.length
m == edges.length
1 <= n <= 10^5
0 <= m <= 10^5
colors consists of lowercase English letters.
0 <= aj, bj < n*/

class Solution {
    public int largestPathValue(String colors, int[][] edges) {
        int n = colors.length();
        HashMap<Integer, List<Integer>> adj = new HashMap<>();
        int []indegree = new int[n];

        for(int []edge : edges){
            int u = edge[0];
            int v = edge[1];
            adj.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
            indegree[v]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        int [][]t = new int[n][26];

        for(int i=0;i<n;i++){
            if(indegree[i] == 0){
                queue.offer(i);
                t[i][colors.charAt(i) - 'a'] = 1;
            }
        }

        int res = 0;
        int countNodes = 0;

        while(!queue.isEmpty()){
            int u = queue.poll();
            countNodes++;

            for(int i=0;i<26;i++){
                res = Math.max(res, t[u][i]);
            }

            List<Integer> neighbors = adj.getOrDefault(u, new ArrayList<>());
            for(int v : neighbors){
                for(int i=0;i<26;i++){
                    int val = t[u][i] + (colors.charAt(v) - 'a' == i ? 1 : 0);
                    t[v][i] = Math.max(t[v][i], val);
                }

                indegree[v]--;
                if(indegree[v] == 0){
                    queue.offer(v);
                }
            }
        }

        return countNodes < n ? -1 : res;
    }
}
