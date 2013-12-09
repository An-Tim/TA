package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {

    public static void index() {
        User frontUser = User.find("order by name desc").first();
        List<User> olderUser = User.find(
            "order by statusUser desc"
        ).from(1).fetch(10);
        render(frontUser, olderUser);
    }

}