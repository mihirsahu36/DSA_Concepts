/* 3197. Find the Minimum Area to Cover All Ones II
You are given a 2D binary array grid.
You need to find 3 non-overlapping rectangles having non-zero areas
with horizontal and vertical sides such that all the 1's in grid lie inside these rectangles.
Return the minimum possible sum of the area of these rectangles.
Note that the rectangles are allowed to touch.

Example 1:
Input: grid = [[1,0,1],[1,1,1]]
Output: 5
Explanation:
The 1's at (0, 0) and (1, 0) are covered by a rectangle of area 2.
The 1's at (0, 2) and (1, 2) are covered by a rectangle of area 2.
The 1 at (1, 1) is covered by a rectangle of area 1.

Example 2:
Input: grid = [[1,0,1,0],[0,1,0,1]]
Output: 5
Explanation:
The 1's at (0, 0) and (0, 2) are covered by a rectangle of area 3.
The 1 at (1, 1) is covered by a rectangle of area 1.
The 1 at (1, 3) is covered by a rectangle of area 1.

Constraints:
1 <= grid.length, grid[i].length <= 30
grid[i][j] is either 0 or 1.
The input is generated such that there are at least three 1's in grid. */

class Solution {
    private int [][]rotateClockWise(int [][]grid){
        int n = grid.length;
        int m = grid[0].length;
        int [][]rotatedGrid = new int[m][n];

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                rotatedGrid[j][n-i-1] = grid[i][j];
            }
        }

        return rotatedGrid;
    }

    private int minimumArea(int startRow, int endRow, int startCol, int endCol, int [][]grid){
        int n = grid.length;
        int m = grid[0].length;
        int minRow = n, maxRow = -1, minCol = m, maxCol = -1;

        for(int i=startRow;i<endRow;i++){
            for(int j=startCol;j<endCol;j++){
                if(grid[i][j] == 1){
                    minRow = Math.min(minRow, i);
                    maxRow = Math.max(maxRow, i);;
                    minCol = Math.min(minCol, j);;
                    maxCol = Math.max(maxCol, j);;
                }
            }
        }

        if(maxRow == -1){ // no 1's is present in subgrid
            return 0;
        }

        return (maxRow - minRow + 1) * (maxCol - minCol  + 1);
    }

    private int utility(int [][]grid){
        int n = grid.length;
        int m = grid[0].length;
        int res = Integer.MAX_VALUE;

        for(int rowSplit=1;rowSplit<n;rowSplit++){
            for(int colSplit=1;colSplit<m;colSplit++){
                int top = minimumArea(0, rowSplit, 0, m, grid);
                int bottomLeft = minimumArea(rowSplit, n, 0, colSplit, grid);
                int bottomRight = minimumArea(rowSplit, n, colSplit, m, grid);

                res = Math.min(res, top + bottomLeft + bottomRight);
            }
        }

        for(int rowSplit=1;rowSplit<n;rowSplit++){
            for(int colSplit=1;colSplit<m;colSplit++){
                int topLeft = minimumArea(0, rowSplit, 0, colSplit, grid);
                int topRight = minimumArea(0, rowSplit, colSplit, m, grid);
                int bottom = minimumArea(rowSplit, n, 0, m, grid);

                res = Math.min(res, topLeft + topRight + bottom);
            }
        }

        for(int split1=1;split1<n;split1++){
            for(int split2=split1+1;split2<n;split2++){
                int top = minimumArea(0, split1, 0, m, grid);
                int middle = minimumArea(split1, split2, 0, m, grid);
                int bottom = minimumArea(split2, n, 0, m, grid);

                res = Math.min(res, top + middle + bottom);
            }
        }

        return res;
    }

    public int minimumSum(int[][] grid) {
        int res = utility(grid);
        int [][]rotatedGrid = rotateClockWise(grid);
        res = Math.min(res, utility(rotatedGrid));

        return res;
    }
}
