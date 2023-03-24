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

        actions.add(new chineseAction(bundle.getString("Mandarin"), null, bundle.getString("ChineseDesc"), Integer.valueOf(KeyEvent.VK_M)));
        actions.add(new frenchAction(bundle.getString("French"), null, bundle.getString("FrenchDesc"), Integer.valueOf(KeyEvent.VK_F)));
        actions.add(new spanishAction(bundle.getString("Spanish"),null, bundle.getString("SpanishDesc"), Integer.valueOf(KeyEvent.VK_S)));
        actions.add(new englishAction(bundle.getString("English"), null,bundle.getString("EnglishDesc"),Integer.valueOf(KeyEvent.VK_E)));
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
            


        }  




}

}




