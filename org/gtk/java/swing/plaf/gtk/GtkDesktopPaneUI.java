/*
 * @(#)GtkDesktopPaneUI.java	1.2 00/01/12
 *
 * Copyright 1997-2000 Sun Microsystems, Inc. All Rights Reserved.
 * 
 * This software is the proprietary information of Sun Microsystems, Inc.  
 * Use is subject to license terms.
 * 
 */

package org.gtk.java.swing.plaf.gtk;

import javax.swing.*;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.plaf.*;
import java.io.Serializable;

/**
 * <p>
 * <strong>Warning:</strong>
 * Serialized objects of this class will not be compatible with
 * future Swing releases.  The current serialization support is appropriate
 * for short term storage or RMI between applications running the same
 * version of Swing.  A future release of Swing will provide support for
 * long term persistence.
 *
 * @version 1.16 08/28/98
 * @author David Kloba
 */
public class GtkDesktopPaneUI extends javax.swing.plaf.basic.BasicDesktopPaneUI
{

/// DesktopPaneUI methods
    public static ComponentUI createUI(JComponent d)    {
        return new GtkDesktopPaneUI();
    }

    public GtkDesktopPaneUI() {
    }

    protected void installDesktopManager() {
	if(desktop.getDesktopManager() == null) {
	    desktopManager = new GtkDesktopManager();
	    desktop.setDesktopManager(desktopManager);
	}
    }

    public Insets getInsets(JComponent c) {return new Insets(0,0,0,0);}

////////////////////////////////////////////////////////////////////////////////////
///  DragPane class
////////////////////////////////////////////////////////////////////////////////////
    private class DragPane extends JComponent {
	public void paint(Graphics g) {
	    g.setColor(Color.darkGray);
	    g.drawRect(0, 0, getWidth()-1, getHeight()-1);
	}
    }

////////////////////////////////////////////////////////////////////////////////////
///  GtkDesktopManager class
////////////////////////////////////////////////////////////////////////////////////
    private class GtkDesktopManager extends DefaultDesktopManager implements Serializable {
        JComponent dragPane;
        boolean usingDragPane;

    // PENDING(klobad) this should be optimized
    public void setBoundsForFrame(JComponent f, int newX, int newY, 
			int newWidth, int newHeight) {
	if(!usingDragPane) {
            boolean didResize;
            didResize = (f.getWidth() != newWidth || f.getHeight() != newHeight);
	    Rectangle r = f.getBounds();
	    f.setBounds(newX, newY, newWidth, newHeight);		
	    SwingUtilities.computeUnion(newX, newY, newWidth, newHeight, r);
	    f.getParent().repaint(r.x, r.y, r.width, r.height);
	    if(didResize) {
	        f.validate();
  	    }
	} else {
	    Rectangle r = dragPane.getBounds();
	    dragPane.setBounds(newX, newY, newWidth, newHeight);		
	    SwingUtilities.computeUnion(newX, newY, newWidth, newHeight, r);
	    dragPane.getParent().repaint(r.x, r.y, r.width, r.height);
	}
    }

    public void beginDraggingFrame(JComponent f) {	
	usingDragPane = false;
	if(f.getParent() instanceof JLayeredPane) {
	    if(dragPane == null)
		dragPane = new DragPane();
	    JLayeredPane p = (JLayeredPane)f.getParent();
	    p.setLayer(dragPane, Integer.MAX_VALUE);
	    dragPane.setBounds(f.getX(), f.getY(), f.getWidth(), f.getHeight());
	    p.add(dragPane);
	    usingDragPane = true;
	}
    }

    public void dragFrame(JComponent f, int newX, int newY) {
	setBoundsForFrame(f, newX, newY, f.getWidth(), f.getHeight());
    }

    public void endDraggingFrame(JComponent f) {
	if(usingDragPane) {
	    JLayeredPane p = (JLayeredPane)f.getParent();
	    p.remove(dragPane);
	    usingDragPane = false;
            setBoundsForFrame(f, dragPane.getX(), dragPane.getY(), 
				dragPane.getWidth(), dragPane.getHeight());
	}
    }

    public void beginResizingFrame(JComponent f, int direction) {
	usingDragPane = false;
	if(f.getParent() instanceof JLayeredPane) {
	    if(dragPane == null)
		dragPane = new DragPane();
	    JLayeredPane p = (JLayeredPane)f.getParent();
	    p.setLayer(dragPane, Integer.MAX_VALUE);
	    dragPane.setBounds(f.getX(), f.getY(), 
				f.getWidth(), f.getHeight());
	    p.add(dragPane);
	    usingDragPane = true;
	}
    }

    public void resizeFrame(JComponent f, int newX, int newY, 
				int newWidth, int newHeight) {
	setBoundsForFrame(f, newX, newY, newWidth, newHeight);
    }

    public void endResizingFrame(JComponent f) {
	if(usingDragPane) {
	    JLayeredPane p = (JLayeredPane)f.getParent();
	    p.remove(dragPane);
	    usingDragPane = false;
            setBoundsForFrame(f, dragPane.getX(), dragPane.getY(), 
				dragPane.getWidth(), dragPane.getHeight());
	}
    }

    } /// END of GtkDesktopManager

}

