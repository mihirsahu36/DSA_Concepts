/*3306. Count of Substrings Containing Every Vowel and K Consonants II
You are given a string word and a non-negative integer k.
Return the total number of substrings of word that contain every vowel ('a', 'e', 'i', 'o', and 'u') at least once and exactly k consonants.

Example 1:
Input: word = "aeioqq", k = 1
Output: 0
Explanation:
There is no substring with every vowel.

Example 2:
Input: word = "aeiou", k = 0
Output: 1
Explanation:
The only substring with every vowel and zero consonants is word[0..4], which is "aeiou".

Example 3:
Input: word = "ieaouqqieaouqq", k = 1
Output: 3
Explanation:
The substrings with every vowel and one consonant are:
word[0..5], which is "ieaouq".
word[6..11], which is "qieaou".
word[7..12], which is "ieaouq".

Constraints:
5 <= word.length <= 2 * 10^5
word consists only of lowercase English letters.
0 <= k <= word.length - 5*/

class Solution {
    private boolean isVowel(char ch){
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }

    public long countOfSubstrings(String word, int k) {
        int n = word.length();
        HashMap<Character, Integer> map = new HashMap<>();
        int []nextCons = new int[n];
        int lastCons = n;

        for(int i=n-1;i>=0;i--){
            nextCons[i] = lastCons;
            if(!isVowel(word.charAt(i))){
                lastCons = i;
            }
        }

        int i = 0, j = 0;
        long count = 0;
        int cons = 0;

        while(j < n){
            char ch = word.charAt(j);
            if(isVowel(ch)){
                map.put(ch, map.getOrDefault(ch, 0) + 1);
            }else{
                cons++;
            }

            while(cons > k){
                char c = word.charAt(i);
                if(isVowel(c)){
                    map.put(c, map.get(c) - 1);
                    if(map.get(c) == 0){
                        map.remove(c);
                    }
                }else{
                    cons--;
                }
                i++;
                }

                while(i < n && map.size() == 5 && cons == k){
                    int idx = nextCons[j];
                    count += idx - j;

                    char c = word.charAt(i);
                    if(isVowel(c)){
                        map.put(c, map.get(c) - 1);
                        if(map.get(c) == 0){
                            map.remove(c);
                        }
                    }else{
                        cons--;
                    }
                    i++;
                }
                j++;
        }

        return count;
    }
}
