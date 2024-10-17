/*670. Maximum Swap
You are given an integer num. You can swap two digits at most once to get the maximum valued number.
Return the maximum valued number you can get.

Example 1:
Input: num = 2736
Output: 7236
Explanation: Swap the number 2 and the number 7.

Example 2:
Input: num = 9973
Output: 9973
Explanation: No swap.
 
Constraints:
0 <= num <= 108*/

class Solution {
    public int maximumSwap(int num) {
        char[] numArr = Integer.toString(num).toCharArray();
        int n = numArr.length;
        int maxi = num;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {//Swap two places
                char temp = numArr[i];
                numArr[i] = numArr[j];
                numArr[j] = temp;

                int newNum = Integer.parseInt(new String(numArr)); //Converting back to Integer
                maxi = Math.max(maxi, newNum); //Keeping track of maximum after each swap

                temp = numArr[i];   //Swap back to get the original number back
                numArr[i] = numArr[j];
                numArr[j] = temp;
            }
        }
        return maxi;
    }
}
