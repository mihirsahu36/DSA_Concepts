/*214. Shortest Palindrome

You are given a string s. You can convert s to a palindrome by adding characters in front of it.
Return the shortest palindrome you can find by performing this transformation.

Example 1:
Input: s = "aacecaaa"
Output: "aaacecaaa"
Example 2:
Input: s = "abcd"
Output: "dcbabcd"
 

Constraints:
0 <= s.length <= 5 * 104
s consists of lowercase English letters only.*/


class Solution {
    public String shortestPalindrome(String s) {
        String rev = new StringBuilder(s).reverse().toString();
        for(int i=0;i<rev.length();i++){
            if(s.startsWith(rev.substring(i))){
                return rev.substring(0,i) + s;
            }
        }
        return rev + s;
    }
}
