/*3272. Find the Count of Good Integers
You are given two positive integers n and k.
An integer x is called k-palindromic if:
x is a palindrome.
x is divisible by k.
An integer is called good if its digits can be rearranged to form a k-palindromic integer.
For example, for k = 2, 2020 can be rearranged to form the k-palindromic integer 2002, whereas 1010 cannot be rearranged to form a k-palindromic integer.
Return the count of good integers containing n digits.
Note that any integer must not have leading zeros, neither before nor after rearrangement. For example, 1010 cannot be rearranged to form 101.

Example 1:
Input: n = 3, k = 5
Output: 27
Explanation:
Some of the good integers are:
551 because it can be rearranged to form 515.
525 because it is already k-palindromic.

Example 2:
Input: n = 1, k = 4
Output: 2
Explanation:
The two good integers are 4 and 8.

Example 3:
Input: n = 5, k = 6
Output: 2468

Constraints:
1 <= n <= 10
1 <= k <= 9*/

class Solution {
    public long countGoodIntegers(int n, int k) {
        HashSet<String> set = new HashSet<>();
        int d = (n + 1) / 2; // for calculating mid
        int start = (int)Math.pow(10, d - 1);
        int end = (int)Math.pow(10, d) - 1;

        for(int num = start; num<=end;num++){
            String leftHalf = Integer.toString(num);
            String full;

            if(n % 2 == 0){ // for even length just reverse the leftHalf of integer and append to leftHalf
                StringBuilder rightHalf = new StringBuilder(leftHalf).reverse();
                full = leftHalf + rightHalf.toString();
            }else{ // for odd reverse the leftHalf except the mid of integer and append it to leftHalf
                StringBuilder rightHalf = new StringBuilder(leftHalf.substring(0, d - 1)).reverse();
                full = leftHalf + rightHalf.toString();
            }

            long number = Long.parseLong(full); // convert to long from string
            if(number % k != 0){ // if number not divisible by k
                continue;
            }

            char []chars = full.toCharArray();
            Arrays.sort(chars); // sort the char
            set.add(new String(chars));
        }

        // calculate the factorial
        long []factorial = new long[11];
        factorial[0] = 1;
        for(int i=1;i<11;i++){
            factorial[i] = factorial[i-1] * i;
        }

        long res  = 0;
        for(String s : set){
            int []count = new int[10];
            for(char ch : s.toCharArray()){ // keeping count of each digit
                count[ch - '0']++;
            }

            // taking care of leading zeros
            int totalDigits = s.length(); // total length of the string/integer
            int zeroDigits = count[0]; // keeping track of zeros
            int nonZeroDigits = totalDigits - zeroDigits;

            long permutation = nonZeroDigits * factorial[totalDigits - 1]; // this all are the total integers in which there are no leadig zeros
            for(int i=0;i<10;i++){ // removing the integers resulting in same integer
                permutation /= factorial[count[i]];
            }
            res += permutation;
        }

        return res;
    }
}
