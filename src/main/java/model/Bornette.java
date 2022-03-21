/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import model.enumeration.Etat;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author emerik
 */
@Entity
public class Bornette {
    
    public Bornette(){};
    
    public Bornette(Station station){
        this.setStation(station);
    }
    
    @Id
    @GenericGenerator(name = "kaugen1", strategy = "increment")
    @GeneratedValue(generator = "kaugen1")
    private Long numero;
    
    private Etat etat;
    
    @ManyToOne
    private Station station;
    
    @OneToOne
    private Velo veloAccroche;

    public Long getNumero() {
        return numero;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public Velo getVelo() {
        return veloAccroche;
    }

    public void setVelo(Velo velo) {
        this.veloAccroche = velo;
    }
    
    
}
