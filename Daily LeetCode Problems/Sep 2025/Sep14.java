/* 966. Vowel Spellchecker
Given a wordlist, we want to implement a spellchecker that converts a query word into a correct word.
For a given query word, the spell checker handles two categories of spelling mistakes:
Capitalization: If the query matches a word in the wordlist (case-insensitive),
then the query word is returned with the same case as the case in the wordlist.
Example: wordlist = ["yellow"], query = "YellOw": correct = "yellow"
Example: wordlist = ["Yellow"], query = "yellow": correct = "Yellow"
Example: wordlist = ["yellow"], query = "yellow": correct = "yellow"
Vowel Errors: If after replacing the vowels ('a', 'e', 'i', 'o', 'u') of the query word with any vowel individually,
it matches a word in the wordlist (case-insensitive), then the query word is returned with the same case as the match in the wordlist.
Example: wordlist = ["YellOw"], query = "yollow": correct = "YellOw"
Example: wordlist = ["YellOw"], query = "yeellow": correct = "" (no match)
Example: wordlist = ["YellOw"], query = "yllw": correct = "" (no match)
In addition, the spell checker operates under the following precedence rules:
When the query exactly matches a word in the wordlist (case-sensitive), you should return the same word back.
When the query matches a word up to capitlization, you should return the first such match in the wordlist.
When the query matches a word up to vowel errors, you should return the first such match in the wordlist.
If the query has no matches in the wordlist, you should return the empty string.
Given some queries, return a list of words answer, where answer[i] is the correct word for query = queries[i].

Example 1:
Input: wordlist = ["KiTe","kite","hare","Hare"], queries = ["kite","Kite","KiTe","Hare","HARE","Hear","hear","keti","keet","keto"]
Output: ["kite","KiTe","KiTe","Hare","hare","","","KiTe","","KiTe"]

Example 2:
Input: wordlist = ["yellow"], queries = ["YellOw"]
Output: ["yellow"]

Constraints:
1 <= wordlist.length, queries.length <= 5000
1 <= wordlist[i].length, queries[i].length <= 7
wordlist[i] and queries[i] consist only of only English letters. */


class Solution {
    Set<String> exactWords = new HashSet<>();
    Map<String, String> caseMap = new HashMap<>();
    Map<String, String> vowelMap = new HashMap<>();

    private String toLower(String s){
        return s.toLowerCase();
    }

    private String maskVowels(String s){
        StringBuilder sb = new StringBuilder();
        for(char ch : s.toCharArray()){
            if(isVowel(ch)){
                sb.append("*");
            }else{
                sb.append(ch);
            }
        }

        return sb.toString();
    }

    private boolean isVowel(char ch){
        return (ch == 'a' || ch == 'e' || ch == 'i' || ch =='o' || ch == 'u');
    }

    private String check(String query){
        if(exactWords.contains(query)){
            return query;
        }

        String lowerQuery = toLower(query);
        if(caseMap.containsKey(lowerQuery)){
            return caseMap.get(lowerQuery);
        }

        String maskedQuery = maskVowels(lowerQuery);
        if(vowelMap.containsKey(maskedQuery)){
            return vowelMap.get(maskedQuery);
        }

        return "";
    }
    public String[] spellchecker(String[] wordlist, String[] queries) {
        exactWords.clear();
        caseMap.clear();
        vowelMap.clear();

        for(String word : wordlist){
            exactWords.add(word);

            String lowerWords = toLower(word);
            caseMap.putIfAbsent(lowerWords, word);

            String maskedWords = maskVowels(lowerWords);
            vowelMap.putIfAbsent(maskedWords, word);
        }

        String []res = new String[queries.length];
        for(int i=0;i<queries.length;i++){
            res[i] = check(queries[i]);
        }

        return res;
    }
}
