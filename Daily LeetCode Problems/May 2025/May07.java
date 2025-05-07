/*3341. Find Minimum Time to Reach Last Room I
There is a dungeon with n x m rooms arranged as a grid.
You are given a 2D array moveTime of size n x m, where moveTime[i][j] represents the minimum time in seconds when you can start moving to that room.
You start from the room (0, 0) at time t = 0 and can move to an adjacent room. Moving between adjacent rooms takes exactly one second.
Return the minimum time to reach the room (n - 1, m - 1).
Two rooms are adjacent if they share a common wall, either horizontally or vertically.

Example 1:
Input: moveTime = [[0,4],[4,4]]
Output: 6
Explanation:
The minimum time required is 6 seconds.
At time t == 4, move from room (0, 0) to room (1, 0) in one second.
At time t == 5, move from room (1, 0) to room (1, 1) in one second.

Example 2:
Input: moveTime = [[0,0,0],[0,0,0]]
Output: 3
Explanation:
The minimum time required is 3 seconds.
At time t == 0, move from room (0, 0) to room (1, 0) in one second.
At time t == 1, move from room (1, 0) to room (1, 1) in one second.
At time t == 2, move from room (1, 1) to room (1, 2) in one second.

Example 3:
Input: moveTime = [[0,1],[1,2]]
Output: 3

Constraints:
2 <= n == moveTime.length <= 50
2 <= m == moveTime[i].length <= 50
0 <= moveTime[i][j] <= 10^9*/

class Solution {
    private static final int [][]directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public int minTimeToReach(int[][] moveTime) {
        int n = moveTime.length;
        int m = moveTime[0].length;

        int [][]res = new int[n][m];
        for(int []row : res){
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        res[0][0] = 0;
        pq.offer(new int[]{0, 0, 0});

        while(!pq.isEmpty()){
            int []curr = pq.poll();
            int currTime = curr[0];
            int i = curr[1];
            int j = curr[2];

            if(i == n - 1 && j == m - 1){
                return currTime;
            }

            for(int []dir : directions){
                int i_ = i + dir[0];
                int j_ = j + dir[1];

                if(i_ >= 0 && i_ < n && j_ >= 0 && j_ < m){
                    int wait = Math.max(moveTime[i_][j_] - currTime, 0);
                    int arrTime = currTime + wait + 1;

                    if(res[i_][j_] > arrTime){
                        res[i_][j_] = arrTime;
                        pq.offer(new int[]{arrTime, i_, j_});
                    }
                }
            }
        }

        return -1;
    }
}
