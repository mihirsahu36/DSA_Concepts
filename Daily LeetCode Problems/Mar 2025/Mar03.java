/*2161. Partition Array According to Given Pivot
You are given a 0-indexed integer array nums and an integer pivot. Rearrange nums such that the following conditions are satisfied:
Every element less than pivot appears before every element greater than pivot.
Every element equal to pivot appears in between the elements less than and greater than pivot.
The relative order of the elements less than pivot and the elements greater than pivot is maintained.
More formally, consider every pi, pj where pi is the new position of the ith element and pj is the new position of the jth element.
If i < j and both elements are smaller (or larger) than pivot, then pi < pj.
Return nums after the rearrangement.

Example 1:
Input: nums = [9,12,5,10,14,3,10], pivot = 10
Output: [9,5,3,10,10,12,14]
Explanation: 
The elements 9, 5, and 3 are less than the pivot so they are on the left side of the array.
The elements 12 and 14 are greater than the pivot so they are on the right side of the array.
The relative ordering of the elements less than and greater than pivot is also maintained. [9, 5, 3] and [12, 14] are the respective orderings.

Example 2:
Input: nums = [-3,4,3,2], pivot = 2
Output: [-3,2,4,3]
Explanation: 
The element -3 is less than the pivot so it is on the left side of the array.
The elements 4 and 3 are greater than the pivot so they are on the right side of the array.
The relative ordering of the elements less than and greater than pivot is also maintained. [-3] and [4, 3] are the respective orderings.

Constraints:
1 <= nums.length <= 10^5
-10^6 <= nums[i] <= 10^6
pivot equals to an element of nums.*/

// Approach 1
class Solution {
    public int[] pivotArray(int[] nums, int pivot) {
        int n = nums.length;
        ArrayList<Integer> lessPivot = new ArrayList<>();
        ArrayList<Integer> equalPivot = new ArrayList<>();
        ArrayList<Integer> greaterPivot = new ArrayList<>();

        for(int num : nums){
            if(num < pivot){
                lessPivot.add(num);
            }else if(num == pivot){
                equalPivot.add(num);
            }else{
                greaterPivot.add(num);
            }
        }

        ArrayList<Integer> res = new ArrayList<>();
        res.addAll(lessPivot);
        res.addAll(equalPivot);
        res.addAll(greaterPivot);

        return res.stream().mapToInt(i -> i).toArray();
    }
}

// Approach 2
class Solution {
    public int[] pivotArray(int[] nums, int pivot) {
        int n = nums.length;
        int countLess = 0;
        int countEqual = 0;

        for(int num : nums){
            if(num < pivot){
                countLess++;
            }else if(num == pivot){
                countEqual++;
            }
        }

        int i = 0;
        int j = countLess;
        int k = countLess + countEqual;
        int []res = new int[n];

        for(int num : nums){
            if(num < pivot){
                res[i] = num;
                i++;
            }else if(num == pivot){
                res[j] = num;
                j++; 
            }else{
                res[k] = num;
                k++;
            }
        }

        return res;
    }
}

// Approach 3
class Solution {
    public int[] pivotArray(int[] nums, int pivot) {
        int n = nums.length;
        int []res = new int[n];
        int i = 0, j = n - 1;
        int i_ = 0, j_ = n - 1;

        while(i < n && j >= 0){
            if(nums[i] < pivot){
                res[i_] = nums[i];
                i_++;
            }

            if(nums[j] > pivot){
                res[j_] = nums[j];
                j_--;
            }

            i++;
            j--;
        }

        while(i_ <= j_){
            res[i_] = pivot;
            i_++;
        }

        return res;
    }
}
