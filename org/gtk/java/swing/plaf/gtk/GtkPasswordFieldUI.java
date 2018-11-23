package org.gtk.java.swing.plaf.gtk;

import javax.swing.*;
import javax.swing.text.*;
import javax.swing.plaf.*;
import javax.swing.plaf.basic.BasicPasswordFieldUI;

public class GtkPasswordFieldUI extends BasicPasswordFieldUI {

    /**
     * Creates a UI for a JPasswordField.
     *
     * @param c the JPasswordField
     * @return the UI
     */
    public static ComponentUI createUI(JComponent c) {
        return new GtkPasswordFieldUI();
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
