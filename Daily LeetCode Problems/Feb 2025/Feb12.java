/*2342. Max Sum of a Pair With Equal Sum of Digits
You are given a 0-indexed array nums consisting of positive integers. You can choose two indices i and j, such that i != j,
and the sum of digits of the number nums[i] is equal to that of nums[j].
Return the maximum value of nums[i] + nums[j] that you can obtain over all possible indices i and j that satisfy the conditions.

Example 1:
Input: nums = [18,43,36,13,7]
Output: 54
Explanation: The pairs (i, j) that satisfy the conditions are:
- (0, 2), both numbers have a sum of digits equal to 9, and their sum is 18 + 36 = 54.
- (1, 4), both numbers have a sum of digits equal to 7, and their sum is 43 + 7 = 50.
So the maximum sum that we can obtain is 54.

Example 2:
Input: nums = [10,12,19,14]
Output: -1
Explanation: There are no two numbers that satisfy the conditions, so we return -1.

Constraints:
1 <= nums.length <= 10^5
1 <= nums[i] <= 10^9*/

// Approach 1 (Using HashMap)
class Solution {
    private int getDigitSum(int num){
        int sum = 0;

        while(num > 0){
            sum += (num % 10);
            num /= 10;
        }
        return sum;
    }

    public int maximumSum(int[] nums) {
       int n = nums.length;
       int res = -1;
       HashMap<Integer, Integer> map = new HashMap<>();

       for(int i=0;i<n;i++){
        int digitSum = getDigitSum(nums[i]);

        if(map.containsKey(digitSum)){
            res = Math.max(res, nums[i] + map.get(digitSum));
        }
        map.put(digitSum, Math.max(map.getOrDefault(digitSum, 0), nums[i]));
       }

       return res; 
    }
}

// Approach 2 (Using Array)
class Solution {
    private int getDigitSum(int num){
        int sum = 0;

        while(num > 0){
            sum += (num % 10);
            num /= 10;
        }
        return sum;
    }

    public int maximumSum(int[] nums) {
       int n = nums.length;
       int res = -1;
       int []map = new int[82];

       for(int i=0;i<n;i++){
        int digitSum = getDigitSum(nums[i]);

        if(map[digitSum] > 0){
            res = Math.max(res, nums[i] + map[digitSum]);
        }
        map[digitSum] = Math.max(map[digitSum], nums[i]);
       }

       return res; 
    }
}
