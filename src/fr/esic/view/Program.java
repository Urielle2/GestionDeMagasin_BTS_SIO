package fr.esic.view;
import fr.esic.controller.Data;

public class Program {
    public static void main(String[] args) {
        // initialisation des données de test
        Data.loadData();

        // les produits sont maintenant directement accessibles via Data.rayon

        // l'authentification dans le menu s'appuiera sur la liste d'employés de Data
        Menu.menuEmploye();
    }
}
