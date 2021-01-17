package exceptions;


/**
 * This excetion is throw when you try to create a new user and that at least one field is empty.
 * 
 * @author Tratskas Thomas, Delatte Mélanie, Logeot Gautier, Mayeur Pierre
 *
 */
public class UserEmptyFieldException extends Exception{

	public UserEmptyFieldException(){
		super("At least one field is empty.");
	}
}
