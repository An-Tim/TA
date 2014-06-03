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
public class Linker extends Model {
 
    
    public int numQuest;
    public int valueQuest;
        
    @Lob
    public String content;
    
    @ManyToOne
    public Questions quest;
    
    @ManyToOne
    public Form form;
    
    public Linker (Questions q, Form form, int value, int num) {
        this.quest = quest;
        this.valueQuest = value;
        this.form = form;
        this.numQuest = num;
    }
    
}
