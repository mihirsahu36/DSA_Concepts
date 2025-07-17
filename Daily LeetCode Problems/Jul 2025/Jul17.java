/* 3202. Find the Maximum Length of Valid Subsequence II
You are given an integer array nums and a positive integer k.
A subsequence sub of nums with length x is called valid if it satisfies:
(sub[0] + sub[1]) % k == (sub[1] + sub[2]) % k == ... == (sub[x - 2] + sub[x - 1]) % k.
Return the length of the longest valid subsequence of nums.

Example 1:
Input: nums = [1,2,3,4,5], k = 2
Output: 5
Explanation:
The longest valid subsequence is [1, 2, 3, 4, 5].

Example 2:
Input: nums = [1,4,2,3,1,4], k = 3
Output: 4
Explanation:
The longest valid subsequence is [1, 4, 1, 4].

Constraints:
2 <= nums.length <= 10^3
1 <= nums[i] <= 10^7
1 <= k <= 10^3 */

class Solution {
    public int maximumLength(int[] nums, int k) {
        int n = nums.length;
        int [][]t = new int[k][n];
        for(int i=0;i<k;i++){
            for(int j=0;j<n;j++){
                t[i][j] = 1;
            }
        }

        int maxSub = 1;
        for(int i=1;i<n;i++){
            for(int j=0;j<i;j++){
                int mod = (nums[i] + nums[j]) % k;

                t[mod][i] = Math.max(t[mod][i], 1 + t[mod][j]);
                maxSub = Math.max(maxSub, t[mod][i]);
            }
        }

        return maxSub;
    }
}
