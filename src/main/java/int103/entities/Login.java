package int103.entities;

import java.util.HashMap;
import java.util.Map;

public class Login {
    private Map<String, String> users = new HashMap<>();

    public Login(){
        users.put("oct", "oct");
        users.put("chaiyo", "chaiyo");
        users.put("kan", "kan");
        users.put("tangmo", "tangmo");
        users.put("aun", "aun");
    }
    public boolean checkLogin(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            return false;
        } else if (users.containsKey(username) && users.get(username).equals(password)) {
            return true;
        }
        return false;
    }
}
