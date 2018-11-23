package org.gtk.java.swing.plaf.gtk;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.plaf.*;

import javax.swing.plaf.basic.BasicSliderUI;

/**
 * @version 1.16 08/28/98
 * @author Jeff Dinkins
 */
public class GtkSliderUI extends BasicSliderUI {

    static final Dimension PREFERRED_HORIZONTAL_SIZE = new Dimension(164, 15);
    static final Dimension PREFERRED_VERTICAL_SIZE = new Dimension(15, 164);

    static final Dimension MINIMUM_HORIZONTAL_SIZE = new Dimension(43, 15);
    static final Dimension MINIMUM_VERTICAL_SIZE = new Dimension(15, 43);

    private Color lightShadow = UIManager.getColor("lightShadow");
    private Color mediumShadow = UIManager.getColor("mediumShadow");
    private Color darkShadow = UIManager.getColor("darkShadow");
    private Color background = UIManager.getColor("Slider.background");

    public GtkSliderUI(JSlider b)   {
        super(b);
    }

    public static ComponentUI createUI(JComponent b)    {
        return new GtkSliderUI((JSlider)b);
    }

    public Dimension getPreferredHorizontalSize() {
        return PREFERRED_HORIZONTAL_SIZE;
    }


    public Dimension getPreferredVerticalSize() {
        return PREFERRED_VERTICAL_SIZE;
    }


    public Dimension getMinimumHorizontalSize() {
        return MINIMUM_HORIZONTAL_SIZE;
    }


    public Dimension getMinimumVerticalSize() {
        return MINIMUM_VERTICAL_SIZE;
    }


    protected Dimension getThumbSize() {
        if (slider.getOrientation() == JSlider.HORIZONTAL) {
	    return new Dimension(31, 11);
	}
	else {
	    return new Dimension(11, 31);
	}
    }


    public void paintFocus(Graphics g)  {        
    }


    public void paintTrack(Graphics g) {        
	//        g.setColor(windowDark);
	g.setColor(background);
        g.fillRect(trackRect.x, trackRect.y, trackRect.width, trackRect.height+1);
    }
  

    public void paintThumb(Graphics g) {
        Rectangle knobBounds = thumbRect;

        int x = knobBounds.x;
        int y = knobBounds.y;       
        int w = knobBounds.width;
        int h = knobBounds.height;      

        if (slider.isEnabled()) {
            g.setColor(slider.getForeground());
        }
        else {
            g.setColor(slider.getForeground().darker());
        }

        if (slider.getOrientation() == JSlider.HORIZONTAL) {
            g.translate(x, y-1);
	    g.fillRect(0, 1, w, h-1);
	    g.translate(-x, -(y-1));
	    GtkGraphicsUtils.drawBorder(g, x, y, w, h, 
					lightShadow, mediumShadow, darkShadow,
					true);
	    g.translate(x, y);
	    g.setColor(mediumShadow);
	    g.drawLine(w/2, 2, w/2+1, 2);
	    g.drawLine(w/2, 2, w/2, 7);
	    g.setColor(lightShadow);
	    g.drawLine(w/2, 8, w/2+1, 8);
	    g.drawLine(w/2+1, 3, w/2+1, 8);
	    g.translate(-x, y);
        }
        else {
            g.translate(x-1, 0);
            g.fillRect(1, y, w-1, h);
            g.translate(-(x-1), 0);
	    GtkGraphicsUtils.drawBorder(g, x, y, w, h, 
					lightShadow, mediumShadow, darkShadow,
					true);
	    g.translate(x, y);
	    g.setColor(mediumShadow);
	    g.drawLine(2, h/2, 2, h/2+1);
	    g.drawLine(2, h/2, 7, h/2);
	    g.setColor(lightShadow);
	    g.drawLine(8, h/2, 8, h/2+1);
	    g.drawLine(3, h/2+1, 8, h/2+1);
	    g.translate(-x, y);
        }
    }
   
    protected void installKeyboardActions(JSlider slider) {
        super.installKeyboardActions(slider);
        slider.unregisterKeyboardAction(KeyStroke.getKeyStroke(KeyEvent.VK_PAGE_UP, 0));
        slider.unregisterKeyboardAction(KeyStroke.getKeyStroke(KeyEvent.VK_PAGE_DOWN, 0));

        slider.registerKeyboardAction(new ActionScroller(slider, POSITIVE_SCROLL, true),
                                      KeyStroke.getKeyStroke(KeyEvent.VK_PAGE_UP, Event.CTRL_MASK), JComponent.WHEN_FOCUSED);

        slider.registerKeyboardAction(new ActionScroller(slider, NEGATIVE_SCROLL, true),
                                      KeyStroke.getKeyStroke(KeyEvent.VK_PAGE_DOWN, Event.CTRL_MASK), JComponent.WHEN_FOCUSED);
    }

    protected void uninstallKeyboardActions(JSlider slider) {
        slider.unregisterKeyboardAction(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0));
        slider.unregisterKeyboardAction(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0));
        slider.unregisterKeyboardAction(KeyStroke.getKeyStroke(KeyEvent.VK_PAGE_DOWN, Event.CTRL_MASK));
        slider.unregisterKeyboardAction(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0));
        slider.unregisterKeyboardAction(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0));
        slider.unregisterKeyboardAction(KeyStroke.getKeyStroke(KeyEvent.VK_PAGE_UP, Event.CTRL_MASK));
        slider.unregisterKeyboardAction(KeyStroke.getKeyStroke(KeyEvent.VK_HOME, 0));
        slider.unregisterKeyboardAction(KeyStroke.getKeyStroke(KeyEvent.VK_END, 0) );
    }
}
