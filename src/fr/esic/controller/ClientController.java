package fr.esic.controller;
 
import java.util.ArrayList;
import java.util.List;
 
import fr.esic.library.OutInPut;
import fr.esic.model.Client;
import fr.esic.model.Produit;
import fr.esic.controller.Data;
 

 
public class ClientController {
 
	// 1- Afficher les clients
	public static void afficherClient() {
		String data = "";
		if (Data.clients.isEmpty()) {
			OutInPut.afficher("La commande ne contient pas d'article");
		} else {
			for (Client client : Data.clients) {
				data += client.infoClient() + "\n";
			}
			OutInPut.afficher("Commande en cours : \n\n" + data);
		}
	}
 
	public static List<Client> trouverParNom(String nom) {
		List<Client> result = new ArrayList<>();
		for (Client client : Data.clients) {
 
			if (client.getNom().equalsIgnoreCase(nom)) {
				result.add(client);
			}
		}
 
		return result;
	}
 
	public static void rechercherClientParNomContenant() {
		List<Client> result = trouverParNomContenant(
				OutInPut.saisirTexte("Entrez le nom (ou partie du nom) du client à rechercher"));
		String data = "";
		if (result.isEmpty()) {
			OutInPut.afficher("Aucun client trouvé avec ce nom");
		} else {
			for (Client client : result) {
				data += client.infoClient() + "\n"
						+ "---------------------------------------------------------------------\n";
			}
			OutInPut.afficher(data);
		}
	}
 
	public static List<Client> trouverParNomContenant(String nom) {
 
		List<Client> result = new ArrayList<>();
		for (Client client : Data.clients) {
			if (client.getNom().toLowerCase().contains(nom.toLowerCase())) {
				result.add(client);
			}
		}
 
		return result;
	}
 
	// permet de choisir un article à mettre à jour parmi une liste d'articles
	public static Client trouverClientPourLeModifier(List<Client> result) {
		if (result.isEmpty()) {
			OutInPut.afficher("Aucun client trouvé avec ce nom");
			return null;
			// Si un seul article trouvé, le retourner directement
		} else if (result.size() == 1) {
			return result.get(0);
			// Si plusieurs articles trouvés, afficher la liste et demander à l'utilisateur
			// de choisir
		} else {
			String data = "Plusieurs clients trouvés :\n";
			// Cette boucle permet d'afficher les produits avec un numéro
			// i+1 pour commencer à 1 au lieu de 0 car l'index d'une liste commence à 0 par
			// défaut
			for (int i = 0; i < result.size(); i++) {
				data += (i + 1) + ". " + result.get(i).infoClient() + "\n";
			}
			// Demander à l'utilisateur de choisir un article (data contient la liste des
			// produits
			int choix = OutInPut.saisirEntier(data + "\n\nEntrez le numéro du client à mettre à jour : ");
			// Vérifier que le choix est valide
			if (choix < 1 || choix > result.size()) {
				OutInPut.afficher("Choix invalide");
				return null;
			} else {
				// Retourner l'article choisi (choix -1 pour correspondre à l'index de la
				// liste)
				return result.get(choix - 1);
			}
		}
	}
 
	// 2- Consulter le solde fidélité
	public static void consulterFidelite() {
 
		int code = OutInPut.saisirEntier("Entrez votre code fidélité : ");
		double soldeFidelite = 0;
		int codeFidelite = 0;
 
		if (Data.clients.isEmpty()) {
			OutInPut.afficher("Aucun client");
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
	public static double recupererSolde() {
		String code = OutInPut.saisirTexte("Entrez votre numéro client : ");
 
		if (Data.clients.isEmpty()) {
			OutInPut.afficher("Aucun client trouvé");
			return 0;
		} else {
			for (Client client : Data.clients) {
				if (code.equalsIgnoreCase(client.getNumeroClient()) ) {
					return client.getSolde();
				}
 
			}
			return 0;
		}

 
	}	
}