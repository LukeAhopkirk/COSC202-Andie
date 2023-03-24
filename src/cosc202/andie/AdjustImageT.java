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
        actions.add(new ResizeIAction(bundle.getString("Resize")+"(+50%)", null, "Increase image by 50%", Integer.valueOf(KeyEvent.VK_M)));
        actions.add(new ResizeDAction(bundle.getString("Resize")+"(-50%)", null, "Decrease image by 50%", Integer.valueOf(KeyEvent.VK_M)));
        //Flip
        actions.add(new FlipVAction(bundle.getString("FlipV"), null, "Flip image vertically", Integer.valueOf(KeyEvent.VK_M)));
        actions.add(new FlipHAction(bundle.getString("FlipH"), null, "Flip image horizontally", Integer.valueOf(KeyEvent.VK_M)));
        //Flips Image 90 Degrees
        actions.add(new Flip90Action("Flip 90\u00B0", null, "Flip image 90\u00B0", Integer.valueOf(KeyEvent.VK_F)));
        // Template for adding new action   
        

    }

    // Create actual tab in Andie with drop downs
    public JMenu createMenu() {
        JMenu fileMenu = new JMenu(bundle.getString("Adjust"));

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
    //Nested class for flipping 90 action
    
    public class Flip90Action extends ImageAction {

        Flip90Action(String name, ImageIcon icon,
        String desc, Integer mnemonic) {
        super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {

        target.getImage().apply(new Flip90());
        target.repaint();
        target.getParent().revalidate();
        }
    }
}
    