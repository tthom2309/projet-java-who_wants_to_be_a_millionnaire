package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import utility.Observateur;
import model.CurrentUser;
import model.Game;
import model.JokerButton;
import model.Round;
/**
 * This class allows to display a frame that gives you the information after you won or lost the game
 * @author Pierre Mayeur, Tratskas Thomas, Logeot Gautier, Delatte Mélanie
 *
 */
public class PanelEndGame extends JPanel implements Observateur{

	private JLabel jlResult;
	private JButton jbContinue;
	private JLabel wheathley;

	public PanelEndGame(){
		BorderLayout bd = new BorderLayout();
		this.setLayout(bd);
		Game.getInstance().add(this);
		this.add(getWheathley(),BorderLayout.NORTH);
		this.add(new JPanel(),BorderLayout.WEST);
		this.add(new JPanel(),BorderLayout.EAST);
		this.add(getJlResult(),BorderLayout.CENTER);
		this.add(getJbContinue(),BorderLayout.SOUTH);
	}

	/**
	 * 
	 * @return a jlabel containing a picture
	 */
	public JLabel getWheathley() {
		if(wheathley == null){
			Image img = Toolkit.getDefaultToolkit().getImage("img/wheathley.png");
			wheathley = new JLabel(new ImageIcon(img));
			
		}
		return wheathley;
	}
	
	/**
	 * 
	 * @return a jlabel with the results of the game
	 */
	public JLabel getJlResult() {
		if(jlResult==null){
			jlResult = new JLabel();
			jlResult.setVerticalAlignment(SwingConstants.CENTER);
			
		}
		return jlResult;
	}

	/**
	 * this method actualize the information of the panelquestionview when the user answers
	 * if the answer is wrong then the user goes on the panelendgame
	 * if not the user can continue his game
	 */
	@Override
	public void actualise() {
		if(Game.getInstance().isLoose()==true){
			if(Game.getInstance().getPalier()==Round.FIRST_ROUND){
				jlResult.setText("Ooooooooooooooooh ... It's a Game over !  You win 0 €.");
			}
			if(Game.getInstance().getPalier()==Round.SECOND_ROUND){
				jlResult.setText("Ooooooooooooooooh ... It's a Game over !  You win "+Game.getInstance().getValueGain(4)+" €.");
				
				if(!CurrentUser.getInstance().getUser().getPseudo().equals("guest")){
					if(CurrentUser.getInstance().getUser().getMaxGain()<Game.getInstance().getValueGain(4)){
						CurrentUser.getInstance().getUser().setMaxGain(Game.getInstance().getValueGain(4));
					}
				}
				
			}
			if(Game.getInstance().getPalier()==Round.LAST_ROUND){
				jlResult.setText("Ooooooooooooooooh ... It's a Game over !  You win "+Game.getInstance().getValueGain(9)+" €.");
				
				if(!CurrentUser.getInstance().getUser().getPseudo().equals("guest")){
					if(CurrentUser.getInstance().getUser().getMaxGain()<Game.getInstance().getValueGain(9)){
						CurrentUser.getInstance().getUser().setMaxGain(Game.getInstance().getValueGain(9));
					}
				}
				
			}
		}
		else
		{
			if(Game.getInstance().getValueGain(Game.getInstance().getTour()-2)==1000000){
				jlResult.setText("This was a triumph ! I'm making a note ... Here ... \"HUGE SUCCESS\" ! You win "+Game.getInstance().getValueGain(Game.getInstance().getTour()-2)+" €.");
			}
			else{
				jlResult.setText("You filthy casual coward .... There's your gains ... I don't know if you deserve it .. "+Game.getInstance().getValueGain(Game.getInstance().getTour()-2)+" €.");
			}
			try{
				if(!CurrentUser.getInstance().getUser().getPseudo().equals("guest") ){
					if(CurrentUser.getInstance().getUser().getMaxGain()<Game.getInstance().getValueGain(Game.getInstance().getTour()-2)){
						CurrentUser.getInstance().getUser().setMaxGain(Game.getInstance().getValueGain(Game.getInstance().getTour()-2));
					}
				}
			}
			catch(NullPointerException e1){
				
			}
		}
		jlResult.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		
	}

	/**
	 * If the user click on this button, he goes on the panelmenugame
	 * @return a jbutton with an actionperformed
	 */
	public JButton getJbContinue() {
		if(jbContinue==null){
			jbContinue = new JButton("Continue");
			Dimension dim = new Dimension(125,25);
			jbContinue.setPreferredSize(dim);
			jbContinue.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					JFrameproject05 fen = (JFrameproject05)SwingUtilities.getAncestorOfClass(JFrameproject05.class, PanelEndGame.this);
					CurrentUser.getInstance().notifyO();
					fen.getjPanelCartes().getCl().show(fen.getjPanelCartes(), "Menu");
					
				}
			});
		}
		return jbContinue;
	}
	
	
	
}
