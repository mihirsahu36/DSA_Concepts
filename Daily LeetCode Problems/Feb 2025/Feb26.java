/*1749. Maximum Absolute Sum of Any Subarray
You are given an integer array nums. The absolute sum of a subarray [numsl, numsl+1, ..., numsr-1, numsr] is abs(numsl + numsl+1 + ... + numsr-1 + numsr).
Return the maximum absolute sum of any (possibly empty) subarray of nums.
Note that abs(x) is defined as follows:
If x is a negative integer, then abs(x) = -x.
If x is a non-negative integer, then abs(x) = x.

Example 1:
Input: nums = [1,-3,2,3,-4]
Output: 5
Explanation: The subarray [2,3] has absolute sum = abs(2+3) = abs(5) = 5.

Example 2:
Input: nums = [2,-5,1,-4,3,-2]
Output: 8
Explanation: The subarray [-5,1,-4] has absolute sum = abs(-5+1-4) = abs(-8) = 8.

Constraints:
1 <= nums.length <= 10^5
-10^4 <= nums[i] <= 10^4*/

class Solution {
    public int maxAbsoluteSum(int[] nums) {
        int n = nums.length;
        int maxSum = nums[0];
        int minSum = nums[0];
        int currMaxSum = nums[0];
        int currMinSum = nums[0];

        for(int i=1;i<n;i++){
            currMaxSum = Math.max(nums[i], currMaxSum + nums[i]); // kadane's algorithm for max subarray sum
            maxSum = Math.max(maxSum, currMaxSum);

            currMinSum = Math.min(nums[i], currMinSum + nums[i]); // kadane's algorithm for min subarray sum
            minSum = Math.min(minSum, currMinSum);
        }

        return Math.max(maxSum, Math.abs(minSum));
    }
}
