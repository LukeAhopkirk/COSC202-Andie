package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;

//Adjust image tab for resize, flip and rotate
public class AdjustImageT {
    // Different options under the tab
    protected ArrayList<Action> actions;

    public AdjustImageT() {
        actions = new ArrayList<Action>();


        // Template for adding new action
        // actions.add(new MeanFilterAction("Mean filter", null, "Apply a mean filter", Integer.valueOf(KeyEvent.VK_M)));
        

    }

    // Create actual tab in Andie with drop downs
    public JMenu createMenu() {
        JMenu fileMenu = new JMenu("Adjust");

        for(Action action: actions) {
            fileMenu.add(new JMenuItem(action));
        }

        return fileMenu;
    }

    //Nested class for resizing action
    public class ResizeAction extends ImageAction {
        // don't really know what this does atm
        ResizeAction(String name, ImageIcon icon,
        String desc, Integer mnemonic) {
        super(name, icon, desc, mnemonic);
        }

        //Calls the resize class
        public void actionPerformed(ActionEvent e) {
        // Create and apply the filter
        // idk why this is wrong yet might be problem with editable image
        target.getImage().apply(new Resize());
        target.repaint();
        target.getParent().revalidate();
        }
        }
}
