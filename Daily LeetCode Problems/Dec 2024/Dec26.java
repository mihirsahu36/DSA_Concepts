/*494. Target Sum
You are given an integer array nums and an integer target.
You want to build an expression out of nums by adding one of the symbols '+' and '-' before each integer in nums and then concatenate all the integers.
For example, if nums = [2, 1], you can add a '+' before 2 and a '-' before 1 and concatenate them to build the expression "+2-1".
Return the number of different expressions that you can build, which evaluates to target.

Example 1:
Input: nums = [1,1,1,1,1], target = 3
Output: 5
Explanation: There are 5 ways to assign symbols to make the sum of nums be target 3.
-1 + 1 + 1 + 1 + 1 = 3
+1 - 1 + 1 + 1 + 1 = 3
+1 + 1 - 1 + 1 + 1 = 3
+1 + 1 + 1 - 1 + 1 = 3
+1 + 1 + 1 + 1 - 1 = 3

Example 2:
Input: nums = [1], target = 1
Output: 1

Constraints:
1 <= nums.length <= 20
0 <= nums[i] <= 1000
0 <= sum(nums[i]) <= 1000
-1000 <= target <= 1000*/

class Solution {
    private int totalSum;

    public int solve(int []nums, int i, int currSum, int target, int [][]t){
        if(i == nums.length){ // if reached end and currSum equals to target then return 1 else return 0
            return currSum == target ? 1 : 0;
        }

        if(t[i][currSum + totalSum] != Integer.MIN_VALUE){ // memoization
            return t[i][currSum + totalSum];
        }

        int plus = solve(nums, i + 1, currSum + nums[i], target, t); // recursion for '+' symbol
        int minus = solve(nums, i + 1, currSum - nums[i], target, t); // recursion for '-' symbol

        return t[i][currSum + totalSum] = plus + minus; // total number of expression that evaluates to target
    }
    public int findTargetSumWays(int[] nums, int target) {
        int n = nums.length;
        totalSum = Arrays.stream(nums).sum(); // total sum of array nums
        int [][] t = new int[n][2 * totalSum + 1];

        for(int []row : t){
            Arrays.fill(row, Integer.MIN_VALUE);
        }
        return solve(nums, 0, 0, target, t);
    }
}
