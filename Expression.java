import java.util.ArrayList;

/**
 * @author Steven Maggio
 * This class converts infix to postfix and has a method to evaluate the postfix expression
 * 
 */
public class Expression {
	
	/**
	 * The content of this expression
	 */
	private String infix;
	
	
	/**
	 * constructor that initiates the infix string
	 * @param i
	 */
	public Expression(String i){
		//initializing the string
		infix=i;
	}
	
	/**
	 * converts infix to postfix
	 * @return postfix expression
	 */
	public ArrayList<String> infixToPostfix(){
		//postfix arraylist
		 ArrayList<String> postfix=new ArrayList<>();
		 //stack used to process the transition from infix to postfix
		 GenericStack<String> stack=new GenericStack<>();
		 //string used to process the transition from infix to postfix without affecting the infix string
		 String expression=infix;
		 
		 //index integer
		 int i=0;
		 
		 //going through the string expression
		 while(i<expression.length()) {
			 //if the character at i is a number
			 if(Character.isDigit(expression.charAt(i))) {
				 //if the number is a double digit
				 if(expression.charAt(i)=='1'&&i+1<expression.length()&&Character.isDigit(expression.charAt(i+1))) {
					 //add the number to the postfix arraylist
					 postfix.add(expression.substring(i,i+2));
					 //increment the index
					 i++;
				 }
				 //if number is single digit
				 else {
					 //add the number to the arraylist
					 postfix.add(expression.substring(i,i+1));
				 }
			 }
			 
			 //if the character at i is an operator
			 else if(isOperator(expression.charAt(i))) {
				 //if stack size is zero
				 if(stack.getSize()==0) {
					 //add the character to the stack
					 stack.push(expression.charAt(i));
				 }
				 
				 //if operator has greater precedence
				 else if(prec((char)stack.peek())<prec(expression.charAt(i))) {
					 //add operator to stack
					 stack.push(expression.charAt(i));
				 }
				 //if the operator has lower precedence
				 else {
					 
					 while(stack.getSize()>0) {
						 //if the next element in stack is not '('
						 if((char)stack.peek()!='(') {
							 //add the element to a temporary string
							 String temp=""+stack.pop();
							 //add the string to the array list
							 postfix.add(temp);
						 }
						 //if element is '('
						 else {
							 //pop element from stack
							 stack.pop();
						 }
						 
					 }
					 //add the operator of lower precedence to the stack
					 stack.push(expression.charAt(i));
				 }
				 
			 }
			 //if element is '('
			 else if(expression.charAt(i)=='(') {
				 //add '(' to the stack
				 stack.push('(');
			 }
			 //if element is ')'
			 else if(expression.charAt(i)==')') {
				 //parameter boolean
				 boolean isParenthases=false;
				 
				 while(!isParenthases) {
					 //if stack has elements and the last element in stack is not '('
					 if(stack.getSize()>0&&(char)stack.peek()!='(') {
						 //turn popped stack value into string
						 String temp=""+stack.pop();
						 //add string to the array list
						 postfix.add(temp);
					 }
					 //if element is '('
					 else {
						 //if stack has an element
						 if(stack.getSize()>0) {
							 //pop the value
							 stack.pop();
						 }
						 //change parameter boolean so the while loop can end
						 isParenthases=true;
					 }
				 }
			 }
			 
			 //If there is still an element left on the last index
			 if(i==expression.length()-1&&stack.getSize()>0) {
				 //Store the popped value in a string
				 String temp=""+stack.pop();
				 //add string value to array list
				 postfix.add(temp);
			 }
			 //increment index
			 i++;
		 }
		 //return the postfix array list
		 return postfix;
	}
	
	
	/**
	 * evaluates the postfix returned by the infixToPostfix method
	 * @return value of evaluated postfix expression
	 */
	public int evaluate() {
		//postfix array list that is initialized by the method infixToPostfix
		ArrayList<String> postfix=infixToPostfix();
		
		//stack used to evaluate the postfix expression
		GenericStack<String> stack= new GenericStack<>();
		
		//iterating through the postfix expression
		for(int i=0;i<postfix.size();i++) {
			int num1=0; //used to add operands to stack
			int val1=0; //stores one number that will be evaluated
			int val2=0; //stores another number that will be evaluated
			
			//if operand
			if(!isOperator(postfix.get(i))) {
				//set num1 equal to the number value of the string value at i
				num1=Integer.parseInt(postfix.get(i));
				//add value to the stack
				stack.push(num1);
			}
			//if operator
			else {
				val1=(int) stack.pop(); //value at top of stack is stored in a variable
				val2=(int) stack.pop(); //next value in stack is stored in a variable
				
				//switch determining the operation to use
				switch(postfix.get(i))
                {
					//if operator is +
                    case "+":
                    //evaluate using appropriate operator
                    stack.push(val2+val1);
                    //exit switch
                    break;
                      
                    //if operator is -
                    case "-":
                    //evaluate using appropriate operator
                    stack.push(val2- val1);
                    //exit switch
                    break;
                      
                    //if operator is /
                    case "/":
                    //evaluate using appropriate operator
                    stack.push(val2/val1);
                    //exit switch
                    break;
                      
                    //if operator is *
                    case "*":
                    //evaluate using appropriate operator
                    stack.push(val2*val1);
                    //exit switch
                    break;
            }
			}
		}
		
		//return the last element in the stack, which is the value of the evaluated postfix expression
		return (int) stack.pop();
	}
	
	//Helper methods
	
	/**
	 * returns the precedence value based on stack operator precedence
	 * @param ch
	 * @return precedence value
	 */
	public int prec(char ch)
    {
		//switch uses the operator to determine the value that is returned
        switch (ch)
        {
        //if operator is '('
        case '(':
        	//returns 1
        	return 1;
        	
        //if operator is '+' or '-'
        case '+':
        case '-':
        	//returns 2
            return 2;
       
        //if operator is '*' or '/'
        case '*':
        case '/':
        	//returns 3
            return 3;
        }
        //if the operator wasn't any of the following, returns -1
        return -1;
    }
	
	/**
	 * returns true if the string is an operator, returns false otherwise
	 * @param s
	 * @return true if s is an operator, returns false otherwise
	 */
	public boolean isOperator(String s) {
		//array of possible operators
		String []arr={"+","-","*","/"};
		//traversing through array
		for(int i=0;i<arr.length;i++) {
			//if operator is the same as any elements in the array
			if(s.equals(arr[i])) {
				//returns true
				return true;
			}
		}
		//otherwise returns false
		return false;
	}
	
	/**
	 * returns true if the char is an operator, returns false otherwise
	 * @param s
	 * @return true if s is an operator, returns false otherwise
	 */
	public boolean isOperator(char s) {
		//array of possible operators
		char []arr={'+','-','*','/'};
		//traversing through array
		for(int i=0;i<arr.length;i++) {
			//if operator is the same as any elements in the array
			if(s==arr[i]) {
				//returns true
				return true;
			}
		}
		//otherwise returns false
		return false;
	}
	
}
