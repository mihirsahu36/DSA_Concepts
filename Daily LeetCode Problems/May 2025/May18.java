/*1931. Painting a Grid With Three Different Colors
You are given two integers m and n. Consider an m x n grid where each cell is initially white.
You can paint each cell red, green, or blue. All cells must be painted.
Return the number of ways to color the grid with no two adjacent cells having the same color.
Since the answer can be very large, return it modulo 109 + 7.

Example 1:
Input: m = 1, n = 1
Output: 3
Explanation: The three possible colorings are shown in the image above.

Example 2:
Input: m = 1, n = 2
Output: 6
Explanation: The six possible colorings are shown in the image above.

Example 3:
Input: m = 5, n = 5
Output: 580986

Constraints:
1 <= m <= 5
1 <= n <= 1000*/

class Solution {
    private List<String> colStates = new ArrayList<>();
    private int [][]t;
    private final int MOD = (int)1e9 + 7;

    private void generateColStates(String currCol, int rowsRem, char prevColor){
        if(rowsRem == 0){
            colStates.add(currCol);
            return;
        }

        for(char color : new char[]{'R', 'G', 'B'}){
            if(color == prevColor) continue;
            generateColStates(currCol + color, rowsRem - 1, color);
        }
    }

    private int solve(int remCols, int prevColIdx, int m){
        if(remCols == 0) return 1;
        if(t[remCols][prevColIdx] != -1) return t[remCols][prevColIdx];

        int totalWays = 0;
        String prevCol = colStates.get(prevColIdx);

        for(int nextColIdx=0;nextColIdx<colStates.size();nextColIdx++){
            String nextCol = colStates.get(nextColIdx);
            boolean valid = true;

            for(int r=0;r<m;r++){
                if(prevCol.charAt(r) == nextCol.charAt(r)){
                    valid = false;
                    break;
                }
            }

            if(valid){
                totalWays = (totalWays + solve(remCols - 1, nextColIdx, m)) % MOD;
            }
        }

        return t[remCols][prevColIdx] = totalWays;
    }

    public int colorTheGrid(int m, int n) {
        colStates.clear();
        generateColStates("", m, '#');
        int numColPatterns = colStates.size();
        t = new int[n][numColPatterns];

        for(int []row : t){
            Arrays.fill(row, -1);
        }

        int res = 0;
        for(int i=0;i<numColPatterns;i++){
            res = (res + solve(n - 1, i, m)) % MOD;
        }

        return res;
    }
}
