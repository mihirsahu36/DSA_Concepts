/*440. K-th Smallest in Lexicographical Order
Given two integers n and k, return the kth lexicographically smallest integer in the range [1, n].

Example 1:
Input: n = 13, k = 2
Output: 10
Explanation: The lexicographical order is [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9], so the second smallest number is 10.

Example 2:
Input: n = 1, k = 1
Output: 1

Constraints:
1 <= k <= n <= 10^9 */

class Solution {
    private int countNumbers(long curr, long next, int n){
        int countNum = 0;

        while(curr <= n){
            countNum += Math.min(next, (long)(n + 1)) - curr;
            curr *= 10;
            next *= 10;
        }

        return countNum;
    }

    public int findKthNumber(int n, int k) {
        int curr = 1;
        k -= 1;

        while(k > 0){
            int count = countNumbers(curr, curr + 1, n);
            if(count <= k){
                curr++;
                k -= count;
            }else{
                curr *= 10;
                k -= 1;
            }
        }

        return curr;
    }
}
