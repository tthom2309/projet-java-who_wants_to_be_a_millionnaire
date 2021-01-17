package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import model.ColorProject;
import model.RankTableAdapter;

/**
 * This class create the JPanels that shows the rank of each user.
 * 
 * @author Pierre Mayeur, Tratskas Thomas, Logeot Gautier, Delatte Mélanie
 *
 */
public class PanelRanking extends JPanel {

	private PanelRankNorth panelRankNorth;
	private PanelRankCenter panelRankCenter;
	private PanelRankSouth panelRankSouth;
	
	public PanelRanking(){
		this.setLayout(new BorderLayout());
		this.add(getPanelRankNorth(),BorderLayout.NORTH);
		this.add(getPanelRankCenter(),BorderLayout.CENTER);
		this.add(getPanelRankSouth(),BorderLayout.SOUTH);
		this.add(new JPanel(), BorderLayout.WEST);
		this.add(new JPanel(), BorderLayout.EAST);
	}

	/**
	 * This method initializes and returns the PanelRankNorth.
	 * @return PanelRankNorth
	 */
	public PanelRankNorth getPanelRankNorth() {
		if(panelRankNorth==null){
			panelRankNorth = new PanelRankNorth();
		}
		return panelRankNorth;
	}

	/**
	 * This method initializes and returns the PanelRankSouth.
	 * @return PanelRankCenter
	 */
	public PanelRankCenter getPanelRankCenter() {
		if(panelRankCenter==null){
			panelRankCenter = new PanelRankCenter();
		}
		return panelRankCenter;
	}

	/**
	 * This method initializes and returns the PanelRankSouth
	 * @return
	 */
	public PanelRankSouth getPanelRankSouth() {
		if(panelRankSouth==null){
			panelRankSouth = new PanelRankSouth();
		}
		return panelRankSouth;
	}
	
	
	
}

/**
 * This class generates the jPanel that allows to change the ranking type.
 * 
 * @author Pierre Mayeur, Tratskas Thomas, Logeot Gautier, Delatte Mélanie
 *
 */
class PanelRankNorth extends JPanel{
	private JLabel jlTypeRanking;
	private JComboBox<String> jcTypeRanking;
	
	public PanelRankNorth(){
		this.setLayout(new GridLayout(1,4,10,0));
		this.add(getJlTypeRanking());
		this.add(getJcTypeRanking());
	}

	/**
	 * This method initializes and returns the JLabel jlTypeRanking
	 * @return JLabel
	 */
	public JLabel getJlTypeRanking() {
		if(jlTypeRanking==null){
			jlTypeRanking = new JLabel("Type of ranking: ");
		}
		return jlTypeRanking;
	}

	/**
	 * This method initializes and returns the JComboBox that  allows to choose the type of ranking to show
	 * @return JComboBox<String>
	 */
	public JComboBox<String> getJcTypeRanking() {
		if(jcTypeRanking==null){
			String[] content = {"Ranking total gain","Ranking max gain"};
			jcTypeRanking = new JComboBox<String>(content);
			jcTypeRanking.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {

					PanelRanking panelR = (PanelRanking) SwingUtilities.getAncestorOfClass(PanelRanking.class, PanelRankNorth.this);
	
					String selection = (String) panelR.getPanelRankNorth().getJcTypeRanking().getSelectedItem();
					if(selection.equals("Ranking total gain")){
						panelR.getPanelRankCenter().getCl().show(panelR.getPanelRankCenter(), "total"); 
					}
					else if(selection.equals("Ranking max gain")){
						panelR.getPanelRankCenter().getCl().show(panelR.getPanelRankCenter(), "max");
					}	
				}
			});
		}
		return jcTypeRanking;
	}
	
	
}

/**
 * This class generates the Panel that shows the different types of ranking with a JTable.
 * 
 * @author Pierre Mayeur, Tratskas Thomas, Logeot Gautier, Delatte Mélanie
 *
 */
class PanelRankCenter extends JPanel{
	
	private JTable jtRankTotal;
	private JTable jtRankMax;
	private CardLayout cl;
	
	public PanelRankCenter(){
		this.setLayout(getCl());
		this.add(new JScrollPane(getJtRankTotal()),"total");
		this.add(new JScrollPane(getJtRankMax()),"max");
	}

	/**
	 * This method initializes and returns the Cardlayout that change the JPanel with the rank to show.
	 * @return CardLayout
	 */
	public CardLayout getCl() {
		if(cl==null){
			cl = new CardLayout();
		}
		return cl;
	}

	/**
	 * This method initializes and returns the Jtable with the users sort by the total gain.
	 * @return JTable
	 */
	public JTable getJtRankTotal() {
		if(jtRankTotal==null){
			jtRankTotal = new JTable(new ModelTableRank<RankTableAdapter>(new ModelTableAdapterTotal()));
			jtRankTotal.setAutoCreateRowSorter(true);
		}
		return jtRankTotal;
	}

	/**
	 * This method initializes and returns the Jtable with the users sort by the maximum gain.
	 * @return JTable
	 */
	public JTable getJtRankMax() {
		if(jtRankMax==null){
			jtRankMax = new JTable(new ModelTableRank<RankTableAdapter>(new ModelTableAdapterMax()));
			jtRankMax.setAutoCreateRowSorter(true);
		}
		return jtRankMax;
	}
	
	
}

/**
 * This class generates the panel with the button that allows to get back to the main menu.
 * 
 * @author Pierre Mayeur, Tratskas Thomas, Logeot Gautier, Delatte Mélanie
 *
 */
class PanelRankSouth extends JPanel{
	
	private JButton jbMenu;
	
	public PanelRankSouth(){
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.add(getJbMenu());
	}

	/**
	 * This method initializes and returns the button that allows to get back the main menu.
	 * @return JButton
	 */
	public JButton getJbMenu() {
		if(jbMenu==null){
			jbMenu = new JButton("Menu");
			jbMenu.setPreferredSize(new Dimension(120,25));
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
					JFrameproject05 fen = (JFrameproject05)SwingUtilities.getAncestorOfClass(JFrameproject05.class, PanelRankSouth.this);
					fen.getjPanelCartes().getCl().show(fen.getjPanelCartes(), "Menu");
				}
			});
		}
		return jbMenu;
	}
	
	
}