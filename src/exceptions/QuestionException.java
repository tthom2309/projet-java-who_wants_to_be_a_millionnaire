package exceptions;

/**
 * This exception is throw when you try to add an invalid question.
 * 
 * @author Tratskas Thomas, Delatte Mélanie, Logeot Gautier, Mayeur Pierre
 *
 */
public class QuestionException extends Exception{

	public QuestionException(){
		super("Your question is invalid.");
	}
}
