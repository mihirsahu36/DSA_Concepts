/*2523. Closest Prime Numbers in Range
Given two positive integers left and right, find the two integers num1 and num2 such that:
left <= num1 < num2 <= right .
Both num1 and num2 are prime numbers.
num2 - num1 is the minimum amongst all other pairs satisfying the above conditions.
Return the positive integer array ans = [num1, num2]. If there are multiple pairs satisfying these conditions,
return the one with the smallest num1 value. If no such numbers exist, return [-1, -1].

Example 1:
Input: left = 10, right = 19
Output: [11,13]
Explanation: The prime numbers between 10 and 19 are 11, 13, 17, and 19.
The closest gap between any pair is 2, which can be achieved by [11,13] or [17,19].
Since 11 is smaller than 17, we return the first pair.

Example 2:
Input: left = 4, right = 6
Output: [-1,-1]
Explanation: There exists only one prime number in the given range, so the conditions cannot be satisfied.

Constraints:
1 <= left <= right <= 10^6*/

class Solution {
    private boolean isPrime(int num){
        if(num == 1){
            return false;
        }

        for(int i=2;i*i<=num;i++){
            if(num % i == 0){
                return false;
            }
        }

        return true;
    }

    public int[] closestPrimes(int left, int right) {
        List<Integer> primes = new ArrayList<>();

        for(int num=left;num<=right;num++){
            if(isPrime(num)){
                if(!primes.isEmpty() && num - primes.get(primes.size() - 1) <= 2){
                    return new int[]{primes.get(primes.size() - 1), num};
                }
                primes.add(num);
            }
        }

        int minDiff = Integer.MAX_VALUE;
        int []res = {-1, -1};
        for(int i=1;i<primes.size();i++){
            int diff = primes.get(i) - primes.get(i-1);
            if(diff < minDiff){
                minDiff = diff;
                res[0] = primes.get(i-1);
                res[1] = primes.get(i);
            }
        }

        return res;
    }
}
