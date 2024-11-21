/*2257. Count Unguarded Cells in the Grid
You are given two integers m and n representing a 0-indexed m x n grid. You are also given two 2D integer arrays guards and walls where guards[i] = [rowi, coli] and walls[j] = [rowj, colj] represent the positions of the ith guard and jth wall respectively.
A guard can see every cell in the four cardinal directions (north, east, south, or west) starting from their position unless obstructed by a wall or another guard. A cell is guarded if there is at least one guard that can see it.
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
1 <= m, n <= 105
2 <= m * n <= 105
1 <= guards.length, walls.length <= 5 * 104
2 <= guards.length + walls.length <= m * n
guards[i].length == walls[j].length == 2
0 <= rowi, rowj < m
0 <= coli, colj < n
All the positions in guards and walls are unique.*/

class Solution {
    private void dfs(int [][] grid, int row, int col, int rows,int cols, int direction){
        if(row < 0 || col < 0 || row >= rows || col >= cols || grid[row][col] == 1 || grid[row][col] == 2){ // boundary case skipping guarded or walled cells
            return;
        }
        grid[row][col] = 3; // current cell visited by  guard line of sight

        if(direction == 1){ // up
            dfs(grid, row-1, col, rows, cols, direction);
        }else if(direction == 2){ // down
            dfs(grid, row+1, col, rows, cols, direction);
        }else if(direction == 3){ // left
            dfs(grid, row, col-1, rows, cols, direction);
        }else{ // right
            dfs(grid, row, col+1, rows, cols, direction);
        }
    }
    public int countUnguarded(int rows, int cols, int[][] guards, int[][] walls) {
        int [][] grid = new int[rows][cols];

        for(int []guard : guards){ // marking guards
            int i = guard[0];
            int j = guard[1];
            grid[i][j] = 1;
        }

        for(int []wall : walls){ // marking walls
            int i = wall[0];
            int j = wall[1];
            grid[i][j] = 2;
        }

        for(int []guard : guards){ // computing dfs for each guard in all four direction
            int guardRow = guard[0];
            int guardCol = guard[1];

            dfs(grid, guardRow-1, guardCol, rows, cols, 1); // up
            dfs(grid, guardRow+1, guardCol, rows, cols, 2); // down
            dfs(grid, guardRow, guardCol-1, rows, cols, 3); // left
            dfs(grid, guardRow, guardCol+1, rows, cols, 4); // right
        }

        int unguardedCount = 0;
        for(int row=0;row<rows;row++){ // counting of unguarded cell
            for(int col=0;col<cols;col++){
                if(grid[row][col] == 0){
                    unguardedCount++;
                }
            }
        }
        return unguardedCount;
    }
}
