/*1718. Construct the Lexicographically Largest Valid Sequence
Given an integer n, find a sequence that satisfies all of the following:

The integer 1 occurs once in the sequence.
Each integer between 2 and n occurs twice in the sequence.
For every integer i between 2 and n, the distance between the two occurrences of i is exactly i.
The distance between two numbers on the sequence, a[i] and a[j], is the absolute difference of their indices, |j - i|.

Return the lexicographically largest sequence. It is guaranteed that under the given constraints, there is always a solution.
A sequence a is lexicographically larger than a sequence b (of the same length) if in the first position where a and b differ,
sequence a has a number greater than the corresponding number in b. For example, [0,1,9,0] is lexicographically larger than [0,1,5,6]
because the first position they differ is at the third number, and 9 is greater than 5.

Example 1:
Input: n = 3
Output: [3,1,2,3,2]
Explanation: [2,3,2,1,3] is also a valid sequence, but [3,1,2,3,2] is the lexicographically largest valid sequence.

Example 2:
Input: n = 5
Output: [5,3,1,4,3,5,2,4,2]

Constraints:
1 <= n <= 20*/

class Solution {
    public int[] constructDistancedSequence(int n) {
        int []res = new int [2*n-1];
        boolean []used = new boolean[n+1];

        Arrays.fill(res, -1);
        solve(0, n, res, used);

        return res;
    }

    public boolean solve(int i, int n, int []res, boolean []used){
        if(i >= res.length){
            return true;
        }

        if(res[i] != -1){
            return solve(i+1, n, res, used);
        }

        for(int num=n;num>=1;num--){
            if(used[num]){
                continue;
            }

            used[num] = true;
            res[i] = num;

            if(num == 1){
                if(solve(i+1, n, res, used)){
                    return true;
                }
            }else{
                int j = res[i] + i;
                if(j < res.length && res[j] == -1){
                    res[j] = num;
                    if(solve(i+1, n, res, used)){
                        return true;
                    }
                    res[j] = -1;
                }
            }
            used[num] =false;
            res[i] = -1;
        }
        return false;
    }
}
