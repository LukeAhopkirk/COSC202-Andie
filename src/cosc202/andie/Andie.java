package cosc202.andie;

import java.awt.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

import javax.swing.*;

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

                // Actions that select / crop the image
                SelectActions selectActions = new SelectActions();
                menuBar.add(selectActions.createMenu());

                // Actions that affect draw on the image
                DrawActions drawActions = new DrawActions();
                menuBar.add(drawActions.createMenu());

                LanguageActions languageActions = new LanguageActions();
                menuBar.add(languageActions.createMenu());

                // Add a ToolBar
                JToolBar toolbar = new JToolBar();
                frame.add(toolbar, BorderLayout.WEST);
                toolbar.setLayout(new BoxLayout(toolbar, BoxLayout.Y_AXIS));

                // Create all the JButton events
                Toolbar.createEvents();

                // Add all the buttons to the JToolBar
                ArrayList<JButton> buttonList = Toolbar.getButtons();
                for (int i = 0; i < buttonList.size(); i++) {
                        toolbar.add(buttonList.get(i));
                }

                // Add a Toolbar for colours
                JToolBar toolbarColours = new JToolBar();
                frame.add(toolbarColours, BorderLayout.EAST);
                toolbarColours.setLayout(new BoxLayout(toolbarColours, BoxLayout.Y_AXIS));

                // Create all the JButtonColour events
                Toolbar.createColourEvents();

                // Add all the buttons to the JToolBar
                ArrayList<JButton> buttonColourList = Toolbar.getColourButtons();
                for (int i = 0; i < buttonColourList.size(); i++) {
                        toolbar.add(buttonColourList.get(i));
                }

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
