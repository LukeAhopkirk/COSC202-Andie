package cosc202.andie;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

import javax.swing.*;

import cosc202.andie.FileActions.FileSaveAction;
import cosc202.andie.ViewActions.ZoomInAction;

import javax.imageio.*;

/**
 * <p>
 * Main class for A Non-Destructive Image Editor (ANDIE).
 * </p>
 * 
 * <p>
 * This class is the entry point for the program.
 * It creates a Graphical User Interface (GUI) that provides access to various
 * image editing and processing operations.
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
public class Andie {

        static ResourceBundle bundle = ResourceBundle.getBundle("MessageBundle");

        /**
         * <p>
         * Launches the main GUI for the ANDIE program.
         * </p>
         * 
         * <p>
         * This method sets up an interface consisting of an active image (an
         * {@code ImagePanel})
         * and various menus which can be used to trigger operations to load, save,
         * edit, etc.
         * These operations are implemented {@link ImageOperation}s and triggerd via
         * {@code ImageAction}s grouped by their general purpose into menus.
         * </p>
         * 
         * @see ImagePanel
         * @see ImageAction
         * @see ImageOperation
         * @see FileActions
         * @see EditActions
         * @see ViewActions
         * @see FilterActions
         * @see ColourActions
         * 
         * @throws Exception if something goes wrong.
         */
        private static void createAndShowGUI() throws Exception {
                // Set up the main GUI frame
                JFrame frame = new JFrame("ANDIE");

                Image image = ImageIO.read(Andie.class.getClassLoader().getResource("icon.png"));
                frame.setIconImage(image);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                // The main content area is an ImagePanel
                ImagePanel imagePanel = new ImagePanel();
                ImageAction.setTarget(imagePanel);
                JScrollPane scrollPane = new JScrollPane(imagePanel);
                frame.add(scrollPane, BorderLayout.CENTER);

                // Add in menus for various types of action the user may perform.
                JMenuBar menuBar = new JMenuBar();

                // File menus are pretty standard, so things that usually go in File menus go
                // h5ere.
                FileActions fileActions = new FileActions();
                menuBar.add(fileActions.createMenu());

                // Likewise Edit menus are very common, so should be clear what might go here.
                EditActions editActions = new EditActions();
                menuBar.add(editActions.createMenu());

                // ------------------------------------------------
                // Adjust image tab for rotate, resize and flip
                AdjustImageT adjustActions = new AdjustImageT();
                menuBar.add(adjustActions.createMenu());
                // ------------------------------------------------

                // View actions control how the image is displayed, but do not alter its actual
                // content
                ViewActions viewActions = new ViewActions();
                menuBar.add(viewActions.createMenu());

                // Filters apply a per-pixel operation to the image, generally based on a local
                // window
                FilterActions filterActions = new FilterActions();
                menuBar.add(filterActions.createMenu());

                // Actions that affect the representation of colour in the image
                ColourActions colourActions = new ColourActions();
                menuBar.add(colourActions.createMenu());

                LanguageActions languageActions = new LanguageActions();
                menuBar.add(languageActions.createMenu());

                // Add a ToolBar
                JToolBar toolbar = new JToolBar();
                frame.add(toolbar, BorderLayout.WEST);
                toolbar.setLayout(new BoxLayout(toolbar, BoxLayout.Y_AXIS));

                // Add Buttons
                int defaultHeight = 20;
                int defaultWidth = 20;

                // Add open icon & event
                ImageIcon icon0 = new ImageIcon(Andie.class.getClassLoader().getResource("OPENICON.png"));
                Image img0 = icon0.getImage();
                Image newImg0 = img0.getScaledInstance(defaultWidth, defaultHeight,
                                java.awt.Image.SCALE_AREA_AVERAGING);
                ImageIcon newIcon0 = new ImageIcon(newImg0);
                JButton openButton = new JButton(newIcon0);
                openButton.setToolTipText("Open image");
                FileActions fileActions0 = new FileActions(); // create an instance of the outer class
                FileActions.FileOpenAction openAction = fileActions0.new FileOpenAction(bundle.getString("Open"), null,
                                bundle.getString("OpenDesc"), KeyEvent.VK_O);
                // Add the openAction as an ActionListener to the openButton
                openButton.addActionListener(openAction);

                // Add save icon & event
                ImageIcon icon = new ImageIcon(Andie.class.getClassLoader().getResource("SAVEICON.png"));
                Image img = icon.getImage();
                Image newImg = img.getScaledInstance(defaultWidth, defaultHeight, java.awt.Image.SCALE_AREA_AVERAGING);
                ImageIcon newIcon = new ImageIcon(newImg);
                JButton saveButton = new JButton(newIcon);
                saveButton.setToolTipText("Save image");
                FileActions fileActions1 = new FileActions(); // create an instance of the outer class
                FileActions.FileSaveAction saveAction = fileActions1.new FileSaveAction(bundle.getString("Save"), null,
                                bundle.getString("SaveDesc"), KeyEvent.VK_S);
                saveButton.addActionListener(saveAction);

                // Add rotate right icon & event
                ImageIcon icon1 = new ImageIcon(Andie.class.getClassLoader().getResource("ROTATE-RIGHT.png"));
                Image img1 = icon1.getImage();
                Image newImg1 = img1.getScaledInstance(defaultWidth, defaultHeight,
                                java.awt.Image.SCALE_AREA_AVERAGING);
                ImageIcon newIcon1 = new ImageIcon(newImg1);
                JButton rotateRightButton = new JButton(newIcon1);
                rotateRightButton.setToolTipText("Rotate Right 90 degrees");
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
                Image img2 = icon2.getImage();
                Image newImg2 = img2.getScaledInstance(defaultWidth, defaultHeight,
                                java.awt.Image.SCALE_AREA_AVERAGING);
                ImageIcon newIcon2 = new ImageIcon(newImg2);
                JButton rotateLeftButton = new JButton(newIcon2);
                rotateLeftButton.setToolTipText("Rotate Left 90 degrees");
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
                Image img3 = icon3.getImage();
                Image newImg3 = img3.getScaledInstance(defaultWidth, defaultHeight,
                                java.awt.Image.SCALE_AREA_AVERAGING);
                ImageIcon newIcon3 = new ImageIcon(newImg3);
                JButton greyScale = new JButton(newIcon3);
                greyScale.setToolTipText("Convert to greyscale");
                ColourActions colourAction = new ColourActions(); // Create a new colourAction instance
                // Create a new ColourActions.ConvertToGreyAction instance
                ColourActions.ConvertToGreyAction greyAction = colourAction.new ConvertToGreyAction(
                                bundle.getString("Greyscale"),
                                null, bundle.getString("GreyscaleDesc"),
                                Integer.valueOf(KeyEvent.VK_L));
                // Add the greyAction as an ActionListener to the greyScaleButton
                greyScale.addActionListener(greyAction);

                // Add zoom in icon & event
                ImageIcon icon4 = new ImageIcon(Andie.class.getClassLoader().getResource("ZOOM-IN.png"));
                Image img4 = icon4.getImage();
                Image newImg4 = img4.getScaledInstance(defaultWidth, defaultHeight,
                                java.awt.Image.SCALE_AREA_AVERAGING);
                ImageIcon newIcon4 = new ImageIcon(newImg4);
                JButton zoomIn = new JButton(newIcon4);
                zoomIn.setToolTipText("Zoom in");
                ViewActions viewActions2 = new ViewActions(); // Create a new viewActions instance
                // Create a new ViewActions.ZoomInAction instance
                ViewActions.ZoomInAction zoomInAction = viewActions2.new ZoomInAction(bundle.getString("ZoomI"),
                                null, bundle.getString("ZoomIDesc"), Integer.valueOf(KeyEvent.VK_PLUS));
                // Add the zoomInAction as an ActionListener to the zoomIn
                zoomIn.addActionListener(zoomInAction);

                // Add zoom out icon & event
                ImageIcon icon5 = new ImageIcon(Andie.class.getClassLoader().getResource("ZOOM-OUT.png"));
                Image img5 = icon5.getImage();
                Image newImg5 = img5.getScaledInstance(defaultWidth, defaultHeight,
                                java.awt.Image.SCALE_AREA_AVERAGING);
                ImageIcon newIcon5 = new ImageIcon(newImg5);
                JButton zoomOut = new JButton(newIcon5);
                zoomOut.setToolTipText("Zoom out");
                ViewActions viewActions3 = new ViewActions(); // Create a new viewActions instance
                // Create a new ViewActions.ZoomInAction instance
                ViewActions.ZoomOutAction zoomOutAction = viewActions3.new ZoomOutAction(bundle.getString("ZoomO"),
                                null, bundle.getString("ZoomODesc"), Integer.valueOf(KeyEvent.VK_MINUS));
                // Add the zoomInAction as an ActionListener to the zoomIn
                zoomOut.addActionListener(zoomOutAction);

                // Add brightness icon & event
                ImageIcon icon6 = new ImageIcon(Andie.class.getClassLoader().getResource("BRIGHTNESS.png"));
                Image img6 = icon6.getImage();
                Image newImg6 = img6.getScaledInstance(defaultWidth, defaultHeight,
                                java.awt.Image.SCALE_AREA_AVERAGING);
                ImageIcon newIcon6 = new ImageIcon(newImg6);
                JButton brightnessButton = new JButton(newIcon6);
                brightnessButton.setToolTipText("Change brightness");
                ColourActions brightnessAction = new ColourActions(); // Create a new colourAction instance
                // Create a new ColourActions.brightnessAction instance
                ColourActions.BrightnessAction brightnessAction1 = brightnessAction.new BrightnessAction(
                                bundle.getString("Brightness"),
                                null, bundle.getString("BrightnessDesc"),
                                Integer.valueOf(KeyEvent.VK_B));
                // Add the brightnessAction as an ActionListener to the brightnessButton
                brightnessButton.addActionListener(brightnessAction1);

                toolbar.add(openButton);
                toolbar.add(saveButton);
                toolbar.add(rotateRightButton);
                toolbar.add(rotateLeftButton);
                toolbar.add(brightnessButton);
                toolbar.add(greyScale);
                toolbar.add(zoomIn);
                toolbar.add(zoomOut);

                frame.setJMenuBar(menuBar);
                frame.pack();
                frame.setVisible(true);
        }

        /**
         * <p>
         * Main entry point to the ANDIE program.
         * </p>
         * 
         * <p>
         * Creates and launches the main GUI in a separate thread.
         * As a result, this is essentially a wrapper around {@code createAndShowGUI()}.
         * </p>
         * 
         * @param args Command line arguments, not currently used
         * @throws Exception If something goes awry
         * @see #createAndShowGUI()
         */

        public static void main(String[] args) throws Exception {

                ResourceBundle.clearCache();
                Preferences prefs = Preferences.userNodeForPackage(Andie.class);

                Locale.setDefault(new Locale(prefs.get("language", "en"),
                                prefs.get("country", "NZ")));

                ResourceBundle.clearCache();

                javax.swing.SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                                try {
                                        createAndShowGUI();
                                } catch (Exception ex) {
                                        ex.printStackTrace();
                                        System.exit(1);
                                }
                        }
                });
        }
}
