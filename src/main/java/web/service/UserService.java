package web.service;

import web.model.User;
import java.util.List;

public interface UserService {

    void addUser(String username, String email);
    void removeUserById(int id);
    void editUser(User user, String username, String email, int id);
    User getUserById(int id);
    List<User> allUsers();
}
