package com.tilldawn.Models;

import com.tilldawn.database.UserDAO;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static User currentUser;
    private static List<User> users = new ArrayList<>();

    private static final UserDAO userDAO = new UserDAO();

    public static UserDAO getUserDAO() {
        return userDAO;
    }

    public static User getUserByUsername(String username) {
        return userDAO.getUserByUsername(username);
    }

    public static void addUser(User user) {
        users.add(user);
    }

    public static void removeUser(User user) {
        users.remove(user);
    }

    public static boolean userExists(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkPasswordUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user.getPassword().equals(password);
            }
        }
        return false;
    }

//    public static User getUserByUsername(String username) {
//        for (User user : users) {
//            if (user.getUsername().equals(username)) {
//                return user;
//            }
//        }
//        return null;
//    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        App.currentUser = currentUser;
    }
}
