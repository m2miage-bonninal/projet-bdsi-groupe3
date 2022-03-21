/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.ManyToAny;
/**
 *
 * @author emerik
 */
@Entity
public class Trajet {
    
    public Trajet(Location location, Station stationDebut){
        this.setLocation(location);
        this.setStationDebut(stationDebut);
        this.setDateheureDebut(LocalDateTime.now());
    }
    
    @Id
    @GenericGenerator(name = "kaugen1", strategy = "increment")
    @GeneratedValue(generator = "kaugen1")
    private int id;
    
    private int duree;
    
    private LocalDateTime dateheureDebut;
    
    private Boolean prime;
    
    private Location location;
    
    @ManyToOne
    private Station stationDebut;
    
    @ManyToOne
    private Station stationFin;
    
    @ManyToOne
    private Velo velo;

    public int getId() {
        return id;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public LocalDateTime getDateheureDebut() {
        return dateheureDebut;
    }

    public void setDateheureDebut(LocalDateTime dateheureDebut) {
        this.dateheureDebut = dateheureDebut;
    }

    public Boolean getPrime() {
        return prime;
    }

    public void setPrime(Boolean prime) {
        this.prime = prime;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Station getStationDebut() {
        return stationDebut;
    }

    public void setStationDebut(Station stationDebut) {
        this.stationDebut = stationDebut;
    }

    public Station getStationFin() {
        return stationFin;
    }

    public void setStationFin(Station stationFin) {
        this.stationFin = stationFin;
    }

    public Velo getVelo() {
        return velo;
    }

    public void setVelo(Velo velo) {
        this.velo = velo;
    }
    
    
}
