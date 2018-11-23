package org.gtk.java.swing.plaf.gtk;

import javax.swing.*;
import javax.swing.text.*;
import javax.swing.plaf.*;
import javax.swing.plaf.basic.BasicEditorPaneUI;

/**
 * @author  Timothy Prinzing
 * @version 1.9 08/28/98
 */

public class GtkEditorPaneUI extends BasicEditorPaneUI {

    /**
     * Creates a UI for the JTextPane.
     *
     * @param c the JTextPane component
     * @return the UI
     */
    public static ComponentUI createUI(JComponent c) {
        return new GtkEditorPaneUI();
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

}
