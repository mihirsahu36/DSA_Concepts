/* 2048. Next Greater Numerically Balanced Number
An integer x is numerically balanced if for every digit d in the number x,
there are exactly d occurrences of that digit in x.
Given an integer n, return the smallest numerically balanced number strictly greater than n.

Example 1:
Input: n = 1
Output: 22
Explanation: 
22 is numerically balanced since:
- The digit 2 occurs 2 times. 
It is also the smallest numerically balanced number strictly greater than 1.

Example 2:
Input: n = 1000
Output: 1333
Explanation: 
1333 is numerically balanced since:
- The digit 1 occurs 1 time.
- The digit 3 occurs 3 times. 
It is also the smallest numerically balanced number strictly greater than 1000.
Note that 1022 cannot be the answer because 0 appeared more than 0 times.

Example 3:
Input: n = 3000
Output: 3133
Explanation: 
3133 is numerically balanced since:
- The digit 1 occurs 1 time.
- The digit 3 occurs 3 times.
It is also the smallest numerically balanced number strictly greater than 3000.

Constraints:
0 <= n <= 10^6 */

class Solution {
    private int []digitCount = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

    private int solve(int n, int curr, int count){
        if(count == 0){
            for(int digit=1;digit<=9;digit++){
                if(digitCount[digit] != 0 && digitCount[digit] != digit){
                    return 0;
                }
            }

            return curr > n ? curr : 0;
        }

        int res = 0;

        for(int digit=1;digit<=9;digit++){
            if(digitCount[digit] > 0 && digitCount[digit] <= count){
                digitCount[digit]--;
                res = solve(n, curr * 10 + digit, count - 1);
                digitCount[digit]++;
            }

            if(res != 0){
                break;
            }
        }

        return res;
    }

    public int nextBeautifulNumber(int n) {
        int numDigits = String.valueOf(n).length();
        int res = solve(n, 0, numDigits);

        if(res == 0){
            res = solve(n, 0, numDigits + 1);
        }

        return res;
    }
}
