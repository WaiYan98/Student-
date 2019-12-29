public class Login {

    private static String NAME = "waiyan";
    private static String PASSWORD = "007";

    public static boolean logIn(User user) {
        if (user.getName().equals(NAME) && user.getPassword().equals(PASSWORD)) {
            return true;
        }
        return false;
    }

}
