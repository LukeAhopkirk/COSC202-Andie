package cosc202.andie;

import java.awt.Image;
import java.awt.event.KeyEvent;
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
 * created which stores all the JButtons. Each JButton
 * has a specific event attatched.
 * </p>
 * 
 */
public class Toolbar extends Andie {

        // Defualt value for the icon size
        final static int defaultHeight = 20;
        final static int defaultWidth = 20;

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

        /**
         * <p>
         * Method that when called, creates all the events that
         * are triggered when a specific JButton is clicked,
         * within the JToolBar
         * </p>
         */
        public static void createEvents() {
                // Add open icon & event
                ImageIcon icon0 = new ImageIcon(Andie.class.getClassLoader().getResource("OPENICON.png"));
                JButton openButton = Toolbar.crateButton(icon0, "Open Image");
                addButtonList(openButton);
                FileActions fileActions0 = new FileActions(); // create an instance of the outer class
                FileActions.FileOpenAction openAction = fileActions0.new FileOpenAction(bundle.getString("Open"), null,
                                bundle.getString("OpenDesc"), KeyEvent.VK_O);
                // Add the openAction as an ActionListener to the openButton
                openButton.addActionListener(openAction);

                // Add save icon & event
                ImageIcon icon = new ImageIcon(Andie.class.getClassLoader().getResource("SAVEICON.png"));
                JButton saveButton = Toolbar.crateButton(icon, "Save Image");
                addButtonList(saveButton);
                FileActions fileActions1 = new FileActions(); // create an instance of the outer class
                FileActions.FileSaveAction saveAction = fileActions1.new FileSaveAction(bundle.getString("Save"), null,
                                bundle.getString("SaveDesc"), KeyEvent.VK_S);
                saveButton.addActionListener(saveAction);

                // Add rotate right icon & event
                ImageIcon icon1 = new ImageIcon(Andie.class.getClassLoader().getResource("ROTATE-RIGHT.png"));
                JButton rotateRightButton = Toolbar.crateButton(icon1, "Rotate Right 90 degrees");
                addButtonList(rotateRightButton);
                AdjustImageT adjustImage1 = new AdjustImageT(); // Create an instance of the outer class
                // Create a new AdjustImageT.RotateRightAction instance
                AdjustImageT.RotateRightAction rotateActionR = adjustImage1.new RotateRightAction(
                                bundle.getString("RotateRight"),
                                null, bundle.getString("RotateRightDesc"),
                                Integer.valueOf(KeyEvent.VK_F));
                // Add the rotateActionR as an ActionListener to the rotateButton
                rotateRightButton.addActionListener(rotateActionR);

                // Add rotate left icon & event
                ImageIcon icon2 = new ImageIcon(Andie.class.getClassLoader().getResource("ROTATE-LEFT.png"));
                JButton rotateLeftButton = Toolbar.crateButton(icon2, "Rotate Left 90 degrees");
                addButtonList(rotateLeftButton);
                AdjustImageT adjustImage2 = new AdjustImageT(); // Create a new AdjustImageT instance
                // Create a new AdjustImageT.RotateRightAction instance
                AdjustImageT.RotateLeftAction rotateActionL = adjustImage2.new RotateLeftAction(
                                bundle.getString("RotateLeft"),
                                null, bundle.getString("RotateLeftDesc"),
                                Integer.valueOf(KeyEvent.VK_L));
                // Add the rotateActionL as an ActionListener to the rotateButton
                rotateLeftButton.addActionListener(rotateActionL);

                // Add greyscale icon & event
                ImageIcon icon3 = new ImageIcon(Andie.class.getClassLoader().getResource("BANDW.png"));
                JButton greyScaleButton = Toolbar.crateButton(icon3, "Convert to greyscale");
                addButtonList(greyScaleButton);
                ColourActions colourAction = new ColourActions(); // Create a new colourAction instance
                // Create a new ColourActions.ConvertToGreyAction instance
                ColourActions.ConvertToGreyAction greyAction = colourAction.new ConvertToGreyAction(
                                bundle.getString("Greyscale"),
                                null, bundle.getString("GreyscaleDesc"),
                                Integer.valueOf(KeyEvent.VK_L));
                // Add the greyAction as an ActionListener to the greyScaleButton
                greyScaleButton.addActionListener(greyAction);

                // Add brightness icon & event
                ImageIcon icon4 = new ImageIcon(Andie.class.getClassLoader().getResource("BRIGHTNESS.png"));
                JButton brightnessButton = Toolbar.crateButton(icon4, "Change brightness");
                addButtonList(brightnessButton);
                ColourActions brightnessAction = new ColourActions(); // Create a new colourAction instance
                // Create a new ColourActions.brightnessAction instance
                ColourActions.BrightnessAction brightnessAction1 = brightnessAction.new BrightnessAction(
                                bundle.getString("Brightness"),
                                null, bundle.getString("BrightnessDesc"),
                                Integer.valueOf(KeyEvent.VK_B));
                // Add the brightnessAction as an ActionListener to the brightnessButton
                brightnessButton.addActionListener(brightnessAction1);

                // Add zoom in icon & event
                ImageIcon icon5 = new ImageIcon(Andie.class.getClassLoader().getResource("ZOOM-IN.png"));
                JButton zoomInButton = Toolbar.crateButton(icon5, "Zoom in");
                addButtonList(zoomInButton);
                ViewActions viewActions2 = new ViewActions(); // Create a new viewActions instance
                // Create a new ViewActions.ZoomInAction instance
                ViewActions.ZoomInAction zoomInAction = viewActions2.new ZoomInAction(bundle.getString("ZoomI"),
                                null, bundle.getString("ZoomIDesc"), Integer.valueOf(KeyEvent.VK_PLUS));
                // Add the zoomInAction as an ActionListener to the zoomIn
                zoomInButton.addActionListener(zoomInAction);

                // Add zoom out icon & event
                ImageIcon icon6 = new ImageIcon(Andie.class.getClassLoader().getResource("ZOOM-OUT.png"));
                JButton zoomOutButton = Toolbar.crateButton(icon6, "Zoom out");
                addButtonList(zoomOutButton);
                ViewActions viewActions3 = new ViewActions(); // Create a new viewActions instance
                // Create a new ViewActions.ZoomInAction instance
                ViewActions.ZoomOutAction zoomOutAction = viewActions3.new ZoomOutAction(bundle.getString("ZoomO"),
                                null, bundle.getString("ZoomODesc"), Integer.valueOf(KeyEvent.VK_MINUS));
                // Add the zoomInAction as an ActionListener to the zoomIn
                zoomOutButton.addActionListener(zoomOutAction);

                // Add draw circle icon & event
                ImageIcon icon7 = new ImageIcon(Andie.class.getClassLoader().getResource("CIRCLE.png"));
                JButton drawCircleButton = Toolbar.crateButton(icon7, "Draw circle");
                addButtonList(drawCircleButton);
                DrawActions drawActions1 = new DrawActions(); // Create a new viewActions instance
                // Create a new DrawActions.DrawCircleAction instance
                DrawActions.DrawCircleAction drawCircleAction = drawActions1.new DrawCircleAction(
                                bundle.getString("Circle"),
                                null, bundle.getString("CircleDesc"), Integer.valueOf(KeyEvent.VK_C));
                // Add the zoomInAction as an ActionListener to the zoomIn
                drawCircleButton.addActionListener(drawCircleAction);

                // Add draw rectangle icon & event
                ImageIcon icon8 = new ImageIcon(Andie.class.getClassLoader().getResource("RECTANGLE.png"));
                JButton drawRectangleButton = Toolbar.crateButton(icon8, "Draw rectangle");
                addButtonList(drawRectangleButton);
                DrawActions drawActions2 = new DrawActions(); // Create a new viewActions instance
                // Create a new DrawActions.DrawRectangleAction instance
                DrawActions.DrawRectangleAction drawRectangleAction = drawActions2.new DrawRectangleAction(
                                bundle.getString("Rectangle"),
                                null, bundle.getString("RectangleDesc"), Integer.valueOf(KeyEvent.VK_R));
                // Add the zoomInAction as an ActionListener to the zoomIn
                drawRectangleButton.addActionListener(drawRectangleAction);
        }
}
