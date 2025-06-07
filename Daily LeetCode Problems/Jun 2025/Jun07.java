/*3170. Lexicographically Minimum String After Removing Stars
You are given a string s. It may contain any number of '*' characters. Your task is to remove all '*' characters.
While there is a '*', do the following operation:
Delete the leftmost '*' and the smallest non-'*' character to its left. If there are several smallest characters, you can delete any of them.
Return the lexicographically smallest resulting string after removing all '*' characters.

Example 1:
Input: s = "aaba*"
Output: "aab"
Explanation:
We should delete one of the 'a' characters with '*'. If we choose s[3], s becomes the lexicographically smallest.

Example 2:
Input: s = "abc"
Output: "abc"
Explanation:
There is no '*' in the string.

Constraints:
1 <= s.length <= 10^5
s consists only of lowercase English letters and '*'.
The input is generated such that it is possible to delete all '*' characters.*/

class Solution {
    public String clearStars(String s) {
        int n = s.length();
        char []str = s.toCharArray();

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if(a[0] == b[0]){
                return b[1] - a[1];
            }
            return a[0] - b[0];
        });

        boolean []removed = new boolean[n];
        
        for(int i=0;i<n;i++){
            char c = str[i];
            if(c != '*'){
                pq.add(new int[]{c, i});
                continue;
            }

            while(!pq.isEmpty()){
                int []top = pq.peek();
                if(top[1] < i && !removed[top[1]]){
                    removed[top[1]] = true;
                    pq.poll();
                    break;
                }else{
                    pq.poll();
                }
            }
            removed[i] = true;
        }

        char []res = new char[n];
        int idx = 0;
        for(int i=0;i<n;i++){
            if(!removed[i] && str[i] != '*'){
                res[idx++] = str[i];
            }
        }

        return new String(res, 0, idx);
    }
}
