package controllers;

import play.*;
import play.mvc.*;

import java.util.*;
import java.lang.Math;

import models.*;

public class MessApp extends Controller {
    public static void message(long i, long id) {

        Messages mes = Messages.findById(id);
        if (mes.statusMess.equals("не прочитанно")) {
            mes.statusMess = "прочитанно";
            mes.save();
        }
        if (i == 0) {
            i = 1;
        }
        User u = User.findById(i);
        render(mes, u);
    }
    
    public static void messageList(long i) {

        List<User> user = User.findAll();
        List<Messages> mes = Messages.findAll();
        List<Form> form = Form.findAll();
        if (i == 0) {
            i = 1;
        }
        User u = User.findById(i);
        render(user, form, mes, u);
    }

}