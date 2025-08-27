/* 3459. Length of Longest V-Shaped Diagonal Segment
You are given a 2D integer matrix grid of size n x m, where each element is either 0, 1, or 2.
A V-shaped diagonal segment is defined as:
The segment starts with 1.
The subsequent elements follow this infinite sequence: 2, 0, 2, 0, ....
The segment:
Starts along a diagonal direction (top-left to bottom-right, bottom-right to top-left, top-right to bottom-left, or bottom-left to top-right).
Continues the sequence in the same diagonal direction.
Makes at most one clockwise 90-degree turn to another diagonal direction while maintaining the sequence.
Return the length of the longest V-shaped diagonal segment. If no valid segment exists, return 0.

Example 1:
Input: grid = [[2,2,1,2,2],[2,0,2,2,0],[2,0,1,1,0],[1,0,2,2,2],[2,0,0,2,2]]
Output: 5
Explanation:
The longest V-shaped diagonal segment has a length of 5 and follows these coordinates: (0,2) → (1,3) → (2,4), takes a 90-degree clockwise turn at (2,4), and continues as (3,3) → (4,2).

Example 2:
Input: grid = [[2,2,2,2,2],[2,0,2,2,0],[2,0,1,1,0],[1,0,2,2,2],[2,0,0,2,2]]
Output: 4
Explanation:
The longest V-shaped diagonal segment has a length of 4 and follows these coordinates: (2,3) → (3,2), takes a 90-degree clockwise turn at (3,2), and continues as (2,1) → (1,0).

Example 3:
Input: grid = [[1,2,2,2,2],[2,2,2,2,0],[2,0,0,0,0],[0,0,2,2,2],[2,0,0,2,0]]
Output: 5
Explanation:
The longest V-shaped diagonal segment has a length of 5 and follows these coordinates: (0,0) → (1,1) → (2,2) → (3,3) → (4,4).

Example 4:
Input: grid = [[1]]
Output: 1
Explanation:
The longest V-shaped diagonal segment has a length of 1 and follows these coordinates: (0,0).

Constraints:
n == grid.length
m == grid[i].length
1 <= n, m <= 500
grid[i][j] is either 0, 1 or 2. */

class Solution {
    int[][] dirs = {{-1,1},{1,1},{1,-1},{-1,-1}};
    int n, m;
    int [][][][]t;

    private int solve(int i, int j, int d, int canTurn, int val, int [][]grid){
        int i_ = i + dirs[d][0];
        int j_ = j + dirs[d][1];

        if(i_ < 0 || i_ >= n || j_ < 0 || j_ >= m || grid[i_][j_] != val){
            return 0;
        }

        if(t[i_][j_][d][canTurn] != -1){
            return t[i_][j_][d][canTurn];
        }

        int res = 0;
        int keepMoving = 1 + solve(i_, j_, d, canTurn, val == 2 ? 0 : 2, grid);
        res = Math.max(res, keepMoving);

        if(canTurn == 1){
            int turnAndMove = 1 + solve(i_, j_, (d + 1) % 4, 0, val == 2 ? 0 : 2, grid);
            res = Math.max(res, turnAndMove);
        }

        return t[i_][j_][d][canTurn] = res;
    }

    public int lenOfVDiagonal(int[][] grid) {
        n = grid.length;
        m = grid[0].length;
        t = new int[n][m][4][2];

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                for(int d=0;d<=3;d++){
                    for(int c=0;c<2;c++){
                        t[i][j][d][c] = -1;
                    }
                }
            }
        }

        int res = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j] == 1){
                    for(int d=0;d<=3;d++){
                        res = Math.max(res, 1 + solve(i, j, d, 1, 2, grid));
                    }
                }
            }
        }

        return res;
    }
}
