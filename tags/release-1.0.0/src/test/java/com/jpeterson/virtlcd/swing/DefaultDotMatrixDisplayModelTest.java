package com.jpeterson.virtlcd.swing;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class DefaultDotMatrixDisplayModelTest extends TestCase {
	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public DefaultDotMatrixDisplayModelTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(DefaultDotMatrixDisplayModelTest.class);
	}

	/**
	 * Test getting/setting the character.
	 */
	public void test_char() {
		DotMatrixDisplayModel model = new DefaultDotMatrixDisplayModel();

		assertEquals("Unexpected text", "", model.getText());
		model.setText("Unit test");
		assertEquals("Unexpected char", "Unit test", model.getText());
	}

	/**
	 * Test adding/removing change listener.
	 */
	public void test_changeListener() {
		DefaultDotMatrixDisplayModel model = new DefaultDotMatrixDisplayModel();
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
