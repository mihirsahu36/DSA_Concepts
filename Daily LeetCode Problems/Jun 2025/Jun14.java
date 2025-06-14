/*2566. Maximum Difference by Remapping a Digit
You are given an integer num. You know that Bob will sneakily remap one of the 10 possible digits (0 to 9) to another digit.
Return the difference between the maximum and minimum values Bob can make by remapping exactly one digit in num.
Notes:
When Bob remaps a digit d1 to another digit d2, Bob replaces all occurrences of d1 in num with d2.
Bob can remap a digit to itself, in which case num does not change.
Bob can remap different digits for obtaining minimum and maximum values respectively.
The resulting number after remapping can contain leading zeroes.

Example 1:
Input: num = 11891
Output: 99009
Explanation: 
To achieve the maximum value, Bob can remap the digit 1 to the digit 9 to yield 99899.
To achieve the minimum value, Bob can remap the digit 1 to the digit 0, yielding 890.
The difference between these two numbers is 99009.

Example 2:
Input: num = 90
Output: 99
Explanation:
The maximum value that can be returned by the function is 99 (if 0 is replaced by 9)
and the minimum value that can be returned by the function is 0 (if 9 is replaced by 0).
Thus, we return 99.

Constraints:
1 <= num <= 10^8 */

// Approach 1
class Solution {
    public int minMaxDifference(int num) {
        String minNum = Integer.toString(num);
        String maxNum = Integer.toString(num);
        int n = minNum.length();
        char c = ' ';

        for(int i=0;i<n;i++){
            if(maxNum.charAt(i) != '9'){
                c = maxNum.charAt(i);
                break;
            }
        }

        if(c != ' '){
            maxNum = maxNum.replace(c, '9');
        }

        c = minNum.charAt(0);
        minNum = minNum.replace(c, '0');

        return Integer.parseInt(maxNum) - Integer.parseInt(minNum);
    }
}

// Approach 2
class Solution {
    public int minMaxDifference(int num) {
        String  s1 = Integer.toString(num);
        String s2 = s1;

        int idx = 0;
        for(int i=0;i<s1.length();i++){
            if(s1.charAt(i) != '9'){
                idx = i;
                break;
            }
        }

        if(idx < s1.length()){
            s1 = s1.replace(s1.charAt(idx), '9');
        }

        s2 = s2.replace(s2.charAt(0), '0');

        return Integer.parseInt(s1) - Integer.parseInt(s2);
    }
}
