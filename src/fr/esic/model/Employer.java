package fr.esic.model;

// Classe représentant un employé du magasin
// Pour différents types d'employés (caissière, manager,magasinier), on utilisera la même classe 
// Employer,mais avec des rôles différents dans les menus et les fonctionnalités accessibles.
// Ils auront tous les mêmes attributs de base (nom, prénom, mot de passe), 
// Mais ont se basera sur le mot de passe pour différencier les rôles et les accès aux différentes fonctionnalités du système.


public class Employer {

	private String nom;
	private String prenom;
	private int password;

	public Employer() {

	}

	public Employer(String nom, String prenom, int password) {
		this.nom = nom;
		this.prenom = prenom;
		this.password = password;
	}

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


	public int getPassword() {
		return password;
	}

	public void setPassword(int password) {
		this.password = password;
	}

	public String infoPerson() {
		return "Nom: " + nom + ", Prénom: " + prenom;
	}

    public String infoPersonManager() {
        return "Nom: " + nom + ", Prénom: " + prenom + ", Password: " + password;
    }
}

