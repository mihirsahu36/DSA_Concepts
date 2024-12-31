/*983. Minimum Cost For Tickets 
You have planned some train traveling one year in advance. 
The days of the year in which you will travel are given as an integer array days. Each day is an integer from 1 to 365.
Train tickets are sold in three different ways:
a 1-day pass is sold for costs[0] dollars,
a 7-day pass is sold for costs[1] dollars, and
a 30-day pass is sold for costs[2] dollars.
The passes allow that many days of consecutive travel.
For example, if we get a 7-day pass on day 2, then we can travel for 7 days: 2, 3, 4, 5, 6, 7, and 8.
Return the minimum number of dollars you need to travel every day in the given list of days.

Example 1:
Input: days = [1,4,6,7,8,20], costs = [2,7,15]
Output: 11
Explanation: For example, here is one way to buy passes that lets you travel your travel plan:
On day 1, you bought a 1-day pass for costs[0] = $2, which covered day 1.
On day 3, you bought a 7-day pass for costs[1] = $7, which covered days 3, 4, ..., 9.
On day 20, you bought a 1-day pass for costs[0] = $2, which covered day 20.
In total, you spent $11 and covered all the days of your travel.

Example 2:
Input: days = [1,2,3,4,5,6,7,8,9,10,30,31], costs = [2,7,15]
Output: 17
Explanation: For example, here is one way to buy passes that lets you travel your travel plan:
On day 1, you bought a 30-day pass for costs[2] = $15 which covered days 1, 2, ..., 30.
On day 31, you bought a 1-day pass for costs[0] = $2 which covered day 31.
In total, you spent $17 and covered all the days of your travel.

Constraints:
1 <= days.length <= 365
1 <= days[i] <= 365
days is in strictly increasing order.
costs.length == 3
1 <= costs[i] <= 1000*/

class Solution {
    private int []dp;

    private int solve(List<Integer> days, int []costs, int n, int idx){
        if(idx >= n){ // if we checked all days, no cost is required
            return 0;
        }

        if(dp[idx] != -1){ // memoization
            return dp[idx];
        }

        int cost1 = costs[0] + solve(days, costs, n, idx + 1); // cost of 1 day pass with recursion

        int i = idx;
        while(i < n && days.get(i) < days.get(idx) + 7){
            i++;
        }
        int cost7 = costs[1] + solve(days, costs, n, i); // cost of 7 day pass with recursion

        int j = idx;
        while (j < n && days.get(j) < days.get(idx) + 30) {
            j++;
        }
        int cost30 = costs[2] + solve(days, costs, n, j); // cost of 30 day pass with recursion

        dp[idx] = Math.min(cost1, Math.min(cost7, cost30));
        return dp[idx];
    }
    public int mincostTickets(int[] days, int[] costs) {
        dp = new int [days.length];
        Arrays.fill(dp, -1);

        List<Integer> dayList = new ArrayList<>();
        for(int day : days){ // convert the days array to a list
            dayList.add(day);
        }
        return solve(dayList, costs, days.length, 0);
    }
}
