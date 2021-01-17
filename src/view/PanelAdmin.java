package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;

import model.ColorProject;
import model.GestionUser;
import model.Save;
import model.SaveManage;
import model.User;
import utility.Observateur;

/**
 * This class is the JPanel for the administrator management.
 * 
 * @author Pierre Mayeur, Tratskas Thomas, Logeot Gautier, Delatte Mélanie
 *
 */
public class PanelAdmin extends JPanel{

	private PanelAdminButton panelAdminButton;
	private PanelCenterAdmin panelCenterAdmin;
	
	public PanelAdmin(){
		this.setBackground(Color.WHITE);
		BorderLayout bl = new BorderLayout();
		this.setLayout(bl);
		this.add(getPanelAdminButton(),BorderLayout.NORTH);
		this.add(getPanelCenterAdmin(),BorderLayout.CENTER);
	}

	/**
	 * This method initializes and get the PanelAdminButton.
	 * @return PanelAdminButton
	 */
	public PanelAdminButton getPanelAdminButton() {
		if(panelAdminButton==null){
			panelAdminButton = new PanelAdminButton();
		}
		return panelAdminButton;
	}

	/**
	 * This method initializes and get the PanelCenterAdmin.
	 * @return PanelCenterAdmin
	 */
	public PanelCenterAdmin getPanelCenterAdmin() {
		if(panelCenterAdmin==null){
			panelCenterAdmin = new PanelCenterAdmin();
		}
		return panelCenterAdmin;
	}
	
}

/**
 * This JPanel is the center of the PanelAdmin that shows the data of the users and saves.
 * 
 * @author Pierre Mayeur, Tratskas Thomas, Logeot Gautier, Delatte Mélanie
 *
 */
class PanelCenterAdmin extends JPanel{
	private CardLayout cl;
	private ListeUser listeUser;
	private ListeSave listeSave;
	
	public PanelCenterAdmin(){
		this.setBackground(Color.WHITE);
		this.setLayout(getCl());
		this.add(getListeUser(),"user");
		this.add(getListeSave(),"save");
	}
	
	/**
	 * This method returns the cardLayout to change the Jtable shows in this panel.
	 * @return CardLayout
	 */
	public CardLayout getCl() {
		if(cl==null){
			cl = new CardLayout();
		}
		return cl;
	}	
	/**
	 * This method returns the panel with the JTable with the list of users.
	 * @return ListeUser
	 */
	public ListeUser getListeUser() {
		if(listeUser==null){
			listeUser = new ListeUser();
		}
		return listeUser;
	}

	/**
	 * This method returns the panel with the JTable with the list of saves.
	 * @return ListeSave
	 */
	public ListeSave getListeSave() {
		if(listeSave==null){
			listeSave = new ListeSave();
		}
		return listeSave;
	}
}

/**
 * This panel contains that allows to navigate into the PanelAdmin
 * 
 * @author Pierre Mayeur, Tratskas Thomas, Logeot Gautier, Delatte Mélanie
 *
 */
class PanelAdminButton extends JPanel{
	private JButton jbUser;
	private JButton jbSave;
	private JButton jbMenu;
	
	public PanelAdminButton(){
		this.setBackground(Color.WHITE);
		this.add(getJbUser());
		this.add(getJbSave());
		this.add(getJbMenu());
	}

	/**
	 * This method initializes and returns the button that shows the JTable that contains the list of users.
	 * @return JButton
	 */
	public JButton getJbUser() {
		if(jbUser==null){
			jbUser = new JButton("Users");
			Dimension dim = new Dimension(150,30);
			jbUser.setPreferredSize(dim);
			jbUser.setBackground(ColorProject.COLOR_BLUE.getCol());
			jbUser.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					jbUser.setBackground(ColorProject.COLOR_BLUE.getCol());
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					jbUser.setBackground(ColorProject.COLOR_BLUE.getCol().brighter());
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
				}
			});
			jbUser.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					PanelAdmin pa =(PanelAdmin)SwingUtilities.getAncestorOfClass(PanelAdmin.class, PanelAdminButton.this);
					pa.getPanelCenterAdmin().getCl().show(pa.getPanelCenterAdmin(), "user");
				}
			});
		}
		return jbUser;
	}

	/**
	 * This method initializes and returns the button that shows the JTable that contains the list of saves.
	 * @return JButton
	 */
	public JButton getJbSave() {
		if(jbSave==null){
			jbSave = new JButton("Saves");
			Dimension dim = new Dimension(150,30);
			jbSave.setPreferredSize(dim);
			jbSave.setBackground(ColorProject.COLOR_BLUE.getCol());
			jbSave.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					jbSave.setBackground(ColorProject.COLOR_BLUE.getCol());
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					jbSave.setBackground(ColorProject.COLOR_BLUE.getCol().brighter());
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
				}
			});
			jbSave.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					PanelAdmin pa =(PanelAdmin)SwingUtilities.getAncestorOfClass(PanelAdmin.class, PanelAdminButton.this);
					pa.getPanelCenterAdmin().getCl().show(pa.getPanelCenterAdmin(), "save");
				}
			});
		}
		return jbSave;
	}

	/**
	 * This methods initializes and returns the button that allows to coming back to the main menu.
	 * @return JButton
	 */
	public JButton getJbMenu() {
		if(jbMenu==null){
			jbMenu = new JButton("Menu");
			Dimension dim = new Dimension(150,30);
			jbMenu.setPreferredSize(dim);
			jbMenu.setBackground(ColorProject.COLOR_BLUE.getCol());
			jbMenu.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					jbMenu.setBackground(ColorProject.COLOR_BLUE.getCol());
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					jbMenu.setBackground(ColorProject.COLOR_BLUE.getCol().brighter());
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
				}
			});
			jbMenu.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					JFrameproject05 fen = (JFrameproject05)SwingUtilities.getAncestorOfClass(JFrameproject05.class, PanelAdminButton.this);
					fen.getjPanelCartes().getCl().show(fen.getjPanelCartes(), "Menu");
					
				}
			});
		}
		return jbMenu;
	}
	
	
}

/**
 * This JPanel generate the JTable with the list of users.
 * 
 * @author Pierre Mayeur, Tratskas Thomas, Logeot Gautier, Delatte Mélanie
 *
 */
class ListeUser extends JPanel{
	
	private JTable jtUser;
	private JButton jbDelete;
	private JButton jbUpdate;
	
	public ListeUser(){
		BorderLayout bl = new BorderLayout();
		this.setLayout(bl);
		this.add(new JScrollPane(getJtUser()),BorderLayout.CENTER);
		this.add(new JPanel(),BorderLayout.WEST);
		this.add(new JPanel(),BorderLayout.EAST);
		JPanel jp = new JPanel();
		jp.add(getJbDelete());
		jp.add(getJbUpdate());
		this.add(jp,BorderLayout.SOUTH);
	}
	
	/**
	 * This method initializes and returns the JTable that contains the users list.
	 * @return JTable
	 */
	public JTable getJtUser() {
		if(jtUser==null){
			jtUser = new JTable( new ModeleTableUser());
			jtUser.setAutoCreateRowSorter(true);
		}
		return jtUser;
	}

	/**
	 * This method initializes and returns the button that allow to delete a user if he is not the "admin", "user" or "guest" user.
	 * @return JButton
	 */
	public JButton getJbDelete() {
		if(jbDelete==null){
			jbDelete = new JButton("Delete");
			jbDelete.setPreferredSize(new Dimension(120,25));
			jbDelete.setBackground(ColorProject.COLOR_BLUE.getCol());
			jbDelete.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					jbDelete.setBackground(ColorProject.COLOR_BLUE.getCol());
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					jbDelete.setBackground(ColorProject.COLOR_BLUE.getCol().brighter());
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
				}
			});
			jbDelete.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					int indice = getJtUser().getSelectedRow();
					//vérifie qu'il a bien un user sélectionné
					if(indice==-1){
						JOptionPane.showConfirmDialog(ListeUser.this,"No User selected","Error",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE );
					}
					//vérifie qu'il ne sagit pas des users "guest', "admin", ou "user"
					else if(GestionUser.getInstance().getUsers(indice).getPseudo().equals("guest") || GestionUser.getInstance().getUsers(indice).getPseudo().equals("admin") || GestionUser.getInstance().getUsers(indice).getPseudo().equals("user")){
						JOptionPane.showConfirmDialog(ListeUser.this,"You can't delete this user","Error",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE );
					}
					else
					{
						//demande à l'admin si il est sur de vouloir supprimer le user
						if(indice!=-1){
							int reponse = JOptionPane.showConfirmDialog(ListeUser.this,"Do you really want to delete this user?","Confirmation",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
							if(reponse==JOptionPane.YES_OPTION){
								GestionUser.getInstance().removeUser(indice);
							}
						}
						else
						{
							JOptionPane.showConfirmDialog(ListeUser.this,"No User selected","Error",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE );
						}
					}
				}
			});
		}
		return jbDelete;
	}

	/**
	 * This method initializes and returns the button that allows to update a user statute as an admin. 
	 * @return JButton
	 */
	public JButton getJbUpdate() {
		if(jbUpdate==null){
			jbUpdate = new JButton("Update");
			jbUpdate.setPreferredSize(new Dimension(120,25));
			jbUpdate.setBackground(ColorProject.COLOR_BLUE.getCol());
			jbUpdate.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					jbUpdate.setBackground(ColorProject.COLOR_BLUE.getCol());
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					jbUpdate.setBackground(ColorProject.COLOR_BLUE.getCol().brighter());
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
				}
			});
			jbUpdate.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					int indice = getJtUser().getSelectedRow();
					//vérifie qu'il y a bien un user sélectionné
					if(indice==-1){
						JOptionPane.showConfirmDialog(ListeUser.this, "No user selected","Error",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE);
					}
					//vérifie qu'il ne sagit pas des users "guest', "admin", ou "user"
					else if(GestionUser.getInstance().getUsers(indice).equals("user") || GestionUser.getInstance().getUsers(indice).equals("guest")){
						JOptionPane.showConfirmDialog(ListeUser.this, "You can't upate this user","Error",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE);
					}
					//vérfie si le user sélectionné est déjà admin ou non
					else if(GestionUser.getInstance().getUsers(indice).isAdmin()==true){
						JOptionPane.showConfirmDialog(ListeUser.this, "This user is already an admin","Error",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						//demande à l'admin si il est sur de vouloir update le statut de l'user
						if(indice!=-1){
							GestionUser.getInstance().getUsers(indice).setAdmin(true);
							JOptionPane.showConfirmDialog(ListeUser.this, "This user has been updated","User updated",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE);
						}
						else
						{
							JOptionPane.showConfirmDialog(ListeUser.this, "No user selected","Error",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			});
		}
		return jbUpdate;
	}
	
	
}

/**
 * This JPanel generate the JTable with the list of saves.
 * 
 * @author Pierre Mayeur, Tratskas Thomas, Logeot Gautier, Delatte Mélanie
 *
 */
class ListeSave extends JPanel{
	
	private JTable jtSave;
	
	public ListeSave(){
		BorderLayout bl = new BorderLayout();
		this.setLayout(bl);
		this.add(new JScrollPane(getJtSave()),BorderLayout.CENTER);
		this.add(new JPanel(),BorderLayout.WEST);
		this.add(new JPanel(),BorderLayout.EAST);
	}
	
	/**
	 * This method initializes and returns the JTable that contains the saves list.
	 * @return JTable
	 */
	public JTable getJtSave() {
		if(jtSave==null){
			jtSave = new JTable(new ModeleTableSave());
			jtSave.setAutoCreateRowSorter(true);
		}
		return jtSave;
	}
}

/**
 * This class redefines the table model to show the fields of users.
 * 
 * @author Pierre Mayeur, Tratskas Thomas, Logeot Gautier, Delatte Mélanie
 *
 */
class ModeleTableUser extends AbstractTableModel implements Observateur{

	private Object[] nomColonnes = {"Nickname","Name","First name","Status"};
	
	public ModeleTableUser() {
		GestionUser.getInstance().add(this);
	}
	
	public String getColumnName(int col){
		return (String)nomColonnes[col];
	}
	
	@Override
	public int getColumnCount() {
		return nomColonnes.length;
	}

	@Override
	public int getRowCount() {
		return GestionUser.getInstance().numberUsers();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		User tmp = GestionUser.getInstance().getUsers(rowIndex);
		switch(columnIndex){
		case 0:
			return tmp.getPseudo();
		case 1:
			return tmp.getNom();
		case 2:
			return tmp.getPrenom();
		case 3:
			String result="";
			if(tmp.isAdmin()==true){
				result="Admin";
			}
			if(tmp.isAdmin()==false){
				result="User";
			}
			return result;
	}
	return null;
	}

	@Override
	public void actualise() {
		this.fireTableDataChanged();
	}
	
}

/**
* This class redefines the table model to show the fields of saves.
 * 
 * @author Pierre Mayeur, Tratskas Thomas, Logeot Gautier, Delatte Mélanie
 *
 */
class ModeleTableSave extends AbstractTableModel implements Observateur{

	private Object[] nomColonnes = {"Gamer","Current Question","50:50 joker","Switch joker","Call a friend joker"};
	
	public ModeleTableSave(){
		SaveManage.getInstance().add(this);
	}
	
	
	public String getColumnName(int col){
		return (String)nomColonnes[col];
	}
	
	@Override
	public int getColumnCount() {
		return nomColonnes.length;
	}

	@Override
	public int getRowCount() {
		return SaveManage.getInstance().getNbSave();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Save tmp = SaveManage.getInstance().getSave(rowIndex);
		switch(columnIndex){
		case 0:
			return tmp.getUser();
		case 1:
			return tmp.getTour();
		case 2:
			String result5050="";
			if(tmp.isJoker5050()==true){
				result5050="Available";
			}
			else
			{
				result5050="Used";
			}
			return result5050;
		case 3:
			String resultsw="";
			if(tmp.isJokerSwitch()==true){
				resultsw="Available";
			}
			else
			{
				resultsw="Used";
			}
			return resultsw;
		case 4:
			String resultcall="";
			if(tmp.isJokerCallFriend()==true){
				resultcall="Available";
			}
			else
			{
				resultcall="Used";
			}
			return resultcall;
	}
		return null;
	}

	@Override
	public void actualise() {
		this.fireTableDataChanged();
	}
	
}