package commons;

public class GlobalConstants {
    // System Information
    public static final String PROJECT_PATH = System.getProperty("user.dir");
    public static final String OS_NAME = System.getProperty("os.name");
    public static final String SEPARATOR = System.getProperty("file.separator");

    // App Information User
    public static final String USER_URL = "https://demo.nopcommerce.com";

    // App Information Admin
    public static final String ADMIN_URL = "https://admin-demo.nopcommerce.com";
    public static final String ADMIN_EMAIL = "admin@yourstore.com";
    public static final String ADMIN_PASSWORD = "admin";

    // Waiting time
    public static final long SHORT_TIMEOUT = 3;
    public static final long LONG_TIMEOUT = 30;

    // Directory
    public static final String UPLOAD_FOLDER_PATH = PROJECT_PATH + SEPARATOR + "uploadFiles" + SEPARATOR;

}
