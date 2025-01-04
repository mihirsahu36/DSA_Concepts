/*1930. Unique Length-3 Palindromic Subsequences
Given a string s, return the number of unique palindromes of length three that are a subsequence of s.
Note that even if there are multiple ways to obtain the same subsequence, it is still only counted once.
A palindrome is a string that reads the same forwards and backwards.
A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.
For example, "ace" is a subsequence of "abcde".

Example 1:
Input: s = "aabca"
Output: 3
Explanation: The 3 palindromic subsequences of length 3 are:
- "aba" (subsequence of "aabca")
- "aaa" (subsequence of "aabca")
- "aca" (subsequence of "aabca")

Example 2:
Input: s = "adc"
Output: 0
Explanation: There are no palindromic subsequences of length 3 in "adc".

Example 3:
Input: s = "bbcbaba"
Output: 4
Explanation: The 4 palindromic subsequences of length 3 are:
- "bbb" (subsequence of "bbcbaba")
- "bcb" (subsequence of "bbcbaba")
- "bab" (subsequence of "bbcbaba")
- "aba" (subsequence of "bbcbaba")

Constraints:
3 <= s.length <= 10^5
s consists of only lowercase English letters.*/

class Solution {
    public int countPalindromicSubsequence(String s) {
        int []first = new int[26]; // to store first occurrence
        int []last = new int[26]; // to store last occurrence
        
        Arrays.fill(first, -1);
        Arrays.fill(last, -1);

        for(int i=0;i<s.length();i++){ // to iterate through the string
            int curr = s.charAt(i) - 'a'; // to store character respective to its index

            if(first[curr] == -1){ // if it is first visit
                first[curr] = i; // update the index
            }
            last[curr] = i; // last occurence visit, update the index
        }
        int res = 0;
        for(int i=0;i<26;i++){
            if(first[i] == -1){
                continue;
            }
            HashSet<Character> set = new HashSet<>(); // to store unique character
            for(int middle=first[i]+1;middle<last[i];middle++){ // iterate between first and last
                set.add(s.charAt(middle)); // add all character in between
            }
            res += set.size();
        }
        return res;
    }
}
