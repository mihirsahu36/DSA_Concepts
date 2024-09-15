/*
592. Fraction Addition and Subtraction

Given a string expression representing an expression of fraction addition and subtraction, return the calculation result in string format.

The final result should be an irreducible fraction. If your final result is an integer, change it to the format of a fraction that has a denominator 1. So in this case, 2 should be converted to 2/1.

 

Example 1:

Input: expression = "-1/2+1/2"
Output: "0/1"
Example 2:

Input: expression = "-1/2+1/2+1/3"
Output: "1/3"
Example 3:

Input: expression = "1/3-1/2"
Output: "-1/6"*/




class Solution {
    public String fractionAddition(String expression) {
        int numerator = 0, denominator = 1, i = 0, n = expression.length();
        
        while (i < n) {
            int sign = expression.charAt(i) == '-' ? -1 : 1;
            if (expression.charAt(i) == '-' || expression.charAt(i) == '+') i++;
            int num = 0, denom = 0;
            
            while (i < n && Character.isDigit(expression.charAt(i))) num = num * 10 + (expression.charAt(i++) - '0');
            i++;
            while (i < n && Character.isDigit(expression.charAt(i))) denom = denom * 10 + (expression.charAt(i++) - '0');
            
            numerator = numerator * denom + sign * num * denominator;
            denominator *= denom;
            int gcd = gcd(Math.abs(numerator), denominator);
            numerator /= gcd;
            denominator /= gcd;
        }
        
        return numerator + "/" + denominator;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
