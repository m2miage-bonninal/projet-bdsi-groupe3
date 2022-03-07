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
import javax.persistence.OneToOne;

/**
 *
 * @author emerik
 */
@Entity
public class Location {
    
    @Id
    private Long id;
            
    private LocalDateTime dateHeureDebut;
    
    private Long duree; // durée en minute
    
    private float montant;
    
    private Boolean prime;
    
    @ManyToOne
    private Station stationDepart;
    
    @ManyToOne
    private Station stationArrivee;
    
    @ManyToMany
    private List<Velo> velo;
    
    @ManyToOne
    private Client locataire;

    public Long getId() {
        return id;
    }

    public LocalDateTime getDateDebut() {
        return dateHeureDebut;
    }

    public void setDateDebut(LocalDateTime dateDebut) {
        this.dateHeureDebut = dateDebut;
    }

    public Long getDuree() {
        return duree;
    }

    public void setDuree(Long duree) {
        this.duree = duree;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public Boolean getPrime() {
        return prime;
    }

    public void setPrime(Boolean prime) {
        this.prime = prime;
    }

    public Station getStationDepart() {
        return stationDepart;
    }

    public void setStationDepart(Station stationDepart) {
        this.stationDepart = stationDepart;
    }

    public Station getStationArrivee() {
        return stationArrivee;
    }

    public void setStationArrivee(Station stationArrivee) {
        this.stationArrivee = stationArrivee;
    }

    public List<Velo> getVelo() {
        return velo;
    }

    public void setVelo(List<Velo> velo) {
        this.velo = velo;
    }

    public Client getLocataire() {
        return locataire;
    }

    public void setLocataire(Client locataire) {
        this.locataire = locataire;
    }
    
    
}
