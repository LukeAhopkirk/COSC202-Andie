package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * <p>
 * Actions provided by the Edit menu.
 * </p>
 * 
 * <p>
 * The Edit menu is very common across a wide range of applications.
 * There are a lot of operations that a user might expect to see here.
 * In the sample code there are Undo and Redo actions, but more may need to be
 * added.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @author Steven Mills
 * @version 1.0
 */
public class EditActions {

    /** A list of actions for the Edit menu. */
    protected ArrayList<Action> actions;
    ResourceBundle bundle = ResourceBundle.getBundle("MessageBundle");

    /**
     * <p>
     * Create a set of Edit menu actions.
     * </p>
     */
    public EditActions() {
        actions = new ArrayList<Action>();
        actions.add(new UndoAction(bundle.getString("Undo"), null, bundle.getString("UndoDesc"),
                Integer.valueOf(KeyEvent.VK_U)));
        actions.get(0).putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK));
        actions.add(new RedoAction(bundle.getString("Redo"), null, bundle.getString("RedoDesc"),
                Integer.valueOf(KeyEvent.VK_R)));
        actions.get(1).putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl shift pressed Z"));

        actions.add(new MacroSAction("Start Macro", null, "Starts macro", Integer.valueOf(KeyEvent.VK_R)));
        // actions.get(2).putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl
        // shift pressed S"));
        actions.add(new MacroFAction("Stop Macro", null, "Stops and saves macro", Integer.valueOf(KeyEvent.VK_R)));
        // actions.get(3).putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl
        // shift pressed F"));

        actions.add(new MacroOpenAction("Open Macro", null, "Open saved macro", Integer.valueOf(KeyEvent.VK_R)));
        // actions.get(3).putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl
        // shift pressed F"));

    }

    /**
     * <p>
     * Create a menu contianing the list of Edit actions.
     * </p>
     * 
     * @return The edit menu UI element.
     */
    public JMenu createMenu() {
        JMenu editMenu = new JMenu(bundle.getString("Edit"));

        for (Action action : actions) {
            editMenu.add(new JMenuItem(action));
        }

        return editMenu;
    }

    /**
     * <p>
     * Action to undo an {@link ImageOperation}.
     * </p>
     * 
     * @see EditableImage#undo()
     */
    public class UndoAction extends ImageAction {

        /**
         * <p>
         * Create a new undo action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        UndoAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the undo action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the UndoAction is triggered.
         * It undoes the most recently applied operation.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            target.getImage().undo();
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * <p>
     * Action to redo an {@link ImageOperation}.
     * </p>
     * 
     * @see EditableImage#redo()
     */
    public class RedoAction extends ImageAction {

        /**
         * <p>
         * Create a new redo action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        RedoAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the redo action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the RedoAction is triggered.
         * It redoes the most recently undone operation.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            target.getImage().redo();
            target.repaint();
            target.getParent().revalidate();
        }
    }

    public class MacroSAction extends ImageAction {

        MacroSAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            target.getImage().macroS();
            target.repaint();
            target.getParent().revalidate();
        }
    }

    public class MacroFAction extends ImageAction {

        MacroFAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {

            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showSaveDialog(target);
            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    String imageFilepath = fileChooser.getSelectedFile().getCanonicalPath();
                    target.getImage().macroF(imageFilepath);
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "Operation macro saving error.");
                }
            }
        }
    }

    public class MacroOpenAction extends ImageAction {

        MacroOpenAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {

            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(target);
            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    String imageFilepath = fileChooser.getSelectedFile().getCanonicalPath();
                    if (target.getImage().hasImage() == false) {
                        JOptionPane.showMessageDialog(null, "No image to apply macro on.");
                        return;
                    }
                    String extension = imageFilepath.substring(imageFilepath.lastIndexOf(".") + 1).toLowerCase();
                    if (!extension.equals("macro")) {
                        throw new IllegalArgumentException("Selected file is not a macro");
                    }
                    // String filename = JOptionPane.showInputDialog("Please enter a file name for
                    // your operations:");
                    target.getImage().macroOpen(imageFilepath);

                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(target, "Error opening image macro: " + e1.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            target.repaint();
            target.getParent().revalidate();
        }
    }

}
