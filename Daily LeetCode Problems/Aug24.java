/*564. Find the Closest Palindrome
Given a string n representing an integer, return the closest integer (not including itself), which is a palindrome. If there is a tie, return the smaller one.

The closest is defined as the absolute difference minimized between two integers.

 

Example 1:

Input: n = "123"
Output: "121"
Example 2:

Input: n = "1"
Output: "0"
Explanation: 0 and 2 are the closest palindromes but we return the smallest which is 0.*/

class Solution {
    public String nearestPalindromic(String n) {
        int length = n.length();
        long num = Long.parseLong(n);
        long original = num;

        long[] candidates = new long[5];
        candidates[0] = (long) Math.pow(10, length) + 1;
        candidates[1] = (long) Math.pow(10, length - 1) - 1;
        long prefix = Long.parseLong(n.substring(0, (length + 1) / 2));

        candidates[2] = createPalindrome(prefix, length % 2 == 0);
        candidates[3] = createPalindrome(prefix - 1, length % 2 == 0);
        candidates[4] = createPalindrome(prefix + 1, length % 2 == 0);

        long closest = -1;

        for (long candidate : candidates) {
            if (candidate != original) {
                if (closest == -1 ||
                    Math.abs(candidate - original) < Math.abs(closest - original) ||
                    (Math.abs(candidate - original) == Math.abs(closest - original) && candidate < closest)) {
                    closest = candidate;
                }
            }
        }

        return String.valueOf(closest);
    }

    private long createPalindrome(long prefix, boolean evenLength) {
        StringBuilder sb = new StringBuilder();
        sb.append(prefix);
        String reverse = new StringBuilder(sb).reverse().toString();
        if (evenLength) {
            sb.append(reverse);
        } else {
            sb.append(reverse.substring(1));
        }
        return Long.parseLong(sb.toString());
    }
}
