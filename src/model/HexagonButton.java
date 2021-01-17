package model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Polygon;
import java.awt.Toolkit;

import javax.swing.JButton;

/**
 * This class creates new JButton with a new shape, color and size.
 * 
 * @author Pierre Mayeur, Tratskas Thomas, Logeot Gautier, Delatte Mélanie
 *
 */
public class HexagonButton extends JButton{

	private Polygon poly;
	private String name;

	public HexagonButton(String name){
		super(name);
		this.name=name;
		
		setBorderPainted(false);
	    setFocusPainted(false);
	    setContentAreaFilled(false);

	    //on génére le polygone qui sera la nouvelle  forme du boutton
		poly = new Polygon();
		int x=0;
		int y=0;
		
		//Point 1
		x = 600;
		y = 40;
		poly.addPoint(x,y);
		
		//Point2
		x = 550;
		y = 0;
		poly.addPoint(x,y);
		
		//Point 3
		x = 50;
		poly.addPoint(x,y);
		
		//Point 4
		x = 0;
		y = 40;
		poly.addPoint(x,y);
		
		//Point 5
		x = 50;
		y = 80;
		poly.addPoint(x,y);
		
		//Point 6
		x = 550;
		poly.addPoint(x,y);
		
		setPreferredSize(poly.getBounds().getSize());
	}

	
	@Override
	public void paintComponent(java.awt.Graphics g) {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension dim = new Dimension(toolkit.getScreenSize().width,toolkit.getScreenSize().height);
		//on choisit la couleur du bouton
		g.setColor(ColorProject.COLOR_BLUE.getCol().brighter());
		//on crée le polygone du bouton
		g.fillPolygon(poly);
		//on choisit la couleur de la police d'écriture
		g.setColor(Color.BLACK);
		//on écrit dans le bouton
		g.drawString(name, 260, 50 + (int)(50.0 * Math.sin(0 * (double) 2.0 * Math.PI / 6.0)));
		//on sélectionne la couleur des bords
		g.setColor(ColorProject.COLOR_GREY.getCol());
		//on dessine les bords du bouton
		g.drawPolygon(poly);
	}
}
