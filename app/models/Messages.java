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
public class Messages extends Model {
 
    public String message;
    public String statusMess;
    public Date dateMess;
    public String topic;
    
    
    @Lob
    public String content;
    
    @ManyToOne
    public Form form;
    
    @ManyToOne
    public User who;
    
    @ManyToOne
    public User whom;
    
        
    public Messages (User who, User whom, String mess, String status, Date date, Form form, String topic) {
        this.who = who;
        this.whom = whom;
        this.statusMess = status;
        this.dateMess = date;
        this.message = mess;
        this.form = form;
        this.topic = topic;
                
    }
    
}
