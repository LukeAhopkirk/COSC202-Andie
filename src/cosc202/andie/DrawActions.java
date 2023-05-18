package cosc202.andie;

import java.util.*;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
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
    private static boolean isLineRunning = false;
    private static boolean isFillRunning = false;
    private static boolean isColorPickerRunning = false;

    // Shape x,y,height,width
    private static int[][] shapeXYCircle = new int[100][4];
    private static int[][] shapeXYRectangle = new int[100][4];
    private static int counterCircle = 0;
    private static int counterRectangle = 0;

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
        actions.add(new DrawLineAction(bundle.getString("Line"), null, bundle.getString("LineDesc"),
                Integer.valueOf(KeyEvent.VK_L)));
        // actions.get(1).putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl
        // pressed MINUS"));
        actions.add(new FillShapeAction(bundle.getString("Fill"), null, bundle.getString("FillDesc"),
                Integer.valueOf(KeyEvent.VK_F)));
        actions.add(new ChangeClickColourAction(bundle.getString("ColourPicker"), null,
                bundle.getString("ColourPickerDesc"),
                Integer.valueOf(KeyEvent.VK_P)));

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
            if (isLineRunning) {
                JOptionPane.showMessageDialog(null, "Please finish drawing the line first");
                return;
            }
            if (isRectangleRunning) {
                JOptionPane.showMessageDialog(null, "Please finish drawing the rectangle first");
                return;
            }
            if (isFillRunning || isColorPickerRunning) {
                // Restore the default cursor
                target.setCursor(Cursor.getDefaultCursor());
                isFillRunning = false;
                isColorPickerRunning = false;
            }
            isCircleRunning = true;
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
                    int width = Math.abs(endX - startX);
                    int height = Math.abs(endY - startY);
                    int x = Math.min(startX, endX);
                    int y = Math.min(startY, endY);
                    int diameter = Math.min(width, height);
                    target.getImage().apply(new Circle(getMyColour(), x, y, diameter, diameter, false));
                    changeLastNumbers(x, y, diameter, diameter, true);
                    shapeXYCircle[counterCircle][0] = x;
                    shapeXYCircle[counterCircle][1] = y;
                    shapeXYCircle[counterCircle][2] = height;
                    shapeXYCircle[counterCircle][3] = width;
                    counterCircle++;
                    target.repaint();
                    target.getParent().revalidate();
                    target.removeMouseListener(this);
                    target.removeMouseMotionListener(this);
                    isCircleRunning = false;
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
                    g2d.setStroke(new BasicStroke(4));
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
            if (isLineRunning) {
                JOptionPane.showMessageDialog(null, "Please finish drawing the line first");
                return;
            }
            if (isCircleRunning) {
                JOptionPane.showMessageDialog(null, "Please finish drawing the circle first");
                return;
            }
            if (isFillRunning || isColorPickerRunning) {
                // Restore the default cursor
                target.setCursor(Cursor.getDefaultCursor());
                isFillRunning = false;
                isColorPickerRunning = false;
            }
            isRectangleRunning = true;
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
                    int width = Math.abs(endX - startX);
                    int height = Math.abs(endY - startY);
                    int x = Math.min(startX, endX);
                    int y = Math.min(startY, endY);
                    target.getImage().apply(new Rectangle(getMyColour(), x, y, width, height, false));
                    changeLastNumbers(x, y, width, height, false);
                    shapeXYRectangle[counterRectangle][0] = x;
                    shapeXYRectangle[counterRectangle][1] = y;
                    shapeXYRectangle[counterRectangle][2] = height;
                    shapeXYRectangle[counterRectangle][3] = width;
                    counterRectangle++;
                    target.repaint();
                    target.getParent().revalidate();
                    target.removeMouseListener(this);
                    target.removeMouseMotionListener(this);
                    isRectangleRunning = false;
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
                    g2d.setStroke(new BasicStroke(4));
                    g2d.setColor(getMyColour());
                    g2d.drawRect(x, y, width, height);
                }
            });
        }
    }

    /**
     * <p>
     * Action to draw a line
     * </p>
     */
    public class DrawLineAction extends ImageAction {

        /**
         * <p>
         * Create a new drawline action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        DrawLineAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the drawline action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the drawline is triggered.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            if (isRectangleRunning) {
                JOptionPane.showMessageDialog(null, "Please finish drawing the rectangle shape first");
                return;
            }
            if (isCircleRunning) {
                JOptionPane.showMessageDialog(null, "Please finish drawing the circle first");
                return;
            }
            if (isFillRunning || isColorPickerRunning) {
                // Restore the default cursor
                target.setCursor(Cursor.getDefaultCursor());
                isFillRunning = false;
                isColorPickerRunning = false;
            }
            isLineRunning = true;
            target.addMouseListener(new MouseAdapter() {
                int startX, startY, endX, endY;

                @Override
                public void mousePressed(MouseEvent e) {
                    startX = e.getX();
                    startY = e.getY();
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    endX = e.getX();
                    endY = e.getY();
                    target.getImage().apply(new Line(getMyColour(), startX, startY, endX, endY));
                    target.repaint();
                    target.getParent().revalidate();
                    target.removeMouseListener(this);
                    target.removeMouseMotionListener(this);
                    isLineRunning = false;
                }

                @Override
                public void mouseDragged(MouseEvent e) {
                    endX = e.getX();
                    endY = e.getY();
                    target.repaint();
                    Graphics2D g2d = (Graphics2D) target.getGraphics();
                    g2d.setStroke(new BasicStroke(4));
                    g2d.setColor(getMyColour());
                    g2d.drawLine(startX, startY, endX, endY);
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
        private boolean colourWheel;

        /**
         * <p>
         * Changes the current colour
         * </p>
         * 
         */
        ChangeColourAction(Color currColour, boolean colourWheel) {
            innerClassColor = currColour;
            this.colourWheel = colourWheel;
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
            if (colourWheel) {
                Color newColor = JColorChooser.showDialog(null, "Choose a color", innerClassColor);
                if (newColor == null) {
                    return;
                } else {
                    setMyColour(newColor);
                }
            } else {
                setMyColour(innerClassColor);
            }
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
            if (isRectangleRunning) {
                JOptionPane.showMessageDialog(null, "Please finish drawing the rectangle shape first");
                return;
            }
            if (isCircleRunning) {
                JOptionPane.showMessageDialog(null, "Please finish drawing the circle first");
                return;
            }
            if (isLineRunning) {
                JOptionPane.showMessageDialog(null, "Please finish drawing the line first");
                return;
            }
            if (isColorPickerRunning) {
                JOptionPane.showMessageDialog(null, "Please pick a colour first");
                return;
            }

            if (getX() == 0 && getY() == 0 && getWidth() == 0 && getHeight() == 0) {
                JOptionPane.showMessageDialog(null, "No shapes drawn");
            } else {
                // } else {
                // if (getIsCircle()) {
                // target.getImage().apply(new Circle(getMyColour(), getX(), getY(), getWidth(),
                // getHeight(), true));
                // } else {
                // target.getImage()
                // .apply(new Rectangle(getMyColour(), getX(), getY(), getHeight(), getWidth(),
                // true));
                // }

                isFillRunning = true;
                // Load the custom cursor image
                Image cursorImage = Toolkit.getDefaultToolkit()
                        .getImage(Andie.class.getClassLoader().getResource("PAINT.png"));
                // Create a custom cursor from the image
                Cursor customCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImage, new Point(0, 0),
                        "Custom Cursor");

                // Set the custom cursor
                target.setCursor(customCursor);

                target.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        // Co-ordinates of mouse click
                        int pixelX = e.getX();
                        int pixelY = e.getY();

                        // Checking if user click lies within any rectangles
                        for (int i = 0; i < shapeXYRectangle.length; i++) {
                            if (shapeXYRectangle[i][0] == 0 && shapeXYRectangle[i][1] == 0 &&
                                    shapeXYRectangle[i][2] == 0 && shapeXYRectangle[i][3] == 0) {
                                break;
                            }

                            int rectX = shapeXYRectangle[i][0];
                            int rectY = shapeXYRectangle[i][1];
                            int rectWidth = shapeXYRectangle[i][3];
                            int rectHeight = shapeXYRectangle[i][2];

                            if (pixelX >= rectX && pixelX <= rectX + rectWidth &&
                                    pixelY >= rectY && pixelY <= rectY + rectHeight) {
                                target.getImage().apply(new Rectangle(getMyColour(), rectX, rectY,
                                        rectWidth, rectHeight, true));
                            }
                        }

                        // Checking if user click lies within any circles
                        for (int j = 0; j < shapeXYCircle.length; j++) {
                            if (shapeXYCircle[j][0] == 0 && shapeXYCircle[j][1] == 0 && shapeXYCircle[j][2] == 0
                                    && shapeXYCircle[j][3] == 0) {
                                break;
                            }

                            int circX = shapeXYCircle[j][0];
                            int circY = shapeXYCircle[j][1];
                            int circWidth = shapeXYCircle[j][3];
                            int circHeight = shapeXYCircle[j][2];

                            if (pixelX >= circX && pixelX <= circX + circWidth &&
                                    pixelY >= circY && pixelY <= circY + circHeight) {
                                target.getImage().apply(new Circle(getMyColour(), circX, circY,
                                        Math.min(circWidth, circHeight), Math.min(circWidth, circHeight), true));
                            }
                        }

                        target.removeMouseListener(this);

                        // Restore the default cursor
                        target.setCursor(Cursor.getDefaultCursor());
                        isFillRunning = false;

                        target.repaint();
                        target.getParent().revalidate();
                    }
                });
            }
        }

    }

    /**
     * <p>
     * Action to change the current drawing colour with a user click
     * </p>
     */
    public class ChangeClickColourAction extends ImageAction {

        /**
         * <p>
         * Create a new ChangeClickColourAction action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        ChangeClickColourAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the changeclickcolour action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the changeclickcolour is triggered.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            if (isRectangleRunning) {
                JOptionPane.showMessageDialog(null, "Please finish drawing the rectangle shape first");
                return;
            }
            if (isCircleRunning) {
                JOptionPane.showMessageDialog(null, "Please finish drawing the circle first");
                return;
            }
            if (isLineRunning) {
                JOptionPane.showMessageDialog(null, "Please finish drawing the line first");
                return;
            }
            if (isFillRunning) {
                JOptionPane.showMessageDialog(null, "Please fill the shape first");
                return;
            }
            isColorPickerRunning = true;
            // Load the custom cursor image
            Image cursorImage = Toolkit.getDefaultToolkit()
                    .getImage(Andie.class.getClassLoader().getResource("COLORPICKER.png"));

            // Create a custom cursor from the image
            Cursor customCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImage, new Point(0, 0),
                    "Custom Cursor");

            // Set the custom cursor
            target.setCursor(customCursor);

            target.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    int pixelX = e.getX();
                    int pixelY = e.getY();
                    target.getImage().apply(new ColourUserPick(pixelX, pixelY));
                    target.repaint();
                    target.getParent().revalidate();

                    // Restore the default cursor
                    target.setCursor(Cursor.getDefaultCursor());
                    isColorPickerRunning = false;

                    target.removeMouseListener(this);
                }
            });
        }

    }
}
