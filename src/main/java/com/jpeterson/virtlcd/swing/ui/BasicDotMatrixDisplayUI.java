package com.jpeterson.virtlcd.swing.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import javax.swing.JComponent;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.ComponentUI;

import com.jpeterson.virtlcd.swing.JDotMatrixDisplay;
import com.jpeterson.virtlcd.swing.JDotMatrixSixByEight;

/**
 * Implementation of the "view" for actually displaying the basic LCD characters
 * in a grid of columns and rows.
 * 
 * @author Jesse Peterson
 */
public class BasicDotMatrixDisplayUI extends DotMatrixDisplayUI {
	/**
	 * The associated dot matrix character.
	 */
	protected JDotMatrixDisplay dotMatrixDisplay;

	protected ChangeListener dotMatrixDisplayChangeListener;

	protected static final int VERTICAL_GAP = 2;

	protected JDotMatrixSixByEight dotMatrixCharacters[][];

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.plaf.ComponentUI#createUI(javax.swing.JComponent)
	 */
	public static ComponentUI createUI(JComponent c) {
		return new BasicDotMatrixDisplayUI();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.plaf.ComponentUI#installUI(javax.swing.JComponent)
	 */
	public void installUI(JComponent c) {
		this.dotMatrixDisplay = (JDotMatrixDisplay) c;
		installDefaults();
		installComponents();
		installListeners();

		c.setLayout(createLayoutManager());
		c.setBorder(new EmptyBorder(1, 1, 1, 1));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.plaf.ComponentUI#uninstallUI(javax.swing.JComponent)
	 */
	public void uninstallUI(JComponent c) {
		c.setLayout(null);
		uninstallListeners();
		uninstallComponents();
		uninstallDefaults();

		this.dotMatrixDisplay = null;
	}

	public void installDefaults() {

	}

	public void installComponents() {
		int rows = dotMatrixDisplay.getRows();
		int columns = dotMatrixDisplay.getColumns();

		dotMatrixCharacters = new JDotMatrixSixByEight[rows][columns];

		for (int row = 0; row < rows; row++) {
			for (int column = 0; column < columns; column++) {
				dotMatrixCharacters[row][column] = new JDotMatrixSixByEight();
				dotMatrixDisplay.add(dotMatrixCharacters[row][column]);
			}
		}
	}

	public void installListeners() {
		this.dotMatrixDisplayChangeListener = new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				processModelChange();

				dotMatrixDisplay.repaint();
			}
		};
		this.dotMatrixDisplay.getModel().addChangeListener(
				this.dotMatrixDisplayChangeListener);
	}

	public void uninstallDefaults() {
	}

	public void uninstallComponents() {

	}

	public void uninstallListeners() {
		this.dotMatrixDisplay.getModel().removeChangeListener(
				this.dotMatrixDisplayChangeListener);
		this.dotMatrixDisplayChangeListener = null;
	}

	@Override
	public void paint(Graphics g, JComponent c) {
		super.paint(g, c);

		// make sure the colors are correct
		Color color;
		int rows = dotMatrixDisplay.getRows();
		int columns = dotMatrixDisplay.getColumns();

		color = dotMatrixCharacters[0][0].getForeground();
		if (!color.equals(dotMatrixDisplay.getForeground())) {
			color = dotMatrixDisplay.getForeground();
			for (int row = 0; row < rows; row++) {
				for (int column = 0; column < columns; column++) {
					dotMatrixCharacters[row][column].setForeground(color);
				}
			}
		}

		color = dotMatrixCharacters[0][0].getBackground();
		if (!color.equals(dotMatrixDisplay.getBackground())) {
			color = dotMatrixDisplay.getBackground();
			for (int row = 0; row < rows; row++) {
				for (int column = 0; column < columns; column++) {
					dotMatrixCharacters[row][column].setBackground(color);
				}
			}
		}
	}

	/**
	 * Update the display with the text from the underlying model.
	 */
	void processModelChange() {
		String text = dotMatrixDisplay.getModel().getText();
		int columns = dotMatrixDisplay.getColumns();
		int rows = dotMatrixDisplay.getRows();
		char c;
		String line;
		int lineLength;
		int row = 0;

		// line 1
		// second line

		BufferedReader source = new BufferedReader(new StringReader(text));

		try {
			line = source.readLine();
			while ((line != null) && (row < rows)) {
				lineLength = line.length();
				for (int column = 0; column < columns; column++) {
					if (column < lineLength) {
						c = line.charAt(column);
						dotMatrixCharacters[row][column].setChar(c);
					} else {
						dotMatrixCharacters[row][column].setChar(' ');
					}
				}

				++row;
				line = source.readLine();
			}
			// clear out the rest
			while (row < rows) {
				for (int column = 0; column < columns; column++) {
					dotMatrixCharacters[row][column].setChar(' ');
				}

				++row;
			}
		} catch (IOException e) {
			// clear out the rest
			while (row < rows) {
				for (int column = 0; column < columns; column++) {
					dotMatrixCharacters[row][column].setChar(' ');
				}

				++row;
			}
		}
	}

	/**
	 * Invoked by <code>installUI</code> to create a layout manager object to
	 * manage the dot matrix display.
	 * 
	 * @return a layout manager object
	 */
	protected LayoutManager createLayoutManager() {
		GridLayout layoutManager;

		layoutManager = new GridLayout(dotMatrixDisplay.getRows(),
				dotMatrixDisplay.getColumns(), 0, VERTICAL_GAP);

		return layoutManager;
	}
}
