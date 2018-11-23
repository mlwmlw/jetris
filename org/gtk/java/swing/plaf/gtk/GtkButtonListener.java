package org.gtk.java.swing.plaf.gtk;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.basic.*;
import javax.swing.event.*;


public class GtkButtonListener extends BasicButtonListener {
    public GtkButtonListener(AbstractButton b ) {
        super(b);
    }

    public void focusGained(FocusEvent e) { 
	AbstractButton b = (AbstractButton) e.getSource();
        if (b instanceof JButton && ((JButton)b).isDefaultCapable()) {
            // Only change the default button IF the root pane
            // containing this button has a default set.
            JRootPane root = SwingUtilities.getRootPane(b);
            if (root != null) {
                JButton current = root.getDefaultButton();
                if (current != null) {
                    root.setDefaultButton((JButton)b);
                }
            }
        }
	b.repaint();
    }
  
    // Here for rollover purposes
    public void mouseEntered(MouseEvent e) {
        AbstractButton button = (AbstractButton)e.getSource();
        button.getModel().setRollover(true);
    }
  
    // Here for rollover purposes
    public void mouseExited(MouseEvent e) {
        AbstractButton button = (AbstractButton)e.getSource();
        button.getModel().setRollover(false);
    }

    protected void checkOpacity(AbstractButton b) {
	b.setOpaque(false);
    }
}
