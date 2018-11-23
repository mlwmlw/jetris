/*
 * @(#)GtkTextFieldUI.java	1.2 00/01/12
 *
 * Copyright 1997-2000 Sun Microsystems, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Sun Microsystems, Inc.
 * Use is subject to license terms.
 *
 */
package org.gtk.java.swing.plaf.gtk;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTextFieldUI;
import javax.swing.plaf.*;
import javax.swing.text.Caret;

/**
 * Provides the Gtk look and feel for a text field.
 * <p>
 * <strong>Warning:</strong>
 * Serialized objects of this class will not be compatible with
 * future Swing releases.  The current serialization support is appropriate
 * for short term storage or RMI between applications running the same
 * version of Swing.  A future release of Swing will provide support for
 * long term persistence.
 *
 * @author  Timothy Prinzing
 * @version 1.18 08/28/98
 */

import java.awt.*;

public class GtkTextFieldUI extends BasicTextFieldUI {

    /**
     * Creates a UI for a JTextField.
     *
     * @param c the text field
     * @return the UI
     */
    public static ComponentUI createUI(JComponent c) {
        return new GtkTextFieldUI();
    }

    /**
     * Creates the object to use for a caret.  By default an
     * instance of GtkTextUI.GtkCaret is created.  This method
     * can be redefined to provide something else that implements
     * the Caret interface.
     *
     * @return the caret object
     */
    protected Caret createCaret() {
        return GtkTextUI.createCaret();
    }

    protected void paintBackground(Graphics g, JComponent b) {
	Dimension size = b.getSize();
        if (b.isEnabled()) {
	    g.setColor(Color.white);
	    
        }
	else {
	    g.setColor(new Color(10, 20, 30));
	}
	g.fillRect(0, 0, size.width, size.height);

    }

}
