package exceptions;

/**
 * This excetion is throw when you try to create a new user and that the mail adress is invalid.
 * 
 * @author Tratskas Thomas, Delatte Mélanie, Logeot Gautier, Mayeur Pierre
 *
 */
public class UserMailException extends Exception{

	public UserMailException(){
		super("This mail adress is invalid.");
	}
}
