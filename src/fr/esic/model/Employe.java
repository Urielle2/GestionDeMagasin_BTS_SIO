package fr.esic.model;

public class Employe {

    private String nom;
    private String prenom;
    private String poste;
    private double salaire;

    public Employe(String nom, String prenom, String poste, double salaire) {
        this.nom = nom;
        this.prenom = prenom;
        this.poste = poste;
        this.salaire = salaire;
    }

    public String getNom() {
        return nom;
    }

    public String infoEmploye() {
        return prenom + " " + nom + " | Poste : " + poste + " | Salaire : " + salaire + "€";
    }
}

