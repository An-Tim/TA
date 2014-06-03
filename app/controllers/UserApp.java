package controllers;

import play.*;
import play.mvc.*;

import java.util.*;
import java.lang.Math;

import models.*;

public class UserApp extends Controller {
    
    public static void userList(long i) {

        List<User> user = User.findAll();
        List<User> user2 = User.findAll();
        List<User> user3 = User.findAll();
        if (i == 0) {
            i = 1;
        }
        User u = User.findById(i);
        render(user, user2, user3, u);
    }
    
    public static void userPage(long i, long id) {

        User user = User.findById(id);
        if (i == 0) {
            i = 1;
        }
        User u = User.findById(i);

        render(user, u);
    }
    
    
}