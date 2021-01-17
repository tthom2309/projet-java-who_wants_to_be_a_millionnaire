package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import utility.Audio;
import utility.Serialisation;
import model.Deck;
import model.GestionUser;
import model.HexagonButton;
import model.Question;
import model.Round;
import model.SaveManage;

/**
 * This class generates the main menu of the application.
 * 
 * @author Pierre Mayeur, Tratskas Thomas, Logeot Gautier, Delatte Mélanie
 *
 */
public class PanelStart extends JPanel{

	private PanelStartCenter panelStartCenter;
	private PanelStartNorth panelStartNorth;
	
	public PanelStart(){
		
		this.setBackground(Color.WHITE);
		
		BorderLayout bl = new BorderLayout();
		this.setLayout(bl);
		this.add(getPanelStartNorth(),BorderLayout.NORTH);
		this.add(getPanelStartCenter(),BorderLayout.CENTER);
		
	}

	/**
	 * This class initializes and returns the PanelStartCenter.
	 * @return PanelStartCenter
	 */
	public PanelStartCenter getPanelStartCenter() {
		if(panelStartCenter==null){
			panelStartCenter = new PanelStartCenter();
		}
		return panelStartCenter;
	}

	/**
	 * This class initializes and returns the PanelStartNorth
	 * @return PanelStartNorth
	 */
	public PanelStartNorth getPanelStartNorth() {
		if(panelStartNorth==null){
			panelStartNorth = new PanelStartNorth();
		}
		return panelStartNorth;
	}
}

/**
 * This class generates an image on the panel of main menu.
 * 
 * @author Pierre Mayeur, Tratskas Thomas, Logeot Gautier, Delatte Mélanie
 *
 */
class PanelStartNorth extends JPanel{
	
	private JLabel jlWeathly;
	
	public PanelStartNorth(){
		this.setBackground(Color.WHITE);
		this.add(getJlWeathly(),BorderLayout.CENTER);
	}

	/**
	 * This method initializes and returns a JLabel that contains an image for the JPanelStart.
	 * @return JLabel
	 */
	public JLabel getJlWeathly() {
		if(jlWeathly==null){
			Image img = Toolkit.getDefaultToolkit().getImage("img/weathlystart.png");
			jlWeathly = new JLabel(new ImageIcon(img));
		}
		return jlWeathly;
	}

	
}

/**
 * This class generates the button of the main menu.
 * 
 * @author Pierre Mayeur, Tratskas Thomas, Logeot Gautier, Delatte Mélanie
 *
 */
class PanelStartCenter extends JPanel{
	
	private HexagonButton jbPlay;
	private HexagonButton jbQuestions;
	private HexagonButton jbRanking;
	private HexagonButton jbQuit;
	
	public PanelStartCenter() {
		this.setBackground(Color.WHITE);
		GroupLayout gr = new GroupLayout(this);
		this.setLayout(gr);
		
		gr.setAutoCreateGaps(true);
		gr.setAutoCreateContainerGaps(true);
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension dim = new Dimension(toolkit.getScreenSize().width,toolkit.getScreenSize().height);
		
	
		
		gr.setVerticalGroup(gr.createSequentialGroup()
				//.addGap(dim.height/100*25,dim.height/100*25,dim.height/100*25)
				.addComponent(getJbPlay(),81,81,81)
				.addComponent(getJbQuestions(),81,81,81)
				.addComponent(getJbRanking(),81,81,81)
				.addComponent(getJbQuit(),81,81,81)
				);
		
		gr.setHorizontalGroup(gr.createSequentialGroup()
				.addGap(dim.width/2-300)
				.addGroup(gr.createParallelGroup()
						.addComponent(getJbPlay(),601,601,601)
						.addComponent(getJbQuestions(),601,601,601)
						.addComponent(getJbRanking(),601,601,601)
						.addComponent(getJbQuit(),601,601,601)
						)
				
				);
	}
	
	/**
	 * This method initializes and returns a button that allows to go to the PanelMenuGame.
	 * @return HexagonButton
	 */
	public HexagonButton getJbPlay() {
		if(jbPlay==null){
			jbPlay = new HexagonButton("Play");
			jbPlay.addActionListener(e->{ //lambda
					JFrameproject05 fen = (JFrameproject05)SwingUtilities.getAncestorOfClass(JFrameproject05.class, PanelStartCenter.this);
					fen.getjPanelCartes().getCl().show(fen.getjPanelCartes(), "MenuGame");
					
				}
			);
		}
		return jbPlay;
	}

	/**
	 * This method initializes and returns a button that allows to go to the PanelQuestionTable.
	 * @return HexagonButton
	 */
	public HexagonButton getJbQuestions() {
		if(jbQuestions==null){
			jbQuestions = new HexagonButton("Questions");
			jbQuestions.addActionListener(e->{//lambda
					JFrameproject05 fen = (JFrameproject05)SwingUtilities.getAncestorOfClass(JFrameproject05.class, PanelStartCenter.this);
					fen.getjPanelCartes().getCl().show(fen.getjPanelCartes(), "Questions");
					
				}
			);
		}
		return jbQuestions;
	}

	/**
	 * This method initializes and returns a button that allows to go to the PanelRanking
	 * @return HexagonButton
	 */
	public HexagonButton getJbRanking() {
		if(jbRanking==null){
			jbRanking = new HexagonButton("Ranking");
			jbRanking.addActionListener(e->{
				JFrameproject05 fen = (JFrameproject05)SwingUtilities.getAncestorOfClass(JFrameproject05.class, PanelStartCenter.this);
				fen.getjPanelCartes().getCl().show(fen.getjPanelCartes(), "Ranking");
			});
		}
		return jbRanking;
	}

	/**
	 * This method initializes and returns a button that allows to quit the application.
	 * @return HexagonButton
	 */
	public HexagonButton getJbQuit() {
		if(jbQuit==null){
			jbQuit = new HexagonButton("Exit");
			jbQuit.addActionListener(e->{ //lambda
					JFrameproject05 fen = (JFrameproject05)SwingUtilities.getAncestorOfClass(JFrameproject05.class, PanelStartCenter.this);
					int reponse = JOptionPane.showConfirmDialog(fen,"Do you want to exit?","Confirmation",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
					if (reponse==JOptionPane.YES_OPTION){
						Deck.getInstance().removeO();
						GestionUser.getInstance().removeO();
						SaveManage.getInstance().removeO();
						Serialisation.toJsonSave("save.json", SaveManage.getInstance());
						Serialisation.toJsonUsers("users.json", GestionUser.getInstance());
						Serialisation.toJsonDeck("deck.json", Deck.getInstance());
						System.exit(0);
					}
				}
			);
		}
		return jbQuit;
	}
}