package controllers;

import static controllers.Application.home;
import static controllers.Application.vacancy;
import static controllers.Application.vacancyList;
import play.*;
import play.mvc.*;

import java.util.*;
import java.lang.Math;

import models.*;

public class FormApp extends Controller {
    
    public static void newResForm(long id) {

        Vacancy vac = Vacancy.findById(id);
        render(vac);
    }
    public static void addRes(long i, long id, String fio, String pas, String education, String job, String skills, String personal, String info, String pass) {

        Vacancy v = Vacancy.findById(id);
        String vac = v.nameVacancy;
        User u = new User(pas, pass, fio, vac);
        u.save();

        Results res = new Results(u, "отправил резюме", 0, v);
        res.save();
        User whom = User.findById((long) 4);
        String mess = "Пользователь отправил резюме на рассмотрение:";
        String status = "не прочитанно";
        Date d = new Date();

        Questions q = Questions.findById((long) 1);
        Answers a1 = new Answers(u, q, fio, 0);
        a1.save();

        Form f = Form.findById(q.form.id);

        Messages mes = new Messages(u, whom, mess, status, d, f, "резюме");
        mes.save();

        Questions q2 = Questions.findById((long) 2);
        Answers a2 = new Answers(u, q2, pas, 0);
        a2.save();

        Questions q3 = Questions.findById((long) 3);
        Answers a3 = new Answers(u, q3, education, 0);
        a3.save();

        Questions q4 = Questions.findById((long) 4);
        Answers a4 = new Answers(u, q4, job, 0);
        a4.save();

        Questions q5 = Questions.findById((long) 5);
        Answers a5 = new Answers(u, q5, skills, 0);
        a5.save();

        Questions q6 = Questions.findById((long) 6);
        Answers a6 = new Answers(u, q6, personal, 0);
        a6.save();

        Questions q7 = Questions.findById((long) 7);
        Answers a7 = new Answers(u, q7, info, 0);
        a7.save();

        vacancy(i, id);

    }

    public static void refuse(long i, long ri, long ui) {

        User user = User.findById(ui);
        user.statusUser = "отказано";
        user.save();
        Results res = Results.findById(ri);
        res.statusRes = "отказано";
        res.save();
        vacancyList(i);
    }
    
     public static void newResForm(long i, long id) {

        Vacancy vac = Vacancy.findById(id);
        if (i == 0) {
            i = 1;
        }
        User u = User.findById(i);
        render(vac, u);
    }

    public static void newTask(long i, long id) {

        Vacancy vac = Vacancy.findById(id);
        if (i == 0) {
            i = 1;
        }
        User u = User.findById(i);
        render(vac, u);
    }

    public static void newTestForm(long i, long id) {

        Vacancy vac = Vacancy.findById(id);
        if (i == 0) {
            i = 1;
        }
        User u = User.findById(i);
        render(vac, u);
    }

    public static void addTask(long i, long vacId, String name, String desc) {
        Vacancy vac = Vacancy.findById(vacId);
        Form form = new Form(vac, name, desc, "1", "task");
        form.save();
        vac.form.add(form);
        if (i == 0) {
            i = 1;
        }
        User u = User.findById(i);
        render(form, u);
    }

    public static void addTest(long i, long vacId, String name, String desc) {
        Vacancy vac = Vacancy.findById(vacId);
        Form form = new Form(vac, name, desc, "1", "test");
        form._save();
        vac.form.add(form);
        if (i == 0) {
            i = 1;
        }
        User u = User.findById(i);
        render(form, u);
    }

    public static void saveTask(long i, long id, String question, String answers, int valueQuest) {
        Form form = Form.findById(id);
        Questions q = new Questions(form, question, 1, valueQuest);
        q.save();
        Variants v = new Variants(q, answers, valueQuest);
        v.save();
        if (i == 0) {
            i = 1;
        }
        User u = User.findById(i);

        render(form, u);
    }

    public static void saveTest(long i, long id, String question, String answers1, String answers2, String answers3, String answers4, int valueQuest) {
        Form form = Form.findById(id);
        Questions q = new Questions(form, question, 1, valueQuest);
        q.save();
        Variants v1 = new Variants(q, answers1, valueQuest);
        v1.save();
        Variants v2 = new Variants(q, answers2, 0);
        v2.save();
        Variants v3 = new Variants(q, answers3, 0);
        v3.save();
        Variants v4 = new Variants(q, answers4, 0);
        v4.save();
        if (i == 0) {
            i = 1;
        }
        User u = User.findById(i);

        render(form, u);
    }

    public static void readyTask(long i, long id, String question, String answers, int valueQuest) {
        Form form = Form.findById(id);

        Vacancy vac = Vacancy.findById(form.vac.id);
        vacancy(i, vac.id);

    }

    public static void finalTask(long i, long id) {
        Form form = Form.findById(id);
        List<Questions> q = Questions.findAll();
        List<Variants> var = Variants.findAll();
        if (i == 0) {
            i = 1;
        }
        User u = User.findById(i);

        render(form, q, var, u);
    }

    public static void checkRes(long i, long ui, long fi) {

        User user = User.findById(ui);
        Form form = Form.findById(fi);
        List<Questions> q = Questions.findAll();
        List<Variants> var = Variants.findAll();
        List<Answers> ans = Answers.findAll();
        if (i == 0) {
            i = 1;
        }
        User u = User.findById(i);

        render(form, q, var, u, user, ans);
    }

    public static void markRes(long i, long ui) {

        User us = User.findById(ui);
        String status = "резюме проверенно";
        int mark = 10;
        List<Results> r = Results.findAll();
        for (int k = 1; k < r.size(); k++) {
            if (r.get(k).us.id == us.id) {
                r.get(k).statusRes = status;
                r.get(k).markUser = mark;
                r.get(k).save();
            }
        }

        home(i);
    }

    public static void doTest(long i, long id) {
        Form form = Form.findById(id);
        List<Questions> q = Questions.findAll();
        List<Variants> var = Variants.findAll();
        if (i == 0) {
            i = 1;
        }
        User u = User.findById(i);

        render(form, q, var, u);
    }

    public static void test(long i, long id) {
        Form form = Form.findById(id);
        List<Questions> q = Questions.findAll();
        List<Variants> var = Variants.findAll();
        if (i == 0) {
            i = 1;
        }
        User u = User.findById(i);

        render(form, q, var, u);
    }
    
}
