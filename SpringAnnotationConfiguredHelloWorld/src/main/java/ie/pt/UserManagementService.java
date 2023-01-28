package ie.pt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserManagementService {

    @Autowired
    UserService userService;


    public List<User> getAllTheUsers() {

        return userService.getAllUsers();
    }
}
