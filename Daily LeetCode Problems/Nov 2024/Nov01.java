/*1957. Delete Characters to Make Fancy String
A fancy string is a string where no three consecutive characters are equal.
Given a string s, delete the minimum possible number of characters from s to make it fancy.
Return the final string after the deletion. It can be shown that the answer will always be unique.

Example 1:
Input: s = "leeetcode"
Output: "leetcode"
Explanation:
Remove an 'e' from the first group of 'e's to create "leetcode".
No three consecutive characters are equal, so return "leetcode".

Example 2:
Input: s = "aaabaaaa"
Output: "aabaa"
Explanation:
Remove an 'a' from the first group of 'a's to create "aabaaaa".
Remove two 'a's from the second group of 'a's to create "aabaa".
No three consecutive characters are equal, so return "aabaa".

Example 3:
Input: s = "aab"
Output: "aab"
Explanation: No three consecutive characters are equal, so return "aab".

Constraints:
1 <= s.length <= 105
s consists only of lowercase English letters.*/


class Solution {
    public String makeFancyString(String s) {
    StringBuilder res = new StringBuilder();
    res.append(s.charAt(0)); // appending first character
    int n = s.length();
    int count = 1;
    for(int i=1;i<n;i++){
        if(s.charAt(i) == res.charAt(res.length()-1)){ // if the string character matches with res most recent character
            count++; // increment count as now there will be same character appended
            if(count < 3){ // if consecutive character count is less than 3 append it
                res.append(s.charAt(i));
            }
        }else{ // if character are different than reset the count to 1 and append the character
            count = 1;
            res.append(s.charAt(i));
        }
    }
    return res.toString();
    }
}
