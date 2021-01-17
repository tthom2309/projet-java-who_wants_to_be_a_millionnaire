package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import utility.Observable;
import utility.Serialisation;
/**
 * This class allows to create the save instance and to save the information of the current part in json file
 * @author Pierre
 *
 */
public class SaveManage extends Observable{

	private List<Save>saves;
	private static SaveManage instance=null;
	
	private SaveManage(){
		saves= new ArrayList<Save>();	
	}

	/**
	 * allows to know the saves of all of the users
	 * @return a list of saves
	 */
	public List<Save> getSaves() {
		return saves;
	}
	
	/**
	 * allows to create an instance of saves
	 * @return an instance of SaveManage type
	 */
	public static SaveManage getInstance() {
		if(instance==null){
			instance = Serialisation.fromJsonSave("save.json");
		}
		return instance;
	}
	
	/**
	 * allows to add a save to the list of saves
	 * @param s = the save you want to add
	 */
	public void addSave(Save s){
		if(!saves.contains(s)){
			saves.add(s);
			notifyO();
		}
	}
	
	/**
	 * return the number of saves in the list
	 * @return
	 */
	public int getNbSave(){
		return saves.size();
	}
	
	/**
	 * allows to delete a saves in the list based on the save
	 * @param s = the save you want to delete
	 */
	public void deleteSave(Save s){
		saves.remove(s);
		notifyO();
	}
	
	/**
	 * allows to know the last save of a user, the search is based on the user
	 * @param u = the user you're searching the save
	 * local variable s = the save you return
	 * @return a save
	 */
	public Save getSave(User u){
		for(Save s:saves){
			if(s.getUser().equals(u.getPseudo())){
				return s;
			}
		}
		return null;
	}
	
	/**
	 * allows to know if a user has a save in the list
	 * @param u = the user you're searching a save
	 * @return true if the user has a game, false if not
	 */
	public boolean isUserhasSave(User u){
		for(Save s:saves){
			if(s.getUser().equals(u.getPseudo())){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * allows to find a save in the list base on its index
	 * @param indice = the index of the save in the list
	 * @return a save
	 */
	public Save getSave(int indice) {
		return saves.get(indice);
	}
	
}
