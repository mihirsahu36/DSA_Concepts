/*1371. Find the Longest Substring Containing Vowels in Even Counts

Given the string s, return the size of the longest substring containing each vowel an even number of times. That is, 'a', 'e', 'i', 'o', and 'u' must appear an even number of times.

Example 1:
Input: s = "eleetminicoworoep"
Output: 13
Explanation: The longest substring is "leetminicowor" which contains two each of the vowels: e, i and o and zero of the vowels: a and u.

Example 2:
Input: s = "leetcodeisgreat"
Output: 5
Explanation: The longest substring is "leetc" which contains two e's.

Example 3:
Input: s = "bcbcbc"
Output: 6
Explanation: In this case, the given string "bcbcbc" is the longest because all vowels: a, e, i, o and u appear zero times.*/


class Solution {
    public int findTheLongestSubstring(String s) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        
        int mask = 0, maxLength = 0;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (c == 'a') mask ^= 1 << 0;
            else if (c == 'e') mask ^= 1 << 1;
            else if (c == 'i') mask ^= 1 << 2;
            else if (c == 'o') mask ^= 1 << 3;
            else if (c == 'u') mask ^= 1 << 4;
            
            if (map.containsKey(mask)) {
                maxLength = Math.max(maxLength, i - map.get(mask));
            } else {
                map.put(mask, i);
            }
        }
        
        return maxLength;
    }
}
