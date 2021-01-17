package model;

/**
 * The interface for the ranking system based on the Adapter Design Pattern.
 * 
 * @author Pierre Mayeur, Tratskas Thomas, Logeot Gautier, Delatte Mélanie
 *
 */
public interface RankTableAdapter {

	public String[] getcolumnsName();
	public int getNBrElements();
	public Object getValueAt(int rowIndex, int columnIndex);
}
