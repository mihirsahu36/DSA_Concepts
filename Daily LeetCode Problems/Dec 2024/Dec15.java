/*1792. Maximum Average Pass Ratio
There is a school that has classes of students and each class will be having a final exam. You are given a 2D integer array classes, where classes[i] = [passi, totali]. You know beforehand that in the ith class, there are totali total students, but only passi number of students will pass the exam.
You are also given an integer extraStudents. There are another extraStudents brilliant students that are guaranteed to pass the exam of any class they are assigned to. You want to assign each of the extraStudents students to a class in a way that maximizes the average pass ratio across all the classes.
The pass ratio of a class is equal to the number of students of the class that will pass the exam divided by the total number of students of the class. The average pass ratio is the sum of pass ratios of all the classes divided by the number of the classes.
Return the maximum possible average pass ratio after assigning the extraStudents students. Answers within 10-5 of the actual answer will be accepted.

Example 1:
Input: classes = [[1,2],[3,5],[2,2]], extraStudents = 2
Output: 0.78333
Explanation: You can assign the two extra students to the first class. The average pass ratio will be equal to (3/4 + 3/5 + 2/2) / 3 = 0.78333.

Example 2:
Input: classes = [[2,4],[3,9],[4,5],[2,10]], extraStudents = 4
Output: 0.53485

Constraints:
1 <= classes.length <= 10^5
classes[i].length == 2
1 <= passi <= totali <= 10^5
1 <= extraStudents <= 10^5*/

class Solution {
    public double maxAverageRatio(int[][] classes, int extraStudents) {
        int n = classes.length;
        PriorityQueue<double[]> pq = new PriorityQueue<>((a, b) -> Double.compare(b[0], a[0])); // max heap will store max delta and index
        for(int i=0;i<n;i++){ // initialize the pq with delta and its index
            double currPR = (double)classes[i][0] / classes[i][1]; // calculate current pass ratio
            double newPR = (double)(classes[i][0] + 1) / (classes[i][1] + 1); // calculate pass ratio after adding extraStudent
            double delta = newPR - currPR; // calculate the difference
            pq.offer(new double[]{delta, i}); // store the difference and the index in max heap
        }

        while(extraStudents-- > 0){ // keep check of extraStudents
            double []curr = pq.poll();
            int idx = (int)curr[1];

            classes[idx][0]++;
            classes[idx][1]++;

            // recalculating the delta for this class
            double currPR = (double)classes[idx][0] / classes[idx][1];
            double newPR = (double)(classes[idx][0] + 1) / (classes[idx][1] + 1);
            double delta = newPR - currPR;
            pq.offer(new double[]{delta, idx}); // store the update difference and its index back in pq
        }

        double res = 0.0;
        for(int i=0;i<n;i++){ // average pass ratio
            res += (double) classes[i][0] / classes[i][1];
        }
        return res / n;
    }
}
