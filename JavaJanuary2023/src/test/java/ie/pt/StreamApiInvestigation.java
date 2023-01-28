package ie.pt;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class StreamApiInvestigation {

    @Test
    public void testIterateItemsInList() {

        Integer[] arr = { 1, 4, 3, 7, 6, 9, 10, 2, 3, 7};

        for (int i=0; i< arr.length; i++) {
            System.out.println(arr[i]);
        }
        System.out.println("------------------------------");

        for (int item : arr) {
            System.out.println(item);
        }
        System.out.println("------------------------------");

        List<Integer> list = Arrays.asList(arr);

        list.forEach((item) -> {

            System.out.println(item);
        });


        System.out.println("------------------------------");

        // finding the even numbers

        Stream<Integer> evenNumbers = list.stream().filter((item) -> {
            if (item % 2 == 0) {
                return true;
            } else {
                return false;
            }
        });

        evenNumbers.forEach(item -> System.out.println(item));


    }

    @Test
    public void testFilter() {

        Integer[] arr = {1, 3, 4, 2, 6, 5, 8, 10, 9, 7};
        List<Integer> list = Arrays.asList(arr);

        Stream<Integer> evenNumbers = list.stream().filter(item -> item % 2 == 0);

        //evenNumbers.forEach(item -> System.out.println(item));

        System.out.println("------------------------------");

        evenNumbers.forEach(System.out::println);

    }

    @Test
    public void testFindMax() {

        Integer[] arr = {1, 3, 4, 21, 6, 5, 8, 10, 9, 7};
        List<Integer> list = Arrays.asList(arr);

        Optional<Integer> max = list.stream().max((i, j) -> i.compareTo(j));

        System.out.println(max.get());

    }

    @Test
    public void testSteamObjects() {

        UserDao dao = new InMemoryUserDao();
        List<User> users = dao.getUsers();
        users.stream().forEach(System.out::println);
    }

    @Test
    public void testCallClassMethod() {

        UserDao dao = new InMemoryUserDao();
        List<User> users = dao.getUsers();
        users.stream().forEach(User::display);
    }

    @Test
    public void testFindMaxId() {

        UserDao dao = new InMemoryUserDao();
        List<User> users = dao.getUsers();
        Optional<User> lastUser = users.stream().max((u1, u2) -> u1.getId() - u2.getId());
        lastUser.get().display();
    }


    @Test
    public void testFindItem() {

        UserDao dao = new InMemoryUserDao();
        List<User> users = dao.getUsers();

        /*
        Stream<User> user4 = users.stream().filter(user -> user.getId() == 4);

        System.out.println(user4.findFirst().get());
        */

        User u4 = users.stream()
                       .filter(user -> user.getId() == 4)
                       .findFirst()
                       .get();

        System.out.println(u4);
    }

    @Test
    public void testSortList() {

        UserDao dao = new InMemoryUserDao();
        List<User> users = dao.getUsers();

        users.sort((u1, u2) -> {

            System.out.println("Comparing " + u1.getId() + " and " + u2.getId());
            return u1.getId() - u2.getId();
        });

        users.stream().forEach(System.out::println);

    }


    public int findIndex(List<User> users, int id) throws UserDaoException {
        for (int i=0; i<users.size(); i++) {

            if (users.get(i).getId() == id) {
                return i;
            }
        }
        throw new UserDaoException("not found");
    }
    @Test
    public void testFindIndexOfObject() throws UserDaoException {

        UserDao dao = new InMemoryUserDao();
        List<User> users = dao.getUsers();

        int id = 7;

        int index = findIndex(users, id);

        System.out.println(index);



    }








}











