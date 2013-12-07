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
public class Answers extends Model {
 
    
    public int markAnswer;
    public String answer;
    
    @Lob
    public String content;
    
    @ManyToOne
    public User us;
    
    @ManyToOne
    public Linker lin;
    
    public Answers (User us, Linker li, String answer, int mark) {
        this.us = us;
        this.lin = li;
        this.answer = answer;
        this.markAnswer = mark;
    }
    
}
