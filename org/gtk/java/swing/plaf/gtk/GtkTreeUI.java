package org.gtk.java.swing.plaf.gtk;

import java.awt.*;
import java.awt.event.*;

import java.io.*;
import java.util.*;

import javax.swing.*;
import javax.swing.plaf.*;
import javax.swing.tree.*;
import javax.swing.plaf.basic.*;

/**
 * @version 1.16 08/28/98
 * @author Jeff Dinkins
 */
public class GtkTreeUI extends BasicTreeUI
{
    static final int HALF_SIZE = 4;
    static final int SIZE = 9;

    /**
     * creates a UI object to represent a Gtk Tree widget
     */
    public GtkTreeUI() {
	super();
    }

    public void installUI(JComponent c) {
	super.installUI(c);
    }

    // BasicTreeUI overrides
  
    protected void paintVerticalLine(Graphics g, JComponent c, int x, int top, int bottom) {
	g.fillRect(x, top, 1, bottom-top);
    }

    protected void paintHorizontalLine(Graphics g, JComponent c, int y, int left, int right) {
	g.fillRect(left, y, right-left, 1);
    }


    /**
     * The minus sign button icon.
     */
    public static class GtkExpandedIcon implements Icon, Serializable {
	static Color bg;
	static Color fg;

	public GtkExpandedIcon() {
	    bg = UIManager.getColor("Tree.iconBackground");
	    fg = UIManager.getColor("Tree.iconForeground");
	}

        public static Icon createExpandedIcon() {
	    return new GtkExpandedIcon();
        }

	public void paintIcon(Component c, Graphics g, int x, int y) {
	    g.setColor(bg);
	    g.fillRect(x, y, SIZE-1, SIZE-1);
	    
	    g.setColor(fg);
	    g.drawRect(x, y, SIZE-1, SIZE-1);
	    g.drawLine(x+2, y+HALF_SIZE, x+SIZE-3, y+HALF_SIZE);
	}

	public int getIconWidth() { return SIZE; }
	public int getIconHeight() { return SIZE; }
    }

    /**
     * The plus sign button icon.
     */
    public static class GtkCollapsedIcon extends GtkExpandedIcon {
        public static Icon createCollapsedIcon() {
	    return new GtkCollapsedIcon();
        }

	public void paintIcon(Component c, Graphics g, int x, int y) {
	    super.paintIcon(c, g, x, y);
	    g.drawLine(x + HALF_SIZE, y + 2, x + HALF_SIZE, y + (SIZE - 3));
	}
    }
    
    protected void drawDashedHorizontalLine(Graphics g, int y, int x1, int x2) {
    }

    protected void drawDashedVerticalLine(Graphics g, int x, int y1, int y2) {
    }

    public static ComponentUI createUI(JComponent x) {
	return new GtkTreeUI();
    }

    public TreeCellRenderer createDefaultCellRenderer() {
    	return new GtkTreeCellRenderer();
    }

}
