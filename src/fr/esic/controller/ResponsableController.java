package fr.esic.controller;

import fr.esic.library.OutInPut;
import fr.esic.model.Employer;
import fr.esic.controller.Data;
import java.util.ArrayList;
import java.util.List;

public class ResponsableController {

    public static void ajouterEmploye() {
        String nom = OutInPut.saisirTexte("Nom : ");
        String prenom = OutInPut.saisirTexte("Prénom : ");
        String poste = OutInPut.saisirTexte("Poste : ");
        int password = OutInPut.saisirEntier("Password : ");

        Employer e = new Employer(nom, prenom, poste, password);
        Data.employes.add(e);

        OutInPut.afficher("Employé ajouté avec succès !");
    }

    public static void afficherEmployes() {
        if (Data.employes.isEmpty()) {
            OutInPut.afficher("Aucun employé enregistré.");
            return;
        }

        String affichage = "";
        for (Employer e : Data.employes) {
            affichage += e.infoPersonManager() + "\n";
        }

        OutInPut.afficher(affichage);
    }

    public static void rechercherEmploye() {
        String nom = OutInPut.saisirTexte("Nom de l'employé à rechercher : ");
        int c = 1;
        String affichage = "";

        for (Employer e : Data.employes) {
            if (e.getNom().toLowerCase().contains(nom.toLowerCase())) {
                affichage += c + ". " + e.infoPersonManager() + "\n";
            }
        }

        if (affichage.isEmpty()) {
            OutInPut.afficher("Aucun employé trouvé avec ce nom.");
        } else {
            if (c == 1) {
                OutInPut.afficher("Employé trouvé : \n" + affichage);
            } else {
                OutInPut.afficher("Employés trouvés : \n" + affichage);
            }
        }
    }

    public static List<Employer> recupererEmploye() {
        String nom = OutInPut.saisirTexte("Nom de l'employé à rechercher : ");
		List<Employer> resultats = new ArrayList<>();

        for (Employer e : Data.employes) {
            if (e.getNom().toLowerCase().contains(nom.toLowerCase())) {
				resultats.add(e);
            }
        }

        return resultats;
    }


	public static Employer getEmployerToUpdate(List<Employer> result) {
			  if (result.isEmpty()) {
			   OutInPut.afficher("Aucune correspondance avec ce nom.");
			   return null;
			   // Si un seul produit trouvé, le retourner directement
			  } else if (result.size() == 1) {
			   return result.get(0);
			   // Si plusieurs produits trouvés, afficher la liste et demander à l'utilisateur
			   // de choisir
			  } else {
			   String data = "Plusieurs employés trouvés :\n";
			   // Cette boucle permet d'afficher les produits avec un numéro
			   // i+1 pour commencer à 1 au lieu de 0 car l'index d'une liste commence à 0 par
			   // défaut
			   for (int i = 0; i < result.size(); i++) {
			    data += (i + 1) + ". " + result.get(i).infoPerson() + "\n";
			   }
			   // Demander à l'utilisateur de choisir une personne (data contient la liste des
			   // personnes)
			   int choix = OutInPut.saisirEntier(data + "\n\nEntrez le numéro de l'employer rechercher : ");
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
 

    public static void supprimerEmploye() {
		Employer employeASupprimer = getEmployerToUpdate(recupererEmploye());

        if (employeASupprimer != null) {
			boolean confirmation = OutInPut.saisirConfirmation("Etes-vous sur de vouloir retirer cet employer ?");
            if (confirmation) {
                employes.remove(employeASupprimer);
                OutInPut.afficher("Employé supprimé.");
            } else {
                OutInPut.afficher("Suppression annulée.");
            }
        } else {
            OutInPut.afficher("Aucun employé trouvé avec ce nom.");
        }
    }
	
	public static void modifierEmploye() {
		Employer employeAModifier = getEmployerToUpdate(recupererEmploye());

		if (employeAModifier != null) {
			String nom = OutInPut.saisirTexte("Nouveau nom (laisser vide pour ne pas changer) : ");
			String prenom = OutInPut.saisirTexte("Nouveau prénom (laisser vide pour ne pas changer) : ");
			String poste = OutInPut.saisirTexte("Nouveau poste (laisser vide pour ne pas changer) : ");
			String passwordStr = OutInPut.saisirTexte("Nouveau password (laisser vide pour ne pas changer) : ");

			if (!nom.isEmpty()) {
				employeAModifier.setNom(nom);
			}
			if (!prenom.isEmpty()) {
				employeAModifier.setPrenom(prenom);
			}
			if (!poste.isEmpty()) {
				employeAModifier.setPoste(poste);
			}
			if (!passwordStr.isEmpty()) {
				try {
					int password = Integer.parseInt(passwordStr);
					employeAModifier.setPassword(password);
				} catch (NumberFormatException e) {
					OutInPut.afficher("Password doit être un nombre entier. Modification du password annulée.");
				}
			}

			OutInPut.afficher("Employé modifié avec succès !");
		} else {
			OutInPut.afficher("Aucun employé trouvé avec ce nom.");
		}
	}

}
