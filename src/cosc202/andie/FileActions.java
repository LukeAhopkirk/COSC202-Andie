package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * <p>
 * Actions provided by the File menu.
 * </p>
 * 
 * <p>
 * The File menu is very common across applications,
 * and there are several items that the user will expect to find here.
 * Opening and saving files is an obvious one, but also exiting the program.
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
public class FileActions {

    /** A list of actions for the File menu. */
    protected ArrayList<Action> actions;
    ResourceBundle bundle = ResourceBundle.getBundle("MessageBundle");

    /**
     * <p>
     * Create a set of File menu actions.
     * </p>
     */
    public FileActions() {

        actions = new ArrayList<Action>();
        // WHEN YOU ARE EDITING THIS, PLEASE DON'T CHANGE THE ORDER OF THE OPERATIONS OR
        // ELSE KEYBOARD
        // SHORTCUTS WOULD BREAK

        // Open
        actions.add(new FileOpenAction(bundle.getString("Open"), null, bundle.getString("OpenDesc"),
                Integer.valueOf(KeyEvent.VK_O)));
        if (OperatingSystem.getOS().equals("mac")) {
            actions.get(0).putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("meta pressed O"));
        } else {
            actions.get(0).putValue(Action.ACCELERATOR_KEY,
                    KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
        }

        // Save
        actions.add(new FileSaveAction(bundle.getString("Save"), null, bundle.getString("SaveDesc"),
                Integer.valueOf(KeyEvent.VK_S)));
        if (OperatingSystem.getOS().equals("mac")) {
            actions.get(1).putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("meta pressed S"));
        } else {
            actions.get(1).putValue(Action.ACCELERATOR_KEY,
                    KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
        }

        // SaveAs
        actions.add(new FileSaveAsAction(bundle.getString("SaveAs"), null, bundle.getString("SaveAsDesc"),
                Integer.valueOf(KeyEvent.VK_A)));
        if (OperatingSystem.getOS().equals("mac")) {
            actions.get(2).putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("meta shift pressed S"));
        } else {
            actions.get(2).putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl shift pressed S"));
        }

        // Export
        actions.add(new ExportAction(bundle.getString("Export"), null, bundle.getString("ExportDesc"),
                Integer.valueOf(KeyEvent.VK_E)));
        if (OperatingSystem.getOS().equals("mac")) {
            actions.get(3).putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("meta pressed X"));
        } else {
            actions.get(3).putValue(Action.ACCELERATOR_KEY,
                    KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK));
        }

        // Exit
        actions.add(
                new FileExitAction(bundle.getString("Exit"), null, bundle.getString("ExitDesc"), Integer.valueOf(0)));

        if (OperatingSystem.getOS().equals("mac")) {
            actions.get(4).putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("meta pressed Q"));
        } else {
            actions.get(4).putValue(Action.ACCELERATOR_KEY,
                    KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK));
        }

        // actions.get(1).putValue(Action.ACCELERATOR_KEY,
        // KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));

        // KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, KeyEvent.SHIFT_MASK |
        // KeyEvent.CTRL_MASK)
        // KeyStroke.getKeyStroke("shift ctrl pressed SPACE")
    }

    /**
     * <p>
     * Create a menu contianing the list of File actions.
     * </p>
     * 
     * @return The File menu UI element.
     */
    public JMenu createMenu() {

        JMenu fileMenu = new JMenu(bundle.getString("File"));

        for (Action action : actions) {
            fileMenu.add(new JMenuItem(action));
        }

        return fileMenu;
    }

    /**
     * <p>
     * Action to open an image from file.
     * </p>
     * 
     * @see EditableImage#open(String)
     */
    public class FileOpenAction extends ImageAction {

        /**
         * <p>
         * Create a new file-open action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        FileOpenAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the file-open action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the FileOpenAction is triggered.
         * It prompts the user to select a file and opens it as an image.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(target);

            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    String imageFilepath = fileChooser.getSelectedFile().getCanonicalPath();
                    if (target.getImage().hasImage() == true) {

                        // Ask user if they want to save their current picture
                        int userSelection = JOptionPane.showConfirmDialog(target,
                                "Do you wish to save before opening a new file? Unsaved changes will be lost!",
                                "Are you sure you want to override this image?", JOptionPane.YES_NO_CANCEL_OPTION,
                                JOptionPane.INFORMATION_MESSAGE);
                        if (userSelection == JOptionPane.YES_OPTION) {
                            try {
                                target.getImage().save();
                            } catch (Exception ex) {
                                System.exit(1);
                            }
                        } else if (userSelection == JOptionPane.CANCEL_OPTION) {
                            return;
                        }
                    }

                    // Check if selected file is an image file
                    String extension = imageFilepath.substring(imageFilepath.lastIndexOf(".") + 1).toLowerCase();
                    if (!extension.equals("jpg") && !extension.equals("jpeg") && !extension.equals("png")
                            && !extension.equals("gif")) {
                        throw new IllegalArgumentException("Selected file is not an image file");
                    }

                    target.getImage().open(imageFilepath);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(target, "Error opening image file: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * <p>
     * Action to save an image to its current file location.
     * </p>
     * 
     * @see EditableImage#save()
     */
    public class FileSaveAction extends ImageAction {

        /**
         * <p>
         * Create a new file-save action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        FileSaveAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the file-save action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the FileSaveAction is triggered.
         * It saves the image to its original filepath.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            try {
                target.getImage().save();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Please open an image to save.");
            }
        }

    }

    /**
     * <p>
     * Action to save an image to a new file location.
     * </p>
     * 
     * @see EditableImage#saveAs(String)
     */
    public class FileSaveAsAction extends ImageAction {

        /**
         * <p>
         * Create a new file-save-as action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        FileSaveAsAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the file-save-as action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the FileSaveAsAction is triggered.
         * It prompts the user to select a file and saves the image to it.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showSaveDialog(target);

            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    String imageFilepath = fileChooser.getSelectedFile().getCanonicalPath();
                    target.getImage().saveAs(imageFilepath);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "" + e);
                }
            }
        }

    }

    public class ExportAction extends ImageAction {

        ExportAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showSaveDialog(target);

            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    String imageFilepath = fileChooser.getSelectedFile().getCanonicalPath();
                    target.getImage().export(imageFilepath);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Please open an image to export.");
                }
            }
        }

    }

    /**
     * <p>
     * Action to quit the ANDIE application.
     * </p>
     */
    public class FileExitAction extends AbstractAction {

        /**
         * <p>
         * Create a new file-exit action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        FileExitAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon);
            putValue(SHORT_DESCRIPTION, desc);
            putValue(MNEMONIC_KEY, mnemonic);
        }

        /**
         * <p>
         * Callback for when the file-exit action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the FileExitAction is triggered.
         * It quits the program.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }

    }

}
