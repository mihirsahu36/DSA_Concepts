/* 3625. Count Number of Trapezoids II
You are given a 2D integer array points where points[i] = [xi, yi] represents the coordinates of the ith point on the Cartesian plane.
Return the number of unique trapezoids that can be formed by choosing any four distinct points from points.
A trapezoid is a convex quadrilateral with at least one pair of parallel sides. Two lines are parallel if and only if they have the same slope.

Example 1:
Input: points = [[-3,2],[3,0],[2,3],[3,2],[2,-3]]
Output: 2
Explanation:
There are two distinct ways to pick four points that form a trapezoid:
The points [-3,2], [2,3], [3,2], [2,-3] form one trapezoid.
The points [2,3], [3,2], [3,0], [2,-3] form another trapezoid.

Example 2:
Input: points = [[0,0],[1,0],[0,1],[2,1]]
Output: 1
Explanation:
There is only one trapezoid which can be formed.

Constraints:
4 <= points.length <= 500
–1000 <= xi, yi <= 1000
All points are pairwise distinct. */

class Solution {
    public int countTrapezoids(int[][] points) {
        int n = points.length;
        double INF = 1e9 + 7;
        Map<Double, List<Double>> slopeIntercpts = new HashMap<>();
        Map<Integer, List<Double>> midPointMap = new HashMap<>();
        int res = 0;

        for(int i=0;i<n;i++){
            int x1 = points[i][0];
            int y1 = points[i][1];

            for(int j=i+1;j<n;j++){
                int x2 = points[j][0];
                int y2 = points[j][1];

                int dx = x1 - x2;
                int dy = y1 - y2;

                double slope;
                double intercept;

                if(x1 == x2){
                    slope = INF;
                    intercept = x1; 
                }else{
                    slope = 1.0 * (y2 - y1) / (x2 - x1);
                    intercept = 1.0 * (y1 * dx - x1 * dy) / dx;
                }

                if(slope == -0.0){
                    slope = 0.0;
                }
                if(intercept == -0.0){
                    intercept = 0.0;
                }

                int midPointKey = (x1 + x2) * 10000 + (y1 + y2);

                slopeIntercpts
                    .computeIfAbsent(slope, k -> new ArrayList<>())
                    .add(intercept);

                midPointMap
                    .computeIfAbsent(midPointKey, k -> new ArrayList<>())
                    .add(slope);
            }
        }

        for(List<Double> interceptList : slopeIntercpts.values()){
            if(interceptList.size() <= 1){
                continue;
            }

            TreeMap<Double, Integer> freq = new TreeMap<>();
            for(double b : interceptList){
                freq.put(b, freq.getOrDefault(b, 0) + 1);
            }

            int prefix = 0;
            for(int count : freq.values()){
                res += prefix * count;
                prefix += count;
            }
        }

        for(List<Double> slopeList : midPointMap.values()){
            if (slopeList.size() <= 1){
                continue;
            }

            TreeMap<Double, Integer> freq = new TreeMap<>();
            for(double s : slopeList){
                freq.put(s, freq.getOrDefault(s, 0) + 1);
            }

            int prefix = 0;
            for(int count : freq.values()){
                res -= prefix * count;
                prefix += count;
            }
        }

        return res;
    }
}
