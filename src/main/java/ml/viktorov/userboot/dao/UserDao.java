package ml.viktorov.userboot.dao;

import ml.viktorov.userboot.model.User;

import java.util.List;

public interface UserDao {
    User findById(Long id);
    List<User> getAllUsers();

    void saveUser(String firstName, String lastName);

    void updateUser(Long id, String firstName, String lastName);

    void deleteById(Long id);

}
