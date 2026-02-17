package fr.esic.controller;

import fr.esic.model.Client;
import fr.esic.model.Employer;
import fr.esic.model.Produit;
import java.util.ArrayList;
import java.util.List;

/**
 * Fournit des jeux de données en mémoire pour faciliter les tests
 * (produits et employés). Les collections sont statiques et peuvent
 * être récupérées directement ou copiées dans d'autres structures.
 */
public class Data {

    public static final List<Produit> rayon = new ArrayList<>();
    public static final List<Produit> stock = new ArrayList<>(); 
    public static final List<Employer> employes = new ArrayList<>();
    public static final List<Client> clients = new ArrayList<>();
 
	

    public static void loadData() {
        // éviter les doublons si la méthode est appelée plusieurs fois
        rayon.clear();
        stock.clear();
        employes.clear();
        System.out.println("DEBUG: loadData() lancée");

        // dix produits d'exemple
        rayon.add(new Produit("Pommes", "Fruit rouge", 0.99, 100, "Rayon"));
        rayon.add(new Produit("Bananes", "Fruit jaune", 0.59, 120, "Rayon"));
        rayon.add(new Produit("Lait","Bouteille 1L", 1.29, 80, "Rayon"));
        rayon.add(new Produit("Pain","Baguette", 0.85, 60, "Rayon"));
        rayon.add(new Produit("Fromage","Emmental 200g", 2.49, 40, "Rayon"));
        rayon.add(new Produit("Beurre","250g", 1.79, 50, "Rayon"));
        rayon.add(new Produit("Œufs","Boîte de 12", 2.99, 70, "Rayon"));
        rayon.add(new Produit("Poulet","1 kg", 5.99, 30, "Rayon"));
        rayon.add(new Produit("Riz","Sac 1kg", 1.15, 90, "Rayon"));
        rayon.add(new Produit("Pâtes","500g", 0.99, 110, "Rayon"));
        System.out.println("DEBUG: " + rayon.size() + " produits chargés en rayon");

        // Produits en stock (différents du rayon)
        stock.add(new Produit("Tomates", "Boîte 500g", 1.49, 200, "Stock"));
        stock.add(new Produit("Carottes", "Botte 1kg", 0.79, 150, "Stock"));
        stock.add(new Produit("Oignons", "Sac 2kg", 1.99, 180, "Stock"));
        stock.add(new Produit("Pommes de terre", "Sac 5kg", 3.49, 120, "Stock"));
        stock.add(new Produit("Eau", "Bouteille 1.5L", 0.69, 300, "Stock"));
        System.out.println("DEBUG: " + stock.size() + " produits chargés en stock");

        // trois employés de rôles différents
        employes.add(new Employer("Dupont", "Alice", "Caissière", 1));   // caissière
        employes.add(new Employer("Martin", "Bob", "Responsable", 2));      // responsable
        employes.add(new Employer("Bernard", "Carla", "Magasinier", 3));   // magasinier
        System.out.println("DEBUG: " + employes.size() + " employés chargés");


        Client p100 = new Client("100", "WARD", "Tom", 100, 68, 1000);														
		Client p200 = new Client("200", "WULF", "Frere", 200, 27, 2000); 															
		Client p300 = new Client("300", "MALKIN", "Grim", 300, 41, 3000);
		Client p400 = new Client("400", "LEVIEUX", "Gregory", 400, 18, 4000);
 
		clients.add(p100);
		clients.add(p200);
		clients.add(p300);
		clients.add(p400);
    }



        
    private Data() {
        // pas d'instance
    }
}
