/* 3370. Smallest Number With All Set Bits
You are given a positive number n.
Return the smallest number x greater than or equal to n, such that the binary representation of x contains only set bits

Example 1:
Input: n = 5
Output: 7
Explanation:
The binary representation of 7 is "111".

Example 2:
Input: n = 10
Output: 15
Explanation:
The binary representation of 15 is "1111".

Example 3:
Input: n = 3
Output: 3
Explanation:
The binary representation of 3 is "11".

Constraints:
1 <= n <= 1000 */

// Approach 1
class Solution {
    public boolean isAllSetBits(int x){
        return (x & (x + 1)) == 0;
    }

    public int smallestNumber(int n) {
        int res = n;

        while(!isAllSetBits(res)){
            res++;
        }

        return res;
    }
}


// Approach 2
class Solution {
    public int smallestNumber(int n) {
        int res = 1;

        while(res < n){
            res = 2 * res + 1;
        }

        return res;
    }
}


// Approach 3
class Solution {
    public int smallestNumber(int n) {
        int bits = (int)(Math.log(n) / Math.log(2)) + 1;

        return (1 << bits) - 1;
    }
}
