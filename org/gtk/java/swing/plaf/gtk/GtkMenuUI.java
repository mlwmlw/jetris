package org.gtk.java.swing.plaf.gtk;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.plaf.*;
import javax.swing.border.*;
import javax.swing.plaf.basic.*;


import javax.swing.plaf.basic.BasicMenuUI;
import javax.swing.plaf.basic.BasicGraphicsUtils;

public class GtkMenuUI extends BasicMenuUI
{

    public static ComponentUI createUI(JComponent x ) {
	return new GtkMenuUI();
    }

    protected void installDefaults() {
	super.installDefaults();
    }
   
 
    protected ChangeListener createChangeListener(JComponent c) {
 	return new GtkChangeHandler((JMenu) c, this);
    }

    public void paint(Graphics g, JComponent c){
	GtkGraphicsUtils.paintMenuItem(g, c, checkIcon, arrowIcon,
				       selectionBackground, selectionForeground,
				       defaultTextIconGap);
    }

    private boolean popupIsOpen (JMenu m,MenuElement me[]) {
        int i;
        JPopupMenu pm = m.getPopupMenu();

        for (i = me.length-1; i >= 0; i--) {
            if (me[i].getComponent() == pm)
                return true;
        }
        return false;
    }

    
    public class GtkChangeHandler extends ChangeHandler {

 	public GtkChangeHandler(JMenu m, GtkMenuUI ui) {
 	    super(m, ui);
 	}
	
	public void stateChanged(ChangeEvent e) {
	    JMenuItem c = (JMenuItem)e.getSource();
	    if (c.isArmed() || c.isSelected()) {
		c.setBorderPainted(true);
		// c.repaint();
	    }
	    else {
		c.setBorderPainted(false);
	    }

	    super.stateChanged(e);
	}
    }

}
