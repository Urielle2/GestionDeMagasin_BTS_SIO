package fr.esic.controller;

import fr.esic.model.Employer;
import fr.esic.model.Produit;
import fr.esic.library.*;
import java.util.List;
import java.util.ArrayList;

public class CassiereController {

    // panier temporaire pour la caisse
    public static List<Produit> panier = new ArrayList<>();

    /**
     * Modifier un produit du stock (identique à la logique du responsable)
     * le paramètre lstProduit est un affichage pré‑fabriqué du stock, s'il est
     * fourni on le montre ; la sélection est gérée par ProduitController.
     */
    public static void modifier() {
        // modification de la quantité d'un produit déjà scanné (dans le panier) et ajustement du stock
        if (panier.isEmpty()) {
            OutInPut.afficher("Le panier est vide, aucun produit à modifier.");
            return;
        }
        String data = "";
        for (int i = 0; i < panier.size(); i++) {
            data += (i + 1) + ". " + panier.get(i).infoProduit() + "\n";
        }
        int choix = OutInPut.saisirEntier(data + "Entrez le numéro du produit dont vous voulez changer la quantité : ");
        if (choix < 1 || choix > panier.size()) {
            OutInPut.afficher("Choix invalide.");
            return;
        }
        Produit p = panier.get(choix - 1);
        int ancienneQte = p.getStock();
        String saisie = OutInPut.saisirTexte("Quantité actuelle " + ancienneQte + ". Nouvelle quantité : ");
        if (saisie.isEmpty()) {
            OutInPut.afficher("Aucune modification effectuée.");
            return;
        }
        int nouvelleQte = Integer.parseInt(saisie); // on suppose une entrée valable
        if (nouvelleQte < 0) {
            OutInPut.afficher("La quantité ne peut pas être négative.");
            return;
        }
        int diff = nouvelleQte - ancienneQte;
        if (diff > 0) {
            // retirer du stock magasin
            for (Produit prod : ProduitController.produits) {
                if (prod.getNom().equalsIgnoreCase(p.getNom())) {
                    if (prod.getStock() < diff) {
                        OutInPut.afficher("Stock magasin insuffisant. Opération annulée.");
                        return;
                    }
                    prod.setStock(prod.getStock() - diff);
                    break;
                }
            }
        } else if (diff < 0) {
            // restituer au magasin
            for (Produit prod : ProduitController.produits) {
                if (prod.getNom().equalsIgnoreCase(p.getNom())) {
                    prod.setStock(prod.getStock() - diff);
                    break;
                }
            }
        }
        p.setStock(nouvelleQte);
        OutInPut.afficher("Quantité mise à jour dans le panier et le stock magasin.");
    }

    /**
     * Affiche le menu de scanner et exécute l'action choisie.
     */
    public static void scanner() {
        String menu = "1. Ajouter un produit\n2. Supprimer un produit\n0. Retour";
        int choix = OutInPut.saisirEntier(menu);
        switch (choix) {
            case 1:
                ajouterProduitPanier();
                break;
            case 2:
                supprimerProduitPanier();
                break;
            case 0:
                // retour à l'appelant
                break;
            default:
                OutInPut.afficher("Choix invalide. Veuillez réessayer.");
                scanner();
        }
    }

    private static void ajouterProduitPanier() {
        String nom = OutInPut.saisirTexte("Entrez le nom du produit à scanner : ");
        List<Produit> result = ProduitController.findByNameContains(nom);
        Produit selection = ProduitController.getProductToUpdate(result);
        if (selection == null) {
            OutInPut.afficher("Aucun produit ajouté.");
            return;
        }
        int quantite = OutInPut.saisirEntier("Quantité à ajouter : ");
        if (quantite <= 0) {
            OutInPut.afficher("Quantité invalide.");
            return;
        }
        if (quantite > selection.getStock()) {
            OutInPut.afficher("Stock insuffisant : " + selection.getStock());
            return;
        }
        // diminuer le stock du produit original
        selection.setStock(selection.getStock() - quantite);
        // ajouter une copie au panier
        Produit p = new Produit(selection.getNom(), selection.getDescription(), selection.getPrix(), quantite);
        panier.add(p);
        OutInPut.afficher("Produit ajouté au panier.");
    }

    private static void supprimerProduitPanier() {
        if (panier.isEmpty()) {
            OutInPut.afficher("Le panier est vide.");
            return;
        }
        String data = "";
        for (int i = 0; i < panier.size(); i++) {
            data += (i + 1) + ". " + panier.get(i).infoProduit() + "\n";
        }
        int choix = OutInPut.saisirEntier(data + "Entrez le numéro du produit à retirer : ");
        if (choix < 1 || choix > panier.size()) {
            OutInPut.afficher("Choix invalide.");
            return;
        }
        Produit p = panier.remove(choix - 1);
        // restituer le stock dans l'inventaire
        for (Produit prod : ProduitController.produits) {
            if (prod.getNom().equalsIgnoreCase(p.getNom())) {
                prod.setStock(prod.getStock() + p.getStock());
                break;
            }
        }
        OutInPut.afficher("Produit retiré du panier.");
    }


    public static void appliquerPromotion() {
        // ProduitController.applyPromotion();
    }

    /**
     * Traitement de la carte de fidélité (simulé).
     */
    public static void passerCarteFidelite() {
        String numero = OutInPut.saisirTexte("Saisissez le numéro de la carte de fidélité : ");
        // traitement simplifié
        OutInPut.afficher("Carte " + numero + " enregistrée. Remise appliquée si pertinente.");
    }

    /**
     * Calcule le total et gère le paiement.
     */
    public static void payer() {
        if (panier.isEmpty()) {
            OutInPut.afficher("Aucun article dans le panier.");
            return;
        }
        double total = 0;
        for (Produit p : panier) {
            total += p.getPrix() * p.getStock();
        }
        OutInPut.afficher("Total à payer : " + total + " €");
        double montant = OutInPut.saisirDouble("Montant reçu : ");
        if (montant < total) {
            OutInPut.afficher("Montant insuffisant. Transaction annulée.");
        } else {
            double rendu = montant - total;
            OutInPut.afficher("Paiement accepté. Retour : " + rendu + " €");
            panier.clear();
        }
    }
}