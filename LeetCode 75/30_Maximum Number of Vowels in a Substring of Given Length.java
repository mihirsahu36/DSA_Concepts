/*1456. Maximum Number of Vowels in a Substring of Given Length
Given a string s and an integer k, return the maximum number of vowel letters in any substring of s with length k.
Vowel letters in English are 'a', 'e', 'i', 'o', and 'u'.

Example 1:
Input: s = "abciiidef", k = 3
Output: 3
Explanation: The substring "iii" contains 3 vowel letters.
Example 2:
Input: s = "aeiou", k = 2
Output: 2
Explanation: Any substring of length 2 contains 2 vowels.
Example 3:
Input: s = "leetcode", k = 3
Output: 2
Explanation: "lee", "eet" and "ode" contain 2 vowels.*/


class Solution {
    public boolean vowel(char c){
        if(c=='a' || c== 'e' || c== 'i' || c== 'o' || c== 'u')
            return true;
        return false;
    }
    public int maxVowels(String s, int k) {
        int count = 0;
        int maxCount = 0;
        for (int index = 0; index < k; index++){
            if(vowel(s.charAt(index))){
                count++;
            }
        }
        maxCount = count;
        for(int i=k;i<s.length();i++){
            if(vowel(s.charAt(i)))
                count++;
            if(vowel(s.charAt(i-k)))
                count--;
            maxCount = Math.max(maxCount,count);
        }
        return maxCount;
        }
    }
