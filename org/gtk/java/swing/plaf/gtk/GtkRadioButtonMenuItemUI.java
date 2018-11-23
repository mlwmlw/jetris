package org.gtk.java.swing.plaf.gtk;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.plaf.*;
import javax.swing.plaf.basic.BasicRadioButtonMenuItemUI;
import javax.swing.plaf.basic.BasicGraphicsUtils;

import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;


/**
 * @version 1.36 08/28/98
 * @author Georges Saab
 * @author Rich Schiavi
 */
public class GtkRadioButtonMenuItemUI extends BasicRadioButtonMenuItemUI
{
    protected ChangeListener changeListener;

    public static ComponentUI createUI(JComponent b) {
        return new GtkRadioButtonMenuItemUI();
    }

    protected void installDefaults() {
	super.installDefaults();
	defaultTextIconGap = 8;
    }

    protected void installListeners() {
	super.installListeners();
        changeListener = createChangeListener(menuItem);
        menuItem.addChangeListener(changeListener);	
    }
    
    protected void uninstallListeners() {
	super.uninstallListeners();
	menuItem.removeChangeListener(changeListener);
    }

    protected ChangeListener createChangeListener(JComponent c) {
	return new ChangeHandler();
    }

    protected class ChangeHandler implements ChangeListener, Serializable {
	public void stateChanged(ChangeEvent e) {
	    JMenuItem c = (JMenuItem)e.getSource();
	    if (c.isArmed()) {
		c.setBorderPainted(true);
	    } else {
		c.setBorderPainted(false);
	    }
	}
    }

    public void paint(Graphics g, JComponent c) {
	GtkGraphicsUtils.paintMenuItem(g, c, 
				       checkIcon,
				       arrowIcon,
				       selectionBackground, 
				       selectionForeground,
				       defaultTextIconGap);
    }

}
