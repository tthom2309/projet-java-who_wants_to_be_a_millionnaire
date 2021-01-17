package model;
/**
 * this class gives informations of the current part of the player.
 * 
 * @author Pierre Mayeur, Delattte Mélanie, Tratskas Thomas, Logeot Gautier
 *
 */
public class Save {

	private String user;
	private Round round;
	private int tour;
	private boolean joker5050;
	private boolean jokerSwitch;
	private boolean jokerCallFriend;
	
	
	public Save(String user,Round round,int tour,boolean j5050,boolean jswitch,boolean jcall){
		this.user=user;
		this.round=round;
		this.tour=tour;
		this.joker5050=j5050;
		this.jokerSwitch=jswitch;
		this.jokerCallFriend=jcall;
	}

	/**
	 * return a User type that contains the information of the player
	 * @return
	 */
	public String getUser() {
		return user;
	}

	/**
	 * return the round of the current question
	 * @return
	 */
	public Round getRound() {
		return round;
	}

	/**
	 * return the number of the question
	 * @return
	 */
	public int getTour() {
		return tour;
	}

	/**
	 * return a boolean for the use of the joker 5050
	 * @return true if it was used, false if not
	 */
	public boolean isJoker5050() {
		return joker5050;
	}

	/**
	 * return a boolean for the use of the joker Switch
	 * @return true if it was used, false if not
	 */
	public boolean isJokerSwitch() {
		return jokerSwitch;
	}

	/**
	 * return a boolean for the use of the joker Call a friend
	 * @return true if it was used, false if not
	 */
	public boolean isJokerCallFriend() {
		return jokerCallFriend;
	}

	/**
	 * Allow to compare two saves when the user will save a new game
	 */
	public boolean equals(Object o){
		if(o instanceof Save){
			Save s=(Save)o;
			if(this.user.equals(s.user)){
				return true;
			}
		}
		return false;
	}

}
