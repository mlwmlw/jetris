package org.gtk.java.swing.plaf.gtk;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.plaf.*;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import java.io.Serializable; 

/**
 * @version 1.41 08/28/98
 * @author Amy Fowler
 * @author Philip Milne
 */
public class GtkTabbedPaneUI extends BasicTabbedPaneUI
{

    protected Color unselectedTabBackground;
    protected Color unselectedTabForeground;
    protected Color unselectedTabShadow;
    protected Color unselectedTabHighlight;
    protected Color mediumShadow;
    protected Color lightShadow;
    protected Color darkShadow;


    public static ComponentUI createUI(JComponent tabbedPane) {
        return new GtkTabbedPaneUI();
    }


    protected void installDefaults() {
        super.installDefaults();

        unselectedTabBackground = UIManager.getColor("TabbedPane.unselectedTabBackground");
        unselectedTabForeground = UIManager.getColor("TabbedPane.unselectedTabForeground");
        unselectedTabShadow = UIManager.getColor("TabbedPane.unselectedTabShadow");
        unselectedTabHighlight = UIManager.getColor("TabbedPane.unselectedTabHighlight");
	mediumShadow = UIManager.getColor("TabbedPane.mediumShadow");
	lightShadow = UIManager.getColor("TabbedPane.lightShadow");
	darkShadow = UIManager.getColor("TabbedPane.darkShadow");
    }

    protected void uninstallDefaults() {
        super.uninstallDefaults();
	
	lightShadow = null;
	mediumShadow = null;
	darkShadow = null;
        unselectedTabBackground = null;
        unselectedTabForeground = null;
        unselectedTabShadow = null;
        unselectedTabHighlight = null;
    }

    protected void paintContentBorderTopEdge(Graphics g, int tabPlacement,
					     int selectedIndex, 
					     int x, int y, int w, int h) {

        g.setColor(lightShadow);
        if (tabPlacement != TOP || selectedIndex < 0) {
            g.drawLine(x, y, x+w-2, y);
        } 
	else {
            Rectangle selRect = rects[selectedIndex];

            g.drawLine(x, y, selRect.x - 1, y);
            if (selRect.x + selRect.width < x + w - 2) {
                g.drawLine(selRect.x + selRect.width, y, 
                           x+w-2, y);
            } 
        }
    }

    protected void paintContentBorderBottomEdge(Graphics g, int tabPlacement,
                                               int selectedIndex,
                                               int x, int y, int w, int h) { 
        g.setColor(darkShadow);
        if (tabPlacement != BOTTOM || selectedIndex < 0) {
	    g.setColor(darkShadow);
	    g.drawLine(x, y+h-1, x+w-1, y+h-1);
	    g.setColor(mediumShadow);
	    g.drawLine(x+2, y+h-2, x+w-3, y+h-2);
        }
	else {
            Rectangle selRect = rects[selectedIndex];

            g.drawLine(x+1, y+h-1, selRect.x - 1, y+h-1);
            if (selRect.x + selRect.width < x + w - 2) {
                g.drawLine(selRect.x + selRect.width, y+h-1, x+w-2, y+h-1);
            } 
        }
    }

    protected void paintContentBorderRightEdge(Graphics g, int tabPlacement,
                                               int selectedIndex,
                                               int x, int y, int w, int h) {
        if (tabPlacement != RIGHT || selectedIndex < 0) {
	    g.setColor(darkShadow);
            g.drawLine(x+w-1, y, x+w-1, y+h-1);
	    g.setColor(mediumShadow);
	    g.drawLine(x+w-2, y+2, x+w-2, y+h-2);
        }
	else {
            Rectangle selRect = rects[selectedIndex];
	    if (y < selRect.y) {
		g.setColor(darkShadow);
		g.drawLine(x+w-1, y, x+w-1, selRect.y-1);
		g.setColor(mediumShadow);
		g.drawLine(x+w-2, y+2, x+w-2, selRect.y-1);
	    }

            if (selRect.y+selRect.height < y+h-2) {
		g.setColor(darkShadow);
                g.drawLine(x+w-1, selRect.y+selRect.height, 
                           x+w-1, y+h-1);
		g.setColor(mediumShadow);
                g.drawLine(x+w-2, selRect.y+selRect.height, 
                           x+w-2, y+h-2);
            } 
        }
    }

    protected void paintContentBorderLeftEdge(Graphics g, int tabPlacement,
                                               int selectedIndex,
                                               int x, int y, int w, int h) { 
        g.setColor(lightShadow);
        if (tabPlacement != LEFT || selectedIndex < 0) {
            g.drawLine(x, y, x, y+h-2);
        }
	else {
            Rectangle selRect = rects[selectedIndex];
	    g.drawLine(x, y, x, selRect.y-1);
            if (selRect.y + selRect.height < y + h - 2) {
		g.drawLine(x, selRect.y+selRect.height, x, y+h-1);
            } 
        }
    }

    protected void paintTabBackground(Graphics g,
                                      int tabPlacement, int tabIndex,
                                      int x, int y, int w, int h,
		 		      boolean isSelected) {
        g.setColor(isSelected?tabPane.getBackgroundAt(tabIndex):unselectedTabBackground);
        switch(tabPlacement) {
          case LEFT:
              g.fillRect(x+1, y+1, w-1, h-2);
              break;
          case RIGHT:
              g.fillRect(x, y+1, w-1, h-2);
              break;
          case BOTTOM:
              g.fillRect(x, y, w-1, h-1);
              break;
          case TOP:
          default:
              g.fillRect(x+1, y+1, w-1, h-1);
        }

    }


    protected void paintTopTabBorder(int tabIndex, Graphics g, 
				     int x, int y, int w, int h,
				     int btm, int rght,
				     boolean isSelected) {
	int h1 = isSelected?h:h+2;
	g.setColor(lightShadow);
	g.drawLine(x+1, y, x+w-2, y);
	g.drawLine(x, y+1, x, y+h1-3);
	g.setColor(mediumShadow);
	g.drawLine(x+w-2, y+2, x+w-2, y+h1-3);
	g.setColor(darkShadow);
	g.drawLine(x+w-1, y+1, x+w-1, y+h1-3);
    }

    protected void paintBottomTabBorder(int tabIndex, Graphics g, 
				     int x, int y, int w, int h,
				     int btm, int rght,
				     boolean isSelected) {
	int y1 = isSelected?y:(y-1);
	g.setColor(lightShadow);
	g.drawLine(x, y1+1, x, y1+h-2);
	g.setColor(mediumShadow);
	g.drawLine(x+2, y1+h-2, x+w-2, y1+h-2);
	g.drawLine(x+w-2, y1+h-2, x+w-2, y1+2);
	g.setColor(darkShadow);
	g.drawLine(x+1, y1+h-1, x+w-1, y1+h-1);
	g.drawLine(x+w-1, y1+h-1, x+w-1, y1+1);
    }

    protected void paintLeftTabBorder(int tabIndex, Graphics g, 
				     int x, int y, int w, int h,
				     int btm, int rght,
				     boolean isSelected) {
	int w1 = isSelected?(w-1):w;

	g.setColor(lightShadow);
	g.drawLine(x+1, y, x+w1-1, y);
	g.drawLine(x, y+1, x, y+h-1);
	g.setColor(mediumShadow);
	g.drawLine(x+2, y+h-2, x+w1-1, y+h-2);
	g.setColor(darkShadow);
	g.drawLine(x+1, y+h-1, x+w1-1, y+h-1);
    }

    protected void paintRightTabBorder(int tabIndex, Graphics g, 
				     int x, int y, int w, int h,
				     int btm, int rght,
				     boolean isSelected) {
	g.setColor(lightShadow);
	g.drawLine(x+1, y, x+w-2, y);
	g.setColor(mediumShadow);
	g.drawLine(x+2, y+h-2, x+w-2, y+h-2);
	g.drawLine(x+w-2, y+h-3, x+w-2, y+2);
	g.setColor(darkShadow);
	g.drawLine(x+w-1, y+1, x+w-1, y+h-2);
	g.drawLine(x+1, y+h-1, x+w-2, y+h-1);
    }

    protected void paintTabBorder(Graphics g,
                                  int tabPlacement, int tabIndex,
                                  int x, int y, int w, int h,
				  boolean isSelected) { 
	int right = x + (w-1);
	int bottom = y  + (h-1);

        g.setColor(isSelected? lightHighlight : unselectedTabHighlight);

        switch(tabPlacement) {
	case LEFT:
	    paintLeftTabBorder(tabIndex, g, x, y, w, h, bottom, right, isSelected);
	    break;
	case RIGHT:
	    paintRightTabBorder(tabIndex, g, x, y, w, h, bottom, right, isSelected);
	    break;
	case BOTTOM:
	    paintBottomTabBorder(tabIndex, g, x, y, w, h, bottom, right, isSelected);
	    break;
	case TOP:
	default:
	    paintTopTabBorder(tabIndex, g, x, y, w, h, bottom, right, isSelected);
        }
    }

    protected void paintFocusIndicator(Graphics g, int tabPlacement,
                                       Rectangle[] rects, int tabIndex, 
                                       Rectangle iconRect, Rectangle textRect,
                                       boolean isSelected) {
        Rectangle tabRect = rects[tabIndex];
        if (tabPane.hasFocus() && isSelected) {
            int x, y, w, h;
	    g.setColor(focus);
            switch(tabPlacement) {
	    case LEFT:
		x = tabRect.x + 4;
		y = tabRect.y + 4;
		w = tabRect.width - 10;
		h = tabRect.height - 10;
		break;
	    case RIGHT:
		x = tabRect.x + 4;
		y = tabRect.y + 4;
		w = tabRect.width - 10;
		h = tabRect.height - 10;
		break;
	    case BOTTOM:
		x = tabRect.x + 4;
		y = tabRect.y + 4;
		w = tabRect.width - 10;
		h = tabRect.height - 10;
		break;
	    case TOP:
	    default:
		x = tabRect.x + 4;
		y = tabRect.y + 4;
		w = tabRect.width - 10;
		h = tabRect.height - 10;
            }
            g.drawRect(x, y, w, h);
        }
    }

     protected int getTabRunIndent(int tabPlacement, int run) {
         return run*5;
     }

     protected int getTabRunOverlay(int tabPlacement) {
         tabRunOverlay = (tabPlacement == LEFT || tabPlacement == RIGHT)?
             (int)Math.round((float)maxTabWidth * .10) :
             (int)Math.round((float)maxTabHeight * .22);
         return 0;
     } 
       
}
