
/**
 * 
 * @author Steven Maggio
 * This interface maps out an adt stack
 * @param <E>
 */
public interface GenericStackInterface<E>{
	
	
	  /**
	   * This method returns the size of the list
	   * @return size of list
	   */
	  public int getSize();
	  
	  /**
	   * Determines whether the stack is empty.
	   * Precondition: None.
	   * Postcondition: Returns true if the stack is empty; otherwise returns false.
	   * @return A boolean value specifying if this stack is empty
	   */
	  public boolean isEmpty();
	   
	  /**
	   * Adds an item to the top of a stack.
	   * Precondition: newItem is the item to be added.
	   * Postcondition: If insertion is successful, item is on the top of the stack.
	   * @param item An item to be added
	   */
	  public void push(Object item);
	  
    /**
     * Removes the top of a stack.
     * Precondition: None.
     * Postcondition: If the stack is not empty, the item that was added most recently is removed from the stack and returned.
     * @return The item that was most recently added to this stack
     * @throws StackException If the stack is empty.
     */
	  public Object pop() throws StackException;
	  
	  
	  
	  /**
	   * Returns a reference to the top item of a stack.
	   * Precondition: None.
	   * Postcondition: If the stack is not empty, the item that was added most recently is returned. The stack is unchanged.
	   * @return A reference to the item that was added most recently or null if stack is empty
	   * @throws StackException If the stack is empty.
	   */
	  public Object peek() throws StackException;
	}
