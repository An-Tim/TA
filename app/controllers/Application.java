package controllers;

import play.*;
import play.mvc.*;

import java.util.*;
import javax.mail.Message;

import models.*;

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Application extends Controller {

    public static void index() {

        List<User> user = User.findAll();
        List<Vacancy> vac = Vacancy.findAll();
        User u = User.findById((long) 1);

        render(user, vac, u);
    }

    public static void home(long i) {

        List<User> user = User.findAll();
        List<Vacancy> vac = Vacancy.findAll();
        if (i == 0) {
            i = 1;
        }
        User u = User.findById(i);

        render(user, vac, u);

    }

    public static void exit(long i) {

        home((long) 1);

    }

    public static void vacancy(long i, long id) {

        Vacancy vac = Vacancy.findById(id);
        List<Form> form = Form.findAll();
        List<Results> res = Results.findAll();
        if (i == 0) {
            i = 1;
        }
        User u = User.findById(i);
        render(vac, form, u, res);
    }

    public static void respons(long i, long id, String s, String ans) {

        Messages mes = Messages.findById(id);
        User who = User.findById(mes.whom.id);
        User whom = User.findById(mes.who.id);
        String status = "не прочитанно";
        Date date = new Date();
        Form form = Form.findById((long) 1);
        String topic = "Re:" + mes.topic;
        Messages m1 = new Messages(who, whom, ans, status, date, form, topic);
        m1.save();

        resp(i, s);
    }
    
    public static void didtest(long i) {

         userPage(i, i);
        
    }
    

    public static void resp(long i, String mes) {
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

    public static void editVacancy(long i, long id) {

        Vacancy vac = Vacancy.findById(id);
        List<Form> form = Form.findAll();
        if (i == 0) {
            i = 1;
        }
        User u = User.findById(i);
        render(vac, form, u);
    }

    public static void vacancyList(long i) {

        List<User> user = User.findAll();
        List<Vacancy> vac = Vacancy.findAll();
        List<Vacancy> vac1 = Vacancy.findAll();
        if (i == 0) {
            i = 1;
        }
        User u = User.findById(i);
        render(user, vac, u, vac1);
    }

    public static void resume(long i, long id) {

        Vacancy vac = Vacancy.findById(id);
        Form form = Form.findById((long) 1);
        List<Questions> q = Questions.findAll();
        if (i == 0) {
            i = 1;
        }
        User u = User.findById(i);
        render(vac, form, q, u);
    }

    public static void newVacancy(long i, long id) {

        List<User> user = User.findAll();
        List<Vacancy> vac = Vacancy.findAll();
        if (i == 0) {
            i = 1;
        }
        User u = User.findById(i);
        render(user, vac, u);
    }

    public static void addVac(long i,
            String nameVacancy, String experience, String salary, String time, String descVacancy, String function, String requirements, String conditions, int amount
    ) {
        Date d = new Date();
        Vacancy vac = new Vacancy(nameVacancy, descVacancy, experience, "активна", salary, time, amount, d, conditions, requirements, function);
        vac._save();
        long id = vac.id;

        vacancy(i, id);
    }

    public static void saveEditVac(long i, long id, String nameVacancy, String experience, String salary, String time, String descVacancy, String function, String requirements, String conditions, int amount
    ) {

        Vacancy vac = Vacancy.findById(id);
        vac.amount = amount;
        vac.experience = experience;
        vac.salary = salary;
        vac.descVacancy = descVacancy;
        vac.time = time;
        vac.function = function;
        vac.requirements = requirements;
        vac.conditions = conditions;
        vac._save();

        vacancy(i, id);
    }

    public static void take(long i, long ri, long ui, long vi) {

        User user = User.findById(ui);
        user.statusUser = "принят";
        user.save();
        Results res = Results.findById(ri);
        res.statusRes = "принят";
        res.save();
        Vacancy vac = Vacancy.findById(vi);
        if (vac.amount != 0) {
            vac.amount = vac.amount--;
        }
        vacancyList(i);
    }

    public static void statusVac(long i, long id) {

        Vacancy vac = Vacancy.findById(id);
        if (vac.statusVacancy.equals("активна")) {
            vac.statusVacancy = "не активна";
            vac.dateVacancy = new java.util.Date();
        } else {
            vac.statusVacancy = "активна";
            vac.dateVacancy = new java.util.Date();
        }
        vac.save();
        vacancyList(i);
    }

    public static void come(String email, String pass) {

        /*final String username = "a.tsimoshchanka@gmail.com";
         final String password = "Vedmak@1599367";

         Properties props = new Properties();
         props.put("mail.smtp.starttls.enable", "true");
         props.put("mail.smtp.auth", "true");
         props.put("mail.smtp.host", "smtp.gmail.com");
         props.put("mail.smtp.port", "587");

         Session session = Session.getInstance(props,
         new javax.mail.Authenticator() {
         protected PasswordAuthentication getPasswordAuthentication() {
         return new PasswordAuthentication(username, password);
         }
         });

         try {

         Message message = new MimeMessage(session);
         message.setFrom(new InternetAddress("a.tsimoshchanka@gmail.com"));
         message.setRecipients(Message.RecipientType.TO,
         InternetAddress.parse(email));
         message.setSubject("Testing Subject");
         message.setText("Dear Mail Crawler,"
         + "\n\n No spam to my email, please!");

         Transport.send(message);

         System.out.println("Done");

         } catch (MessagingException e) {
         throw new RuntimeException(e);
         }*/
        List<User> u = User.findAll();
        long id = 1;
        int s = u.size();
        for (int k = 1; k < s; k++) {
            if (u.get(k).pass.equals(pass) && u.get(k).email.equals(email)) {
                id = u.get(k).id;
            }
        }
        if (id != 1) {
            userPage(id, id);
        } else {

            resp((long) 1, "Пользователь не найден");
        }

    }
    
    
    public static void newEmail(long i, long id, String topic, String ans) {
        
        
        
        User who = User.findById(i);
        User whom = User.findById(id);
        String status = "не прочитанно";
        Date date = new Date();
        Form form = Form.findById((long) 1);
        
        final String username = "a.tsimoshchanka@gmail.com";
         final String password = "Vedmak@1599367";

         Properties props = new Properties();
         props.put("mail.smtp.starttls.enable", "true");
         props.put("mail.smtp.auth", "true");
         props.put("mail.smtp.host", "smtp.gmail.com");
         props.put("mail.smtp.port", "587");

         Session session = Session.getInstance(props,
         new javax.mail.Authenticator() {
         protected PasswordAuthentication getPasswordAuthentication() {
         return new PasswordAuthentication(username, password);
         }
         });

         try {

         Message message = new MimeMessage(session);
         message.setFrom(new InternetAddress("a.tsimoshchanka@gmail.com"));
         message.setRecipients(Message.RecipientType.TO,
         InternetAddress.parse(whom.email));
         message.setSubject(topic);
         message.setText(ans
         + "\n\n No spam to my email, please!");

         Transport.send(message);

         System.out.println("Done");

         } catch (MessagingException e) {
         throw new RuntimeException(e);
         }
        
        
        Messages m1 = new Messages(who, whom, ans, status, date, form, topic);
        m1.save();

        resp(i, "сообщение отправлено!!");
    }

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

    public static void newResForm(long id) {

        Vacancy vac = Vacancy.findById(id);
        render(vac);
    }
    
    
    public static void addRes(long i, long id, String fio, String pas, String education, String job, String skills, String personal, String info, String pass) {

        Vacancy v = Vacancy.findById(id);
        String vac = v.nameVacancy;
        User u = new User(pas, pass, fio, vac);
        u.save();

        Results res = new Results(u, "отправил резюме", 1, v);
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

    public static void checkRes(long i, long ui, long fi, long mi) {
        Messages mes = Messages.findById(mi);
        if (mes.statusMess.equals("не прочитанно")) {
            mes.statusMess = "прочитанно";
            mes.save();
        }

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
        us.save();
        User who = User.findById((long) 4);
        String mess = "Пройдите тест:";
        String stat = "не прочитанно";
        Date d = new Date();

        Questions q = Questions.findById((long) 1);
        
        Form f = Form.findById((long)4);

        Messages mes = new Messages(who, us, mess, stat, d, f, "тест");
        mes.save();
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

    public static void test(long i) {
        Form form = Form.findById((long)4);
        List<Questions> q = Questions.findAll();
        List<Variants> var = Variants.findAll();
        if (i == 0) {
            i = 1;
        }
        User u = User.findById(i);

        render(form, q, var, u);
    }

    public static void message(long i, long id) {

        Messages mes = Messages.findById(id);

        if (i == 0) {
            i = 1;
        }
        User u = User.findById(i);
        render(mes, u);
    }

}
