/*2381. Shifting Letters II
You are given a string s of lowercase English letters and a 2D integer array shifts where shifts[i] = [starti, endi, directioni]. 
For every i, shift the characters in s from the index starti to the index endi (inclusive) forward if directioni = 1,
or shift the characters backward if directioni = 0.
Shifting a character forward means replacing it with the next letter in the alphabet (wrapping around so that 'z' becomes 'a'). 
Similarly, shifting a character backward means replacing it with the previous letter in the alphabet (wrapping around so that 'a' becomes 'z').
Return the final string after all such shifts to s are applied.

Example 1:
Input: s = "abc", shifts = [[0,1,0],[1,2,1],[0,2,1]]
Output: "ace"
Explanation: Firstly, shift the characters from index 0 to index 1 backward. Now s = "zac".
Secondly, shift the characters from index 1 to index 2 forward. Now s = "zbd".
Finally, shift the characters from index 0 to index 2 forward. Now s = "ace".

Example 2:
Input: s = "dztz", shifts = [[0,0,0],[1,1,1]]
Output: "catz"
Explanation: Firstly, shift the characters from index 0 to index 0 backward. Now s = "cztz".
Finally, shift the characters from index 1 to index 1 forward. Now s = "catz".

Constraints:
1 <= s.length, shifts.length <= 5 * 10^4
shifts[i].length == 3
0 <= starti <= endi < s.length
0 <= directioni <= 1
s consists of lowercase English letters.*/

class Solution {
    public String shiftingLetters(String s, int[][] shifts) {
        int n = s.length();
        int []diff = new int[n]; // difference array

        for(int []shift : shifts){
            int start = shift[0]; // start index
            int end = shift[1]; // ending index
            int dir = shift[2]; // direction

            if(dir == 1){ // forward shift
                diff[start] += 1;  // if direction is 1 then add 1
                if(end + 1 < n){
                    diff[end + 1] -= 1;
                }
            }else{ // backward shift
                diff[start] -= 1; // if direction is 0 then substract 1
                if(end + 1 < n){
                    diff[end + 1] += 1;
                }
            }
        }
        for(int i=1;i<n;i++){ // cummulative array, compute the prefix sum to get the net shifts for each character
            diff[i] += diff[i-1];
        }

        StringBuilder res = new StringBuilder(s);
        for(int i=0;i<n;i++){
            int shift = diff[i] % 26; // ensure the shift is within [0, 25] range
            if(shift < 0){ // handle negative shift backward movement
                shift += 26;
            }

            // apply shift to the characters
            char newChar = (char)(((res.charAt(i) - 'a' + shift) % 26) + 'a');
            res.setCharAt(i, newChar);
        }
        return res.toString();
    }
}
