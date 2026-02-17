package fr.esic.controller;

import fr.esic.model.Produit;
import fr.esic.model.Employer;
import java.util.List;
import java.util.ArrayList;

/**
 * Fournit des jeux de données en mémoire pour faciliter les tests
 * (produits et employés). Les collections sont statiques et peuvent
 * être récupérées directement ou copiées dans d'autres structures.
 */
public class Data {

    public static final List<Produit> produits = new ArrayList<>();
    public static final List<Employer> employes = new ArrayList<>();

    public static void loadData() {
        // éviter les doublons si la méthode est appelée plusieurs fois
        produits.clear();
        employes.clear();

        // dix produits d'exemple
        // produits.add(new Produit("Pommes", "Fruit rouge", 0.99, 100));
        // produits.add(new Produit("Bananes", "Fruit jaune", 0.59, 120));
        // produits.add(new Produit("Lait","Bouteille 1L", 1.29, 80));
        // produits.add(new Produit("Pain","Baguette", 0.85, 60));
        // produits.add(new Produit("Fromage","Emmental 200g", 2.49, 40));
        // produits.add(new Produit("Beurre","250g", 1.79, 50));
        // produits.add(new Produit("Œufs","Boîte de 12", 2.99, 70));
        // produits.add(new Produit("Poulet","1 kg", 5.99, 30));
        // produits.add(new Produit("Riz","Sac 1kg", 1.15, 90));
        // produits.add(new Produit("Pâtes","500g", 0.99, 110));

        // trois employés de rôles différents
        employes.add(new Employer("Dupont", "Alice", "Caissière", 1));   // caissière
        employes.add(new Employer("Martin", "Bob", "Responsable", 2));      // responsable
        employes.add(new Employer("Bernard", "Carla", "Magasinier", 3));   // magasinier
    }

    private Data() {
        // pas d'instance
    }
}
