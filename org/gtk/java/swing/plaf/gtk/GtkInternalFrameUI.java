package org.gtk.java.swing.plaf.gtk;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import java.util.EventListener;

import javax.swing.plaf.basic.*;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.border.*;
import javax.swing.plaf.*;


/**
 * @version 1.14 10/30/98
 * @author Tom Ball
 */
public class GtkInternalFrameUI extends BasicInternalFrameUI {

    Color color;
    Color highlight;
    Color shadow;
    GtkInternalFrameTitlePane titlePane;

    protected KeyStroke closeMenuKey;


    /////////////////////////////////////////////////////////////////////////////
    // ComponentUI Interface Implementation methods
    /////////////////////////////////////////////////////////////////////////////
    public static ComponentUI createUI(JComponent w)    {
        return new GtkInternalFrameUI((JInternalFrame)w);
    }

    public GtkInternalFrameUI(JInternalFrame w)   {
        super(w);
    }
            
    public void installUI(JComponent c)   {
        super.installUI(c);
        setColors((JInternalFrame)c);
    }   

    protected void installDefaults() {
	Border frameBorder = frame.getBorder();
        if (frameBorder == null || frameBorder instanceof UIResource) {
            frame.setBorder(new GtkBorders.InternalFrameBorder(frame));
        }	
    }


    protected void installKeyboardActions(){
      super.installKeyboardActions();
      // we use JPopup in our TitlePane so need escape support
      closeMenuKey = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
    }


    protected void uninstallDefaults() {
        LookAndFeel.uninstallBorder(frame);
    }

    private JInternalFrame getFrame(){
      return frame;
    }

    public JComponent createNorthPane(JInternalFrame w) {
        titlePane = new GtkInternalFrameTitlePane(w);
        return titlePane;
    }

    public Dimension getMaximumSize(JComponent x) {
        return Toolkit.getDefaultToolkit().getScreenSize();
    }

    protected void uninstallKeyboardActions(){
      super.uninstallKeyboardActions();
      if (isKeyBindingRegistered()){
	frame.unregisterKeyboardAction(closeMenuKey);
	frame.getDesktopIcon().unregisterKeyboardAction(closeMenuKey);
      }
    }
    
    protected void setupMenuOpenKey(){
	frame.registerKeyboardAction(new ActionListener(){
	    public void actionPerformed(ActionEvent e){
		titlePane.showSystemMenu();
	    }
	    public boolean isEnabled(){
		return isKeyBindingActive();
	    }
	},
	    openMenuKey,
	    JComponent.WHEN_IN_FOCUSED_WINDOW);

	frame.getDesktopIcon().registerKeyboardAction(new ActionListener(){
	    public void actionPerformed(ActionEvent e){
	      JInternalFrame.JDesktopIcon icon = getFrame().getDesktopIcon();
	      GtkDesktopIconUI micon = (GtkDesktopIconUI)icon.getUI();
	      micon.showSystemMenu();
	    }
	    public boolean isEnabled(){
		return isKeyBindingActive();
	    }
	},
	    openMenuKey,
	    JComponent.WHEN_IN_FOCUSED_WINDOW);

    }       


    protected void setupMenuCloseKey(){
      	frame.registerKeyboardAction(new ActionListener(){
	  public void actionPerformed(ActionEvent e){
	    titlePane.hideSystemMenu();
	  }
	  public boolean isEnabled(){
	    return isKeyBindingActive();
	  }
	},
	  closeMenuKey,
	  JComponent.WHEN_IN_FOCUSED_WINDOW);

      	frame.getDesktopIcon().registerKeyboardAction(new ActionListener(){
	  public void actionPerformed(ActionEvent e){
	    JInternalFrame.JDesktopIcon icon = getFrame().getDesktopIcon();
	    GtkDesktopIconUI micon = (GtkDesktopIconUI)icon.getUI();
	    micon.hideSystemMenu();
	  }
	  public boolean isEnabled(){
	    return isKeyBindingActive();
	  }
	},
	  closeMenuKey,
	  JComponent.WHEN_IN_FOCUSED_WINDOW);


    }

    /** This method is called when the frame becomes selected.
      */
    protected void activateFrame(JInternalFrame f) {
        super.activateFrame(f);
        setColors(f);
    }
    /** This method is called when the frame is no longer selected.
      */
    protected void deactivateFrame(JInternalFrame f) {
        setColors(f);
        super.deactivateFrame(f);
    }

    void setColors(JInternalFrame frame) {
        if (frame.isSelected()) {
            color = UIManager.getColor("activeCaptionBorder");
        } else {
            color = UIManager.getColor("inactiveCaptionBorder");
        }
        highlight = color.brighter();
        shadow = color.darker().darker();
        titlePane.setColors(color, highlight, shadow);
    }


}
