package StackInterface;

import java.util.Scanner;
/**
   An implementation of Linked Data Stacks to Convert an Infix Expression to a Postfix Expression
   @author Huijun Hu
   @version 1.0
*/
public class LinkedStackTest {
    public static void main(String[] args) {
        System.out.println("\n...ConvertToPostfix with Linked Stack...\n");

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter an infix expression: ");
        String in = sc.nextLine();

        System.out.println("\nThe corresponding postfix expression is: \n" + convertToPostfix(in) + "\n");

    } // end main

    /** To convert infix to a postfix expression
     * @param infix expression to be convert
     * @return simulated postfix
     */
    public static String convertToPostfix(String infix){

        char Operator;
        char currentChar;
        String postfix = "";
        String operand1, operand2;

        LinkedStack<Character> operatorStack = new LinkedStack<>();
        LinkedStack<String> postfixHoldStack = new LinkedStack<>();

        for(int c = 0; c < infix.length(); c++ ){
            if(infix.charAt(c) != ' '){

                currentChar = infix.charAt(c);

                if (currentChar >= 'a' && currentChar <= 'z') {
                    String temp = "";
                    temp += currentChar;
                    postfixHoldStack.push(temp);
                    
                }
                else if(currentChar == '^'){
                    Operator = currentChar;
                }
                else if (currentChar == '+' || currentChar == '-' || currentChar == '*' || currentChar == '/' ) {
                    
                    if ((!operatorStack.isEmpty())){
                        if ( getPrecedence(currentChar) <= getPrecedence(operatorStack.peek()) && notParentheses(operatorStack.peek())){
                        Operator = operatorStack.pop();
                        operatorStack.push(currentChar);
                        operand2 = postfixHoldStack.pop();
                        operand1 = postfixHoldStack.pop();
                        postfix = operand1 + operand2 + Operator;
                        postfixHoldStack.push(postfix);
                        }else{
                            operatorStack.push(currentChar);
                        }
                    }else{
                        operatorStack.push(currentChar);
                    }
                }
                else if (currentChar == '('){
                    operatorStack.push(currentChar);
                }
                else if(currentChar == ')'){
                    Operator = operatorStack.pop();
                    while (Operator != '('){
                        operand2 = postfixHoldStack.pop();
                        operand1 = postfixHoldStack.pop();
                        postfix = operand1 + operand2 + Operator;
                        postfixHoldStack.push(postfix);
                        Operator = operatorStack.pop();
                    }
                }
                
            }
            
        }
        while(!(operatorStack.isEmpty())){
            Operator = operatorStack.pop();
            operand2 = postfixHoldStack.pop();
            operand1 = postfixHoldStack.pop();
            postfix = operand1 + operand2 + Operator;
            postfixHoldStack.push(postfix);
        }

        

        return postfixHoldStack.peek();
    } // end convertToPostfix

    /** To identify the precedence of an binary operator
     * @param operator binary operator
     * @return precedence
     */
    static int getPrecedence(char operator){
        switch (operator) {
            case '+':
                return 1;
            case '-':
                return 1;
            case '*':
                return 2;
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                return 4;
        }
    } // end getPrecedence

    /** To verify if a char not parentheses
     * @param charToCheck char to be checked
     * @return true if it is not parentheses, false otherwise
     */
    static boolean notParentheses(char charToCheck){
        return charToCheck!='(';
    } // end notParentheses

}
