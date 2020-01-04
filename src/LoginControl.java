import java.util.ArrayList;

public class LoginControl {

    protected ArrayList<User> userList = new ArrayList<>();

    public void saveLogin() {
        Out out = new Out("login.txt");

        for (int i = 0; i < userList.size(); i++) {
            out.println(userList.get(i).getName() + ":" +
                    userList.get(i).getPassword() + ":" +
                    userList.get(i).getId());
        }
    }

    public void readLogin() {
        In in = new In("login.txt");
        String[] array = new String[3];

        while (in.hasNextLine()) {
            array = in.readLine().split(":");
            userList.add(new User(array[0], array[1], Integer.parseInt(array[2])));
        }
    }

    public boolean checkLogin(User user) {
        boolean result = false;
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getName().equals(user.getName()) &&
                    userList.get(i).getPassword().equals(user.getPassword())) {
                result = true;
                break;
            }
        }
        return result;
    }

}
