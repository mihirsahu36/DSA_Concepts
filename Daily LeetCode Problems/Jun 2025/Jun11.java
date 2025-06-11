/*3445. Maximum Difference Between Even and Odd Frequency II
You are given a string s and an integer k.
Your task is to find the maximum difference between the frequency of two characters,
freq[a] - freq[b], in a substring subs of s, such that:
subs has a size of at least k.
Character a has an odd frequency in subs.
Character b has an even frequency in subs.
Return the maximum difference.
Note that subs can contain more than 2 distinct characters.

Example 1:
Input: s = "12233", k = 4
Output: -1
Explanation:
For the substring "12233", the frequency of '1' is 1 and the frequency of '3' is 2. The difference is 1 - 2 = -1.

Example 2:
Input: s = "1122211", k = 3
Output: 1
Explanation:
For the substring "11222", the frequency of '2' is 3 and the frequency of '1' is 2. The difference is 3 - 2 = 1.

Example 3:
Input: s = "110", k = 3
Output: -1

Constraints:
3 <= s.length <= 3 * 10^4
s consists only of digits '0' to '4'.
The input is generated that at least one substring has a character with an even frequency and a character with an odd frequency.
1 <= k <= s.length*/

class Solution {
    private int getState(int count_a, int count_b){
        int parity_a = count_a % 2;
        int parity_b = count_b % 2;

        if(parity_a == 0 && parity_b == 0){ // even even
            return 0;
        }
        if(parity_a == 0 && parity_b == 1){ // even odd
            return 1;
        }
        if(parity_a == 1 && parity_b == 0){ // odd even
            return 2;
        }
        return 3;
    }

    public int maxDifference(String s, int k) {
        int n = s.length();
        int res = Integer.MIN_VALUE;

        for(char a='0';a<='4';a++){
            for(char b='0';b<='4';b++){
                if(a == b){
                    continue;
                }

                int []stateMinPrev_a_b = new int[4];
                Arrays.fill(stateMinPrev_a_b, Integer.MAX_VALUE);

                int count_a = 0;
                int count_b = 0;

                int prev_a = 0;
                int prev_b = 0;

                int l = -1;
                int r = 0;

                while(r < n){
                    count_a += (s.charAt(r) == a) ? 1 : 0;
                    count_b += (s.charAt(r) == b) ? 1 : 0;

                    while(r - l >= k && count_b - prev_b >= 2 && count_a - prev_a >= 1){
                        int leftState = getState(prev_a, prev_b);
                        stateMinPrev_a_b[leftState] = Math.min(stateMinPrev_a_b[leftState], prev_a - prev_b);
                        l++;
                        if(s.charAt(l) == a){
                            prev_a++;
                        }
                        if(s.charAt(l) == b){
                            prev_b++;
                        }
                    }

                    int rightState = getState(count_a, count_b);
                    int bestLeftState = rightState ^ 2;
                    int bestMinDiffValueLeft = stateMinPrev_a_b[bestLeftState];

                    if(bestMinDiffValueLeft != Integer.MAX_VALUE){
                        res = Math.max(res, (count_a - count_b) - bestMinDiffValueLeft);
                    }

                    r++;
                }
            }
        }

        return res;
    }
}
