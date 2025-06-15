/*1432. Max Difference You Can Get From Changing an Integer
You are given an integer num. You will apply the following steps to num two separate times:
Pick a digit x (0 <= x <= 9).
Pick another digit y (0 <= y <= 9). Note y can be equal to x.
Replace all the occurrences of x in the decimal representation of num by y.
Let a and b be the two results from applying the operation to num independently.
Return the max difference between a and b.
Note that neither a nor b may have any leading zeros, and must not be 0.

Example 1:

Input: num = 555
Output: 888
Explanation: The first time pick x = 5 and y = 9 and store the new integer in a.
The second time pick x = 5 and y = 1 and store the new integer in b.
We have now a = 999 and b = 111 and max difference = 888
Example 2:

Input: num = 9
Output: 8
Explanation: The first time pick x = 9 and y = 9 and store the new integer in a.
The second time pick x = 9 and y = 1 and store the new integer in b.
We have now a = 9 and b = 1 and max difference = 8

Constraints:
1 <= num <= 10^8*/

class Solution {
    public int maxDiff(int num) {
        String s1 = Integer.toString(num);
        String s2 = s1;

        int idx = -1;
        for(int i=0;i<s1.length();i++){
            if(s1.charAt(i) != '9'){
                idx = i;
                break;
            }
        }

        if(idx != -1){
            char c = s1.charAt(idx);
            s1 = s1.replace(c, '9');
        }

        for(int i=0;i<s2.length();i++){
            char c = s2.charAt(i);
            if(i == 0){
                if(c != '1'){
                    s2 = s2.replace(c, '1');
                    break;
                }
            }else if(c != '0' && c != s2.charAt(0)){
                    s2 = s2.replace(c, '0');
                    break;
            }
        }

        return Integer.parseInt(s1) - Integer.parseInt(s2);
    }
}
