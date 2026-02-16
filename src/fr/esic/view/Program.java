package fr.esic.view;
import fr.esic.controller.Data;

public class Program {
    public static void main(String[] args) {
        // initialisation des données de test
        Data.loadData();

        // distribuer les produits chargés vers le contrôleur principal
        fr.esic.controller.ProduitController.produits.clear();
        fr.esic.controller.ProduitController.produits.addAll(Data.produits);

        // l'authentification dans le menu s'appuiera sur la liste d'employés de Data
        Menu.menuEmploye();
    }
}
