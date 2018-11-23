package org.gtk.java.swing.plaf.gtk;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.beans.*;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.basic.BasicSplitPaneUI;


/**
 * @version 1.30 05/10/99
 * @author Scott Violet
 */
public class GtkSplitPaneDivider extends BasicSplitPaneDivider
    implements PropertyChangeListener
{
    public static final int defaultDividerSize = 10;

    protected int thumbSize = 10;

    protected static Color lightShadow;
    protected static Color mediumShadow;
    protected static Color darkShadow;
    protected static Color control;
    protected static Color focus;

    static final Cursor crosshairCursor =
                            Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);

    static final Cursor defaultCursor =
	Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);


    public GtkSplitPaneDivider(GtkSplitPaneUI ui) {
	super(ui);
	//	orientation = splitPane.getOrientation();
        setBackground(UIManager.getColor("SplitPane.background"));
        lightShadow = UIManager.getColor("lightShadow");
        mediumShadow = UIManager.getColor("mediumShadow");
        darkShadow = UIManager.getColor("darkShadow");
	control = UIManager.getColor("control");
	focus = UIManager.getColor("focus");
	setDividerSize(thumbSize);
    }

    public void setGtkSplitPaneUI(GtkSplitPaneUI newUI) {
        if (splitPane != null) {
            splitPane.removePropertyChangeListener(this);
           if (mouseHandler != null) {
               splitPane.removeMouseListener(mouseHandler);
               splitPane.removeMouseMotionListener(mouseHandler);
	       removeMouseListener(mouseHandler);
	       removeMouseMotionListener(mouseHandler);
               mouseHandler = null;
           }
        }
        splitPaneUI = (GtkSplitPaneUI)newUI;
        if (newUI != null) {
            splitPane = newUI.getSplitPane();
            if (splitPane != null) {
                if (mouseHandler == null) mouseHandler = new MouseHandler();
                splitPane.addMouseListener(mouseHandler);
                splitPane.addMouseMotionListener(mouseHandler);
		addMouseListener(mouseHandler);
		addMouseMotionListener(mouseHandler);
                splitPane.addPropertyChangeListener(this);
                if (splitPane.isOneTouchExpandable()) {
                    oneTouchExpandableChanged();
                }
            }
        }
        else {
            splitPane = null;
        }
    }


    public Rectangle getThumbBounds() {
        Dimension size = getSize();
	int x = size.width - 2*thumbSize;
	int y = size.height - 2*thumbSize;
	if (orientation == JSplitPane.HORIZONTAL_SPLIT) {
	    return new Rectangle(0, y, thumbSize, thumbSize);
	}
	else {
	    return new Rectangle(x, 0, thumbSize, thumbSize);
	}
    }

    public void setDividerSize(int newSize) {
	super.setDividerSize(newSize);
    }

    public int getDividerSize() {
        return defaultDividerSize;
    }


    public void paint(Graphics g) {
        Color               bgColor = getBackground();
        Dimension           size = getSize();

        g.setColor(bgColor);

        if(getBasicSplitPaneUI().getOrientation() == JSplitPane.HORIZONTAL_SPLIT) {
            int center = (size.width-1)/2;
            int y = size.height - 2*thumbSize;

	    g.fillRect(0, 0, size.width, size.height);
	    g.drawLine(0, 0, 0, size.height);
	    g.setColor(lightShadow);
	    g.drawLine(1, 0, 1, size.height);
	    g.setColor(darkShadow);
	    g.drawLine(size.width-1, 0, size.width-1, size.height-1);
	    g.setColor(mediumShadow);
	    g.drawLine(size.width-2, 0, size.width-2, size.height-1);
	    g.drawLine(center, 0, center, size.height-1);
	    g.setColor(lightShadow);
	    g.drawLine(center+1, 0, center+1, size.height-1);

	    // paint thumb
	    g.setColor(bgColor);
	    g.fillRect(0, y, thumbSize, thumbSize);
	    GtkGraphicsUtils.drawBorder(g, 0, y, thumbSize, thumbSize,
					lightShadow, mediumShadow, darkShadow,
					true);
        }
	else {
	    int center = (size.height-1)/2;
	    int x = size.width - 2*thumbSize;

	    g.fillRect(0, 0, size.width, size.height);
	    g.drawLine(0, 0, size.width, 0);
	    g.setColor(lightShadow);
	    g.drawLine(0, 1, size.width, 1);
	    g.setColor(darkShadow);
	    g.drawLine(0, size.height-1, size.width-1, size.height-1);
	    g.setColor(mediumShadow);
	    g.drawLine(0, size.height-2, size.width-1, size.height-2);
	    g.drawLine(0, center, size.width-1, center);
	    g.setColor(lightShadow);
	    g.drawLine(0, center+1, size.width-1, center+1);

	    // paint thumb
	    g.setColor(bgColor);
	    g.fillRect(x, 0, thumbSize, thumbSize);
	    GtkGraphicsUtils.drawBorder(g, x, 0, thumbSize, thumbSize,
					lightShadow, mediumShadow, darkShadow,
					true);
        }
        super.paint(g);
    }

    protected class MouseHandler extends BasicSplitPaneDivider.MouseHandler
            implements MouseMotionListener
    {
        /**
         * Starts the dragging session by creating the appropriate instance
         * of DragController.
         */
        public void mousePressed(MouseEvent e) {
	    Rectangle bounds = getThumbBounds();
	    if (bounds.contains(e.getX(), e.getY())) {
		super.mousePressed(e);
	    }
	}

        public void mouseMoved(MouseEvent e) {
            if (dragger != null) return;

            int         eventX = e.getX();
            int         eventY = e.getY();
            Rectangle   bounds = getThumbBounds();
	    Cursor      newCursor;

	    if (e.getSource() == GtkSplitPaneDivider.this) {
		if (bounds.contains(eventX, eventY)) {
		    newCursor = crosshairCursor;
		}
		else {
		    newCursor = defaultCursor;
		}
	    }
	    else {
		if (eventX >= (bounds.x - 1) &&
		    eventX <= (bounds.x + bounds.width) &&
		    eventY >= (bounds.y - 1) &&
		    eventY <= (bounds.y + bounds.height)) {
		    newCursor = crosshairCursor;
		}
		else {
		    newCursor = defaultCursor;
		}
	    }
	    if (getCursor() != newCursor) {
		setCursor(newCursor);
            }
        }
    }


    public static void paintTop(Graphics g, int x) {
	g.setColor(control);
	g.fillRect(x+2, 0, 6, 2);
	g.setColor(mediumShadow);
	g.drawLine(x+1, 0, x+1, 0);
	g.drawLine(x+4, 0, x+5, 0);
	g.drawLine(x+4, 1, x+4, 1);
	g.drawLine(x+8, 0, x+8, 1);
	g.setColor(lightShadow);
	g.drawLine(x+1, 1, x+1, 1);
	g.drawLine(x+5, 1, x+5, 1);
    }

    public static void paintBottom(Graphics g, int x, int y) {
	g.setColor(control);
	g.fillRect(x+2, y-1, 6, 2);
	g.setColor(lightShadow);
	g.drawLine(x+1, y-1, x+1, y);
	g.drawLine(x+5, y-1, x+5, y-1);
	g.drawLine(x+4, y, x+5, y);
	g.setColor(mediumShadow);
	g.drawLine(x+4, y-1, x+4, y-1);
	g.drawLine(x+8, y-1, x+8, y);
	g.setColor(darkShadow);
	g.drawLine(x+9, y-1, x+9, y-1);
    }

    public static void paintLeft(Graphics g, int y) {
	g.setColor(control);
	g.fillRect(0, y+2, 2, 6);
	g.setColor(mediumShadow);
	g.drawLine(0, y+1, 0, y+1);
	g.drawLine(0, y+4, 2, y+4);
	g.drawLine(0, y+8, 2, y+8);
	g.setColor(lightShadow);
	g.drawLine(1, y+1, 1, y+1);
	g.drawLine(0, y+5, 2, y+5);	
    }

    public static void paintRight(Graphics g, int y, int x) {
	g.setColor(control);
	g.fillRect(x-1, y+2, 2, 6);
	g.setColor(lightShadow);
	g.drawLine(x-1, y+1, x, y+1);
	g.drawLine(x-1, y+5, x, y+5);
	g.setColor(mediumShadow);
	g.drawLine(x-1, y+4, x, y+4);
	g.drawLine(x-1, y+8, x, y+8);
	g.setColor(darkShadow);
	g.drawLine(x-1, y+9, x-1, y+9);
    }
    
}
