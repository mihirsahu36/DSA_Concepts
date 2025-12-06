/* 3578. Count Partitions With Max-Min Difference at Most K
You are given an integer array nums and an integer k.
Your task is to partition nums into one or more non-empty contiguous segments such that in each segment,
the difference between itvs maximum and minimum elements is at most k.
Return the total number of ways to partition nums under this condition.
Since the answer may be too large, return it modulo 109 + 7.

Example 1:
Input: nums = [9,4,1,3,7], k = 4
Output: 6
Explanation:
There are 6 valid partitions where the difference between the maximum and minimum elements in each segment is at most k = 4:
[[9], [4], [1], [3], [7]]
[[9], [4], [1], [3, 7]]
[[9], [4], [1, 3], [7]]
[[9], [4, 1], [3], [7]]
[[9], [4, 1], [3, 7]]
[[9], [4, 1, 3], [7]]

Example 2:
Input: nums = [3,3,4], k = 0
Output: 2
Explanation:
There are 2 valid partitions that satisfy the given conditions:
[[3], [3], [4]]
[[3, 3], [4]]
 
Constraints:
2 <= nums.length <= 5 * 10^4
1 <= nums[i] <= 10^9
0 <= k <= 10^9 */

class Solution {
    public int countPartitions(int[] nums, int k) {
        int n = nums.length, MOD = 1_000_000_007;
        long []dp = new long[n + 1];
        long []pref = new long[n + 1];
        dp[0] = pref[0] = 1;

        Deque<Integer> minD = new ArrayDeque<>();
        Deque<Integer> maxD = new ArrayDeque<>();

        int L = 0;
        for(int R=0;R<n;R++){

            while(!minD.isEmpty() && nums[minD.peekLast()] >= nums[R]){
                minD.pollLast();
            }
            minD.addLast(R);

            while(!maxD.isEmpty() && nums[maxD.peekLast()] <= nums[R]){
                maxD.pollLast();
            }
            maxD.addLast(R);

            while(nums[maxD.peekFirst()] - nums[minD.peekFirst()] > k){
                if(minD.peekFirst() == L){
                    minD.pollFirst();
                }
                if(maxD.peekFirst() == L){
                    maxD.pollFirst();
                }
                L++;
            }

            long ways = pref[R] - (L == 0 ? 0 : pref[L - 1]);
            if(ways < 0){
                ways += MOD;
            }
            dp[R + 1] = ways % MOD;
            pref[R + 1] = (pref[R] + dp[R + 1]) % MOD;
        }

        return (int)dp[n];
    }
}
