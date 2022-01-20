import java.util.ArrayList;

/**
 * @author Steven Maggio
 * This class makes a stack adt and implements the generic stack interface
 * @param <E>
 */
public class GenericStack<E> implements GenericStackInterface<E>{
	  /**
	   * The list of objects of this stack
	   */
	  private java.util.ArrayList<E> list;

	  /**
	   * constructor that initializes the list
	   */
	  public GenericStack() {
		  //initializing the list
		  list=new ArrayList<E>();
	  }
	  
	  
	
	  /**
	   * This method returns the size of the list
	   * @return size of list
	   */
	  @Override
	public int getSize() {
		// TODO Auto-generated method stub
		  //returning size of the list
		return list.size();
	}

	
	  /**
	   * Determines whether the stack is empty.
	   * Precondition: None.
	   * Postcondition: Returns true if the stack is empty; otherwise returns false.
	   * @return A boolean value specifying if this stack is empty
	   */
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		//returns true if list is empty, returns false otherwise
		return (list.size()==0);
	}

	
	/**
	   * Adds an item to the top of a stack.
	   * Precondition: newItem is the item to be added.
	   * Postcondition: If insertion is successful, item is on the top of the stack.
	   * @param item An item to be added
	   */
	@SuppressWarnings("unchecked")
	@Override
	public void push(Object item)  {
		// TODO Auto-generated method stub
		//adds item to the list
			list.add((E) item);
		
	}

	
	/**
     * Removes the top of a stack.
     * Precondition: None.
     * Postcondition: If the stack is not empty, the item that was added most recently is removed from the stack and returned.
     * @return The item that was most recently added to this stack
     * @throws StackException If the stack is empty.
     */
	@Override
	public Object pop() throws StackException {
		// TODO Auto-generated method stub
		//if the list is empty
		if(list.size()==0) {
			//throw error
			throw new IllegalStateException("Stack is empty.");
		}
		//if list is not empty, return the last element from the stack and remove it from the list
		return list.remove(list.size()-1);
		
	}

	
	/**
	   * Returns a reference to the top item of a stack.
	   * Precondition: None.
	   * Postcondition: If the stack is not empty, the item that was added most recently is returned. The stack is unchanged.
	   * @return A reference to the item that was added most recently or null if stack is empty
	   * @throws StackException If the stack is empty.
	   */
	@Override
	public Object peek() throws StackException {
		// TODO Auto-generated method stub
		//if the list is empty
		if(list.size()==0) {
			//throw error
			throw new IllegalStateException("Stack is empty.");
		}
		//if list is not empty, return the last element of the list, does not remove the element from the list
		return list.get(list.size()-1);
	}
	  
}
