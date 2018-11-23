package org.gtk.java.swing.plaf.gtk;

import javax.swing.*;
import javax.swing.plaf.basic.BasicOptionPaneUI;
import javax.swing.plaf.ComponentUI;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;

/**
 * @version 1.12 08/28/98
 * @author Scott Violet
 */
public class GtkOptionPaneUI extends BasicOptionPaneUI
{
    /**
      * Creates a new GtkOptionPaneUI instance.
      */
    public static ComponentUI createUI(JComponent x) {
	return new GtkOptionPaneUI();
    }

    /**
     * Creates and returns a Container containin the buttons. The buttons
     * are created by calling <code>getButtons</code>.
     */
    protected Container createButtonArea() {
	Container          b = super.createButtonArea();

	if(b != null && b.getLayout() instanceof ButtonAreaLayout) {
	    ((ButtonAreaLayout)b.getLayout()).setCentersChildren(false);
	}
	return b;
    }

    /**
     * Returns null, CDE/Gtk does not impose a minimum size.
     */
    public Dimension getMinimumOptionPaneSize() {
	return null;
    }

    protected Container createSeparator() {
        return new JPanel() {

            public Dimension getPreferredSize() {
                return new Dimension(10, 2);
            }

            public void paint(Graphics g) {
                int width = getWidth();
	        g.setColor(Color.darkGray);
	        g.drawLine(0, 0, width, 0);
	        g.setColor(Color.white);
	        g.drawLine(0, 1, width, 1);
            }
	};
    }

    /**
     * Creates and adds a JLabel representing the icon returned from
     * <code>getIcon</code> to <code>top</code>. This is messaged from
     * <code>createMessageArea</code>
     */
    protected void addIcon(Container top) {
	/* Create the icon. */
	Icon                  sideIcon = getIcon();

	if (sideIcon != null) {
	    JLabel            iconLabel = new JLabel(sideIcon);

	    iconLabel.setVerticalAlignment(SwingConstants.CENTER);
	    top.add(iconLabel, "West");
	}
    }

}
