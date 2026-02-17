package fr.esic.controller;

import fr.esic.library.*;
import fr.esic.model.Client;
import fr.esic.controller.*;
import fr.esic.model.Produit;
import java.util.ArrayList;
import java.util.List;

public class CaissiereController {

    // panier temporaire pour la caisse
    public static List<Produit> panier = new ArrayList<>();

    /**
     * Modifier un produit du stock (identique à la logique du responsable) le
     * paramètre lstProduit est un affichage pré‑fabriqué du stock, s'il est
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
            for (Produit prod : Data.rayon) {
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
            for (Produit prod : Data.rayon) {
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
        Produit p = new Produit(selection.getNom(), selection.getDescription(), selection.getPrix(), quantite, selection.getPlace());
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
        for (Produit prod : Data.rayon) {
            if (prod.getNom().equalsIgnoreCase(p.getNom())) {
                prod.setStock(prod.getStock() + p.getStock());
                break;
            }
        }
        OutInPut.afficher("Produit retiré du panier.");
    }

    public static List<Produit> appliquerPromotion(List<Produit> produits) {
        int choix = OutInPut.saisirEntier("1. Appliquer la promotion sur tout le panier\n2. Appliquer la promotion à un produit\n\n0. Retour");

        switch (choix) {
            case 1: {
                if (produits == null || produits.isEmpty()) {
                    OutInPut.afficher("Le panier est vide.");
                    return produits;
                }
                int promo = OutInPut.saisirEntier("Entrez le pourcentage (10 = 10%) :");
                double factor = (100.0 - promo) / 100.0;
                StringBuilder sb = new StringBuilder();
                for (Produit p : produits) {
                    double oldPrix = p.getPrix();
                    double newPrix = Math.max(0.0, oldPrix * factor);
                    p.setPrix(newPrix);
                    sb.append(p.getNom()).append(" : ").append(String.format("%.2f", oldPrix)).append(" -> ")
                            .append(String.format("%.2f", newPrix)).append("\n");
                }
                OutInPut.afficher("Promotion appliquée (" + promo + "%) sur tout le panier :\n" + sb.toString());
            }
            break;
            case 2: {
                if (produits == null || produits.isEmpty()) {
                    OutInPut.afficher("Le panier est vide.");
                    return produits;
                }
                String data = "";
                for (int i = 0; i < produits.size(); i++) {
                    data += (i + 1) + ". " + produits.get(i).infoProduit() + "\n";
                }
                int idx = OutInPut.saisirEntier(data + "\nEntrez le numéro du produit à promouvoir : ");
                if (idx < 1 || idx > produits.size()) {
                    OutInPut.afficher("Choix invalide.");
                    return produits;
                }
                Produit cible = produits.get(idx - 1);
                double oldPrix = cible.getPrix();
                int promo = OutInPut.saisirEntier("Entrez le pourcentage (10 = 10%) :");
                double factor2 = (100.0 - promo) / 100.0;
                double newPrix = Math.max(0.0, oldPrix * factor2);
                cible.setPrix(newPrix);
                OutInPut.afficher("Promotion de " + promo + "% appliquée à " + cible.getNom() + " : "
                        + String.format("%.2f", oldPrix) + " -> " + String.format("%.2f", newPrix));
            }
            break;
            case 0:
                return produits;
            default:
                OutInPut.afficher("Choix incorrect");
        }
        return produits;
    }

    // wrapper sans paramètre utilisé par le menu
    public static void appliquerPromotion() {
        appliquerPromotion(panier);
    }

    /**
     * Calcule le total et gère le paiement.
     */
    public static void payer() {
        if (panier.isEmpty()) {
            OutInPut.afficher("Aucun article dans le panier.");
            return;
        }

        StringBuilder receipt = new StringBuilder();
        double total = 0.0;
        for (Produit p : panier) {
            double line = p.getPrix() * p.getStock();
            receipt.append(p.getNom()).append(" x").append(p.getStock()).append(" : ")
                    .append(String.format("%.2f", line)).append(" €\n");
            total += line;
        }

        OutInPut.afficher("Détails du panier :\n" + receipt.toString());
        OutInPut.afficher("Total à payer : " + String.format("%.2f", total) + " €");
        double montant = OutInPut.saisirDouble("Montant reçu : ");
        if (montant < total) {
            OutInPut.afficher("Montant insuffisant. Transaction annulée.");
            return;
        }
        double rendu = montant - total;
        OutInPut.afficher("Paiement accepté. Retour : " + String.format("%.2f", rendu) + " €");
        panier.clear();
    }

    public static void depenserSoldeFideliteEtPaiement() {
        // 1. Vérification du panier
        if (panier.isEmpty()) {
            OutInPut.afficher("Aucun article trouvé dans le panier.");
            return;
        }

        // 2. Calcul du total du panier
        double total = 0;
        for (Produit p : panier) {
            total += p.getPrix() * p.getStock();
        }
        OutInPut.afficher("Total à régler : " + String.format("%.2f", total) + " €");

        // 3. Identification du client
        // On utilise la liste globale Data.clients pour trouver le client
        Client client = ClientController.trouverClientPourLeModifier(Data.clients);

        if (client == null) {
            OutInPut.afficher("Opération annulée : aucun client sélectionné.");
            return;
        }

        double soldeFidelite = client.getSoldeFidelite();
        boolean utiliserFidelite = OutInPut.saisirConfirmation("Voulez-vous utiliser votre solde fidélité (Disponible: " + soldeFidelite + " €) ?");

        double resteAPayer = total;

        if (utiliserFidelite) {
            if (soldeFidelite >= total) {
                // La fidélité couvre tout
                client.setSoldeFidelite(soldeFidelite - total);
                resteAPayer = 0;
                OutInPut.afficher("Le solde fidélité a couvert la totalité de l'achat.");
            } else {
                // La fidélité ne couvre qu'une partie
                resteAPayer = total - soldeFidelite;
                client.setSoldeFidelite(0);
                OutInPut.afficher("Fidélité déduite. Reste à payer : " + String.format("%.2f", resteAPayer) + " €");
            }
        }

        // 4. Paiement du reste (par solde bancaire ou espèces)
        if (resteAPayer > 0) {
            double montantSaisi = 0;
            // Correction de la boucle infinie : on vérifie que le montant est suffisant
            while (montantSaisi < resteAPayer) {
                montantSaisi = OutInPut.saisirDouble("Entrez le montant pour finaliser la transaction (" + String.format("%.2f", resteAPayer) + " € requis) : ");

                if (montantSaisi < resteAPayer) {
                    OutInPut.afficher("Montant insuffisant. Veuillez recommencer.");
                }
            }

            // Mise à jour du solde bancaire du client
            double nouveauSoldeBancaire = client.getSolde() - resteAPayer;
            client.setSolde(nouveauSoldeBancaire);

            if (montantSaisi > resteAPayer) {
                OutInPut.afficher("Monnaie rendue : " + String.format("%.2f", (montantSaisi - resteAPayer)) + " €");
            }
        }

        // 5. Finalisation
        OutInPut.afficher("Paiement validé avec succès !");
        OutInPut.afficher("Infos Client mises à jour :\n" + client.infoClient());

        // Vider le panier après le succès du paiement
        panier.clear();
    }

    public static double recupererSoldeFidelite() {

        int code = OutInPut.saisirEntier("Entrez votre code fidélité : ");

        if (Data.clients.isEmpty()) {
            OutInPut.afficher("Aucun client trouvé");
            return 0;
        } else {
            for (Client client : Data.clients) {

                if (code == client.getCodeFidelite()) {
                    return client.getSoldeFidelite();
                }

            }
            return 0;
        }

    }

    // 2- Consulter le solde fidélité
    public static void consulterFidelite() {

        int code = OutInPut.saisirEntier("Entrez votre code fidélité : ");
        double soldeFidelite = 0;
        int codeFidelite = 0;

        if (Data.clients.isEmpty()) {
            OutInPut.afficher("Aucun client trouvé");
        } else {
            for (Client client : Data.clients) {

                codeFidelite = client.getCodeFidelite();

                if (code == codeFidelite) {
                    soldeFidelite = client.getSoldeFidelite();
                    OutInPut.afficher("Votre solde fidélité est de : " + soldeFidelite + " €");
                }

            }
        }

    }
}
