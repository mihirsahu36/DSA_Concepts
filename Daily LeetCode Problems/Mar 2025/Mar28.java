/*2503. Maximum Number of Points From Grid Queries
You are given an m x n integer matrix grid and an array queries of size k.
Find an array answer of size k such that for each integer queries[i]
you start in the top left cell of the matrix and repeat the following process:
If queries[i] is strictly greater than the value of the current cell that you are in,
then you get one point if it is your first time visiting this cell,
and you can move to any adjacent cell in all 4 directions: up, down, left, and right.
Otherwise, you do not get any points, and you end this process.
After the process, answer[i] is the maximum number of points you can get.
Note that for each query you are allowed to visit the same cell multiple times.
Return the resulting array answer.

Example 1:
Input: grid = [[1,2,3],[2,5,7],[3,5,1]], queries = [5,6,2]
Output: [5,8,1]
Explanation: The diagrams above show which cells we visit to get points for each query.

Example 2:
Input: grid = [[5,2,1],[1,1,2]], queries = [3]
Output: [0]
Explanation: We can not get any points because the value of the top left cell is already greater than or equal to 3.

Constraints:
m == grid.length
n == grid[i].length
2 <= m, n <= 1000
4 <= m * n <= 10^5
k == queries.length
1 <= k <= 10^4
1 <= grid[i][j], queries[i] <= 10^6*/

class Solution {
    int [][]directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int[] maxPoints(int[][] grid, int[] queries) {
        int m = grid.length;
        int n = grid[0].length;
        int q = queries.length;
        int []res = new int[q];
        int [][]sortedQ = new int[q][2];

        for(int i=0;i<q;i++){
            sortedQ[i][0] = queries[i];
            sortedQ[i][1] = i;
        }

        Arrays.sort(sortedQ, Comparator.comparingInt(a -> a[0]));

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        boolean [][]visited = new boolean[m][n];
        int points = 0;

        pq.offer(new int[]{grid[0][0], 0, 0});
        visited[0][0] = true;

        for(int i=0;i<q;i++){
            int queryValue = sortedQ[i][0];
            int idx = sortedQ[i][1];

            while(!pq.isEmpty() && pq.peek()[0] < queryValue){
                int []top = pq.poll();
                int x = top[1], y = top[2];
                points++;

                for(int []dir : directions){
                    int i_ = x + dir[0];
                    int j_ = y + dir[1];
                    if(i_ >= 0 && i_ < m && j_ >= 0 && j_ < n && !visited[i_][j_]){
                        pq.offer(new int []{grid[i_][j_], i_, j_});
                        visited[i_][j_] = true;
                    }
                }
            }
            res[idx] = points;
        }

        return res;
    }
}
