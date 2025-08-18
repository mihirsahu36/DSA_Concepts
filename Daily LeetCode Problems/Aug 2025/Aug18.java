/* 679. 24 Game
You are given an integer array cards of length 4. You have four cards, each containing a number in the range [1, 9].
You should arrange the numbers on these cards in a mathematical expression using the operators ['+', '-', '*', '/'] and the parentheses '(' and ')' to get the value 24.
You are restricted with the following rules:
The division operator '/' represents real division, not integer division.
For example, 4 / (1 - 2 / 3) = 4 / (1 / 3) = 12.
Every operation done is between two numbers. In particular, we cannot use '-' as a unary operator.
For example, if cards = [1, 1, 1, 1], the expression "-1 - 1 - 1 - 1" is not allowed.
You cannot concatenate numbers together
For example, if cards = [1, 2, 1, 2], the expression "12 + 12" is not valid.
Return true if you can get such expression that evaluates to 24, and false otherwise.

Example 1:
Input: cards = [4,1,8,7]
Output: true
Explanation: (8-4) * (7-1) = 24

Example 2:
Input: cards = [1,2,1,2]
Output: false

Constraints:
cards.length == 4
1 <= cards[i] <= 9 */

class Solution {
    private static final double EPSILON = 0.1;
    public boolean judgePoint24(int[] cards) {
        List<Double> nums = new ArrayList<>();
        for(int card : cards){
            nums.add((double)card);
        }

        return solve(nums);
    }

    private boolean solve(List<Double> cards){
        if(cards.size() == 1){
            return Math.abs(cards.get(0) - 24) <= EPSILON;
        }

        for(int i=0;i<cards.size();i++){
            for(int j=0;j<cards.size();j++){
                if(i == j){
                    continue;
                }

                List<Double> temp = new ArrayList<>();
                for(int k=0;k<cards.size();k++){
                    if(k != i && k != j){
                        temp.add(cards.get(k));
                    }
                }

                double a = cards.get(i);
                double b = cards.get(j);
                List<Double> possibleVals = new ArrayList<>();
                possibleVals.add(a + b);
                possibleVals.add(a - b);
                possibleVals.add(b - a);
                possibleVals.add(a * b);

                if(Math.abs(b) > 0.0){
                    possibleVals.add(a / b);
                }

                if(Math.abs(a) > 0.0){
                    possibleVals.add(b / a);
                }

                for(double val : possibleVals){
                    temp.add(val);
                    if(solve(temp)){
                        return true;
                    }
                    temp.remove(temp.size() - 1); 
                }                
            }
        }

        return false;
    }
}
