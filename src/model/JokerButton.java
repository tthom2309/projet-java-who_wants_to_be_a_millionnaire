package model;

import java.awt.Dimension;

import javax.swing.Icon;
import javax.swing.JButton;

/**
 * This class allows to create a JButton with an image as background.
 * 
 * @author Pierre Mayeur, Tratskas Thomas, Logeot Gautier, Delatte Mélanie
 *
 */
public class JokerButton extends JButton{

	public JokerButton(Icon icon){
		super(icon);
		setBorderPainted(false);
	    setFocusPainted(false);
	    setContentAreaFilled(false);
	}
	
	 public boolean contains(int x, int y) {
		    Dimension size = getSize();
		    float x0 = size.width / 2F;
		    float y0 = size.height / 2F;
		 
		    Icon icon = getIcon();
		    float w = icon.getIconWidth() / 2F;
		    float h = icon.getIconHeight() / 2F;
		 
		    return (x - x0) * (x - x0) + (y - y0) * (y - y0) <= w * h;
		  }
}
