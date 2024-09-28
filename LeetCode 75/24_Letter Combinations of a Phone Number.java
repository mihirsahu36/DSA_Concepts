/*Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.

A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.


 

Example 1:

Input: digits = "23"
Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
Example 2:

Input: digits = ""
Output: []
Example 3:

Input: digits = "2"
Output: ["a","b","c"]*/


class Solution {
    public List<String> letterCombinations(String digits) {
        if (digits.isEmpty())
            return new ArrayList<>();
        
        List<String> ans = new ArrayList<>();
        ans.add("");
        
        final String[] digitToLetters = {
            "",    "",    "abc",  "def", "ghi",
            "jkl", "mno", "pqrs", "tuv", "wxyz"
        };

        for (final char d : digits.toCharArray()) {
            List<String> temp = new ArrayList<>();
            for (final String s : ans) {
                for (final char c : digitToLetters[d - '0'].toCharArray()) {
                    temp.add(s + c);
                }
            }
            ans = temp;
        }

        return ans;
    }
}
