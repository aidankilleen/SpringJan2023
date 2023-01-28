package ie.pt.springbootwebinvestigation;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


public class InMemoryUserDao implements UserDao {

    private List<User>users = new ArrayList<>();


    public InMemoryUserDao() {

        users.add(new User(9, "Fred", "fred@gmail.com", false));
        users.add(new User(6, "Ger", "ger@gmail.com", false));
        users.add(new User(3, "Harriet", "harriet@gmail.com", true));
        users.add(new User(4, "Ingrid", "ingrid@gmail.com", true));
        users.add(new User(2, "John", "john@gmail.com", false));
        users.add(new User(1, "Ken", "ken@gmail.com", true));
        users.add(new User(5, "Lynn", "lynn@gmail.com", true));
        users.add(new User(11, "Mary", "mary@gmail.com", false));
        users.add(new User(10, "Niall", "niall@gmail.com", true));
        users.add(new User(8, "Oscar", "oscar@gmail.com", false));
        users.add(new User(7, "Paula", "paula@gmail.com", true));
    }


    @Override
    public void close() {
        users.clear();
    }

    @Override
    public List<User> getUsers() {
        return users;
    }

    @Override
    public User getUser(int id) throws UserDaoException {
        int index = findIndex(id);
        return users.get(index);
    }

    @Override
    public void deleteUser(int id) throws UserDaoException {

        int index = findIndex(id);
        users.remove(index);
    }

    @Override
    public User updateUser(User userToUpdate) throws UserDaoException {

        int index = findIndex(userToUpdate.getId());
        users.set(index, userToUpdate);
        return userToUpdate;
    }

    @Override
    public User addUser(User newUser) throws UserDaoException {

        if (newUser.getId() == -1) {

            User lastUser = users.stream()
                                 .max((u1, u2) -> u1.getId() - u2.getId())
                                 .get();
            newUser.setId(lastUser.getId() + 1);
        } else {
            // check for duplicates


            for (User u: users) {
                if (u.getId() == newUser.getId()) {
                    // duplicate Id
                    throw new UserDaoException("Duplicate id");
                }
            }
        }
        users.add(newUser);

        return newUser;
    }

    public int findIndex(int id) throws UserDaoException {

        // Java design brief
        // 1. no uninitialised variables
        // 2. arrays are objects with bound checking
        // 3. Use Exceptions for error handling
        // 4. Use same syntax as c and c++
        // 5. OOP
        // 6. Garbage Collection
        // 7. to get GC we need a runtime environment - ie. an interpreter  -> may lead to poor performance definitely going to be slower than c c++
        // 8. Multiple Inheritance?   No.




        /*
        int[] clist = malloc(10000);

        //....

        free(clist);

        // c++

        void fn() {
            int[] list = new int[1000];




        }



        //...

        delete list;








        int[] x = {1, 2, 3};
        int y = 999;

        System.out.println(x[3]);

        x[3] = 888;


        */






        for (int i=0; i<users.size(); i++) {
            if (users.get(i).getId() == id) {
                return i;
            }
        }
        throw new UserDaoException("not found");
    }
}
