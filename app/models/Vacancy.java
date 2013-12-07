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
public class Vacancy extends Model {
 
    
    
    public String nameVacancy;
    public String descVacancy;
    public String experience;
    public String statusVacancy;
    public String salary;
    public int amount;
    public String time;
    public Date dateVacancy;
    
       
    public Vacancy (String name, String desc, String exp, String status, String sal, String time, int amount, Date date) {
        this.experience = exp;
        this.statusVacancy = status;
        this.nameVacancy = name;
        this.salary = salary;
        this.descVacancy = desc;
        this.amount = amount;
        this.time = time;
        this.dateVacancy = date;
        
                
        
               
        
    }
    
}
