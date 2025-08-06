/* 3479. Fruits Into Baskets III
You are given two arrays of integers, fruits and baskets, each of length n,
where fruits[i] represents the quantity of the ith type of fruit, and baskets[j] represents the capacity of the jth basket.
From left to right, place the fruits according to these rules:
Each fruit type must be placed in the leftmost available basket with a capacity greater than or equal to the quantity of that fruit type.
Each basket can hold only one type of fruit.
If a fruit type cannot be placed in any basket, it remains unplaced.
Return the number of fruit types that remain unplaced after all possible allocations are made.

Example 1:
Input: fruits = [4,2,5], baskets = [3,5,4]
Output: 1
Explanation:
fruits[0] = 4 is placed in baskets[1] = 5.
fruits[1] = 2 is placed in baskets[0] = 3.
fruits[2] = 5 cannot be placed in baskets[2] = 4.
Since one fruit type remains unplaced, we return 1.

Example 2:
Input: fruits = [3,6,1], baskets = [6,4,7]
Output: 0
Explanation:
fruits[0] = 3 is placed in baskets[0] = 6.
fruits[1] = 6 cannot be placed in baskets[1] = 4 (insufficient capacity) but can be placed in the next available basket, baskets[2] = 7.
fruits[2] = 1 is placed in baskets[1] = 4.
Since all fruits are successfully placed, we return 0.

Constraints:
n == fruits.length == baskets.length
1 <= n <= 10^5
1 <= fruits[i], baskets[i] <= 109 */

class Solution {
    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        int n = fruits.length;
        int []segTree = new int[4*n];

        build(0, 0, n - 1, baskets, segTree);

        int unplaced = 0;
        for(int fruit : fruits){
            if(!querySegTree(0, 0, n - 1, segTree, fruit)){
                unplaced++;
            }
        }

        return unplaced;
    }

    private void build(int i, int l, int r, int []baskets, int []segTree){
        if(l == r){
            segTree[i] = baskets[l];
            return;
        }

        int m = (l + r) / 2;

        build(2 * i + 1, l, m, baskets, segTree);
        build(2 * i + 2, m + 1, r, baskets, segTree);

        segTree[i] = Math.max(segTree[2*i+1], segTree[2*i+2]);
    }

    private boolean querySegTree(int i, int l, int r, int []segTree, int val){
        if(segTree[i] < val){
            return false;
        }

        if(l == r){
            segTree[i] = -1;
            return true;
        }

        int mid = l + (r - l) / 2;
        boolean placed;

        if(segTree[2*i+1] >= val){
            placed = querySegTree(2 * i + 1, l, mid, segTree, val);
        }else{
            placed = querySegTree(2 * i + 2, mid + 1, r, segTree, val);
        }

        segTree[i] = Math.max(segTree[2*i+1], segTree[2*i+2]);

        return placed;
    }
}
