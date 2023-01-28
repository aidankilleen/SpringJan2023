package ie.pt.springbootrestinvestigation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaUserDao implements UserDao {

    @Autowired
    UserRepository repo;

    @Override
    public void close() {

    }

    @Override
    public List<User> getUsers() {
        return repo.findAll();
    }

    @Override
    public User getUser(int id) throws UserDaoException {
        Optional<User> user = repo.findById(id);

        if (!user.isPresent()) {
            throw new UserDaoException("User not found: " + id);
        }
        return user.get();
    }

    @Override
    public void deleteUser(int id) throws UserDaoException {
        try {
            repo.deleteById(id);
        } catch(EmptyResultDataAccessException ex) {
            throw new UserDaoException("User not found: " + id);
        }
    }

    @Override
    public User updateUser(User userToUpdate) throws UserDaoException {
        repo.save(userToUpdate);

        return userToUpdate;
    }

    @Override
    public User addUser(User newUser) throws UserDaoException {

        User addedUser = repo.save(newUser);
        return addedUser;
    }
}
