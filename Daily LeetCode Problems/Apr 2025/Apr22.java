/*2338. Count the Number of Ideal Arrays
You are given two integers n and maxValue, which are used to describe an ideal array.
A 0-indexed integer array arr of length n is considered ideal if the following conditions hold:
Every arr[i] is a value from 1 to maxValue, for 0 <= i < n.
Every arr[i] is divisible by arr[i - 1], for 0 < i < n.
Return the number of distinct ideal arrays of length n. Since the answer may be very large, return it modulo 10^9 + 7.

Example 1:
Input: n = 2, maxValue = 5
Output: 10
Explanation: The following are the possible ideal arrays:
- Arrays starting with the value 1 (5 arrays): [1,1], [1,2], [1,3], [1,4], [1,5]
- Arrays starting with the value 2 (2 arrays): [2,2], [2,4]
- Arrays starting with the value 3 (1 array): [3,3]
- Arrays starting with the value 4 (1 array): [4,4]
- Arrays starting with the value 5 (1 array): [5,5]
There are a total of 5 + 2 + 1 + 1 + 1 = 10 distinct ideal arrays.

Example 2:
Input: n = 5, maxValue = 3
Output: 11
Explanation: The following are the possible ideal arrays:
- Arrays starting with the value 1 (9 arrays): 
   - With no other distinct values (1 array): [1,1,1,1,1] 
   - With 2nd distinct value 2 (4 arrays): [1,1,1,1,2], [1,1,1,2,2], [1,1,2,2,2], [1,2,2,2,2]
   - With 2nd distinct value 3 (4 arrays): [1,1,1,1,3], [1,1,1,3,3], [1,1,3,3,3], [1,3,3,3,3]
- Arrays starting with the value 2 (1 array): [2,2,2,2,2]
- Arrays starting with the value 3 (1 array): [3,3,3,3,3]
There are a total of 9 + 1 + 1 = 11 distinct ideal arrays.



Constraints:
2 <= n <= 10^4
1 <= maxValue <= 10^4*/

class Solution {
    private static final int MOD = (int)1e9 + 7;
    private long [][]count;
    private long [][]prefixSum;
    private long []options;

    private void countUniqueSequences(int curr, int idx, int maxValue){
        options[idx]++;
        for(int j=2;curr*j<=maxValue;++j){
            countUniqueSequences(curr * j, idx + 1, maxValue);
        }
    }

    public int idealArrays(int n, int maxValue) {
        count = new long[15][10005];
        prefixSum = new long[15][10005];
        options = new long[15];

        for(int i=1;i<=10000;++i){// pre-fill 1st row
            count[1][i] = 1;
            prefixSum[1][i] = i;
        }

        for(int i=2;i<=14;++i){// fill the count table
            for(int j=i;j<=10000;++j){
                count[i][j] = prefixSum[i - 1][j - 1];
                prefixSum[i][j] = (count[i][j] + prefixSum[i][j - 1]) % MOD;
                count[i][j] %= MOD;
            }
        }

        for(int i=1;i<=maxValue;++i){// calculate options
            countUniqueSequences(i, 1, maxValue);
        }

        // count total ideal arrays
        long res = 0;
        for(int i=1;i<=14;++i) {
            long ways = (count[i][n] * options[i]) % MOD;
            res = (res + ways) % MOD;
        }
        return (int)res;
    }
}
