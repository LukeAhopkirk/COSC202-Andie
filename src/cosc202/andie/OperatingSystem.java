package cosc202.andie;

// To get the operating system
// OperatingSystem.getOS();
// Macbook -> mac
// Windows -> windows
// Any other OS -> other
public class OperatingSystem {
    protected OperatingSystem() {
    }

    public static String getOS() {
        String os = System.getProperty("os.name");
        if (os.startsWith("Windows")) {
            return "windows";
        } else if (os.startsWith("Mac")) {
            return "mac";
        } else {
            return "other";
        }
    }

}
