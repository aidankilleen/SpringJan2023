package ie.pt.springbootrestinvestigation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserDao dao;

    @GetMapping("/test")
    public User test() {
        User u = new User(1, "Alice", "alice@gmail.com", true);
        return u;
    }

    @GetMapping("")
    public List<User> getUsers() {
        return dao.getUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id) {

        User user = null;
        try {
            user = dao.getUser(id);
        } catch(UserDaoException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        try {
            dao.deleteUser(id);
        } catch(UserDaoException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@RequestBody User userToAdd ) throws UserDaoException {
        User addedUser = dao.addUser(userToAdd);
        return addedUser;
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User userToUpdate) throws UserDaoException {
        User updatedUser = dao.updateUser(userToUpdate);
        return updatedUser;
    }

}
