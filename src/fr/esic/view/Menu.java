package fr.esic.view;

public class Menu {

    public static void main(String[] args) {
        menuEmploye();
    }

    public static void menuEmploye() {
        String menu = "1. Caissière\n2. Magasinier\n3. Responsable \n\n0. Quitter";
        int choix = fr.esic.library.OutInPut.saisirEntier(menu);
        switch (choix) {
            case 1:
                // if (){
                // menuCaissiere()
                // }
                menuCaissiere();
                // LOGIQUE DU CODE POUR VERIFIER SI L'EMPLOYE EST CAISSIERE A AJOUTER
                break;
            case 2:
                // if (){
                // menuMagasinier()
                // }
                menuMagasinier();
                // LOGIQUE DU CODE POUR VERIFIER SI L'EMPLOYE EST MAGASINIER A AJOUTER
                break;
            case 3:
                // if (){
                // menuResponsable()
                // }
                menuResponsable();
                // LOGIQUE DU CODE POUR VERIFIER SI L'EMPLOYE EST RESPONSABLE A AJOUTER
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

    public static void menuCaissiere() {
        String menu = "1. Scanner un produit\n2.Modifier un produit\n3.Appliquer une promotion\n\n4. Paiement\n\n0. Retour au menu principal";
        int choix = fr.esic.library.OutInPut.saisirEntier(menu);
        switch (choix) {
            case 1:

                break;
            case 2:

                break;
            case 3:

                break;
            case 4:
                menuPaiement();
                break;
            case 0:
                menuEmploye();
                break;
            default:
                fr.esic.library.OutInPut.afficher("Choix invalide. Veuillez réessayer.");
                menuCaissiere(); // Affiche à nouveau le menu
        }

    }

    public static void menuScanner() {
        String menu = "1. Ajouter un produit\n2. Supprimer un produit\n\n0. Retour au menu caissière";
        int choix = fr.esic.library.OutInPut.saisirEntier(menu);
        switch (choix) {
            case 1:

                break;
            case 2:

                break;
            case 0:
                menuCaissiere();
                break;
            default:
                fr.esic.library.OutInPut.afficher("Choix invalide. Veuillez réessayer.");
                menuScanner(); // Affiche à nouveau le menu
        }
    }

    public static void menuPaiement() {
        String menu = "1. Passer une Carte de Fidelité\n2. Payer\n\n0. Retour au menu caissière";
        int choix = fr.esic.library.OutInPut.saisirEntier(menu);
        switch (choix) {
            case 1:

                break;
            case 2:

                break;
            case 0:
                menuCaissiere();
                break;
            default:
                fr.esic.library.OutInPut.afficher("Choix invalide. Veuillez réessayer.");
                menuPaiement(); // Affiche à nouveau le menu
        }

    }

    public static void menuMagasinier() {
        String menu = "1. Voir l'inventaire\n2. Effectuer une livraison\n3. Mise en rayon\n\n0. Retour au menu principal";
        int choix = fr.esic.library.OutInPut.saisirEntier(menu);
        switch (choix) {
            case 1:

                menuMagasinier();
                break;
            case 2:

                menuMagasinier();
                break;
            case 3:

                menuMagasinier();
                break;
            case 0:
                menuEmploye();
                break;
            default:
                fr.esic.library.OutInPut.afficher("Choix invalide. Veuillez réessayer.");
                menuMagasinier(); // Affiche à nouveau le menu
        }
    }

    public static void menuResponsable() {
        String menu = "1. Gestion des employés\n2. Validation des commandes\n3. Menu Caissière\n4. Menu Magasinier\n\n0. Retour au menu principal";
        int choix = fr.esic.library.OutInPut.saisirEntier(menu);
        switch (choix) {
            case 1:

                menuResponsable();
                break;
            case 2:

                menuResponsable();
                break;
            case 3:
                menuCaissiere();
                break;
            case 4:
                menuMagasinier();
                break;
            case 0:
                menuEmploye();
                break;
            default:
                fr.esic.library.OutInPut.afficher("Choix invalide. Veuillez réessayer.");
                menuResponsable(); // Affiche à nouveau le menu
        }
    }
    
    
    public static void menuGestionEmployes() {
    	String menu = "1. Ajouter un employé \n2. Afficher les employés \n 3. Supprimer un employé \n\n\n0. Retour au menu principal";
        int choix = fr.esic.library.OutInPut.saisirEntier(menu);
        switch (choix) {
            case 1:

                menuResponsable();
                break;
            case 2:

                menuResponsable();
                break;
            case 3:
                menuCaissiere();
                break;
            case 0:
                menuEmploye();
                break;
            default:
                fr.esic.library.OutInPut.afficher("Choix invalide. Veuillez réessayer.");
                menuResponsable(); // Affiche à nouveau le menu
        }
    }

}