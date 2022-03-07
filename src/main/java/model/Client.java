/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author emerik
 */
@Entity
public class Client {
    
    @Id
    private String id;
    
    private String codeSecret;

    public String getId() {
        return id;
    }


    public String getCodeSecret() {
        return codeSecret;
    }

    public void setCodeSecret(String codeSecret) {
        this.codeSecret = codeSecret;
    }

    public Integer getNumeroCB() {
        return numeroCB;
    }

    public void setNumeroCB(Integer numeroCB) {
        this.numeroCB = numeroCB;
    }

    public Location getLocations() {
        return locations;
    }

    public void setLocations(Location locations) {
        this.locations = locations;
    }
    
    private Integer numeroCB;
    
    @OneToMany
    private Location locations;
    
    
}
