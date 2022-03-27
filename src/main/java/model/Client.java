/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.github.javafaker.Faker;
import java.util.List;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import org.hibernate.annotations.GenericGenerator;
import repository.api.ClientRepository;

/**
 *
 * @author emerik
 */
@Inheritance (strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="type")
@Entity
public abstract class Client {
    
    public Client(){};
    
    public Client(String numCB, String codeSecret){
        this.setCodeSecret(codeSecret);
        this.setNumeroCB(numCB);
    }
    
    public static String generateCode(ClientRepository clientRepo){
        String code = Faker.instance().bothify("??????");
        
        while(clientRepo.existsCode(code)){
            code = Faker.instance().bothify("??????");
        }
        
        return code;
    }
    
    @Id

    @GenericGenerator(name = "kaugen1", strategy = "increment")
    @GeneratedValue(generator = "kaugen1")
    private Long id;
    
    private String codeSecret;

    private String numeroCB;
    
    @OneToMany(mappedBy=("locataire"))
    private List<Location> locations;

    public Long getId() {
        return id;
    }

    public String getCodeSecret() {
        return codeSecret;
    }

    public void setCodeSecret(String codeSecret) {
        this.codeSecret = codeSecret;
    }

    public String getNumeroCB() {
        return numeroCB;
    }

    public void setNumeroCB(String numeroCB) {
        this.numeroCB = numeroCB;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }
    
    
    
}
