package org.gtk.java.swing.plaf.gtk;

import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;
import javax.swing.plaf.*;
import javax.swing.plaf.basic.BasicSeparatorUI;

/**
 *
 * @version 1.16 08/28/98
 * @author Georges Saab
 * @author Jeff Shapiro
 */

public class GtkSeparatorUI extends BasicSeparatorUI {

    protected static Color mediumShadow;
    protected static Color lightShadow;

    public static ComponentUI createUI(JComponent c) {
        return new GtkSeparatorUI();
    }

    public void installUI(JComponent c)
    {
        installDefaults((JSeparator)c);
        installListeners((JSeparator)c);
    }

    public void uninstallUI(JComponent c)
    {
        uninstallDefaults((JSeparator)c);
        uninstallListeners((JSeparator)c);
    }

    protected void installDefaults(JSeparator s)
    {
        LookAndFeel.installColors(s, "Separator.background", "Separator.foreground");
    }

    protected void uninstallDefaults(JSeparator s)
    {
    }

    protected void installListeners(JSeparator s)
    {
    }

    protected void uninstallListeners(JSeparator s)
    {
    }

    public GtkSeparatorUI() {
	super();
	UIDefaults table = UIManager.getLookAndFeelDefaults();
	lightShadow = table.getColor("lightShadow");
	mediumShadow = table.getColor("mediumShadow");
    }

    public void paint(Graphics g, JComponent c) {
	Dimension s = c.getSize();
	if (((JSeparator)c).getOrientation() == JSeparator.VERTICAL)  {
          g.setColor(mediumShadow);
          g.drawLine(0, 0, 0, s.height);
          g.setColor(lightShadow);
          g.drawLine(1, 0, 1, s.height);
        }
	else { // HORIZONTAL
          g.setColor(mediumShadow);
          g.drawLine(0, 0, s.width, 0);

          g.setColor(lightShadow);
          g.drawLine(0, 1, s.width, 1);
        }

    }

    public Dimension getPreferredSize(JComponent c)
    { 
        if (((JSeparator)c).getOrientation() == JSeparator.VERTICAL)
            return new Dimension(2, 0);
        else
            return new Dimension(0, 2);
    }

    public Dimension getMinimumSize(JComponent c) { return null; }
    public Dimension getMaximumSize(JComponent c) { return null; }

}




