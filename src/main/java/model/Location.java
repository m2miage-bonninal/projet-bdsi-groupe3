/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author emerik
 */
@Entity
public class Location {

    @Id
    private int id;
    
    private Float montant;
    
    @ManyToOne
    private Client locataire;
    
    @OneToMany
    private List<Trajet> trajets;

    public int getId() {
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
    
    // TODO
    boolean verifTrajetEnCours(){
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
