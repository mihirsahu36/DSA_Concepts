/* 869. Reordered Power of 2
You are given an integer n. We reorder the digits in any order (including the original order) such that the leading digit is not zero.
Return true if and only if we can do this so that the resulting number is a power of two.

Example 1:
Input: n = 1
Output: true

Example 2:
Input: n = 10
Output: false

Constraints:
1 <= n <= 10^9 */

// Approach 1
class Solution {
    private String getSortedStr(int n){
        char []chars = String.valueOf(n).toCharArray();
        Arrays.sort(chars);
        return new String (chars);
    }
    
    public boolean reorderedPowerOf2(int n) {
        String s = getSortedStr(n);
        for(int p=0;p<=29;p++){
            if(s.equals(getSortedStr(1 << p))){
                return true;
            }
        }
        return false;
    }
}

// Approach 2
class Solution {
    private int getCount(int n){
        int num = 0;
        while(n > 0){
            num += (int)Math.pow(10, n % 10);
            n /= 10;
        }
        return num;
    }

    public boolean reorderedPowerOf2(int n) {
        int input = getCount(n);
        for(int p=0;p<=29;p++){
            if(input == getCount(1 << p)){
                return true;
            }
        }
        return false;
    }
}
