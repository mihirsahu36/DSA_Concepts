/*2493. Divide Nodes Into the Maximum Number of Groups
You are given a positive integer n representing the number of nodes in an undirected graph. The nodes are labeled from 1 to n.
You are also given a 2D integer array edges, where edges[i] = [ai, bi] indicates that there is a bidirectional edge between nodes ai and bi.
Notice that the given graph may be disconnected.
Divide the nodes of the graph into m groups (1-indexed) such that:

Each node in the graph belongs to exactly one group.
For every pair of nodes in the graph that are connected by an edge [ai, bi], if ai belongs to the group with index x,
and bi belongs to the group with index y, then |y - x| = 1.
Return the maximum number of groups (i.e., maximum m) into which you can divide the nodes.
Return -1 if it is impossible to group the nodes with the given conditions.

Example 1:
Input: n = 6, edges = [[1,2],[1,4],[1,5],[2,6],[2,3],[4,6]]
Output: 4
Explanation: As shown in the image we:
- Add node 5 to the first group.
- Add node 1 to the second group.
- Add nodes 2 and 4 to the third group.
- Add nodes 3 and 6 to the fourth group.
We can see that every edge is satisfied.
It can be shown that that if we create a fifth group and move any node from the third or fourth group to it, at least on of the edges will not be satisfied.

Example 2:
Input: n = 3, edges = [[1,2],[2,3],[3,1]]
Output: -1
Explanation: If we add node 1 to the first group, node 2 to the second group, and node 3 to the third group to satisfy the first two edges,
we can see that the third edge will not be satisfied. It can be shown that no grouping is possible.

Constraints:
1 <= n <= 500
1 <= edges.length <= 10^4
edges[i].length == 2
1 <= ai, bi <= n
ai != bi
There is at most one edge between any pair of vertices.*/

class Solution {
    public boolean isBiPartite(HashMap<Integer, List<Integer>> adj, int curr, int []colors, int currColor){
        colors[curr] = currColor;
        for(int neighbour : adj.get(curr)){
            if(colors[neighbour] == colors[curr]){
                return false;
            }

            if(colors[neighbour] == -1){
                if(!isBiPartite(adj, neighbour, colors, 1 - currColor)){
                    return false;
                }
            }
        }
        return true;
    }

    public int bfs(HashMap<Integer, List<Integer>> adj, int currNode, int n) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];
        queue.add(currNode);
        visited[currNode] = true;

        int level = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int curr = queue.poll();
                
                for (int neighbour : adj.get(curr)) {
                    if (visited[neighbour]) continue;
                    
                    queue.add(neighbour);
                    visited[neighbour] = true;
                }
            }
            level++;
        }

        return level - 1;
    }

    public int getMaxFromEachComp(HashMap<Integer, List<Integer>> adj, int curr, boolean []visited, int []levels){
        int maxGroup = levels[curr];
        visited[curr] = true;

        for(int neighbour : adj.get(curr)){
            if(!visited[neighbour]){
                maxGroup = Math.max(maxGroup, getMaxFromEachComp(adj, neighbour, visited, levels));
            }
        }
        return maxGroup;
    }

    public int magnificentSets(int n, int[][] edges) {
        HashMap<Integer, List<Integer>> adj = new HashMap<>();
        for (int i = 0; i < n; i++) {
            adj.put(i, new ArrayList<>());
        }
        for (int[] edge : edges) {
            int u = edge[0] - 1;
            int v = edge[1] - 1;

            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        // check Bipartite
        int[] colors = new int[n];
        Arrays.fill(colors, -1);

        for (int node = 0; node < n; node++) {
            if (colors[node] == -1) {
                if (!isBiPartite(adj, node, colors, 1)) {
                    return -1;
                }
            }
        }

        // BFS to find max levels for each node
        int[] levels = new int[n];
        for (int node = 0; node < n; node++) {
            levels[node] = bfs(adj, node, n);
        }

        // Ssum of max sized group for each components
        int maxGroupEachComp = 0;
        boolean[] visited = new boolean[n];
        for (int node = 0; node < n; node++) {
            if (!visited[node]) {
                maxGroupEachComp += getMaxFromEachComp(adj, node, visited, levels);
            }
        }

        return maxGroupEachComp;
    }
}
