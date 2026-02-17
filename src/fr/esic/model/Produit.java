package fr.esic.model;

public class Produit {
    private String nom;
    private String description;
    private double prix;
    private int stock;
    private String place;

    public Produit() {

    }

    public Produit(String nom, String description, double prix, int stock, String place) {
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.stock = stock;
        this.place = place;

    }

    public String getPlace(){
        return place;
    }

    public void setPlace(String place){
        this.place=place;
    }

    public void echange(){
        if (this.place.equalsIgnoreCase("rayon")){
            this.place="Stock";
        }else if(this.place.equalsIgnoreCase("stock")){
            this.place="Rayon";
        }
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }


    public String infoProduit() {
        return "Nom: " + nom + " | Description: " + description + " | Prix: " + prix + " | Stock: " + stock + " | Lieu: "+ place;
    }

}