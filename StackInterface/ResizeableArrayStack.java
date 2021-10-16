package StackInterface;
import java.util.Arrays;
/**
    A class of stacks whose entries are stored in an array.
    @author Huijun Hu
    @version 1.0
*/
public final class ResizeableArrayStack<T> implements StackInterface<T>
{
	private T[] stack;    // Array of stack entries, oldest entry <-> latest entry
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

    /** Adds a new entry to the top of this stack.
        @param newEntry  An object to be added to the stack. */
        public void push(T newEntry){
            topIndex++;
            checkintegrity();
            if (topIndex > stack.length)
            {
                doubleCapacity();
            }
            stack[topIndex] = newEntry;
        } // end push
  
        /** Removes and returns this stack's top entry.
            @return  The object at the top of the stack. 
            @throws  EmptyStackException if the stack is empty before the operation. */
        public T pop(){
            T result = stack[topIndex];
            stack[topIndex] = null;
            topIndex--;
            return result;
        } // end pop
      
        /** Retrieves this stack's top entry.
            @return  The object at the top of the stack.
            @throws  EmptyStackException if the stack is empty. */
        public T peek(){
            return stack[topIndex];
        } // end peek
      
        /** Detects whether this stack is empty.
            @return  True if the stack is empty. */
        public boolean isEmpty(){
            return topIndex == -1;
        } // end isEmpty
      
       /** Removes all entries from this stack. */
       public void clear(){
            // The cast is safe because the new array contains null entries
            @SuppressWarnings("unchecked")
            T[] emptyArray = (T[]) new Object[DEFAULT_CAPACITY];
            stack = emptyArray;
            topIndex = -1;
            integrityOK = true;
       } // end clear


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
            throw new IllegalStateException("Attempt to create a stack whose capacity exceeds " +
                                        "allowed maximum of " + MAX_CAPACITY);
    } // end checkCapacity
  
    // Throws an exception if receiving object is not initialized.
    private void checkintegrity()
    {
        if (!integrityOK)
            throw new SecurityException ("ArrayStack object is corrupt.");
    } // end checkintegrity
//  . . .
} // end ArrayStack

