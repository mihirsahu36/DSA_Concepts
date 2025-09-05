/* 2749. Minimum Operations to Make the Integer Zero
You are given two integers num1 and num2.
In one operation, you can choose integer i in the range [0, 60] and subtract 2i + num2 from num1.
Return the integer denoting the minimum number of operations needed to make num1 equal to 0.
If it is impossible to make num1 equal to 0, return -1.

Example 1:
Input: num1 = 3, num2 = -2
Output: 3
Explanation: We can make 3 equal to 0 with the following operations:
- We choose i = 2 and subtract 22 + (-2) from 3, 3 - (4 + (-2)) = 1.
- We choose i = 2 and subtract 22 + (-2) from 1, 1 - (4 + (-2)) = -1.
- We choose i = 0 and subtract 20 + (-2) from -1, (-1) - (1 + (-2)) = 0.
It can be proven, that 3 is the minimum number of operations that we need to perform.

Example 2:
Input: num1 = 5, num2 = 7
Output: -1
Explanation: It can be proven, that it is impossible to make 5 equal to 0 with the given operation.

Constraints:
1 <= num1 <= 10^9
-10^9 <= num2 <= 10^9 */

// Approach 1
class Solution {
    public int makeTheIntegerZero(int num1, int num2) {
        int t = 0;

        while(true){
            long val = (long)num1 - (long)t * num2;

            if(val < 0){
                return -1;
            }

            if(Long.bitCount(val) <= t && t <= val){
                return t;
            }

            t++;
        }
    }
}

// Approach 2
class Solution {
    public int makeTheIntegerZero(int num1, int num2) {
        for(int t=1;t<=36;t++){
            long val = (long)num1 - (long)t * num2;

            if(val < 0){
                return -1;
            }

            if(Long.bitCount(val) <= t && t <= val){
                return t;
            }
        }

        return -1;
    }
}
