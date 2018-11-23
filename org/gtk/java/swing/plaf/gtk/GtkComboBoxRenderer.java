package org.gtk.java.swing.plaf.gtk;

/**
 * A renderer for combo box with gtk look and feel
 *
 * @version 1.8 08/28/98
 * @author Arnaud Weber
 */

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;

import java.awt.Component;
import java.awt.Color;

import java.io.Serializable;


public class GtkComboBoxRenderer extends JLabel 
    implements ListCellRenderer, Serializable
{
    protected static Border noFocusBorder = new EmptyBorder(1, 1, 1, 1);

    public GtkComboBoxRenderer() {
	super();
	setOpaque(true);
	setBorder(noFocusBorder);
    }


    public Component getListCellRendererComponent(
        JList list, 
	Object value,
        int index, 
        boolean isSelected, 
        boolean cellHasFocus) 
    {
	Color lightShadow = UIManager.getColor("lightShadow");
	setHorizontalAlignment(SwingConstants.LEFT);
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        }
	else {
	    //            setBackground(list.getBackground());
	    setBackground(lightShadow);
            setForeground(list.getForeground());
        }
	
	if (value instanceof Icon) {
	    setIcon((Icon)value);
	}
	else {
	    setText((value == null) ? "" : value.toString());
	}
	return this;
    }


    /**
     * A subclass of GtkComboBoxRenderer that implements UIResource.
     * GtkComboBoxRenderer doesn't implement UIResource
     * directly so that applications can safely override the
     * cellRenderer property with GtkListCellRenderer subclasses.
     * <p>
     * <strong>Warning:</strong>
     * Serialized objects of this class will not be compatible with
     * future Swing releases.  The current serialization support is appropriate
     * for short term storage or RMI between applications running the same
     * version of Swing.  A future release of Swing will provide support for
     * long term persistence.
     */
    public static class UIResource extends GtkComboBoxRenderer 
        implements javax.swing.plaf.UIResource
    {
    }

}
