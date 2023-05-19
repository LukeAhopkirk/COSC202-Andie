package cosc202.andie;

import java.util.*;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.*;

import javax.swing.*;

/**
 * <p>
 * Actions provided by the Colour menu.
 * </p>
 * 
 * <p>
 * The Colour menu contains actions that affect the colour of each pixel
 * directly
 * without reference to the rest of the image.
 * This includes conversion to greyscale in the sample code, but more operations
 * will need to be added.
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
public class ColourActions {

    /** A list of actions for the Colour menu. */
    protected ArrayList<Action> actions;
    ResourceBundle bundle = ResourceBundle.getBundle("MessageBundle");

    /**
     * <p>
     * Create a set of Colour menu actions.
     * </p>
     */
    public ColourActions() {
        actions = new ArrayList<Action>();
        actions.add(new ConvertToGreyAction(bundle.getString("Greyscale"), null, bundle.getString("GreyscaleDesc"),
                Integer.valueOf(KeyEvent.VK_G)));
        actions.add(new BrightnessAction(bundle.getString("Brightness"), null, bundle.getString("BrightnessDesc"),
                Integer.valueOf(KeyEvent.VK_B)));
        actions.add(new ContrastAction(bundle.getString("Contrast"), null, bundle.getString("ContrastDesc"),
                Integer.valueOf(KeyEvent.VK_C)));
        actions.add(new SaturationAction(bundle.getString("Saturation"), null, bundle.getString("SaturationDesc"),
                Integer.valueOf(KeyEvent.VK_S)));
        actions.add(new ColoursplashAction(bundle.getString("Coloursplash"), null, bundle.getString("ColoursplashDesc"),
                Integer.valueOf(KeyEvent.VK_P)));
    }

    /**
     * <p>
     * Create a menu contianing the list of Colour actions.
     * </p>
     * 
     * @return The colour menu UI element.
     */
    public JMenu createMenu() {
        JMenu fileMenu = new JMenu(bundle.getString("Colour"));

        for (Action action : actions) {
            fileMenu.add(new JMenuItem(action));
        }

        return fileMenu;
    }

    /**
     * <p>
     * Action to convert an image to greyscale.
     * </p>
     * 
     * @see ConvertToGrey
     */
    public class ConvertToGreyAction extends ImageAction {

        /**
         * <p>
         * Create a new convert-to-grey action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        ConvertToGreyAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the convert-to-grey action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the ConvertToGreyAction is triggered.
         * It changes the image to greyscale.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new ConvertToGrey());
            target.repaint();
            target.getParent().revalidate();
        }

    }

    /**
     * <p>
     * Action to change Brightness of an image
     * </p>
     * 
     * @see Brightness
     */
    public class BrightnessAction extends ImageAction {

        /**
         * <p>
         * Create a new brightness action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        BrightnessAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the brightness action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the BrightnessAction is triggered.
         * It changes the brightness of an image.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            int brightness = 1;

            // Pop-up dialog box to ask for the brightness value using a JSlider.
            JSlider brightnessSlider = new JSlider(-100, 100, 1);
            brightnessSlider.setMajorTickSpacing(50);
            brightnessSlider.setMinorTickSpacing(10);
            brightnessSlider.setPaintTicks(true);
            brightnessSlider.setPaintLabels(true);
            brightnessSlider.setSnapToTicks(true);
            int option = JOptionPane.showOptionDialog(null, brightnessSlider, bundle.getString("BrightnessValueDesc"),
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                brightness = brightnessSlider.getValue();
            }

            // Create and apply the filter
            target.getImage().apply(new Brightness(brightness));
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * <p>
     * Action to change Contrast of an image
     * </p>
     * 
     * @see Contrast
     */
    public class ContrastAction extends ImageAction {

        /**
         * <p>
         * Create a new contrast action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        ContrastAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the contrast action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the ContrastAction is triggered.
         * It changes the contrast of an image.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            int contrast = 1;

            // Pop-up dialog box to ask for the brightness value using a JSlider.
            JSlider contrastSlider = new JSlider(-100, 100, 1);
            contrastSlider.setMajorTickSpacing(50);
            contrastSlider.setMinorTickSpacing(10);
            contrastSlider.setPaintTicks(true);
            contrastSlider.setPaintLabels(true);
            contrastSlider.setSnapToTicks(true);
            int option = JOptionPane.showOptionDialog(null, contrastSlider, bundle.getString("ContrastValueDesc"),
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                contrast = contrastSlider.getValue();
            }

            // Create and apply the filter
            target.getImage().apply(new Contrast(contrast));
            target.repaint();
            target.getParent().revalidate();
        }

    }

    /**
     * <p>
     * Action to change satuaration of an image
     * </p>
     * 
     * @see Saturation
     */
    public class SaturationAction extends ImageAction {

        /**
         * <p>
         * Create a new satuariton action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        SaturationAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the satuartion action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the SaturationAction is triggered.
         * It changes the contrast of an image.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            int saturation = 1;

            // Pop-up dialog box to ask for the brightness value using a JSlider.
            JSlider contrastSlider = new JSlider(-100, 100, 1);
            contrastSlider.setMajorTickSpacing(50);
            contrastSlider.setMinorTickSpacing(10);
            contrastSlider.setPaintTicks(true);
            contrastSlider.setPaintLabels(true);
            contrastSlider.setSnapToTicks(true);
            int option = JOptionPane.showOptionDialog(null, contrastSlider, bundle.getString("SaturationValueDesc"),
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                saturation = contrastSlider.getValue();
            }

            // Create and apply the filter
            target.getImage().apply(new Saturation(saturation));
            target.repaint();
            target.getParent().revalidate();
        }

    }

    /**
     * <p>
     * Action to change satuaration of an image
     * </p>
     * 
     * @see Coloursplash
     */
    public class ColoursplashAction extends ImageAction {

        /**
         * <p>
         * Create a new satuariton action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        ColoursplashAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the coloursplash action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the ColourSplashAction is triggered.
         * It changes the contrast of an image.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            // Load the custom cursor image
            Image cursorImage = Toolkit.getDefaultToolkit()
                    .getImage(Andie.class.getClassLoader().getResource("COLORPICKER.png"));

            // Create a custom cursor from the image
            Cursor customCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImage, new Point(0, 0),
                    "Custom Cursor");

            // Set the custom cursor
            target.setCursor(customCursor);

            JOptionPane.showMessageDialog(null, "Please click on a colour inside the image to apply the filter.");

            target.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    int pixelX = e.getX();
                    int pixelY = e.getY();
                    target.getImage().apply(new Coloursplash(pixelX, pixelY));
                    target.repaint();
                    target.getParent().revalidate();

                    // Restore the default cursor
                    target.setCursor(Cursor.getDefaultCursor());

                    target.removeMouseListener(this);
                }
            });
        }
    }

}
