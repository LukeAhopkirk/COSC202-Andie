package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * <p>
 * Actions provided by the Draw menu.
 * </p>
 * 
 * <p>
 * The View menu contains actions that draw on the image.
 * </p>
 * 
 *
 */
public class DrawActions {

    /**
     * A list of actions for the View menu.
     */
    protected ArrayList<Action> actions;
    ResourceBundle bundle = ResourceBundle.getBundle("MessageBundle");

    /**
     * <p>
     * Create a set of View menu actions.
     * </p>
     */
    public DrawActions() {
        actions = new ArrayList<Action>();
        actions.add(new DrawCircleAction(bundle.getString("Circle"), null, bundle.getString("CircleDesc"),
                Integer.valueOf(KeyEvent.VK_C)));
        // actions.get(0).putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl
        // shift pressed EQUALS"));
        actions.add(new DrawRectangleAction(bundle.getString("Rectangle"), null, bundle.getString("RectangleDesc"),
                Integer.valueOf(KeyEvent.VK_R)));
        // actions.get(1).putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl
        // pressed MINUS"));
    }

    /**
     * <p>
     * Create a menu containing the list of View actions.
     * </p>
     * 
     * @return The view menu UI element.
     */
    public JMenu createMenu() {
        JMenu viewMenu = new JMenu(bundle.getString("Draw"));

        for (Action action : actions) {
            viewMenu.add(new JMenuItem(action));
        }

        return viewMenu;
    }

    /**
     * <p>
     * Action to draw a circle on an image.
     * </p>
     */
    public class DrawCircleAction extends ImageAction {

        /**
         * <p>
         * Create a new drawcircle action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        DrawCircleAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the drawcricle action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the drawcircle is triggered.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new Circle()); // pass in co-ordinates? etc
            target.repaint();
            target.getParent().revalidate();
        }

    }

    /**
     * <p>
     * Action to draw Rectangle
     * </p>
     */
    public class DrawRectangleAction extends ImageAction {

        /**
         * <p>
         * Create a new drawrectangle action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        DrawRectangleAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the drawrectangle action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the drawRectangle is triggered.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new Rectangle()); // pass in co-ordinates? etc
            target.repaint();
            target.getParent().revalidate();
        }

    }
}
