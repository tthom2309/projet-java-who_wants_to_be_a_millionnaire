package model;

import utility.Observable;

/**
 * This class create the current user instance of the application.
 * 
 * @author Tratskas Thomas, Delatte Mélanie, Logeot Gautier, Mayeur Pierre
 *
 */
public class CurrentUser extends Observable{

	private User user;
	private boolean admin;
	private static CurrentUser instance=null;
	
	private CurrentUser(){
		user=null;
		setAdmin(false);;
	}
	
	/**
	 * This method returns the class instance based on the singleton Design pattern.
	 * @return CurrentUser
	 */
	public static CurrentUser getInstance(){
		if(instance==null){
			instance = new CurrentUser();
		}
		return instance;
	}
	
	/**
	 * This method returns the current user of the instance.
	 * @return User.
	 */
	public User getUser() {
		return user;
	}
	
	/**
	 * This method change the current user of the instance.
	 * @param user
	 */
	public void setUser(User user) {
		this.user = user;
		notifyO();
	}
	
	/**
	 * This method returns if the user statute is admin or not.
	 * @return boolean
	 */
	public  boolean isAdmin() {
		return admin;
	}
	
	/**
	 * This method change the statute use of the current user.
	 * @param admin
	 */
	public void setAdmin(boolean admin) {
		this.admin = admin;
		notifyO();
	}
	
	
}
