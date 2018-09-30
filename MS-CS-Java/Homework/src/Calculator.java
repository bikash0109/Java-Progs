/*
* Program Name: Calculator.java
*
* Version :  1.1
*
* @author: Bikash Roy
* @author: Tanay Bhardwaj
*
*
* This program does operation of expression based on the precedence of operators including parentheses.
* It also validates an expression to be legal or not - basically it checks if all brackets are balanced or not.
* */

import java.util.LinkedList;
import java.util.Random;
import java.util.Stack;

public class Calculator {
    // Stack for numbers: 'numberStack'
    public static Stack<Double> numberStack = new Stack<>();

    // Stack for Operators: 'operatorStack'
    public static Stack<Character> operatorStack = new Stack<>();

    public static boolean checkForIllegalExpression(String expression) {
        boolean result = true;
        Stack<Character> stack = new Stack<>();
        char currentBracket, previousBracket;
        for (int i = 0; i < expression.length(); i++) {
            currentBracket = expression.charAt(i);
            if (currentBracket == '(' || currentBracket == '[' || currentBracket == '{') {
                stack.push(currentBracket);
            } else if (currentBracket == ')' || currentBracket == ']' || currentBracket == '}') {
                if (stack.isEmpty()) {
                    result = false;
                } else {
                    previousBracket = stack.peek();
                    if ((currentBracket == ')' && previousBracket == '(') || (currentBracket == ']' && previousBracket == '[') || (currentBracket == '}' && previousBracket == '{')) {
                        stack.pop();
                    } else {
                        result = false;
                    }
                }
            }
        }
        if (!stack.isEmpty()) {
            result = false;
        }
        return result;
    }

    public static void calculate(String expression) {
        if (checkForIllegalExpression(expression)) {
            for (int i = 0; i < expression.length(); i++) {
                if (expression.charAt(i) == ' ') // Discarding the empty spaces
                    continue;

                // Push Numbers
                if (expression.charAt(i) >= '0' && expression.charAt(i) <= '9') {
                    StringBuffer numberBuffer = new StringBuffer();
                    // There may be more than one digits in number .. 200,300 etc
                    while (i < expression.length() && expression.charAt(i) >= '0' && expression.charAt(i) <= '9')
                        numberBuffer.append(expression.charAt(i++));
                    numberStack.push(Double.parseDouble(numberBuffer.toString()));
                }

                // Push operators
                else if (expression.charAt(i) == '(' || expression.charAt(i) == '{' || expression.charAt(i) == '[')
                    operatorStack.push(expression.charAt(i));

                    // Closing ")", work only on first brackets
                else if (expression.charAt(i) == ')') {
                    evaluateBracketOperations('(');
                }

                // Closing "}", work only on second brackets
                else if (expression.charAt(i) == '}') {
                    evaluateBracketOperations('{');
                }

                // Closing ])", work only on third brackets
                else if (expression.charAt(i) == ']') {
                    evaluateBracketOperations('[');
                }

                // If operator exits in operatorStack, based on the precedence, perform the operation else push into the stack.
                else if (expression.charAt(i) == '^' || expression.charAt(i) == '*' || expression.charAt(i) == '/' || expression.charAt(i) == '+' || expression.charAt(i) == '-') {
                    while (!operatorStack.empty() && precedence(expression.charAt(i), operatorStack.peek())) {
                        numberStack.push(evaluate(operatorStack.pop(), numberStack.pop(), numberStack.pop()));
                    }
                    operatorStack.push(expression.charAt(i));
                }
            }

            while (!operatorStack.empty()) {
                numberStack.push(evaluate(operatorStack.pop(), numberStack.pop(), numberStack.pop()));
            }

            System.out.println(expression + "= " + numberStack.pop()); // Top of 'numberStack' is the final result, return it
        }
        else
            System.out.println(expression + " - this is an illegal expression.");
    }

    public  static void evaluateBracketOperations(char bracket)
    {
        while (operatorStack.peek() != bracket)
            numberStack.push(evaluate(operatorStack.pop(), numberStack.pop(), numberStack.pop()));
        operatorStack.pop();
    }

    // Returns true if 'op2' has higher or same precedence as 'op1',
    // otherwise returns false.
    public static boolean precedence(char operator1, char operator2) {
        if (operator2 == '(' || operator2 == ')')
            return false;
        if (operator2 == '{' || operator2 == '}')
            return false;
        if (operator2 == '[' || operator2 == ']')
            return false;
        if ((operator1 == '^') && (operator2 == '*' || operator2 == '/' || operator2 == '+' || operator2 == '-'))
            return false;
        if ((operator1 == '*' || operator1 == '/') && (operator2 == '+' || operator2 == '-'))
            return false;
        else
            return true;
    }

    public static Double evaluate(char currentOperator, Double right, Double left) {
        switch (currentOperator) {
            case '+':
                return left + right;
            case '-':
                return left - right;
            case '*':
                return left * right;
            case '/':
                return left / right; // Did not check for right == 0, since Java prints infinity as result.
            case '^':
                return Math.pow(left, right);
        }
        return 0.0;
    }

    public static void main(String[] args) {
//        calculate("1 * { 2 + 3 - [ 1 * ( 2 - 1 ) ] + 3 }");
//        calculate("2 + [ ( 3 - 6 ) / 5 ]");
//        calculate("2 + [ ( 3 - 6 ) / 0 ]"); // Dividing by 0 shows Infinity
//        calculate("1 + ( 2 + 3 ) * 3");
//        calculate("2 ^ 3 ^ 4");
//        calculate("( 2 ^ 3 ) ^ 4");
//        calculate("1 - ( 2 + [ + 2 - ) - 3 ] ");
        //calculate("1 - ");
//        LinkedList<Integer> intList = new LinkedList<>();
//        intList.addFirst(4);
//        intList.addFirst(5);
//        var ab = intList.add(8);
//        intList.addLast(1);
//        intList.add(2, 10);
//        System.out.println(intList);
//        intList.remove(2);
//        System.out.println(intList);
        System.out.println(new Random().nextInt(2));
    }
}