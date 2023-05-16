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
public class DrawActions {

    /**
     * A list of actions for the View menu.
     */
    protected ArrayList<Action> actions;
    ResourceBundle bundle = ResourceBundle.getBundle("MessageBundle");

    // Currnt colour for drawing - default is black
    private static Color currColour = Color.BLACK;

    // Status for last drawn shape
    private static int lastX = 0, lastY = 0, lastHeight = 0, lastWidth = 0;
    private static boolean isCircle = false;
    private static boolean isCircleRunning = false;
    private static boolean isRectangleRunning = false;

    /**
     * <p>
     * Change the last x,y,height,width, status for a shape
     * </p>
     * 
     * @param color The new colour.
     */
    public static void changeLastNumbers(int x, int y, int height, int width, boolean isLastCircle) {
        lastX = x;
        lastY = y;
        lastHeight = height;
        lastWidth = width;
        isCircle = isLastCircle;
    }

    /**
     * <p>
     * Get the current colour for drawing.
     * </p>
     * 
     * @param color The new colour.
     */
    public static Color getMyColour() {
        return currColour;
    }

    /*
     * <p>
     * Get the last x co-ordinate for a shape
     */
    private static int getX() {
        return lastX;
    }

    /*
     * <p>
     * Get the last y co-ordinate for a shape
     */
    private static int getY() {
        return lastY;
    }

    /*
     * <p>
     * Get the last height for a shape
     */
    private static int getHeight() {
        return lastHeight;
    }

    /*
     * <p>
     * Get the last width for a shape
     */
    private static int getWidth() {
        return lastWidth;
    }

    /*
     * <p>
     * Get the last width for a shape
     */
    private static boolean getIsCircle() {
        return isCircle;
    }

    /**
     * <p>
     * Set the current colour for drawing.
     * </p>
     * 
     * @param color The new colour.
     */
    public static void setMyColour(Color chosenColour) {
        currColour = chosenColour;
    }

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
            isCircleRunning = true;
            if (isRectangleRunning == true) {
                JOptionPane.showMessageDialog(null, "Please finish drawing the rectangle first");
                return;
            }
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
                    isCircleRunning = false;
                    endX = e.getX();
                    endY = e.getY();
                    int width = Math.abs(endX - startX);
                    int height = Math.abs(endY - startY);
                    int x = Math.min(startX, endX);
                    int y = Math.min(startY, endY);
                    int diameter = Math.min(width, height);
                    target.getImage().apply(new Circle(getMyColour(), x, y, diameter, diameter, false));
                    changeLastNumbers(x, y, diameter, diameter, true);
                    target.repaint();
                    target.getParent().revalidate();
                    target.removeMouseListener(this);
                    target.removeMouseMotionListener(this);
                }

                @Override
                public void mouseDragged(MouseEvent e) {
                    endX = e.getX();
                    endY = e.getY();
                    int width = Math.abs(endX - startX);
                    int height = Math.abs(endY - startY);
                    int x = Math.min(startX, endX);
                    int y = Math.min(startY, endY);
                    int diameter = Math.min(width, height);
                    target.repaint();
                    Graphics2D g2d = (Graphics2D) target.getGraphics();
                    g2d.setStroke(new BasicStroke(3));
                    g2d.setColor(getMyColour());
                    g2d.drawOval(x, y, diameter, diameter);
                }
            });
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
            isRectangleRunning = true;
            System.out.println(isCircleRunning);
            if (isCircleRunning == true) {
                JOptionPane.showMessageDialog(null, "Please finish drawing the circle first");
                return;
            }
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
                    isRectangleRunning = false;
                    endX = e.getX();
                    endY = e.getY();
                    int width = Math.abs(endX - startX);
                    int height = Math.abs(endY - startY);
                    int x = Math.min(startX, endX);
                    int y = Math.min(startY, endY);
                    target.getImage().apply(new Rectangle(getMyColour(), x, y, width, height, false));
                    changeLastNumbers(x, y, width, height, false);
                    target.repaint();
                    target.getParent().revalidate();
                    target.removeMouseListener(this);
                    target.removeMouseMotionListener(this);
                }

                @Override
                public void mouseDragged(MouseEvent e) {
                    endX = e.getX();
                    endY = e.getY();
                    int width = Math.abs(endX - startX);
                    int height = Math.abs(endY - startY);
                    int x = Math.min(startX, endX);
                    int y = Math.min(startY, endY);
                    target.repaint();
                    Graphics2D g2d = (Graphics2D) target.getGraphics();
                    g2d.setStroke(new BasicStroke(2));
                    g2d.setColor(getMyColour());
                    g2d.drawRect(x, y, width, height);
                }
            });
        }
    }

    /**
     * <p>
     * Action to change the current drawing colour
     * </p>
     */
    public class ChangeColourAction implements ActionListener {

        private Color innerClassColor;

        /**
         * <p>
         * Changes the current colour
         * </p>
         * 
         */
        ChangeColourAction(Color currColour) {
            innerClassColor = currColour;
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
            setMyColour(innerClassColor);
        }

    }

    /**
     * <p>
     * Action to fill the last shape
     * </p>
     */
    public class FillShapeAction extends ImageAction {

        // private Color innerClassColor;

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
        FillShapeAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the fillshapeaction is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the fillshapeaction is triggered.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            if (getX() == 0 && getY() == 0 && getWidth() == 0 && getHeight() == 0) {
                JOptionPane.showMessageDialog(null, "No shapes drawn");
            } else {
                if (getIsCircle()) {
                    target.getImage().apply(new Circle(getMyColour(), getX(), getY(), getWidth(), getHeight(), true));
                } else {
                    target.getImage()
                            .apply(new Rectangle(getMyColour(), getX(), getY(), getHeight(), getWidth(), true));
                }
                target.repaint();
                target.getParent().revalidate();
            }
        }

    }
}
