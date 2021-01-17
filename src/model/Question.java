package model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import exceptions.QuestionException;
/**
 * this class allow to create a question for the game that we'll use in the deck
 * @author Pierre Mayeur, Tratskas Thomas, Logeot Gautier, Delatte Mélanie
 *
 */
public class Question {
	private String author; //auteur de la question
	private Round round;	//palier atteind par le joueur
	private String statement;	//question
	private Map<String,Boolean>choices;	// les 4 choix
	
	
	//constructeur

	public Question(String author, Round round, String statement) {			
		this.author = author;
		this.round = round;
		this.statement = statement;
		this.choices=new HashMap<String, Boolean>(4);
	}
	
	
	//fin constructeur
	
	/**
	 *	method to add a choices in the list, with his validity
	 * @param proposition : proposition of answer, String, hashKey
	 * @param validity : validity of the answer,Boolean
	 */
	public void addAnswer(String proposition,boolean validity){ 
		if(!choices.containsKey(proposition)){	//verify occurence on proposition in map<>choices
			choices.put(proposition, validity);
		}
	}
	
	/**
	 * method to delete an answer based on his key
	 * @param key : key for delete, Sring
	 */
	public void deleteChoices(String key){
		choices.remove(key);
	}

	/**
	 * to display questions with possible answer
	 */
	public String toString(){
		StringBuilder result = new StringBuilder();
		result.append("Author: ").append(this.author).append("\nStatement: ").append(this.statement).append("\nRound: ").append(this.round.toString()).append("\n");
		Set<Map.Entry<String, Boolean>>entrees = choices.entrySet();
		Iterator<Map.Entry<String, Boolean>> it = entrees.iterator();
		while(it.hasNext()){
			Map.Entry<String, Boolean>entree=it.next();
			result.append("Choice: ").append(entree.getKey()).append("\t").append(entree.getValue()).append("\n");
		}
		return result.toString();		
	}
	
	
	
	/**
	 * method for check if a question is in good format to be add to the setlist
	 * @return
	 * @throws QuestionException
	 */
	
public boolean check() throws QuestionException{
		int nb=0;
		int nbc=0;
		Set<Map.Entry<String, Boolean>>entrees = choices.entrySet();
		Iterator<Map.Entry<String, Boolean>> it = entrees.iterator();
		while(it.hasNext()){
			Map.Entry<String, Boolean>entree=it.next();
			nb++;
			if(entree.getValue()==true){
				nbc++;
			}
		}
		if(nb==4 && nbc==1){
			if(author!=null){
				if(statement!=null){	
					if(round!=null){
						return true;
					}
					else
					{
						throw new QuestionException();
					}
				}
				else
				{
					throw new QuestionException();
				}
			}
			else
			{
				throw new QuestionException();
			}
		}
		else
		{
			throw new QuestionException();
		}
	}
	
	
	/**
	 * method to compare 2 different questions and for check if they are the same
	 */
	public boolean equals(Object o){
		if(o instanceof Question){
			Question p = (Question)o;
			if(this.statement.equals(p.statement)){
				return true;
			}
		}
		return false;
	}
	/**
	 * Displays the informations of a question 
	 * Like its statement, author
	 * 
	 */
	public String afficherUneQuestion(){
		String result="";
		result+=this.statement+"\n";
		Set<Map.Entry<String, Boolean>>entrees = choices.entrySet();
		Iterator<Map.Entry<String, Boolean>> it = entrees.iterator();
		int i=0;
		while(it.hasNext()){
			i++;
			Map.Entry<String, Boolean>entree=it.next();
			result+=i+") "+entree.getKey()+"\n";
		}
		return result;		
	}
	/**
	 * Found the different choices of a question
	 * @return
	 */
	public Map<String, Boolean> getChoices() {
		return choices;
	}
	/**return the boolean associate with the key of the choice for
	 * the number of the question passed in arguments
	 * 
	 * @param ind
	 * @return
	 */
	public Boolean getVerif(int ind){
		String tmp="";
		Set<Map.Entry<String, Boolean>>entrees = choices.entrySet();
		Iterator<Map.Entry<String, Boolean>> it = entrees.iterator();
		int i=0;
		while(i!=ind){
			i++;
			Map.Entry<String, Boolean>entree=it.next();
			tmp=entree.getKey();
		}
		return choices.get(tmp);
	}
	
	/**
	 * Found only one choice for a question
	 * based on its index
	 * @param ind
	 * @return a string with the informations of the choice
	 */
	public String getOneChoices(int ind){
		StringBuilder result = new StringBuilder();
		Set<Map.Entry<String, Boolean>>entrees = choices.entrySet();
		Iterator<Map.Entry<String, Boolean>> it = entrees.iterator();
		int i=0;
		while(i!=ind){
			i++;
			Map.Entry<String, Boolean>entree=it.next();
			result.append(i).append(")  ").append(entree.getKey());
		}
		return result.toString();
	}
	
	/**
	 * return the round of the question
	 * @return a enumeration of Round type
	 */
	public Round getRound() {
		return round;
	}
	/**
	 * Displays the informations of a question
	 * @return
	 */
	public String afficherQuestionSprint01(){
		String result=this.statement;
		return result; 
	}
	/**
	 * Allows to clone a question with the same informations
	 */
	public Question clone(){
		Question result = new Question(author,round,statement);
		Set<Map.Entry<String, Boolean>>entrees = choices.entrySet();
		Iterator<Map.Entry<String, Boolean>> it = entrees.iterator();
		int i=0;
		while(i!=4){
			i++;
			Map.Entry<String, Boolean>entree=it.next();
			result.addAnswer(entree.getKey(), entree.getValue());
		}
		return result;
	}
	
	/**
	 * return the names of the different columns
	 * @return a board with the names of the columns
	 */
	public static Object[] getNomColonnes() {
		Object[] listeColonnes = {"Author","Round","Statement","choices"};
		return listeColonnes;
	}

	/**
	 * this method return the author of the question
	 * @return a String with the name of the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * return the statement of the question	
	 * @return a String with the statement of the question
	 */
	public String getStatement() {
		return statement;
	}
	
	
}
