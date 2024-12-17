/*2182. Construct String With Repeat Limit
You are given a string s and an integer repeatLimit. Construct a new string repeatLimitedString using the characters of s such that no letter appears more than repeatLimit times in a row. You do not have to use all characters from s.
Return the lexicographically largest repeatLimitedString possible.
A string a is lexicographically larger than a string b if in the first position where a and b differ, string a has a letter that appears later in the alphabet than the corresponding letter in b. If the first min(a.length, b.length) characters do not differ, then the longer string is the lexicographically larger one.

Example 1:
Input: s = "cczazcc", repeatLimit = 3
Output: "zzcccac"
Explanation: We use all of the characters from s to construct the repeatLimitedString "zzcccac".
The letter 'a' appears at most 1 time in a row.
The letter 'c' appears at most 3 times in a row.
The letter 'z' appears at most 2 times in a row.
Hence, no letter appears more than repeatLimit times in a row and the string is a valid repeatLimitedString.
The string is the lexicographically largest repeatLimitedString possible so we return "zzcccac".
Note that the string "zzcccca" is lexicographically larger but the letter 'c' appears more than 3 times in a row, so it is not a valid repeatLimitedString.

Example 2:
Input: s = "aababab", repeatLimit = 2
Output: "bbabaa"
Explanation: We use only some of the characters from s to construct the repeatLimitedString "bbabaa". 
The letter 'a' appears at most 2 times in a row.
The letter 'b' appears at most 2 times in a row.
Hence, no letter appears more than repeatLimit times in a row and the string is a valid repeatLimitedString.
The string is the lexicographically largest repeatLimitedString possible so we return "bbabaa".
Note that the string "bbabaaa" is lexicographically larger but the letter 'a' appears more than 2 times in a row, so it is not a valid repeatLimitedString.

Constraints:
1 <= repeatLimit <= s.length <= 10^5
s consists of lowercase English letters.*/

class Solution {
    public String repeatLimitedString(String s, int repeatLimit) {
        int []count = new int[26];
        for(char ch : s.toCharArray()){ // keeping count of each alphabet
            count[ch - 'a']++;
        }

        PriorityQueue<Character> pq = new PriorityQueue<>(Comparator.reverseOrder()); // max heap
        for(int i=0;i<26;i++){
            if(count[i] > 0){ // inserting all alphabet whose count is greater than 0
            pq.offer((char)('a' + i));
            }
        }

        StringBuilder res = new StringBuilder();
        while(!pq.isEmpty()){
            char ch = pq.poll(); // get the largest alphabet
            int freq = Math.min(count[ch - 'a'], repeatLimit);

            for(int i=0;i<freq;i++){ // append current alphabet freq number of times
                res.append(ch);
            }
            count[ch - 'a'] -= freq;

            if(count[ch - 'a'] > 0 && !pq.isEmpty()){ // if current alphabet still has remaining occurrence
                char nextChar = pq.poll(); // next largest alphabet
                res.append(nextChar);
                count[nextChar - 'a']--;

                if(count[nextChar - 'a'] > 0){
                    pq.offer(nextChar);
                }
                pq.offer(ch);
            }
        }
        return res.toString();
    }
}
