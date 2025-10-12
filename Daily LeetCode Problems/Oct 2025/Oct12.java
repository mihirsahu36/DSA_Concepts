/* 3539. Find Sum of Array Product of Magical Sequences
You are given two integers, m and k, and an integer array nums.
A sequence of integers seq is called magical if:
seq has a size of m.
0 <= seq[i] < nums.length
The binary representation of 2seq[0] + 2seq[1] + ... + 2seq[m - 1] has k set bits.
The array product of this sequence is defined as prod(seq) = (nums[seq[0]] * nums[seq[1]] * ... * nums[seq[m - 1]]).
Return the sum of the array products for all valid magical sequences.
Since the answer may be large, return it modulo 109 + 7.
A set bit refers to a bit in the binary representation of a number that has a value of 1.

Example 1:
Input: m = 5, k = 5, nums = [1,10,100,10000,1000000]
Output: 991600007
Explanation:
All permutations of [0, 1, 2, 3, 4] are magical sequences, each with an array product of 1013.

Example 2:
Input: m = 2, k = 2, nums = [5,4,3,2,1]
Output: 170
Explanation:
The magical sequences are [0, 1], [0, 2], [0, 3], [0, 4], [1, 0], [1, 2], [1, 3], [1, 4], [2, 0],
[2, 1], [2, 3], [2, 4], [3, 0], [3, 1], [3, 2], [3, 4], [4, 0], [4, 1], [4, 2], and [4, 3].

Example 3:
Input: m = 1, k = 1, nums = [28]
Output: 28
Explanation:
The only magical sequence is [0].

Constraints:
1 <= k <= m <= 30
1 <= nums.length <= 50
1 <= nums[i] <= 10^8 */


class Solution {
    public long quickmul(long base, long exp, long mod){
        long res = 1;
        long cur = base % mod;

        while(exp > 0){
            if((exp & 1) == 1){
                res = (res * cur) % mod;
            }
            exp >>= 1;
            cur = (cur * cur) % mod;
        }

        return res;
    }

    public int magicalSum(int m, int k, int[] nums) {
        int n = nums.length;
        long mod = 1000000007;
        long []fact = new long[m + 1];
        fact[0] = 1;

        for(int i=1;i<=m;i++){
            fact[i] = (fact[i - 1] * i) % mod;
        }

        long []invFact = new long[m + 1];
        invFact[0] = 1;
        invFact[1] = 1;

        for(int i=2;i<=m;i++){
            invFact[i] = quickmul(i, mod - 2, mod);
        }

        for(int i=2;i<=m;i++){
            invFact[i] = (invFact[i - 1] * invFact[i]) % mod;
        }

        long [][]numsPower = new long[n][m + 1];

        for(int i=0;i<n;i++){
            numsPower[i][0] = 1;
            for(int j=1;j<=m;j++){
                numsPower[i][j] = (numsPower[i][j - 1] * nums[i]) % mod;
            }
        }

        long [][][][]dp = new long[n][m + 1][m * 2 + 1][k + 1];
        for(int j=0;j<=m;j++){
            dp[0][j][j][0] = (numsPower[0][j] * invFact[j]) % mod;
        }

        for(int i=0;i+1<n;i++){
            for(int j=0;j<=m;j++){
                for(int p=0;p<=m*2;p++){
                    for(int q=0;q<=k;q++){
                        int q2 = (p % 2) + q;
                        if(q2 > k){
                            break;
                        }

                        for(int r=0;r+j<=m;r++){
                            int p2 = p / 2 + r;
                            dp[i + 1][j + r][p2][q2] +=
                                (((dp[i][j][p][q] * numsPower[i + 1][r]) % mod) *
                                    invFact[r]) %
                                mod;
                            dp[i + 1][j + r][p2][q2] %= mod;
                        }
                    }
                }
            }
        }

        long res = 0;
        for(int p=0;p<=m*2;p++){
            for(int q=0;q<=k;q++){
                if(Integer.bitCount(p) + q == k){
                    res = (res + ((dp[n - 1][m][p][q] * fact[m]) % mod)) % mod;
                }
            }
        }

        return (int)res;
    }
}
