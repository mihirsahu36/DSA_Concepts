/*1910. Remove All Occurrences of a Substring
Given two strings s and part, perform the following operation on s until all occurrences of the substring part are removed:
Find the leftmost occurrence of the substring part and remove it from s.
Return s after removing all occurrences of part.
A substring is a contiguous sequence of characters in a string.

Example 1:
Input: s = "daabcbaabcbc", part = "abc"
Output: "dab"
Explanation: The following operations are done:
- s = "daabcbaabcbc", remove "abc" starting at index 2, so s = "dabaabcbc".
- s = "dabaabcbc", remove "abc" starting at index 4, so s = "dababc".
- s = "dababc", remove "abc" starting at index 3, so s = "dab".
Now s has no occurrences of "abc".

Example 2:
Input: s = "axxxxyyyyb", part = "xy"
Output: "ab"
Explanation: The following operations are done:
- s = "axxxxyyyyb", remove "xy" starting at index 4 so s = "axxxyyyb".
- s = "axxxyyyb", remove "xy" starting at index 3 so s = "axxyyb".
- s = "axxyyb", remove "xy" starting at index 2 so s = "axyb".
- s = "axyb", remove "xy" starting at index 1 so s = "ab".
Now s has no occurrences of "xy".

Constraints:
1 <= s.length <= 1000
1 <= part.length <= 1000
s​​​​​​ and part consists of lowercase English letters.*/

// Approach 1(Using stack)
class Solution {
    private boolean check(Stack<Character> stack,String part,int n){
        Stack<Character> tempStack = new Stack<>();
        
        tempStack.addAll(stack);
        for(int i=n-1;i>=0;i--){
            if(tempStack.peek() != part.charAt(i)){
                return false;
            }
            tempStack.pop();
        }
        return true;
    }

    public String removeOccurrences(String s, String part) {
        int m = s.length();
        int n = part.length();
        Stack<Character> stack = new Stack<>();

        for(int i=0;i<m;i++){
            stack.push(s.charAt(i));
            if(stack.size() >= n && check(stack, part, n)){
                for(int j=0;j<n;j++){
                    stack.pop();
                }
            }
        }

        StringBuilder res = new StringBuilder();
        while(!stack.isEmpty()){
            res.append(stack.pop());
        }

        return res.reverse().toString();
    }
}

// Approach 2(Using string as stack)
class Solution {
    public String removeOccurrences(String s, String part) {
        StringBuilder res = new StringBuilder();
        int n = part.length();

        for(char ch : s.toCharArray()){
            res.append(ch);

            if(res.length() >= n && res.substring(res.length() - n).equals(part)){
                res.delete(res.length() - n, res.length());
            }
        }
        return res.toString();
    }
}
