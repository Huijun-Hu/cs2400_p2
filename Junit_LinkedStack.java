import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Junit_LinkedStack {
    @Test
    public void convertTest(){
        String infix = "a+b*c";
        String acutal = convertToPostfix(infix);
        assertEquals("abc*+", acutal);
    }

}
