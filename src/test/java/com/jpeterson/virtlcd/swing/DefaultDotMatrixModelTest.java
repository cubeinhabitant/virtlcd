package com.jpeterson.virtlcd.swing;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class DefaultDotMatrixModelTest extends TestCase {
	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public DefaultDotMatrixModelTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(DefaultDotMatrixModelTest.class);
	}

	/**
	 * Test getting/setting the character.
	 */
	public void test_char() {
		DotMatrixModel model = new DefaultDotMatrixModel();

		assertEquals("Unexpected char", ' ', model.getChar());
		model.setChar('q');
		assertEquals("Unexpected char", 'q', model.getChar());
	}

	/**
	 * Test adding/removing change listener.
	 */
	public void test_changeListener() {
		DefaultDotMatrixModel model = new DefaultDotMatrixModel();
		ChangeListener changeListener = new TestChangeListener();
		ChangeListener[] changeListeners;

		changeListeners = model.getChangeListeners();
		assertEquals("Unexpected number of change listeners", 0,
				changeListeners.length);

		model.addChangeListener(changeListener);

		changeListeners = model.getChangeListeners();
		assertEquals("Unexpected number of change listeners", 1,
				changeListeners.length);
		assertEquals("Unexpected change listener", changeListener,
				changeListeners[0]);

		model.removeChangeListener(changeListener);

		changeListeners = model.getChangeListeners();
		assertEquals("Unexpected number of change listeners", 0,
				changeListeners.length);
	}

	private class TestChangeListener implements ChangeListener {

		public void stateChanged(ChangeEvent e) {
		}

	}
}
