package cosc202.andie;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * <p>
 * A class to help create a Toolbar
 * </p>
 * 
 * <p>
 * Creates JButtons with custom text when user hovers
 * over the JButton and custom icon. An arrayList is
 * created which stores all the JButtons
 * </p>
 * 
 */
public class Toolbar extends Andie {

    // Defualt value for the icon size
    protected static int defaultHeight = 20;
    protected static int defaultWidth = 20;

    static ArrayList<JButton> JButtonList = new ArrayList<>();

    /* Default constructor */
    Toolbar() {
    }

    /**
     * <p>
     * Creates a custom JButton
     * </p>
     * 
     * <p>
     * Adds custom text and icon to a JButton, eventually
     * to be added to the JToolBar which is attached to
     * the JPanel
     * </p>
     * 
     * @param icon    The icon to add to the JButton
     * @param tipText The text to be added when user hovers over button
     * @return The JButton with image and text attached.
     */
    public static JButton crateButton(ImageIcon icon, String tipText) {
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(defaultWidth, defaultHeight,
                java.awt.Image.SCALE_AREA_AVERAGING);
        ImageIcon newIcon0 = new ImageIcon(newImg);
        JButton currButton = new JButton(newIcon0);
        currButton.setToolTipText(tipText);
        return currButton;
    }

    /**
     * <p>
     * Adds a JButton to the current list
     * </p>
     * 
     * @param currButton Current JButton to be added to the list
     */
    public static void addButtonList(JButton currButton) {
        JButtonList.add(currButton);
    }

    /**
     * <p>
     * Accessor method for the JButton list
     * </p>
     * 
     * @return ArrayList containing all the JButtons
     */
    public static ArrayList<JButton> getButtons() {
        return JButtonList;
    }
}
