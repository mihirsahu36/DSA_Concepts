/*838. Push Dominoes
There are n dominoes in a line, and we place each domino vertically upright.
In the beginning, we simultaneously push some of the dominoes either to the left or to the right.
After each second, each domino that is falling to the left pushes the adjacent domino on the left.
Similarly, the dominoes falling to the right push their adjacent dominoes standing on the right.
When a vertical domino has dominoes falling on it from both sides, it stays still due to the balance of the forces.
For the purposes of this question, we will consider that a falling domino expends no additional force to a falling or already fallen domino.
You are given a string dominoes representing the initial state where:
dominoes[i] = 'L', if the ith domino has been pushed to the left,
dominoes[i] = 'R', if the ith domino has been pushed to the right, and
dominoes[i] = '.', if the ith domino has not been pushed.
Return a string representing the final state.

Example 1:
Input: dominoes = "RR.L"
Output: "RR.L"
Explanation: The first domino expends no additional force on the second domino.

Example 2:
Input: dominoes = ".L.R...LR..L.."
Output: "LL.RR.LLRRLL.."

Constraints:
n == dominoes.length
1 <= n <= 10^5
dominoes[i] is either 'L', 'R', or '.'.*/

class Solution {
    public String pushDominoes(String dominoes) {
        int n = dominoes.length();
        int []leftClosestR = new int[n];
        int []rightClosestL = new int[n];

        for(int i=n-1;i>=0;i--){
            if(dominoes.charAt(i) == 'L'){
                rightClosestL[i] = i;
            }else if(dominoes.charAt(i) == '.'){
                rightClosestL[i] = i < n - 1 ? rightClosestL[i+1] : -1;
            }else{
                rightClosestL[i] = -1;
            }
        }
        
        for(int i=0;i<n;i++){
            if(dominoes.charAt(i) == 'R'){
                leftClosestR[i] = i;
            }else if(dominoes.charAt(i) == '.'){
                leftClosestR[i] = i > 0 ? leftClosestR[i-1] : -1;
            }else{
                leftClosestR[i] = -1;
            }
        }

        StringBuilder res = new StringBuilder();

        for(int i=0;i<n;i++){
            int distRightL = rightClosestL[i] == -1 ? Integer.MAX_VALUE : Math.abs(i - rightClosestL[i]);
            int distLeftR = leftClosestR[i] == -1 ? Integer.MAX_VALUE : Math.abs(i - leftClosestR[i]);

            if(rightClosestL[i] == leftClosestR[i]){
                res.append('.');
            }else if(rightClosestL[i] == -1){
                res.append('R');
            }else if(leftClosestR[i] == -1){
                res.append('L');
            }else if(distLeftR == distRightL){
                res.append('.');
            }else{
                res.append(distRightL < distLeftR ? 'L' : 'R');
            }
        }

        return res.toString(); 
    }
}
