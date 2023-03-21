package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.prefs.Preferences;

public class LanguageActions {

    protected ArrayList<Action> actions;
    ResourceBundle bundle = ResourceBundle.getBundle("MessageBundle"); // retrieves values from resource bundles to enable translation
    Preferences prefs = Preferences.userNodeForPackage(Andie.class);
   

    public LanguageActions(){

/** a list of actions for the language menu */
        actions = new ArrayList<Action>();

        actions.add(new chineseAction("Mandarin", null, null, null));
        actions.add(new frenchAction("French", null, null, null));
        actions.add(new spanishAction("Spanish", null, null, null));
        actions.add(new englishAction("English", null,null,null));
    }
/** create a menu containing all actions in list */
public JMenu createMenu() {
    JMenu languageMenu = new JMenu(bundle.getString("Language"));

    for(Action action: actions) {
        languageMenu.add(new JMenuItem(action));
    }

    return languageMenu;
}
/** Action to change program language to spanish */
public class spanishAction extends ImageAction{

   spanishAction(String name, ImageIcon icon, String desc, Integer mnemonic){
    super(name, icon, desc, mnemonic);

}

/** sets locale to spanish */
public void actionPerformed(ActionEvent e) {

        
        Locale.setDefault(new Locale("es", "ESP"));
        ResourceBundle.clearCache();
        prefs.put("language", "es");
        prefs.put("country", "ESP");
        System.out.println("SPANISH");

    
}
    }
   /** action to set language to English */
public class englishAction extends ImageAction{

    englishAction(String name, ImageIcon icon, String desc, Integer mnemonic){
        super(name, icon, desc, mnemonic);
     
     }

        public void actionPerformed(ActionEvent e) {
           
            Locale.setDefault(new Locale("en", "NZ"));
            ResourceBundle.clearCache();
            prefs.put("language", "en");
            prefs.put("country", "NZ");
            System.out.println("ENGLISH");


        }  




}
/** action to change language to chinese */
public class chineseAction extends ImageAction{

    chineseAction(String name, ImageIcon icon, String desc, Integer mnemonic){
        super(name, icon, desc, mnemonic);
     
     }

        public void actionPerformed(ActionEvent e) {
           
            Locale.setDefault(new Locale("cn", "CHN"));
            ResourceBundle.clearCache();
            prefs.put("language", "cn");
            prefs.put("country", "CHI");
            System.out.println("CHINESE");


        }  




}
/** action to change language to French */
public class frenchAction extends ImageAction{

    frenchAction(String name, ImageIcon icon, String desc, Integer mnemonic){
        super(name, icon, desc, mnemonic);
     
     }

        public void actionPerformed(ActionEvent e) {
           
            ResourceBundle.clearCache();
            Locale.setDefault(new Locale("fr", "FRA"));
            prefs.put("language", "fr");
            prefs.put("country", "FRA");
            System.out.println("FRENCH");


        }  




}

}




