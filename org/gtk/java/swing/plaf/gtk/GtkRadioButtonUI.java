package org.gtk.java.swing.plaf.gtk;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.basic.BasicRadioButtonUI;
import javax.swing.plaf.basic.BasicGraphicsUtils;
import javax.swing.plaf.basic.BasicButtonListener;

import javax.swing.plaf.*;

import java.awt.*;


public class GtkRadioButtonUI extends BasicRadioButtonUI {

    private static final GtkRadioButtonUI gtkRadioButtonUI = new GtkRadioButtonUI();

    protected Color focusColor;
    protected Color highlight;

    private boolean defaults_initialized = false;
    
    public static ComponentUI createUI(JComponent c) {
	return gtkRadioButtonUI;
    }

    protected BasicButtonListener createButtonListener(AbstractButton b) {
	return new GtkButtonListener(b);
    }

    public void installDefaults(AbstractButton b) {
	super.installDefaults(b);
	if(!defaults_initialized) {
	    focusColor = UIManager.getColor(getPropertyPrefix() + "focus");
	    highlight = UIManager.getColor(getPropertyPrefix() + "highlight");
	    defaults_initialized = true;
	}
    }

    protected void uninstallDefaults(AbstractButton b) {
	super.uninstallDefaults(b);
	defaults_initialized = false;
    }
    
    private static Dimension size = new Dimension();
    private static Rectangle viewRect = new Rectangle();
    private static Rectangle iconRect = new Rectangle();
    private static Rectangle textRect = new Rectangle();

    public synchronized void paint(Graphics g, JComponent c) {
        AbstractButton b = (AbstractButton) c;
        ButtonModel model = b.getModel();

        Font f = c.getFont();
        g.setFont(f);
        FontMetrics fm = g.getFontMetrics();

        size = b.getSize(size);
        viewRect.x = viewRect.y = 0;
        viewRect.width = size.width;
        viewRect.height = size.height;
        iconRect.x = iconRect.y = iconRect.width = iconRect.height = 0;
        textRect.x = textRect.y = textRect.width = textRect.height = 0;

        Icon altIcon = b.getIcon();
        Icon selectedIcon = null;
        Icon disabledIcon = null;

        String text = SwingUtilities.layoutCompoundLabel(
            c, fm, b.getText(), altIcon != null ? altIcon : getDefaultIcon(),
            b.getVerticalAlignment(), b.getHorizontalAlignment(),
            b.getVerticalTextPosition(), b.getHorizontalTextPosition(),
            viewRect, iconRect, textRect, getDefaultTextIconGap(b));

        // fill background
        if(c.isOpaque()) {
	    if (model.isRollover()) {
		g.setColor(highlight);
	    }
	    else {
		g.setColor(b.getBackground());
	    }
            g.fillRect(0,0, size.width, size.height); 
        }


        // Paint the radio button
        if(altIcon != null) { 

            if(!model.isEnabled()) {
                altIcon = b.getDisabledIcon();
            } else if(model.isPressed() && model.isArmed()) {
                altIcon = b.getPressedIcon();
                if(altIcon == null) {
                    // Use selected icon
                    altIcon = b.getSelectedIcon();
                } 
            } else if(model.isSelected()) {
                if(b.isRolloverEnabled() && model.isRollover()) {
                        altIcon = (Icon) b.getRolloverSelectedIcon();
                        if (altIcon == null) {
                                altIcon = (Icon) b.getSelectedIcon();
                        }
                }
                else {
                        altIcon = (Icon) b.getSelectedIcon();
                }
            } else if(b.isRolloverEnabled() && model.isRollover()) {
                altIcon = (Icon) b.getRolloverIcon();
            } 

            if(altIcon == null) {
                altIcon = b.getIcon();
            }

            altIcon.paintIcon(c, g, iconRect.x, iconRect.y);

        } else {
            getDefaultIcon().paintIcon(c, g, iconRect.x, iconRect.y);
        }


        // Draw the Text
        if(text != null) {
            if(model.isEnabled()) {
                // *** paint the text normally
                g.setColor(b.getForeground());
                BasicGraphicsUtils.drawString(g,text,model.getMnemonic(),
                                              textRect.x, 
                                              textRect.y + fm.getAscent());
            }
	    else {
                // *** paint the text disabled
                g.setColor(b.getBackground().brighter());
                BasicGraphicsUtils.drawString(g,text,model.getMnemonic(),
                                              textRect.x + 1, 
                                              textRect.y + fm.getAscent() + 1);
                g.setColor(b.getBackground().darker());
                BasicGraphicsUtils.drawString(g,text,model.getMnemonic(),
                                              textRect.x, 
                                              textRect.y + fm.getAscent());
            }
            if(b.hasFocus() && b.isFocusPainted() && 
               textRect.width > 0 && textRect.height > 0 ) {
                paintFocus(g, textRect, size);
            }
        }
    }

    protected Color getFocusColor() {
	return focusColor;
    }
    
    protected void paintFocus(Graphics g, Rectangle t, Dimension d){
	g.setColor(getFocusColor());
	g.drawRect(0,0,d.width-1,d.height-1);
    } 
    
} 
