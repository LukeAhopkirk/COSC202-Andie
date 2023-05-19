package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * <p>
 * Actions provided by the Filter menu.
 * </p>
 * 
 * <p>
 * The Filter menu contains actions that update each pixel in an image based on
 * some small local neighbourhood.
 * This includes a mean filter (a simple blur) in the sample code, but more
 * operations will need to be added (done).
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
public class FilterActions {

    /** A list of actions for the Filter menu. */
    protected ArrayList<Action> actions;
    ResourceBundle bundle = ResourceBundle.getBundle("MessageBundle");

    /**
     * <p>
     * Create a set of Filter menu actions.
     * </p>
     */
    public FilterActions() {
        actions = new ArrayList<Action>();
        actions.add(new MeanFilterAction(bundle.getString("Mean"), null, bundle.getString("MeanDesc"),
                Integer.valueOf(KeyEvent.VK_M)));
        actions.add(new SoftBlurAction(bundle.getString("Soft"), null, bundle.getString("SoftDesc"),
                Integer.valueOf(KeyEvent.VK_S)));
        actions.add(new SharpenAction(bundle.getString("Sharpen"), null, bundle.getString("SharpenDesc"),
                Integer.valueOf(KeyEvent.VK_H)));
        actions.add(new GaussianAction(bundle.getString("Gaussian"), null, bundle.getString("GaussianDesc"),
                Integer.valueOf(KeyEvent.VK_G)));
        actions.add(new MedianAction(bundle.getString("Median"), null, bundle.getString("MedianDesc"),
                Integer.valueOf(KeyEvent.VK_M)));
        actions.add(new SobelAction(bundle.getString("Sobel"), null,
                bundle.getString("SobelDesc"),
                Integer.valueOf(KeyEvent.VK_B)));
        actions.add(new EmbossAction(bundle.getString("Emboss"), null,
                bundle.getString("EmbossDesc"),
                Integer.valueOf(KeyEvent.VK_E)));
    }

    /**
     * <p>
     * Create a menu contianing the list of Filter actions.
     * </p>
     * 
     * @return The filter menu UI element.
     */
    public JMenu createMenu() {
        JMenu fileMenu = new JMenu(bundle.getString("Filter"));

        for (Action action : actions) {
            fileMenu.add(new JMenuItem(action));
        }

        return fileMenu;
    }

    /**
     * <p>
     * Action to blur an image with a mean filter.
     * </p>
     * 
     * @see MeanFilter
     */
    public class MeanFilterAction extends ImageAction {

        /**
         * <p>
         * Create a new mean-filter action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        MeanFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the MeanFilter action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the MeanFilterAction is triggered.
         * It prompts the user for a filter radius, then applys an appropriately sized
         * {@link MeanFilter}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            // Determine the radius - ask the user.
            int radius = 1;

            // Pop-up dialog box to ask for the radius value.
            // Pop-up dialog box to ask for the radius value.
            int min = 1;
            int max = 10;
            int initialValue = 1;
            DefaultBoundedRangeModel radiusModel = new DefaultBoundedRangeModel(initialValue, 0, min, max);
            JSlider radiusSlider = new JSlider(radiusModel);
            radiusSlider.setMajorTickSpacing(1);
            radiusSlider.setPaintTicks(true);
            radiusSlider.setPaintLabels(true);
            int option = JOptionPane.showOptionDialog(null, radiusSlider, bundle.getString("FilterValueDesc"),
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                radius = radiusModel.getValue();
            }

            // Create and apply the filter
            target.getImage().apply(new MeanFilter(radius));
            target.repaint();
            target.getParent().revalidate();
        }

    }

    /**
     * <p>
     * Action to blur an image with a Gaussian Blur filter.
     * </p>
     * 
     * @see GaussianB
     */
    public class GaussianAction extends ImageAction {

        /**
         * <p>
         * Create a new GaussianBlur filter action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        GaussianAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * This method is called whenever the MeanFilterAction is triggered.
         * It prompts the user for a filter radius, then applys an appropriately sized
         * {@link GaussianB}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            int radius = 1;

            // Pop-up dialog box to ask for the radius value, using a JSlider
            int min = 1;
            int max = 10;
            int initialValue = 1;
            DefaultBoundedRangeModel radiusModel = new DefaultBoundedRangeModel(initialValue, 0, min, max);
            JSlider radiusSlider = new JSlider(radiusModel);
            radiusSlider.setMajorTickSpacing(1);
            radiusSlider.setPaintTicks(true);
            radiusSlider.setPaintLabels(true);
            int option = JOptionPane.showOptionDialog(null, radiusSlider, bundle.getString("FilterValueDesc"),
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                radius = radiusModel.getValue();
            }

            // Create and apply the filter
            target.getImage().apply(new GaussianB(radius, radius / 3)); // TRY MAKE USER ENTER A NUMBER HERE
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * <p>
     * Action to remove noise with a Median filter.
     * </p>
     * 
     * @see MeanFilter
     */
    public class MedianAction extends ImageAction {

        /**
         * <p>
         * Create a new Median-filter action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        MedianAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * This method is called whenever the MedianAction is triggered.
         * It prompts the user the number of kernels, then applys an appropriately sized
         * {@link MedianFilter}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            int kernel = 1;

            // Pop-up dialog box to ask for the radius value, using a JSlider
            int min = 1;
            int max = 10;
            int initialValue = 1;
            DefaultBoundedRangeModel radiusModel = new DefaultBoundedRangeModel(initialValue, 0, min, max);
            JSlider radiusSlider = new JSlider(radiusModel);
            radiusSlider.setMajorTickSpacing(1);
            radiusSlider.setPaintTicks(true);
            radiusSlider.setPaintLabels(true);
            int option = JOptionPane.showOptionDialog(null, radiusSlider, "Enter number of kernels",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                kernel = radiusModel.getValue();
            }

            // Create and apply the filter
            target.getImage().apply(new MedianFilter(kernel)); // TRY MAKE USER ENTER A NUMBER HERE
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * <p>
     * Action to blur an image with a SoftBlur filter.
     * </p>
     * 
     * @see SoftBlur
     */
    public class SoftBlurAction extends ImageAction {

        /**
         * <p>
         * Create a new SoftBlur action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        SoftBlurAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * This method is called whenever the SoftBlurAction is triggered.
         * Applys an appropriately sized {@link SoftBlur}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            // Create and apply the filter
            target.getImage().apply(new SoftBlur());
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * <p>
     * Action to sharpen an image with a Sharpen filter.
     * </p>
     * 
     * @see Sharpen
     */
    public class SharpenAction extends ImageAction {

        /**
         * <p>
         * Create a new Sharpen Filter action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        SharpenAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * This method is called whenever the MeanFilterAction is triggered.
         * Applys an appropriately sized {@link Sharpen}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            // Create and apply the filter
            target.getImage().apply(new Sharpen());
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * <p>
     * Action to apply an Sobel Filter.
     * </p>
     *
     * @see Sobel
     */
    public class SobelAction extends ImageAction {

        /**
         * <p>
         * Create a new Emboss Filter action.
         * </p>
         *
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        SobelAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * This method is called whenever the MeanFilterAction is triggered.
         * Applys an appropriately sized {@link Sobel}.
         * </p>
         *
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            // Create and apply the filter
            target.getImage().apply(new Sobel());
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * <p>
     * Action to apply an Emboss Filter.
     * </p>
     *
     * @see Emboss
     */
    public class EmbossAction extends ImageAction {

        /**
         * <p>
         * Create a new Emboss Filter action.
         * </p>
         *
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        EmbossAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * This method is called whenever the MeanFilterAction is triggered.
         * Applys an appropriately sized {@link Emboss}.
         * </p>
         *
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            // Create and apply the filter
            target.getImage().apply(new Emboss());
            target.repaint();
            target.getParent().revalidate();
        }
    }
}
