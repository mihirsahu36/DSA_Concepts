/*1277. Count Square Submatrices with All Ones
Given a m * n matrix of ones and zeros, return how many square submatrices have all ones.
  
Example 1:
Input: matrix =
[
  [0,1,1,1],
  [1,1,1,1],
  [0,1,1,1]
]
Output: 15
Explanation: 
There are 10 squares of side 1.
There are 4 squares of side 2.
There is  1 square of side 3.
Total number of squares = 10 + 4 + 1 = 15.
  
Example 2:
Input: matrix = 
[
  [1,0,1],
  [1,1,0],
  [1,1,0]
]
Output: 7
Explanation: 
There are 6 squares of side 1.  
There is 1 square of side 2. 
Total number of squares = 6 + 1 = 7.
 
Constraints:
1 <= arr.length <= 300
1 <= arr[0].length <= 300
0 <= arr[i][j] <= 1*/

class Solution {
    public int countSquares(int[][] matrix) {
        int n = matrix.length; // no. of rows
        int m = matrix[0].length; // no. of columns
        int[][] dp = new int[n][m];
        int ans = 0; // for storing result
        for (int i = 0; i < n; i++) {
            dp[i][0] = matrix[i][0]; // setting first row of dp as matrix first row
            ans += dp[i][0];
        }
        for (int j = 1; j < m; j++) {
            dp[0][j] = matrix[0][j]; // setting first column of dp as matrix first column
            ans += dp[0][j];
        }
        for(int i = 1; i < n; i++) {
            for(int j = 1; j < m; j++) {
                if(matrix[i][j] == 1) {
                    dp[i][j] = 1 + Math.min(Math.min(dp[i][j-1], dp[i-1][j]), dp[i-1][j-1]); // adding its own value (1) and miniumum of value(its top, left top, and left value)
                }
                ans += dp[i][j]; // returing total value
            }
        }
        
        return ans;
    }
}
