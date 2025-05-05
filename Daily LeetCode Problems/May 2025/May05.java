/*790. Domino and Tromino Tiling
You have two types of tiles: a 2 x 1 domino shape and a tromino shape. You may rotate these shapes.
Given an integer n, return the number of ways to tile an 2 x n board. Since the answer may be very large, return it modulo 109 + 7.
In a tiling, every square must be covered by a tile.
Two tilings are different if and only if there are two 4-directionally adjacent cells on the board 
such that exactly one of the tilings has both squares occupied by a tile.

Example 1:
Input: n = 3
Output: 5
Explanation: The five different ways are show above.

Example 2:
Input: n = 1
Output: 1

Constraints:
1 <= n <= 1000*/

// Approach 1 (Bottom Up)
class Solution {
    public int numTilings(int n) {
        final int MOD = 1000000007;

        if(n == 1) return 1;
        if(n == 2) return 2;
        if(n == 3) return 5;

        int []t = new int[n + 1];

        t[1] = 1;
        t[2] = 2;
        t[3] = 5;

        for(int i=4;i<=n;i++){
            t[i] = (int)((2L * t[i - 1] % MOD + t[i - 3]) % MOD);
        }

        return t[n];
    }
}

// Approach 2 (Recursion + Memoization)
class Solution {
    final int MOD = 1000000007;
    int []t = new int[1001];

    public int solve(int n){
        if(n == 1 || n == 2){
            return n;
        }

        if(n == 3){
            return 5;
        }

        if(t[n] != -1){
            return t[n];
        }

        t[n] = (int)((2L * solve(n - 1) % MOD + solve(n - 3)) % MOD);
        return t[n];
    }
    public int numTilings(int n) {
        for(int i=0;i<=n;i++){
            t[i] = -1;
        }

        return solve(n);
    }
}
