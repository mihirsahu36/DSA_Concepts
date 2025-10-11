/* 3186. Maximum Total Damage With Spell Casting
A magician has various spells.
You are given an array power, where each element represents the damage of a spell.
Multiple spells can have the same damage value.
It is a known fact that if a magician decides to cast a spell with a damage of power[i],
they cannot cast any spell with a damage of power[i] - 2, power[i] - 1, power[i] + 1, or power[i] + 2.
Each spell can be cast only once.
Return the maximum possible total damage that a magician can cast.

Example 1:
Input: power = [1,1,3,4]
Output: 6
Explanation:
The maximum possible damage of 6 is produced by casting spells 0, 1, 3 with damage 1, 1, 4.

Example 2:
Input: power = [7,1,6,6]
Output: 13
Explanation:
The maximum possible damage of 13 is produced by casting spells 1, 2, 3 with damage 1, 6, 6.

Constraints:
1 <= power.length <= 10^5
1 <= power[i] <= 10^9 */


class Solution {
    private int n;
    private Map<Long, Long> freq;
    private long []dp;

    private long solve(int i, List<Long> nums){
        if(i >= n){
            return 0;
        }

        if(dp[i] != -1){
            return dp[i];
        }

        long notTake = solve(i + 1, nums);
        int j = lowerBound(nums, i + 1, nums.get(i) + 3);
        long take = nums.get(i) * freq.get(nums.get(i)) + solve(j, nums);

        return dp[i] = Math.max(take, notTake);
    }

    private int lowerBound(List<Long> nums, int start, long target){
        int low = start, high = nums.size();

        while(low < high){
            int mid = low + (high - low) / 2;

            if(nums.get(mid) < target){
                low = mid + 1;
            }else{
                high = mid;
            }
        }
        
        return low;
    }

    public long maximumTotalDamage(int[] power) {
        freq = new HashMap<>();
        for(int x : power){
            freq.put((long)x, freq.getOrDefault((long)x, 0L) + 1);
        }

        List<Long> nums = new ArrayList<>(freq.keySet());
        Collections.sort(nums);
        n = nums.size();
        dp = new long[n];
        Arrays.fill(dp, -1);

        return solve(0, nums);
    }
}
