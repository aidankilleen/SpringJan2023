package ie.pt;

import org.junit.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamInvestigation {

    @Test
    public void testIterate() {

        UserDao dao = new InMemoryUserDao();

        List<User> users = dao.getUsers();

        users.forEach(System.out::println);

        System.out.println("Active Users:");
        //Stream<User> activeUsers = users.stream().filter(user -> user.isActive());

        //List<User> activeUsersList = activeUsers.collect(Collectors.toList());

        //activeUsers.forEach(System.out::println);

        Optional<User> max = users.stream().max(Comparator.comparing(User::getId));

        System.out.println(max.get());



    }
}
