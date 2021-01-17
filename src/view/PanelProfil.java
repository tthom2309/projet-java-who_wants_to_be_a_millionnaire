package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Group;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;

import utility.Observateur;
import model.ColorProject;
import model.CurrentUser;

/**
 * This JPanel shows the information of the current user.
 * 
 * @author Pierre Mayeur, Tratskas Thomas, Logeot Gautier, Delatte Mélanie
 *
 */
public class PanelProfil extends JPanel implements Observateur{

	private JPanel jpLeft;
	private JPanel jpRight;

	private JLabel jlPseudo;
	private JLabel jlNom;
	private JLabel jlFirstName;
	private JLabel jlBirthDate;
	private JLabel jlMail;

	private JLabel jlGameplayed;
	private JLabel jlNbCorrectAnswer;
	private JLabel jlMoyGain;
	private JLabel jlTotalGain;
	private JLabel jlNbGiveUp;
	private JLabel jlVictory;
	private JLabel jlLoose;
	private JLabel jlNbJoker;
	private JLabel jlJoker5050;
	private JLabel jlJokerSwitch;
	private JLabel jlCallFriend;
	//bouton retour au menu
	private JButton backMenu;

	public PanelProfil(){
		CurrentUser.getInstance().add(this);

		Image img = Toolkit.getDefaultToolkit().getImage("img/informations.png");
		JLabel wheathley = new JLabel(new ImageIcon(img));

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension dim = new Dimension(toolkit.getScreenSize().width,toolkit.getScreenSize().height);

		GroupLayout gr=new GroupLayout(this);
		this.setLayout(gr);

		gr.setAutoCreateGaps(true);
		gr.setAutoCreateContainerGaps(true);

		gr.setVerticalGroup(gr.createSequentialGroup()
				.addGroup(gr.createParallelGroup()
						.addComponent(wheathley)
						)
						.addGroup(gr.createParallelGroup()
								.addComponent(getJpLeft(),350,350,350)
								.addComponent(getJpRight(),350,350,350)
								)
								.addGroup(gr.createParallelGroup()
								.addComponent(getBackMenu(),30,30,30)
								)
				);

		gr.setHorizontalGroup(gr.createParallelGroup()
				.addGroup(gr.createSequentialGroup()
						.addGap(dim.width/2-135)
						.addComponent(wheathley)
						)
						.addGroup(gr.createSequentialGroup()
								.addGap(dim.width/2-400)
								.addComponent(getJpLeft(),400,400,400)
								.addComponent(getJpRight(),400,400,400)
								)
								.addGroup(gr.createSequentialGroup()
								.addGap(dim.width/2-100)
								.addComponent(getBackMenu(),200,200,200)
								)
				);

	}



	public JLabel getJlPseudo() {
		if(jlPseudo==null){

			jlPseudo = new JLabel("Pseudo:  ");

		}
		if(CurrentUser.getInstance().getUser()==null){
			jlPseudo.setText("Nickname:  ");
		}
		else
		{
			jlPseudo.setText("Nickname:  "+CurrentUser.getInstance().getUser().getPseudo());
		}
		return jlPseudo;
	}

	public JLabel getJlNom() {
		if(jlNom==null){
			jlNom = new JLabel("Name: ");
		}
		if(CurrentUser.getInstance().getUser()==null){
			jlNom.setText("Name: ");
		}
		else
		{
			jlNom.setText("Name: "+CurrentUser.getInstance().getUser().getNom());
		}
		return jlNom;
	}

	public JLabel getJlFirstName() {
		if(jlFirstName==null){

			jlFirstName = new JLabel("First Name: ");

		}if(CurrentUser.getInstance().getUser()==null){
			jlFirstName.setText("First Name: ");
		}
		else
		{
			jlFirstName.setText("First Name: "+CurrentUser.getInstance().getUser().getPrenom());
		}
		return jlFirstName;
	}

	public JLabel getJlBirthDate() {
		if(jlBirthDate==null){
			jlBirthDate = new JLabel("Date of birthday: ");
		}
		if(CurrentUser.getInstance().getUser()==null){
			jlBirthDate.setText("Date of birthday: ");
		}
		else
		{
			jlBirthDate.setText("Date of birthday: "+CurrentUser.getInstance().getUser().getBirthDate());
		}
		return jlBirthDate;
	}

	public JLabel getJlMail() {
		if(jlMail==null){
			jlMail = new JLabel("Mail: ");
		}
		if(CurrentUser.getInstance().getUser()==null){
			jlMail.setText("Mail: ");
		}
		else
		{
			jlMail.setText("Mail: "+CurrentUser.getInstance().getUser().getMail());
		}
		return jlMail;
	}

	public JLabel getJlGameplayed() {
		if(jlGameplayed==null ){
			jlGameplayed = new JLabel("Parts played:  ");
		}
		if(CurrentUser.getInstance().getUser()==null){
			jlGameplayed.setText("Parts played:  ");
		}
		else
		{
			jlGameplayed.setText("Parts played:  "+CurrentUser.getInstance().getUser().getGamePlayed());
		}
		return jlGameplayed;
	}

	public JLabel getJlNbCorrectAnswer() {
		if(jlNbCorrectAnswer==null){
			jlNbCorrectAnswer = new JLabel("Correct answers given: ");
		}
		if(CurrentUser.getInstance().getUser()==null){
			jlNbCorrectAnswer.setText("Correct answers given: ");
		}
		else
		{
			jlNbCorrectAnswer.setText("Correct answers given: "+CurrentUser.getInstance().getUser().getNbCorrectAnswers());
		}
		return jlNbCorrectAnswer;
	}

	public JLabel getJlMoyGain() {
		if(jlMoyGain==null){
			jlMoyGain = new JLabel("Average Gain: ");
		}
		if(CurrentUser.getInstance().getUser()==null){
			jlMoyGain.setText("Average Gain: ");
		}
		else
		{
			if(CurrentUser.getInstance().getUser().getGamePlayed()!=0){
				jlMoyGain.setText("Average Gain: "+CurrentUser.getInstance().getUser().getTotalGain()/CurrentUser.getInstance().getUser().getGamePlayed());
			}
		}
		return jlMoyGain;
	}

	public JLabel getJlTotalGain() {
		if(jlTotalGain==null){
			jlTotalGain = new JLabel("Total of Gains: ");
		}
		if(CurrentUser.getInstance().getUser()==null){
			jlTotalGain.setText("Total of Gains: ");
		}
		else
		{
			jlTotalGain.setText("Total of Gains: "+CurrentUser.getInstance().getUser().getTotalGain());
		}
		return jlTotalGain;
	}

	public JLabel getJlNbGiveUp() {
		if(jlNbGiveUp==null){
			jlNbGiveUp = new JLabel("Number of \"Give Up\": ");
		}
		if(CurrentUser.getInstance().getUser()==null){
			jlNbGiveUp.setText("Number of \"Give Up\": ");
		}
		else
		{
			jlNbGiveUp.setText("Number of \"Give Up\": "+CurrentUser.getInstance().getUser().getNbGiveUp());
		}
		return jlNbGiveUp;
	}

	public JLabel getJlVictory() {
		if(jlVictory==null){
			jlVictory = new JLabel("Number of victories: ");
		}
		if(CurrentUser.getInstance().getUser()==null){
			jlVictory.setText("Number of victories: ");
		}
		else
		{
			jlVictory.setText("Number of victories: "+CurrentUser.getInstance().getUser().getNbWin());
		}
		return jlVictory;
	}

	public JLabel getJlLoose() {
		if(jlLoose==null){
			jlLoose = new JLabel("Number of looses: ");
		}
		if(CurrentUser.getInstance().getUser()==null){
			jlLoose.setText("Number of looses: ");
		}
		else
		{
			jlLoose.setText("Number of looses: "+CurrentUser.getInstance().getUser().getNbLoose());
		}
		return jlLoose;
	}

	public JLabel getJlNbJoker() {
		if(jlNbJoker==null){
			jlNbJoker = new JLabel("Total of jokers used: ");
		}
		if(CurrentUser.getInstance().getUser()==null){
			jlNbJoker.setText("Total of jokers used: ");
		}
		else
		{
			jlNbJoker.setText("Total of jokers used: "+CurrentUser.getInstance().getUser().getNbJokers());
		}
		return jlNbJoker;
	}

	public JLabel getJlJoker5050() {
		if(jlJoker5050==null){
			jlJoker5050=new JLabel("Total of jokers \"50 - 50\" used: ");
		}
		if(CurrentUser.getInstance().getUser()==null){
			jlJoker5050.setText("Total of jokers \"50 - 50\" used: ");
		}
		else
		{
			jlJoker5050.setText("Total of jokers \"50 - 50\" used: "+CurrentUser.getInstance().getUser().getNbJoker5050());
		}
		return jlJoker5050;
	}

	public JLabel getJlJokerSwitch() {
		if(jlJokerSwitch==null){
			jlJokerSwitch = new JLabel("Total of jokers \"Switch\" used: ");
		}
		if(CurrentUser.getInstance().getUser()==null){
			jlJokerSwitch.setText("Total of jokers \"Switch\" used: ");
		}
		else
		{
			jlJokerSwitch.setText("Total of jokers \"Switch\" used: "+CurrentUser.getInstance().getUser().getNbJokerSwitch());
		}
		return jlJokerSwitch;
	}

	public JLabel getJlCallFriend() {
		if(jlCallFriend==null){
			jlCallFriend = new JLabel("Total of jokers \"Call a friend\" used: ");
		}
		if(CurrentUser.getInstance().getUser()==null){
			jlCallFriend.setText("Total of jokers \"Call a friend\" used: ");
		}
		else
		{
			jlCallFriend.setText("Total of jokers \"Call a friend\" used: "+CurrentUser.getInstance().getUser().getNbJokerCall());
		}
		return jlCallFriend;
	}



	public JPanel getJpLeft() {
		if(jpLeft==null){
			jpLeft = new JPanel(new GridLayout(15,1,50,10));

			jpLeft.add(getJlPseudo());
			jpLeft.add(getJlNom());
			jpLeft.add(getJlFirstName());
			jpLeft.add(getJlBirthDate());
			jpLeft.add(getJlMail());

			jpLeft.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		}
		return jpLeft;
	}



	public JPanel getJpRight() {
		if(jpRight==null){
			jpRight = new JPanel(new GridLayout(11,1,50,10));
			jpRight.add(getJlGameplayed());
			jpRight.add(getJlNbCorrectAnswer());
			jpRight.add(getJlMoyGain());
			jpRight.add(getJlTotalGain());
			jpRight.add(getJlNbGiveUp());
			jpRight.add(getJlVictory());
			jpRight.add(getJlLoose());
			jpRight.add(getJlNbJoker());
			jpRight.add(getJlJoker5050());
			jpRight.add(getJlJokerSwitch());
			jpRight.add(getJlCallFriend());

			jpRight.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		}
		return jpRight;
	}



	/**
	 * allow to back to the PanelMenuGame
	 * @return
	 */
	public JButton getBackMenu() {
		if(backMenu==null){
			backMenu = new JButton("Menu");
			backMenu.setPreferredSize(new Dimension(120,25));
			backMenu.setBackground(ColorProject.COLOR_BLUE.getCol());
			backMenu.addMouseListener(new MouseListener() {

				@Override
				public void mouseReleased(MouseEvent e) {
				}

				@Override
				public void mousePressed(MouseEvent e) {
				}

				@Override
				public void mouseExited(MouseEvent e) {
					backMenu.setBackground(ColorProject.COLOR_BLUE.getCol());
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					backMenu.setBackground(ColorProject.COLOR_BLUE.getCol().brighter());
				}

				@Override
				public void mouseClicked(MouseEvent e) {
				}
			});
			backMenu.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					JFrameproject05 fen = (JFrameproject05)SwingUtilities.getAncestorOfClass(JFrameproject05.class, PanelProfil.this);
					fen.getjPanelCartes().getCl().show(fen.getjPanelCartes(), "Menu");

				}
			});
		}

		return backMenu;
	}



	@Override
	public void actualise() {
		this.getJlPseudo();
		this.getJlNom();
		this.getJlFirstName();
		this.getJlBirthDate();
		this.getJlMail();
		this.getJlGameplayed();
		this.getJlNbCorrectAnswer();
		this.getJlMoyGain();
		this.getJlTotalGain();
		this.getJlNbGiveUp();
		this.getJlVictory();
		this.getJlLoose();
		this.getJlNbJoker();
		this.getJlJoker5050();
		this.getJlJokerSwitch();
		this.getJlCallFriend();
	}



}
