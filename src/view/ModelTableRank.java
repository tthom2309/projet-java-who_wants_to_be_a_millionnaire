package view;

import java.util.Collections;

import javax.swing.table.AbstractTableModel;

import utility.Observateur;
import model.GestionUser;
import model.RankTableAdapter;
import model.User;

/**
 * This class create the Adapter class for the ranking panel.
 * 
 * @author Pierre Mayeur, Tratskas Thomas, Logeot Gautier, Delatte Mélanie
 *
 * @param <E>
 */
public class ModelTableRank<E extends RankTableAdapter> extends AbstractTableModel implements Observateur{

	private E model;
	private String[] columnsName;
		
	public ModelTableRank(E model) 
	{
		GestionUser.getInstance().add(this);
		this.model = model;
		columnsName = ((RankTableAdapter) model).getcolumnsName();
	}
	
	public String getColumnName(int columnIndex) 
	{
		if (columnIndex >= 0 && columnIndex < columnsName.length)
		{
			return columnsName[columnIndex];
		}
			return "Unknown";
	}
	
	@Override
	public int getColumnCount() {
		return columnsName.length;
	}

	@Override
	public int getRowCount() {
		return ((RankTableAdapter) model).getNBrElements();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return model.getValueAt(rowIndex, columnIndex);
	}

	@Override
	public void actualise() {
		this.fireTableDataChanged();
	}

}

/**
 * This class is an adapted class of ModelTableRank for the ranking by the total gain.
 * 
 * @author Pierre Mayeur, Tratskas Thomas, Logeot Gautier, Delatte Mélanie
 *
 */
class ModelTableAdapterTotal implements RankTableAdapter{

	private GestionUser clone;
	
	public ModelTableAdapterTotal() {
		clone = GestionUser.getInstance();
		clone.sortByTotalGain();
		Collections.reverse(clone.getUser());
	}
	@Override
	public String[] getcolumnsName() {
		return new String[] {"Position","Nickname","Game played","Total gain"};
	}

	@Override
	public int getNBrElements() {
		return GestionUser.getInstance().numberUsers()-1;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		User tmp = clone.getUsers(rowIndex);
		switch(columnIndex){
		case 0:
			return rowIndex+1;
		case 1:
			return tmp.getPseudo();
		case 2:
			return tmp.getGamePlayed();
		case 3:
			return tmp.getTotalGain();
		}
		return null;
	}
	
}

/**
 * This class is an adapted class of ModelTableRank for the ranking by the maximum gain.
 * 
 * @author Pierre Mayeur, Tratskas Thomas, Logeot Gautier, Delatte Mélanie
 *
 */
class ModelTableAdapterMax implements RankTableAdapter{

	private GestionUser clone;
	
	public ModelTableAdapterMax(){
		clone = GestionUser.getInstance();
		clone.sortByMaxGain();
		Collections.reverse(clone.getUser());
	}
	
	@Override
	public String[] getcolumnsName() {
		return new String[] {"Position","Nickname","Game played","Max gain"};
	}

	@Override
	public int getNBrElements() {
		return GestionUser.getInstance().numberUsers()-1;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		User tmp = clone.getUsers(rowIndex);
		switch(columnIndex){
		case 0:
			return rowIndex+1;
		case 1:
			return tmp.getPseudo();
		case 2:
			return tmp.getGamePlayed();
		case 3:
			return tmp.getMaxGain();
		}
		return null;
	}
	
}