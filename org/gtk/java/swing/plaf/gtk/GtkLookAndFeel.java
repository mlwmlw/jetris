package org.gtk.java.swing.plaf.gtk;

import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.util.*;

import java.lang.reflect.*;

import javax.swing.*;
import javax.swing.plaf.*;
import javax.swing.border.*;
import javax.swing.text.JTextComponent;
import javax.swing.text.DefaultEditorKit;

import javax.swing.plaf.basic.BasicLookAndFeel;
import javax.swing.plaf.basic.BasicBorders;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import javax.swing.plaf.basic.BasicComboBoxEditor;


public class GtkLookAndFeel extends BasicLookAndFeel {

    public String getName() {
        return "GTK";
    }

    public String getID() {
        return "GTK";
    }

    public String getDescription() {
        return "The GTK Look and Feel";
    }

    public boolean isNativeLookAndFeel() {
        String osName = System.getProperty("os.name");
        return (osName != null) && (osName.indexOf("Linux") != -1);
    }

    public boolean isSupportedLookAndFeel() {
        return true;
    }


    protected void initSystemColorDefaults(UIDefaults table)
    {
        String[] defaultSystemColors = {
	    "desktop", "#EAEAEA", /* Color of the desktop background */
            "activeCaption", "#000080", /* Color for captions (title bars) when they are active. */
	    "activeCaptionText", "#FFFFFF", /* Text color for text in captions (title bars). */
	    "activeCaptionBorder", "#B24D7A", /* Border color for caption (title bar) window borders. */
	    "inactiveCaption", "#AEB2C3", /* Color for captions (title bars) when not active. */
	    "inactiveCaptionText", "#000000", /* Text color for text in inactive captions (title bars). */
	    "inactiveCaptionBorder", "#A0B0C0", /* Border color for inactive caption (title bar) window borders. */
	    "window", "#D6D6D6", /* Default color for the interior of windows */
	    "windowLight", "#EAEAEA",
	    "windowDark", "#C3C3C3",

	    "menu", "#D6D6D6", /* ??? */
	    "menuText", "#000000", /* ??? */
	    "text", "#FFFFFF", /* Text background color */
	    "textText", "#000000", /* Text foreground color */
            "textHighlight", "#00009C", /* Text background color when selected */
	    "textHighlightText", "#FFFFFF", /* Text color when selected */
	    "textInactiveText", "#808080", /* Text color when disabled */

	    "control", "#D6D6D6",
	    "controlText", "#000000", // Default color for text in controls
	    "controlDark", "#C3C3C3",
	    "controlHighlight", "#EAEAEA", // Highlight color for controls
            "controlShadow", "#63656F", /* Shadow color for controls */

	    "info", "#FFF7E9", /* ??? */
	    "unselectedTabBG", "#C3C3C3",
	    "infoText", "#000000",  /* ??? */

	    "lightShadow", "#FFFFFF",
	    "mediumShadow", "#969696",
	    "darkShadow", "#000000",
	    "focus", "#000000"
        };

        loadSystemColors(table, defaultSystemColors, false/*is1dot2*/);
    }


    protected void initClassDefaults(UIDefaults table)
    {
        super.initClassDefaults(table);
        String gtkPackageName = "org.gtk.java.swing.plaf.gtk.";

        Object[] uiDefaults = {
	    "ButtonUI", gtkPackageName + "GtkButtonUI",
	    "CheckBoxUI", gtkPackageName + "GtkCheckBoxUI",
            "DirectoryPaneUI", gtkPackageName + "GtkDirectoryPaneUI",
	    "FileChooserUI", gtkPackageName + "GtkFileChooserUI",
	    "LabelUI", gtkPackageName + "GtkLabelUI",
	    "MenuBarUI", gtkPackageName + "GtkMenuBarUI",
	    "MenuUI", gtkPackageName + "GtkMenuUI",
	    "MenuItemUI", gtkPackageName + "GtkMenuItemUI",
	    "CheckBoxMenuItemUI", gtkPackageName + "GtkCheckBoxMenuItemUI",
	    "RadioButtonMenuItemUI", gtkPackageName + "GtkRadioButtonMenuItemUI",
	    "RadioButtonUI", gtkPackageName + "GtkRadioButtonUI",
	    "ToggleButtonUI", gtkPackageName + "GtkToggleButtonUI",
	    "PopupMenuUI", gtkPackageName + "GtkPopupMenuUI",
	    "ProgressBarUI", gtkPackageName + "GtkProgressBarUI",
	    "ScrollBarUI", gtkPackageName + "GtkScrollBarUI",
	    "ScrollPaneUI", gtkPackageName + "GtkScrollPaneUI",
	    "SliderUI", gtkPackageName + "GtkSliderUI",
	    "SplitPaneUI", gtkPackageName + "GtkSplitPaneUI",
	    "TabbedPaneUI", gtkPackageName + "GtkTabbedPaneUI",
	    "TextAreaUI", gtkPackageName + "GtkTextAreaUI",
	    "TextFieldUI", gtkPackageName + "GtkTextFieldUI",
            "PasswordFieldUI", gtkPackageName + "GtkPasswordFieldUI",
	    "TextPaneUI", gtkPackageName + "GtkTextPaneUI",
	    "EditorPaneUI", gtkPackageName + "GtkEditorPaneUI",
	    "TreeUI", gtkPackageName + "GtkTreeUI",
            "InternalFrameUI", gtkPackageName + "GtkInternalFrameUI",
	    "DesktopPaneUI", gtkPackageName + "GtkDesktopPaneUI",
	    "SeparatorUI", gtkPackageName + "GtkSeparatorUI",
	    "PopupMenuSeparatorUI", gtkPackageName + "GtkPopupMenuSeparatorUI",
	    "OptionPaneUI", gtkPackageName + "GtkOptionPaneUI",
	    "ComboBoxUI", gtkPackageName + "GtkComboBoxUI",
	    "DesktopIconUI", gtkPackageName + "GtkDesktopIconUI"
        };

        table.putDefaults(uiDefaults);
    }


    private void loadResourceBundle(UIDefaults table) {
        ResourceBundle bundle =
	    ResourceBundle.getBundle("org.gtk.java.swing.plaf.gtk.resources.gtk");
	Enumeration iter = bundle.getKeys();
	while(iter.hasMoreElements()) {
	    String key = (String) iter.nextElement();
	    table.put(key, bundle.getObject(key));
	}
    }


    protected void initComponentDefaults(UIDefaults table) 
    {
        super.initComponentDefaults(table);
	
        loadResourceBundle(table);
	
        FontUIResource dialogPlain = new FontUIResource("Dialog", Font.PLAIN, 12);
        FontUIResource serifPlain = new FontUIResource("Serif", Font.PLAIN, 12);
        FontUIResource sansSerifPlain = new FontUIResource("SansSerif", Font.PLAIN, 12);
        FontUIResource monospacedPlain = new FontUIResource("Monospaced", Font.PLAIN, 12);

        ColorUIResource red = new ColorUIResource(Color.red);
        ColorUIResource black = new ColorUIResource(Color.black);
        ColorUIResource white = new ColorUIResource(Color.white);
        ColorUIResource lightGray = new ColorUIResource(Color.lightGray);

        ColorUIResource menuItemPressedBackground = 
	    new ColorUIResource(table.getColor("controlHighlight"));
        ColorUIResource menuItemPressedForeground =
	    new ColorUIResource(table.getColor("controlText"));

        Border loweredBevelBorder = 
	    new GtkBorders.BevelBorder(false, table.getColor("lightShadow"),
				       table.getColor("mediumShadow"),
				       table.getColor("darkShadow"));

        Border raisedBevelBorder = 
	    new GtkBorders.BevelBorder(true, table.getColor("lightShadow"),
				       table.getColor("mediumShadow"),
				       table.getColor("darkShadow"));

        Border marginBorder = new BasicBorders.MarginBorder();

        Border focusBorder = 
	    new GtkBorders.FocusBorder(table.getColor("window"), 
				       table.getColor("focus"));
        Border focusBevelBorder = 
	    new BorderUIResource.CompoundBorderUIResource(focusBorder, loweredBevelBorder);

        Border comboBoxBorder = 
	    new BorderUIResource.CompoundBorderUIResource(focusBorder, raisedBevelBorder);

         Border buttonBorder = 
	     new BorderUIResource.CompoundBorderUIResource(new
		     GtkBorders.ButtonBorder(table.getColor("lightShadow"),
					     table.getColor("mediumShadow"),
					     table.getColor("darkShadow"),
					     table.getColor("focus")),
							   marginBorder);

	
        Border toggleButtonBorder = 
	    new BorderUIResource.CompoundBorderUIResource(new
		    GtkBorders.ToggleButtonBorder(table.getColor("lightShadow"),
						  table.getColor("mediumShadow"),
						  table.getColor("darkShadow"),
						  table.getColor("focus")),
							  marginBorder);

	Border textFieldBorder = 
	    new BorderUIResource.CompoundBorderUIResource(focusBevelBorder, marginBorder);

	Border radioButtonBorder =
	    new BorderUIResource.CompoundBorderUIResource(new 
		GtkBorders.RadioButtonBorder(table.getColor("lightShadow"),
					     table.getColor("mediumShadow"),
					     table.getColor("darkShadow"),
					     table.getColor("focus")),
							  marginBorder);

	Border tableHeaderBorder =
	    new BorderUIResource.CompoundBorderUIResource(new
		GtkBorders.TableHeaderBorder(table.getColor("lightShadow"),
					     table.getColor("mediumShadow"),
					     table.getColor("darkShadow"),
					     table.getColor("focus")),
							  marginBorder);

	Border scrollPaneBorder =
	    new GtkBorders.ScrollPaneBorder(table.getColor("lightShadow"),
					    table.getColor("mediumShadow"),
					    table.getColor("darkShadow"),
					    table.getColor("focus"));

        Object menuItemCheckIcon = new UIDefaults.LazyValue() {
		public Object createValue(UIDefaults t) {
		    return GtkIconFactory.getMenuItemCheckIcon();
		}
	    };

        Object menuItemArrowIcon = new UIDefaults.LazyValue() {
		public Object createValue(UIDefaults t) {
		    return GtkIconFactory.getMenuItemArrowIcon();
		}
	    };

        Object menuArrowIcon = new UIDefaults.LazyValue() {
		public Object createValue(UIDefaults t) {
		    return GtkIconFactory.getMenuArrowIcon();
		}
	    };

        Object checkBoxIcon = new UIDefaults.LazyValue() {
		public Object createValue(UIDefaults t) {
		    return GtkIconFactory.getCheckBoxIcon();
		}
	    };

        Object radioButtonIcon = new UIDefaults.LazyValue() {
		public Object createValue(UIDefaults t) {
		    return GtkIconFactory.getRadioButtonIcon();
		}
	    };

        Object unselectedTabShadow = new UIDefaults.LazyValue() {
		public Object createValue(UIDefaults t) { 
		    Color c = (Color)t.getColor("control");
		    Color base = new Color(Math.max((int)(c.getRed()*.85),0), 
					   Math.max((int)(c.getGreen()*.85),0), 
					   Math.max((int)(c.getBlue()*.85),0));
		    return new ColorUIResource(base.darker());
		}
	    };

        Object unselectedTabHighlight = new UIDefaults.LazyValue() {
		public Object createValue(UIDefaults t) { 
		    Color c = (Color)t.getColor("control");
		    Color base = new Color(Math.max((int)(c.getRed()*.85),0), 
					   Math.max((int)(c.getGreen()*.85),0), 
					   Math.max((int)(c.getBlue()*.85),0));
		    return new ColorUIResource(base.brighter());
		}
	    };


	// Text key bindings

	JTextComponent.KeyBinding[] fieldBindings = {
	    new JTextComponent.KeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_INSERT, 
								 InputEvent.CTRL_MASK),
					  DefaultEditorKit.copyAction),
	    new JTextComponent.KeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_C, 
								 InputEvent.CTRL_MASK),
					  DefaultEditorKit.copyAction),
	    new JTextComponent.KeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_INSERT, 
								 InputEvent.SHIFT_MASK),
					  DefaultEditorKit.pasteAction),
	    new JTextComponent.KeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_V, 
								 InputEvent.CTRL_MASK),
					  DefaultEditorKit.pasteAction),
	    new JTextComponent.KeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 
								 InputEvent.SHIFT_MASK),
					  DefaultEditorKit.cutAction),
	    new JTextComponent.KeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_X, 
								 InputEvent.CTRL_MASK),
					  DefaultEditorKit.cutAction),
	    new JTextComponent.KeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_F, 
								 InputEvent.CTRL_MASK),
					  DefaultEditorKit.forwardAction),
	    new JTextComponent.KeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_B, 
								 InputEvent.CTRL_MASK),
					  DefaultEditorKit.backwardAction),
	    new JTextComponent.KeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_D, 
								 InputEvent.CTRL_MASK),
					  DefaultEditorKit.deleteNextCharAction),
	    new JTextComponent.KeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 
								 InputEvent.SHIFT_MASK),
					  DefaultEditorKit.selectionBackwardAction),
	    new JTextComponent.KeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 
								 InputEvent.SHIFT_MASK),
					  DefaultEditorKit.selectionForwardAction),
	    new JTextComponent.KeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 
								 InputEvent.CTRL_MASK),
					  DefaultEditorKit.previousWordAction),
	    new JTextComponent.KeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 
								 InputEvent.CTRL_MASK),
					  DefaultEditorKit.nextWordAction),
	    new JTextComponent.KeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 
								 InputEvent.CTRL_MASK | 
								 InputEvent.SHIFT_MASK),
					  DefaultEditorKit.selectionPreviousWordAction),
	    new JTextComponent.KeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 
								 InputEvent.CTRL_MASK |
								 InputEvent.SHIFT_MASK),
					  DefaultEditorKit.selectionNextWordAction),
	    new JTextComponent.KeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_SLASH, 
								 InputEvent.CTRL_MASK),
					  DefaultEditorKit.selectAllAction),
	    new JTextComponent.KeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_HOME, 0),
					  DefaultEditorKit.beginLineAction),
	    new JTextComponent.KeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_END, 0),
					  DefaultEditorKit.endLineAction),
	    new JTextComponent.KeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_HOME, 
								 InputEvent.SHIFT_MASK),
					  DefaultEditorKit.selectionBeginLineAction),
	    new JTextComponent.KeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_END, 
								 InputEvent.SHIFT_MASK),
					  DefaultEditorKit.selectionEndLineAction),
	    new JTextComponent.KeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),
					  JTextField.notifyAction)
		};

	JTextComponent.KeyBinding[] multilineBindings = {
	    new JTextComponent.KeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_INSERT, 
								 InputEvent.CTRL_MASK),
					  DefaultEditorKit.copyAction),
	    new JTextComponent.KeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_C, 
								 InputEvent.CTRL_MASK),
					  DefaultEditorKit.copyAction),
	    new JTextComponent.KeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_INSERT, 
								 InputEvent.SHIFT_MASK),
					  DefaultEditorKit.pasteAction),
	    new JTextComponent.KeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_V, 
								 InputEvent.CTRL_MASK),
					  DefaultEditorKit.pasteAction),
	    new JTextComponent.KeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 
								 InputEvent.SHIFT_MASK),
					  DefaultEditorKit.cutAction),
	    new JTextComponent.KeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_X, 
								 InputEvent.CTRL_MASK),
					  DefaultEditorKit.cutAction),
	    new JTextComponent.KeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_F, 
								 InputEvent.CTRL_MASK),
					  DefaultEditorKit.forwardAction),
	    new JTextComponent.KeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_B, 
								 InputEvent.CTRL_MASK),
					  DefaultEditorKit.backwardAction),
	    new JTextComponent.KeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_D, 
								 InputEvent.CTRL_MASK),
					  DefaultEditorKit.deleteNextCharAction),
	    new JTextComponent.KeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_N,
								 InputEvent.CTRL_MASK),
					  DefaultEditorKit.downAction),
	    new JTextComponent.KeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_P,
								 InputEvent.CTRL_MASK),
					  DefaultEditorKit.upAction),
	    new JTextComponent.KeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 
								 InputEvent.SHIFT_MASK),
					  DefaultEditorKit.selectionBackwardAction),
	    new JTextComponent.KeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 
								 InputEvent.SHIFT_MASK),
					  DefaultEditorKit.selectionForwardAction),
	    new JTextComponent.KeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 
								 InputEvent.CTRL_MASK),
					  DefaultEditorKit.previousWordAction),
	    new JTextComponent.KeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 
								 InputEvent.CTRL_MASK),
					  DefaultEditorKit.nextWordAction),
	    new JTextComponent.KeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 
								 InputEvent.CTRL_MASK | 
								 InputEvent.SHIFT_MASK),
					  DefaultEditorKit.selectionPreviousWordAction),
	    new JTextComponent.KeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 
								 InputEvent.CTRL_MASK |
								 InputEvent.SHIFT_MASK),
					  DefaultEditorKit.selectionNextWordAction),
	    new JTextComponent.KeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_SLASH, 
								 InputEvent.CTRL_MASK),
					  DefaultEditorKit.selectAllAction),
	    new JTextComponent.KeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_HOME, 0),
					  DefaultEditorKit.beginLineAction),
	    new JTextComponent.KeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_END, 0),
					  DefaultEditorKit.endLineAction),
	    new JTextComponent.KeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_HOME, 
								 InputEvent.SHIFT_MASK),
					  DefaultEditorKit.selectionBeginLineAction),
	    new JTextComponent.KeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_END, 
								 InputEvent.SHIFT_MASK),
					  DefaultEditorKit.selectionEndLineAction),
	    new JTextComponent.KeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0),
					  DefaultEditorKit.upAction),
	    new JTextComponent.KeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0),
					  DefaultEditorKit.downAction),
	    new JTextComponent.KeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_PAGE_UP, 0),
					  DefaultEditorKit.pageUpAction),
	    new JTextComponent.KeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_PAGE_DOWN, 0),
					  DefaultEditorKit.pageDownAction),
	    new JTextComponent.KeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_UP,
								 InputEvent.SHIFT_MASK),
					  DefaultEditorKit.selectionUpAction),
	    new JTextComponent.KeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN,
								 InputEvent.SHIFT_MASK),
					  DefaultEditorKit.selectionDownAction),
	    new JTextComponent.KeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),
					  DefaultEditorKit.insertBreakAction),
	    new JTextComponent.KeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0),
					  DefaultEditorKit.insertTabAction),
	    new JTextComponent.KeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SLASH, 
								 InputEvent.CTRL_MASK),
					  "unselect"/*DefaultEditorKit.unselectAction*/),
	    new JTextComponent.KeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_HOME, 
								 InputEvent.CTRL_MASK),
					  DefaultEditorKit.beginAction),
	    new JTextComponent.KeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_END, 
								 InputEvent.CTRL_MASK),
					  DefaultEditorKit.endAction),
	    new JTextComponent.KeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_HOME, 
								 InputEvent.CTRL_MASK |
								 InputEvent.SHIFT_MASK),
					  DefaultEditorKit.selectionBeginAction),
	    new JTextComponent.KeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_END, 
								 InputEvent.CTRL_MASK |
								 InputEvent.SHIFT_MASK),
					  DefaultEditorKit.selectionEndAction)
		};


        // Tree

        Object treeOpenIcon = LookAndFeel.makeIcon(getClass(), "icons/TreeOpen.gif");

        Object treeClosedIcon = LookAndFeel.makeIcon(getClass(), "icons/TreeClosed.gif");

        Object treeLeafIcon = new UIDefaults.LazyValue() {
		public Object createValue(UIDefaults t) {
		    return GtkTreeCellRenderer.loadLeafIcon();
		}
	    };

        Object treeExpandedIcon = new UIDefaults.LazyValue() {
		public Object createValue(UIDefaults t) {
		    return GtkTreeUI.GtkExpandedIcon.createExpandedIcon();
		}
	    };

        Object treeCollapsedIcon = new UIDefaults.LazyValue() {
		public Object createValue(UIDefaults t) {
		    return GtkTreeUI.GtkCollapsedIcon.createCollapsedIcon();
		}
	    };

        Border menuBarBorder = 
	    new GtkBorders.MenuBarBorder(table.getColor("lightShadow"), 
					 table.getColor("mediumShadow"),
					 table.getColor("darkShadow"));

	Border menuMarginBorder = 
	    new BorderUIResource.CompoundBorderUIResource(raisedBevelBorder, marginBorder);

        Border focusCellHighlightBorder =
	    new BorderUIResource.LineBorderUIResource(table.getColor("focus"));

	Object sliderFocusInsets = new InsetsUIResource(0, 0, 0, 0);

	// for tabbedpane

	Object tabbedPaneTabInsets = new InsetsUIResource(0, 0, 0, 0);
        Object tabbedPaneTabPadInsets = new InsetsUIResource(0, 0, 0, 0);
        Object tabbedPaneTabAreaInsets = new InsetsUIResource(4, 2, 0, 1);
        Object tabbedPaneContentBorderInsets = new InsetsUIResource(2, 2, 2, 2);


        // for optionpane

        Object optionPaneBorder = 
	    new BorderUIResource.EmptyBorderUIResource(10,0,10,0);
        Object optionPaneButtonAreaBorder =
	    new BorderUIResource.EmptyBorderUIResource(10,10,12,10);
        Object optionPaneMessageAreaBorder =
	    new BorderUIResource.EmptyBorderUIResource(0,10,12,10);


        Object[] defaults = {

            "Desktop.background", table.get("desktop"),

            "Panel.background", table.get("control"),
            "Panel.foreground", table.get("textText"),
            "Panel.font", dialogPlain,

            "ProgressBar.font", dialogPlain,
            "ProgressBar.foreground", table.get("control"),
            "ProgressBar.background", table.get("control"), 
	    "ProgressBar.selectionForeground", table.get("controlText"),
	    "ProgressBar.selectionBackground", table.get("controlText"),
	    "ProgressBar.border", loweredBevelBorder,
            "ProgressBar.cellLength", new Integer(6),
            "ProgressBar.cellSpacing", new Integer(0),

	    // Buttons
	    "Button.margin", new InsetsUIResource(2, 4, 2, 4),
            "Button.border", buttonBorder,
            "Button.background", table.get("control"),
            "Button.foreground", table.get("controlText"),
            "Button.select", table.get("controlDark"),
	    "Button.lightShadow", table.get("lightShadow"),
	    "Button.mediumShadow", table.get("mediumShadow"),
	    "Button.darkShadow", table.get("darkShadow"),
	    "Button.highlight", table.get("controlHighlight"),
            "Button.font", dialogPlain,

	    "CheckBox.margin", new InsetsUIResource(2, 2, 6, 2),
	    "CheckBox.border", radioButtonBorder,
	    "CheckBox.textIconGap", new Integer(8),
            "CheckBox.background", table.get("control"),
            "CheckBox.foreground", table.get("controlText"),
            "CheckBox.icon", checkBoxIcon,
	    "CheckBox.highlight", table.get("controlHighlight"),
            "CheckBox.focus", table.get("focus"),

	    "RadioButton.margin", new InsetsUIResource(2, 2, 6, 2),
	    "RadioButton.border", radioButtonBorder,
	    "RadioButton.textIconGap", new Integer(8),
            "RadioButton.background", table.get("control"),
            "RadioButton.foreground", table.get("controlText"),
            "RadioButton.icon", radioButtonIcon,
	    "RadioButton.highlight", table.get("controlHighlight"),
            "RadioButton.focus", table.get("focus"),

            "ToggleButton.border", toggleButtonBorder,
            "ToggleButton.background", table.get("control"),
            "ToggleButton.foreground", table.get("controlText"),
	    "ToggleButton.lightShadow", table.get("lightShadow"),
	    "ToggleButton.mediumShadow", table.get("mediumShadow"),
	    "ToggleButton.darkShadow", table.get("darkShadow"),
            "ToggleButton.focus", table.get("focus"),
	    "ToggleButton.highlight", table.get("controlHighlight"),
            "ToggleButton.select", table.get("controlDark"),

	    // Menus
            "Menu.border", menuBarBorder,
            "Menu.font", dialogPlain,
            "Menu.acceleratorFont", dialogPlain,
            "Menu.foreground", table.get("menuText"),
            "Menu.background", table.get("menu"),
            "Menu.selectionForeground", menuItemPressedForeground,
            "Menu.selectionBackground", menuItemPressedBackground,
            "Menu.checkIcon", menuItemCheckIcon,
            "Menu.arrowIcon", menuArrowIcon,

            "MenuBar.border", menuBarBorder,
            "MenuBar.foreground", table.get("menuText"),
            "MenuBar.background", table.get("menu"),
            "MenuBar.font", dialogPlain,

            "MenuItem.border", menuBarBorder,
            "MenuItem.font", dialogPlain,
            "MenuItem.acceleratorFont", dialogPlain,
            "MenuItem.foreground", table.get("menuText"),
            "MenuItem.background", table.get("menu"),
            "MenuItem.selectionForeground", menuItemPressedForeground,
            "MenuItem.selectionBackground", menuItemPressedBackground,
            "MenuItem.checkIcon", menuItemCheckIcon,
            "MenuItem.arrowIcon", menuItemArrowIcon,

            "RadioButtonMenuItem.border", menuBarBorder,
            "RadioButtonMenuItem.font", dialogPlain,
            "RadioButtonMenuItem.acceleratorFont", dialogPlain,
            "RadioButtonMenuItem.foreground", table.get("menuText"),
            "RadioButtonMenuItem.background", table.get("menu"),
            "RadioButtonMenuItem.selectionForeground", menuItemPressedForeground,
            "RadioButtonMenuItem.selectionBackground", menuItemPressedBackground,
            "RadioButtonMenuItem.checkIcon", radioButtonIcon,
            "RadioButtonMenuItem.arrowIcon", menuItemArrowIcon,

            "CheckBoxMenuItem.border", menuBarBorder,
            "CheckBoxMenuItem.font", dialogPlain,
            "CheckBoxMenuItem.acceleratorFont", dialogPlain,
            "CheckBoxMenuItem.foreground", table.get("menuText"),
            "CheckBoxMenuItem.background", table.get("menu"),
            "CheckBoxMenuItem.selectionForeground", menuItemPressedForeground,
            "CheckBoxMenuItem.selectionBackground", menuItemPressedBackground,
            "CheckBoxMenuItem.checkIcon", checkBoxIcon,
            "CheckBoxMenuItem.arrowIcon", menuItemArrowIcon,

            "PopupMenu.border", raisedBevelBorder,            
            "PopupMenu.foreground", table.get("menuText"),
            "PopupMenu.background", table.get("menu"),
            "PopupMenu.font", dialogPlain,

            "Label.font", dialogPlain,
            "Label.background", table.get("control"),
            "Label.foreground", table.get("controlText"),

            "Separator.background", table.get("mediumShadow"),
            "Separator.foreground", table.get("lightShadow"),

            "List.focusCellHighlightBorder", focusCellHighlightBorder,

            "DesktopIcon.icon", LookAndFeel.makeIcon(getClass(), 
						     "icons/DesktopIcon.gif"),
	    "DesktopIcon.border", null,

            "ScrollBar.foreground", table.get("control"),
            "ScrollBar.track", table.get("controlDark"),
            "ScrollBar.thumb", table.get("control"),
            "ScrollBar.border", loweredBevelBorder,
 
            "ScrollPane.border", scrollPaneBorder,
            "ScrollPane.background", table.get("control"),
            "ScrollPane.foreground", table.get("textText"),
            "ScrollPane.font", dialogPlain,
	    //"ScrollPane.viewportBorder", loweredBevelBorder,

            "Slider.border", focusBevelBorder,
            "Slider.foreground", table.get("control"),
            "Slider.background", table.get("controlDark"),
            "Slider.highlight", table.get("controlHighlight"),
            "Slider.focus", table.get("focus"),
	    "Slider.focusInsets", sliderFocusInsets,

            "SplitPane.background", table.get("control"),
	    //	    "SplitPane.border", 
	    "SplitPane.border", null,

            "TabbedPane.font", dialogPlain,
            "TabbedPane.background", table.get("control"),
            "TabbedPane.foreground", table.get("controlText"),
            "TabbedPane.highlight", table.get("controlHighlight"),
            "TabbedPane.mediumShadow", table.get("mediumShadow"),
            "TabbedPane.darkShadow", table.get("darkShadow"),
            "TabbedPane.lightShadow", table.get("lightShadow"),
            "TabbedPane.unselectedTabBackground", table.get("unselectedTabBG"),
            "TabbedPane.unselectedTabForeground", table.get("controlText"),
            "TabbedPane.unselectedTabHighlight", unselectedTabHighlight,
            "TabbedPane.unselectedTabShadow", unselectedTabShadow,
            "TabbedPane.focus", table.get("focus"),
	    //            "TabbedPane.tabInsets", tabbedPaneTabInsets,
	    //            "TabbedPane.selectedTabPadInsets", tabbedPaneTabPadInsets,
            "TabbedPane.tabAreaInsets", tabbedPaneTabAreaInsets,
	    //            "TabbedPane.contentBorderInsets", tabbedPaneContentBorderInsets,

            "Tree.background", table.get("lightShadow"),
            "Tree.iconBackground", table.get("lightShadow"),
            "Tree.iconForeground", table.get("controlText"),
            "Tree.textBackground", table.get("text"),
            "Tree.textForeground", table.get("textText"),
            "Tree.selectionBackground", table.get("textHighlight"),
            "Tree.selectionForeground", table.get("textHighlightText"),
            "Tree.selectionBorderColor", table.get("focus"),
	    "Tree.openIcon", treeOpenIcon,
	    "Tree.closedIcon", treeClosedIcon,
	    "Tree.leafIcon", treeLeafIcon,
	    "Tree.expandedIcon", treeExpandedIcon,
	    "Tree.collapsedIcon", treeCollapsedIcon,
	    "Tree.editorBorder", focusBorder,
	    "Tree.editorBorderSelectionColor", table.get("activeCaptionBorder"),
	    "Tree.rowHeight", new Integer(18),
	    "Tree.changeSelectionWithFocus", Boolean.FALSE,
	    "Tree.drawsFocusBorderAroundIcon", Boolean.TRUE,

            "Table.focusCellHighlightBorder", focusCellHighlightBorder,
	    "Table.focusCellBackground", table.get("windowLight"),
            "Table.scrollPaneBorder", scrollPaneBorder,
	    "TableHeader.cellBorder", tableHeaderBorder,

            "ComboBox.control", table.get("control"),
            "ComboBox.controlForeground", table.get("controlText"),
            "ComboBox.background", table.get("control"), 
            "ComboBox.foreground", table.get("controlText"),
            "ComboBox.border", comboBoxBorder,
            "ComboBox.selectionBackground", table.get("textHighlight"),
            "ComboBox.selectionForeground", table.get("textHighlightText"),
            "ComboBox.listBackground", table.get("windowLight"),
            "ComboBox.listForeground", table.get("textText"),
            "ComboBox.disabledBackground", table.get("control"),
            "ComboBox.disabledForeground", table.get("textInactiveText"),
            "ComboBox.font", dialogPlain,

            "TextField.caretForeground", black,
            "TextField.caretBlinkRate", new Integer(500),
            "TextField.inactiveForeground", table.get("textInactiveText"),
            "TextField.selectionBackground", table.get("textHighlight"),
            "TextField.selectionForeground", table.get("textHighlightText"),
            "TextField.background", table.get("text"),
            "TextField.foreground", table.get("textText"),
            "TextField.font", sansSerifPlain,
            "TextField.border", textFieldBorder,
	    "TextField.keyBindings", fieldBindings,
            
            "PasswordField.caretForeground", black,
            "PasswordField.caretBlinkRate", new Integer(500),
            "PasswordField.inactiveForeground", table.get("textInactiveText"),
            "PasswordField.selectionBackground", table.get("textHighlight"),
            "PasswordField.selectionForeground", table.get("textHighlightText"),
            "PasswordField.background", table.get("control"),
            "PasswordField.foreground", table.get("textText"),
            "PasswordField.font", monospacedPlain,
            "PasswordField.border", textFieldBorder,
	    "PasswordField.keyBindings", fieldBindings,
            
            "TextArea.caretForeground", black,
            "TextArea.caretBlinkRate", new Integer(500),
            "TextArea.inactiveForeground", table.get("textInactiveText"),
            "TextArea.selectionBackground", table.get("textHighlight"),
            "TextArea.selectionForeground", table.get("textHighlightText"),
            "TextArea.background", table.get("text"),
            "TextArea.foreground", table.get("textText"),
            "TextArea.font", monospacedPlain,
            "TextArea.border", marginBorder,
	    "TextArea.keyBindings", multilineBindings,
            
            "TextPane.caretForeground", black,
            "TextPane.caretBlinkRate", new Integer(500),
            "TextPane.inactiveForeground", table.get("textInactiveText"),
            "TextPane.selectionBackground", lightGray,
            "TextPane.selectionForeground", table.get("textHighlightText"),
            "TextPane.background", table.get("text"),
            "TextPane.foreground", table.get("textText"),
            "TextPane.font", serifPlain,
            "TextPane.border", marginBorder,
	    "TextPane.keyBindings", multilineBindings,
            
            "EditorPane.caretForeground", red,
            "EditorPane.caretBlinkRate", new Integer(500),
            "EditorPane.inactiveForeground", table.get("textInactiveText"),
            "EditorPane.selectionBackground", table.get("textHighlight"),
            "EditorPane.selectionForeground", table.get("textHighlightText"),
            "EditorPane.background", table.get("text"),
            "EditorPane.foreground", table.get("textText"),
            "EditorPane.font", serifPlain,
            "EditorPane.border", marginBorder,
	    "EditorPane.keyBindings", multilineBindings,

	    "FileChooser.pathLabelMnemonic", new Integer(KeyEvent.VK_P), // 'p'
	    "FileChooser.filterLabelMnemonic", new Integer (KeyEvent.VK_R), // 'r'
	    "FileChooser.foldersLabelMnemonic", new Integer (KeyEvent.VK_O), // 'o'
	    "FileChooser.filesLabelMnemonic", new Integer (KeyEvent.VK_I), // 'i'
	    "FileChooser.enterFileNameLabelMnemonic", new Integer (KeyEvent.VK_N), // 'n'

            "ToolTip.border", raisedBevelBorder,
            "ToolTip.background", table.get("controlHighlight"),
            "ToolTip.foreground", table.get("textText"),

            "OptionPane.border", optionPaneBorder,
            "OptionPane.messageAreaBorder", optionPaneMessageAreaBorder,
            "OptionPane.buttonAreaBorder", optionPaneButtonAreaBorder,
            "OptionPane.errorIcon", LookAndFeel.makeIcon(getClass(), 
                                                         "icons/Error.gif"),
            "OptionPane.informationIcon", LookAndFeel.makeIcon(getClass(), 
                                                               "icons/Inform.gif"),
            "OptionPane.warningIcon", LookAndFeel.makeIcon(getClass(), 
                                                           "icons/Warn.gif"),
            "OptionPane.questionIcon", LookAndFeel.makeIcon(getClass(), 
                                                            "icons/Question.gif")
        };

        table.putDefaults(defaults);
    }

    /*
     * Returns whether this is being run on a JDK 1.2 or later VM.
     * This is a system-wide, rather than AppContext-wide, state.
     */
    static boolean is1dot2 = true;

    static {
        try {
            // Test if method introduced in 1.2 is available.
            Method m = Toolkit.class.getMethod("getMaximumCursorColors", null);
            is1dot2 = (m != null);
        }
	catch (NoSuchMethodException e) {
            is1dot2 = false;
        }
    }

}
