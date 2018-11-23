package org.gtk.java.swing.plaf.gtk;

import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;
import javax.swing.plaf.*;

/**
 * @version 1.6 11/05/98
 * @author Jeff Shapiro
 */

public class GtkPopupMenuSeparatorUI extends GtkSeparatorUI {
    
    protected static Color mediumShadow;
    protected static Color lightShadow;
    
    public static ComponentUI createUI(JComponent c) {
        return new GtkPopupMenuSeparatorUI();
    }

    public GtkPopupMenuSeparatorUI() {
	super();
	UIDefaults table = UIManager.getLookAndFeelDefaults();
	lightShadow = table.getColor("lightShadow");
	mediumShadow = table.getColor("mediumShadow");
    }

    public void paint(Graphics g, JComponent c) {
        Dimension s = c.getSize();

	g.setColor(mediumShadow);
	g.drawLine(0, 0, s.width, 0);

	g.setColor(lightShadow);
	g.drawLine(0, 1, s.width, 1);
    }

    public Dimension getPreferredSize(JComponent c) {
        return new Dimension(0, 2);
    }

}
