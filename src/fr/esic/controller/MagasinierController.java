package fr.esic.controller;

import fr.esic.library.OutInPut;
import fr.esic.model.Produit;
import java.util.ArrayList;
import java.util.List;
import fr.esic.controller.*;
// import fr.esic.model.Employer;

public class MagasinierController {

    public static List<List<Produit>> livraisons = new ArrayList<>();

    public static void allProduit() {
        String data = "";
        if (Data.rayon.isEmpty() && Data.stock.isEmpty()) {
            OutInPut.afficher("Aucun produit disponible.");
        } else {
            for (Produit produit : Data.rayon) {
                data += produit.infoProduit() + "\n";
            }
            for (Produit produit : Data.stock) {
                data += produit.infoProduit() + "\n";
            }
            OutInPut.afficher(data);

        }
    }

    public static void livraison() {
        List<Produit> livraison = new ArrayList<>();
        while (true) {
            String menu = "1. Ajouter un produit à la livraison\n2. Afficher le résumer de la commande\n3. Valider la livraison\n\n0. Annuler la livraison";
            int choix = fr.esic.library.OutInPut.saisirEntier(menu);
            switch (choix) {
                case 1:
                    int exstitant = fr.esic.library.OutInPut.saisirEntier("1. Produit existant\n2. Nouveau produit\n\n0. Annuler");
                    switch (exstitant) {
                        case 1:
                            // Demander le nom une seule fois, récupérer la liste et laisser
                            // getProductToUpdate gérer la sélection si plusieurs résultats
                            List<Produit> results = findByNameContains(OutInPut.saisirTexte("Entrez le nom du produit à ajouter à la livraison : "));
                            if (results == null || results.isEmpty()) {
                                OutInPut.afficher("Aucun produit trouvé pour ce nom.");
                                break;
                            }
                            Produit p = getProductToUpdate(results);
                            if (p != null) {
                                int quantiteCmd = OutInPut.saisirEntier("Quantité à commander : ");
                                if (quantiteCmd <= 0) {
                                    OutInPut.afficher("Quantité invalide. Ajout annulé.");
                                } else {
                                    // créer une copie pour la livraison avec la quantité demandée
                                    Produit produitLiv = new Produit(p.getNom(), p.getDescription(), p.getPrix(), quantiteCmd, "Stock");
                                    livraison.add(produitLiv);
                                    OutInPut.afficher("Produit ajouté à la livraison : " + produitLiv.infoProduit());
                                }
                            } else {
                                OutInPut.afficher("Aucun produit sélectionné pour la livraison.");
                            }
                            break;
                        case 2:
                            String nom = OutInPut.saisirTexte("Nom du nouveau produit : ");
                            String description = OutInPut.saisirTexte("Description du nouveau produit : ");
                            double prix = OutInPut.saisirDouble("Prix du nouveau produit : ");
                            int stock = OutInPut.saisirEntier("Quantité en stock du nouveau produit : ");
                            Produit newProduit = new Produit(nom, description, prix, stock, "Rayon");
                            Data.rayon.add(newProduit);
                            livraison.add(newProduit);
                            OutInPut.afficher("Nouveau produit créé et ajouté à la livraison : " + newProduit.infoProduit());
                            break;
                        case 0:
                            break; // annule l'ajout d'un produit et revient au menu de livraison
                        default:
                            OutInPut.afficher("Choix invalide. Veuillez réessayer.");
                    }
                    break;
                case 2:
                    if (!livraison.isEmpty()){
                        String data="";
                        for (Produit p: livraison){
                            data+= p.infoProduit() + "\n";
                        }
                        OutInPut.afficher(data);
                    }else{
                        OutInPut.afficher("Aucun produit ajouté à la livraison");
                    }
                    break;
                case 3:
                    if (livraison.isEmpty()) {
                        OutInPut.afficher("Aucun produit ajouté à la livraison.");
                    } else {
                        // Mettre à jour les stocks existants ou ajouter au stock
                        for (Produit pl : livraison) {
                            boolean updated = false;
                            for (Produit s : Data.stock) {
                                if (s.getNom().equalsIgnoreCase(pl.getNom())) {
                                    s.setStock(s.getStock() + pl.getStock());
                                    updated = true;
                                    break;
                                }
                            }
                            if (!updated) {
                                for (Produit r : Data.rayon) {
                                    if (r.getNom().equalsIgnoreCase(pl.getNom())) {
                                        r.setStock(r.getStock() + pl.getStock());
                                        updated = true;
                                        break;
                                    }
                                }
                            }
                            if (!updated) {
                                // nouveau produit : l'ajouter au stock
                                pl.setPlace("Stock");
                                Data.stock.add(pl);
                            }
                        }
                        livraisons.add(livraison);
                        OutInPut.afficher("Livraison validée avec " + livraison.size() + " produits. Stocks mis à jour.");
                    }
                    return; // revient au menuMagasinier
            }
        }
    }

    public static List<Produit> findByName(String nom) {
        List<Produit> result = new ArrayList<>(); // nouvelle liste 
        for (Produit produit : Data.rayon) {
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

    public static List<Produit> findByNameContains(String nom) {
        List<Produit> result = new ArrayList<>();
        int choix = OutInPut.saisirEntier("1. Produit en rayon\n2. Produit en stock\n3. Les deux");
        switch (choix) {
            case 1:
                for (Produit produit : Data.rayon) {
                    if (produit.getNom().toLowerCase().contains(nom.toLowerCase())) {
                        result.add(produit);
                    }
                }
                break;
            case 2:
                for (Produit produit : Data.stock) {
                    if (produit.getNom().toLowerCase().contains(nom.toLowerCase())) {
                        result.add(produit);
                    }
                }
                break;
            case 3:
                for (Produit produit : Data.stock) {
                    if (produit.getNom().toLowerCase().contains(nom.toLowerCase())) {
                        result.add(produit);
                    }
                }
                for (Produit produit : Data.rayon) {
                    if (produit.getNom().toLowerCase().contains(nom.toLowerCase())) {
                        result.add(produit);
                    }
                }
                break;

        }
        if (!result.isEmpty()) {
            return result;
        } else {
            return null;
        }
    }

    public static List<Produit> findByNameContainsStock(String nom) {
        List<Produit> result = new ArrayList<>();
        for (Produit produit : Data.stock) {
            if (produit.getNom().toLowerCase().contains(nom.toLowerCase())) {
                result.add(produit);
            }
        }
        if (!result.isEmpty()) {
            return result;
        } else {
            return null;
        }
    }

    public static List<Produit> findByNameContainsRayon(String nom) {
        List<Produit> result = new ArrayList<>();
        for (Produit produit : Data.rayon) {
            if (produit.getNom().toLowerCase().contains(nom.toLowerCase())) {
                result.add(produit);
            }
        }
        if (!result.isEmpty()) {
            return result;
        } else {
            return null;
        }
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

    // Création d'un menu qui permet d'augmenter le nombre de produit qu'il y a en stock
    public static void addStock() {
        Produit produit = getProductToUpdate(
                findByNameContainsStock(OutInPut.saisirTexte("Entrez le nom du produit pour ajouter du stock : ")));

        if (produit != null) {
            int quantiteAAjouter = OutInPut.saisirEntier("Entrez la quantité à ajouter au stock : ");
            int nouvelleQuantite = produit.getStock() + quantiteAAjouter;
            produit.setStock(nouvelleQuantite);
            OutInPut.afficher("Stock mis à jour ! Nouvelle quantité en stock : " + nouvelleQuantite);
        }
    }

//Création d'un menu qui permet diminuer le nombre de produit qu'il y a en stock
    public static void removeStock() {
        Produit produit = getProductToUpdate(
                findByNameContains(OutInPut.saisirTexte("Entrez le nom du produit pour retirer du stock : ")));

        if (produit != null) {
            int quantiteARetirer = OutInPut.saisirEntier("Entrez la quantité à retirer du stock : ");
            int nouvelleQuantite = produit.getStock() - quantiteARetirer;
            if (nouvelleQuantite < 0) {
                OutInPut.afficher("Erreur : la quantité à retirer dépasse le stock disponible.");
            } else {
                produit.setStock(nouvelleQuantite);
                OutInPut.afficher("Stock mis à jour ! Nouvelle quantité en stock : " + nouvelleQuantite);
            }
        }
    }

    public static void removeProductFromRayon() {

        Produit produit = getProductToUpdate(
                findByNameContainsRayon(OutInPut.saisirTexte("Entrez le nom du produit à retirer des rayons : ")));

        if (produit != null) {

            boolean valider = OutInPut.saisirConfirmation("Etes-vous sur de vouloir supprimer le produit ?");
            if (valider) {
                produit.echange();
                Data.stock.add(produit);
                Data.rayon.remove(produit);
                OutInPut.afficher("Produit supprimé des rayons et transferer au stock avec succès");
            } else {
                OutInPut.afficher("Produit conservé dans le rayon");
            }
        }

    }

    public static void miseEnRayon() {
        Produit produit = getProductToUpdate(
                findByNameContainsStock(OutInPut.saisirTexte("Entrez le nom du produit à mettre en rayon : ")));

        if (produit != null) {

            boolean valider = OutInPut.saisirConfirmation("Etes-vous sur de vouloir mettre " + produit.getNom() + " en rayon ?");
            if (valider) {
                produit.echange();
                Data.rayon.add(produit);
                Data.stock.remove(produit);
                OutInPut.afficher("Produit mis en rayon !");
            } else {
                OutInPut.afficher("Produit toujours dans le stock");
            }

        }

    }

}
