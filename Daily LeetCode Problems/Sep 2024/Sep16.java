/*539. Minimum Time Difference
Given a list of 24-hour clock time points in "HH:MM" format, return the minimum minutes difference between any two time-points in the list.
 

Example 1:
Input: timePoints = ["23:59","00:00"]
Output: 1
Example 2:
Input: timePoints = ["00:00","23:59","00:00"]
Output: 0
 
Constraints:

2 <= timePoints.length <= 2 * 104
timePoints[i] is in the format "HH:MM".*/


class Solution {
    public int findMinDifference(List<String> timePoints) {
        List<Integer> minutes = new ArrayList<>();
        for (String m : timePoints) {
            int hr = 10 * (m.charAt(0) - '0') + (m.charAt(1) - '0');
            int min = 10 * (m.charAt(3) - '0') + (m.charAt(4) - '0');
            minutes.add(hr * 60 + min);
        }
        Collections.sort(minutes);
        int minimum = Integer.MAX_VALUE;
        for (int i = 1; i < minutes.size(); i++) {
            minimum = Math.min(minimum, minutes.get(i) - minutes.get(i - 1));
        }
        int n = minutes.size();
        minimum = Math.min(minimum, 1440 - (minutes.get(n - 1) - minutes.get(0)));

        return minimum;
    }
}
