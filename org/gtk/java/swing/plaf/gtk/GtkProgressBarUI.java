package org.gtk.java.swing.plaf.gtk;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.plaf.*;
import java.io.Serializable;

import javax.swing.plaf.basic.BasicProgressBarUI;


/**
 * @version 1.8 08/28/98
 * @author Michael C. Albers
 */
public class GtkProgressBarUI extends BasicProgressBarUI 
{
    static protected Color lightShadow = UIManager.getColor("lightShadow");
    static protected Color mediumShadow = UIManager.getColor("mediumShadow");
    static protected Color darkShadow = UIManager.getColor("darkShadow");
    static protected Color highlight = UIManager.getColor("controlHighlight");

    /**
     * Creates the ProgressBar's UI
     */
    public static ComponentUI createUI(JComponent x) {
	return new GtkProgressBarUI();
    }


    public void paint(Graphics g, JComponent c) {
        BoundedRangeModel model = progressBar.getModel();
        
        int barRectX = 0;
        int barRectY = 0;
        int barRectWidth = progressBar.getWidth();
        int barRectHeight = progressBar.getHeight();
        Insets b = progressBar.getInsets(); // area for border
        barRectX += b.left;
        barRectY += b.top;
        barRectWidth -= (b.right + barRectX);
        barRectHeight -= (b.bottom + barRectY);
	int current;
        // a cell and its spacing
        int increment = getCellLength() + getCellSpacing();
        // amount of progress to draw
        int amountFull = getAmountFull(b, barRectWidth, barRectHeight);
        
        g.setColor(highlight);
        if (progressBar.getOrientation() == JProgressBar.HORIZONTAL) {
            // draw the cells
            if (getCellSpacing() == 0 && amountFull > 0) {
                // draw one big Rect because there is no space between cells
                g.fillRect(barRectX, barRectY,
                           barRectX-b.left+amountFull, barRectHeight);
		GtkGraphicsUtils.drawBorder(g, barRectX, barRectY,
					    barRectX-b.left+amountFull, barRectHeight,
					    lightShadow, mediumShadow, darkShadow,
					    true);
            } 
	    else { // draw each individual cells
                // the largest number to draw a cell at
                int max = barRectX + amountFull;
                
                for (current = barRectX;
                     current < max;
                     current += increment) {
                    g.fillRect(current, barRectY,
                               getCellLength(), barRectHeight);
		    GtkGraphicsUtils.drawBorder(g, current, barRectY,
					    getCellLength(), barRectHeight,
					    lightShadow, mediumShadow, darkShadow,
					    true);
                }
            }
        }
	else { // VERTICAL
            // draw the cells
            if (getCellSpacing() == 0 && amountFull > 0) {
                // draw one big Rect because there is no space between cells
                g.fillRect(barRectX, barRectHeight-amountFull+b.top,
                           barRectWidth, amountFull);
		GtkGraphicsUtils.drawBorder(g, barRectX, barRectHeight-amountFull+b.top,
					    barRectWidth, amountFull,
					    lightShadow, mediumShadow, darkShadow,
					    true);
            }
	    else { // draw each individual cells
                // the smallest number to draw a cell at
                //  that is, the number at the top
                int min = barRectHeight - amountFull;
                
                for (current = barRectHeight-getCellLength()+getCellSpacing();
                     current >= min;
                     current -= increment)
		    {
			g.fillRect(barRectX, current,
				   barRectWidth, getCellLength());
			GtkGraphicsUtils.drawBorder(g, barRectX, current,
					    barRectWidth, getCellLength(),
					    lightShadow, mediumShadow, darkShadow,
					    true);
		    }
            }
        }
        
        // Deal with possible text painting
	if (progressBar.isStringPainted()) {
            paintString(g, barRectX, barRectY-2,
                        barRectWidth, barRectHeight,
                        amountFull, b);
        }
    }

}
