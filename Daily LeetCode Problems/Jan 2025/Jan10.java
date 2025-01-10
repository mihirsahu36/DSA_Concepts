/*916. Word Subsets
You are given two string arrays words1 and words2.
A string b is a subset of string a if every letter in b occurs in a including multiplicity.
For example, "wrr" is a subset of "warrior" but is not a subset of "world".
A string a from words1 is universal if for every string b in words2, b is a subset of a.
Return an array of all the universal strings in words1. You may return the answer in any order.

Example 1:
Input: words1 = ["amazon","apple","facebook","google","leetcode"], words2 = ["e","o"]
Output: ["facebook","google","leetcode"]

Example 2:
Input: words1 = ["amazon","apple","facebook","google","leetcode"], words2 = ["l","e"]
Output: ["apple","google","leetcode"]

Constraints:
1 <= words1.length, words2.length <= 10^4
1 <= words1[i].length, words2[i].length <= 10
words1[i] and words2[i] consist only of lowercase English letters.
All the strings of words1 are unique.*/

class Solution {
    public List<String> wordSubsets(String[] words1, String[] words2) {
        ArrayList<String> res = new ArrayList<>();

        int []freq2 = new int[26]; // array to store the maximum frequency of each character in words2

        for(String word : words2){ // iterate over each element of words2
            int []temp = new int[26]; // array to store the character frequencies of the current word
            for(char ch : word.toCharArray()){ // count the frequency of each character in the current word
                temp[ch-'a']++;
                freq2[ch-'a'] = Math.max(freq2[ch-'a'], temp[ch-'a']);  // update freq2 to store the maximum frequency of each character across all words in words2
            }
        }
        for(String word : words1){ // iterate over each element of words1
            int []temp = new int[26]; // array to store the character frequencies of the current word
            for(char ch : word.toCharArray()){ // count the frequency of each character in the current word
                temp[ch-'a']++;
            }
            if(isSubset(freq2, temp)){ // check if the current word in words1 is a universal string
                res.add(word);
            }
        }
        return res;
    }

    private boolean isSubset(int []freq2, int []temp){
        for(int i=0;i<26;i++){
            if(temp[i] < freq2[i]){ // if any character in freq2 has a higher count than in temp
                return false;
            }
        }
        return true;
    }
}
