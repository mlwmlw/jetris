package org.gtk.java.swing.plaf.gtk;

import javax.swing.*;

import javax.swing.plaf.UIResource;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Polygon;

import java.io.Serializable;


public class GtkIconFactory implements Serializable
{
    private static Icon checkBoxIcon;
    private static Icon radioButtonIcon;
    private static Icon menuItemCheckIcon;
    private static Icon menuItemArrowIcon;
    private static Icon menuArrowIcon;

    public static Icon getMenuItemCheckIcon() {
	if (menuItemCheckIcon == null) {
	    menuItemCheckIcon = new MenuItemCheckIcon();
	}
	return menuItemCheckIcon;
    }

    public static Icon getMenuItemArrowIcon() {
	if (menuItemArrowIcon == null) {
	    menuItemArrowIcon = new MenuItemArrowIcon();
	}
	return menuItemArrowIcon;
    }

    public static Icon getMenuArrowIcon() {
	if (menuArrowIcon == null) {
	    menuArrowIcon = new MenuArrowIcon();
	}
	return menuArrowIcon;
    }

    public static Icon getCheckBoxIcon() {
	if (checkBoxIcon == null) {
	    checkBoxIcon = new CheckBoxIcon();
	}
	return checkBoxIcon;
    }

    public static Icon getRadioButtonIcon() {
	if (radioButtonIcon == null) {
	    radioButtonIcon = new RadioButtonIcon();
	}
	return radioButtonIcon;
    }


    // NOTCOMPLETED
    private static class CheckBoxIcon implements Icon, UIResource, Serializable  {
	final static int csize = 10;

	private Color lightShadow = UIManager.getColor("lightShadow");
	private Color mediumShadow = UIManager.getColor("mediumShadow");
	private Color darkShadow = UIManager.getColor("darkShadow");
	private Color windowDark = UIManager.getColor("windowDark");
	
	public void paintIcon(Component c, Graphics g, int x, int y){
	    AbstractButton b = (AbstractButton) c;
	    ButtonModel model = b.getModel();

	    boolean isPressed = model.isPressed();
	    boolean isArmed = model.isArmed();
	    boolean isEnabled = model.isEnabled();
	    boolean isSelected = model.isSelected();
	    
	    boolean checkToggleIn = ((isPressed && 
				      !isArmed   &&
				      isSelected) ||
				     (isPressed &&
				      isArmed   &&
				      !isSelected));
	    boolean uncheckToggleOut = ((isPressed && 
					 !isArmed &&
					 !isSelected) ||
					(isPressed &&
					 isArmed &&
					 isSelected));
	    
	    boolean checkIn = (!isPressed  &&
			       isArmed    &&
			       isSelected  ||
			       (!isPressed &&
				!isArmed  &&
				isSelected));

            // Padding required to keep focus highlight from intersecting icon.
            x += (GtkGraphicsUtils.isLeftToRight(c)) ? 2 : -3;
            
	    if (checkToggleIn) {
		// toggled from unchecked to checked
		GtkGraphicsUtils.drawBorder(g, x, y, csize, csize,
					    lightShadow, mediumShadow, darkShadow,
					    true);
	    }
	    else if (uncheckToggleOut) {
		GtkGraphicsUtils.drawBorder(g, x, y, csize, csize,
					    lightShadow, mediumShadow, darkShadow,
					    false);
	    }	  
 	    else if (checkIn) { 
 		// show checked, unpressed state
		GtkGraphicsUtils.drawBorder(g, x, y, csize, csize,
					    lightShadow, mediumShadow, darkShadow,
					    false);
		g.setColor(windowDark);
		g.fillRect(x+2, y+2, csize-3, csize-3);
 	    }
 	    else { //  show unchecked state
		GtkGraphicsUtils.drawBorder(g, x, y, csize, csize,
					    lightShadow, mediumShadow, darkShadow,
					    true);
 	    }
	}
	
	public int getIconWidth() {
	    return csize;
	}
	
	public int getIconHeight() {
	    return csize;
	}
	
    } // end class CheckBoxIcon
    

    // OK
    private static class RadioButtonIcon implements Icon, UIResource, Serializable {
	private Color lightShadow = UIManager.getColor("lightShadow");
	private Color mediumShadow = UIManager.getColor("mediumShadow");
	private Color darkShadow = UIManager.getColor("darkShadow");

	public void paintIcon(Component c, Graphics g, int x, int y) {
	    // fill interior
	    AbstractButton b = (AbstractButton) c;
	    ButtonModel model = b.getModel();
	    
	    int w = getIconWidth();
	    int h = getIconHeight();

	    // add pad so focus isn't smudged on the x
            x += (GtkGraphicsUtils.isLeftToRight(c))? 2 : -3;

	    boolean isPressed = model.isPressed();
	    boolean isArmed = model.isArmed();
	    boolean isEnabled = model.isEnabled();
	    boolean isSelected = model.isSelected();
	    
	    boolean checkIn = ((isPressed && 
				!isArmed   &&
				isSelected) ||
			       (isPressed &&
				isArmed   &&
				!isSelected) 
			       ||
			       (!isPressed  &&
				isArmed    &&
				isSelected  ||
				(!isPressed &&
				 !isArmed  &&
				 isSelected)));
	    
	    g.translate(x, y);
	    if (checkIn){
		g.setColor(mediumShadow);
		g.drawLine(0, 5, 5, 0);
		g.drawLine(1, 5, 5, 1);
		g.drawLine(5, 0, 10, 5);
		g.drawLine(5, 1, 9, 5);
		g.setColor(darkShadow);
		g.drawLine(2, 5, 5, 2);
		g.drawLine(5, 2, 8, 5);
		g.setColor(lightShadow);
		g.drawLine(1, 6, 5, 10);
		g.drawLine(5, 10, 9, 6);
		g.drawLine(2, 6, 5, 9);
		g.drawLine(5, 9, 8, 6);
	    }
	    else {
		g.setColor(lightShadow);
		g.drawLine(0, 5, 5, 0);
		g.drawLine(1, 5, 5, 1);
		g.drawLine(5, 0, 10, 5);
		g.drawLine(5, 1, 9, 5);
		g.setColor(mediumShadow);
		g.drawLine(2, 6, 5, 9);
		g.drawLine(5, 9, 8, 6);
		g.drawLine(3, 6, 5, 8);
		g.drawLine(5, 8, 7, 6);
		g.setColor(darkShadow);
		g.drawLine(1, 6, 5, 10);
		g.drawLine(5, 10, 9, 6);
	    }
	    g.translate(-x, -y);
	}
	
	public int getIconWidth() {
	    return 11;
	}
	
	public int getIconHeight() {
	    return 11;
	}
    } // end class RadioButtonIcon


    private static class MenuItemCheckIcon implements Icon, UIResource, Serializable
    {
	public void paintIcon(Component c,Graphics g, int x, int y) {
	}
	public int getIconWidth() { return 0; }
	public int getIconHeight() { return 0; }
    }  // end class MenuItemCheckIcon
    
    
    private static class MenuItemArrowIcon implements Icon, UIResource, Serializable
    {
	public void paintIcon(Component c,Graphics g, int x, int y) {
	}
	public int getIconWidth() { return 0; }
	public int getIconHeight() { return 0; }
    }  // end class MenuItemArrowIcon
    
    
    // TOBECOMPLETED
    private static class MenuArrowIcon implements Icon, UIResource, Serializable 
    {
	private Color lightShadow = UIManager.getColor("lightShadow");
	private Color mediumShadow = UIManager.getColor("mediumShadow");
	private Color darkShadow = UIManager.getColor("darkShadow");

        public void paintIcon(Component c, Graphics g, int x, int y) {
            AbstractButton b = (AbstractButton) c;
            ButtonModel model = b.getModel();

            int w = getIconWidth();
            int h = getIconHeight();

            if (model.isSelected()){
                if(GtkGraphicsUtils.isLeftToRight(c)) {
		    g.translate(x, y);
		    g.setColor(lightShadow);
		    g.drawLine(0, 9, 9, 5);
		    g.setColor(darkShadow);
		    g.drawLine(1, 2, 7, 5);
		    g.setColor(mediumShadow);
		    g.drawLine(0, 0, 0, 8);
		    g.drawLine(1, 1, 8, 5);
		    g.setColor(darkShadow);
		    g.drawLine(1, 1, 1, 7);
		    g.translate(-x, -y);
                } 
		else {
                }
            } 
	    else {		// not selected
                if (GtkGraphicsUtils.isLeftToRight(c)){
		    g.translate(x, y);
		    g.setColor(lightShadow);
		    g.drawLine(0, 0, 0, 8);
		    g.drawLine(1, 1, 8, 4);
		    g.setColor(mediumShadow);
		    g.drawLine(1, 8, 6, 6);
		    g.setColor(darkShadow);
		    g.drawLine(0, 9, 9, 5);
		    g.translate(-x, -y);
                }
		else {
                }
            }

        }

        public int getIconWidth() { return 10; }

        public int getIconHeight() { return 10; }

    } // End class MenuArrowIcon
}
