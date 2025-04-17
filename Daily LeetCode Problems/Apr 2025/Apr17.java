/*2176. Count Equal and Divisible Pairs in an Array
Given a 0-indexed integer array nums of length n and an integer k, return the number of pairs (i, j) where 0 <= i < j < n,
such that nums[i] == nums[j] and (i * j) is divisible by k.

Example 1:
Input: nums = [3,1,2,2,2,1,3], k = 2
Output: 4
Explanation:
There are 4 pairs that meet all the requirements:
- nums[0] == nums[6], and 0 * 6 == 0, which is divisible by 2.
- nums[2] == nums[3], and 2 * 3 == 6, which is divisible by 2.
- nums[2] == nums[4], and 2 * 4 == 8, which is divisible by 2.
- nums[3] == nums[4], and 3 * 4 == 12, which is divisible by 2.

Example 2:
Input: nums = [1,2,3,4], k = 1
Output: 0
Explanation: Since no value in nums is repeated, there are no pairs (i,j) that meet all the requirements.

Constraints:
1 <= nums.length <= 100
1 <= nums[i], k <= 100*/

// Approach 1
class Solution {
    public int countPairs(int[] nums, int k) {
        int n = nums.length;
        int res = 0;

        HashMap<Integer, List<Integer>> indicesMap = new HashMap<>();
        for(int i=0;i<n;i++){
            indicesMap.computeIfAbsent(nums[i], x -> new ArrayList<>()).add(i);
        }

        HashSet<Integer> div = new HashSet<>();
        for(int f=1;f*f<=k;f++){
            if(k % f == 0){
                div.add(f);
                div.add(k / f);
            }
        }

        for(Map.Entry<Integer, List<Integer>> entry : indicesMap.entrySet()){
            List<Integer> indices = entry.getValue();
            HashMap<Integer, Integer> factorsMap = new HashMap<>();

            for(int i : indices){
                int gcd = gcd(i, k);
                int j = k / gcd;
                res += factorsMap.getOrDefault(j, 0);

                for(int f : div){
                    if(i % f == 0){
                        factorsMap.put(f, factorsMap.getOrDefault(f, 0) + 1);
                    }
                }
            }
        }

        return res;
    }

    private int gcd(int a, int b){
        while(b != 0){
            int t = b;
            b = a % b;
            a = t;
        }

        return a;
    }
}

// Approach 2
class Solution {
    public int countPairs(int[] nums, int k) {
        int n = nums.length;
        int res = 0;

        for(int i=0;i<n-1;i++){
            for(int j=i+1;j<n;j++){
                if(nums[i] == nums[j] && (i * j) % k == 0){
                    res++;
                }
            }
        }

        return res;
    }
}
