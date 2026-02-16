package fr.esic.controller;

import java.util.ArrayList;
import java.util.List;
import fr.esic.library.OutInPut;
import fr.esic.model.Employe;

public class EmployeController {

	public static List<Employe> employes = new ArrayList<>();

	public static void ajouterEmploye() {
		String nom = OutInPut.saisirTexte("Nom : ");
		String prenom = OutInPut.saisirTexte("Prénom : ");
		String poste = OutInPut.saisirTexte("Poste : ");
		double salaire = OutInPut.saisirDouble("Salaire : ");

		Employe e = new Employe(nom, prenom, poste, salaire);
		employes.add(e);

		OutInPut.afficher("Employé ajouté avec succès !");
	}

	public static void afficherEmployes() {
		if (employes.isEmpty()) {
			OutInPut.afficher("Aucun employé enregistré.");
			return;
		}

		String data = "";
		for (Employe e : employes) {
			data += e.infoEmploye() + "\n";
		}

		OutInPut.afficher(data);
	}

	public static void rechercherEmploye() {
		String nom = OutInPut.saisirTexte("Nom de l'employé à rechercher : ");

		for (Employe e : employes) {
			if (e.getNom().equalsIgnoreCase(nom)) {
				OutInPut.afficher("Employé trouvé :\n" + e.infoEmploye());
				return;
			}
		}

		OutInPut.afficher("Aucun employé trouvé avec ce nom.");
	}

	public static void supprimerEmploye() {
		String nom = OutInPut.saisirTexte("Nom de l'employé à supprimer : ");

		Employe employeASupprimer = null;

		for (Employe e : employes) {
			if (e.getNom().equalsIgnoreCase(nom)) {
				employeASupprimer = e;
				break;
			}
		}

		if (employeASupprimer != null) {
			employes.remove(employeASupprimer);
			OutInPut.afficher("Employé supprimé.");
		} else {
			OutInPut.afficher("Aucun employé trouvé avec ce nom.");
		}
	}

}
