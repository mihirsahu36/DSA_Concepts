/* 2257. Count Unguarded Cells in the Grid
You are given two integers m and n representing a 0-indexed m x n grid.
You are also given two 2D integer arrays guards and walls where 
guards[i] = [rowi, coli] and walls[j] = [rowj, colj] represent the positions of the ith guard and jth wall respectively.
A guard can see every cell in the four cardinal directions (north, east, south, or west)
starting from their position unless obstructed by a wall or another guard.
A cell is guarded if there is at least one guard that can see it.
Return the number of unoccupied cells that are not guarded.

Example 1:
Input: m = 4, n = 6, guards = [[0,0],[1,1],[2,3]], walls = [[0,1],[2,2],[1,4]]
Output: 7
Explanation: The guarded and unguarded cells are shown in red and green respectively in the above diagram.
There are a total of 7 unguarded cells, so we return 7.

Example 2:
Input: m = 3, n = 3, guards = [[1,1]], walls = [[0,1],[1,0],[2,1],[1,2]]
Output: 4
Explanation: The unguarded cells are shown in green in the above diagram.
There are a total of 4 unguarded cells, so we return 4.

Constraints:
1 <= m, n <= 10^5
2 <= m * n <= 10^5
1 <= guards.length, walls.length <= 5 * 10^4
2 <= guards.length + walls.length <= m * n
guards[i].length == walls[j].length == 2
0 <= rowi, rowj < m
0 <= coli, colj < n
All the positions in guards and walls are unique. */

class Solution {
    private void solve(int [][]grid, int row, int col, int rows, int cols, int direction){
        if(row < 0 || col < 0 || row >= rows || col >= cols || grid[row][col] == 1 || grid[row][col] == 2){
            return;
        }

        grid[row][col] = 3;

        if(direction == 1){
            solve(grid, row - 1, col, rows, cols, direction);
        }else if( direction == 2){
            solve(grid, row + 1, col, rows, cols, direction);
        }else if(direction == 3){
            solve(grid, row, col - 1, rows, cols, direction);
        }else{
            solve(grid, row, col + 1, rows, cols, direction);
        }
    }

    public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
        int [][]grid = new int[m][n];
        for(int []guard : guards){
            int i = guard[0];
            int j = guard[1];
            grid[i][j] = 1;
        }

        for(int []wall : walls){
            int i = wall[0];
            int j = wall[1];
            grid[i][j] = 2;
        }

        for(int []guard : guards){
            int guardRow = guard[0];
            int guardCol = guard[1];

            solve(grid, guardRow - 1, guardCol, m, n, 1);
            solve(grid, guardRow + 1, guardCol, m, n, 2);
            solve(grid, guardRow, guardCol - 1, m, n, 3);
            solve(grid, guardRow, guardCol + 1, m, n, 4);
        }

        int unguardedCount = 0;
        for(int row=0;row<m;row++){
            for(int col=0;col<n;col++){
                if(grid[row][col] == 0){
                    unguardedCount++;
                }
            }
        }

        return unguardedCount;
    }
}
