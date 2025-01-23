/*1267. Count Servers that Communicate
You are given a map of a server center, represented as a m * n integer matrix grid,
where 1 means that on that cell there is a server and 0 means that it is no server.
Two servers are said to communicate if they are on the same row or on the same column.
Return the number of servers that communicate with any other server.

Example 1:
Input: grid = [[1,0],[0,1]]
Output: 0
Explanation: No servers can communicate with others.

Example 2:
Input: grid = [[1,0],[1,1]]
Output: 3
Explanation: All three servers can communicate with at least one other server.

Example 3:
Input: grid = [[1,1,0,0],[0,0,1,0],[0,0,1,0],[0,0,0,1]]
Output: 4
Explanation: The two servers in the first row can communicate with each other. The two servers in the third column can communicate with each other. The server at right bottom corner can't communicate with any other server.

Constraints:
m == grid.length
n == grid[i].length
1 <= m <= 250
1 <= n <= 250
grid[i][j] == 0 or 1*/

class Solution {
    public int countServers(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int []indexColCount = new int[n];
        int []indexRowCount = new int[m];

        for(int row=0;row<m;row++){ // preprocessing, keeping track of number of servers found in row and column
            for(int col=0;col<n;col++){
                if(grid[row][col] == 1){
                    indexColCount[col]++;
                    indexRowCount[row]++;
                }
            }
        }

        int resServers = 0;
        for(int row=0;row<m;row++){ // if number of servers in row or column greater than 1 then increment result
            for(int col=0;col<n;col++){
                if(grid[row][col] == 1 && (indexColCount[col] > 1 || indexRowCount[row] > 1)){
                    resServers++;
                }
            }
        }
        return resServers;
    }
}
