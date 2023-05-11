package cosc202.andie;

// To get the operating system
// OperatingSystem.getOS();
// Macbook -> mac
// Windows -> windows
// Any other OS -> other
public class OperatingSystem {
    private static String OS;
    protected OperatingSystem(){}
    public static String getOS(){
        String os = System.getProperty("os.name");
        if(os.startsWith("Windows")){
            OS = "windows";
        }else if(os.startsWith("Mac")){
            OS = "mac";
        }else{
            OS = "other";
        }
        return OS;
    }
    
}
