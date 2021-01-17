package exceptions;


/**
 * This exception is throw when you try to delete a question without selected one
 * 
 * @author Tratskas Thomas, Delatte Mélanie, Logeot Gautier, Mayeur Pierre
 *
 */
public class QuestionRemoveException extends ArrayIndexOutOfBoundsException{
	public QuestionRemoveException(){
		super("No questions selected");
	}
}
