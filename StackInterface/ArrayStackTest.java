package StackInterface;
import java.util.Scanner;
import java.lang.Math;

/**
   An implementation of Resizeable Array Stacks to Evaluate a Postfix Expression
   @author Huijun Hu
   @version 1.0
*/

public class ArrayStackTest {
    public static void main(String[] arg){
        System.out.println("\nWelcome to Postfix Evaluation...");
        System.out.print("\nEnter the postfix you want to evaluate: ");
        Scanner sc = new Scanner(System.in);
        String postfix = sc.nextLine();
        System.out.printf("\nThe result is %d\n", evaluatePostfix(postfix));
    } // end main

    /** To evaluate value of a postfix expression
     * @param postfix expression to be evaluated
     * @return value of the postfix
     */
    static int evaluatePostfix(String postfix){

        ResizeableArrayStack<Integer> valueStack = new ResizeableArrayStack<Integer>();
        Character currentChar;
        int value1, value2;
        int length = postfix.length();

        String valueAssign[][] = assignVariable();
        int numOfVar = valueAssign.length;

        for(int i=0; i < length; i++){

            currentChar = postfix.charAt(i);

            if(isOperator(currentChar)){
                int result;
                value2 = valueStack.pop();
                value1 = valueStack.pop();
                switch(currentChar){
                    case '+':
                        result = value1+value2;
                        valueStack.push(result);
                        break;
                    case '-':
                        result = value1-value2;
                        valueStack.push(result);
                        break;
                    case '*':
                        result = value1*value2;
                        valueStack.push(result);
                        break;
                    case '/':
                        result = value1/value2;
                        valueStack.push(result);
                        break;
                    case '^':
                        result = (int) Math.pow(value1,value2);
                        valueStack.push(result);
                        break;
                }
            }else{
                for(int c = 0; c < numOfVar; c++){
                    if ( currentChar.toString().equals(valueAssign[c][0]))
                        valueStack.push(Integer.parseInt(valueAssign[c][1]));
                }
            }
        }

        return valueStack.pop();

    } // end evaluatePostfix

    /** Assign variables corresponding to their values
     * @return A 2-D array with matching variables and values
     */
    static String[][] assignVariable(){

        System.out.print("Enter the number of variable(s): ");
        Scanner sc = new Scanner(System.in);
        int count = sc.nextInt();
        String[][] match = new String[count][2];

        for (int i=0; i<count; i++){
            System.out.printf("What is variable %d : ", i+1);
            char var = sc.next().charAt(0);
            match[i][0] = Character.toString(var);
            System.out.print("What is its value: ");
            int val = (int) sc.nextInt();
            match[i][1] = Integer.toString(val);
        }

        sc.close();
        return match;

    } // end assignVariable

    /** To verify if a char is an operator
     * @param charToCheck binary operator
     * @return whether if it is an operator
     */
    static boolean isOperator(char charToCheck){
        return ((charToCheck == '+') || (charToCheck == '-') || (charToCheck == '*') || (charToCheck == '/') || (charToCheck == '^'));
    } // end isOperator

    /** Calculate an expression with two operands and one operator
     * @param a operand one as an integer
     * @param b operand two as an integer
     * @param c binary operator
     * @return the result of the expression
     */
    static int calculate(int a, int b, char c){
        switch(c){
            case '+':
                return a+b;
            case '-':
                return a-b;
            case '*':
                return a*b;
            case '/':
                 return a/b;
        }
        return (int) Math.pow(a, b);
    } // end calculate
}
