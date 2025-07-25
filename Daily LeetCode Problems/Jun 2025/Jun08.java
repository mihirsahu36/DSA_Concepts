/*386. Lexicographical Numbers
Given an integer n, return all the numbers in the range [1, n] sorted in lexicographical order.
You must write an algorithm that runs in O(n) time and uses O(1) extra space. 

Example 1:
Input: n = 13
Output: [1,10,11,12,13,2,3,4,5,6,7,8,9]

Example 2:
Input: n = 2
Output: [1,2]

Constraints:
1 <= n <= 5 * 10^4 */

// Approach 1
class Solution {
    public List<Integer> lexicalOrder(int n) {
        ArrayList<Integer> list = new ArrayList<>();
        int curr = 1;
        for(int i=1;i<=n;i++){
            list.add(curr);
            if(curr*10<=n){
                curr = curr * 10;
            }else{
                while(curr%10 == 9 || curr>=n){
                    curr /=10;
                }
                curr += 1;
            }
        }

        return list;
    }
}

// Approach 2
class Solution {
    private void solve(int curr, int n, List<Integer> res){
        if(curr > n){
            return;
        }
        res.add(curr);

        for(int nextDigit=0;nextDigit<=9;nextDigit++){
            int nextNum = (curr * 10) + nextDigit;

            if(nextNum > n){
                return;
            }
            solve(nextNum, n, res);
        }
    }
    
    public List<Integer> lexicalOrder(int n) {
        List<Integer> res = new ArrayList<>();

        for(int num=1;num<=9;num++){
            solve(num, n, res);
        }

        return res;
    }
}
