/*1106. Parsing A Boolean Expression
A boolean expression is an expression that evaluates to either true or false. It can be in one of the following shapes:
't' that evaluates to true.
'f' that evaluates to false.
'!(subExpr)' that evaluates to the logical NOT of the inner expression subExpr.
'&(subExpr1, subExpr2, ..., subExprn)' that evaluates to the logical AND of the inner expressions subExpr1, subExpr2, ..., subExprn where n >= 1.
'|(subExpr1, subExpr2, ..., subExprn)' that evaluates to the logical OR of the inner expressions subExpr1, subExpr2, ..., subExprn where n >= 1.
Given a string expression that represents a boolean expression, return the evaluation of that expression.
It is guaranteed that the given expression is valid and follows the given rules.

Example 1:
Input: expression = "&(|(f))"
Output: false
Explanation: 
First, evaluate |(f) --> f. The expression is now "&(f)".
Then, evaluate &(f) --> f. The expression is now "f".
Finally, return false.

Example 2:
Input: expression = "|(f,f,f,t)"
Output: true
Explanation: The evaluation of (false OR false OR false OR true) is true.

Example 3:
Input: expression = "!(&(f,t))"
Output: true
Explanation: 
First, evaluate &(f,t) --> (false AND true) --> false --> f. The expression is now "!(f)".
Then, evaluate !(f) --> NOT false --> true. We return true.

Constraints:
1 <= expression.length <= 2 * 104
expression[i] is one following characters: '(', ')', '&', '|', '!', 't', 'f', and ','.*/


class Solution {
    public boolean parseBoolExpr(String expression) {
        Stack<Character> stack = new Stack<>();
        int n = expression.length();
        for(int i=0;i<n;i++){
            char ch = expression.charAt(i);
            if(ch == ')'){
                ArrayList<Character> list = new ArrayList<>();
                while(stack.peek() != '('){
                    list.add(stack.pop());
                }
                stack.pop();
                char op = stack.pop();
                char ans = evaluate(list,op);
                stack.push(ans);
            }else{
                if(ch != ','){// ignore comma
                    stack.push(ch);
                }
            }
        }
        return stack.peek() == 't';
    }
    public char evaluate(ArrayList<Character> list, char op){
        if(op == '&'){// AND operator case
            if(find(list, 'f')){
                return 'f';
            }else{
                return 't';
            }
        }else if(op == '|'){ // OR operator case
            if(find(list, 't')){
                return 't';
            }else{
                return 'f';
            }
        }else{// NOT operator case
            return list.get(0) == 't' ? 'f' : 't';
        }
    }
    public boolean find(ArrayList<Character> list, char val){// to find whether the character is present or not
        for(char ch : list){
            if(ch == val){
                return true;
            }
        }
        return false;
    }
}
