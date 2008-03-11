package com.jpeterson.virtlcd.swing;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.EventListenerList;

/**
 * Default implementation of the <code>DotMatrixModel</code>.
 * 
 * @author Jesse Peterson
 */
public class DefaultDotMatrixModel implements DotMatrixModel {

	/**
	 * The default character is a "space" character.
	 */
	public static final char DEFAULT_CHAR = ' ';

	private char c = DEFAULT_CHAR;

	protected EventListenerList listenerList = new EventListenerList();

	public char getChar() {
		return c;
	}

	public void setChar(char c) {
		if (this.c != c) {
			this.c = c;
			fireStateChanged();
		}
	}

	public void addChangeListener(ChangeListener l) {
		listenerList.add(ChangeListener.class, l);
	}

	public void removeChangeListener(ChangeListener l) {
		listenerList.remove(ChangeListener.class, l);
	}

	/**
	 * Runs each <code>ChangeListener</code>'s <code>stateChanged</code>
	 * method.
	 */
	protected void fireStateChanged() {
		ChangeEvent event = new ChangeEvent(this);
		Object[] listeners = listenerList.getListenerList();
		for (int i = listeners.length - 2; i >= 0; i -= 2) {
			if (listeners[i] == ChangeListener.class) {
				((ChangeListener) listeners[i + 1]).stateChanged(event);
			}
		}
	}

	/**
	 * Returns an array of all the change listeners registered on this
	 * <code>DefaultBoundedRangeModel</code>.
	 * 
	 * @return all of this model's <code>ChangeListener</code>s or an empty
	 *         array if no change listeners are currently registered
	 * 
	 * @see #addChangeListener
	 * @see #removeChangeListener
	 */
	public ChangeListener[] getChangeListeners() {
		return (ChangeListener[]) listenerList
				.getListeners(ChangeListener.class);
	}
}
