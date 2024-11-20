/*2516. Take K of Each Character From Left and Right
You are given a string s consisting of the characters 'a', 'b', and 'c' and a non-negative integer k. Each minute, you may take either the leftmost character of s, or the rightmost character of s.
Return the minimum number of minutes needed for you to take at least k of each character, or return -1 if it is not possible to take k of each character.

Example 1:
Input: s = "aabaaaacaabc", k = 2
Output: 8
Explanation: 
Take three characters from the left of s. You now have two 'a' characters, and one 'b' character.
Take five characters from the right of s. You now have four 'a' characters, two 'b' characters, and two 'c' characters.
A total of 3 + 5 = 8 minutes is needed.
It can be proven that 8 is the minimum number of minutes needed.

Example 2:
Input: s = "a", k = 1
Output: -1
Explanation: It is not possible to take one 'b' or 'c' so return -1.
 
Constraints:
1 <= s.length <= 105
s consists of only the letters 'a', 'b', and 'c'.
0 <= k <= s.length*/

class Solution {
    public int takeCharacters(String s, int k) {
        int n = s.length();
        int countA = 0, countB = 0, countC = 0;

        for(char ch : s.toCharArray()){ // find the total count of a, b, c
            if(ch == 'a') countA++;
            else if(ch == 'b') countB++;
            else if(ch == 'c') countC++;
        }

        if(countA < k || countB < k || countC < k){ // if count of a, b, c less than k then nothing can be removed
            return -1;
        }

        int i = 0, j = 0;
        int notRemoveWindowSize = 0;

        while(j < n){ // reduce count of character at index j
            if(s.charAt(j) == 'a'){
                countA--;
            }else if(s.charAt(j) == 'b'){
                countB--;
            }else if(s.charAt(j) == 'c'){
                countC--;
            }

            while(i<=j && (countA < k || countB < k || countC < k)){ // if count of any character gets below k then shrink the window from left
                if(s.charAt(i) == 'a'){
                    countA++;
                }else if(s.charAt(i) == 'b'){
                    countB++;
                }else if(s.charAt(i) == 'c'){
                    countC++;
                }
                i++;
            }
            notRemoveWindowSize = Math.max(notRemoveWindowSize, j-i+1);
            j++;
        }
        return n - notRemoveWindowSize;
    }
}
