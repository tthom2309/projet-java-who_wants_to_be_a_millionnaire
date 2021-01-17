package view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingUtilities;

import exceptions.QuestionException;
import model.ColorProject;
import model.Deck;
import model.Question;
import model.Round;
/**
 * this panel allows to add a question to the deck, list of question
 * @author Pierre Mayeur, Tratksas Thomas, Logeot Gautier, Delatte Mélanie
 *
 */
public class PanelAddQuestion extends JPanel {

	private JLabel jlAuthor;
	private JLabel jlStatement;
	private JLabel jlChoice1;
	private JLabel jlChoice2;
	private JLabel jlChoice3;
	private JLabel jlChoice4;
	
	private JTextField jtAuthor;
	private JTextField jtStatement;
	private JTextField jtChoice1;
	private JTextField jtChoice2;
	private JTextField jtChoice3;
	private JTextField jtChoice4;
	
	private JComboBox<String>jcRound;
	private JRadioButton jrChoice1;
	private JRadioButton jrChoice2;
	private JRadioButton jrChoice3;
	private JRadioButton jrChoice4;
	
	private JButton jbSave;
	private JButton jbCancel;
	
	/**
	 * Constructs a panel which contains a form to enter and save some questions
	 */
	public PanelAddQuestion(){
		
		
		GroupLayout gr = new GroupLayout(this);
		this.setLayout(gr);
		gr.setAutoCreateGaps(true);
		gr.setAutoCreateContainerGaps(true);
		
		gr.setVerticalGroup(gr.createSequentialGroup()
				.addGroup(gr.createParallelGroup()
						.addComponent(getJlAuthor())
						.addComponent(getJtAuthor(),GroupLayout.PREFERRED_SIZE,25,GroupLayout.PREFERRED_SIZE)
						)
				.addGap(100)
				.addGroup(gr.createParallelGroup()
						.addComponent(getJlStatement())
						.addComponent(getJtStatement(),GroupLayout.PREFERRED_SIZE,25,GroupLayout.PREFERRED_SIZE)
						.addComponent(getJcRound(),GroupLayout.PREFERRED_SIZE,25,GroupLayout.PREFERRED_SIZE)
						)
				.addGroup(gr.createParallelGroup()
						.addComponent(getJlChoice1())
						.addComponent(getJtChoice1(),GroupLayout.PREFERRED_SIZE,25,GroupLayout.PREFERRED_SIZE)
						.addComponent(getJrChoice1())
						)
				.addGroup(gr.createParallelGroup()
						.addComponent(getJlChoice2())
						.addComponent(getJtChoice2(),GroupLayout.PREFERRED_SIZE,25,GroupLayout.PREFERRED_SIZE)
						.addComponent(getJrChoice2())
						)
				.addGroup(gr.createParallelGroup()
						.addComponent(getJlChoice3())
						.addComponent(getJtChoice3(),GroupLayout.PREFERRED_SIZE,25,GroupLayout.PREFERRED_SIZE)
						.addComponent(getJrChoice3())
						)
				.addGroup(gr.createParallelGroup()
						.addComponent(getJlChoice4())
						.addComponent(getJtChoice4(),GroupLayout.PREFERRED_SIZE,25,GroupLayout.PREFERRED_SIZE)
						.addComponent(getJrChoice4())
						)
				.addGroup(gr.createParallelGroup()
						.addComponent(getJbSave(),GroupLayout.PREFERRED_SIZE,25,GroupLayout.PREFERRED_SIZE)
						.addComponent(getJbCancel(),GroupLayout.PREFERRED_SIZE,25,GroupLayout.PREFERRED_SIZE)
						)
				);
		
		gr.setHorizontalGroup(gr.createParallelGroup()
				.addGroup(gr.createSequentialGroup()
						.addComponent(getJlAuthor())
						.addComponent(getJtAuthor())
						)
				.addGroup(gr.createSequentialGroup()
						.addComponent(getJlStatement())
						.addComponent(getJtStatement())
						.addComponent(getJcRound(),GroupLayout.PREFERRED_SIZE,150,GroupLayout.PREFERRED_SIZE)
						)
				.addGroup(gr.createSequentialGroup()
						.addComponent(getJlChoice1())
						.addComponent(getJtChoice1())
						.addComponent(getJrChoice1())
						)
				.addGroup(gr.createSequentialGroup()
						.addComponent(getJlChoice2())
						.addComponent(getJtChoice2())
						.addComponent(getJrChoice2())
						)
				.addGroup(gr.createSequentialGroup()
						.addComponent(getJlChoice3())
						.addComponent(getJtChoice3())
						.addComponent(getJrChoice3())
						)
				.addGroup(gr.createSequentialGroup()
						.addComponent(getJlChoice4())
						.addComponent(getJtChoice4())
						.addComponent(getJrChoice4())
						)
				.addGroup(gr.createSequentialGroup()
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(getJbSave(),GroupLayout.PREFERRED_SIZE,100,GroupLayout.PREFERRED_SIZE)
						.addComponent(getJbCancel(),GroupLayout.PREFERRED_SIZE,100,GroupLayout.PREFERRED_SIZE)
						)
				);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(this.getJrChoice1());
		bg.add(this.getJrChoice2());
		bg.add(this.getJrChoice3());
		bg.add(this.getJrChoice4());
	}

	/**
	 * 
	 * @return the jlabel of the author
	 */
	public JLabel getJlAuthor() {
		if(jlAuthor==null){
			jlAuthor = new JLabel("Author: ");
		}
		return jlAuthor;
	}

	/**
	 * 
	 * @return the jlabel of the statement
	 */
	public JLabel getJlStatement() {
		if(jlStatement==null){
			jlStatement = new JLabel("Statement: ");
		}
		return jlStatement;
	}

	/**
	 * 
	 * @return the jlabel of the choice 1
	 */
	public JLabel getJlChoice1() {
		if(jlChoice1==null){
			jlChoice1 = new JLabel("Choice: ");
		}
		return jlChoice1;
	}

	/**
	 * 
	 * @return the jlabel of the choice 2
	 */
	public JLabel getJlChoice2() {
		if(jlChoice2==null){
			jlChoice2 = new JLabel("Choice: ");
		}
		return jlChoice2;
	}

	/**
	 * 
	 * @return the jlabel of the choice 3
	 */
	public JLabel getJlChoice3() {
		if(jlChoice3==null){
			jlChoice3 = new JLabel("Choice: ");
		}
		return jlChoice3;
	}

	/**
	 * 
	 * @return the jlabel of the choice 4
	 */
	public JLabel getJlChoice4() {
		if(jlChoice4==null){
			jlChoice4 = new JLabel("Choice: ");
		}
		return jlChoice4;
	}

	public JTextField getJtAuthor() {
		if(jtAuthor==null){
			jtAuthor = new JTextField();
		}
		return jtAuthor;
	}

	/**
	 * Returns the JtextField of the question's statement
	 * @return the statement
	 */
	public JTextField getJtStatement() {
		if(jtStatement==null){
			jtStatement = new JTextField();
		}
		return jtStatement;
	}

	/**
	 * Returns the JtextField of the question's choice 1
	 * @return the choice 1
	 */
	public JTextField getJtChoice1() {
		if(jtChoice1==null){
			jtChoice1 = new JTextField();
		}
		return jtChoice1;
	}

	/**
	 * Returns the JtextField of the question's choice 2
	 * @return the choice 2
	 */
	public JTextField getJtChoice2() {
		if(jtChoice2==null){
			jtChoice2 = new JTextField();
		}
		return jtChoice2;
	}

	/**
	 * Returns the JtextField of the question's choice 3
	 * @return the choice 3
	 */
	public JTextField getJtChoice3() {
		if(jtChoice3==null){
			jtChoice3 = new JTextField();
		}
		return jtChoice3;
	}

	/**
	 * Returns the JtextField of the question's choice 4
	 * @return the choice 4
	 */
	public JTextField getJtChoice4() {
		if(jtChoice4==null){
			jtChoice4 = new JTextField();
		}
		return jtChoice4;
	}

	/**
	 * 
	 * @return a JComboBox with the values of the different round
	 */
	public JComboBox<String> getJcRound() {
		if(jcRound==null){
			jcRound = new JComboBox<String>();
			jcRound.addItem(Round.FIRST_ROUND.toString());
			jcRound.addItem(Round.SECOND_ROUND.toString());
			jcRound.addItem(Round.LAST_ROUND.toString());
		}
		return jcRound;
	}

	/**
	 * 
	 * @return true if checked, false if not
	 */
	public JRadioButton getJrChoice1() {
		if(jrChoice1==null){
			jrChoice1 = new JRadioButton("True");
		}
		return jrChoice1;
	}

	/**
	 * 
	 * @return true if checked, false if not
	 */
	public JRadioButton getJrChoice2() {
		if(jrChoice2==null){
			jrChoice2 = new JRadioButton("True");
		}
		return jrChoice2;
	}

	/**
	 * 
	 * @return true if checked, false if not
	 */
	public JRadioButton getJrChoice3() {
		if(jrChoice3==null){
			jrChoice3 = new JRadioButton("True");
		}
		return jrChoice3;
	}

	/**
	 * 
	 * @return true if checked, false if not
	 */
	public JRadioButton getJrChoice4() {
		if(jrChoice4==null){
			jrChoice4 = new JRadioButton("True");
		}
		return jrChoice4;
	}

	/**
	 * 
	 * @return a JButton that allows to save the question
	 */
	public JButton getJbSave() {
		if(jbSave==null){
			jbSave = new JButton("Save");
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
					Question result = extractQuestion();
					
					try {
						if(result.check()==true){
							JOptionPane.showMessageDialog(PanelAddQuestion.this,"Question add","Confirmation",JOptionPane.INFORMATION_MESSAGE);
							Deck.getInstance().addQuestion(result);
						}
					} catch (QuestionException e1) {
						JOptionPane.showMessageDialog(PanelAddQuestion.this, e1.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
					}
					
					
				}
				
				/**
				 * allows to extract the information that the user enters and transform it in a object 
				 * Question
				 * @return
				 */
				private Question extractQuestion() {
					
					String author = getJtAuthor().getText();
					
					Round round = null;
					if(getJcRound().getSelectedItem().toString().equals(Round.FIRST_ROUND.toString())){
						round = Round.FIRST_ROUND;
					}
					if(getJcRound().getSelectedItem().toString().equals(Round.SECOND_ROUND.toString())){
						round = Round.SECOND_ROUND;
					}
					if(getJcRound().getSelectedItem().toString().equals(Round.LAST_ROUND.toString())){
						round = Round.LAST_ROUND;
					}
					
					String statement = getJtStatement().getText();
					
					Question result = new Question(author, round, statement);
					for(int i=0; i<4;i++){
						result.addAnswer(getJtEnterTab()[i], getResultTab()[i]);
					}
					
					System.out.println(result.toString());
					
					return result;
				}
				
				/**
				 * allows to know if the jradiobutton is checked or not
				 * @return
				 */
				private boolean[] getResultTab(){
					boolean[] resultTab = new boolean[4];
					resultTab[0] = getJrChoice1().isSelected();
					resultTab[1] = getJrChoice2().isSelected();
					resultTab[2] = getJrChoice3().isSelected();
					resultTab[3] = getJrChoice4().isSelected();
					return resultTab;
				}
				
				/**
				 * 
				 * @return the value of the choices in a board
				 */
				private String[] getJtEnterTab(){
					String[] jtEnterTab = new String[4];
					jtEnterTab[0] = getJtChoice1().getText();
					jtEnterTab[1] = getJtChoice2().getText();
					jtEnterTab[2] = getJtChoice3().getText();
					jtEnterTab[3] = getJtChoice4().getText();
					return jtEnterTab;
				}
							
			});
		}
		return jbSave;
	}

	/**
	 * allows to cancel the adding of a question and get back
	 * to the panelQuestionTable
	 * @return a jbutton
	 */
	public JButton getJbCancel() {
		if(jbCancel==null){
			jbCancel = new JButton("Back");
			jbCancel.setBackground(ColorProject.COLOR_BLUE.getCol());
			jbCancel.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					jbCancel.setBackground(ColorProject.COLOR_BLUE.getCol());
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					jbCancel.setBackground(ColorProject.COLOR_BLUE.getCol().brighter());
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
				}
			});
			jbCancel.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					JFrameproject05 fen = (JFrameproject05)SwingUtilities.getAncestorOfClass(JFrameproject05.class, PanelAddQuestion.this);
					fen.getjPanelCartes().getCl().show(fen.getjPanelCartes(), "Questions");
					
				}
			});
		}
		return jbCancel;
	}

	
}
