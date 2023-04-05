package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * <p>
 * Actions provided by the Adjust Image.
 * </p>
 * 
 * <p>
 * The Adjust menu contains actions that alter images
 * via size or orientation
 * </p>
 */
public class AdjustImageT {

    protected ArrayList<Action> actions;
    ResourceBundle bundle = ResourceBundle.getBundle("MessageBundle");

    public AdjustImageT() {
        actions = new ArrayList<Action>();
        actions.add(new ResizeAction(bundle.getString("Resize"), null, bundle.getString("ResizeDesc"),
                Integer.valueOf(KeyEvent.VK_R)));
        actions.add(new FlipVAction(bundle.getString("FlipV"), null, bundle.getString("FlipVDesc"),
                Integer.valueOf(KeyEvent.VK_V)));
        actions.add(new FlipHAction(bundle.getString("FlipH"), null, bundle.getString("FlipHDesc"),
                Integer.valueOf(KeyEvent.VK_H)));
        actions.add(new RotateLeftAction("Rotate 90\u00B0 Left", null, "Rotate image 90\u00B0 Left",
                Integer.valueOf(KeyEvent.VK_F)));
        actions.add(new RotateRightAction("Rotate 90\u00B0 Right", null, "Rotate image 90\u00B0 Right",
                Integer.valueOf(KeyEvent.VK_F)));
        actions.add(new Rotate180Action("Rotate 180\u00B0", null, "Rotate image 180\u00B0",
                Integer.valueOf(KeyEvent.VK_F)));

    }

    /**
     * <p>
     * Create a menu contianing the list of Adjust actions.
     * </p>
     * 
     * @return The filter menu UI element.
     */
    public JMenu createMenu() {
        JMenu fileMenu = new JMenu(bundle.getString("Adjust"));

        for (Action action : actions) {
            fileMenu.add(new JMenuItem(action));
        }
        return fileMenu;
    }

    /**
     * <p>
     * Action to Resize an image with Resize
     * </p>
     * 
     * @see Resize
     */
    public class ResizeAction extends ImageAction {

        /**
         * <p>
         * Create a Resize action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        ResizeAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the Resize action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the ResizeAction is triggered.
         * Asks user for multiplier value then
         * Applys an appropriately sized
         * {@link Resize}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            int multiplier = 100;

            // Pop-up dialog box to ask for the multiplier value.
            int min = 1;
            int max = 200;
            int initialValue = 100;
            DefaultBoundedRangeModel multiplierModel = new DefaultBoundedRangeModel(initialValue, 0, min, max);
            JSlider multiplierSlider = new JSlider(multiplierModel);
            multiplierSlider.setMajorTickSpacing(199);
            multiplierSlider.setMinorTickSpacing(10);
            multiplierSlider.setPaintTicks(true);
            multiplierSlider.setPaintLabels(true);
            int option = JOptionPane.showOptionDialog(null, multiplierSlider, bundle.getString("FilterValueDesc"),
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                multiplier = multiplierSlider.getValue();
            }

            target.getImage().apply(new Resize(multiplier));
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * <p>
     * Action to Flip an image vertically with FlipV
     * </p>
     * 
     * @see FlipV
     */
    public class FlipVAction extends ImageAction {

        /**
         * <p>
         * Create a Flip Vertical action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        FlipVAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the FlipV action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the FlipVAction is triggered.
         * Applys
         * {@link FlipV}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            target.getImage().apply(new FlipV());
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * <p>
     * Action to Flip an image horizontally with FlipH
     * </p>
     * 
     * @see FlipH
     */
    public class FlipHAction extends ImageAction {

        /**
         * <p>
         * Create a Flip Horizontally action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        FlipHAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the FlipH action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the FlipHAction is triggered.
         * Applys
         * {@link FlipH}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            target.getImage().apply(new FlipH());
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * <p>
     * Action to rotate an image left 90 degrees with Rotate90Left
     * </p>
     * 
     * @see Rotate90Left
     */
    public class RotateLeftAction extends ImageAction {

        /**
         * <p>
         * Create a Rotate Left action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        RotateLeftAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the RotateLeft action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the RotateLeftAction is triggered.
         * Applys
         * {@link Rotate90Left}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            target.getImage().apply(new Rotate90Left());
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * <p>
     * Action to rotate an image right 90 degrees with Rotate90Right
     * </p>
     * 
     * @see Rotate90Right
     */
    public class RotateRightAction extends ImageAction {

        /**
         * <p>
         * Create a Rotate Right action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        RotateRightAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the RotateRight action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the RotateRightAction is triggered.
         * Applys
         * {@link Rotate90Right}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            target.getImage().apply(new Rotate90Right());
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * <p>
     * Action to rotate an image left 180 degrees with Rotate180
     * </p>
     * 
     * @see Rotate180
     */
    public class Rotate180Action extends ImageAction {

        /**
         * <p>
         * Create a Rotate 180 action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        Rotate180Action(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the Rotate18- action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the Rotate180Action is triggered.
         * Applys
         * {@link Rotate180}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            target.getImage().apply(new Rotate180());
            target.repaint();
            target.getParent().revalidate();
        }
    }
}
