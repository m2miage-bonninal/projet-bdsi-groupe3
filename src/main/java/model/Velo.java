/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import model.enumeration.Etat;
import model.enumeration.Modele;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author emerik
 */
@Entity
public class Velo {
    
    public Velo(Modele modele, Date misEnService){
        this.setModele(modele);
        this.setMisEnService(misEnService);
        this.setEtat(Etat.OK);
    }
    
    @Id
    @GenericGenerator(name = "kaugen1", strategy = "increment")
    @GeneratedValue(generator = "kaugen1")
    private Long numero;
    
    private Modele modele;
    
    private Date misEnService;
    
    private Etat etat;
    
    @OneToOne
    private Bornette bornette;
    
    @ManyToMany
    private List<Location> locations;

    public List<Location> getListeLocation() {
        return locations;
    }

    public void setListeLocation(List<Location> listeLocation) {
        this.locations = listeLocation;
    }

    public Bornette getBornette() {
        return bornette;
    }

    public void setBornette(Bornette bornette) {
        this.bornette = bornette;
    }

    public Long getNumero() {
        return numero;
    }

    public Modele getModele() {
        return modele;
    }

    public void setModele(Modele modele) {
        this.modele = modele;
    }

    public Date getMisEnService() {
        return misEnService;
    }

    public void setMisEnService(Date misEnService) {
        this.misEnService = misEnService;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }
    
    
}
