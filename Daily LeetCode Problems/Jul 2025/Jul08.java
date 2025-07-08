/* 1751. Maximum Number of Events That Can Be Attended II
You are given an array of events where events[i] = [startDayi, endDayi, valuei].
The ith event starts at startDayi and ends at endDayi, and if you attend this event, you will receive a value of valuei.
You are also given an integer k which represents the maximum number of events you can attend.
You can only attend one event at a time. If you choose to attend an event, you must attend the entire event.
Note that the end day is inclusive: that is, you cannot attend two events where one of them starts and the other ends on the same day.
Return the maximum sum of values that you can receive by attending events.

Example 1:
Input: events = [[1,2,4],[3,4,3],[2,3,1]], k = 2
Output: 7
Explanation: Choose the green events, 0 and 1 (0-indexed) for a total value of 4 + 3 = 7.

Example 2:
Input: events = [[1,2,4],[3,4,3],[2,3,10]], k = 2
Output: 10
Explanation: Choose event 2 for a total value of 10.
Notice that you cannot attend any other event as they overlap, and that you do not have to attend k events.

Example 3:
Input: events = [[1,1,1],[2,2,2],[3,3,3],[4,4,4]], k = 3
Output: 9
Explanation: Although the events do not overlap, you can only attend 3 events. Pick the highest valued three.

Constraints:
1 <= k <= events.length
1 <= k * events.length <= 10^6
1 <= startDayi <= endDayi <= 10^9
1 <= valuei <= 10^6 */

class Solution {
    int [][]t;
    int []nextEvent;
    int n;

    private int attendEvent(int [][]events, int pos, int k){
        if(k == 0 || pos >= n){
            return 0;
        }

        if(t[pos][k] != -1){
            return t[pos][k];
        }

        int notAttend = attendEvent(events, pos + 1, k);

        int next = nextEvent[pos];
        int attendVal = events[pos][2] + attendEvent(events, next, k - 1);

        return t[pos][k] = Math.max(notAttend, attendVal);
    }

    public int maxValue(int[][] events, int k) {
        n = events.length;
        Arrays.sort(events, (a, b) -> Integer.compare(a[0], b[0]));
        t = new int[n][k+1];

        for(int []row : t){
            Arrays.fill(row, -1);
        }

        nextEvent = new int[n];
        for(int i=0;i<n;i++){
            int left = i + 1;
            int right = n;

            while(left < right){
                int mid = left + (right - left) / 2;
                
                if(events[mid][0] > events[i][1]){
                    right = mid;
                }else{
                    left = mid + 1;
                }
            }
            nextEvent[i] = left;
        }

        return attendEvent(events, 0, k);
    }
}
