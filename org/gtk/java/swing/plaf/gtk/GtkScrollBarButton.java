/*
 * @(#)GtkScrollBarButton.java	1.2 00/01/12
 *
 * Copyright 1997-2000 Sun Microsystems, Inc. All Rights Reserved.
 * 
 * This software is the proprietary information of Sun Microsystems, Inc.  
 * Use is subject to license terms.
 * 
 */

package org.gtk.java.swing.plaf.gtk;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.plaf.*;
import javax.swing.plaf.basic.BasicArrowButton;

import java.awt.*;
import java.awt.event.*;


public class GtkScrollBarButton extends BasicArrowButton
{
    private Color darkShadow;
    private Color mediumShadow;
    private Color lightShadow;
    private Color background;
    private Color control;
    private Color highlight;
    private boolean mouseover;

    public GtkScrollBarButton(int direction)
    {
        super(direction);

	switch (direction) {
	case NORTH:
	case SOUTH:
	case EAST:
	case WEST:
	    this.direction = direction;
	    break;
	default:
	    throw new IllegalArgumentException("invalid direction");
	}

	setRequestFocusEnabled(false);
	setOpaque(true);
	
	lightShadow = UIManager.getColor("lightShadow");
	mediumShadow = UIManager.getColor("mediumShadow");
	darkShadow = UIManager.getColor("darkShadow");
	control = UIManager.getColor("control");
	background = UIManager.getColor("ScrollBar.track");
	highlight = UIManager.getColor("controlHighlight");
	setBackground(background);
	addMouseListener(new MouseListener() {
                public void mouseClicked(MouseEvent e) {
                }
                public void mousePressed(MouseEvent e) {
                }
                public void mouseReleased(MouseEvent e) {
                }
                public void mouseEntered(MouseEvent e) {
		    mouseover = true;
		    repaint();
                }
                public void mouseExited(MouseEvent e) {
		    mouseover = false;
		    repaint();
                }
	    });
	// setForeground(UIManager.getColor("ScrollBar.foreground"));
    }


    public Dimension getPreferredSize() {
	return new Dimension(12, 12);
    }

    public Dimension getMinimumSize() {
	return getPreferredSize();
    }

    public Dimension getMaximumSize() {
	return getPreferredSize();
    }

    public boolean isFocusTraversable() {
	return false;
    }

    public void paint(Graphics g) 
    {
	int w = getWidth();
	int h = getHeight();

	int[] xs = new int[3];
	int[] ys = new int[3];

	boolean isPressed = getModel().isPressed();

	if (isOpaque()) {
	    g.setColor(background);
	    g.fillRect(0, 0, w, h);
	}

	g.setColor(mouseover?highlight:control);

	switch (direction) {
	case NORTH:
	    xs[0] = 5; ys[0] = 0;
	    xs[1] = 0; ys[1] = 10;
	    xs[2] = 10; ys[2] = 10;
	    if (isPressed) {
		g.setColor(mediumShadow);
		g.drawLine(0, 10, 5, 0);
		g.setColor(darkShadow);
		g.drawLine(1, 10, 5, 1);
		g.setColor(lightShadow);
		g.drawLine(2, 10, 8, 10);
		g.drawLine(6, 1, 10, 10);
	    } 
	    else {
		g.fillPolygon(xs, ys, 3);
		g.setColor(lightShadow);
		g.drawLine(0, 10, 5, 0);
		g.setColor(mediumShadow);
		g.drawLine(2, 9, 9, 9);
		g.drawLine(6, 3, 9, 10);
		g.setColor(darkShadow);
		g.drawLine(6, 1, 10, 10);
		g.drawLine(2, 10, 8, 10);
	    }	    
	    break;

	case SOUTH:
	    xs[0] = 5; ys[0] = 10;
	    xs[1] = 0; ys[1] = 0;
	    xs[2] = 10; ys[2] = 0;
	    if (isPressed) {
		g.setColor(darkShadow);
		g.drawLine(4, 7, 1, 0);
		g.drawLine(2, 1, 8, 1);
		g.setColor(mediumShadow);
		g.drawLine(4, 8, 0, 0);
		g.drawLine(2, 0, 8, 0);
		g.setColor(lightShadow);
		g.drawLine(5, 10, 10, 0);
	    } 
	    else {
		g.fillPolygon(xs, ys, 3);
		g.setColor(lightShadow);
		g.drawLine(4, 8, 0, 0);
		g.drawLine(2, 0, 8, 0);
		g.setColor(mediumShadow);
		g.drawLine(9, 0, 5, 9);
		g.setColor(darkShadow);
		g.drawLine(5, 10, 10, 0);
	    }
	    break;

	case EAST:
	    xs[0] = 10; ys[0] = 5;
	    xs[1] = 0; ys[1] = 0;
	    xs[2] = 0; ys[2] = 10;
	    if (isPressed) {
		g.setColor(mediumShadow);
		g.drawLine(0, 0, 0, 9);
		g.drawLine(8, 4, 1, 1);
		g.setColor(lightShadow);
		g.drawLine(10, 5, 0, 10);
		g.setColor(darkShadow);
		g.drawLine(1, 1, 1, 8);
	    }
	    else {
		g.fillPolygon(xs, ys, 3);
		g.setColor(lightShadow);
		g.drawLine(0, 0, 0, 9);
		g.drawLine(8, 4, 2, 1);
		g.setColor(darkShadow);
		g.drawLine(10, 5, 0, 10);
	    }
	    break;

	case WEST:
	    xs[0] = 0; ys[0] = 5;
	    xs[1] = 10; ys[1] = 0;
	    xs[2] = 10; ys[2] = 10;
	    if (isPressed) {
		g.setColor(mediumShadow);
		g.drawLine(10, 0, 0, 5);
		g.setColor(darkShadow);
		g.drawLine(1, 5, 10, 1);
		g.setColor(lightShadow);
		g.drawLine(1, 6, 8, 9);
		g.drawLine(10, 2, 10, 10);
	    }
	    else {
		g.fillPolygon(xs, ys, 3);
		g.setColor(lightShadow);
		g.drawLine(10, 0, 0, 5);
		g.setColor(mediumShadow);
		g.drawLine(2, 6, 9, 9);
		g.drawLine(9, 2, 9, 10);
		g.setColor(darkShadow);
		g.drawLine(1, 6, 8, 9);
		g.drawLine(10, 2, 10, 10);
	    }
	    break;
	}
    }
}


