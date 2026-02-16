package fr.esic.controller;

import fr.esic.library.OutInPut;   

import fr.esic.model.Produit;
import java.util.ArrayList;
import java.util.List;

public class ProduitController {

    public static List<Produit> produits = new ArrayList<>();

    public static void createProduit() {
        String nom = OutInPut.saisirTexte("Entrez le nom du produit : ");
        String description = OutInPut.saisirTexte("Entrez la description du produit : ");
        double prix = OutInPut.saisirDouble("Entrez le prix du produit : ");
        int quantite = OutInPut.saisirEntier("Entrez la quantité du produit : ");

        // Logique pour créer et sauvegarder le produit
        Produit produit = new Produit(nom, description, prix, quantite);
        produits.add(produit);
    }

    public static void allProduit() {
        String data = "";
        if (produits.isEmpty()) {
            OutInPut.afficher("Aucun produit disponible.");
        } else {
            for (Produit produit : produits) {
                data += produit.infoProduit() + "\n";
            }
            OutInPut.afficher(data);

        }
    }

    public static List<Produit> findByName(String nom) {
        List<Produit> result = new ArrayList<>(); // nouvelle liste
        for (Produit produit : produits) {
            if (produit.getNom().equalsIgnoreCase(nom)) {
                result.add(produit);
            }
        }
        return result;
    }

    public static void searchProduitByName() {
        List<Produit> result = findByName(OutInPut.saisirTexte("Entrez le nom du produit à rechercher : "));
        String data = "";
        if (result.isEmpty()) {
            OutInPut.afficher("Aucun produit trouvé avec ce nom.");
        } else {
            for (Produit produit : result) {
                data += produit.infoProduit() + "\n";
            }
            OutInPut.afficher(data);
        }
    }

    // recherche produit par nom si nom saisie contient des élements du nom du
    // produit
    public static List<Produit> findByNameContains(String nom) {
        List<Produit> result = new ArrayList<>();
        for (Produit produit : produits) {
            if (produit.getNom().toLowerCase().contains(nom.toLowerCase())) {
                result.add(produit);
            }
        }
        return result;
    }

    public static void searchByNameContains() {
        List<Produit> result = findByNameContains(
                OutInPut.saisirTexte("Entrez le nom (ou partie du nom) du produit à rechercher : "));
        String data = "";
        if (result.isEmpty()) {
            OutInPut.afficher("Aucun produit trouvé avec ce nom.");
        } else {
            for (Produit produit : result) {
                data += produit.infoProduit() + "\n";
            }
            OutInPut.afficher(data);
        }
    }

    // permet de choisir un produit à mettre à jour parmi une liste de produits
    public static Produit getProductToUpdate(List<Produit> result) {
        if (result.isEmpty()) {
            OutInPut.afficher("Aucun produit trouvé avec ce nom.");
            return null;
            // Si un seul produit trouvé, le retourner directement
        } else if (result.size() == 1) {
            return result.get(0);
            // Si plusieurs produits trouvés, afficher la liste et demander à l'utilisateur
            // de choisir
        } else {
            String data = "Plusieurs produits trouvés :\n";
            // Cette boucle permet d'afficher les produits avec un numéro
            // i+1 pour commencer à 1 au lieu de 0 car l'index d'une liste commence à 0 par
            // défaut
            for (int i = 0; i < result.size(); i++) {
                data += (i + 1) + ". " + result.get(i).infoProduit() + "\n";
            }
            // Demander à l'utilisateur de choisir un produit (data contient la liste des
            // produits)
            int choix = OutInPut.saisirEntier(data + "\n\nEntrez le numéro du produit à mettre à jour : ");
            // Vérifier que le choix est valide
            if (choix < 1 || choix > result.size()) {
                OutInPut.afficher("Choix invalide.");
                return null;
            } else {
                // Retourner le produit choisi (choix-1 pour correspondre à l'index de la liste)
                return result.get(choix - 1);
            }
        }
    }

    public static void updateProduct() {
        Produit produit = getProductToUpdate(
                findByNameContains(OutInPut.saisirTexte("Entrez le nom du produit à mettre à jour : ")));

        if (produit != null) {
            Produit p = new Produit();
            p = produit;
            String oldInfo = produit.infoProduit();
            String newNom = OutInPut
                    .saisirTexte("Entrez le nouveau nom du produit (laisser vide pour ne pas changer) : ");

            if (!newNom.isEmpty()) {
                produit.setNom(newNom);
            }
            String newDescription = OutInPut
                    .saisirTexte("Entrez la nouvelle description du produit (laisser vide pour ne pas changer) : ");
            if (!newDescription.isEmpty()) {
                produit.setDescription(newDescription);
            }
            String prixInput = OutInPut
                    .saisirTexte("Entrez le nouveau prix du produit (laisser vide pour ne pas changer) : ");
            if (!prixInput.isEmpty()) {
                double newPrix = Double.parseDouble(prixInput);
                produit.setPrix(newPrix);
                System.out.println(prixInput);
                System.out.println(newPrix);
            }
            String quantiteInput = OutInPut
                    .saisirTexte("Entrez la nouvelle quantité du produit (laisser vide pour ne pas changer) : ");
            if (!quantiteInput.isEmpty()) {
                int newQuantite = Integer.parseInt(quantiteInput);
                produit.setStock(newQuantite);
            }
            OutInPut.afficher("Produit mis à jour avec succès. \n" + "Ancienne info produit : " + oldInfo
                    + "\n" + "New info produit : " + produit.infoProduit());
        } else {
            OutInPut.afficher("Mise à jour annulée car aucun produit sélectionné.");
        }
    }

    public static void removeProduct() {

        Produit produit = getProductToUpdate(
                findByNameContains(OutInPut.saisirTexte("Entrez le nom du produit à supprimer : ")));

        if (produit != null) {

            String valider = OutInPut
                    .saisirTexte("Saisissez OUI pour supprimer ce produit ou NON pour le conserver : ");
            if (valider.equalsIgnoreCase("oui")) {
                produits.remove(produit);
                OutInPut.afficher("Produit supprimé avec succès");
            } else if (valider.equalsIgnoreCase("non")) {
                OutInPut.afficher("Produit conservé dans le catalogue");
            } else {
                OutInPut.afficher("Commande invalide");
            }
        }

    }

    
}