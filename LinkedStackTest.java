import javax.print.event.PrintJobListener;
import java.lang.*;

public class LinkedStackTest {
    public static void main(String[] args) {
        System.out.println("...ConvertToPostfix with Linked Stack...");
        System.out.println(convertToPostfix("a+b*r"));
    }
    static String convertToPostfix(String infix){
        LinkedStack<Character> operatorStack = new LinkedStack<Character>();
        LinkedStack<Character> operandStack = new LinkedStack<Character>();

        int l = infix.length();
        int c = 0;
        char nextChar;
        char topOperator, operand1, operand2;
        String postfix = "";

        while(!infix.isEmpty() && c < l){
            if(infix.charAt(c) != ' '){

                nextChar = infix.charAt(c);

                if (Character.isLetter(infix.charAt(c))) {
                    operandStack.push(nextChar);
                }
                else{
                    switch (nextChar) {
                        case '+':
                            operatorStack.push(nextChar);
                            if(!operatorStack.isEmpty() && getPrecedence(nextChar) <= getPrecedence(operatorStack.peek())){
                                topOperator = operatorStack.pop();
                                operand2 = operandStack.pop();
                                operand1 = operandStack.pop();
                                postfix = postfix+operand1+operand2+topOperator;
                            }
                            case '-':
                            operatorStack.push(nextChar);
                            if(!operatorStack.isEmpty() && getPrecedence(nextChar) <= getPrecedence(operatorStack.peek())){
                                topOperator = operatorStack.pop();
                                operand2 = operandStack.pop();
                                operand1 = operandStack.pop();
                                postfix = postfix+operand1+operand2+topOperator;
                            }
                            case '*':
                            operatorStack.push(nextChar);
                            if(!operatorStack.isEmpty() && getPrecedence(nextChar) <= getPrecedence(operatorStack.peek())){
                                topOperator = operatorStack.pop();
                                operand2 = operandStack.pop();
                                operand1 = operandStack.pop();
                                postfix = postfix+operand1+operand2+topOperator;
                            }
                        case '/':
                            operatorStack.push(nextChar);
                            if(!operatorStack.isEmpty() && getPrecedence(nextChar) <= getPrecedence(operatorStack.peek())){
                                topOperator = operatorStack.pop();
                                operand2 = operandStack.pop();
                                operand1 = operandStack.pop();
                                postfix = postfix+operand1+operand2+topOperator;
                            }
                        case '^':
                            operatorStack.push(nextChar);
                        default:
                            break; // ignore unexpected characters
                    }
                }
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
}
