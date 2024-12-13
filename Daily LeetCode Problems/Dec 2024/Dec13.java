/*2593. Find Score of an Array After Marking All Elements
You are given an array nums consisting of positive integers.
Starting with score = 0, apply the following algorithm:
Choose the smallest integer of the array that is not marked. If there is a tie, choose the one with the smallest index.
Add the value of the chosen integer to score.
Mark the chosen element and its two adjacent elements if they exist.
Repeat until all the array elements are marked.
Return the score you get after applying the above algorithm.

Example 1:
Input: nums = [2,1,3,4,5,2]
Output: 7
Explanation: We mark the elements as follows:
- 1 is the smallest unmarked element, so we mark it and its two adjacent elements: [2,1,3,4,5,2].
- 2 is the smallest unmarked element, so we mark it and its left adjacent element: [2,1,3,4,5,2].
- 4 is the only remaining unmarked element, so we mark it: [2,1,3,4,5,2].
Our score is 1 + 2 + 4 = 7.

Example 2:
Input: nums = [2,3,5,1,3,2]
Output: 5
Explanation: We mark the elements as follows:
- 1 is the smallest unmarked element, so we mark it and its two adjacent elements: [2,3,5,1,3,2].
- 2 is the smallest unmarked element, since there are two of them, we choose the left-most one, so we mark the one at index 0 and its right adjacent element: [2,3,5,1,3,2].
- 2 is the only remaining unmarked element, so we mark it: [2,3,5,1,3,2].
Our score is 1 + 2 + 2 = 5.

Constraints:
1 <= nums.length <= 10^5
1 <= nums[i] <= 10^6*/

// Approach 1
class Solution {
    public long findScore(int[] nums) {
        int n = nums.length;
        boolean []visited = new boolean[n];

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> { // min heap if element value are same then check for their index
            if(a[0] != b[0]) return Integer.compare(a[0], b[0]); 
            return Integer.compare(a[1], b[1]);});

        for(int i=0;i<n;i++){ // add the values and their index to the heap
            pq.add(new int[] {nums[i], i});
        }

        long score = 0;
        while(!pq.isEmpty()){
            int []element = pq.poll();
            int value = element[0];
            int index = element[1];

            if(!visited[index]){ // add the smallest value to the score skipping all visited index
                score += value;
                visited[index] = true;
                if(index - 1 >= 0){
                    visited[index - 1] = true;
                }
                if(index + 1 < n){
                    visited[index + 1] = true;
                }
            }
        }
        return score;
    }
}



// Approach 2
class Solution {
    public long findScore(int[] nums) {
        int n = nums.length;
        boolean []visited = new boolean[n]; // to store if the the current element and its adjacent are marked visited or not
        int [][] dp = new int[n][2]; // to store value and its index

        for(int i=0;i<n;i++){
            dp[i][0] = nums[i];
            dp[i][1] = i;
        }

        Arrays.sort(dp, (a, b) -> a[0] - b[0]); // sort based on value of element

        long sum = 0;
        for(int []num : dp){
            int value = num[0];
            int index = num[1];

            if(!visited[index]){ // calculating sum and marking the current and its adjacent element visited
                sum += value;
                visited[index] = true;
                if(index - 1 >= 0){
                    visited[index - 1] = true;
                }
                if(index + 1 < n){
                    visited[index + 1] = true;
                }
            }
        }
        return sum;
    }
}
