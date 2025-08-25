/* 498. Diagonal Traverse
Given an m x n matrix mat, return an array of all the elements of the array in a diagonal order.

Example 1:
Input: mat = [[1,2,3],[4,5,6],[7,8,9]]
Output: [1,2,4,7,5,3,6,8,9]

Example 2:
Input: mat = [[1,2],[3,4]]
Output: [1,2,3,4]

Constraints:
m == mat.length
n == mat[i].length
1 <= m, n <= 10^4
1 <= m * n <= 10^4
-10^5 <= mat[i][j] <= 10^5 */

class Solution {
    public int[] findDiagonalOrder(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        Map<Integer, List<Integer>> map = new HashMap<>();

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                int key = i + j;
                map.putIfAbsent(key, new ArrayList<>());
                map.get(key).add(mat[i][j]);
            }
        }

        List<Integer> resList = new ArrayList<>();
        boolean flip = true;

        for(int k=0;k<=m+n-2;k++){
            List<Integer> diagonal = map.get(k);
            if(diagonal == null){
                continue;
            }

            if(flip){
                Collections.reverse(diagonal);
            }
            resList.addAll(diagonal);

            flip = !flip;
        }

        int []res = new int[resList.size()];
        for(int i=0;i<resList.size();i++){
            res[i] = resList.get(i);
        }

        return res;
    }
}
