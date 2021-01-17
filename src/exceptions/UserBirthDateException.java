package exceptions;

/**
 * This exception is throw when you try to create a new user and that the birth date is invalid.
 * 
 * @author Tratskas Thomas, Delatte Mélanie, Logeot Gautier, Mayeur Pierre
 *
 */
public class UserBirthDateException extends Exception{

	public UserBirthDateException(){
		super("This birthdate is invalid.");
	}
}
