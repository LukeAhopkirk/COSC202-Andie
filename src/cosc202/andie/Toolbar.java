package cosc202.andie;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import cosc202.andie.DrawActions.ChangeColourAction;
import cosc202.andie.DrawActions.FillShapeAction;

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
        final static int defaultHeight = 23;
        final static int defaultWidth = 23;

        static ArrayList<JButton> JButtonList = new ArrayList<>();
        static ArrayList<JButton> JButtonColourList = new ArrayList<>();

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
         * Adds a JButton to the current list of colours
         * </p>
         * 
         * @param currButton Current JButton to be added to the list
         */
        public static void addButtonColourList(JButton currButton) {
                JButtonColourList.add(currButton);
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
         * Accessor method for the JButtonColour list
         * </p>
         * 
         * @return ArrayList containing all the JButtons
         */
        public static ArrayList<JButton> getColourButtons() {
                return JButtonColourList;
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

                // Add undo icon & event
                ImageIcon undo = new ImageIcon(Andie.class.getClassLoader().getResource("UNDO.png"));
                JButton undoButton = Toolbar.crateButton(undo, "Undo");
                addButtonList(undoButton);
                EditActions editActions = new EditActions(); // create an instance of the outer class
                EditActions.UndoAction undoAction = editActions.new UndoAction(bundle.getString("Undo"), null,
                                bundle.getString("UndoDesc"), KeyEvent.VK_U);
                undoButton.addActionListener(undoAction);

                // Add undo icon & event
                ImageIcon redo = new ImageIcon(Andie.class.getClassLoader().getResource("REDO.png"));
                JButton redoButton = Toolbar.crateButton(redo, "Redo");
                addButtonList(redoButton);
                EditActions editActions1 = new EditActions(); // create an instance of the outer class
                EditActions.RedoAction redoAction = editActions1.new RedoAction(bundle.getString("Redo"), null,
                                bundle.getString("RedoDesc"), KeyEvent.VK_R);
                redoButton.addActionListener(redoAction);

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
        }

        /**
         * <p>
         * Method that when called, creates all the events that
         * are triggered when a specific JButton is clicked,
         * within the colour JToolBar
         * </p>
         */
        public static void createColourEvents() {

                // Add draw circle icon & event
                ImageIcon icon7 = new ImageIcon(Andie.class.getClassLoader().getResource("CIRCLE.png"));
                JButton drawCircleButton = Toolbar.crateButton(icon7, "Draw circle");
                addButtonColourList(drawCircleButton);
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
                addButtonColourList(drawRectangleButton);
                DrawActions drawActions2 = new DrawActions(); // Create a new viewActions instance
                // Create a new DrawActions.DrawRectangleAction instance
                DrawActions.DrawRectangleAction drawRectangleAction = drawActions2.new DrawRectangleAction(
                                bundle.getString("Rectangle"),
                                null, bundle.getString("RectangleDesc"), Integer.valueOf(KeyEvent.VK_R));
                // Add the zoomInAction as an ActionListener to the zoomIn
                drawRectangleButton.addActionListener(drawRectangleAction);

                // Add draw line icon & event
                ImageIcon icon9 = new ImageIcon(Andie.class.getClassLoader().getResource("LINE.png"));
                JButton drawLineButton = Toolbar.crateButton(icon9, "Draw line");
                addButtonColourList(drawLineButton);
                DrawActions drawActions3 = new DrawActions(); // Create a new viewActions instance
                // Create a new DrawActions.DrawRectangleAction instance
                DrawActions.DrawLineAction drawLineAction = drawActions3.new DrawLineAction(
                                bundle.getString("Line"),
                                null, bundle.getString("LineDesc"), Integer.valueOf(KeyEvent.VK_L));
                // Add the zoomInAction as an ActionListener to the zoomIn
                drawLineButton.addActionListener(drawLineAction);

                // Add fill icon & event
                ImageIcon fill = new ImageIcon(Andie.class.getClassLoader().getResource("PAINT.png"));
                JButton fillButton = Toolbar.crateButton(fill, "Fill shape");
                addButtonColourList(fillButton);
                DrawActions fillAction = new DrawActions(); // create an instance of the outer class
                FillShapeAction fillShapeAction = fillAction.new FillShapeAction("Fill", null, "Fill Shape",
                                Integer.valueOf(KeyEvent.VK_F));
                // Add the openAction as an ActionListener to the openButton
                fillButton.addActionListener(fillShapeAction);

                // Add red icon & event
                ImageIcon red = new ImageIcon(Andie.class.getClassLoader().getResource("REDCIRCLE.png"));
                JButton redButton = Toolbar.crateButton(red, "Red");
                addButtonColourList(redButton);
                DrawActions redAction = new DrawActions(); // create an instance of the outer class
                ChangeColourAction changeAction = redAction.new ChangeColourAction(Color.RED);
                // Add the openAction as an ActionListener to the openButton
                redButton.addActionListener(changeAction);

                // Add blue icon & event
                ImageIcon blue = new ImageIcon(Andie.class.getClassLoader().getResource("BLUECIRCLE.png"));
                JButton blueButton = Toolbar.crateButton(blue, "Blue");
                addButtonColourList(blueButton);
                DrawActions blueAction = new DrawActions(); // create an instance of the outer class
                ChangeColourAction changeAction1 = blueAction.new ChangeColourAction(Color.BLUE);
                // Add the openAction as an ActionListener to the openButton
                blueButton.addActionListener(changeAction1);

                // Add purple icon & event
                ImageIcon purple = new ImageIcon(Andie.class.getClassLoader().getResource("PURPLECIRCLE.png"));
                JButton purpleButton = Toolbar.crateButton(purple, "Purple");
                addButtonColourList(purpleButton);
                DrawActions purpleAction = new DrawActions(); // create an instance of the outer class
                ChangeColourAction changeAction7 = purpleAction.new ChangeColourAction(new Color(128, 0, 128));
                // Add the openAction as an ActionListener to the openButton
                purpleButton.addActionListener(changeAction7);

                // Add green icon & event
                ImageIcon green = new ImageIcon(Andie.class.getClassLoader().getResource("GREENCIRCLE.png"));
                JButton greenButton = Toolbar.crateButton(green, "Green");
                addButtonColourList(greenButton);
                DrawActions greenAction = new DrawActions(); // create an instance of the outer class
                ChangeColourAction changeAction2 = greenAction.new ChangeColourAction(Color.GREEN);
                // Add the openAction as an ActionListener to the openButton
                greenButton.addActionListener(changeAction2);

                // Add yellow icon & event
                ImageIcon yellow = new ImageIcon(Andie.class.getClassLoader().getResource("YELLOWCIRCLE.png"));
                JButton yellowButton = Toolbar.crateButton(yellow, "Yellow");
                addButtonColourList(yellowButton);
                DrawActions yellowAction = new DrawActions(); // create an instance of the outer class
                ChangeColourAction changeAction3 = yellowAction.new ChangeColourAction(Color.YELLOW);
                // Add the openAction as an ActionListener to the openButton
                yellowButton.addActionListener(changeAction3);

                // Add pink icon & event
                ImageIcon pink = new ImageIcon(Andie.class.getClassLoader().getResource("PINKCIRCLE.png"));
                JButton pinkButton = Toolbar.crateButton(pink, "Pink");
                addButtonColourList(pinkButton);
                DrawActions pinkAction = new DrawActions(); // create an instance of the outer class
                ChangeColourAction changeAction4 = pinkAction.new ChangeColourAction(Color.PINK);
                // Add the openAction as an ActionListener to the openButton
                pinkButton.addActionListener(changeAction4);

                // Add orange icon & event
                ImageIcon orange = new ImageIcon(Andie.class.getClassLoader().getResource("ORANGECIRCLE.png"));
                JButton orangeButton = Toolbar.crateButton(orange, "Orange");
                addButtonColourList(orangeButton);
                DrawActions orangeAction = new DrawActions(); // create an instance of the outer class
                ChangeColourAction changeAction5 = orangeAction.new ChangeColourAction(Color.ORANGE);
                // Add the openAction as an ActionListener to the openButton
                orangeButton.addActionListener(changeAction5);

                // Add black icon & event
                ImageIcon black = new ImageIcon(Andie.class.getClassLoader().getResource("BLACKCIRCLE.png"));
                JButton blackButton = Toolbar.crateButton(black, "Black");
                addButtonColourList(blackButton);
                DrawActions blackAction = new DrawActions(); // create an instance of the outer class
                ChangeColourAction changeAction6 = blackAction.new ChangeColourAction(Color.BLACK);
                // Add the openAction as an ActionListener to the openButton
                blackButton.addActionListener(changeAction6);

                // Add custom icon & event
                ImageIcon custom = new ImageIcon(Andie.class.getClassLoader().getResource("COLORPICKER.png"));
                JButton customButton = Toolbar.crateButton(custom, "Choose a colour");
                addButtonColourList(customButton);
                DrawActions customAction = new DrawActions(); // create an instance of the outer class
                DrawActions.ChangeClickColourAction changeClickAction = customAction.new ChangeClickColourAction(
                                "Custom", null, "Choose a colour", Integer.valueOf(KeyEvent.VK_F));
                // Add the openAction as an ActionListener to the openButton
                customButton.addActionListener(changeClickAction);

        }
}
