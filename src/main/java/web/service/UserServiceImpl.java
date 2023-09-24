package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import web.dao.UserDAO;
import web.model.User;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;


@Component
public class UserServiceImpl implements UserService {


    UserDAO dao;

    @Autowired
    public UserServiceImpl(UserDAO dao) {
        this.dao = dao;
    }

    @Override
    public void addUser(String username, String email) {
        dao.addUser(new User(username, email));
    }

    @Override
    public void removeUserById(int id) {
        dao.deleteUserById(id);
    }

    @Override
    public void editUser(User user, String username, String email, int id) {
        dao.updateUser(user, email, username, id);
    }

    @Override
    public User getUserById(int id) {
        return dao.getUserById(id);
    }

    @Override
    public List<User> allUsers() {
/*        List<User> result = dao.allUsers();
        Comparator<User> idComparator = Comparator.comparing(User::getId, (id1, id2) -> id2.compareTo(id1));
        Comparator<User> emailComparator = Comparator.comparing(User::getEmail);
        Collections.sort(result, emailComparator);
        return result;*/
        return dao.allUsers();
    }
}
