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
        actions.add(new ResizeAction(bundle.getString("Resize"), null, "Increase image size", Integer.valueOf(KeyEvent.VK_R)));
        //Flip
        actions.add(new FlipVAction("Flip Vertical", null, "Flip image vertically", Integer.valueOf(KeyEvent.VK_V)));
        actions.add(new FlipHAction("Flip Horizontal", null, "Flip image horizontally", Integer.valueOf(KeyEvent.VK_H)));
        // Template for adding new action
        // actions.add(new MeanFilterAction("Mean filter", null, "Apply a mean filter", Integer.valueOf(KeyEvent.VK_M)));
        

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
}
