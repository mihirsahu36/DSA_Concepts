/*2466. Count Ways To Build Good Strings
Given the integers zero, one, low, and high, we can construct a string by starting with an empty string, and then at each step perform either of the following:
Append the character '0' zero times.
Append the character '1' one times.
This can be performed any number of times.
A good string is a string constructed by the above process having a length between low and high (inclusive).
Return the number of different good strings that can be constructed satisfying these properties. Since the answer can be large, return it modulo 109 + 7.

Example 1:
Input: low = 3, high = 3, zero = 1, one = 1
Output: 8
Explanation: 
One possible valid good string is "011". 
It can be constructed as follows: "" -> "0" -> "01" -> "011". 
All binary strings from "000" to "111" are good strings in this example.

Example 2:
Input: low = 2, high = 3, zero = 1, one = 2
Output: 5
Explanation: The good strings are "00", "11", "000", "110", and "011".

Constraints:
1 <= low <= high <= 10^5
1 <= zero, one <= low*/

class Solution {
    int res;
    final int MOD = 1_000_000_007;
    int L, H, Z, O;
    int []dp; // memoization
    public int solve(int l){
        if(l > H){ // if length exceeds the upper limit, return 0
            return 0;
        }
        if(dp[l] != -1){
            return dp[l];
        }
        boolean addOne = false;
        if(l >= L && l <= H){  // current length is within valid range
            addOne = true;
        }

        // recursively calculate results for adding Z and O
        int takeZero = solve(l + Z);
        int takeOne = solve(l + O);

        dp[l] = (addOne ? 1 : 0) + takeZero + takeOne; // compute result for current length and store it
        dp[l] %= MOD;

        return dp[l];
    }
    public int countGoodStrings(int low, int high, int zero, int one) {
        L = low;
        H = high;
        Z = zero;
        O = one;

        dp = new int[high+1];
        Arrays.fill(dp, -1);

        return solve(0);
    }
}
