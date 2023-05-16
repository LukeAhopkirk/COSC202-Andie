package cosc202.andie;

import java.util.*;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
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
public class SelectActions {
    private int x, y, height, width;

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getHeight(){
        return height;
    }
    public int getWidth(){
        return width;
    }

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
    public SelectActions() {
        actions = new ArrayList<Action>();
        actions.add(new SelectAreaAction(bundle.getString("Select"), null, bundle.getString("SelectDesc"),
                Integer.valueOf(KeyEvent.VK_C)));
        actions.add(new CropAction(bundle.getString("Crop"), null, bundle.getString("CropDesc"),
                Integer.valueOf(KeyEvent.VK_C)));
       }

    /**
     * <p>
     * Create a menu containing the list of View actions.
     * </p>
     * 
     * @return The view menu UI element.
     */
    public JMenu createMenu() {
        JMenu viewMenu = new JMenu(bundle.getString("Select"));

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
    public class CropAction extends ImageAction{
        CropAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }
        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new Crop(getX(), getY(), getWidth(), getHeight()));
            target.repaint();
            target.getParent().revalidate();
        }


    }
  
    public class SelectAreaAction extends ImageAction {

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
        SelectAreaAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
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
            target.addMouseListener(new MouseAdapter() {
                int startX, startY, endX, endY;

                @Override
                public void mousePressed(MouseEvent e) {
                    startX = e.getX();
                    startY = e.getY();
                    target.addMouseMotionListener(this);
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    endX = e.getX();
                    endY = e.getY();
                    target.getParent().revalidate();
                    target.removeMouseListener(this);
                    target.removeMouseMotionListener(this);
                }

                @Override
                public void mouseDragged(MouseEvent e) {
                    endX = e.getX();
                    endY = e.getY();
                    width = Math.abs(endX - startX);
                    height = Math.abs(endY - startY);
                    x = Math.min(startX, endX);
                    y = Math.min(startY, endY);
                    target.repaint();
                    Graphics2D g2d = (Graphics2D) target.getGraphics();
                    g2d.setStroke(new BasicStroke(2));
                    g2d.setColor(Color.black);
                    g2d.drawRect(x, y, width, height);
                }
            });
        }
    }

 
}
