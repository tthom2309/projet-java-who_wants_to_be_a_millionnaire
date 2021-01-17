package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import utility.Observateur;
import utility.Serialisation;
import model.CurrentUser;
import model.Deck;
import model.Game;
import model.GestionUser;
import model.HexagonButton;
import model.Question;
import model.Round;
import model.SaveManage;

/**
 * This class is the JPanel for the game menu.
 * 
 * @author Pierre Mayeur, Tratskas Thomas, Logeot Gautier, Delatte Mélanie
 *
 */
public class PanelMenuGame extends JPanel implements Observateur{
	
	private HexagonButton jbNewGame;
	private HexagonButton jbLoadGame;
	private HexagonButton jbMenu;
	private HexagonButton jbExit;
	
	public PanelMenuGame(){
		CurrentUser.getInstance().add(this);
		SaveManage.getInstance().add(this);
		
		this.setBackground(Color.WHITE);
		
		GroupLayout gr = new GroupLayout(this);
		this.setLayout(gr);
		
		gr.setAutoCreateGaps(true);
		gr.setAutoCreateContainerGaps(true);
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension dim = new Dimension(toolkit.getScreenSize().width,toolkit.getScreenSize().height);
		
		gr.setVerticalGroup(gr.createSequentialGroup()
				.addGap(dim.height/100*25,dim.height/100*25,dim.height/100*25)
				.addComponent(getJbNewGame(),81,81,81)
				.addComponent(getJbLoadGame(),81,81,81)
				.addComponent(getJbMenu(),81,81,81)
				.addComponent(getJbExit(),81,81,81)
				);
		
		gr.setHorizontalGroup(gr.createSequentialGroup()
				.addGap(dim.width/2-300)
				.addGroup(gr.createParallelGroup()
						.addComponent(getJbNewGame(),601,601,601)
						.addComponent(getJbLoadGame(),601,601,601)
						.addComponent(getJbMenu(),601,601,601)
						.addComponent(getJbExit(),601,601,601)
						)
				
				);
	}
	
	
	/**
	 * This method initializes and returns the button that allows to create a new game.
	 * @return HexagonButton
	 */
	public HexagonButton getJbNewGame() {
		if(jbNewGame==null){
			jbNewGame = new HexagonButton("New Game");
			jbNewGame.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Game.getInstance().begin();
					JFrameproject05 fen = (JFrameproject05)SwingUtilities.getAncestorOfClass(JFrameproject05.class, PanelMenuGame.this);
					
					if(!CurrentUser.getInstance().getUser().getPseudo().equals("guest")){
						CurrentUser.getInstance().getUser().setGamePlayed(1);
						System.out.println(GestionUser.getInstance().getUser(CurrentUser.getInstance().getUser().getPseudo(), CurrentUser.getInstance().getUser().getPassword()).getGamePlayed());
					}
					
					fen.getjPanelCartes().getCl().show(fen.getjPanelCartes(), "Game");
					
				}
			});
		}
		return jbNewGame;
	}
	
	/**
	 * This method initializes and returns the button that allows to load an existed game.
	 * @return HexagonButton
	 */
	public HexagonButton getJbLoadGame() {
		if(jbLoadGame==null){
			jbLoadGame = new HexagonButton("Load a game");
			jbLoadGame.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					Game.getInstance().load(SaveManage.getInstance().getSave(CurrentUser.getInstance().getUser()));
					JFrameproject05 fen = (JFrameproject05)SwingUtilities.getAncestorOfClass(JFrameproject05.class, PanelMenuGame.this);
					fen.getjPanelCartes().getCl().show(fen.getjPanelCartes(), "Game");
				}
			});
		}
		return jbLoadGame;
	}
	
	/**
	 * This method initializes and returns the button that allows to get back to the main menu.
	 * @return HexagonButton
	 */
	public HexagonButton getJbMenu() {
		if(jbMenu==null){
			jbMenu = new HexagonButton("Menu");
			jbMenu.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					JFrameproject05 fen = (JFrameproject05)SwingUtilities.getAncestorOfClass(JFrameproject05.class, PanelMenuGame.this);
					fen.getjPanelCartes().getCl().show(fen.getjPanelCartes(), "Menu");
					
				}
			});
		}
		return jbMenu;
	}
	/**
	 * This method initializes and returns the button that allows to quit the application
	 * @return HexagonButton
	 */
	public HexagonButton getJbExit() {
		if(jbExit==null){
			jbExit = new HexagonButton("Exit");
			jbExit.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					JFrameproject05 fen = (JFrameproject05)SwingUtilities.getAncestorOfClass(JFrameproject05.class, PanelMenuGame.this);
					int reponse = JOptionPane.showConfirmDialog(fen,"Do you want to exit?","Confirmation",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
					if (reponse==JOptionPane.YES_OPTION){
						//on vide les instances de leurs observateurs
						Deck.getInstance().removeO();
						GestionUser.getInstance().removeO();
						SaveManage.getInstance().removeO();
						//on met à jour les fichiers json
						Serialisation.toJsonSave("save.json", SaveManage.getInstance());
						Serialisation.toJsonUsers("users.json", GestionUser.getInstance());
						Serialisation.toJsonDeck("deck.json", Deck.getInstance());
						System.exit(0);
					}
					
				}
			});
		}
		return jbExit;
	}



	@Override
	public void actualise() {
		//bloc try and catch pour empêcher une exception
		try{
			if(CurrentUser.getInstance().getUser().getPseudo().equals("guest")){
				getJbLoadGame().hide();
			}
			else
			{
				getJbLoadGame().show();
			}
			if(SaveManage.getInstance().isUserhasSave(CurrentUser.getInstance().getUser())){
				getJbLoadGame().show();
			}
			else
			{
				getJbLoadGame().hide();
			}
		}catch (NullPointerException e1){
			
		}
	}
	
	
}
