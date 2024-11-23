/*1861. Rotating the Box
You are given an m x n matrix of characters box representing a side-view of a box. Each cell of the box is one of the following:
A stone '#'
A stationary obstacle '*'
Empty '.'
The box is rotated 90 degrees clockwise, causing some of the stones to fall due to gravity. Each stone falls down until it lands on an obstacle, another stone, or the bottom of the box. Gravity does not affect the obstacles' positions, and the inertia from the box's rotation does not affect the stones' horizontal positions.
It is guaranteed that each stone in box rests on an obstacle, another stone, or the bottom of the box.
Return an n x m matrix representing the box after the rotation described above.

Example 1:
Input: box = [["#",".","#"]]
Output: [["."],
         ["#"],
         ["#"]]
         
Example 2:
Input: box = [["#",".","*","."],
              ["#","#","*","."]]
Output: [["#","."],
         ["#","#"],
         ["*","*"],
         [".","."]]
         
Example 3:
Input: box = [["#","#","*",".","*","."],
              ["#","#","#","*",".","."],
              ["#","#","#",".","#","."]]
Output: [[".","#","#"],
         [".","#","#"],
         ["#","#","*"],
         ["#","*","."],
         ["#",".","*"],
         ["#",".","."]]
 

Constraints:
m == box.length
n == box[i].length
1 <= m, n <= 500
box[i][j] is either '#', '*', or '.'.*/

class Solution {
    public char[][] rotateTheBox(char[][] box) {
        int m = box.length;
        int n = box[0].length;
        char [][]res = new char[n][m];

        for(int i=0;i<n;i++){ // transposing the box
            for(int j=0;j<m;j++){
                res[i][j] = box[j][i];
            }
        }

        for(int i=0;i<n;i++){ // reverseing the row to achieve 90 degree rotation
            revRow(res[i]);
        }

        for(int j=0;j<m;j++){ // applying gravity
            int spaceCell = n - 1;
            for(int i=n-1;i>=0;i--){
                if(res[i][j] == '*'){ // if found '*' then make spaceCell move to i-1 position
                    spaceCell = i - 1;
                    continue;
                }
                if(res[i][j] == '#'){ // if found '#' then replace that position with '.' and spaceCell position with '#' and decrement the spaceCell
                    res[i][j] = '.';
                    res[spaceCell][j] = '#';
                    spaceCell--;
                }
            }
        }
        return res;
    }
    private void revRow(char []row){ // function for reversing the row
        int left = 0, right = row.length - 1;
        while(left<right){
            char temp = row[left];
            row[left] = row[right];
            row[right] = temp;
            left++;
            right--;
        }
    }
}
