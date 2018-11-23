/*
 * @(#)GtkLabelUI.java	1.2 00/01/12
 *
 * Copyright 1997-2000 Sun Microsystems, Inc. All Rights Reserved.
 * 
 * This software is the proprietary information of Sun Microsystems, Inc.  
 * Use is subject to license terms.
 * 
 */

package org.gtk.java.swing.plaf.gtk;

import javax.swing.*;
import javax.swing.plaf.basic.BasicLabelUI;
import javax.swing.plaf.ComponentUI;

/**
 * A Gtk L&F implementation of LabelUI.
 * This merely sets up new default values in GtkLookAndFeel.
 * <p>
 * <strong>Warning:</strong>
 * Serialized objects of this class will not be compatible with
 * future Swing releases.  The current serialization support is appropriate
 * for short term storage or RMI between applications running the same
 * version of Swing.  A future release of Swing will provide support for
 * long term persistence.
 *
 * @version 1.7 08/28/98
 * @author Amy Fowler
 */
public class GtkLabelUI extends BasicLabelUI
{
    static GtkLabelUI sharedInstance = new GtkLabelUI();

    public static ComponentUI createUI(JComponent c) {
        return sharedInstance;
    }
}
