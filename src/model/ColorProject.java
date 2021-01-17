package model;

import java.awt.Color;

/**
 * This enum is used for the custom color of the application.
 * 
 * @author Tratskas Thomas, Delatte Mélanie, Logeot Gautier, Mayeur Pierre
 *
 */
public enum ColorProject {

	COLOR_BLUE(new Color(45,153,196)),
	COLOR_GREY(new Color(105,97,88));

	private Color col;
	
	ColorProject(Color color){
		this.col=color;
	}

	/**
	 * This method return the color of the enumeration.
	 * @return Color
	 */
	public Color getCol() {
		return col;
	}
	
	
}
