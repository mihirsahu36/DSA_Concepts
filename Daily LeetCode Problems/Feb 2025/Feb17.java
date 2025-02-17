/*1079. Letter Tile Possibilities
You have n  tiles, where each tile has one letter tiles[i] printed on it.
Return the number of possible non-empty sequences of letters you can make using the letters printed on those tiles.

Example 1:
Input: tiles = "AAB"
Output: 8
Explanation: The possible sequences are "A", "B", "AA", "AB", "BA", "AAB", "ABA", "BAA".

Example 2:
Input: tiles = "AAABBC"
Output: 188

Example 3:
Input: tiles = "V"
Output: 1

Constraints:
1 <= tiles.length <= 7
tiles consists of uppercase English letters.*/

// Approach 1 (Using character array + Backtracking)
class Solution {
    private int total;

    private void findSeq(int []count){
        total++;
        for(int pos=0;pos<26;pos++){
            if(count[pos] == 0){
                continue;
            }

            count[pos]--;
            findSeq(count);
            count[pos]++;
        }
    }

    public int numTilePossibilities(String tiles) {
       total = 0;
       int []count = new int[26];
       for(char ch : tiles.toCharArray()){
        count[ch - 'A']++;
       }

       findSeq(count);
       return total - 1;
    }
}

// Approach 2 (Using Backtracking)
class Solution {
    int n;

    public int numTilePossibilities(String tiles) {
        n = tiles.length();
        boolean []used = new boolean[n];
        HashSet<String> res = new HashSet<>();
        StringBuilder curr = new StringBuilder();

        solve(tiles, used, res, curr);
        return res.size() - 1;
    }

    private void solve(String tiles, boolean []used, HashSet<String> res, StringBuilder curr){
        res.add(curr.toString());

        for(int i=0;i<n;i++){
            if(used[i]){
                continue;
            }

            curr.append(tiles.charAt(i));
            used[i] = true;

            solve(tiles, used, res, curr);

            used[i] = false;
            curr.deleteCharAt(curr.length() - 1);
        }
    }
}
