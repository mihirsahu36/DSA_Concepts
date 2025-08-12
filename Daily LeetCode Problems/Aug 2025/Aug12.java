/* 2787. Ways to Express an Integer as Sum of Powers
Given two positive integers n and x.
Return the number of ways n can be expressed as the sum of the xth power of unique positive integers,
in other words, the number of sets of unique integers [n1, n2, ..., nk] where n = n1^x + n2^x + ... + nk^x.
Since the result can be very large, return it modulo 10^9 + 7.
For example, if n = 160 and x = 3, one way to express n is n = 2^3 + 3^3 + 5^3.

Example 1:
Input: n = 10, x = 2
Output: 1
Explanation: We can express n as the following: n = 3^2 + 1^2 = 10.
It can be shown that it is the only way to express 10 as the sum of the 2nd power of unique integers.

Example 2:
Input: n = 4, x = 1
Output: 2
Explanation: We can express n in the following ways:
- n = 4^1 = 4.
- n = 3^1 + 1^1 = 4.

Constraints:
1 <= n <= 300
1 <= x <= 5 */

class Solution {
    private final int MOD = (int)1e9 + 7;
    private int [][]t = new int[301][301];

    private int solve(int n, int num, int x){
        if(n == 0){
            return 1;
        }

        if(n < 0){
            return 0;
        }

        int currPowVal = (int)Math.pow(num, x);
        if(currPowVal > n){
            return 0;
        }

        if(t[n][num] != -1){
            return t[n][num];
        }

        int take = solve(n - currPowVal, num + 1, x);
        int notTake = solve(n, num + 1, x);

        return t[n][num] = (take + notTake) % MOD;
    }

    public int numberOfWays(int n, int x) {
        for(int i=0;i<=n;i++){
            for(int j=0;j<=n;j++){
                t[i][j] = -1;
            }
        }

        return solve(n, 1, x);
    }
}



