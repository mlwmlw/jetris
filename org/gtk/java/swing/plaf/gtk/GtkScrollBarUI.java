package org.gtk.java.swing.plaf.gtk;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.plaf.*;
import javax.swing.border.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.plaf.basic.BasicGraphicsUtils;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.*;


public class GtkScrollBarUI extends BasicScrollBarUI 
{
    private Color darkShadow = UIManager.getColor("darkShadow");
    private Color mediumShadow = UIManager.getColor("mediumShadow");
    private Color lightShadow = UIManager.getColor("lightShadow");
    private Color trackColor = UIManager.getColor("ScrollBar.track");
    private Color highlight = UIManager.getColor("controlHighlight");
    private int scrollBarWidth = 19;

    private JScrollBar scrollbar;
    private boolean mouseover = false;

    public static ComponentUI createUI(JComponent c) {
	return new GtkScrollBarUI();
    }

    public void installUI(JComponent c) {
	super.installUI(c);
	scrollbar = (JScrollBar) c;
    }

    public void uninstallUI(JComponent c) {
	super.uninstallUI(c);
    }

    public GtkScrollBarUI() {
	super();
    }


    public Dimension getPreferredSize(JComponent c) {
	if (scrollbar.getOrientation() == JScrollBar.VERTICAL) {
	    return new Dimension(scrollBarWidth, scrollBarWidth*3+10);
	}
	else {
	    return new Dimension(scrollBarWidth*3+10, scrollBarWidth);
	}
    }

    protected JButton createDecreaseButton(int orientation) {
	return new GtkScrollBarButton(orientation);
    } 

    protected JButton createIncreaseButton(int orientation) {
	return new GtkScrollBarButton(orientation);
    }
  

    public void paintTrack(Graphics g, JComponent c, Rectangle trackBounds)  {        
        g.setColor(trackColor);
        g.fillRect(trackBounds.x, trackBounds.y,
		   trackBounds.width+1, trackBounds.height);
    }


    public void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds)  
    {        

	if(thumbBounds.isEmpty() || !scrollbar.isEnabled() )	{
	    return;
	}

	int w = thumbBounds.width;
	int h = thumbBounds.height;		

	g.translate(thumbBounds.x, thumbBounds.y);
	if (mouseover) {
	    g.setColor(highlight);
	}
	else {
	    g.setColor(c.getBackground());
	}
	g.fillRect(0, 0, w, h);
	GtkGraphicsUtils.drawBorder(g, 0, 0, w, h, 
				    lightShadow, mediumShadow, darkShadow,
				    true);
	g.translate(-thumbBounds.x, -thumbBounds.y);
    }


    protected Dimension getMinimumThumbSize() {
	return new Dimension(scrollBarWidth, scrollBarWidth);
    }

    
    protected TrackListener createTrackListener() {
	return new GtkTrackListener();
    }


    protected Rectangle getThumbBounds() {
	return thumbRect;
    }

    protected boolean isDraggingTrack() {
	return isDragging;
    }

    protected class GtkTrackListener extends TrackListener {

	public void mouseReleased(MouseEvent e) {
	    // WARNING: ONLY COMPILES WITH JIKES
	    super.mouseReleased(e);
	    mouseover = false;
	    scrollbar.repaint();
	}

	public void mouseExited(MouseEvent e) {
	    boolean oldmouseover = mouseover;
	    if (!isDraggingTrack()) {
		mouseover = false;
		if (oldmouseover != mouseover) {
		    scrollbar.repaint();
		}
	    }
	}

	public void mouseMoved(MouseEvent e) {
	    int currentMouseX = e.getX();
	    int currentMouseY = e.getY();
	    boolean oldmouseover = mouseover;
	    mouseover = false;

	    if (getThumbBounds().contains(currentMouseX, currentMouseY) ||
		isDraggingTrack()) {
		mouseover = true;
	    }
	    if (oldmouseover != mouseover) {
		scrollbar.repaint();
	    }
	}
    }
}
