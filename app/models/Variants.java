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
public class Variants extends Model {
 
    
    public int costVariants;
    public String variant;
    
    @Lob
    public String content;
        
    @ManyToOne
    public Linker lin;
    
    public Variants (Linker li, String vart, int cost) {
        this.lin = li;
        this.variant = vart;
        this.costVariants = cost;
    }
    
}
