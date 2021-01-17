package exceptions;

/**
 * This exception is throw when you try to create a new user and that the nickname is already taken.
 * 
 * @author Tratskas Thomas, Delatte Mélanie, Logeot Gautier, Mayeur Pierre
 *
 */
public class UserNicknameException extends Exception{

	public UserNicknameException(){
		super("This nickname user already exist.");
	}
}
