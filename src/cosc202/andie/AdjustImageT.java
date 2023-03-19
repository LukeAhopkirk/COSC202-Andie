package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;

// Added adjust image tab - WORKING

//Adjust image tab for resize, flip and rotate
public class AdjustImageT {
    // Different options under the tab
    protected ArrayList<Action> actions;

    public AdjustImageT() {
        actions = new ArrayList<Action>();
        //Resize
        actions.add(new ResizeIAction("Resize (+50%)", null, "Increase image by 50%", Integer.valueOf(KeyEvent.VK_M)));
        actions.add(new ResizeDAction("Resize (-50%)", null, "Decrease image by 50%", Integer.valueOf(KeyEvent.VK_M)));
        //Flip
        actions.add(new FlipVAction("Flip (Vertial)", null, "Flip image vertically", Integer.valueOf(KeyEvent.VK_M)));
        actions.add(new FlipHAction("Flip (Horizontal)", null, "Flip image horizontally", Integer.valueOf(KeyEvent.VK_M)));
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

    //Nested class for resizing increase action
    public class ResizeIAction extends ImageAction {
        ResizeIAction(String name, ImageIcon icon,
        String desc, Integer mnemonic) {
        super(name, icon, desc, mnemonic);
        }

        //Calls the resize class
        public void actionPerformed(ActionEvent e) {
        // Create and apply the filter
        target.getImage().apply(new ResizeI());
        target.repaint();
        target.getParent().revalidate();
        }
    }

    //Nested class for resizing decrease action
    public class ResizeDAction extends ImageAction {
        ResizeDAction(String name, ImageIcon icon,
        String desc, Integer mnemonic) {
        super(name, icon, desc, mnemonic);
        }

        //Calls the resize class
        public void actionPerformed(ActionEvent e) {
        target.getImage().apply(new ResizeD());
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
