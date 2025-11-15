/* 3234. Count the Number of Substrings With Dominant Ones
You are given a binary string s.
Return the number of substrings with dominant ones.
A string has dominant ones if the number of ones in the string
is greater than or equal to the square of the number of zeros in the string.

Example 1:
Input: s = "00011"
Output: 5
Explanation:
The substrings with dominant ones are shown in the table below.
i	j	s[i..j]	Number of Zeros	Number of Ones
3	3	1	0	1
4	4	1	0	1
2	3	01	1	1
3	4	11	0	2
2	4	011	1	2

Example 2:
Input: s = "101101"
Output: 16
Explanation:
The substrings with non-dominant ones are shown in the table below.
Since there are 21 substrings total and 5 of them have non-dominant ones,
it follows that there are 16 substrings with dominant ones.
i	j	s[i..j]	Number of Zeros	Number of Ones
1	1	0	1	0
4	4	0	1	0
1	4	0110	2	2
0	4	10110	2	3
1	5	01101	2	3

Constraints:
1 <= s.length <= 4 * 10^4
s consists only of characters '0' and '1'. */

class Solution {
    public int numberOfSubstrings(String s) {
        int n = s.length();
        int []cumCountOne = new int[n];

        cumCountOne[0] = (s.charAt(0) == '1') ? 1 : 0;

        for(int i=1;i<n;i++){
            cumCountOne[i] = cumCountOne[i-1] + (s.charAt(i) == '1' ? 1 : 0);
        }

        int res = 0;

        for(int i=0;i<n;i++){
            for(int j=i;j<n;j++){

                int oneCount = cumCountOne[j] - (i > 0 ? cumCountOne[i-1] : 0);
                int zeroCount = (j - i + 1) - oneCount;

                int z2 = zeroCount * zeroCount;

                if(z2 > oneCount){
                    int waste = z2 - oneCount;
                    j += waste - 1;
                }else if(z2 == oneCount){
                    res++;
                }else{
                    res++;

                    int k = (int)Math.sqrt(oneCount) - zeroCount;
                    int next = j + k;

                    if(next >= n){
                        res += (n - j - 1);
                        break;
                    }else{
                        res += k;
                    }

                    j = next;
                }
            }
        }

        return res;
    }
}
