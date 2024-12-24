/*3203. Find Minimum Diameter After Merging Two Trees
There exist two undirected trees with n and m nodes, numbered from 0 to n - 1 and from 0 to m - 1, respectively. 
You are given two 2D integer arrays edges1 and edges2 of lengths n - 1 and m - 1, respectively, 
where edges1[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the first tree and 
edges2[i] = [ui, vi] indicates that there is an edge between nodes ui and vi in the second tree.
You must connect one node from the first tree with another node from the second tree with an edge.
Return the minimum possible diameter of the resulting tree.
The diameter of a tree is the length of the longest path between any two nodes in the tree.

Example 1:
Input: edges1 = [[0,1],[0,2],[0,3]], edges2 = [[0,1]]
Output: 3
Explanation:
We can obtain a tree of diameter 3 by connecting node 0 from the first tree with any node from the second tree.

Example 2:
Input: edges1 = [[0,1],[0,2],[0,3],[2,4],[2,5],[3,6],[2,7]], edges2 = [[0,1],[0,2],[0,3],[2,4],[2,5],[3,6],[2,7]]
Output: 5
Explanation:
We can obtain a tree of diameter 5 by connecting node 0 from the first tree with node 0 from the second tree.

Constraints:
1 <= n, m <= 10^5
edges1.length == n - 1
edges2.length == m - 1
edges1[i].length == edges2[i].length == 2
edges1[i] = [ai, bi]
0 <= ai, bi < n
edges2[i] = [ui, vi]
0 <= ui, vi < m
The input is generated such that edges1 and edges2 represent valid trees.*/

class Solution {
    public Map<Integer, List<Integer>> buildAdjList(int [][]edges){ // construct an adjacency list
        Map<Integer, List<Integer>> adjList =new HashMap<>();
        for(int []edge : edges){
            adjList.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(edge[1]); // a -> b
            adjList.computeIfAbsent(edge[1], k -> new ArrayList<>()).add(edge[0]); // b -> a
        }
        return adjList;
    }

    public int findDiameter(Map<Integer, List<Integer>> adjList){
        List<Integer> farthestNode = BFS(adjList, 0); // first BFS to find the farthest node from an arbitrary node
        farthestNode =  BFS(adjList, farthestNode.get(0)); // second BFS from farthest distance to determine the diameter // 0 -> farthestNode , 1 -> maxDistance
        return farthestNode.get(1); // the diameter is the maximum distance found
    }

    public List<Integer> BFS(Map<Integer, List<Integer>> adjList, int sourceNode){
        Queue<Integer> queue = new LinkedList<>();
        Map<Integer, Boolean> visited = new HashMap<>();

        queue.add(sourceNode); // push sourceNode to queue
        visited.put(sourceNode, true); // mark visited for sourceNode as true

        int maxDistance = 0, farthestNode = sourceNode;

        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0;i<size;i++){
                int currentNode = queue.poll(); // process the current node
                farthestNode = currentNode; // update the farthest node

                for(int neighbour : adjList.getOrDefault(currentNode, new ArrayList<>())){  // traverse all neighbors of the current node
                    if(!visited.getOrDefault(neighbour, false)){ // add the unvisited node to the queue
                        visited.put(neighbour, true);
                        queue.add(neighbour);
                    }
                }
            }
            if(!queue.isEmpty()){ // keeps track of the number of levels
                maxDistance++;
            }
        }
        return Arrays.asList(farthestNode, maxDistance); // return the farthest node and distance
    }

    public int minimumDiameterAfterMerge(int[][] edges1, int[][] edges2) {
        Map<Integer, List<Integer>> adj1 = buildAdjList(edges1);
        Map<Integer, List<Integer>> adj2 = buildAdjList(edges2);

        int d1 = findDiameter(adj1);
        int d2 = findDiameter(adj2);

        int combinedDiameter = (d1 + 1) / 2 + (d2 + 1) / 2 + 1;

        return Math.max(Math.max(d1, d2), combinedDiameter);
    }
}
