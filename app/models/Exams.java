/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;
 
import java.util.*;
import javax.persistence.*;
 
import play.db.jpa.*;

/**
 *
 * @author Анастасия
 */
@Entity
public class Exams extends Model {
 
    
    public Date dateExam;
    
    @Lob
    public String content;
    
    @ManyToOne
    public User contender;
    
    @ManyToOne
    public User inter;
    
    @ManyToOne
    public Form form;
    
    public Exams (User cont, User inter, Form form, Date date) {
        this.contender = cont;
        this.inter = inter;
        this.form = form;
        this.dateExam = date;
    }
    
}
