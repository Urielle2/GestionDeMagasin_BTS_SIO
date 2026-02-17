package fr.esic.view;

import fr.esic.controller.*;

public class Menu {

    public static void main(String[] args) {
        menuEmploye();
    }

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
            String menu = "1. Scanner un produit\n2.Modifier un produit\n3.Appliquer une promotion\n\n4. Paiement\n\n0. Retour au menu principal";
            int choix = fr.esic.library.OutInPut.saisirEntier(menu);
            switch (choix) {
                case 1:
                    fr.esic.controller.CassiereController.scanner();
                    break;
                case 2:
                    fr.esic.controller.CassiereController.modifier();
                    break;
                case 3:
                    fr.esic.controller.CassiereController.appliquerPromotion();
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

        while (true) {
            String menu = "1. Passer une Carte de Fidelité\n2. Payer\n\n0. Retour au menu caissière";
            int choix = fr.esic.library.OutInPut.saisirEntier(menu);
            switch (choix) {
                case 1:
                    fr.esic.controller.CassiereController.passerCarteFidelite();
                    break;
                case 2:
                    fr.esic.controller.CassiereController.payer();
                    return; // après paiement, revenir au menu caissière
                case 0:
                    return; // revient au menuCaissiere
                default:
                    fr.esic.library.OutInPut.afficher("Choix invalide. Veuillez réessayer.");
                    break;
            }
        }
    }

    public static void menuMagasinier() {
        while (true) {
            String menu = "1. Voir l'inventaire\n2. Effectuer une livraison\n3. Mise en rayon\n\n0. Retour au menu principal";
            int choix = fr.esic.library.OutInPut.saisirEntier(menu);
            switch (choix) {
                case 1:
                    // TODO implémenter
                    break;
                case 2:
                    // TODO implémenter
                    break;
                case 3:
                    // TODO implémenter
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
            String menu = "1. Gestion des employés\n2. Validation des commandes\n3. Menu Caissière\n4. Menu Magasinier\n\n0. Retour au menu principal";
            int choix = fr.esic.library.OutInPut.saisirEntier(menu);
            switch (choix) {
                case 1:
                    menuGestionEmploye();
                    break;
                case 2:
                    // TODO implémenter
                    break;
                case 3:
                    menuCaissiere();
                    break;
                case 4:
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