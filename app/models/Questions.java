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
       
    public Questions (String q) {
        this.question = q;
        
    }
    
}
