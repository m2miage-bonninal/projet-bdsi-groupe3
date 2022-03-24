/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author emerik
 */
@Entity
public class Location {
    
    public Location(){
        this.setTrajets(new ArrayList<Trajet>());
    }
    
    public Location(Client locataire){
        this.setLocataire(locataire);
        this.setTrajets(new ArrayList<Trajet>());
    }

    @Id
    @GenericGenerator(name = "kaugen1", strategy = "increment")
    @GeneratedValue(generator = "kaugen1")
    private Long id;
    
    private Float montant;
    
    @ManyToOne
    private Client locataire;
    
    @OneToMany
    private List<Trajet> trajets;

    public Long getId() {
        return id;
    }

    public Float getMontant() {
        return montant;
    }

    public void setMontant(Float montant) {
        this.montant = montant;
    }

    public Client getLocataire() {
        return locataire;
    }

    public void setLocataire(Client locataire) {
        this.locataire = locataire;
    }

    public List<Trajet> getTrajets() {
        return trajets;
    }

    public void setTrajets(List<Trajet> trajets) {
        this.trajets = trajets;
    }
    
    public void addTrajet(Trajet trajet){
        this.trajets.add(trajet);
    }
    
    // TODO
    public boolean verifTrajetEnCours(){
        boolean trouver = false;
        int i = 0;
        while(i < trajets.size() && trouver == false)
        {
            if(trajets.get(i).getStationFin() != null)
                trouver = true;
            
            i++;
        }
        return trouver;
    }
    
    
}
