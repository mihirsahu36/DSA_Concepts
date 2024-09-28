/*Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.

Note that you must do this in-place without making a copy of the array.

 

Example 1:

Input: nums = [0,1,0,3,12]
Output: [1,3,12,0,0]
Example 2:

Input: nums = [0]
Output: [0]*/


class Solution {
    public void moveZeroes(int[] nums) {
        int [] arr=new int[nums.length];
        int n=arr.length;
        int index = 0;
        for(int i=0;i<nums.length;i++){
            if (nums[i] != 0) {
                arr[index++] = nums[i];
            }
        }
        for (int i = index; i < n; i++) {
            arr[i] = 0;
        }
        System.arraycopy(arr, 0, nums, 0, n);
    }
}
