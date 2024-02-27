package com.rest.webservices.restfulwebservices.user;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDAOService {

    public static List<User> users = new ArrayList<>();


    private static Integer usersCount = 0;

    static {
        users.add(new User(++usersCount, "Uros", LocalDate.now().minusYears(27)));
        users.add(new User(++usersCount, "Stefan", LocalDate.now().minusYears(29)));
        users.add(new User(++usersCount, "Dusan", LocalDate.now().minusYears(35)));
    }

    public List<User> findAll() {
        return users;
    }

    public User findById(int id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        return users.stream().filter(predicate).findFirst().orElse(null);
    }

    public User save(User user) {
        user.setId(++usersCount);
        users.add(user);
        return user;
    }

    public void deleteById(int id){
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        users.removeIf(predicate);
    }
}
