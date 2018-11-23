package org.gtk.java.swing.plaf.gtk;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.*;
import java.io.Serializable;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import javax.swing.plaf.*;
import javax.swing.plaf.basic.BasicMenuBarUI;
import javax.swing.plaf.basic.*;

/**
 * 1.33 08/28/98
 * @author Georges Saab
 * @author Rich Schiavi
 */

public class GtkMenuBarUI extends BasicMenuBarUI
{

    public static ComponentUI createUI(JComponent x) {
	return new GtkMenuBarUI();
    }

} // end class

