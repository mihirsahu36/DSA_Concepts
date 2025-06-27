/*2014. Longest Subsequence Repeated k Times
You are given a string s of length n, and an integer k. You are tasked to find the longest subsequence repeated k times in string s.
A subsequence is a string that can be derived from another string by deleting some or no characters without changing the order of the remaining characters.
A subsequence seq is repeated k times in the string s if seq * k is a subsequence of s, where seq * k represents a string constructed by concatenating seq k times.
For example, "bba" is repeated 2 times in the string "bababcba", because the string "bbabba",
constructed by concatenating "bba" 2 times, is a subsequence of the string "bababcba".
Return the longest subsequence repeated k times in string s.
If multiple such subsequences are found, return the lexicographically largest one. If there is no such subsequence, return an empty string.

Example 1:
Input: s = "letsleetcode", k = 2
Output: "let"
Explanation: There are two longest subsequences repeated 2 times: "let" and "ete".
"let" is the lexicographically largest one.

Example 2:
Input: s = "bb", k = 2
Output: "b"
Explanation: The longest subsequence repeated 2 times is "b".

Example 3:
Input: s = "ab", k = 2
Output: ""
Explanation: There is no subsequence repeated 2 times. Empty string is returned.

Constraints:
n == s.length
2 <= n, k <= 2000
2 <= n < k * 8
s consists of lowercase English letters.*/

class Solution {
    String res = "";

    private boolean isSubsequence(String s, String sub, int k){
        int i = 0, j = 0;
        int n = s.length(), len = sub.length();

        while(i < n && j < k * len){
            if(s.charAt(i) == sub.charAt(j % len)){
                j++;
            }
            i++;
        }

        return j == k * len;
    }

    private boolean solve(String s, StringBuilder curr, boolean []canUse, int []reqFreq, int k, int maxLen){
        if(curr.length() == maxLen){
            if(isSubsequence(s, curr.toString(), k)){
                res = curr.toString();
                return true;
            }
            return false;
        }

        for(int i=25;i>=0;i--){
            if(!canUse[i] || reqFreq[i] == 0){
                continue;
            }

            curr.append((char)(i + 'a'));
            reqFreq[i]--;

            if(solve(s, curr, canUse, reqFreq, k, maxLen)){
                return true;
            }

            curr.deleteCharAt(curr.length() - 1);
            reqFreq[i]++;
        }

        return false;
    }

    public String longestSubsequenceRepeatedK(String s, int k) {
        int n = s.length();
        int []freq = new int[26];

        for(char ch : s.toCharArray()){
            freq[ch - 'a']++;
        }

        boolean []canUse = new boolean[26];
        int []reqFreq = new int[26];

        for(int i=0;i<26;i++){
            if(freq[i] >= k){
                canUse[i] = true;
                reqFreq[i] = freq[i] / k;
            }
        }

        int maxLen = n / k;

        for(int len=maxLen;len>=1;len--){
            int []tempReqFreq = reqFreq.clone();
            StringBuilder curr = new StringBuilder();

            if(solve(s, curr, canUse, tempReqFreq, k, len)){
                return res;
            }
        }

        return res;
    }
}
