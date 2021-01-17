package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import model.CurrentUser;
import model.Deck;
import model.Game;
import model.GestionUser;
import model.Save;
import model.SaveManage;
import utility.Audio;
import utility.Main;
import utility.Observateur;
import utility.Serialisation;
/**
 * This is the JFrame that launch the application, use all the panels
 * 
 * @author  Pierre Mayeur, Tratskas Thomas, Logeot Gautier, Delatte Mélanie
 *
 */
public class JFrameproject05 extends JFrame implements Observateur{

	private JPanelCartes jPanelCartes;

	private JMenuBar jmBar;
	private Audio son;
	public boolean activer = true;
	private JMenu jmGame;
	private JMenu jmOption;
	private JMenu jmInterrogation;
	public boolean etat = true;
	//jmGame
	private JMenuItem jmiSaveGame;
	private JMenuItem jmiHelpGame;

	//jmOption
	private JMenuItem jmiProfil;
	private JMenuItem jmiPanelAdmin;
	private JMenuItem jmiMusic;
	private JMenuItem jmiLogout; 

	//jmInterrogation
	private JMenuItem jmiAbout;

	/**
	 * Launch a song a the beginning
	 * launch an game instance, a currentUser instance
	 * A JMenubar is implemented
	 */
	public JFrameproject05(){
		son = new Audio("music/attempt.wav");
		son.start();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		Game.getInstance().add(this);
		CurrentUser.getInstance().add(this);

		this.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
			}

			@Override
			public void windowIconified(WindowEvent e) {
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
			}

			/**
			 * If the application is closed :
			 * remove all the observers in the instances and save the information in the different json files
			 */
			@Override
			public void windowClosing(WindowEvent e) {

				Deck.getInstance().removeO();
				GestionUser.getInstance().removeO();
				SaveManage.getInstance().removeO();
				Serialisation.toJsonSave("save.json", SaveManage.getInstance());
				Serialisation.toJsonUsers("users.json", GestionUser.getInstance());
				Serialisation.toJsonDeck("deck.json", Deck.getInstance());
				System.exit(0);

			}

			@Override
			public void windowClosed(WindowEvent e) {
			}

			@Override
			public void windowActivated(WindowEvent e) {
			}
		});

		this.setTitle("Who Wants To Be a Millionaire ?");
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension dim = new Dimension(toolkit.getScreenSize().width,toolkit.getScreenSize().height);
		this.setMinimumSize(new Dimension(700,500));
		this.setMaximumSize(dim);
		this.setPreferredSize(new Dimension(700,500));
		this.setLocationRelativeTo(null);
		this.pack();

		//initialisation JMBar
		this.getJmGame().add(getJmiSaveGame());
		this.getJmGame().add(getJmiHelpGame());
		this.getJmOption().add(getJmiProfil());
		this.getJmOption().add(getJmiPanelAdmin());
		this.getJmOption().add(getJmiMusic());
		this.getJmOption().add(getJmiLogout());
		this.getJmInterrogation().add(getJmiAbout());
		this.getJmBar().add(getJmGame());
		this.getJmBar().add(getJmOption());
		this.getJmBar().add(getJmInterrogation());
		this.setJMenuBar(getJmBar());


		this.add(getjPanelCartes(),BorderLayout.CENTER);


	}

	/**
	 * 
	 * @return a JPanelCartes that will switch with the different panels
	 */
	public JPanelCartes getjPanelCartes() {
		if(jPanelCartes==null){
			jPanelCartes=new JPanelCartes();
		}
		return jPanelCartes;
	}	


	/**
	 * 
	 * @return a JMenuBar with all the different options like "About" and "Activate/Desactivate music"
	 */
	public JMenuBar getJmBar() {
		if(jmBar==null){
			jmBar = new JMenuBar();
		}
		return jmBar;
	}

	/**
	 * 
	 * @return the option Game in the jmenubar 
	 */
	public JMenu getJmGame() {
		if(jmGame==null){
			jmGame = new JMenu("Game");
		}
		if(Game.getInstance().isOnGoing()==false){
			jmGame.hide();
		}
		if(Game.getInstance().isOnGoing()==true){
			jmGame.show();
		}
		return jmGame;
	}

	/**
	 * 
	 * @return the option Options in the jmenubar
	 */
	public JMenu getJmOption() {
		if(jmOption==null){
			jmOption = new JMenu("Options");
		}
		return jmOption;
	}

	/**
	 * 
	 * @return the option ? in the jmenubar
	 */
	public JMenu getJmInterrogation() {
		if(jmInterrogation==null){
			jmInterrogation = new JMenu("?");
		}
		return jmInterrogation;
	}

	/**
	 * Ask a confirmation to be sure that the user want to quit and save
	 * Prevent the user that a save already exists 
	 * @return the option Save Game in the jmenubar when you're in a game
	 */
	public JMenuItem getJmiSaveGame() {
		if(jmiSaveGame==null){
			jmiSaveGame = new JMenuItem("Save");
			jmiSaveGame.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if(SaveManage.getInstance().isUserhasSave(CurrentUser.getInstance().getUser())){
						int reponse = JOptionPane.showConfirmDialog(jmiSaveGame,"You already have a save.\nIf you want to save the current game, the previous save will be erase.\nDo you want to continue?","Confirmation",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
						if (reponse==JOptionPane.YES_OPTION){
							Save result = new Save(CurrentUser.getInstance().getUser().getPseudo(), Game.getInstance().getPalier(),Game.getInstance().getTour(),Game.getInstance().isJoker5050(),Game.getInstance().isJokerSwitch(),Game.getInstance().isJokerCallFriend());
							SaveManage.getInstance().deleteSave(SaveManage.getInstance().getSave(CurrentUser.getInstance().getUser()));
							SaveManage.getInstance().addSave(result);
							JOptionPane.showConfirmDialog(jmiSaveGame, "Your current progression erased the previous one.","Game saved",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE);
							try{
								Game.getInstance().setOnGoing(false);
								getjPanelCartes().getCl().show(getjPanelCartes(), "Menu");
							}
							catch(NullPointerException e1){

							}
						}
					}
					else
					{
						Save result = new Save(CurrentUser.getInstance().getUser().getPseudo(), Game.getInstance().getPalier(),Game.getInstance().getTour(),Game.getInstance().isJoker5050(),Game.getInstance().isJokerSwitch(),Game.getInstance().isJokerCallFriend());
						SaveManage.getInstance().addSave(result);
						JOptionPane.showConfirmDialog(jmiSaveGame, "Your current game has been saved.","Game saved",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE);
						try{
							Game.getInstance().setOnGoing(false);
							getjPanelCartes().getCl().show(getjPanelCartes(), "Menu");
						}
						catch(NullPointerException e1){

						}
					}
				}
			});
		}
		if(CurrentUser.getInstance().getUser()==null){
			jmiSaveGame.hide();
		}
		else if(CurrentUser.getInstance().getUser().getPseudo().equals("guest")){
			jmiSaveGame.hide();

		}
		else
		{

			jmiSaveGame.show();
		}
		return jmiSaveGame;
	}

	/**
	 * 
	 * @return the option Help in the jmenubar
	 */
	public JMenuItem getJmiHelpGame() {
		if(jmiHelpGame==null){
			jmiHelpGame = new JMenuItem("Help");
			jmiHelpGame.hide();
		}
		return jmiHelpGame;
	}

	/**
	 * 
	 * @return the option Profile when you're connected with an account
	 */
	public JMenuItem getJmiProfil() {
		if(jmiProfil==null){
			jmiProfil = new JMenuItem("Profile");
			jmiProfil.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					getjPanelCartes().getCl().show(getjPanelCartes(), "Profil");
				}
			});
		}
		if(CurrentUser.getInstance().getUser()==null){
			jmiProfil.hide();
		}
		else if(CurrentUser.getInstance().getUser().getPseudo().equals("guest")){
			jmiProfil.hide();
		}
		else if(Game.getInstance().isOnGoing()==true){
			jmiProfil.hide();
		}
		else if(CurrentUser.getInstance().getUser()!=null && !CurrentUser.getInstance().getUser().getPseudo().equals("guest") && Game.getInstance().isOnGoing()==false){
			jmiProfil.show();
		}
		return jmiProfil;
	}

	/**
	 * 
	 * @return the option Panel Admin when you're connected as an admin
	 */
	public JMenuItem getJmiPanelAdmin() {
		if(jmiPanelAdmin==null){
			jmiPanelAdmin = new JMenuItem("Management");
			jmiPanelAdmin.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					getjPanelCartes().getCl().show(getjPanelCartes(), "Admin");

				}
			});
		}
		if(CurrentUser.getInstance().isAdmin()==true && Game.getInstance().isOnGoing()==false){
			jmiPanelAdmin.show();
		}
		else
		{
			jmiPanelAdmin.hide();
		}
		return jmiPanelAdmin;
	}

	/**
	 * 
	 * @return an option to desactivate and activate the music
	 */
	public JMenuItem getJmiMusic(){

		if(jmiMusic==null){
			jmiMusic = new JMenuItem("Activate/Desactivate music");
			jmiMusic.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if(isActiver() == true){
						setActiver(false);
						getSon().stop(); // on stoppe tout son
					}
					else
					{
						setActiver(true);
						setSon(new Audio("music/attempt.wav")); // Je recré le son pour éviter un bug de thread
						getSon().start(); // on relance le son
					}


				}
			});
		}
		return jmiMusic;
	}

	/**
	 * return the song that is activate
	 * @return
	 */
	public Audio getSon() 
	{
		return son;
	}

	/**
	 * Change the sound of the game
	 * @param son
	 */
	public void setSon(Audio son) 
	{
		this.son = son;
	}

	/**
	 * Return the boolean wich can tell is the sound is activate
	 * @return true is sound works, false is sound doesn't work
	 */
	public boolean isActiver()
	{
		return activer;
	}

	/**
	 * Activate the sound
	 * @param activer
	 */
	public void setActiver(boolean activer) 
	{
		this.activer = activer;
	}

	/**
	 * Ask a confirmation
	 * @return an option Log Out when you're connected
	 */
	public JMenuItem getJmiLogout() {
		if(jmiLogout==null){
			jmiLogout = new JMenuItem("Log out");
			jmiLogout.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					int reponse = JOptionPane.showConfirmDialog(JFrameproject05.this,"Do you really want to log out?","Confirmation",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
					if (reponse==JOptionPane.YES_OPTION){
						CurrentUser.getInstance().setUser(null);
						setPreferredSize(getMinimumSize());
						pack();
						setLocationRelativeTo(null);
						getjPanelCartes().getCl().show(getjPanelCartes(), "Welcome");
					}
				}
			});
		}
		if(CurrentUser.getInstance().getUser()==null){
			jmiLogout.hide();
		}
		else
		{
			jmiLogout.show();
		}
		ActionListener[] listeners = jmiLogout.getActionListeners();
		for (ActionListener l : listeners) 
		{
			jmiLogout.removeActionListener(l);
		}
		if(Game.getInstance().isOnGoing()==false){
			jmiLogout.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					int reponse = JOptionPane.showConfirmDialog(JFrameproject05.this,"Do you really want to log out?","Confirmation",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
					if (reponse==JOptionPane.YES_OPTION){
						CurrentUser.getInstance().setUser(null);
						setPreferredSize(getMinimumSize());
						pack();
						setLocationRelativeTo(null);
						getjPanelCartes().getCl().show(getjPanelCartes(), "Welcome");
					}
				}
			});
		}
		if(Game.getInstance().isOnGoing()==true){
			jmiLogout.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					int reponse = JOptionPane.showConfirmDialog(JFrameproject05.this,"You gonna lose your progression, do you really want to log out?","Confirmation",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
					if (reponse==JOptionPane.YES_OPTION){
						CurrentUser.getInstance().setUser(null);
						Game.getInstance().setOnGoing(false);
						setPreferredSize(getMinimumSize());
						pack();
						setLocationRelativeTo(null);
						getjPanelCartes().getCl().show(getjPanelCartes(), "Welcome");
					}
				}
			});
		}
		return jmiLogout;

	}

	/**
	 * 
	 * @return the option About in the jmenubar
	 */
	public JMenuItem getJmiAbout() {
		if(jmiAbout==null){
			jmiAbout = new JMenuItem("About");
			jmiAbout.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					ImageIcon img = new ImageIcon("img/gladosIcone.png");
					JOptionPane.showMessageDialog(jmiAbout, 
							"		Who wanna be a millionnaire ?\n\n"
									+ 
									"Developers :\n"
									+
									"------------\n\nTratskas Thomas\nDelatte Mélanie\nLogeot Gautier\nMayeur Pierre\n\n"
									+
									"Sound :\n"
									+
									"-------\n"
									+
									"[Bokutachi wa Tenshi datta]8bit - Studio Megaane\n\n"
									+
									"No Idea Company : 2015-2016\n\n","About",JOptionPane.DEFAULT_OPTION,img);
				}
			});
		}
		return jmiAbout;
	}

	/**
	 * This method gives the maximum size of the frame
	 */
	public Dimension getMaximumSize(){
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension dim = new Dimension(toolkit.getScreenSize().width,toolkit.getScreenSize().height);
		return dim;
	}


	/**
	 * This method gives the minimum size of the frame
	 */
	public Dimension getMinimumSize(){
		return new Dimension(750, 500);
	}

	/**
	 * This method actualizes and manages the items of the JMenuBar 
	 */
	@Override
	public void actualise() {
		getJmGame();
		getJmiSaveGame();
		getJmiProfil();
		getJmiLogout();
		getJmiPanelAdmin();
	}
}
