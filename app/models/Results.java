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
public class Results extends Model {
 
    
    public int markUser;
    public String statusRes;
    
    @Lob
    public String content;
    
    @ManyToOne
    public User us;
    
    @ManyToOne
    public Vacancy vac;
    
    public Results (User us, String status, int mark, Vacancy vac) {
        this.us = us;
        this.statusRes = status;
        this.markUser = mark;
        this.vac = vac;
    }
    
}
