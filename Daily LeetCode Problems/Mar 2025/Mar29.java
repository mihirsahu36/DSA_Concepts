/*2818. Apply Operations to Maximize Score
You are given an array nums of n positive integers and an integer k.
Initially, you start with a score of 1. You have to maximize your score by applying the following operation at most k times:
Choose any non-empty subarray nums[l, ..., r] that you haven't chosen previously.
Choose an element x of nums[l, ..., r] with the highest prime score. If multiple such elements exist, choose the one with the smallest index.
Multiply your score by x.
Here, nums[l, ..., r] denotes the subarray of nums starting at index l and ending at the index r, both ends being inclusive.
The prime score of an integer x is equal to the number of distinct prime factors of x. For example, the prime score of 300 is 3 since 300 = 2 * 2 * 3 * 5 * 5.
Return the maximum possible score after applying at most k operations.
Since the answer may be large, return it modulo 109 + 7.

Example 1:
Input: nums = [8,3,9,3,8], k = 2
Output: 81
Explanation: To get a score of 81, we can apply the following operations:
- Choose subarray nums[2, ..., 2]. nums[2] is the only element in this subarray. Hence, we multiply the score by nums[2]. The score becomes 1 * 9 = 9.
- Choose subarray nums[2, ..., 3]. Both nums[2] and nums[3] have a prime score of 1, but nums[2] has the smaller index. Hence, we multiply the score by nums[2]. The score becomes 9 * 9 = 81.
It can be proven that 81 is the highest score one can obtain.

Example 2:
Input: nums = [19,12,14,6,10,18], k = 3
Output: 4788
Explanation: To get a score of 4788, we can apply the following operations: 
- Choose subarray nums[0, ..., 0]. nums[0] is the only element in this subarray. Hence, we multiply the score by nums[0]. The score becomes 1 * 19 = 19.
- Choose subarray nums[5, ..., 5]. nums[5] is the only element in this subarray. Hence, we multiply the score by nums[5]. The score becomes 19 * 18 = 342.
- Choose subarray nums[2, ..., 3]. Both nums[2] and nums[3] have a prime score of 2, but nums[2] has the smaller index. Hence, we multipy the score by nums[2]. The score becomes 342 * 14 = 4788.
It can be proven that 4788 is the highest score one can obtain.

Constraints:
1 <= nums.length == n <= 10^5
1 <= nums[i] <= 10^5
1 <= k <= min(n * (n + 1) / 2, 10^9)*/

class Solution {
    final int MOD = (int) 1e9 + 7;

    private long findPower(long a, long b) {
        if (b == 0) return 1;
        long half = findPower(a, b / 2);
        long result = (half * half) % MOD;
        if (b % 2 == 1) {
            result = (result * a) % MOD;
        }

        return result;
    }

    private List<Integer> getPrimes(int limit) {
        boolean []isPrime = new boolean[limit+1];
        Arrays.fill(isPrime, true);
        List<Integer> primes = new ArrayList<>();

        for (int i=2;i*i<=limit;i++) {
            if (isPrime[i]) {
                for (int j=i*i;j<=limit;j+=i) {
                    isPrime[j] = false;
                }
            }
        }

        for (int i=2;i<=limit;i++) {
            if (isPrime[i]) {
                primes.add(i);
            }
        }

        return primes;
    }

    private int []findPrimeScores(List<Integer> nums) {
        int n = nums.size();
        int []primeScores = new int[n];

        int maxElement = Collections.max(nums);
        List<Integer> primes = getPrimes(maxElement);

        for (int i=0;i<n;i++) {
            int num = nums.get(i);

            for (int prime : primes) {
                if (prime * prime > num){
                    break;
                }

                if (num % prime != 0){
                    continue;
                }

                primeScores[i]++;
                while (num % prime == 0) {
                    num /= prime;
                }
            }
            if (num > 1) {
                primeScores[i]++;
            }
        }

        return primeScores;
    }

    private int []findNextGreater(int []primeScores) {
        int n = primeScores.length;
        int []nextGreater = new int[n];
        Arrays.fill(nextGreater, n);
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i=n-1;i>=0;i--) {
            while (!stack.isEmpty() && primeScores[stack.peek()] <= primeScores[i]) {
                stack.pop();
            }
            nextGreater[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }

        return nextGreater;
    }

    private int []findPrevGreater(int []primeScores) {
        int n = primeScores.length;
        int []prevGreater = new int[n];
        Arrays.fill(prevGreater, -1);
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i=0;i<n;i++) {
            while (!stack.isEmpty() && primeScores[stack.peek()] < primeScores[i]) {
                stack.pop();
            }
            prevGreater[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        return prevGreater;
    }

    public int maximumScore(List<Integer> nums, int k) {
        int n = nums.size();
        int []primeScores = findPrimeScores(nums);
        int []nextGreater = findNextGreater(primeScores);
        int []prevGreater = findPrevGreater(primeScores);

        long []subarrays = new long[n];
        for (int i=0;i<n;i++) {
            subarrays[i] = (long) (nextGreater[i] - i) * (i - prevGreater[i]);
        }

        List<int[]> sortedNums = new ArrayList<>();
        for (int i=0;i<n;i++) {
            sortedNums.add(new int[]{nums.get(i), i});
        }

        sortedNums.sort((a, b) -> b[0] - a[0]);

        long score = 1;
        int idx = 0;

        while (k > 0) {
            int num = sortedNums.get(idx)[0];
            int i = sortedNums.get(idx)[1];
            long operations = Math.min((long) k, subarrays[i]);
            score = (score * findPower(num, operations)) % MOD;

            k -= operations;
            idx++;
        }

        return (int) score;
    }
}
