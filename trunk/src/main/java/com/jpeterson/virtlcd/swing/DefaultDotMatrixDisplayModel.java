package com.jpeterson.virtlcd.swing;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.EventListenerList;

/**
 * Default implementation of the <code>DotMatrixDisplayModel</code>.
 * 
 * @author Jesse Peterson
 */
public class DefaultDotMatrixDisplayModel implements DotMatrixDisplayModel {

	protected EventListenerList listenerList = new EventListenerList();

	private String text = "";

	public String getText() {
		return text;
	}

	public void setText(String text) {
		if (!this.text.equals(text)) {
			this.text = text;
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
