package fr.esic.view;

import fr.esic.controller.ProduitController;
import fr.esic.library.OutInPut;

public class Menu {

    // Menu principal
    public static void menuPrincipal() {
        String msg = "=== Menu Principal ===\n" + "1. Gérer les produits\n" + "2. Gérer les personnes\n"
                + "3. Gestion de la Banque\n\n" + "0. Quitter\n" + "Veuillez choisir une option";

        boolean quitter = false;

        while (!quitter) {
            int choix = OutInPut.saisirEntier(msg);

            switch (choix) {
                case 1:
                    // MenuProduit.menuProduit();
                    menuProduit();
                    break;
                case 2:
                    // MenuPersonne.menuPersonne();
                    break;

                case 3:

                    break;
                case 0:
                    quitter = true;
                    OutInPut.afficher("Au revoir !");
                    break;
                default:
                	OutInPut.afficher("Option invalide. Veuillez réessayer.");
            }

        }

    }

    public static void menuProduit() {
        String msg = "=== Menu Produit ===\n" + "1. Ajouter un produit\n" + "2. Afficher les produits\n"
                + "3. Rechercher un produit\n" + "4. Mettre à jour un produit\n" + "5. Supprimer un produit\n\n"
                + "6. Ajouter du stock\n\n" + "7. Supprimer du stock\n\n" + "0. Retour au menu principal\n" + "100. Quitter le programme\n " + "Veuillez choisir une option";

        boolean retour = false;

        while (!retour) {
            int choix = OutInPut.saisirEntier(msg);

            switch (choix) {
                case 1:
                    ProduitController.createProduit();
                    break;
                case 2:
                    ProduitController.allProduit();
                    break;
                case 3:
                    // ProduitController.searchProduitByName();
                    ProduitController.searchByNameContains();
                    break;
                case 4:
                    ProduitController.updateProduct();
                    break;
                case 5:
                	ProduitController.removeProduct();
                    break;
                case 6:
                	ProduitController.addStock();                    
                	break;
                case 7:
                	ProduitController.removeStock();
                    break;
                case 0:
                    retour = true;
                    break;
                case 100:
                    // System.exit est utilisé pour quitter le programme immédiatement, exit(0) veut
                    // dire que le programme se termine sans erreur
                    System.exit(0);
                    break;
                default:
                    OutInPut.afficher("Option invalide. Veuillez réessayer.");

            }
        }
    }

    
}