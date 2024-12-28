/*689. Maximum Sum of 3 Non-Overlapping Subarrays
Given an integer array nums and an integer k, find three non-overlapping subarrays of length k with maximum sum and return them.
Return the result as a list of indices representing the starting position of each interval (0-indexed). If there are multiple answers, return the lexicographically smallest one.

Example 1:
Input: nums = [1,2,1,2,6,7,5,1], k = 2
Output: [0,3,5]
Explanation: Subarrays [1, 2], [2, 6], [7, 5] correspond to the starting indices [0, 3, 5].
We could have also taken [2, 1], but an answer of [1, 3, 5] would be lexicographically larger.

Example 2:
Input: nums = [1,2,1,2,1,2,1,2,1], k = 2
Output: [0,2,4]

Constraints:
1 <= nums.length <= 2 * 10^4
1 <= nums[i] < 2^16
1 <= k <= floor(nums.length / 3)*/

class Solution {
    private int [][]dp = new int [20001][4]; // memoization

    private int helper(int []sum, int k, int idx, int rem){ // helper function to calculate max sum
        if(rem == 0) return 0; // no more subarrays to pick
        if(idx >= sum.length) return Integer.MIN_VALUE; // out of bounds

        if(dp[idx][rem] != -1){
            return dp[idx][rem];
        }

        int take = sum[idx] + helper(sum, k, idx + k, rem - 1); // take the current subarray
        int notTake = helper(sum, k, idx + 1, rem); // skip the current element

        dp[idx][rem] = Math.max(take, notTake);
        return dp[idx][rem];
    }

    private void solve(int []sum, int k, int idx, int rem, List<Integer> indices){
        if(rem == 0 || idx >= sum.length) return; // done or out of bounds

        int take = sum[idx] + helper(sum, k, idx + k, rem - 1); // compute the result to take the current subarray
        int notTake = helper(sum, k, idx + 1, rem); // compute the resul to skip the current element

        if(take >= notTake){ // choose to take the subarray if it's better
            indices.add(idx);
            solve(sum, k, idx + k, rem - 1, indices);
        }else{ // otherwise proceed with notTake case
            solve(sum, k, idx + 1, rem, indices);
        }
    }

    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        for(int []row : dp){
            Arrays.fill(row, -1);
        }

        int n = nums.length - k + 1;
        int []sum = new int [n];
        int windowSum = 0;
        int i = 0, j = 0;

        while(j < nums.length){ // calculate the sum of all sliding windows of size k
            windowSum += nums[j];

            if(j - i + 1 == k){
                sum[i] = windowSum;
                windowSum -= nums[i];
                i++;
            }
            j++;
        }
        List<Integer> indices = new ArrayList<>();
        solve(sum, k, 0, 3, indices); // find the indices of the 3 subarrays with max sum

        return indices.stream().mapToInt(Integer :: intValue).toArray(); // convert the indices to array and return
    }
}
