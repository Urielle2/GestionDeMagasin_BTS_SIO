package fr.esic.view;

import fr.esic.controller.Data;
import fr.esic.library.OutInPut;

public class Program {
	
	//permet d'executer automatiquement le bloc si la classe est solicitée, sans l'appeler
	static {
		//Initialiser les données
		Data.init();
	}
	public static void main(String[] args) {
		OutInPut.afficher("Bienvenue dans le programme de gestion des produits et des personnes !");
		Menu.menuPrincipal();
	}
}
