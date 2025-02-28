/*1092. Shortest Common Supersequence 
Given two strings str1 and str2, return the shortest string that has both str1 and str2 as subsequences.
If there are multiple valid strings, return any of them.
A string s is a subsequence of string t if deleting some number of characters from t (possibly 0) results in the string s.

Example 1:
Input: str1 = "abac", str2 = "cab"
Output: "cabac"
Explanation: 
str1 = "abac" is a subsequence of "cabac" because we can delete the first "c".
str2 = "cab" is a subsequence of "cabac" because we can delete the last "ac".
The answer provided is the shortest such string that satisfies these properties.

Example 2:
Input: str1 = "aaaaaaaa", str2 = "aaaaaaaa"
Output: "aaaaaaaa"

Constraints:
1 <= str1.length, str2.length <= 1000
str1 and str2 consist of lowercase English letters.*/

class Solution {
    public String shortestCommonSupersequence(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
        int [][]t = new int[m + 1][n + 1];

        for(int i=0;i<m+1;i++){
            for(int j=0;j<n+1;j++){
                if(i == 0 || j == 0){
                    t[i][j] = i + j;
                }else if(str1.charAt(i - 1) == str2.charAt(j - 1)){
                    t[i][j] = 1 + t[i - 1][j - 1];
                }else{
                    t[i][j] = 1 + Math.min(t[i - 1][j], t[i][j - 1]);
                }
            }
        }

            StringBuilder scsBuilder = new StringBuilder();
            int i = m, j = n;

            while(i > 0 && j > 0){
                if(str1.charAt(i - 1) == str2.charAt(j - 1)){
                    scsBuilder.append(str1.charAt(i - 1));
                    i--;
                    j--;
                }else{
                    if(t[i - 1][j] < t[i][j - 1]){
                        scsBuilder.append(str1.charAt(i - 1));
                        i--;
                    }else{
                        scsBuilder.append(str2.charAt(j - 1));
                        j--;
                    }
                }
            }

            while(i > 0){
                scsBuilder.append(str1.charAt(i - 1));
                i--;
            }

            while(j > 0){
                scsBuilder.append(str2.charAt(j - 1));
                j--;
            }
            
            return scsBuilder.reverse().toString();
    }
}
