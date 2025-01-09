/*2185. Counting Words With a Given Prefix
You are given an array of strings words and a string pref.
Return the number of strings in words that contain pref as a prefix.
A prefix of a string s is any leading contiguous substring of s.

Example 1:
Input: words = ["pay","attention","practice","attend"], pref = "at"
Output: 2
Explanation: The 2 strings that contain "at" as a prefix are: "attention" and "attend".

Example 2:
Input: words = ["leetcode","win","loops","success"], pref = "code"
Output: 0
Explanation: There are no strings that contain "code" as a prefix.

Constraints:
1 <= words.length <= 100
1 <= words[i].length, pref.length <= 100
words[i] and pref consist of lowercase English letters.*/

// Brute Force Approach
class Solution {
    public int prefixCount(String[] words, String pref) {
        int count = 0;
        for(String word : words){
            if(word.startsWith(pref)){
                count++;
            }
        }
        return count;
    }
}

// using trie
class TrieNode {
    TrieNode []children;
    boolean isEndOfWord;
    int count;

    public TrieNode(){
        children = new TrieNode[26];
        isEndOfWord = false;
        count = 0;
    }
}

class Trie{
    private TrieNode root;
    
    public Trie(){
        root = new TrieNode();
    }

    public void insert(String word){
        TrieNode pCrawl = root;
        for(char ch : word.toCharArray()){
            int idx = ch - 'a';
            if(pCrawl.children[idx] == null){
                pCrawl.children[idx] = new TrieNode();
            }
            pCrawl = pCrawl.children[idx];
            pCrawl.count++;
        }
        pCrawl.isEndOfWord = true;
    }

    public int searchPrefixCount(String pref){
        TrieNode pCrawl = root;
        for(char ch : pref.toCharArray()){
            int idx = ch - 'a';
            if(pCrawl.children[idx] == null){
                return 0;
            }
            pCrawl = pCrawl.children[idx];
        }
        return pCrawl.count;
    }
}

class Solution{ 
    public int prefixCount(String[] words, String pref) {
        int n = words.length;
        int count = 0;
        Trie prefixTrie = new Trie();

        for(String word : words){
            prefixTrie.insert(word);
        }
        return prefixTrie.searchPrefixCount(pref);
    }
}
