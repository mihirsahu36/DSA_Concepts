/*684. Redundant Connection
In this problem, a tree is an undirected graph that is connected and has no cycles.
You are given a graph that started as a tree with n nodes labeled from 1 to n, with one additional edge added. 
The added edge has two different vertices chosen from 1 to n, and was not an edge that already existed. 
The graph is represented as an array edges of length n where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the graph.
Return an edge that can be removed so that the resulting graph is a tree of n nodes. If there are multiple answers, return the answer that occurs last in the input.

Example 1:
Input: edges = [[1,2],[1,3],[2,3]]
Output: [2,3]

Example 2:
Input: edges = [[1,2],[2,3],[3,4],[1,4],[1,5]]
Output: [1,4]

Constraints:
n == edges.length
3 <= n <= 1000
edges[i].length == 2
1 <= ai < bi <= edges.length
ai != bi
There are no repeated edges.
The given graph is connected.*/

// DFS Approach
class Solution {
    public boolean dfs(HashMap<Integer, List<Integer>> adj, int u, int v, boolean []visited){
        visited[u] = true;
        if(u == v){
            return true;
        }
        for(int neighbour : adj.get(u)){
            if(visited[neighbour]){
                continue;
            }
            if(dfs(adj, neighbour, v, visited)){
                return true;
            }
        }
        return false;
    }
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        HashMap<Integer, List<Integer>> adj = new HashMap<>();

        for(int []edge : edges){
            int u = edge[0];
            int v = edge[1];

            if(adj.containsKey(u) && adj.containsKey(v)){
                boolean []visited = new boolean[n+1];
                if(dfs(adj, u, v, visited)){
                    return edge;
                }
            }

            adj.putIfAbsent(u, new ArrayList<>());
            adj.putIfAbsent(v, new ArrayList<>());
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        return new int[0];
    }
}

// BFS Approach
class Solution {
    private int n;

    public boolean bfs(HashMap<Integer, List<Integer>> map, int start, int end){
        boolean []visited = new boolean[n + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            if (curr == end) {
                return true;
            }
            visited[curr] = true;

            if (map.containsKey(curr)) {
                for (int neighbor : map.get(curr)) {
                    if (!visited[neighbor]) {
                        queue.offer(neighbor);
                    }
                }
            }
        }
        return false;
    }

    public int[] findRedundantConnection(int[][] edges) {
        n = edges.length;
        HashMap<Integer, List<Integer>> map = new HashMap<>();

        for(int []edge : edges){
            int u = edge[0];
            int v = edge[1];

            if(map.containsKey(u) && map.containsKey(v) && bfs(map, u, v)){
                return edge;
            }

            map.putIfAbsent(u, new ArrayList<>());
            map.putIfAbsent(v, new ArrayList<>());
            map.get(u).add(v);
            map.get(v).add(u);
        }
        return new int[0];
    }
}
