package utility;

import java.util.ArrayList;
import java.util.List;

/**
 * This class generate the abstract Observable based on the Observer Design Pattern.
 * 
 * @author Pierre Mayeur, Tratskas Thomas, Logeot Gautier, Delatte Mélanie
 *
 */
public abstract class Observable  {

	private List<Observateur>observers;
	
	public Observable(){
		observers = new ArrayList<Observateur>();
	}
	
	/**
	 * This method add an observer 
	 * @param Observateur
	 * @return boolean
	 */
	public boolean add(Observateur o) {
		if(!observers.contains(o)){
			return observers.add(o);
		}
		return false;
	}
	
	/**
	 * This method cleans the list of observer
	 */
	public void removeO() {
		observers.clear();
	}
	
	/**
	 * This method notify the list of observers
	 */
	public void notifyO() {
		for (Observateur o : observers) {
			o.actualise();
		}
	}
}
