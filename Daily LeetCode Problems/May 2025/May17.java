/*75. Sort Colors
Given an array nums with n objects colored red, white, or blue, sort them in-place 
so that objects of the same color are adjacent, with the colors in the order red, white, and blue.
We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
You must solve this problem without using the library's sort function.

Example 1:
Input: nums = [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]

Example 2:
Input: nums = [2,0,1]
Output: [0,1,2]

Constraints:
n == nums.length
1 <= n <= 300
nums[i] is either 0, 1, or 2.*/

// Approach 1
class Solution {
    public void sortColors(int[] nums) {
        Arrays.sort(nums); 
    }
}

// Approach 2
class Solution {
    public void sortColors(int[] nums) {
        int count_0 = 0;
        int count_1 = 0;
        int count_2 = 0;

        for(int num : nums){
            if(num == 0) count_0++;
            if(num == 1) count_1++;
            if(num == 2) count_2++;
        }

        for(int i=0;i<nums.length;i++){
            if(count_0 > 0){
                nums[i] = 0;
                count_0--;
            }else if(count_1 > 0){
                nums[i] = 1;
                count_1--;
            }else{
                nums[i] = 2;
                count_2--;
            }
        }
    }
}

// Approach 3
class Solution {
    private void swap(int []nums, int a, int b){
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    public void sortColors(int[] nums) {
        int n = nums.length;
        int i = 0;
        int j = 0;
        int k = n - 1;

        while(j <= k){
            if(nums[j] == 0){
                swap(nums, i, j);
                i++;
                j++;
            }else if(nums[j] == 2){
                swap(nums, j, k);
                k--;
            }else{
                j++; 
            }
        }
    }
}
