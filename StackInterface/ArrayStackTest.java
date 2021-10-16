package StackInterface;
import java.util.Scanner;
import java.lang.Math;

import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;

public class ArrayStackTest {
    public static void main(String[] arg){
        System.out.println("\nWelcome to Postfix Evaluation...");
        System.out.print("\nEnter the postfix you want to evaluate: ");
        Scanner sc = new Scanner(System.in);
        String postfix = sc.nextLine();
        System.out.printf("\nThe result is %d\n", evaluatePostfix(postfix));
    }

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

        return match;

    } // end assignVariable

    static boolean isOperator(char charToCheck){
        return ((charToCheck == '+') || (charToCheck == '-') || (charToCheck == '*') || (charToCheck == '/') || (charToCheck == '^'));
    } // end isOperator

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
