package fr.esic.view;

import fr.esic.controller.*;
import fr.esic.library.OutInPut;

public class Menu {

    public static void menuEmploye() {
        while (true) {
            String menu = "1. Caissière\n2. Magasinier\n3. Responsable \n\n0. Quitter";
            int choix = fr.esic.library.OutInPut.saisirEntier(menu);
            switch (choix) {
                case 1:
                    int codeEmploye = fr.esic.library.OutInPut.saisirEntier("Entrez votre code employé : ");
                    if (codeEmploye == 1) {
                        menuCaissiere();
                    } else {
                        fr.esic.library.OutInPut.afficher("Code employé invalide. Accès refusé.");
                        menuEmploye();
                    }
                    break;
                case 2:
                    int codeEmploye2 = fr.esic.library.OutInPut.saisirEntier("Entrez votre code employé : ");
                    if (codeEmploye2 == 2) {
                        menuMagasinier();
                    } else {
                        fr.esic.library.OutInPut.afficher("Code employé invalide. Accès refusé.");
                        menuEmploye();
                    }
                    break;
                case 3:
                    int codeEmploye3 = fr.esic.library.OutInPut.saisirEntier("Entrez votre code employé : ");
                    if (codeEmploye3 == 3) {
                        menuResponsable();
                    } else {
                        fr.esic.library.OutInPut.afficher("Code employé invalide. Accès refusé.");
                        menuEmploye();
                    }
                    break;
                case 0:
                    fr.esic.library.OutInPut.afficher("Au revoir !");
                    System.exit(0);
                    break;
                default:
                    fr.esic.library.OutInPut.afficher("Choix invalide. Veuillez réessayer.");
                    menuEmploye(); // Affiche à nouveau le menu
            }
        }
    }

    public static void menuCaissiere() {
        while (true) {
            String menu = "1. Scanner un produit\n2.Modifier un produit\n3.Appliquer une promotion\n4. Paiement\n\n0. Retour au menu principal";
            int choix = fr.esic.library.OutInPut.saisirEntier(menu);
            switch (choix) {
                case 1:
                    fr.esic.controller.CaissiereController.scanner();
                    break;
                case 2:
                    fr.esic.controller.CaissiereController.modifier();
                    break;
                case 3:
                    fr.esic.controller.CaissiereController.appliquerPromotion();
                    break;
                case 4:
                    menuPaiement();
                    break;
                case 0:
                    return; // revenir au menuEmploye
                default:
                    fr.esic.library.OutInPut.afficher("Choix invalide. Veuillez réessayer.");
                    break;
            }
        }
    }

    public static void menuPaiement() {
        String msg = "=== Menu Paiement ===\n\n" + "1. Consulter le solde fidélité\n"
                + "2. Règlement de la commande\n\n" + "0. Retour au menu principal\n" + "100. Quitter le programme\n\n"
                + "Veuillez choisir une option : ";

        boolean retour = false;

        while (!retour) {
            int choix = OutInPut.saisirEntier(msg);

            switch (choix) {
                case 1:
                    fr.esic.controller.CaissiereController.consulterFidelite();
                    break;
                case 2:
                    fr.esic.controller.CaissiereController.depenserSoldeFideliteEtPaiement();
                    break;
                case 0:
                    retour = true;
                    break;
                case 100:
                    System.exit(0);
                    break;
                default:
                    OutInPut.afficher("Option invalide. Veuillez réessayer.");
            }

        }

    }

    public static void menuMagasinier() {
        while (true) {
            String menu = "1. Voir l'inventaire\n2. Effectuer une livraison\n3. Mise en rayon\n4. Retirer un produit en rayon\n\n0. Retour au menu principal";
            int choix = fr.esic.library.OutInPut.saisirEntier(menu);
            switch (choix) {
                case 1:
                    fr.esic.controller.MagasinierController.allProduit();
                    break;
                case 2:
                    fr.esic.controller.MagasinierController.livraison();
                    break;
                case 3:
                    fr.esic.controller.MagasinierController.miseEnRayon();
                    break;
                case 4:
                    fr.esic.controller.MagasinierController.removeProductFromRayon();
                    break;
                case 0:
                    return; // retour menuEmploye
                default:
                    fr.esic.library.OutInPut.afficher("Choix invalide. Veuillez réessayer.");
                    break;
            }
        }
    }

    public static void menuResponsable() {
        while (true) {
            String menu = "1. Gestion des employés\n2. Menu Caissière\n3. Menu Magasinier\n\n0. Retour au menu principal";
            int choix = fr.esic.library.OutInPut.saisirEntier(menu);
            switch (choix) {
                case 1:
                    menuGestionEmploye();
                    break;
                case 2:
                    menuCaissiere();
                    break;
                case 3:
                    menuMagasinier();
                    break;
                case 0:
                    return; // retour menuEmploye
                default:
                    fr.esic.library.OutInPut.afficher("Choix invalide. Veuillez réessayer.");
                    break;
            }
        }
    }

    public static void menuGestionEmploye() {
        while (true) {
            String menu = "1. Ajouter un employé\n2. Modifier un employé\n3. Afficher les employés\n4. Rechercher un employé\n5. Supprimer un employé\n\n0. Retour au menu responsable";
            int choix = fr.esic.library.OutInPut.saisirEntier(menu);
            switch (choix) {
                case 1:
                    fr.esic.controller.ResponsableController.ajouterEmploye();
                    break;
                case 2:
                    fr.esic.controller.ResponsableController.modifierEmploye();
                    break;
                case 3:
                    fr.esic.controller.ResponsableController.afficherEmployes();
                    break;
                case 4:
                    fr.esic.controller.ResponsableController.rechercherEmploye();
                    break;
                case 5:
                    fr.esic.controller.ResponsableController.supprimerEmploye();
                    break;
                case 0:
                    return; // retour menuResponsable
                default:
                    fr.esic.library.OutInPut.afficher("Choix invalide. Veuillez réessayer.");
                    break;
            }

        }
    }
}
