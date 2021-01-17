package model;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import utility.Observable;

/**
 * This class create the instance of a game.
 * 
 * @author Tratskas Thomas, Delatte Mélanie, Logeot Gautier, Mayeur Pierre
 *
 */
public class Game extends Observable{
	//singleton
	private static Game instance=null;
	private Round palier=Round.FIRST_ROUND;
	private List<Question>listQClone=Deck.getInstance().CloneListe(palier);
	private int numeroDeLaQuestion=0;
	private boolean loose=false;
	private int listGain[]={200,300,500,800,1500,3000,6000,12000,24000,48000,72000,100000,150000,300000,1000000};
	private int tour=1;
	//partie encours
	private boolean onGoing=false;
	//quand la valeur des jokers est = true, le joker est dispo
	private boolean joker5050=true;
	private boolean jokerSwitch=true;
	private boolean jokerCallFriend=true;
	//pour le 50:50
	private boolean choice1Visible=true;
	private boolean choice2Visible=true;
	private boolean choice3Visible=true;
	private boolean choice4Visible=true;
	//pour le call a friend
	private String friendAnswer="";
	//pour définir si c'est une partie chargée ou non
	private boolean load=false;
	
	/**
	 * This method change the values of the instance to launch a new game.
	 */
	public void begin(){
		palier=Round.FIRST_ROUND;
		numeroDeLaQuestion=0;
		loose=false;
		tour=1;
		listQClone=Deck.getInstance().CloneListe(palier);
		setOnGoing(true);
		joker5050=true;
		jokerSwitch=true;
		jokerCallFriend=true;
		load=false;
		notifyO();
	}
	
	/**
	 * This method change the values of the instance to load an existent save.
	 * @param s
	 */
	public void load(Save s){
		palier=s.getRound();
		numeroDeLaQuestion=0;
		loose=false;
		tour=s.getTour();
		listQClone=Deck.getInstance().CloneListe(palier);
		setOnGoing(true);
		joker5050=s.isJoker5050();
		jokerSwitch=s.isJokerSwitch();
		jokerCallFriend=s.isJokerCallFriend();
		load=true;
		notifyO();
	}
	
	/**
	 * This method check the round of the game and change it. 
	 */
	public void changerPalier(){
		if(tour==5){
			palier=Round.SECOND_ROUND;
			numeroDeLaQuestion=0;
			listQClone=Deck.getInstance().CloneListe(palier);
		}
		else if(tour==10){
			palier=Round.LAST_ROUND;
			numeroDeLaQuestion=0;
			listQClone=Deck.getInstance().CloneListe(palier);
		}
		
		
	}
	
	/**
	 * This method change the turn of the game instance.
	 */
	public void changerTour(){
		numeroDeLaQuestion++;
		changerPalier();
		tour++;
		choice1Visible=true;
		choice2Visible=true;
		choice3Visible=true;
		choice4Visible=true;
		notifyO();
	}
	
	/**
	 * This method returns the number of the question.
	 * @return int
	 */
	public int getNumeroDeLaQuestion() {
		return numeroDeLaQuestion;
	}
	
	
	
	private Game(){
	}
	
	/**
	 * This method returns the game instance based on the Singleton Design Pattern.
	 * @return Game
	 */
	public static Game getInstance(){
		if (instance==null) {
			instance=new Game();
		}
		return instance;
	}
	//end singleton
	
	/**
	 * This method returns a clone of the list of questions based on the round of the game. 
	 * @return List<Question>
	 */
	public List<Question> getListQClone() {
		return listQClone;
	}

	/**
	 * This method returns the round of the game.
	 * @return int
	 */
	public int getTour() {
		return tour;
	}
	
	/**
	 * This method returns a gain value based on the index passed as a parameter.
	 * @param gain
	 * @return int
	 */
	public int getValueGain(int gain){
		int result=0;
		for(int i=0;i<=listGain.length;i++){
			if(i==gain){
				result=listGain[i];
			}
		}
		return result;
	}
	
	/**
	 * This method set the game instance as loose.
	 */
	public void setLoose(){
		loose=true;
	}

	/**
	 * This method return the round of the game instance.
	 * @return Round
	 */
	public Round getPalier() {
		return palier;
	}

	/**
	 * This method returns if the current game is loose or not.
	 * @return boolean
	 */
	public boolean isLoose() {
		return loose;
	}

	/**
	 * This method returns if the game instance is on going.
	 * @return boolean
	 */
	public boolean isOnGoing() {
		return onGoing;
	}

	/**
	 * This method change the "ongoing" statute of the game instance.
	 * @param boolean onGoing
	 */
	public void setOnGoing(boolean onGoing) {
		this.onGoing = onGoing;
		notifyO();
	}

	/**
	 * This method returns if the "50:50 Joker" is still available on the current game.
	 * @return boolean
	 */
	public boolean isJoker5050() {
		return joker5050;
	}

	/**
	 * This method returns if the "Switch Joker" is still available on the current game.
	 * @return boolean
	 */
	public boolean isJokerSwitch() {
		return jokerSwitch;
	}

	/**
	 * This method returns if the "Call a friend Joker" is still available on the current game.
	 * @return
	 */
	public boolean isJokerCallFriend() {
		return jokerCallFriend;
	}

	/**
	 * This method executes the "50:50 Joker"
	 */
	public void useJoker5050(){
		Random ran = new Random();
		int i = ran.nextInt(3)+1;
		if(this.listQClone.get(numeroDeLaQuestion).getVerif(1)){
			if(i==1){
				choice2Visible=false;
				choice3Visible=false;
			}
			if(i==2){
				choice2Visible=false;
				choice4Visible=false;
			}
			if(i==3){
				choice3Visible=false;
				choice4Visible=false;
			}
		}
		if(this.listQClone.get(numeroDeLaQuestion).getVerif(2)){
			if(i==1){
				choice1Visible=false;
				choice3Visible=false;
			}
			if(i==2){
				choice1Visible=false;
				choice4Visible=false;
			}
			if(i==3){
				choice3Visible=false;
				choice4Visible=false;
			}
		}
		if(this.listQClone.get(numeroDeLaQuestion).getVerif(3)){
			if(i==1){
				choice1Visible=false;
				choice2Visible=false;
			}
			if(i==2){
				choice1Visible=false;
				choice4Visible=false;
			}
			if(i==3){
				choice2Visible=false;
				choice4Visible=false;
			}
		}
		if(this.listQClone.get(numeroDeLaQuestion).getVerif(4)){
			if(i==1){
				choice1Visible=false;
				choice2Visible=false;
			}
			if(i==2){
				choice1Visible=false;
				choice3Visible=false;
			}
			if(i==3){
				choice2Visible=false;
				choice3Visible=false;
			}
		}
		joker5050=false;
		notifyO();
	}
	
	/**
	 * This method executes the "Switch Joker".
	 */
	public void useJokerSwitch(){
		jokerSwitch=false;
		choice1Visible=true;
		choice2Visible=true;
		choice3Visible=true;
		choice4Visible=true;
		Collections.shuffle(listQClone);
		notifyO();
	}
	
	/**
	 * This method executes the "Call a friend".
	 */
	public void useJokerCallFriend(){
		Random ran = new Random();
		int i=0;
		if(palier.equals(Round.FIRST_ROUND)){
			i=1;
		}
		if(palier.equals(Round.SECOND_ROUND)){
			i=2;
		}
		if(palier.equals(Round.LAST_ROUND)){
			i=4;
		}
		int n = ran.nextInt(i)+1;
		if(i==1){
			if(this.listQClone.get(numeroDeLaQuestion).getVerif(1)){
				friendAnswer=listQClone.get(numeroDeLaQuestion).getOneChoices(1);
			}
			if(this.listQClone.get(numeroDeLaQuestion).getVerif(2)){
				friendAnswer=listQClone.get(numeroDeLaQuestion).getOneChoices(2);
			}
			if(this.listQClone.get(numeroDeLaQuestion).getVerif(3)){
				friendAnswer=listQClone.get(numeroDeLaQuestion).getOneChoices(3);
			}
			if(this.listQClone.get(numeroDeLaQuestion).getVerif(4)){
				friendAnswer=listQClone.get(numeroDeLaQuestion).getOneChoices(4);
			}
		}
		else
		{
			int m = ran.nextInt(3)+1;
			if(this.listQClone.get(numeroDeLaQuestion).getVerif(1)){
				if(m==1){
					friendAnswer=listQClone.get(numeroDeLaQuestion).getOneChoices(2);
				}
				if(m==2){
					friendAnswer=listQClone.get(numeroDeLaQuestion).getOneChoices(3);
				}
				if(m==3){
					friendAnswer=listQClone.get(numeroDeLaQuestion).getOneChoices(4);
				}
			}
			if(this.listQClone.get(numeroDeLaQuestion).getVerif(2)){
				if(m==1){
					friendAnswer=listQClone.get(numeroDeLaQuestion).getOneChoices(1);
				}
				if(m==2){
					friendAnswer=listQClone.get(numeroDeLaQuestion).getOneChoices(3);
				}
				if(m==3){
					friendAnswer=listQClone.get(numeroDeLaQuestion).getOneChoices(4);
				}
			}
			if(this.listQClone.get(numeroDeLaQuestion).getVerif(3)){
				if(m==1){
					friendAnswer=listQClone.get(numeroDeLaQuestion).getOneChoices(1);
				}
				if(m==2){
					friendAnswer=listQClone.get(numeroDeLaQuestion).getOneChoices(2);
				}
				if(m==3){
					friendAnswer=listQClone.get(numeroDeLaQuestion).getOneChoices(4);
				}
			}
			if(this.listQClone.get(numeroDeLaQuestion).getVerif(4)){
				if(m==1){
					friendAnswer=listQClone.get(numeroDeLaQuestion).getOneChoices(1);
				}
				if(m==2){
					friendAnswer=listQClone.get(numeroDeLaQuestion).getOneChoices(2);
				}
				if(m==3){
					friendAnswer=listQClone.get(numeroDeLaQuestion).getOneChoices(3);
				}
			}
		}
		jokerCallFriend=false;
		notifyO();
	}

	/**
	 * This method returns if the choice 1 of the current question is visible when you use the "50:50 Joker". 
	 * @return boolean
	 */
	public boolean isChoice1Visible() {
		return choice1Visible;
	}

	/**
	 * This method returns if the choice 2 of the current question is visible when you use the "50:50 Joker". 
	 * @return boolean
	 */
	public boolean isChoice2Visible() {
		return choice2Visible;
	}

	/**
	 * This method returns if the choice 3 of the current question is visible when you use the "50:50 Joker". 
	 * @return boolean
	 */
	public boolean isChoice3Visible() {
		return choice3Visible;
	}

	/**
	 * This method returns if the choice 4 of the current question is visible when you use the "50:50 Joker". 
	 * @return boolean
	 */
	public boolean isChoice4Visible() {
		return choice4Visible;
	}
	
	/**
	 * This method returns the value of the "Call a friend Joker".
	 * @return String
	 */
	public String getFriendAnswer() {
		return friendAnswer;
	}

	/**
	 * This methods returns if the current game is load or not.
	 * @return boolean
	 */
	public boolean isLoad() {
		return load;
	}
	
	
	
	
}
