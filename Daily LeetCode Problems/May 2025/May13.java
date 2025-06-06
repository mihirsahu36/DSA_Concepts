/*3335. Total Characters in String After Transformations I
You are given a string s and an integer t, representing the number of transformations to perform.
In one transformation, every character in s is replaced according to the following rules:
If the character is 'z', replace it with the string "ab".
Otherwise, replace it with the next character in the alphabet. For example, 'a' is replaced with 'b', 'b' is replaced with 'c', and so on.
Return the length of the resulting string after exactly t transformations.
Since the answer may be very large, return it modulo 109 + 7.

Example 1:
Input: s = "abcyy", t = 2
Output: 7
Explanation:
First Transformation (t = 1):
'a' becomes 'b'
'b' becomes 'c'
'c' becomes 'd'
'y' becomes 'z'
'y' becomes 'z'
String after the first transformation: "bcdzz"
Second Transformation (t = 2):
'b' becomes 'c'
'c' becomes 'd'
'd' becomes 'e'
'z' becomes "ab"
'z' becomes "ab"
String after the second transformation: "cdeabab"
Final Length of the string: The string is "cdeabab", which has 7 characters.

Example 2:
Input: s = "azbk", t = 1
Output: 5
Explanation:
First Transformation (t = 1):
'a' becomes 'b'
'z' becomes "ab"
'b' becomes 'c'
'k' becomes 'l'
String after the first transformation: "babcl"
Final Length of the string: The string is "babcl", which has 5 characters.

Constraints:
1 <= s.length <= 10^5
s consists only of lowercase English letters.
1 <= t <= 10^5*/

class Solution {
    public int lengthAfterTransformations(String s, int t) {
        int MOD = (int)1e9 + 7;
        int []map = new int[26];

        for(char ch : s.toCharArray()){
            map[ch - 'a']++;
        }

        for(int i=1;i<=t;i++){
            int []temp = new int[26];

            for(int j=0;j<26;j++){
                char ch = (char) (j + 'a');
                int freq = map[j];

                if(ch != 'z'){
                    temp[(ch + 1) - 'a'] = (temp[(ch + 1) - 'a'] + freq) % MOD;
                }else{
                    temp['a' - 'a'] = (temp['a' - 'a'] + freq) % MOD;
                    temp['b' - 'a'] = (temp['b' - 'a'] + freq) % MOD;
                }
            }

            map = temp;
        }

        int res = 0;
        for(int i=0;i<26;i++){
            res = (res + map[i]) % MOD;
        }

        return res;
    }
}
