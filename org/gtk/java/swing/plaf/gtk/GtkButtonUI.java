package org.gtk.java.swing.plaf.gtk;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.basic.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.plaf.*;


public class GtkButtonUI extends BasicButtonUI {

    private final static GtkButtonUI gtkButtonUI = new GtkButtonUI();

    protected Color selectColor;     
    protected Color lightShadow;
    protected Color mediumShadow;
    protected Color darkShadow;
    protected Color highlight;

    private boolean defaults_initialized = false;
    
    public static ComponentUI createUI(JComponent c){
	return gtkButtonUI;
    }
    
    protected BasicButtonListener createButtonListener(AbstractButton b) {
	return new GtkButtonListener(b);
    }

    public void installDefaults(AbstractButton b) {
	super.installDefaults(b);
	if(!defaults_initialized) {
	    selectColor = UIManager.getColor(getPropertyPrefix() + "select");
	    defaults_initialized = true;
	    lightShadow = UIManager.getColor("Button.lightShadow");
	    mediumShadow = UIManager.getColor("Button.mediumShadow");
	    darkShadow = UIManager.getColor("Button.darkShadow");
	    highlight = UIManager.getColor("Button.highlight");
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

    protected void paintFocus(Graphics g, AbstractButton b, Rectangle viewRect, Rectangle textRect, Rectangle iconRect){
	// focus painting is handled by the border
    }
    
    protected void paintButtonPressed(Graphics g, AbstractButton b) {
        fillContentArea(g, b, getSelectColor());
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
