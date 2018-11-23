package org.gtk.java.swing.plaf.gtk;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.*;
import javax.swing.plaf.basic.*;


public class GtkToggleButtonUI extends BasicToggleButtonUI 
{
    private final static GtkToggleButtonUI gtkToggleButtonUI = new GtkToggleButtonUI();

    protected Color selectColor;
    protected Color highlight;

    private boolean defaults_initialized = false;
    
    public static ComponentUI createUI(JComponent b) {
	return gtkToggleButtonUI;
    }

    protected BasicButtonListener createButtonListener(AbstractButton b) {
	return new GtkButtonListener(b);
    }

    public void installDefaults(AbstractButton b) {
	super.installDefaults(b);
	if(!defaults_initialized) {
	    selectColor = UIManager.getColor(getPropertyPrefix() + "select");
	    defaults_initialized = true;
	    highlight = UIManager.getColor("ToggleButton.highlight");
	}
	b.setOpaque(false);
    }

    protected void uninstallDefaults(AbstractButton b) {
	super.uninstallDefaults(b);
	defaults_initialized = false;
    }
    
    protected Color getSelectColor() {
	return selectColor;
    }
    
    public void paint(Graphics g, JComponent c) {
	AbstractButton b = (AbstractButton) c;
	Color col = c.getBackground();
	if (b.getModel().isRollover()) {
	    col = highlight;
	}
	fillContentArea(g, (AbstractButton) c, col);   
	super.paint(g, c);
    }

    protected void paintButtonPressed(Graphics g, AbstractButton b) {
        if (b.isContentAreaFilled()) {
	    Color oldColor = g.getColor();
	    Dimension size = b.getSize();
	    Insets insets = b.getInsets();
	    Insets margin = b.getMargin();

	    Color col = getSelectColor();
	    if (b.getModel().isRollover()) {
		col = highlight;
	    }
	    fillContentArea(g, b, col);   
	}
    }
    
    protected void fillContentArea(Graphics g, AbstractButton b, Color fillColor) {
        if (b.isContentAreaFilled()) {
	    Insets margin = b.getMargin();
	    Insets insets = b.getInsets();
	    Dimension size = b.getSize();
	    g.setColor(fillColor);
	    g.fillRect(insets.left-margin.left,
		       insets.top-margin.top, 
		       size.width-(insets.left-margin.left)-(insets.right-margin.right),
		       size.height-(insets.top-margin.top)-(insets.bottom-margin.bottom));
	}
    }
} 
