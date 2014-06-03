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
public class User extends Model {
 
    
    public String email;
    public String pass;
    public String name;
    public String statusUser;
    
    public User(String email, String pass, String name, String statusUser) {
        this.email = email;
        this.pass = pass;
        this.name = name;
        this.statusUser = statusUser;
    }
    
    public static User connect (String email, String pass){
        return find("byEmailAndPass", email, pass).first();
    }
       
 
}
