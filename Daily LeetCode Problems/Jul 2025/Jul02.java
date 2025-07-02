/* 3333. Find the Original Typed String II
Alice is attempting to type a specific string on her computer.
However, she tends to be clumsy and may press a key for too long, resulting in a character being typed multiple times.
You are given a string word, which represents the final output displayed on Alice's screen. You are also given a positive integer k.
Return the total number of possible original strings that Alice might have intended to type, if she was trying to type a string of size at least k.
Since the answer may be very large, return it modulo 109 + 7.

Example 1:
Input: word = "aabbccdd", k = 7
Output: 5
Explanation:
The possible strings are: "aabbccdd", "aabbccd", "aabbcdd", "aabccdd", and "abbccdd".

Example 2:
Input: word = "aabbccdd", k = 8
Output: 1
Explanation:
The only possible string is "aabbccdd".

Example 3:
Input: word = "aaabbb", k = 3
Output: 8

Constraints:
1 <= word.length <= 5 * 10^5
word consists only of lowercase English letters.
1 <= k <= 2000 */

class Solution {
    int MOD = (int)1e9 + 7;

    public int possibleStringCount(String word, int k) {
        if(k > word.length()){
            return 0;
        }

        List<Integer> freq = new ArrayList<>();
        int count = 1;
        for(int i=1;i<word.length();i++){
            if(word.charAt(i) == word.charAt(i-1)){
                count++;
            }else{
                freq.add(count);
                count = 1;
            }
        }
        freq.add(count);

        long P = 1; // possible strings;
        for(int f : freq){
            P = (P * f) % MOD;
        }

        if(freq.size() >= k){
            return (int)P;
        }

        int n = freq.size();
        int [][]t = new int[n+1][k+1];

        for(int c=k-1;c>=0;c--){
            t[n][c] = 1;
        }

        for(int i=n-1;i>=0;i--){
            int []prefix = new int[k+2];
            for(int h=1;h<=k;h++){
                prefix[h] = (prefix[h-1] + t[i+1][h-1]) % MOD;
            }

            for(int c=k-1;c>=0;c--){
                int l = c + 1;
                int r = c + freq.get(i);

                if(r + 1 > k){
                    r = k - 1;
                }

                if(l <= r){
                    t[i][c] = (prefix[r+1] - prefix[l] + MOD) % MOD;
                }
            }
        }

        long invalidCount = t[0][0];
        return (int)((P - invalidCount + MOD) % MOD);
    }
}
