/*440. K-th Smallest in Lexicographical Order
Given two integers n and k, return the kth lexicographically smallest integer in the range [1, n].

Example 1:
Input: n = 13, k = 2
Output: 10
Explanation: The lexicographical order is [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9], so the second smallest number is 10.
Example 2:
Input: n = 1, k = 1
Output: 1*/


class Solution {
    public int findKthNumber(int n, int k) {
        int curr = 1;
        k--;
        while(k>0){
            int count = countPrefix(curr,n);
            if(k>=count){
                curr++;
                k -= count;
            }else{
                curr *= 10;
                k--;
            }
        }
        return curr;
    }
    private int countPrefix(int prefix, int n){
        long first = prefix, next = prefix + 1;
        int totalCount = 0;
        while(first <= n){
            totalCount += Math.min(n+1, next) - first;
            first *= 10;
            next *= 10;
        }
        return totalCount;
    }
}
