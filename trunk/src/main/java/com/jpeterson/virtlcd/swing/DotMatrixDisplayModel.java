package com.jpeterson.virtlcd.swing;

import javax.swing.event.ChangeListener;

/**
 * This is the model in the MVC pattern for the <code>JDotMatrixDisplay</code>
 * character.
 * 
 * @author Jesse Peterson
 */
public interface DotMatrixDisplayModel {
	/**
	 * Get the text displayed.
	 * 
	 * @return The text displayed.
	 */
	public String getText();

	/**
	 * Set the text to display.
	 * 
	 * @param text
	 *            The text displayed.
	 */
	public void setText(String text);

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
