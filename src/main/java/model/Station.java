/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import model.enumeration.vType;

/**
 *
 * @author emerik
 */
@Entity
public class Station {
    
    @Id
    private Long id;
    
    private vType vType;
    
    @OneToMany
    private List<Bornette> bornettes;
    
    private String adresse;
    
    public Long getId(){
        return this.id;
    }
    
    public String getAdresse(){
        return this.adresse;
    }
    
    public List<Bornette> getBornettes(){
        return bornettes;
    }
    
    public void setBornette(List<Bornette> newList){
        bornettes = newList;
    }

    public vType getvType() {
        return vType;
    }

    public void setvType(vType vType) {
        this.vType = vType;
    }
    
}
