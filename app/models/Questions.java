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
public class Questions extends Model {
 
    
    public String question;
    public int numQuest;
    public int valueQuest;
    
    @ManyToOne
    public Form form;
    
    @OneToMany(mappedBy="quest", cascade=CascadeType.ALL)
    public List<Variants> var;
       
    public Questions (Form form, String q, int num, int value) {
        this.question = q;
        this.var= new ArrayList<Variants>(); 
        this.numQuest=num;
        this.valueQuest=value;
        this.form = form;
    }
    
}
