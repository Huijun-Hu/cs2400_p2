package StackInterface;

import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class JunitLS {
    @Test
    public void testGetPrecedence(){
        char c;
        c = '+';
        assertEquals(1, LinkedStackTest.getPrecedence(c));
        
        c = '-';
        assertEquals(1, LinkedStackTest.getPrecedence(c));
        
        c = '*';
        assertEquals(2, LinkedStackTest.getPrecedence(c));
        
        c = '/';
        assertEquals(2, LinkedStackTest.getPrecedence(c));

        c = '^';
        assertEquals(3, LinkedStackTest.getPrecedence(c));
    } //end testGetPrecedence

    @Test
    public void testIsParentheses(){
        char c;
        c = '+';
        assertEquals(true, LinkedStackTest.notParentheses(c));
        
        c = '(';
        assertEquals(false, LinkedStackTest.notParentheses(c));
    } // end testIsParentheses

    @Test
    public void testConvertion(){
        String expect;
        expect = "ab-";
        assertEquals(expect, LinkedStackTest.convertToPostfix("a-b"));

        expect = "acb-*";
        assertEquals(expect, LinkedStackTest.convertToPostfix("a*(c-b)"));

        expect = "ab*ca-/de*+";
        assertEquals(expect, LinkedStackTest.convertToPostfix("a*b/(c-a)+d*e"));
    } // end testConvertion
}   
