package com.jpeterson.virtlcd.swing;

import javax.swing.JComponent;
import javax.swing.UIManager;

import com.jpeterson.virtlcd.swing.ui.BasicDotMatrixSixByEightUI;
import com.jpeterson.virtlcd.swing.ui.DotMatrixSixByEightUI;

/**
 * Basic LCD character display. As the name suggests, the characters are
 * composed of six pixels across and eight pixels down.
 * 
 * @author Jesse Peterson
 * @see JDotMatrixDisplay
 */
public class JDotMatrixSixByEight extends JComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The UI class ID string.
	 */
	private static final String uiClassID = "DotMatrixSixByEightUI";

	protected DotMatrixModel model;

	/**
	 * Construct a new component that will display 1 character.
	 */
	public JDotMatrixSixByEight() {
		this.model = new DefaultDotMatrixModel();

		this.updateUI();
	}

	/**
	 * Sets the new UI delegate.
	 * 
	 * @param ui
	 *            New UI delegate.
	 */
	public void setUI(DotMatrixSixByEightUI ui) {
		super.setUI(ui);
	}

	/**
	 * Resets the UI property to a value from the current look and feel.
	 * 
	 * @see JComponent#updateUI
	 */
	public void updateUI() {
		if (UIManager.get(getUIClassID()) != null) {
			setUI((DotMatrixSixByEightUI) UIManager.getUI(this));
		} else {
			setUI(new BasicDotMatrixSixByEightUI());
		}
	}

	/**
	 * Returns the UI object which implements the L&F for this component.
	 * 
	 * @return UI object which implements the L&F for this component.
	 * @see #setUI
	 */
	public DotMatrixSixByEightUI getUI() {
		return (DotMatrixSixByEightUI) ui;
	}

	/**
	 * Returns the name of the UI class that implements the L&F for this
	 * component.
	 * 
	 * @return The name of the UI class that implements the L&F for this
	 *         component.
	 * @see JComponent#getUIClassID
	 * @see UIDefaults#getUI
	 */
	public String getUIClassID() {
		return uiClassID;
	}

	public DotMatrixModel getModel() {
		return this.model;
	}

	/**
	 * Get the character displayed.
	 * 
	 * @return The character displayed.
	 */
	public char getChar() {
		return model.getChar();
	}

	/**
	 * Set the character to display.
	 * 
	 * @param c
	 *            the character to display.
	 */
	public void setChar(char c) {
		char oldC = model.getChar();
		if (oldC != c) {
			model.setChar(c);
		}
	}
}
