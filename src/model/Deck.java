package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import exceptions.QuestionException;
import utility.Observable;
import utility.Serialisation;

/**
 * This class create the deck instance of the application that contains all the questions for the game.
 * 
 * @author Tratskas Thomas, Delatte Mélanie, Logeot Gautier, Mayeur Pierre
 *
 */
public class Deck extends Observable {
	private List<Question>questions;
	private static Deck instance = null; // attention singleton
	
	private Deck(){
		questions = new ArrayList<Question>();
	}
	/**
	 * Based on the Pattern Singleton
	 * It allows to create only one instance for the class Deck
	 * @return a Deck
	 */
	public static Deck getInstance(){
		if(instance==null){
			instance = Serialisation.fromJsonDeck("deck.json");
		}
		return instance;
	}
	/**
	 * 
	 * @return a list of Question
	 */
	public List<Question> getQuestions() {
		return questions;
	}
	
	/**
	 * check Question q and add it to the list if it's true
	 * @param q ,Question
	 * @throws QuestionException
	 */
	public void addQuestion(Question q) throws QuestionException{
		if(q.check()){
			questions.add(q);
			notifyO();
		}
	}
	

	/**
	 * allow to remove a question 
	 * @param q
	 */
	public void removeQuestion(Question q){
		if(questions.contains(q)){		
			questions.remove(q);
			notifyO();
		}
	}
	
	/**
	 * This method remove a question by the index passed as a parameter.
	 * @param indice
	 */
	public void removeQuestion(int indice){
		questions.remove(indice);
		notifyO();
	}
	
	/**
	 * to display questions with possible answer
	 */
	public String toString(){
		String result="Liste des questions:\n";
		for(Question q: questions){
			result+=q.toString()+"\n\n";
		}
		return result;
	}
	
	/**
	 * allow to remove all the questions of the list
	 */
	public void removeAll(){
		questions.removeAll(questions);
	}
	/**
	 * 
	 * @return the size of the list
	 */
	public int getNbQuestions(){
		return questions.size();
	}
	/**
	 * allow to found a question based on its index
	 * @param indice = the index of the question in the list
	 * @return the question founded
	 */
	public Question getQuestion(int indice){
		return questions.get(indice).clone();
	}
	/**
	 * 
	 * @return the name of the columns ( eg : "statement", "author" )
	 */
	public static Object[] getNomColonnes() {
		return Question.getNomColonnes();
	}
	
	
	/**
	 * This method returns a clone of the questions list 
	 * @param round
	 * @return List<Question>
	 */
	public List<Question> CloneListe(Round round){
		List<Question>tmp = new ArrayList<>();
		for(int i=0;i<Deck.getInstance().getQuestions().size();i++){
			if(round==Deck.getInstance().getQuestions().get(i).getRound()){
				tmp.add(questions.get(i));
			}		
		}
		Collections.shuffle(tmp);
		return tmp;
	}
}