package ie.pt.springbootjdbcinvestigation;

import java.util.List;

public interface UserDao {
    void close();

    List<User> getUsers();

    User getUser(int id) throws UserDaoException;

    void deleteUser(int id) throws UserDaoException;

    User updateUser(User userToUpdate) throws UserDaoException;

    User addUser(User newUser) throws UserDaoException;
}
