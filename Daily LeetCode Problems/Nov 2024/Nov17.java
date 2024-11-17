/*862. Shortest Subarray with Sum at Least K
Given an integer array nums and an integer k, return the length of the shortest non-empty subarray of nums with a sum of at least k. If there is no such subarray, return -1.
A subarray is a contiguous part of an array.

Example 1:
Input: nums = [1], k = 1
Output: 1

Example 2:
Input: nums = [1,2], k = 4
Output: -1

Example 3:
Input: nums = [2,-1,2], k = 3
Output: 3

Constraints:
1 <= nums.length <= 105
-105 <= nums[i] <= 105
1 <= k <= 109*/

class Solution {
    public int shortestSubarray(int[] nums, int k) {
        int n = nums.length;
        Deque<Integer> dq = new LinkedList<>();
        long [] cumSum = new long[n]; // stores cumulative sum
        int res = Integer.MAX_VALUE;
        int j = 0;
        while(j<n){ // compute cumulative sum in cumulative sum array
            if(j==0){
                cumSum[j] = nums[j];
            }else{
                cumSum[j] = cumSum[j-1] + nums[j];
            }
            if(cumSum[j]>=k){ //if cumulative sum >= k
                res = Math.min(res, j+1);
            }
            while(!dq.isEmpty() && cumSum[j] - cumSum[dq.peekFirst()] >=k){ // remove indices from where the subarray sum >= k
                res = Math.min(res, j - dq.peekFirst());
                dq.pollFirst(); // remove the front index from the deque
            }
            while(!dq.isEmpty() && cumSum[j] <= cumSum[dq.peekLast()]){ // maintain the montonic property that is the the cumulative some of next index should be greater than previous or else remove the index
                dq.pollLast(); // remove indices that wont required
            }
            dq.offerLast(j); // add the current index to the deque
            j++;
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}
