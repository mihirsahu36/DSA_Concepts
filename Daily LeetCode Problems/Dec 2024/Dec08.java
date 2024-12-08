/*2054. Two Best Non-Overlapping Events
You are given a 0-indexed 2D integer array of events where events[i] = [startTimei, endTimei, valuei]. The ith event starts at startTimei and ends at endTimei, and if you attend this event, you will receive a value of valuei. You can choose at most two non-overlapping events to attend such that the sum of their values is maximized.
Return this maximum sum.
Note that the start time and end time is inclusive: that is, you cannot attend two events where one of them starts and the other ends at the same time. More specifically, if you attend an event with end time t, the next event must start at or after t + 1.

Example 1:
Input: events = [[1,3,2],[4,5,2],[2,4,3]]
Output: 4
Explanation: Choose the green events, 0 and 1 for a sum of 2 + 2 = 4.

Example 2:
Input: events = [[1,3,2],[4,5,2],[1,5,5]]
Output: 5
Explanation: Choose event 2 for a sum of 5.

Example 3:
Input: events = [[1,5,3],[1,5,1],[6,6,5]]
Output: 8
Explanation: Choose events 0 and 2 for a sum of 3 + 5 = 8.

Constraints:
2 <= events.length <= 10^5
events[i].length == 3
1 <= startTimei <= endTimei <= 10^9
1 <= valuei <= 10^6*/

class Solution {
    private int n;
    private int [][]dp = new int[100001][3]; // for memoization

    private int binarySearch(int [][]events, int endTime){ // binary search method is used where we have already sorted the array and based on selected event's endTime we find event where selected event endTime < the other event's startTime
        int left = 0, right = n - 1, res = n;

        while(left <= right){
            int mid = left + (right - left) / 2;
            if(events[mid][0] > endTime){
                res = mid;
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        return res;
    }

    private int solve(int [][]events, int i, int count){
        if(count == 2 || i >= n){
            return 0;
        }
        if(dp[i][count] != -1){
            return dp[i][count];
        }
        int nextValidEventIndex = binarySearch(events, events[i][1]); // to find next Valid event based on selected event endTime and other valid event startTime
        int take = events[i][2] + solve(events, nextValidEventIndex, count + 1); // if we find valid event then add the value of event with previous valid event value
        int notTake = solve(events, i + 1, count); // not valid move to next index and solve

        dp[i][count] = Math.max(take, notTake);
        return dp[i][count];
    }

    public int maxTwoEvents(int[][] events) {
        n = events.length;
        Arrays.sort(events, (a, b) -> Integer.compare(a[0], b[0])); // sorted the events to apply binary search
        for(int []row : dp){ // fill all rows with -1
            Arrays.fill(row, -1);
        }
        return solve(events, 0, 0); // recursive call
    }
}
