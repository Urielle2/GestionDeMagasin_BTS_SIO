package fr.esic.model;
 
public class Client {
 
	private String numeroClient;
	private String nom;
	private String prenom;
	private int codeFidelite;
	private double soldeFidelite;
	private double solde;
 
	public Client() {
 
	}
 
	public Client(String numeroClient, String nom, String prenom, int codeFidelite, double soldeFidelite,
			double solde) {
 
		this.numeroClient = numeroClient;
		this.nom = nom;
		this.prenom = prenom;
		this.codeFidelite = codeFidelite;
		this.soldeFidelite = soldeFidelite;
		this.solde = solde;
 
	}
 
	public String getNumeroClient() {
		return numeroClient;
	}
 
	public void setNumeroClient(String numeroClient) {
		this.numeroClient = numeroClient;
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
 
	public int getCodeFidelite() {
		return codeFidelite;
	}
 
	public void setCodeFidelite(int codeFidelite) {
		this.codeFidelite = codeFidelite;
	}
 
	public double getSoldeFidelite() {
		return soldeFidelite;
	}
 
	public void setSoldeFidelite(double soldeFidelite) {
		this.soldeFidelite = soldeFidelite;
	}
 
	public double getSolde() {
		return solde;
	}
 
	public void setSolde(double solde) {
		this.solde = solde;
	}
 
	public String infoClient() {
		return "| N° Client : " + numeroClient + " | | Nom : " + nom + " |  | Prénom : " + prenom
				+ " |  | Code Fidélité : " + codeFidelite + " | | Solde Fidélité : " + soldeFidelite + " € |  | Solde : " + solde + " € |\n";
	}
 
}
 