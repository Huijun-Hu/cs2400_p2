import java.util.Arrays;
/**
    A class of stacks whose entries are stored in an array.
    @author Huijun Hu, Jiyu Liu
    @version 1.0
*/
public final class ResizeableArrayStack<T> implements StackInterface<T>
{
	private T[] stack;    // Array of stack entries
	private int topIndex; // Index of top entry
    private boolean integrityOK = false;
	private static final int DEFAULT_CAPACITY = 50;
	private static final int MAX_CAPACITY = 10000;

    /** Creates an empty stack whose initial capacity is 50. */
    public ResizeableArrayStack()
    {
        this(DEFAULT_CAPACITY);
    } // end default constructor

    /** Creates an empty stack having a given initial capacity.
	    @param initialCapacity  The integer capacity desired. */
    public ResizeableArrayStack(int initialCapacity)
    {
        integrityOK = false;
        checkCapacity(initialCapacity);
      
        // The cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        T[] tempStack = (T[])new Object[initialCapacity];
        stack = tempStack;
	    topIndex = -1;
        integrityOK = true;
    } // end constructor
  
//  < Implementations of the stack operations go here. >
//  < Implementations of the private methods go here; checkCapacity and checkIntegrity
    
    // Doubles the size of the array stack.
    // Precondition: checkInitialization has been called.
    private void doubleCapacity()
    {
        int newLength = 2 * stack.length;
        checkCapacity(newLength);
        stack = Arrays.copyOf(stack, newLength);
    } // end doubleCapacity
  
    // Throws an exception if the client requests a capacity that is too large.
    private void checkCapacity(int capacity)
    {
        if (capacity > MAX_CAPACITY)
            throw new IllegalStateException("Attempt to create a bag whose capacity exceeds " +
                                        "allowed maximum of " + MAX_CAPACITY);
    } // end checkCapacity
  
    // Throws an exception if receiving object is not initialized.
    private void checkintegrity()
    {
        if (!integrityOK)
            throw new SecurityException ("ArrayBag object is corrupt.");
    } // end checkintegrity
//    are analogous to those in Chapter 2. >
//  . . .
} // end ArrayStack

