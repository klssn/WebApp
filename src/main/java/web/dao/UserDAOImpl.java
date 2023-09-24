package web.dao;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;
import java.util.List;

@Repository
@EnableTransactionManagement
public class UserDAOImpl implements UserDAO {

    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

/*
    @Autowired
    public UserDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }*/

    @Override
    @Transactional
    public void addUser(User user) {
        resetIncr();
        entityManager.persist(user);
    }

    @Override
    @Transactional
    public void deleteUserById(int id) {
        User user = entityManager.find(User.class, id);
        if (user != null) {
            entityManager.remove(user);
        }
    }

    @Override
    @Transactional
    public void updateUser(User user, String email, String username, int id) {
        User temp = entityManager.find(User.class, id);
        if (temp != null) {
            temp.setEmail(email);
            temp.setUsername(username);
            entityManager.merge(temp);
        }
    }

    @Override
    public User getUserById(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public List<User> allUsers() {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM web.model.User u", User.class);
        return query.getResultList();

    }

    public void resetIncr() {
        Query max = entityManager.createNativeQuery("SELECT MAX(id) FROM users");
        Integer lastId = (Integer) max.getSingleResult();

        if (lastId != null) {
            Query query = entityManager.createNativeQuery("ALTER TABLE users AUTO_INCREMENT = " + (lastId + 1));
            query.executeUpdate();
        } else {
            Query query = entityManager.createNativeQuery("ALTER TABLE users AUTO_INCREMENT = 1");
            query.executeUpdate();
        }
    }
}
