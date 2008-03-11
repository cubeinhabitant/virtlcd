package com.jpeterson.virtlcd.swing.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Point;

import javax.swing.JComponent;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.ComponentUI;

import com.jpeterson.virtlcd.swing.DotMatrixModel;
import com.jpeterson.virtlcd.swing.JDotMatrixSixByEight;

/**
 * Implementation of the "view" for actually displaying the basic LCD character.
 * 
 * @author Jesse Peterson
 */
public class BasicDotMatrixSixByEightUI extends DotMatrixSixByEightUI {
	/**
	 * The associated dot matrix character.
	 */
	protected JDotMatrixSixByEight dotMatrixSixByEight;

	protected ChangeListener dotMatrixSixByEightChangeListener;

	protected static final int PIXEL_WIDTH = 2;

	protected static final int PIXEL_HEIGHT = 2;

	protected static final int PIXEL_INNER_WIDTH = 1;

	protected static final int PIXEL_INNER_HEIGHT = 1;

	protected static final int NUM_PIXELS_WIDE = 6;

	protected static final int NUM_PIXELS_HIGH = 8;

	protected static final int WIDTH = (NUM_PIXELS_WIDE * PIXEL_WIDTH)
			+ ((NUM_PIXELS_WIDE - 1) * PIXEL_INNER_WIDTH);

	protected static final int HEIGHT = (NUM_PIXELS_HIGH * PIXEL_HEIGHT)
			+ ((NUM_PIXELS_HIGH - 1) * PIXEL_INNER_HEIGHT);

	protected static Point coords[][] = new Point[6][8];

	static {
		// initialize the x,y coordinates for each pixel in the 6x8 pixel array
		for (int i = 0; i < NUM_PIXELS_WIDE; i++) {
			for (int j = 0; j < NUM_PIXELS_HIGH; j++) {
				coords[i][j] = new Point();
				coords[i][j].x = (PIXEL_WIDTH * i) + (PIXEL_INNER_WIDTH * i);
				coords[i][j].y = (PIXEL_HEIGHT * j) + (PIXEL_INNER_HEIGHT * j);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.plaf.ComponentUI#createUI(javax.swing.JComponent)
	 */
	public static ComponentUI createUI(JComponent c) {
		return new BasicDotMatrixSixByEightUI();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.plaf.ComponentUI#installUI(javax.swing.JComponent)
	 */
	public void installUI(JComponent c) {
		this.dotMatrixSixByEight = (JDotMatrixSixByEight) c;
		installDefaults();
		installComponents();
		installListeners();

		c.setLayout(createLayoutManager());
		// c.setBorder(new EmptyBorder(1, 1, 1, 1));
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

		this.dotMatrixSixByEight = null;
	}

	public void installDefaults() {

	}

	public void installComponents() {
	}

	public void installListeners() {
		this.dotMatrixSixByEightChangeListener = new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				dotMatrixSixByEight.repaint();
			}
		};
		this.dotMatrixSixByEight.getModel().addChangeListener(
				this.dotMatrixSixByEightChangeListener);
	}

	public void uninstallDefaults() {
	}

	public void uninstallComponents() {

	}

	public void uninstallListeners() {
		this.dotMatrixSixByEight.getModel().removeChangeListener(
				this.dotMatrixSixByEightChangeListener);
		this.dotMatrixSixByEightChangeListener = null;
	}

	@Override
	public void paint(Graphics g, JComponent c) {
		super.paint(g, c);
		this.paintPixels(g);
	}

	protected void paintPixels(Graphics g) {
		DotMatrixModel model = dotMatrixSixByEight.getModel();
		char c = model.getChar(); // character to display
		Color origColor = g.getColor();

		// fill background
		g.setColor(dotMatrixSixByEight.getBackground());
		g.fillRect(0, 0, WIDTH, HEIGHT);

		// paint character
		g.setColor(dotMatrixSixByEight.getForeground());

		try {
			for (int i = 0; i < NUM_PIXELS_WIDE; i++) {
				for (int j = 0; j < NUM_PIXELS_HIGH; j++) {
					if (CharSet.BITMAP[c][i][j] == 1) {
						g.fillRect(coords[i][j].x, coords[i][j].y, PIXEL_WIDTH,
								PIXEL_HEIGHT);
					}
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			// no character in char set
			System.err.println("No character defined for (" + c
					+ ") decimal value [" + (int) c + "]");
		}

		g.setColor(origColor);
	}

	/**
	 * Invoked by <code>installUI</code> to create a layout manager object to
	 * manage the dot matrix display.
	 * 
	 * @return a layout manager object
	 */
	protected LayoutManager createLayoutManager() {
		return new DotMatrixSixByEightLayout();
	}

	/**
	 * Layout for the dot matrix display.
	 */
	protected class DotMatrixSixByEightLayout implements LayoutManager {
		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.LayoutManager#addLayoutComponent(java.lang.String,
		 *      java.awt.Component)
		 */
		public void addLayoutComponent(String name, Component c) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.LayoutManager#removeLayoutComponent(java.awt.Component)
		 */
		public void removeLayoutComponent(Component c) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.LayoutManager#preferredLayoutSize(java.awt.Container)
		 */
		public Dimension preferredLayoutSize(Container c) {
			int width = 0;
			int height = 0;
			Insets ins = c.getInsets();

			width = WIDTH;
			// added 1 because it looks like the bottom row of dot matrix pixels
			// has the bottom screen pixel chopped off
			height = HEIGHT + 1;

			return new Dimension(width + ins.left + ins.right, height + ins.top
					+ ins.bottom);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.LayoutManager#minimumLayoutSize(java.awt.Container)
		 */
		public Dimension minimumLayoutSize(Container c) {
			return this.preferredLayoutSize(c);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.LayoutManager#layoutContainer(java.awt.Container)
		 */
		public void layoutContainer(Container c) {
			// Insets ins = c.getInsets();
			// int width = c.getWidth();
			// int height = c.getHeight();

		}
	}
}
