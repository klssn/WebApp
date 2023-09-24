package web.dao;

import web.model.User;
import java.util.List;

public interface UserDAO {

    void addUser(User user);

    void deleteUserById(int id);

    void updateUser(User user, String email, String username, int id);

    User getUserById(int id);
    List<User> allUsers();
}
