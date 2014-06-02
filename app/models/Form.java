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
public class Form extends Model {
 
    
    
    public String nameForm;
    public String descForm;
    public String checking;
    public String statusForm;
    
        
    @ManyToOne
    public Vacancy vac;
    
    @OneToMany(mappedBy="form", cascade=CascadeType.ALL)
    public List<Questions> quest;
    
    public Form (Vacancy vac, String name, String desc, String check, String status) {
        this.vac = vac;
        this.statusForm = status;
        this.nameForm = name;
        this.checking = check;
        this.descForm = desc;
        this.quest= new ArrayList<Questions>();
        
               
        
    }
    
}
