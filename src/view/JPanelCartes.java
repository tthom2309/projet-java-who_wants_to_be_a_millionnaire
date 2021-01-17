package view;

import java.awt.CardLayout;

import javax.swing.JPanel;

/**
 * This class is a JPanel with a CardLayout to display all the panel inside the JFrameproject05.
 * 
 * @author Pierre Mayeur, Tratskas Thomas, Logeot Gautier, Delatte Mélanie
 *
 */
public class JPanelCartes extends JPanel{
	
	//liste des différents panels de l'application
	private PanelWelcome panelWelcome;
	private PanelConnection panelConnection;
	private PanelStart panelStart;
	private PanelQuestionTable panelQuestionTable;
	private PanelQuestionView panelQuestionView;
	private PanelAddQuestion panelAddQuestion;
	private PanelMenuGame  panelMenuGame;
	private PanelEndGame panelEndGame;
	private PanelAdmin panelAdmin;
	private PanelProfil panelProfil;
	private PanelRanking panelRanking;
	private CardLayout cl;
	
	/**
	 * Returns the CardLayout associated to JPanelCartes.
	 * 
	 * @return CardLayout
	 */
	public CardLayout getCl() {
		if(cl==null){
			cl=new CardLayout();
		}
		return cl;
	}
	
	public JPanelCartes(){
		this.setLayout(getCl());
		this.add(getPanelWelcome(),"Welcome");
		this.add(getPanelConnection(),"Connection");
 		this.add(getPanelStart(),"Menu");
 		this.add(getPanelQuestionTable(),"Questions");
 		this.add(getPanelAddQuestion(),"AddQuestion");
 		this.add(getPanelMenuGame(),"MenuGame");
 		this.add(getPanelQuestionView(),"Game");
 		this.add(getPanelEndGame(),"EndGame");
 		this.add(getPanelAdmin(), "Admin");
 		this.add(getPanelProfil(), "Profil");
 		this.add(getPanelRanking(),"Ranking");
	}

	
	/**
	 * Returns the panel to log in or sign in.
	 * @return PanelConnection
	 */
	public PanelConnection getPanelConnection() {
		if(panelConnection==null){
			panelConnection = new PanelConnection();
		}
		return panelConnection;
	}

	/**
	 * Returns the menu panel.
	 * @return PanelStart
	 */
	public PanelStart getPanelStart() {
		if(panelStart==null){
			panelStart = new PanelStart();
		}
		return panelStart;
	}

	/**
	 * Returns the panel with the questions table.
	 * @return PanelQuestionTable
	 */
	public PanelQuestionTable getPanelQuestionTable() {
		if(panelQuestionTable==null){
			panelQuestionTable = new PanelQuestionTable();
		}
		return panelQuestionTable;
	}

	/**
	 * Returns the panel where the user play a game.
	 * @return PanelquestionView
	 */
	public PanelQuestionView getPanelQuestionView() {
		if(panelQuestionView==null){
			panelQuestionView = new PanelQuestionView();
		}
		return panelQuestionView;
	}

	/**
	 * Returns the panel where an admin can add a question in the application.
	 * @return PanelAddQuestion
	 */
	public PanelAddQuestion getPanelAddQuestion() {
		if(panelAddQuestion==null){
			panelAddQuestion = new PanelAddQuestion();
		}
		return panelAddQuestion;
	}

	/**
	 * Returns the panel that allow to begin a game or load a previous one.
	 * @return PanelMenuGame
	 */
	public PanelMenuGame getPanelMenuGame() {
		if(panelMenuGame==null){
			panelMenuGame = new PanelMenuGame();
		}
		return panelMenuGame;
	}

	/**
	 * Returns the panel that show your results at the end of a game.
	 * @return PanelEndGame
	 */
	public PanelEndGame getPanelEndGame() {
		if(panelEndGame==null){
			panelEndGame = new PanelEndGame();
		}
		return panelEndGame;
	}

	/**
	 * Returns the panel the first panel of the application.
	 * @return PanelWelcome
	 */
	public PanelWelcome getPanelWelcome() {
		if(panelWelcome==null){
			panelWelcome = new PanelWelcome();
		}
		return panelWelcome;
	}

	/**
	 * Returns the panel of administrator management.
	 * @return PanelAdmin
	 */
	public PanelAdmin getPanelAdmin() {
		if(panelAdmin==null){
			panelAdmin = new PanelAdmin();
		}
		return panelAdmin;
	}

	/**
	 * Returns the panel that show the profile of the current user.
	 * @return PanelProfil
	 */
	public PanelProfil getPanelProfil() {
		if(panelProfil==null){
			panelProfil = new PanelProfil();
		}
		return panelProfil;
	}

	/**
	 * Returns the panel that show the ranks of each user.
	 * @return PanelRanking
	 */
	public PanelRanking getPanelRanking() {
		if(panelRanking==null){
			panelRanking = new PanelRanking();
		}
		return panelRanking;
	}
	
	
	
}
