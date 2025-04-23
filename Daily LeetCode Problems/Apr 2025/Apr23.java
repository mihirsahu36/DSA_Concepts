/*1399. Count Largest Group
You are given an integer n.
Each number from 1 to n is grouped according to the sum of its digits.
Return the number of groups that have the largest size.

Example 1:
Input: n = 13
Output: 4
Explanation: There are 9 groups in total, they are grouped according sum of its digits of numbers from 1 to 13:
[1,10], [2,11], [3,12], [4,13], [5], [6], [7], [8], [9].
There are 4 groups with largest size.

Example 2:
Input: n = 2
Output: 2
Explanation: There are 2 groups [1], [2] of size 1.

Constraints:
1 <= n <= 10^4*/

class Solution {
    private int findDigitsSum(int num){
        int sum = 0;
        while(num > 0){
            sum += num % 10;
            num /= 10;
        }

        return sum;
    }

    public int countLargestGroup(int n) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int maxVal = 0;
        int count = 0;

        for(int i=1;i<=n;i++){
            int digitSum = findDigitsSum(i);
            map.put(digitSum, map.getOrDefault(digitSum, 0) + 1);
            maxVal = Math.max(maxVal, map.get(digitSum));
        }

        for(int val : map.values()){
            if(val == maxVal){
                count++;
            }
        }

        return count;
    }
}
