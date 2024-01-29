package Model;

import Entity.User;

import java.util.ArrayList;
import java.util.List;

public class HistoryUser{
    private static List<User> users = new ArrayList<>();

    public User saveUser(User user) {
        users.add(user);
        return user;
    }

    public static User seacrhUserByUsername(String username) {
        return users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }
}
