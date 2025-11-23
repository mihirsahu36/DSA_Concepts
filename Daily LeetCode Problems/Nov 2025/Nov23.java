/* 1262. Greatest Sum Divisible by Three
Given an integer array nums, return the maximum possible sum of elements of the array such that it is divisible by three.

Example 1:
Input: nums = [3,6,5,1,8]
Output: 18
Explanation: Pick numbers 3, 6, 1 and 8 their sum is 18 (maximum sum divisible by 3).

Example 2:
Input: nums = [4]
Output: 0

Explanation: Since 4 is not divisible by 3, do not pick any number.
Example 3:
Input: nums = [1,2,3,4,4]
Output: 12
Explanation: Pick numbers 1, 3, 4 and 4 their sum is 12 (maximum sum divisible by 3).

Constraints:
1 <= nums.length <= 4 * 10^4
1 <= nums[i] <= 10^4 */

class Solution {
    private int solve(int i, int remainder, int []nums, int [][]t){
        if(i >= nums.length){
            return (remainder == 0) ? 0 : Integer.MIN_VALUE;
        }

        if(t[i][remainder] != -1){
            return t[i][remainder];
        }

        int pick = nums[i] + solve(i + 1, (remainder + nums[i]) % 3, nums, t);
        int notPick = solve(i + 1, remainder, nums, t);

        return t[i][remainder] = Math.max(pick, notPick);
    }

    public int maxSumDivThree(int[] nums) {
        int n = nums.length;
        int [][]t = new int[n][3];

        for(int i = 0; i < n; i++){
            Arrays.fill(t[i], -1);
        }

        return solve(0, 0, nums, t);
    }
}
