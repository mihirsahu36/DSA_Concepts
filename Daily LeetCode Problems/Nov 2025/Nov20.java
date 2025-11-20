/* 757. Set Intersection Size At Least Two
You are given a 2D integer array intervals where intervals[i] = [starti, endi] represents all the integers
from starti to endi inclusively.
A containing set is an array nums where each interval from intervals has at least two integers in nums.
For example, if intervals = [[1,3], [3,7], [8,9]], then [1,2,4,7,8,9] and [2,3,4,8,9] are containing sets.
Return the minimum possible size of a containing set.

Example 1:
Input: intervals = [[1,3],[3,7],[8,9]]
Output: 5
Explanation: let nums = [2, 3, 4, 8, 9].
It can be shown that there cannot be any containing array of size 4.

Example 2:
Input: intervals = [[1,3],[1,4],[2,5],[3,5]]
Output: 3
Explanation: let nums = [2, 3, 4].
It can be shown that there cannot be any containing array of size 2.

Example 3:
Input: intervals = [[1,2],[2,3],[2,4],[4,5]]
Output: 5
Explanation: let nums = [1, 2, 3, 4, 5].
It can be shown that there cannot be any containing array of size 4.

Constraints:
1 <= intervals.length <= 3000
intervals[i].length == 2
0 <= starti < endi <= 108 */

class Solution {
    public int intersectionSizeTwo(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[1] == b[1] ? b[0] - a[0] : a[1] - b[1]);

        int a = -1, b = -1;
        int count = 0;

        for (int []in : intervals) {
            int start = in[0], end = in[1];

            boolean aIn = a >= start && a <= end;
            boolean bIn = b >= start && b <= end;

            if(aIn && bIn){
                continue;
            }else if (bIn){
                count++;
                a = b;
                b = end;
            }else{
                count += 2;
                a = end - 1;
                b = end;
            }
        }

        return count;
    }
}
