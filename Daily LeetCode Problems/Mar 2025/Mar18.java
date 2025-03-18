/*2401. Longest Nice Subarray
You are given an array nums consisting of positive integers.
We call a subarray of nums nice if the bitwise AND of every pair of elements that are in different positions in the subarray is equal to 0.
Return the length of the longest nice subarray.
A subarray is a contiguous part of an array.
Note that subarrays of length 1 are always considered nice.

Example 1:
Input: nums = [1,3,8,48,10]
Output: 3
Explanation: The longest nice subarray is [3,8,48]. This subarray satisfies the conditions:
- 3 AND 8 = 0.
- 3 AND 48 = 0.
- 8 AND 48 = 0.
It can be proven that no longer nice subarray can be obtained, so we return 3.

Example 2:
Input: nums = [3,1,5,11,13]
Output: 1
Explanation: The length of the longest nice subarray is 1. Any subarray of length 1 can be chosen.

Constraints:
1 <= nums.length <= 10^5
1 <= nums[i] <= 10^9*/

// Approach 1
class Solution {
    public int longestNiceSubarray(int[] nums) {
        int n = nums.length;
        int res = 0;

        for(int i=0;i<n;i++){
            int mask = 0;

            for(int j =i;j<n;j++){
                if((mask & nums[j]) != 0){
                    break;
                }

                res = Math.max(res, j - i + 1);
                mask |= nums[j];
            }
        }

        return res;
    }
}

// Approach 2
class Solution {
    public int longestNiceSubarray(int[] nums) {
        int n = nums.length;
        int i = 0, j = 0;
        int res = 1;
        int mask = 0;

        while(j < n){
            while((mask & nums[j]) != 0){
                mask ^= nums[i];
                i++;
            }

            res = Math.max(res, j - i + 1);
            mask |= nums[j];
            j++;
        }

        return res;
    }
}
