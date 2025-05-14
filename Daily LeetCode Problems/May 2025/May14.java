/*3337. Total Characters in String After Transformations II
You are given a string s consisting of lowercase English letters,
an integer t representing the number of transformations to perform, and an array nums of size 26.
In one transformation, every character in s is replaced according to the following rules:
Replace s[i] with the next nums[s[i] - 'a'] consecutive characters in the alphabet.
For example, if s[i] = 'a' and nums[0] = 3, the character 'a' transforms into the next 3 consecutive characters ahead of it, which results in "bcd".
The transformation wraps around the alphabet if it exceeds 'z'.
For example, if s[i] = 'y' and nums[24] = 3, the character 'y' transforms into the next 3 consecutive characters ahead of it, which results in "zab".
Return the length of the resulting string after exactly t transformations.
Since the answer may be very large, return it modulo 109 + 7.

Example 1:
Input: s = "abcyy", t = 2, nums = [1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2]
Output: 7
Explanation:
First Transformation (t = 1):
'a' becomes 'b' as nums[0] == 1
'b' becomes 'c' as nums[1] == 1
'c' becomes 'd' as nums[2] == 1
'y' becomes 'z' as nums[24] == 1
'y' becomes 'z' as nums[24] == 1
String after the first transformation: "bcdzz"
Second Transformation (t = 2):
'b' becomes 'c' as nums[1] == 1
'c' becomes 'd' as nums[2] == 1
'd' becomes 'e' as nums[3] == 1
'z' becomes 'ab' as nums[25] == 2
'z' becomes 'ab' as nums[25] == 2
String after the second transformation: "cdeabab"
Final Length of the string: The string is "cdeabab", which has 7 characters.

Example 2:
Input: s = "azbk", t = 1, nums = [2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2]
Output: 8
Explanation:
First Transformation (t = 1):
'a' becomes 'bc' as nums[0] == 2
'z' becomes 'ab' as nums[25] == 2
'b' becomes 'cd' as nums[1] == 2
'k' becomes 'lm' as nums[10] == 2
String after the first transformation: "bcabcdlm"
Final Length of the string: The string is "bcabcdlm", which has 8 characters.

Constraints:
1 <= s.length <= 10^5
s consists only of lowercase English letters.
1 <= t <= 10^9
nums.length == 26
1 <= nums[i] <= 25*/

public class Solution {
    private static final int MOD = (int)1e9 + 7;
    private static final int size = 26;

    private List<List<Integer>> matrixMultiplication(List<List<Integer>> A, List<List<Integer>> B){ // matrix multiplication
        List<List<Integer>> res = new ArrayList<>();

        for(int i=0;i<size;i++){
            List<Integer> row = new ArrayList<>(Collections.nCopies(size, 0));
            res.add(row);
        }

        for(int i=0;i<size;++i){
            for(int j=0;j<size;++j){
                long sum = 0;
                for(int k=0;k<size;++k){
                    sum = (sum + 1L * A.get(i).get(k) * B.get(k).get(j)) % MOD;
                }
                res.get(i).set(j, (int)sum);
            }
        }
        return res;
    }

    private List<List<Integer>> matrixExponentiation(List<List<Integer>> base, int exponent){ // matrix exponentiation
        // Identity matrix
        List<List<Integer>> identity = new ArrayList<>();
        for(int i=0;i<size;i++){
            List<Integer> row = new ArrayList<>(Collections.nCopies(size, 0));
            row.set(i, 1);
            identity.add(row);
        }

        if(exponent == 0){
            return identity;
        }

        List<List<Integer>> half = matrixExponentiation(base, exponent / 2);
        List<List<Integer>> res = matrixMultiplication(half, half);

        if(exponent % 2 == 1){
            res = matrixMultiplication(res, base);
        }

        return res;
    }

    public int lengthAfterTransformations(String s, int t, List<Integer> nums) {
        int[] freq = new int[size];

        for(char ch : s.toCharArray()){
            freq[ch - 'a']++;
        }

        List<List<Integer>> T = new ArrayList<>(); // build transformation matrix T
        for(int i=0;i<size;i++){
            T.add(new ArrayList<>(Collections.nCopies(size, 0)));
        }

        for(int i=0;i<size;++i){
            for(int add=1;add<=nums.get(i);++add){
                int newIndex = (i + add) % size;
                T.get(newIndex).set(i, T.get(newIndex).get(i) + 1);
            }
        }

        List<List<Integer>> res = matrixExponentiation(T, t);

        int[] updatedFreq = new int[size]; // apply transformation to frequency vector
        for(int i=0;i<size;++i){
            long value = 0;
            for(int j=0;j<size;++j){
                value = (value + 1L * res.get(i).get(j) * freq[j]) % MOD;
            }
            updatedFreq[i] = (int)value;
        }

        int resLen = 0; // compute final length
        for(int val : updatedFreq){
            resLen = (resLen + val) % MOD;
        }

        return resLen;
    }
}
