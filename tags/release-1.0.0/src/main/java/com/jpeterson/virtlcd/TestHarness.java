package com.jpeterson.virtlcd;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import com.jpeterson.virtlcd.swing.JDotMatrixDisplay;
import com.jpeterson.virtlcd.swing.JDotMatrixSixByEight;

/**
 * <p>
 * Example program that provides examples of using the provide Swing components
 * for displaying an LCD-like character display.
 * </p>
 * <p>
 * This application displays three tabs:
 * </p>
 * <ul>
 * <li>a single character:<br />
 * <img src="../../../../images/screenshot-virtlcd_Demo-Character_test.png"
 * alt="Character test screenshot" /></li>
 * <li>a multi-line display:<br />
 * <img src="../../../../images/screenshot-virtlcd_Demo-Display_test.png"
 * alt="Display test screenshot" /></li>
 * <li>a sample of all of the characters available:<br />
 * <img src="../../../../images/screenshot-virtlcd_Demo-Character_set.png"
 * alt="Character set screenshot" /></li>
 * </ul>
 * 
 * @author Jesse Peterson
 */
public class TestHarness extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTextField characterTestTextField;

	private JTextArea displayTestTestArea;

	private JDotMatrixSixByEight characterTestDotMatrixCharacter;

	private JDotMatrixDisplay displayTestDotMatrixDisplay;

	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

	public TestHarness() {
		super(new GridLayout(1, 1));

		JTabbedPane tabbedPane = new JTabbedPane();

		JComponent panel1 = makeCharacterTestPanel();
		tabbedPane.addTab("Character test", null, panel1,
				"Test individual characters");
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

		JComponent panel2 = makeDisplayTestPanel();
		tabbedPane.addTab("Display test", null, panel2, "Test display");
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

		JComponent panel3 = makeCharSetPanel();
		tabbedPane.addTab("Character set", null, panel3,
				"Display character set");
		tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

		// Add the tabbed pane to this panel.
		add(tabbedPane);

		// The following line enables to use scrolling tabs.
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
	}

	protected JComponent makeCharacterTestPanel() {
		JPanel panel = new JPanel(false);
		JLabel label = new JLabel("Character: ");
		JLabel displayLabel = new JLabel(" Display: ");

		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		characterTestTextField = new JTextField(1);
		characterTestTextField.getDocument().addDocumentListener(
				new CharacterDocumentListener());

		characterTestDotMatrixCharacter = new JDotMatrixSixByEight();
		characterTestDotMatrixCharacter.setBackground(Color.BLACK);
		characterTestDotMatrixCharacter.setForeground(Color.RED);

		panel.add(label);
		panel.add(characterTestTextField);
		panel.add(displayLabel);
		panel.add(characterTestDotMatrixCharacter);

		return panel;
	}

	protected JComponent makeDisplayTestPanel() {
		JPanel panel = new JPanel(false);
		JLabel label = new JLabel("Message: ");
		JLabel displayLabel = new JLabel(" Display: ");

		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		displayTestTestArea = new JTextArea(4, 20);
		displayTestTestArea.getDocument().addDocumentListener(
				new DisplayDocumentListener());

		displayTestDotMatrixDisplay = new JDotMatrixDisplay(20, 4);
		displayTestDotMatrixDisplay.setBackground(Color.BLACK);
		displayTestDotMatrixDisplay.setForeground(Color.RED);

		panel.add(label);
		panel.add(displayTestTestArea);
		panel.add(displayLabel);
		panel.add(displayTestDotMatrixDisplay);

		return panel;
	}

	protected JComponent makeCharSetPanel() {
		JPanel panel = new JPanel(true);

		// 17x17
		// panel.setLayout(new GridLayout(17, 17, 2, 2));
		panel.setLayout(new GridLayout(17, 17, 0, 2));

		// row 0
		panel.add(createHeader(""));
		panel.add(createHeader("0"));
		panel.add(createHeader("16"));
		panel.add(createHeader("32"));
		panel.add(createHeader("48"));
		panel.add(createHeader("64"));
		panel.add(createHeader("80"));
		panel.add(createHeader("96"));
		panel.add(createHeader("112"));
		panel.add(createHeader("128"));
		panel.add(createHeader("144"));
		panel.add(createHeader("160"));
		panel.add(createHeader("176"));
		panel.add(createHeader("192"));
		panel.add(createHeader("208"));
		panel.add(createHeader("224"));
		panel.add(createHeader("240"));

		for (int column = 0; column < 16; column++) {
			panel.add(createHeader(Integer.toString(column)));
			panel.add(createCharacter((char) column));
			panel.add(createCharacter((char) (16 + column)));
			panel.add(createCharacter((char) (32 + column)));
			panel.add(createCharacter((char) (48 + column)));
			panel.add(createCharacter((char) (64 + column)));
			panel.add(createCharacter((char) (80 + column)));
			panel.add(createCharacter((char) (96 + column)));
			panel.add(createCharacter((char) (112 + column)));
			panel.add(createCharacter((char) (128 + column)));
			panel.add(createCharacter((char) (144 + column)));
			panel.add(createCharacter((char) (160 + column)));
			panel.add(createCharacter((char) (176 + column)));
			panel.add(createCharacter((char) (192 + column)));
			panel.add(createCharacter((char) (208 + column)));
			panel.add(createCharacter((char) (224 + column)));
			panel.add(createCharacter((char) (240 + column)));
		}

		return panel;
	}

	protected JComponent createHeader(String s) {
		JLabel label = new JLabel(s);

		return label;
	}

	protected JDotMatrixSixByEight createCharacter(char c) {
		JDotMatrixSixByEight character = new JDotMatrixSixByEight();
		character.setForeground(Color.RED);
		character.setBackground(Color.BLACK);
		character.setChar(c);
		character.setToolTipText(Integer.toString((int) c));

		return character;
	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event-dispatching thread.
	 */
	private static void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("virtlcd Demo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Add content to the window.
		frame.add(new TestHarness());

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	private class CharacterDocumentListener implements DocumentListener {
		public CharacterDocumentListener() {

		}

		public void insertUpdate(DocumentEvent e) {
			updateText(e.getDocument());
		}

		public void removeUpdate(DocumentEvent e) {
			updateText(e.getDocument());
		}

		public void changedUpdate(DocumentEvent e) {
			updateText(e.getDocument());
		}

		protected void updateText(Document document) {
			String text;
			try {
				text = document.getText(0, document.getLength());
				if (text.length() > 0) {
					characterTestDotMatrixCharacter.setChar(text.charAt(0));
				} else {
					characterTestDotMatrixCharacter.setChar(' ');
				}
			} catch (BadLocationException e1) {
				e1.printStackTrace();
				characterTestDotMatrixCharacter.setChar(' ');
			}
		}
	}

	private class DisplayDocumentListener implements DocumentListener {
		public DisplayDocumentListener() {

		}

		public void insertUpdate(DocumentEvent e) {
			updateText(e.getDocument());
		}

		public void removeUpdate(DocumentEvent e) {
			updateText(e.getDocument());
		}

		public void changedUpdate(DocumentEvent e) {
			updateText(e.getDocument());
		}

		protected void updateText(Document document) {
			String text;
			try {
				text = document.getText(0, document.getLength());

				displayTestDotMatrixDisplay.setText(text);
			} catch (BadLocationException e1) {
				e1.printStackTrace();
				displayTestDotMatrixDisplay.setText("");
			}
		}
	}
}
