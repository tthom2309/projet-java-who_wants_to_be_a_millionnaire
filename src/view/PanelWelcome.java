package view;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import utility.Serialisation;
import model.ColorProject;
import model.CurrentUser;
import model.Deck;
import model.GestionUser;
import model.SaveManage;

/**
 * This class creates the first panel of the application.
 * 
 * @author Pierre Mayeur, Tratskas Thomas, Logeot Gautier, Delatte Mélanie
 *
 */
public class PanelWelcome extends JPanel {

	private PanelWelcomeButton panelWelcomeButton;
	private JLabel jlLogo;
	
	public PanelWelcome(){
	
	
		
		BorderLayout bl = new BorderLayout();
		this.setLayout(bl);
		this.setBackground(Color.WHITE);
		this.add(getJlLogo(), BorderLayout.CENTER);
		this.add(getPanelWelcomeButton(),BorderLayout.SOUTH);
		
	}

	/**
	 * This method initializes and returns the PanelWelcomeButton
	 * @return PanelWelcomeButton
	 */
	public PanelWelcomeButton getPanelWelcomeButton() {
		if(panelWelcomeButton==null){
			panelWelcomeButton = new PanelWelcomeButton();
		}
		return panelWelcomeButton;
	}

	/**
	 * This method initializes and returns the JLabel with the logo of the application
	 * @return JLabel
	 */
	public JLabel getJlLogo() {
		if(jlLogo==null){
			jlLogo =  new JLabel(new ImageIcon("img/logo.png"));
		}
		return jlLogo;
	}
	
	
	
}

/**
 * This class generates the buttons of the first panel of the application.
 * 
 * @author Pierre Mayeur, Tratskas Thomas, Logeot Gautier, Delatte Mélanie
 *
 */
class PanelWelcomeButton extends JPanel{
	
	private JButton jbLogSign;
	private JButton jbGuest;
	private JButton jbExit;
	
	public PanelWelcomeButton(){
		GridLayout gl = new GridLayout(1,3,-5,0);
		this.setLayout(gl);
		this.add(getJbLogSign());
		this.add(getJbGuest());
		this.add(getJbExit());
	}

	/**
	 * This method initializes and returns the button that allows to go to the PanelConnection.
	 * @return JButton
	 */
	public JButton getJbLogSign() {
		if(jbLogSign==null){
			jbLogSign = new JButton("Log in/Sign in");
			jbLogSign.setPreferredSize(new Dimension(50, 80));
			jbLogSign.setBackground(ColorProject.COLOR_BLUE.getCol().brighter());
			jbLogSign.addActionListener(e->{//lambda
					JFrameproject05 fen = (JFrameproject05)SwingUtilities.getAncestorOfClass(JFrameproject05.class, PanelWelcomeButton.this);
					fen.getjPanelCartes().getCl().show(fen.getjPanelCartes(), "Connection");
				}
			);
		}
		return jbLogSign;
	}

	/**
	 * This method initializes and returns the button that allows to log as a guest.
	 * @return JButton
	 */
	public JButton getJbGuest() {
		if(jbGuest==null){
			jbGuest = new JButton("Log as a guest");
			jbGuest.setPreferredSize(new Dimension(250, 80));
			jbGuest.setBackground(ColorProject.COLOR_BLUE.getCol().brighter());
			jbGuest.addActionListener(e->{
					JFrameproject05 fen = (JFrameproject05)SwingUtilities.getAncestorOfClass(JFrameproject05.class, PanelWelcomeButton.this);
					CurrentUser.getInstance().setUser(GestionUser.getInstance().getUser("guest",""));
					CurrentUser.getInstance().setAdmin(GestionUser.getInstance().getUser("guest","").isAdmin());
					fen.setPreferredSize(getMaximumSize());
					fen.pack();
					fen.setLocationRelativeTo(null);
					fen.setExtendedState(JFrame.MAXIMIZED_BOTH);
					fen.getjPanelCartes().getCl().show(fen.getjPanelCartes(), "Menu");
					
				}
			);
		}
		return jbGuest;
	}

	/**
	 * This method initializes and returns the button that allows to quit the application
	 * @return
	 */
	public JButton getJbExit() {
		if(jbExit==null){
			jbExit = new JButton("Exit");
			jbExit.setPreferredSize(new Dimension(250, 80));
			jbExit.setBackground(ColorProject.COLOR_BLUE.getCol().brighter());
			jbExit.addActionListener(e->{//lambda
					JFrameproject05 fen = (JFrameproject05)SwingUtilities.getAncestorOfClass(JFrameproject05.class, PanelWelcomeButton.this);
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
		return jbExit;
	}
	
	
}