package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import utility.Observateur;
import model.ColorProject;
import model.CurrentUser;
import model.Deck;
import model.Game;
import model.JokerButton;
import model.Question;
import model.SaveManage;

/**
 * This class creates the JPanel that allows to use the jokers and to give up.
 * 
 * @author Pierre Mayeur, Tratskas Thomas, Logeot Gautier, Delatte Mélanie
 *
 */
class PanelWest extends JPanel implements Observateur{
	private JButton jbjoker5050;
	private JButton jbjokerSwitch;
	private JButton jbjokerCallFriend;
	private JButton jbGiveUp;
	private JPanel jpJoker;
	private JPanel jpGiveUp;

	PanelWest(){
		Game.getInstance().add(this);
		GroupLayout gl = new GroupLayout(this);
		this.setLayout(gl);
		gl.setAutoCreateGaps(true);
		gl.setAutoCreateContainerGaps(true);
		
		gl.setVerticalGroup(gl.createSequentialGroup()
				.addComponent(getJpJoker())
				.addComponent(getJpGiveUp())
				);
		
		gl.setHorizontalGroup(gl.createParallelGroup()
				.addComponent(getJpJoker())
				.addComponent(getJpGiveUp())
				);
	}

	/**
	 * This method initializes and returns the button that allow to give up a game. 
	 * @return JButton
	 */
	public JButton getJbGiveUp() {
		if(jbGiveUp==null){
			Image img = Toolkit.getDefaultToolkit().getImage("img/giveup.jpg");

			jbGiveUp=new JokerButton(new ImageIcon(img));
			jbGiveUp.addActionListener(e->{ //expression lambda
				JFrameproject05 fen=(JFrameproject05)SwingUtilities.getAncestorOfClass(JFrameproject05.class, PanelWest.this);
				Game.getInstance().setOnGoing(false);
				if(!CurrentUser.getInstance().getUser().getPseudo().equals("guest")){
					CurrentUser.getInstance().getUser().setNbGiveUp(1);
				}
				if(Game.getInstance().isLoad()==true){
					SaveManage.getInstance().deleteSave(SaveManage.getInstance().getSave(CurrentUser.getInstance().getUser()));
				}
				fen.getjPanelCartes().getCl().show(fen.getjPanelCartes(), "EndGame");
			}
					);
		}
		return jbGiveUp;
	}

	/**
	 * This method initializes and returns the button that allows to use the "50:50 Joker".
	 * @return JButton
	 */
	public JButton getJbjoker5050() {
		if(jbjoker5050==null){
			//on charge l'image
			Image img = Toolkit.getDefaultToolkit().getImage("img/5050Actif.png");
			jbjoker5050 = new JokerButton(new ImageIcon(img));
			jbjoker5050.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					//si le joker est dispo
					if(Game.getInstance().isJoker5050()==true){
						//si le joueur  n'est pas un invité on met à jour ses données sur le joker 50:50
						if(!CurrentUser.getInstance().getUser().getPseudo().equals("guest")){
							CurrentUser.getInstance().getUser().setNbJokers(1);
							CurrentUser.getInstance().getUser().setNbJoker5050(1);
						}
						//on exécute le joker
						Game.getInstance().useJoker5050();
					}
				}
			});
		}
		//montre le joker si il est toujours disponible
		if(Game.getInstance().isJoker5050()==true){
			jbjoker5050.show();
		}
		//cache le joker si il est utilisé
		if(Game.getInstance().isJoker5050()==false){
			jbjoker5050.hide();
		}
		return jbjoker5050;
	}

	/**
	 * This method initializes and returns the button that allows to use the "Switch Joker".
	 * @return JButton
	 */
	public JButton getJbjokerSwitch() {
		if(jbjokerSwitch==null){
			//on charge l'image
			Image img = Toolkit.getDefaultToolkit().getImage("img/SwitchActif.png");
			jbjokerSwitch = new JokerButton(new ImageIcon(img));
			jbjokerSwitch.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					//si le joker est dispo
					if(Game.getInstance().isJokerSwitch()==true){
						//si le joueur  n'est pas un invité on met à jour ses données sur le joker switch
						if(!CurrentUser.getInstance().getUser().getPseudo().equals("guest")){
							CurrentUser.getInstance().getUser().setNbJokers(1);
							CurrentUser.getInstance().getUser().setNbJokerSwitch(1);
						}
						//on exécute le joker
						Game.getInstance().useJokerSwitch();
					}
				}
			});
		}
		//montre le joker si il est toujours disponible
		if(Game.getInstance().isJokerSwitch()==true){
			jbjokerSwitch.show();
		}
		//cache le joker si il est utilisé
		if(Game.getInstance().isJokerSwitch()==false){
			jbjokerSwitch.hide();
		}
		return jbjokerSwitch;
	}

	/**
	 * This method initializes and returns the button that allows to use the "Call a friend Joker".
	 * @return JButton
	 */
	public JButton getJbjokerCallFriend() {
		if(jbjokerCallFriend==null){
			//on charge l'image
			Image img = Toolkit.getDefaultToolkit().getImage("img/CallFriendActif.png");
			jbjokerCallFriend = new JokerButton(new ImageIcon(img));
			jbjokerCallFriend.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					//si le joker est dispo
					if(Game.getInstance().isJokerCallFriend()==true){
						//si le joueur  n'est pas un invité on met à jour ses données sur le joker call  a friend
						if(!CurrentUser.getInstance().getUser().getPseudo().equals("guest")){
							CurrentUser.getInstance().getUser().setNbJokers(1);
							CurrentUser.getInstance().getUser().setNbJokerCall(1);
						}
						//on exécute le joker
						Game.getInstance().useJokerCallFriend();
						JOptionPane.showMessageDialog(PanelWest.this,"Hello "+CurrentUser.getInstance().getUser().getNom()+"!\nI think the answer is ... "+Game.getInstance().getFriendAnswer());
					}
				}
			});
		}
		//montre le joker si il est toujours disponible
		if(Game.getInstance().isJokerCallFriend()==true){
			jbjokerCallFriend.show();
		}
		//cache le joker si il est utilisé
		if(Game.getInstance().isJokerCallFriend()==false){
			jbjokerCallFriend.hide();
		}
		return jbjokerCallFriend;
	}

	/**
	 * This method initializes and returns the panel that contains the jokers buttons.
	 * @return JPanel
	 */
	
	public JPanel getJpJoker() {
		if(jpJoker==null){
			jpJoker = new JPanel(new GridLayout(3, 1));
			jpJoker.add(getJbjoker5050());
			jpJoker.add(getJbjokerSwitch());
			jpJoker.add(getJbjokerCallFriend());
			this.setBorder(BorderFactory.createTitledBorder("Jokers"));
		}
		return jpJoker;
	}

	/**
	 * This method initializes and returns the panel that contains the bive up button.
	 * @return JPanel
	 */
	public JPanel getJpGiveUp() {
		if(jpGiveUp==null){
			jpGiveUp = new JPanel(new GridLayout(1, 1));
			jpGiveUp.add(getJbGiveUp());
			jpGiveUp.setBorder(BorderFactory.createTitledBorder("Give up"));
		}
		return jpGiveUp;
	}

	@Override
	public void actualise() {
		//pour permettre de cacher les jokers quand il sont utilisés
		getJbjoker5050();
		getJbjokerSwitch();
		getJbjokerCallFriend();
	}


}

/**
 * This class generate JPanel that contains the list of gains and shows to the user where he is on the game.
 * 
 * @author Pierre Mayeur, Tratskas Thomas, Logeot Gautier, Delatte Mélanie
 *
 */
class PanelEast extends JPanel implements Observateur{

	private JLabel jlPalier1;
	private JLabel jlPalier2;
	private JLabel jlPalier3;
	private JLabel jlPalier4;
	private JLabel jlPalier5;
	private JLabel jlPalier6;
	private JLabel jlPalier7;
	private JLabel jlPalier8;
	private JLabel jlPalier9;
	private JLabel jlPalier10;
	private JLabel jlPalier11;
	private JLabel jlPalier12;
	private JLabel jlPalier13;
	private JLabel jlPalier14;
	private JLabel jlPalier15;

	public PanelEast(){
		this.setLayout(new GridLayout(15, 1, 0, 10));
		this.add(getJlPalier15());
		this.add(getJlPalier14());
		this.add(getJlPalier13());
		this.add(getJlPalier12());
		this.add(getJlPalier11());
		this.add(getJlPalier10());
		this.add(getJlPalier9());
		this.add(getJlPalier8());
		this.add(getJlPalier7());
		this.add(getJlPalier6());
		this.add(getJlPalier5());
		this.add(getJlPalier4());
		this.add(getJlPalier3());
		this.add(getJlPalier2());
		this.add(getJlPalier1());
		Game.getInstance().add(this);
		this.setBorder(BorderFactory.createTitledBorder(getBorder(), "List of gain"));
	}

	/**
	 * This methods initializes and returns the label of the gain 1.
	 * @return JLabel
	 */
	public JLabel getJlPalier1() {
		if(jlPalier1==null){
			jlPalier1 = new JLabel("1. "+Game.getInstance().getValueGain(0) +" €");
			jlPalier1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			jlPalier1.setOpaque(true);
		}
		if(Game.getInstance().getTour()==1){
			jlPalier1.setBackground(ColorProject.COLOR_BLUE.getCol().brighter());
		}
		else{
			jlPalier1.setBackground(getBackground());
		}
		return jlPalier1;
	}

	/**
	 * This methods initializes and returns the label of the gain 2.
	 * @return JLabel
	 */
	public JLabel getJlPalier2() {
		if(jlPalier2==null){
			jlPalier2 = new JLabel("2. "+Game.getInstance().getValueGain(1) +" €");
			jlPalier2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			jlPalier2.setOpaque(true);	
		}
		if(Game.getInstance().getTour()==2){
			jlPalier2.setBackground(ColorProject.COLOR_BLUE.getCol().brighter());
		}
		else{
			jlPalier2.setBackground(getBackground());
		}
		return jlPalier2;
	}

	/**
	 * This methods initializes and returns the label of the gain 3.
	 * @return JLabel
	 */
	public JLabel getJlPalier3() {
		if(jlPalier3==null){
			jlPalier3 = new JLabel("3. "+Game.getInstance().getValueGain(2) +" €");
			jlPalier3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			jlPalier3.setOpaque(true);
		}
		if(Game.getInstance().getTour()==3){
			jlPalier3.setBackground(ColorProject.COLOR_BLUE.getCol().brighter());
		}
		else{
			jlPalier3.setBackground(getBackground());
		}
		return jlPalier3;
	}

	/**
	 * This methods initializes and returns the label of the gain 4.
	 * @return JLabel
	 */
	public JLabel getJlPalier4() {
		if(jlPalier4==null){
			jlPalier4 = new JLabel("4. "+Game.getInstance().getValueGain(3) +" €");
			jlPalier4.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			jlPalier4.setOpaque(true);
		}
		if(Game.getInstance().getTour()==4){
			jlPalier4.setBackground(ColorProject.COLOR_BLUE.getCol().brighter());
		}
		else{
			jlPalier4.setBackground(getBackground());
		}
		return jlPalier4;
	}

	/**
	 * This methods initializes and returns the label of the gain 5.
	 * @return JLabel
	 */
	public JLabel getJlPalier5() {
		if(jlPalier5==null){
			jlPalier5 = new JLabel("5. "+Game.getInstance().getValueGain(4) +"€");
			jlPalier5.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
			jlPalier5.setOpaque(true);
		}
		if(Game.getInstance().getTour()==5){
			jlPalier5.setBackground(ColorProject.COLOR_BLUE.getCol().brighter());
		}
		else{
			jlPalier5.setBackground(getBackground());
		}
		return jlPalier5;
	}

	/**
	 * This methods initializes and returns the label of the gain 6.
	 * @return JLabel
	 */
	public JLabel getJlPalier6() {
		if(jlPalier6==null){
			jlPalier6 = new JLabel("6. "+Game.getInstance().getValueGain(5) +"€");
			jlPalier6.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			jlPalier6.setOpaque(true);
		}
		if(Game.getInstance().getTour()==6){
			jlPalier6.setBackground(ColorProject.COLOR_BLUE.getCol().brighter());
		}
		else{
			jlPalier6.setBackground(getBackground());
		}
		return jlPalier6;
	}

	/**
	 * This methods initializes and returns the label of the gain 7.
	 * @return JLabel
	 */
	public JLabel getJlPalier7() {
		if(jlPalier7==null){
			jlPalier7 = new JLabel("7. "+Game.getInstance().getValueGain(6) +" €"); 
			jlPalier7.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			jlPalier7.setOpaque(true);
		}
		if(Game.getInstance().getTour()==7){
			jlPalier7.setBackground(ColorProject.COLOR_BLUE.getCol().brighter());
		}
		else{
			jlPalier7.setBackground(getBackground());
		}
		return jlPalier7;
	}

	/**
	 * This methods initializes and returns the label of the gain 8.
	 * @return JLabel
	 */
	public JLabel getJlPalier8() {
		if(jlPalier8==null){
			jlPalier8 = new JLabel("8. "+Game.getInstance().getValueGain(7) +" €");
			jlPalier8.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			jlPalier8.setOpaque(true);
		}
		if(Game.getInstance().getTour()==8){
			jlPalier8.setBackground(ColorProject.COLOR_BLUE.getCol().brighter());
		}
		else{
			jlPalier8.setBackground(getBackground());
		}
		return jlPalier8;
	}

	/**
	 * This methods initializes and returns the label of the gain 9.
	 * @return JLabel
	 */
	public JLabel getJlPalier9() {
		if(jlPalier9==null){
			jlPalier9 = new JLabel("9. "+Game.getInstance().getValueGain(8) +" €");
			jlPalier9.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			jlPalier9.setOpaque(true);
		}
		if(Game.getInstance().getTour()==9){
			jlPalier9.setBackground(ColorProject.COLOR_BLUE.getCol().brighter());
		}
		else{
			jlPalier9.setBackground(getBackground());
		}
		return jlPalier9;
	}

	/**
	 * This methods initializes and returns the label of the gain 10.
	 * @return JLabel
	 */
	public JLabel getJlPalier10() {
		if(jlPalier10==null){
			jlPalier10 = new JLabel("10. "+Game.getInstance().getValueGain(9) +" €");
			jlPalier10.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
			jlPalier10.setOpaque(true);
		}
		if(Game.getInstance().getTour()==10){
			jlPalier10.setBackground(ColorProject.COLOR_BLUE.getCol().brighter());
		}
		else{
			jlPalier10.setBackground(getBackground());
		}
		return jlPalier10;
	}

	/**
	 * This methods initializes and returns the label of the gain 11.
	 * @return JLabel
	 */
	public JLabel getJlPalier11() {
		if(jlPalier11==null){
			jlPalier11 = new JLabel("11. "+Game.getInstance().getValueGain(10) +" €");
			jlPalier11.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			jlPalier11.setOpaque(true);
		}
		if(Game.getInstance().getTour()==11){
			jlPalier11.setBackground(ColorProject.COLOR_BLUE.getCol().brighter());
		}
		else{
			jlPalier11.setBackground(getBackground());
		}
		return jlPalier11;
	}

	/**
	 * This methods initializes and returns the label of the gain 12.
	 * @return JLabel
	 */
	public JLabel getJlPalier12() {
		if(jlPalier12==null){
			jlPalier12 = new JLabel("12. "+Game.getInstance().getValueGain(11) +" €");
			jlPalier12.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			jlPalier12.setOpaque(true);
		}
		if(Game.getInstance().getTour()==12){
			jlPalier12.setBackground(ColorProject.COLOR_BLUE.getCol().brighter());
		}
		else{
			jlPalier12.setBackground(getBackground());
		}
		return jlPalier12;
	}

	/**
	 * This methods initializes and returns the label of the gain 13.
	 * @return JLabel
	 */
	public JLabel getJlPalier13() {
		if(jlPalier13==null){
			jlPalier13 = new JLabel("13. "+Game.getInstance().getValueGain(12) +" €");
			jlPalier13.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			jlPalier13.setOpaque(true);
		}
		if(Game.getInstance().getTour()==13){
			jlPalier13.setBackground(ColorProject.COLOR_BLUE.getCol().brighter());
		}
		else{
			jlPalier13.setBackground(getBackground());
		}
		return jlPalier13;
	}

	/**
	 * This methods initializes and returns the label of the gain 14.
	 * @return JLabel
	 */
	public JLabel getJlPalier14() {
		if(jlPalier14==null){
			jlPalier14 = new JLabel("14. "+Game.getInstance().getValueGain(13) +" €");
			jlPalier14.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			jlPalier14.setOpaque(true);
		}
		if(Game.getInstance().getTour()==14){
			jlPalier14.setBackground(ColorProject.COLOR_BLUE.getCol().brighter());
		}
		else{
			jlPalier14.setBackground(getBackground());
		}
		return jlPalier14;
	}

	/**
	 * This methods initializes and returns the label of the gain 15.
	 * @return JLabel
	 */
	public JLabel getJlPalier15() {
		if(jlPalier15==null){
			jlPalier15 = new JLabel("15. "+Game.getInstance().getValueGain(14) +" €");
			jlPalier15.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
			jlPalier15.setOpaque(true);
		}
		if(Game.getInstance().getTour()==15){
			jlPalier15.setBackground(ColorProject.COLOR_BLUE.getCol().brighter());
		}
		else{
			jlPalier15.setBackground(getBackground());
		}
		return jlPalier15;
	}

	public void actualise() {
		//pour actualiser la position du joueur
		getJlPalier1();
		getJlPalier2();
		getJlPalier3();
		getJlPalier4();
		getJlPalier5();
		getJlPalier6();
		getJlPalier7();
		getJlPalier8();
		getJlPalier9();
		getJlPalier10();
		getJlPalier11();
		getJlPalier12();
		getJlPalier13();
		getJlPalier14();
		getJlPalier15();
	}
}

/**
 * This class generates the panel that shows the questions and allows to choose the answer.
 * 
 * @author Pierre Mayeur, Tratskas Thomas, Logeot Gautier, Delatte Mélanie
 *
 */
class PanelSouth extends JPanel implements Observateur{

	private JLabel jlStatement;
	private JButton jbChoice1;
	private JButton jbChoice2;
	private JButton jbChoice3;
	private JButton jbChoice4;

	public PanelSouth(){
		GroupLayout gr = new GroupLayout(this);
		this.setLayout(gr);

		gr.setAutoCreateGaps(true);
		gr.setAutoCreateContainerGaps(true);

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension dim = new Dimension(toolkit.getScreenSize().width,toolkit.getScreenSize().height);

		gr.setVerticalGroup(gr.createSequentialGroup()
				.addComponent(getJlStatement(),80,80,80)
				.addGroup(gr.createParallelGroup()
						.addComponent(getJbChoice1())
						.addComponent(getJbChoice2())
						)
						.addGroup(gr.createParallelGroup()
								.addComponent(getJbChoice3())
								.addComponent(getJbChoice4())
								)
				);

		gr.setHorizontalGroup(gr.createParallelGroup()

				.addComponent(getJlStatement(),dim.width/100*99,dim.width/100*99,dim.width/100*99)
				.addGroup(gr.createSequentialGroup()
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,GroupLayout.DEFAULT_SIZE,dim.width/5)
						.addComponent(getJbChoice1(),dim.width/4,dim.width/4,dim.width/4)
						.addComponent(getJbChoice2(),dim.width/4,dim.width/4,dim.width/4)
						)
						.addGroup(gr.createSequentialGroup()
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,GroupLayout.DEFAULT_SIZE,dim.width/5)
								.addComponent(getJbChoice3(),dim.width/4,dim.width/4,dim.width/4)
								.addComponent(getJbChoice4(),dim.width/4,dim.width/4,dim.width/4)
								)
				);
		Game.getInstance().add(this);
	}
	//aleaQuestion est un nombre généré alétoirement de la méme manière que dans le mode console
	//ce qui permet actuellement d'afficher une question aléatoire sans prendre en compte le palier
	public JLabel getJlStatement() {
		if(jlStatement==null){
			jlStatement = new JLabel(Game.getInstance().getListQClone().get(Game.getInstance().getNumeroDeLaQuestion()).getStatement());
			//permet la cr€ation d'un label avec uniquement une question sans les propositions
			jlStatement.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
			//bord noir autour de la question
		}
		return jlStatement;
	}

	/**
	 * This method initializes and returns the button that allows to reply the answer 1.
	 * @return JButton
	 */
	public JButton getJbChoice1() {
		if(jbChoice1==null){
			//création d'un bouton avec la première proposition
			jbChoice1 = new JButton(Game.getInstance().getListQClone().get(Game.getInstance().getNumeroDeLaQuestion()).getOneChoices(1));
			//utilisé pour les 3 autres propositions (code trop semblable pour tout mettre)
			ActionListener actionChoice1=verif(Game.getInstance().getNumeroDeLaQuestion(), 1);
			jbChoice1.addActionListener(actionChoice1);
		}
		else{
			//on vide la liste des actions listeners pour mettre les données de la prochaine question
			ActionListener[] listeners = jbChoice1.getActionListeners();
			for (ActionListener l : listeners) 
			{
				jbChoice1.removeActionListener(l);
			}
			//on met l'actionlistener pour la question suivante
			jbChoice1.addActionListener(verif(Game.getInstance().getNumeroDeLaQuestion(), 1));
		}
		//pour rendre le bouton du choix visible
		if(Game.getInstance().isChoice1Visible()==true){
			jbChoice1.show();
		}
		//pour cacher le bouton lors de l'utilisation du joker 50:50
		if(Game.getInstance().isChoice1Visible()==false){
			jbChoice1.hide();
		}
		return jbChoice1;
	}

	/**
	 * This method initializes and returns the button that allows to reply the answer 2.
	 * @return JButton
	 */
	public JButton getJbChoice2() {
		if(jbChoice2==null){
			jbChoice2 = new JButton(Game.getInstance().getListQClone().get(Game.getInstance().getNumeroDeLaQuestion()).getOneChoices(2));
			jbChoice2.addActionListener(verif(Game.getInstance().getNumeroDeLaQuestion(), 2));
		}
		else{
			ActionListener[] listeners = jbChoice2.getActionListeners();
			for (ActionListener l : listeners) 
			{
				jbChoice2.removeActionListener(l);
			}
			jbChoice2.addActionListener(verif(Game.getInstance().getNumeroDeLaQuestion(), 2));
		}
		if(Game.getInstance().isChoice2Visible()==true){
			jbChoice2.show();
		}
		if(Game.getInstance().isChoice2Visible()==false){
			jbChoice2.hide();
		}
		return jbChoice2;
	}

	/**
	 * This method initializes and returns the button that allows to reply the answer 3.
	 * @return JButton
	 */
	public JButton getJbChoice3() {
		if(jbChoice3==null){
			jbChoice3 = new JButton(Game.getInstance().getListQClone().get(Game.getInstance().getNumeroDeLaQuestion()).getOneChoices(3));
			jbChoice3.addActionListener(verif(Game.getInstance().getNumeroDeLaQuestion(), 3));
		}
		else{
			ActionListener[] listeners = jbChoice3.getActionListeners();
			for (ActionListener l : listeners) 
			{
				jbChoice3.removeActionListener(l);
			}
			jbChoice3.addActionListener(verif(Game.getInstance().getNumeroDeLaQuestion(), 3));
		}
		if(Game.getInstance().isChoice3Visible()==true){
			jbChoice3.show();
		}
		if(Game.getInstance().isChoice3Visible()==false){
			jbChoice3.hide();
		}
		return jbChoice3;
	}

	/**
	 * This method initializes and returns the button that allows to reply the answer 4.
	 * @return JButton
	 */
	public JButton getJbChoice4() {
		if(jbChoice4==null){
			jbChoice4 = new JButton(Game.getInstance().getListQClone().get(Game.getInstance().getNumeroDeLaQuestion()).getOneChoices(4));
			jbChoice4.addActionListener(verif(Game.getInstance().getNumeroDeLaQuestion(), 4));
		}
		else{
			ActionListener[] listeners = jbChoice4.getActionListeners();
			for (ActionListener l : listeners) 
			{
				jbChoice4.removeActionListener(l);
			}
			jbChoice4.addActionListener(verif(Game.getInstance().getNumeroDeLaQuestion(), 4));
		}
		if(Game.getInstance().isChoice4Visible()==true){
			jbChoice4.show();
		}
		if(Game.getInstance().isChoice4Visible()==false){
			jbChoice4.hide();
		}
		return jbChoice4;
	}
	/**
	 * can check if button is click
	 * @param question
	 * @param numButton
	 * @return an actionListener
	 */
	public ActionListener verif(int question,int numButton){
		ActionListener verifClic=e->{
			JFrameproject05 fen = (JFrameproject05)SwingUtilities.getAncestorOfClass(JFrameproject05.class, PanelSouth.this);
			boolean tmp=false;
			tmp=Game.getInstance().getListQClone().get(question).getVerif(numButton);
			Object source = e.getSource();
			if(tmp==true){
				if(!CurrentUser.getInstance().getUser().getPseudo().equals("guest")){
					CurrentUser.getInstance().getUser().setNbCorrectAnswers(1);
				}
				
				if(Game.getInstance().getTour()==15){
					if(!CurrentUser.getInstance().getUser().getPseudo().equals("guest")){
						CurrentUser.getInstance().getUser().setNbWin(1);
						CurrentUser.getInstance().getUser().setTotalGain(1000000);
					}
					Game.getInstance().setOnGoing(false);
					if(Game.getInstance().isLoad()==true){
						SaveManage.getInstance().deleteSave(SaveManage.getInstance().getSave(CurrentUser.getInstance().getUser()));
					}
					fen.getjPanelCartes().getCl().show(fen.getjPanelCartes(), "EndGame");
				}
			}
			else{
				if(!CurrentUser.getInstance().getUser().getPseudo().equals("guest")){
					CurrentUser.getInstance().getUser().setNbLoose(1);
					CurrentUser.getInstance().getUser().setTotalGain(Game.getInstance().getValueGain(Game.getInstance().getTour()-2));
				}
				if(Game.getInstance().isLoad()==true){
					SaveManage.getInstance().deleteSave(SaveManage.getInstance().getSave(CurrentUser.getInstance().getUser()));
				}
				Game.getInstance().setLoose();
				Game.getInstance().setOnGoing(false);
				fen.getjPanelCartes().getCl().show(fen.getjPanelCartes(), "EndGame");
			}
			Game.getInstance().changerTour();
			((Component)source).setBackground(getBackground());
		};	
		return verifClic;
	}

	@Override
	public void actualise() {
		//permet de mettre à jour les questions et réponse.
		getJbChoice1();
		getJbChoice2();
		getJbChoice3();
		getJbChoice4();
		getJlStatement().setText(Game.getInstance().getListQClone().get(Game.getInstance().getNumeroDeLaQuestion()).getStatement());
		getJbChoice1().setText(Game.getInstance().getListQClone().get(Game.getInstance().getNumeroDeLaQuestion()).getOneChoices(1));
		getJbChoice2().setText(Game.getInstance().getListQClone().get(Game.getInstance().getNumeroDeLaQuestion()).getOneChoices(2));
		getJbChoice3().setText(Game.getInstance().getListQClone().get(Game.getInstance().getNumeroDeLaQuestion()).getOneChoices(3));
		getJbChoice4().setText(Game.getInstance().getListQClone().get(Game.getInstance().getNumeroDeLaQuestion()).getOneChoices(4));
	}
}

/**
 * This class generates the Panel with the Glados button image.
 * 
 * @author Pierre Mayeur, Tratskas Thomas, Logeot Gautier, Delatte Mélanie
 *
 */
class PanelCenter extends JPanel{
	
	private JokerButton glados;
	//liste des citations de Glados
	private String[] gladosAnswers={
			"Cake, and grief counseling, will be available at the conclusion of the test. ",
			"You are not a good person. You know that, right? Good people don't get up here. ",
			"Did you know you can donate one or all of your vital organs to the Aperture Science Self Esteem Fund for Girls? .... It's true!",
			"Your entire life has been a mathematical error... a mathematical error I'm about to correct! ",
			"It's been a long time. How have you been?",
			"For the record, you are adopted and that's terrible. Just work with me. ",
			"The birth parents you are trying to reach do not love you. Please hang up.",
			"I hate you so much. ",
			"hat's funny, I don't feel corrupt. In fact, I feel pretty good. ",
			"I had a pretty good life. And then you showed up. You dangerous, mute lunatic.",
			"You look ugly in that jumpsuit.",
			"They said on everyone else it looked fine, but on you, it looked hideous.",
			"You know, I'm not stupid.",
			"Did you know humans frown on weight variances? If you want to upset a human, just say their weight variance is above or below the norm. ",
			"You must love science almost as much as me.",
			"Be quiet!"
			
	};
	
	public PanelCenter(){
		this.add(getGlados());
	}
	
	/**
	 * This method initializes and returns the buttons that shows a random quote of Glados.
	 * @return JokerButton
	 */
	public JokerButton getGlados(){
		if(glados==null){
			//on crée le bouton image
			glados = new JokerButton(new ImageIcon("img/glados.png"));	
			glados.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					Random rand = new Random ();
					int n = rand.nextInt(gladosAnswers.length);
					//icone pour le fenêtre JOptionPane
					ImageIcon img = new ImageIcon("img/gladosIcone.png");
					JOptionPane.showMessageDialog(PanelCenter.this, gladosAnswers[n],"Glados's quote",JOptionPane.DEFAULT_OPTION,img);
				}
			});
		}
		ActionListener[] listeners = glados.getActionListeners();
		for (ActionListener l : listeners) 
		{
			glados.removeActionListener(l);
		}
		glados.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Random rand = new Random ();
				int n = rand.nextInt(gladosAnswers.length);
				ImageIcon img = new ImageIcon("img/gladosIcone.png");
				JOptionPane.showMessageDialog(PanelCenter.this, gladosAnswers[n],"Glados's quote",JOptionPane.DEFAULT_OPTION,img);
			}
		});
		return glados;
	}

}

/**
 * This class creates the JPanel that contains all the JPanel that allows to play a game.
 * 
 * @author Pierre Mayeur, Tratskas Thomas, Logeot Gautier, Delatte Mélanie
 *
 */
public class PanelQuestionView extends JPanel {

	private PanelWest panelWest;
	private PanelSouth panelSouth;
	private PanelEast panelEast;
	private PanelCenter panelCenter;
	

	public PanelQuestionView(){
		BorderLayout bd = new BorderLayout();
		this.setLayout(bd);
		this.add(getPanelWest(),BorderLayout.WEST);
		this.add(getPanelEast(),BorderLayout.EAST);
		this.add(getPanelCenter(),BorderLayout.CENTER);
		this.add(getPanelSouth(),BorderLayout.SOUTH);
	}

	/**
	 * This method initializes and returns the PanelWest.
	 * @return PanelWest
	 */
	public PanelWest getPanelWest() {
		if(panelWest==null){
			panelWest = new PanelWest();
		}
		return panelWest;
	}
	
	/**
	 * This method initializes and returns the PanelCenter.
	 * @return PanelCenter
	 */
	public PanelCenter getPanelCenter(){
		if(panelCenter == null){
			panelCenter = new PanelCenter();
		}
		return panelCenter;
	}

	/**
	 * This method initializes and returns the PanelSouth.
	 * @return PanelSouth
	 */
	public PanelSouth getPanelSouth() {
		if(panelSouth==null){
			panelSouth = new PanelSouth();
		}
		return panelSouth;
	}

	/**
	 * This method initializes and returns the PanelEast.
	 * @return PanelEast
	 */
	public PanelEast getPanelEast() {
		if(panelEast==null){
			panelEast = new PanelEast();
		}
		return panelEast;
	}


}