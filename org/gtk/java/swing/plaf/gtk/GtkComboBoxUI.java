package org.gtk.java.swing.plaf.gtk;

import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.*;
import javax.swing.border.*;
import javax.swing.plaf.basic.*;
import java.io.Serializable;
import java.awt.event.*;


public class GtkComboBoxUI extends BasicComboBoxUI implements Serializable {
    Icon arrowIcon;
    static final int HORIZ_MARGIN = 3;

    public static ComponentUI createUI(JComponent c) {
        return new GtkComboBoxUI();
    }  

    public void installUI(JComponent c) {
        super.installUI(c);
        arrowIcon = new GtkComboBoxArrowIcon(UIManager.getColor("controlHighlight"),
                                               UIManager.getColor("controlShadow"),
                                               UIManager.getColor("control"));

        Runnable initCode = new Runnable() {
            public void run(){
                if (gtkGetEditor() != null) {
                    gtkGetEditor().setBackground(UIManager.getColor("text"));
                }
            }
        };

        SwingUtilities.invokeLater(initCode); 
    }

    public Dimension getMinimumSize(JComponent c) {
        if (!isMinimumSizeDirty) {
            return new Dimension(cachedMinimumSize);
        }
        Dimension size;
        Insets insets = getInsets();
        size = getDisplaySize();
        size.height += insets.top + insets.bottom;
        int buttonSize = iconAreaWidth();
        size.width +=  insets.left + insets.right + buttonSize;

        cachedMinimumSize.setSize(size.width, size.height); 
        isMinimumSizeDirty = false;

        return size;
    }

    protected ComboPopup createPopup() {
        return new GtkComboPopup(comboBox);
    }

    /**
     * This inner class is marked &quot;public&quot; due to a compiler bug.
     * This class should be treated as a &quot;protected&quot; inner class.
     * Instantiate it only within subclasses of <FooUI>.
     */    	     
    public class GtkComboPopup extends BasicComboPopup {
        JComboBox cBox;

        public GtkComboPopup(JComboBox comboBox) {
            super(comboBox);
            cBox = comboBox;
        }

        public MouseMotionListener createListMouseMotionListener() {
	    return new MouseMotionAdapter() {};
        }

        public KeyListener createKeyListener() {
            return new InvocationKeyHandler();
        }

      /**
       * This inner class is marked &quot;public&quot; due to a compiler bug.
       * This class should be treated as a &quot;protected&quot; inner class.
       * Instantiate it only within subclasses of <FooUI>.
       */    	     
        public class InvocationKeyHandler extends BasicComboPopup.InvocationKeyHandler {
            public void keyReleased(KeyEvent e) {
                if (!cBox.isEditable()) {
                    if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                        if (!isVisible()) {
                            show();
                        }
                    }
                    else {
                        super.keyReleased(e);
                    }
                }
                else {
                    if (e.getKeyCode() == KeyEvent.VK_UP ||
                         e.getKeyCode() == KeyEvent.VK_DOWN) {
                        if (!isVisible()) {
                            show();
                        }
                    }
                }
            }
        }
    }

    protected void installComponents() {
        if (comboBox.isEditable()) {
            addEditor();
        }

        comboBox.add(currentValuePane);
    }

    protected void uninstallComponents() {
        removeEditor();
        comboBox.removeAll();
    }

    public void paint(Graphics g, JComponent c) {
        boolean hasFocus = comboBox.hasFocus();
        Rectangle r;

        g.setColor(UIManager.getColor("ComboBox.control"));
        g.fillRect(0,0,c.getWidth(),c.getHeight());

        if (!comboBox.isEditable()) {
            r = rectangleForCurrentValue();
            paintCurrentValue(g,r,hasFocus);
        }
        r = rectangleForArrowIcon();
        arrowIcon.paintIcon(c,g,r.x,r.y);
        if (!comboBox.isEditable()) {
            Border border = comboBox.getBorder();
            Insets in;
            if (border != null) {
                in = border.getBorderInsets(comboBox);
            }
            else {
                in = new Insets(0, 0, 0, 0);
            }
            // Draw the separation
            r.x -= (HORIZ_MARGIN + 2);
            r.y = in.top;
            r.width = 1;
            r.height = comboBox.getBounds().height - in.bottom - in.top;
            g.setColor(UIManager.getColor("controlShadow"));
            g.fillRect(r.x,r.y,r.width,r.height);
            r.x++;
            g.setColor(UIManager.getColor("controlHighlight"));
            g.fillRect(r.x,r.y,r.width,r.height);
        }
    }

    public void paintCurrentValue(Graphics g,Rectangle bounds,boolean hasFocus) {
        ListCellRenderer renderer = comboBox.getRenderer();
        Component c;
        Dimension d;
        c = renderer.getListCellRendererComponent(listBox, comboBox.getSelectedItem(), -1, false, false);
        c.setFont(comboBox.getFont());
        if (comboBox.isEnabled()) {
            c.setForeground(comboBox.getForeground());
            c.setBackground(comboBox.getBackground());
        }
        else {
            c.setForeground(UIManager.getColor("ComboBox.disabledForeground"));
            c.setBackground(UIManager.getColor("ComboBox.disabledBackground"));
        }
        d  = c.getPreferredSize();
        currentValuePane.paintComponent(g,c,comboBox,bounds.x,bounds.y,
                                        bounds.width,d.height);
    }

    protected Rectangle rectangleForArrowIcon() {
        Rectangle b = comboBox.getBounds();
        Border border = comboBox.getBorder();
        Insets in;
        if (border != null) {
            in = border.getBorderInsets(comboBox);
        }
        else {
            in = new Insets(0, 0, 0, 0);
        }
        b.x = in.left;
        b.y = in.top;
        b.width -= (in.left + in.right);
        b.height -= (in.top + in.bottom);

        b.x = b.x + b.width - HORIZ_MARGIN - arrowIcon.getIconWidth();
        b.y = b.y + (b.height - arrowIcon.getIconHeight()) / 2;
        b.width = arrowIcon.getIconWidth();
        b.height = arrowIcon.getIconHeight();
        return b;
    }

    protected Rectangle rectangleForCurrentValue() {
        int width = comboBox.getWidth();
        int height = comboBox.getHeight();
        Insets insets = getInsets();
        return new Rectangle(insets.left, insets.top,
                             (width - (insets.left + insets.right)) - iconAreaWidth(),
                             height - (insets.top + insets.bottom));
    }

    public int iconAreaWidth() {
        if (comboBox.isEditable())
            return arrowIcon.getIconWidth() + (2 * HORIZ_MARGIN);
        else
            return arrowIcon.getIconWidth() + (3 * HORIZ_MARGIN) + 2;
    }

    public void configureEditor() {
        super.configureEditor();
        editor.setBackground(UIManager.getColor("text"));
    }

    protected LayoutManager createLayoutManager() {
        return new ComboBoxLayoutManager();
    }

    Component gtkGetEditor() {
        return editor;
    }

    /**
     * This inner class is marked &quot;public&quot; due to a compiler bug.
     * This class should be treated as a &quot;protected&quot; inner class.
     * Instantiate it only within subclasses of <FooUI>.
     */    	     
    public class ComboBoxLayoutManager extends BasicComboBoxUI.ComboBoxLayoutManager {
        public void layoutContainer(Container parent) {
            if (gtkGetEditor() != null) {
                Rectangle cvb = rectangleForCurrentValue();
                cvb.x += 1;
                cvb.y += 1;
                cvb.width -= 1;
                cvb.height -= 2;
                gtkGetEditor().setBounds(cvb);
            }
        }    
    }

    static class GtkComboBoxArrowIcon implements Icon, Serializable {
        private Color lightShadow;
        private Color darkShadow;
        private Color fill;

        public GtkComboBoxArrowIcon(Color lightShadow, Color darkShadow, Color fill) {
            this.lightShadow = lightShadow;
            this.darkShadow = darkShadow;
            this.fill = fill;
        }


        public void paintIcon(Component c, Graphics g, int xo, int yo) {
            int w = getIconWidth();
            int h = getIconHeight();

            g.setColor(lightShadow);
            g.drawLine(xo, yo, xo+w-1, yo);
            g.drawLine(xo, yo+1, xo+w-3, yo+1);
            g.setColor(darkShadow);
            g.drawLine(xo+w-2, yo+1, xo+w-1, yo+1);

            for (int x = xo+1, y = yo+2, dx = w-6; y+1 < yo+h; y += 2) {
                g.setColor(lightShadow);
                g.drawLine(x, y,   x+1, y);
                g.drawLine(x, y+1, x+1, y+1);
                if (dx > 0) {
                    g.setColor(fill);
                    g.drawLine(x+2, y,   x+1+dx, y);
                    g.drawLine(x+2, y+1, x+1+dx, y+1);
                }
                g.setColor(darkShadow);
                g.drawLine(x+dx+2, y,   x+dx+3, y);
                g.drawLine(x+dx+2, y+1, x+dx+3, y+1);
                x += 1;
                dx -= 2;
            }

            g.setColor(darkShadow);
            g.drawLine(xo+(w/2), yo+h-1, xo+(w/2), yo+h-1); 

        }

        public int getIconWidth() {
            return 11;
        }

        public int getIconHeight() {
            return 11;
        }
    }

    protected void selectNextPossibleValue() {
        super.selectNextPossibleValue();
    }

    protected void selectPreviousPossibleValue() {
        super.selectPreviousPossibleValue();
    }

    /**
     * This method is here as a workaround for a bug in the javac compiler.
     */
    JComboBox gtkGetComboBox() {
        return comboBox;
    }

    /**
     * This method is here as a workaround for a bug in the javac compiler.
     */
    GtkComboBoxUI gtkGetUI() {
        return this;
    }

    protected void installKeyboardActions() {
        super.installKeyboardActions();

        ActionListener downAction = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (gtkGetComboBox().isEnabled() && isPopupVisible(gtkGetComboBox())) {
                    gtkGetUI().selectNextPossibleValue();
                }
            }
        };

        gtkGetComboBox().registerKeyboardAction(downAction,
						 KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0),
						 JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        ActionListener upAction = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (gtkGetComboBox().isEnabled() && isPopupVisible(gtkGetComboBox())) {
                    gtkGetUI().selectPreviousPossibleValue();
                }
            }
        };

        gtkGetComboBox().registerKeyboardAction(upAction,
						KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0)
						,JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    protected void uninstallKeyboardActions() {
        super.uninstallKeyboardActions();
        comboBox.unregisterKeyboardAction(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN,0));
        comboBox.unregisterKeyboardAction(KeyStroke.getKeyStroke(KeyEvent.VK_UP,0));
    }
}

