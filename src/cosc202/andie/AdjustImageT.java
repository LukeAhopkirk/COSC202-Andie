package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;

// Added adjust image tab - WORKING

//Adjust image tab for resize, flip and rotate
public class AdjustImageT {
    // Different options under the tab
    protected ArrayList<Action> actions;
    ResourceBundle bundle = ResourceBundle.getBundle("MessageBundle");



    public AdjustImageT() {
        actions = new ArrayList<Action>();
        //Resize
        actions.add(new ResizeAction(bundle.getString("Resize"), null, bundle.getString("ResizeDesc"), Integer.valueOf(KeyEvent.VK_R)));
        //Flip
        actions.add(new FlipVAction(bundle.getString("FlipV"), null, bundle.getString("FlipVDesc"), Integer.valueOf(KeyEvent.VK_V)));
        actions.add(new FlipHAction(bundle.getString("FlipH"), null, bundle.getString("FlipHDesc"), Integer.valueOf(KeyEvent.VK_H)));
        //Flips Image 90 Degrees
        actions.add(new RotateLeftAction("Rotate 90\u00B0 Left", null, "Rotate image 90\u00B0 Left", Integer.valueOf(KeyEvent.VK_F)));
        //Rotates Image 90 Degrees Right
        actions.add(new RotateRightAction("Rotate 90\u00B0 Right", null, "Rotate image 90\u00B0 Right", Integer.valueOf(KeyEvent.VK_F)));
        //Rotates image 180 degrees
        actions.add(new Rotate180Action("Rotate 180\u00B0", null, "Rotate image 180\u00B0", Integer.valueOf(KeyEvent.VK_F)));
        // Template for adding new action
        // actions.add(new MeanFilterAction(bundle.getString("Mean"), null, bundle.getString("MeanDesc"), Integer.valueOf(KeyEvent.VK_M)));
        

    }

    // Create actual tab in Andie with drop downs
    public JMenu createMenu() {
        JMenu fileMenu = new JMenu(bundle.getString("Adjust"));

        for(Action action: actions) {
            fileMenu.add(new JMenuItem(action));
        }

        return fileMenu;
    }

    //Nested class for resizing decrease action
    public class ResizeAction extends ImageAction {
        ResizeAction(String name, ImageIcon icon,
        String desc, Integer mnemonic) {
        super(name, icon, desc, mnemonic);
        }

        //Calls the resize class
        public void actionPerformed(ActionEvent e) {

            int multiplier = 100;
        
            // Pop-up dialog box to ask for the multiplier value.
            SpinnerNumberModel multiplierModel = new SpinnerNumberModel(100, 0, 200, 1);
            JSpinner multiplierSpinner = new JSpinner(multiplierModel);
            int option = JOptionPane.showOptionDialog(null, multiplierSpinner, "Enter resize %", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
    
            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                multiplier = multiplierModel.getNumber().intValue();
            }


        target.getImage().apply(new Resize(multiplier));
        target.repaint();
        target.getParent().revalidate();
        }
    }

    //Nested class for flipping vertically action
    public class FlipVAction extends ImageAction {

        FlipVAction(String name, ImageIcon icon,
        String desc, Integer mnemonic) {
        super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {

        target.getImage().apply(new FlipV());
        target.repaint();
        target.getParent().revalidate();
        }
    }

    //Nested class for flipping horizontally action
    public class FlipHAction extends ImageAction {

        FlipHAction(String name, ImageIcon icon,
        String desc, Integer mnemonic) {
        super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {

        target.getImage().apply(new FlipH());
        target.repaint();
        target.getParent().revalidate();
        }
    }

    public class RotateLeftAction extends ImageAction {

        RotateLeftAction(String name, ImageIcon icon,
        String desc, Integer mnemonic) {
        super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {

        target.getImage().apply(new Rotate90Left());
        target.repaint();
        target.getParent().revalidate();
        }
    }

    public class RotateRightAction extends ImageAction {

        RotateRightAction(String name, ImageIcon icon,
        String desc, Integer mnemonic) {
        super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {

        target.getImage().apply(new Rotate90Right());
        target.repaint();
        target.getParent().revalidate();
        }
    }

    public class Rotate180Action extends ImageAction {

        Rotate180Action(String name, ImageIcon icon,
        String desc, Integer mnemonic) {
        super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {

        target.getImage().apply(new Rotate180());
        target.repaint();
        target.getParent().revalidate();
        }
    }
}
