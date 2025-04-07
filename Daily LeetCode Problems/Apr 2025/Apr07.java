/*416. Partition Equal Subset Sum
Given an integer array nums, return true if you can partition the array into two subsets
such that the sum of the elements in both subsets is equal or false otherwise. 

Example 1:
Input: nums = [1,5,11,5]
Output: true
Explanation: The array can be partitioned as [1, 5, 5] and [11].

Example 2:
Input: nums = [1,2,3,5]
Output: false
Explanation: The array cannot be partitioned into equal sum subsets.

Constraints:
1 <= nums.length <= 200
1 <= nums[i] <= 100*/

class Solution {
    int [][]t = new int[201][20001];

    public boolean solve(List<Integer> nums, int i, int x){
        if(x == 0){
            return true;
        }

        if(i >= nums.size()){
            return false;
        }

        if(t[i][x] != -1){
            return t[i][x] == 1;
        }

        boolean take = false;
        if(nums.get(i) <= x){
            take = solve(nums, i + 1, x - nums.get(i));
        }

        boolean notTake = solve(nums, i + 1, x);

        t[i][x] = (take || notTake) ? 1 : 0;
        return take || notTake;
    }

    public boolean canPartition(int[] numsArr) {
        List<Integer> nums = new ArrayList<>();
        for(int num : numsArr){
            nums.add(num);
        }

        int n = nums.size();
        int S = 0;
        for(int num : nums){
            S += num;
        }

        if(S % 2 != 0){
            return false;
        }

        for(int []row : t){
            Arrays.fill(row, -1);
        }

        int x = S / 2;

        return solve(nums, 0, x);
    }
}
