package model;
//ne pas toucher hexagonbutton, panelstart, panelmenugame, et tout ce qui va avec l'adapter(ranktableadapter, panelranking)
/**
 * This enumeration allows to give a round to a question
 * @author Pierre Mayeur, Tratksas Thomas, Logeot Gautier, Delatte Mélanie
 *
 */
public enum Round {
	FIRST_ROUND ("First round"),
	SECOND_ROUND ("Second round"),
	LAST_ROUND ("Last round");
	
	private String name="";
	
	Round(String name){
		this.name=name;
	}
	/**
	 * return the name of the round
	 * @return
	 */
	public String getName() {
		return name;
	}
	/**
	 * allow to write the name of the round
	 */
	public String toString(){
		return this.name;
	}
}
