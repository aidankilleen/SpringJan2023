package ie.pt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    @Autowired
    UserDao dao;

    @Autowired
    TestBean tb;

    @Autowired
    @Qualifier("greeting")
    String message;

    public List<User> getAllUsers() {
        System.out.println(tb);
        System.out.println(message);

        return dao.getUsers();
    }


}
