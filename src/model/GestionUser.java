package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import utility.Observable;
import utility.Serialisation;

/**
 * This class create a list of user and allow to manage it.
 * 
 * @author Tratskas Thomas, Delatte Mélanie, Logeot Gautier, Mayeur Pierre
 *
 */
public class GestionUser extends Observable{


		private List<User>users;
		private static GestionUser instance;
		
		private GestionUser(){
			users = new ArrayList<User>();
		}
		/**
		 * Based on the Pattern Singleton
		 * It allows to create only one instance for the class
		 * @return an object GestionUser
		 */
		public static GestionUser getInstance() {//attention threads
			if(instance==null){
				instance = Serialisation.fromJsonUsers("users.json");
			}
			return instance;
		}

		/**
		 * Allows to add a User
		 * @param u = the user you want to add
		 * @return true if the user is added and false if not
		 */
		public boolean addUser(User u){
			if(!users.contains(u)){
				users.add(u);
				notifyO();
				return true;
			}
			return false;
		}
		/**
		 * Remove a user
		 * @param index = the index of the user in the list
		 * @return true if the user is removed, false if not
		 */
		public boolean removeUser(int index){
		
			if(index<users.size()){
				users.remove(index);
				notifyO();
				return true;
			}
			
			return false;
		}
		/**
		 * Displays the informations of a user
		 * 
		 */
		public String toString(){
			StringBuilder result=new StringBuilder();
			for(User u: users){
				result.append(u.toString()).append("\n");
			}
			return result.toString();
		}
		/**
		 * Allow to found a user
		 * @param indice = the user's index in the liste
		 * @return
		 */
		public User getUsers(int indice) {
			return users.get(indice);
		}
		/**
		 * 
		 * @return a list of User
		 */
		public List<User> getUser(){
			return users;
		}
		/**
		 * 
		 * @return the number of Users
		 */
		public int numberUsers(){
			return users.size();
		}

		/**
		 * This method checks if the instance contains a user with the same login and password, than that passed as parameters.
		 * @param login
		 * @param password
		 * @return boolean
		 */
		public boolean containsUser(String login,String password){
			for(User u:users){
				if(u.getPseudo().equals(login) && u.getPassword().equals(password)){
					return true;
				}
			}
			return false;
		}

		/**
		 * This method checks if the instance contains a user with the nickname passed as parameter.
		 * @param nickname
		 * @return boolean
		 */
		public boolean containsUserName(String nickname){
			for(User u:users){
				if(u.getPseudo().equals(nickname)){
					return true;
				}
			}
			return false;
		}
		
		/**
		 * This method returns the user with the same login and password, than that passed as parameters.
		 * @param login
		 * @param password
		 * @return User
		 */
		public User getUser(String login,String password){
			for(User u:users){
				if(u.getPseudo().equals(login) && u.getPassword().equals(password)){
					return u;
				}
			}
			return null;
		}
		
		/**
		 * This method sort a list of User by the field maxGain.
		 */
		public void sortByMaxGain(){
			Collections.sort(users, new Comparator<User>() {

				@Override
				public int compare(User o1, User o2) {
					return ((Integer)o1.getMaxGain()).compareTo(o2.getMaxGain());
				}
				
			} );
		}
		
		/**
		 * This method sort a list of User by the field totalGain.
		 */
		public void sortByTotalGain(){
			Collections.sort(users, new Comparator<User>() {

				@Override
				public int compare(User o1, User o2) {
					return ((Integer)o1.getTotalGain()).compareTo(o2.getTotalGain());
				}
			});
		}
}
