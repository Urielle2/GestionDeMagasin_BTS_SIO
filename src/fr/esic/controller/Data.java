package fr.esic.controller;

import fr.esic.model.Produit;

public class Data {
	public static void init() {
		Produit P1 = new Produit("Ordinateur Portable", "PC portable haute performance", 1200.00, 10);
		Produit P2 = new Produit("Smartphone", "Téléphone intélligent ", 800.00, 10);
		Produit P3 = new Produit("Casque audio", "Casque sans fil avec réduction de bruit", 150.00, 10);
		Produit P4 = new Produit("Tablette", "Tablette légère pour la navigation et le multimédia", 400.00, 10);
		Produit P5 = new Produit("Tablette IOS", "Tablette portable haute performance", 600.00, 10);
		Produit P6 = new Produit("Tablette Android", "Tablette légère pour la navigation et le multimédia", 300.00, 10);
		Produit P7 = new Produit("Ordinateur de Bureau", "PC de bureau puissant pour le gaming", 1500.00, 10);
		
		
		
		
	ProduitController.produits.add(P1);
		ProduitController.produits.add(P2);
		ProduitController.produits.add(P3);
		ProduitController.produits.add(P4);
		ProduitController.produits.add(P5);
		ProduitController.produits.add(P6);
		ProduitController.produits.add(P7);
		
	}
}
