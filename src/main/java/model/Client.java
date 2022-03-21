/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.github.javafaker.Faker;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import repository.impl.ClientRepositoryImpl;

/**
 *
 * @author emerik
 */
@Inheritance (strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="type")
@Entity
public abstract class Client {
    
    public static String generateCode(ClientRepositoryImpl clientRepo){
        String code = Faker.instance().bothify("??????");
        
        while(clientRepo.existsCode(code)){
            code = Faker.instance().bothify("??????");
        }
        
        return code;
    }
    
    @Id
    private String id;
    
    private String codeSecret;

    private Integer numeroCB;
    
    @OneToMany
    private Location locations;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
    
    
    
}
