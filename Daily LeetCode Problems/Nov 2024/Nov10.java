/*3097. Shortest Subarray With OR at Least K II
You are given an array nums of non-negative integers and an integer k.
An array is called special if the bitwise OR of all of its elements is at least k.
Return the length of the shortest special non-empty 
subarray of nums, or return -1 if no special subarray exists.

Example 1:
Input: nums = [1,2,3], k = 2
Output: 1
Explanation:
The subarray [3] has OR value of 3. Hence, we return 1.

Example 2:
Input: nums = [2,1,8], k = 10
Output: 3
Explanation:
The subarray [2,1,8] has OR value of 11. Hence, we return 3.

Example 3:
Input: nums = [1,2], k = 0
Output: 1
Explanation:
The subarray [1] has OR value of 1. Hence, we return 1.

Constraints:
1 <= nums.length <= 2 * 105
0 <= nums[i] <= 109
0 <= k <= 109*/

class Solution {
    void updateFreq(int []bitFreq, int number, int val){
        for(int i=0;i<32;i++){ // this function is to set the bitFreq by adding or substracting val
            if((number & (1<<i)) != 0){
                bitFreq[i] += val;
            }
        }
    }

    int getNumber(int [] bitFreq){ // this function is to return the integer number
        int number = 0;
        long pow = 1;
        for(int i=0;i<32;++i){
            if(bitFreq[i] > 0){
                number  += pow;
            }
            pow *= 2;
        }
        return number;
    }

    public int minimumSubarrayLength(int[] nums, int k) {
        if(k == 0) return 1;
        int n = nums.length;
        int left = 0;
        int right = 0;
        int minLength = Integer.MAX_VALUE;
        int curOR = 0;
        int []bitFreq = new int[32];

        while(right < n){
            updateFreq(bitFreq, nums[right], 1); // adding 1 to the bitFreq
            curOR |= nums[right];

            while(left <= right && curOR >= k){ // Resizing the window with minLength of subarray which is special
                minLength = Math.min(minLength, right-left+1);
                updateFreq(bitFreq, nums[left], -1); // substracting 1 from the bitFreq
                curOR = getNumber(bitFreq);
                left++;
            }
            right++;
        }
        return minLength == Integer.MAX_VALUE ? -1 : minLength;
    }
}
