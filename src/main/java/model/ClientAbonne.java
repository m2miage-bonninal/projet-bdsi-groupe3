/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import model.enumeration.Sexe;

/**
 *
 * @author emerik
 */
@Entity
@DiscriminatorValue (value = "abonne")
public class ClientAbonne extends Client{
    public ClientAbonne(){
        super();
    }
    
    public ClientAbonne(String numCB, String codeSecret, String nom, String prenom, String dateNaissance, String adresse, Sexe sexe){
        super(numCB, codeSecret);
        this.setNom(nom);
        this.setPrenom(prenom);
        this.setDateNaissance(dateNaissance);
        this.setAdresse(adresse);
        this.setSexe(sexe);
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD");
        this.setDateSouscription(sdf.format(new Date()));
    }
    
    private String nom;
    
    private String prenom;
    
    private String dateNaissance;
    
    private String adresse;
    
    private Sexe sexe;
    
    private String dateSouscription;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Sexe getSexe() {
        return sexe;
    }

    public void setSexe(Sexe sexe) {
        this.sexe = sexe;
    }

    public String getDateSouscription() {
        return dateSouscription;
    }

    public void setDateSouscription(String dateSouscription) {
        this.dateSouscription = dateSouscription;
    }
    
    
}
