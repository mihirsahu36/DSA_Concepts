/*1905. Count Sub Islands

You are given two m x n binary matrices grid1 and grid2 containing only 0's (representing water) and 1's (representing land). An island is a group of 1's connected 4-directionally (horizontal or vertical). Any cells outside of the grid are considered water cells.
An island in grid2 is considered a sub-island if there is an island in grid1 that contains all the cells that make up this island in grid2.
Return the number of islands in grid2 that are considered sub-islands.

Example 1:
Input: grid1 = [[1,1,1,0,0],[0,1,1,1,1],[0,0,0,0,0],[1,0,0,0,0],[1,1,0,1,1]], grid2 = [[1,1,1,0,0],[0,0,1,1,1],[0,1,0,0,0],[1,0,1,1,0],[0,1,0,1,0]]
Output: 3
Explanation: In the picture above, the grid on the left is grid1 and the grid on the right is grid2.
The 1s colored red in grid2 are those considered to be part of a sub-island. There are three sub-islands.

Example 2:
Input: grid1 = [[1,0,1,0,1],[1,1,1,1,1],[0,0,0,0,0],[1,1,1,1,1],[1,0,1,0,1]], grid2 = [[0,0,0,0,0],[1,1,1,1,1],[0,1,0,1,0],[0,1,0,1,0],[1,0,0,0,1]]
Output: 2 
Explanation: In the picture above, the grid on the left is grid1 and the grid on the right is grid2.
The 1s colored red in grid2 are those considered to be part of a sub-island. There are two sub-islands.*/

class Solution {
    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int m = grid1.length, n = grid1[0].length;
        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid2[i][j] == 1) {
                    if (isSubIsland(grid1, grid2, i, j)) count++;
                }
            }
        }

        return count;
    }

    private boolean isSubIsland(int[][] grid1, int[][] grid2, int x, int y) {
        boolean isSubIsland = true;
        if (x < 0 || x >= grid1.length || y < 0 || y >= grid1[0].length || grid2[x][y] == 0) return true;
        if (grid1[x][y] == 0) isSubIsland = false;
        grid2[x][y] = 0;

        isSubIsland &= isSubIsland(grid1, grid2, x + 1, y);
        isSubIsland &= isSubIsland(grid1, grid2, x - 1, y);
        isSubIsland &= isSubIsland(grid1, grid2, x, y + 1);
        isSubIsland &= isSubIsland(grid1, grid2, x, y - 1);

        return isSubIsland;
    }
}
