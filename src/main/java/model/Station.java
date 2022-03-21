/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import model.enumeration.VType;
import net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition.Undefined;

import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author emerik
 */
@Entity
public class Station {
    
    public Station(String adresse){
        this.adresse = adresse;
    }
    
    @Id
    @GenericGenerator(name = "kaugen1", strategy = "increment")
    @GeneratedValue(generator = "kaugen1")
    private Long id;
        
    @OneToMany
    private List<Bornette> bornettes;
    
    private String adresse;    
    
    @OneToMany
    private List<HistoriqueVType> historiques;

    public List<HistoriqueVType> getHistoriques() {
        return historiques;
    }

    public void setHistoriques(List<HistoriqueVType> historiques) {
        this.historiques = historiques;
    }
    
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

    public List<Bornette> getBornettesLibres() {
        List<Bornette> bornettesLibres = new ArrayList<Bornette>();
        for(Bornette b : bornettes) {
            if(b.getVelo() == null) {
                bornettesLibres.add(b);
            }
        }
        return bornettesLibres;
    }

}
