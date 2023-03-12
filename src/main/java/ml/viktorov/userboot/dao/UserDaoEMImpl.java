package ml.viktorov.userboot.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import ml.viktorov.userboot.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Repository
public class UserDaoEMImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public User findById(Long id) {
        TypedQuery<User> user = entityManager.createQuery("FROM User u WHERE u.id=:id", User.class);
        user.setParameter("id" ,id);
        return user.getResultList().stream().findAny().orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    @Transactional
    public void saveUser(String firstName, String lastName) {
        User user = new User(firstName,lastName);
        entityManager.persist(user);
    }

    @Override
    @Transactional
    public void updateUser(Long id, String firstName, String lastName) {
        User user = findById(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        entityManager.merge(user);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        User user = findById(id);
        entityManager.remove(user);
    }
}
