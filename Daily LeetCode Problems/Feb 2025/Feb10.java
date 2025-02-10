/*3174. Clear Digits
You are given a string s.
Your task is to remove all digits by doing this operation repeatedly:
Delete the first digit and the closest non-digit character to its left.
Return the resulting string after removing all digits.

Example 1:
Input: s = "abc"
Output: "abc"
Explanation:
There is no digit in the string.

Example 2:
Input: s = "cb34"
Output: ""
Explanation:
First, we apply the operation on s[2], and s becomes "c4".
Then we apply the operation on s[1], and s becomes "".

Constraints:
1 <= s.length <= 100
s consists only of lowercase English letters and digits.
The input is generated such that it is possible to delete all digits.*/

// Approach 1 (Using stack)
class Solution {
    public String clearDigits(String s) {
        Stack<Character> stack = new Stack<>();

        for(char ch : s.toCharArray()){
            if(ch >= 'a' && ch <= 'z'){
                stack.push(ch);
            }else if(!stack.isEmpty()){
                stack.pop();
            }
        }

        StringBuilder res = new StringBuilder();
        while(!stack.isEmpty()){
            res.append(stack.pop());
        }

        return res.reverse().toString();
    }
}

// Approach 2 (Using result string)
class Solution {
    public String clearDigits(String s) {
        StringBuilder res = new StringBuilder();

        for(char ch : s.toCharArray()){
            if(ch >= 'a' && ch <= 'z'){
                res.append(ch);
            }else if(res.length() > 0){
                res.deleteCharAt(res.length() - 1); 
            }
        }

        return res.toString();
    }
}

// Approach 3 (Solving inplace)
class Solution {
    public String clearDigits(String s) {
        char []arr = s.toCharArray();
        int j = 0;

        for(int i=0;i<arr.length;i++){
            if(Character.isDigit(arr[i])){
                j = Math.max(j - 1, 0);
            }else{
                arr[j] = arr[i];
                j++;
            }
        }
        return new String(arr, 0, j);
    }
}
