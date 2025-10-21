/* 3346. Maximum Frequency of an Element After Performing Operations I
You are given an integer array nums and two integers k and numOperations.
You must perform an operation numOperations times on nums, where in each operation you:
Select an index i that was not selected in any previous operations.
Add an integer in the range [-k, k] to nums[i].
Return the maximum possible frequency of any element in nums after performing the operations.

Example 1:
Input: nums = [1,4,5], k = 1, numOperations = 2
Output: 2
Explanation:
We can achieve a maximum frequency of two by:
Adding 0 to nums[1]. nums becomes [1, 4, 5].
Adding -1 to nums[2]. nums becomes [1, 4, 4].

Example 2:
Input: nums = [5,11,20,20], k = 5, numOperations = 1
Output: 2
Explanation:
We can achieve a maximum frequency of two by:
Adding 0 to nums[1].

Constraints:
1 <= nums.length <= 10^5
1 <= nums[i] <= 10^5
0 <= k <= 10^5
0 <= numOperations <= nums.length */

class Solution {
    public int maxFrequency(int[] nums, int k, int numOperations) {
        int maxEl = Arrays.stream(nums).max().getAsInt() + k;
        int []freq = new int[maxEl + 1];

        for(int num : nums){
            freq[num]++;
        }

        for(int i=1;i<=maxEl;i++){
            freq[i] += freq[i-1];
        }

        int res = 0;

        for(int target=0;target<=maxEl;target++){
            if(freq[target] == 0){
                continue;
            }

            int leftNum = Math.max(0, target - k);
            int rightNum = Math.min(maxEl, target + k);
            int totalCount = freq[rightNum] - (leftNum > 0 ? freq[leftNum-1] : 0);
            int targetCount = freq[target] - (target > 0 ? freq[target-1] : 0);
            int needConversion = totalCount - targetCount;
            int maxPossibleFreq = targetCount + Math.min(needConversion, numOperations);

            res = Math.max(res, maxPossibleFreq);
        }

        return res;
    }
}
