/* 2561. Rearranging Fruits
You have two fruit baskets containing n fruits each. You are given two 0-indexed integer arrays basket1 and basket2 representing the cost of fruit in each basket.
You want to make both baskets equal. To do so, you can use the following operation as many times as you want:
Chose two indices i and j, and swap the ith fruit of basket1 with the jth fruit of basket2.
The cost of the swap is min(basket1[i],basket2[j]).
Two baskets are considered equal if sorting them according to the fruit cost makes them exactly the same baskets.
Return the minimum cost to make both the baskets equal or -1 if impossible.

Example 1:
Input: basket1 = [4,2,2,2], basket2 = [1,4,1,2]
Output: 1
Explanation: Swap index 1 of basket1 with index 0 of basket2, which has cost 1. Now basket1 = [4,1,2,2] and basket2 = [2,4,1,2]. Rearranging both the arrays makes them equal.

Example 2:
Input: basket1 = [2,3,4,1], basket2 = [3,2,5,1]
Output: -1
Explanation: It can be shown that it is impossible to make both the baskets equal.

Constraints:
basket1.length == basket2.length
1 <= basket1.length <= 10^5
1 <= basket1[i],basket2[i] <= 10^9 */

class Solution {
    public long minCost(int[] basket1, int[] basket2) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int minEle = Integer.MAX_VALUE;

        for(int x : basket1){
            map.put(x, map.getOrDefault(x, 0) + 1);
            minEle = Math.min(minEle, x);
        }

        for(int x : basket2){
            map.put(x, map.getOrDefault(x, 0) - 1);
            minEle = Math.min(minEle, x);
        }

        ArrayList<Integer> finalList = new ArrayList<>();

        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            int cost = entry.getKey();
            int count = entry.getValue();

            if(count == 0){
                continue;
            }

            if(count % 2 != 0){
                return -1;
            }

            for(int c=1;c<=Math.abs(count)/2;c++){
                finalList.add(cost);
            }
        }

        Collections.sort(finalList);
        long res = 0;

        for(int i=0;i<finalList.size()/2;i++){
            res += Math.min(finalList.get(i), minEle * 2);
        }

        return res;
    }
}
