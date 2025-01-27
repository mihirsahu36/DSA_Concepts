/*1462. Course Schedule IV
There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] 
indicates that you must take course ai first if you want to take course bi.
For example, the pair [0, 1] indicates that you have to take course 0 before you can take course 1.
Prerequisites can also be indirect. If course a is a prerequisite of course b, and course b is a prerequisite of course c, then course a is a prerequisite of course c.
You are also given an array queries where queries[j] = [uj, vj]. For the jth query, you should answer whether course uj is a prerequisite of course vj or not.
Return a boolean array answer, where answer[j] is the answer to the jth query.

Example 1:
Input: numCourses = 2, prerequisites = [[1,0]], queries = [[0,1],[1,0]]
Output: [false,true]
Explanation: The pair [1, 0] indicates that you have to take course 1 before you can take course 0.
Course 0 is not a prerequisite of course 1, but the opposite is true.

Example 2:
Input: numCourses = 2, prerequisites = [], queries = [[1,0],[0,1]]
Output: [false,false]
Explanation: There are no prerequisites, and each course is independent.

Example 3:
Input: numCourses = 3, prerequisites = [[1,2],[1,0],[2,0]], queries = [[1,0],[1,2]]
Output: [true,true]

Constraints:
2 <= numCourses <= 100
0 <= prerequisites.length <= (numCourses * (numCourses - 1) / 2)
prerequisites[i].length == 2
0 <= ai, bi <= numCourses - 1
ai != bi
All the pairs [ai, bi] are unique.
The prerequisites graph has no cycles.
1 <= queries.length <= 10^4
0 <= ui, vi <= numCourses - 1
ui != vi*/

// DFS Approach
class Solution {
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        HashMap<Integer, List<Integer>> adj = new HashMap<>();
        for(int []edge : prerequisites){
            int u = edge[0];
            int v = edge[1];
            adj.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
        }

        int Q = queries.length;
        ArrayList<Boolean> res = new ArrayList<>();

        for(int i=0;i<Q;i++){
            int u = queries[i][0];
            int v = queries[i][1];

            boolean []visited = new boolean[numCourses];
            res.add(dfs(adj, u, v, visited));
        }
        return res;
    }

    private boolean dfs(Map<Integer, List<Integer>> adj, int src, int dest, boolean[] visited) {
        visited[src] = true;

        if (src == dest) {
            return true;
        }

        boolean isReachable = false;
        for (int adjNode : adj.getOrDefault(src, new ArrayList<>())) {
            if (!visited[adjNode]) {
                isReachable = isReachable || dfs(adj, adjNode, dest, visited);
            }
        }

        return isReachable;
    }
}

// Topological Sorting
// Kahn's Algorithm
class Solution {
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        HashMap<Integer, List<Integer>> adj = new HashMap<>();
        int []indegree = new int[numCourses];
        for(int []edge : prerequisites){
            int u = edge[0];
            int v = edge[1];
            adj.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
            indegree[v]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for(int i=0;i<numCourses;i++){
            if(indegree[i] == 0){
                queue.offer(i);
            }
        }

        HashMap<Integer, Set<Integer>>prereqMap = new HashMap<>();
        while(!queue.isEmpty()){
            int node = queue.poll();

            for(int neighbour : adj.getOrDefault(node, new ArrayList<>())){
                prereqMap.computeIfAbsent(neighbour, k -> new HashSet<>()).add(node);
                prereqMap.get(neighbour).addAll(prereqMap.getOrDefault(node, new HashSet<>()));
                indegree[neighbour]--;

                if(indegree[neighbour] == 0){
                    queue.offer(neighbour);
                }
            }
        }

        ArrayList<Boolean> res = new ArrayList<>();
        for(int []query : queries){
            int src = query[0];
            int dest = query[1];
            res.add(prereqMap.getOrDefault(dest, new HashSet<>()).contains(src));
        }
        return res;
    }
}
