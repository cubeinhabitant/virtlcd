package com.jpeterson.virtlcd.swing;

import javax.swing.JComponent;
import javax.swing.UIDefaults;
import javax.swing.UIManager;

import com.jpeterson.virtlcd.swing.ui.BasicDotMatrixDisplayUI;
import com.jpeterson.virtlcd.swing.ui.DotMatrixDisplayUI;

/**
 * Simple component composed of multiple <a href="JDotMatrixSixByEight.html"><code>JDotMatrixSixByEight</code></a>
 * components arranged in a grid. You can specify the number of columns and rows
 * of <code>JDotMatrixSixByEight</code> components to include in the display.
 * 
 * @author Jesse Peterson
 * @see JDotMatrixSixByEight
 */
public class JDotMatrixDisplay extends JComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The UI class ID string.
	 */
	private static final String uiClassID = "DotMatrixDisplayUI";

	protected DotMatrixDisplayModel model;

	private int columns;

	private int rows;

	/**
	 * Create a new dot matrix display.
	 * 
	 * @param columns
	 *            Number of columns, or characters, per row.
	 * @param rows
	 *            Number of rows.
	 * @throws IllegalArgumentException
	 *             Invalid columns or rows specified.
	 */
	public JDotMatrixDisplay(int columns, int rows)
			throws IllegalArgumentException {
		this.model = new DefaultDotMatrixDisplayModel();

		setColumns(columns);
		setRows(rows);

		this.updateUI();
	}

	/**
	 * Get the number of columns, characters, in the display.
	 * 
	 * @return The number of columns.
	 */
	public int getColumns() {
		return columns;
	}

	/**
	 * Set the number of columns in the display.
	 * 
	 * @param columns
	 *            The number of columns in the display.
	 * @throws IllegalArgumentException
	 *             Invalid number of columns in the display.
	 */
	private void setColumns(int columns) throws IllegalArgumentException {
		if (columns <= 0) {
			throw new IllegalArgumentException(
					"Number of columns must be greater than zero");
		}

		this.columns = columns;
	}

	/**
	 * Get the number of rows in the display.
	 * 
	 * @return The number of rows.
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * Set the number of rows in the display.
	 * 
	 * @param rows
	 *            The number of rows in the display.
	 * @throws IllegalArgumentException
	 *             Invalid number of rows in the display.
	 */
	private void setRows(int rows) throws IllegalArgumentException {
		if (rows <= 0) {
			throw new IllegalArgumentException(
					"Number of rows must be greater than zero");
		}

		this.rows = rows;
	}

	/**
	 * Sets the new UI delegate.
	 * 
	 * @param ui
	 *            New UI delegate.
	 */
	public void setUI(DotMatrixDisplayUI ui) {
		super.setUI(ui);
	}

	/**
	 * Resets the UI property to a value from the current look and feel.
	 * 
	 * @see JComponent#updateUI
	 */
	public void updateUI() {
		if (UIManager.get(getUIClassID()) != null) {
			setUI((DotMatrixDisplayUI) UIManager.getUI(this));
		} else {
			setUI(new BasicDotMatrixDisplayUI());
		}
	}

	/**
	 * Returns the UI object which implements the L&F for this component.
	 * 
	 * @return UI object which implements the L&F for this component.
	 * @see #setUI
	 */
	public DotMatrixDisplayUI getUI() {
		return (DotMatrixDisplayUI) ui;
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

	public DotMatrixDisplayModel getModel() {
		return this.model;
	}

	/**
	 * Get the text displayed.
	 * 
	 * @return The text displayed.
	 */
	public String getText() {
		return model.getText();
	}

	/**
	 * Set the text to display.
	 * 
	 * @param text
	 *            The text displayed. Multiple line displays will most likely
	 *            break the string by a new line. Lines that are too long will
	 *            most likely be truncated. The actual display is controlled by
	 *            the UI component.
	 */
	public void setText(String text) {
		model.setText(text);
	}
}
