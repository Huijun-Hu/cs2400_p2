package StackInterface;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

//import javax.print.event.PrintJobListener;
//import java.lang.*;

public class LinkedStackTest {
    public static void main(String[] args) {
        System.out.println("\n...ConvertToPostfix with Linked Stack...");
        System.out.println(convertToPostfix("a*b/(c-a)+d*e"));
    }

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
                        }else if ( getPrecedence(currentChar) > getPrecedence(operatorStack.peek()) && notParentheses(operatorStack.peek()) ){
                        // Operator = currentChar;
                        // operand2 = postfixHoldStack.pop();
                        // operand1 = postfixHoldStack.pop();
                        // postfix = operand1 + operand2 + Operator;
                        // postfixHoldStack.push(postfix);
                        operatorStack.push(currentChar);
                        }
                        else{
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
    }
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

    static boolean notParentheses(char charToCheck){
        return charToCheck!='(';
    } // end notParentheses

    /*
    @Test
    public void convertTest(){
        String infix = "a*b/(c-a)+d*e";
        String acutal = convertToPostfix(infix);
        assertEquals("ab*ca-/de*", acutal);
    }
    @Test
    public void testPush(){
       LinkedStack<Character> testStack = new LinkedStack<>();
       testStack.push('a');
       assertEquals('a', testStack.peek());
    }
    */

}
