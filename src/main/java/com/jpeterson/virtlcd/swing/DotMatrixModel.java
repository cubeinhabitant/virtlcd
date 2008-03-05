package com.jpeterson.virtlcd.swing;

import javax.swing.event.ChangeListener;

/**
 * This is the model in the MVC pattern for the
 * <code>JDotMatrixSixByEight</code> character.
 * 
 * @author Jesse Peterson
 */
public interface DotMatrixModel {

	/**
	 * The character being displayed.
	 * 
	 * @return The character being displayed.
	 */
	public char getChar();

	/**
	 * Set the character to display.
	 * 
	 * @param c
	 *            the character to display.
	 */
	public void setChar(char c);

	/**
	 * Adds a ChangeListener to the model's listener list.
	 * 
	 * @param l
	 *            the ChangeListener to add
	 * @see #removeChangeListener
	 */
	void addChangeListener(ChangeListener l);

	/**
	 * Removes a ChangeListener from the model's listener list.
	 * 
	 * @param l
	 *            the ChangeListener to remove
	 * @see #addChangeListener
	 */
	void removeChangeListener(ChangeListener l);
}
