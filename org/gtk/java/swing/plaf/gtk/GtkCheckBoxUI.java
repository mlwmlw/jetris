package org.gtk.java.swing.plaf.gtk;

import javax.swing.*;

import javax.swing.plaf.*;

import java.awt.*;


public class GtkCheckBoxUI extends GtkRadioButtonUI {

    private static final GtkCheckBoxUI gtkCheckBoxUI = new GtkCheckBoxUI();

    private final static String propertyPrefix = "CheckBox" + ".";

    private boolean defaults_initialized = false;


    public static ComponentUI createUI(JComponent c){
	return gtkCheckBoxUI;
    }

    public String getPropertyPrefix() {
	return propertyPrefix;
    }

    public void installDefaults(AbstractButton b) {
	super.installDefaults(b);
	if(!defaults_initialized) {
	    icon = UIManager.getIcon(getPropertyPrefix() + "icon");
	    defaults_initialized = true;
	}
    }

    protected void uninstallDefaults(AbstractButton b) {
	super.uninstallDefaults(b);
	defaults_initialized = false;
    }
} 
