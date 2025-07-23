/* 1717. Maximum Score From Removing Substrings
You are given a string s and two integers x and y. You can perform two types of operations any number of times.
Remove substring "ab" and gain x points.
For example, when removing "ab" from "cabxbae" it becomes "cxbae".
Remove substring "ba" and gain y points.
For example, when removing "ba" from "cabxbae" it becomes "cabxe".
Return the maximum points you can gain after applying the above operations on s.

Example 1:
Input: s = "cdbcbbaaabab", x = 4, y = 5
Output: 19
Explanation:
- Remove the "ba" underlined in "cdbcbbaaabab". Now, s = "cdbcbbaaab" and 5 points are added to the score.
- Remove the "ab" underlined in "cdbcbbaaab". Now, s = "cdbcbbaa" and 4 points are added to the score.
- Remove the "ba" underlined in "cdbcbbaa". Now, s = "cdbcba" and 5 points are added to the score.
- Remove the "ba" underlined in "cdbcba". Now, s = "cdbc" and 5 points are added to the score.
Total score = 5 + 4 + 5 + 5 = 19.

Example 2:
Input: s = "aabbaaxybbaabb", x = 5, y = 4
Output: 20

Constraints:
1 <= s.length <= 10^5
1 <= x, y <= 10^4
s consists of lowercase English letters. */

// Approach 1
class Solution {
    public int maximumGain(String s, int x, int y) {
        int n = s.length();
        int score = 0;

        String maxStr = (x > y) ? "ab" : "ba";
        String minStr;
        if(maxStr.equals("ab")){
            minStr = "ba";
        }else{
            minStr = "ab";
        }

        String temp1 = removeSubstring(s, maxStr);
        int len = temp1.length();
        int removedPairsCount = (n - len) / 2;
        score += removedPairsCount * Math.max(x, y);

        String temp2 = removeSubstring(temp1, minStr);
        removedPairsCount = (len - temp2.length()) / 2;
        score += removedPairsCount * Math.min(x, y);

        return score;
    }

    private String removeSubstring(String inputStr, String matchStr){
        Stack<Character> stack = new Stack<>();

        for(char ch : inputStr.toCharArray()){
            if(!stack.isEmpty() && ch == matchStr.charAt(1) && stack.peek() == matchStr.charAt(0)){
                stack.pop();
            }else{
                stack.push(ch);
            }
        }

        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }

        return sb.reverse().toString();
    }
}

// Approach 2
class Solution {
    public int maximumGain(String s, int x, int y) {
        int n = s.length();
        int score = 0;

        String maxStr = (x > y) ? "ab" : "ba";
        String minStr;
        if(maxStr.equals("ab")){
            minStr = "ba";
        }else{
            minStr = "ab";
        }

        String temp1 = removeSubstring(s, maxStr);
        int len = temp1.length();
        int removedPairsCount = (n - len) / 2;
        score += removedPairsCount * Math.max(x, y);

        String temp2 = removeSubstring(temp1, minStr);
        removedPairsCount = (len - temp2.length()) / 2;
        score += removedPairsCount * Math.min(x, y);

        return score;
    }

    private String removeSubstring(String inputStr, String matchStr){
        StringBuilder sb = new StringBuilder();
        int j = 0;

        for(int i=0;i<inputStr.length();i++){
            sb.append(inputStr.charAt(i));
            j++;

            if(j > 1 && sb.charAt(j - 2) == matchStr.charAt(0) && sb.charAt(j - 1) == matchStr.charAt(1)){
                sb.delete(j - 2, j);
                j -= 2;
            }
        }

        return sb.toString();
    }
}
