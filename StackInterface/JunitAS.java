package StackInterface;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class JunitAS {
    
    @Test
    public void testCalculate(){
        int a = 9;
        int b = 3;
        char c = '+';
        assertEquals(12, ArrayStackTest.calculate(a,b,c));
        
        c = '-';
        assertEquals(6, ArrayStackTest.calculate(a,b,c));
        
        c = '*';
        assertEquals(27, ArrayStackTest.calculate(a,b,c));
        
        c = '/';
        assertEquals(3, ArrayStackTest.calculate(a,b,c));

        c = '^';
        assertEquals(273, ArrayStackTest.calculate(a,b,c));
        
    } // end testCalculate

    @ParameterizedTest
    @ValueSource(chars = { '+', '-', '*', '/', '^' })
    void testIsOprator(char c) {
        assertTrue(ArrayStackTest.isOperator(c));
    } // end testIsOprator
    
}   
