/*2684. Maximum Number of Moves in a Grid
You are given a 0-indexed m x n matrix grid consisting of positive integers.
You can start at any cell in the first column of the matrix, and traverse the grid in the following way:
From a cell (row, col), you can move to any of the cells: (row - 1, col + 1), (row, col + 1) and (row + 1, col + 1) such that the value of the cell you move to, should be strictly bigger than the value of the current cell.
Return the maximum number of moves that you can perform.

Example 1:
Input: grid = [[2,4,3,5],[5,4,9,3],[3,4,2,11],[10,9,13,15]]
Output: 3
Explanation: We can start at the cell (0, 0) and make the following moves:
- (0, 0) -> (0, 1).
- (0, 1) -> (1, 2).
- (1, 2) -> (2, 3).
It can be shown that it is the maximum number of moves that can be made.

Example 2:
Input: grid = [[3,2,4],[2,1,9],[1,1,7]]
Output: 0
Explanation: Starting from any cell in the first column we cannot perform any moves.

Constraints:
m == grid.length
n == grid[i].length
2 <= m, n <= 1000
4 <= m * n <= 105
1 <= grid[i][j] <= 106*/


class Solution {
    public int maxMoves(int[][] grid) {
        int m = grid.length; // row
        int n = grid[0].length; // column
        int max = 0;
        int [][] dp = new int[m][n]; // for overlapping subcases
        for(int []arr : dp) Arrays.fill(arr, -1); // intialize each element of dp with -1
        for(int i=0;i<m;i++){
            max = Math.max(max, getMax(i, 0, grid, dp)); // get maximum of next grid
        }
        return max;
    }
    private int getMax(int i, int j, int[][] grid, int [][] dp){
        int m = grid.length;
        int n = grid[0].length;
        int max = 0;
        if(dp[i][j] != -1) return dp[i][j]; // already the grid is computed
        if(i == m && j == n) return 0; // reached edges
        if(i-1>=0 && j+1<n && grid[i-1][j+1]>grid[i][j]){ // right top case
            max = 1 + getMax(i-1, j+1, grid, dp);
        }
        if(j+1<n && grid[i][j+1]>grid[i][j]){ // right case
            max = Math.max(max, 1 + getMax(i, j+1, grid, dp));
        }
        if(i+1<m && j+1<n && grid[i+1][j+1]>grid[i][j]){ // right bottom case
           max = Math.max(max, 1 + getMax(i+1, j+1, grid, dp)); 
        }
        return dp[i][j] = max;
    }
}
