import static org.junit.Assert.assertEquals;

import org.junit.Test;

//import javax.print.event.PrintJobListener;
//import java.lang.*;

public class LinkedStackTest {
    public static void main(String[] args) {
        System.out.println("...ConvertToPostfix with Linked Stack...");
        String output = convertToPostfix("a+b*r");
        System.out.println(output);
    }

    public static String convertToPostfix(String infix){
        LinkedStack<Character> operatorStack = new LinkedStack<>();
        LinkedStack<String> postfixHoldStack = new LinkedStack<>();

        int l = infix.length();
        int c = 0;
        Character currentChar;
        char Operator;
        String postfix = "";
        String operand1, operand2;

        while(!infix.isEmpty() && c < l){
            if(infix.charAt(c) != ' '){

                currentChar = infix.charAt(c);

                if (Character.isLetter(currentChar)) {
                    String temp = "";
                    temp += currentChar;
                    postfixHoldStack.push(temp);
                }
                else if(currentChar == '^'){
                    Operator = currentChar;
                }
                else if (currentChar == '+' || currentChar == '-' || currentChar == '*' || currentChar == '/' ) {
                    if (!operatorStack.isEmpty() && getPrecedence(currentChar) <= getPrecedence(operatorStack.peek())){
                        Operator = operatorStack.pop();
                        operatorStack.push(currentChar);
                        operand2 = postfixHoldStack.pop();
                        operand1 = postfixHoldStack.pop();
                        postfix = operand1 + operand2 + Operator;
                        postfixHoldStack.push(postfix);
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
            while(!operatorStack.isEmpty()){
                Operator = operatorStack.pop();
                operand2 = postfixHoldStack.pop();
                operand1 = postfixHoldStack.pop();
                postfix = operand1 + operand2 + Operator;
                postfixHoldStack.push(postfix);
            }
            c++;
        }

        return postfix;
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
                return -1;
        }
    }
    @Test
    public void convertTest(){
        String infix = "a+b*c";
        String acutal = convertToPostfix(infix);
        assertEquals("abc*+", acutal);
    }
}
