package org.gtk.java.swing.plaf.gtk;


import javax.swing.*;
import java.awt.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import javax.swing.plaf.basic.BasicSplitPaneDivider;


/**
 * @version 1.50 04/22/99
 * @author Scott Violet
 * @author Steve Wilson
 * @author Ralph Kar
 */
public class GtkSplitPaneUI extends BasicSplitPaneUI
{
    public GtkSplitPaneUI() {
	super();
    }

    public static ComponentUI createUI(JComponent x) {
        return new GtkSplitPaneUI();
    }

    protected void installDefaults(){ 
	super.installDefaults();
	((GtkSplitPaneDivider)divider).setGtkSplitPaneUI(this);
    }

    public BasicSplitPaneDivider createDefaultDivider() {
        return new GtkSplitPaneDivider(this);
    }


    protected int getDividerBorderSize() {
	return 0;
    }

    public void finishedPaintingChildren(JSplitPane jc, Graphics g) {
	Dimension size = splitPane.getSize();
        if(jc == splitPane && getLastDragLocation() != -1 &&
           !isContinuousLayout() && !draggingHW) {
	    int center = getLastDragLocation();

            g.setColor(Color.black);
            if(getOrientation() == JSplitPane.HORIZONTAL_SPLIT) {
		g.drawLine(center, 0, center, size.height-1);
            }
	    else {
		g.drawLine(0, center, size.width-1, center);
            }
        }
	int x = divider.getLocation().x;
	int y = divider.getLocation().y;
	if (getOrientation() == JSplitPane.HORIZONTAL_SPLIT) {
	    GtkSplitPaneDivider.paintTop(g, x);
	    GtkSplitPaneDivider.paintBottom(g, x, size.height-1);
	}
	else {
	    GtkSplitPaneDivider.paintLeft(g, y);
	    GtkSplitPaneDivider.paintRight(g, y, size.width-1);
	}
    }

}
