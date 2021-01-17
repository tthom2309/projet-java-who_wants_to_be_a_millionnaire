package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;

import exceptions.QuestionRemoveException;
import utility.Observateur;
import model.ColorProject;
import model.CurrentUser;
import model.Deck;
import model.Question;

/**
 * This class is the JPanel that shows the list of he questions in a JTable.
 * 
 * @author Pierre Mayeur, Tratskas Thomas, Logeot Gautier, Delatte Mélanie
 *
 */
public class PanelQuestionTable extends JPanel implements Observateur{

	private JTable jtListeQuestions;
	private JScrollPane jscrollPane;
	private JButton jbAddQuestion;
	private JButton jbDeleteQuestion;
	private JButton jbMenu;
	
	public PanelQuestionTable(){
		CurrentUser.getInstance().add(this);
		GroupLayout gl = new GroupLayout(this);
		this.setLayout(gl);
		
		gl.setAutoCreateContainerGaps(true);
		gl.setAutoCreateGaps(true);
		
		gl.setVerticalGroup(gl.createSequentialGroup()
				.addGroup(gl.createParallelGroup()
						.addComponent(getJscrollPane())
						)
				.addGroup(gl.createParallelGroup()
						.addComponent(getJbAddQuestion(),25,25,25)
						.addComponent(getJbDeleteQuestion(),25,25,25)
						.addComponent(getJbMenu(),25,25,25)
						)
				);
		
		gl.setHorizontalGroup(gl.createParallelGroup()
				.addGroup(gl.createParallelGroup()
						.addComponent(getJscrollPane())
						)
				.addGroup(gl.createSequentialGroup()
						.addComponent(getJbAddQuestion(),120,120,120)
						.addComponent(getJbDeleteQuestion(),120,120,120)
						.addComponent(getJbMenu(),120,120,120)
						)
				);
	}

	/**
	 * This method initializes and returns the JTable.
	 * @return JTable
	 */
	public JTable getJtListeQuestions() {
		if(jtListeQuestions==null){
			jtListeQuestions = new JTable(new ModelTableauQuestion());
			jtListeQuestions.setAutoCreateRowSorter(true);
		}
		return jtListeQuestions;
	}

	/**
	 * This method initializes and returns the JScrollPane of the JTable.
	 * @return JScrollPane
	 */
	public JScrollPane getJscrollPane() {
		if(jscrollPane==null){
			jscrollPane = new JScrollPane(getJtListeQuestions());
		}
		return jscrollPane;
	}

	/**
	 * This method initializes and returns the button that allows to go to the PanelAddQuestion.  
	 * @return Jbutton
	 */
	public JButton getJbAddQuestion() {
		if(jbAddQuestion==null){
			jbAddQuestion = new JButton("Add");
			jbAddQuestion.setBackground(ColorProject.COLOR_BLUE.getCol());
			jbAddQuestion.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					jbAddQuestion.setBackground(ColorProject.COLOR_BLUE.getCol());
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					jbAddQuestion.setBackground(ColorProject.COLOR_BLUE.getCol().brighter());
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
				}
			});
			jbAddQuestion.addActionListener(e->{
					JFrameproject05 fen = (JFrameproject05)SwingUtilities.getAncestorOfClass(JFrameproject05.class, PanelQuestionTable.this);
					fen.getjPanelCartes().getCl().show(fen.getjPanelCartes(), "AddQuestion");
					
				}
			);
	
		}
		return jbAddQuestion;
	}

	/**
	 * This method initializes and returns the button that allows to delete a question from the list.
	 * @return JButton
	 */
	public JButton getJbDeleteQuestion() {
		if(jbDeleteQuestion==null){
			jbDeleteQuestion = new JButton("Delete");
			jbDeleteQuestion.setBackground(ColorProject.COLOR_BLUE.getCol());
			jbDeleteQuestion.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					jbDeleteQuestion.setBackground(ColorProject.COLOR_BLUE.getCol());
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					jbDeleteQuestion.setBackground(ColorProject.COLOR_BLUE.getCol().brighter());
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
				}
			});
			jbDeleteQuestion.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					try{
						
						int indice = getJtListeQuestions().getSelectedRow();
						if(indice!=-1){
							int reponse = JOptionPane.showConfirmDialog(PanelQuestionTable.this,"Do you really  want to remove the selected question?","Confirmation",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
							if (reponse==JOptionPane.YES_OPTION){
							
								Deck.getInstance().removeQuestion(indice);
							}
						}
						else
						{
							//si aucune question sélectionnée
							throw new QuestionRemoveException();
						}
					}
					catch (QuestionRemoveException exc){
						//montre la fenêtre d'erreur si aucune question sélectionnée
						JOptionPane.showMessageDialog(PanelQuestionTable.this, exc.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
					}
				}
			});
		}

		return jbDeleteQuestion;
	}

	/**
	 * This method initializes and returns the button that allows to get back to the main menu.
	 * @return JButton
	 */
	public JButton getJbMenu() {
		if(jbMenu==null){
			jbMenu = new JButton("Menu");
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
					JFrameproject05 fen = (JFrameproject05)SwingUtilities.getAncestorOfClass(JFrameproject05.class, PanelQuestionTable.this);
					fen.getjPanelCartes().getCl().show(fen.getjPanelCartes(), "Menu");
					
				}
			});
		}
		return jbMenu;
	}

	@Override
	public void actualise() {
		//si l'utilisateur n'est pas un admin on cache les boutons pour ajouter et supprimer une question
		if(CurrentUser.getInstance().isAdmin()){
			getJbAddQuestion().show();
			getJbDeleteQuestion().show();
		}
		else
		{
			getJbAddQuestion().hide();
			getJbDeleteQuestion().hide();
		}
	}
	
	
	
	
}

/**
 * This class redefines the table model to show the fields of questions.
 * 
 * @author Pierre Mayeur, Tratskas Thomas, Logeot Gautier, Delatte Mélanie
 *
 */
class ModelTableauQuestion extends AbstractTableModel implements Observateur{

	private Object[] nomColonnes;
	
	
	public ModelTableauQuestion() {
		Deck.getInstance();
		nomColonnes = Deck.getNomColonnes();
		CurrentUser.getInstance().add(this);
		Deck.getInstance().add(this);
		
	}
	
	@Override
	public int getColumnCount() {
		return nomColonnes.length;
	}

	public String getColumnName(int col){
		return (String)nomColonnes[col];
	}
	
	@Override
	public int getRowCount() {
		return Deck.getInstance().getNbQuestions();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(rowIndex<0 || rowIndex>=Deck.getInstance().getNbQuestions()){
			return null;
		}
		Question tmp = Deck.getInstance().getQuestion(rowIndex);
		switch(columnIndex){
			case 0:
				return tmp.getAuthor();
			case 1:
				return tmp.getRound();
			case 2:
				return tmp.getStatement();
			case 3:
				return tmp.getChoices();
		}
		return null;
	}

	@Override
	public void actualise() {
		this.fireTableDataChanged();
	}
}
