package fr.esic.library;

import javax.swing.JOptionPane;

public class OutInPut {
    /**
     * Saisir une chaîne de caractères (String)
     */
    public static String saisirTexte(String message) {
        return JOptionPane.showInputDialog(null, message);
    }

    /**
     * Saisir un nombre entier (int)
     * Gère l'erreur si l'utilisateur saisit autre chose qu'un chiffre
     */
    public static int saisirEntier(String message) {
        try {
            String s = JOptionPane.showInputDialog(null, message);
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Erreur : Vous devez saisir un nombre entier.");
            return 0; // Valeur par défaut en cas d'erreur
        }
    }

    /**
     * Saisir un nombre à virgule (double)
     */
    public static double saisirDouble(String message) {
        String s = JOptionPane.showInputDialog(null, message);
        return Double.parseDouble(s);

    }

    /**
     * Saisir un booléen via une confirmation (Oui/Non)
     */
    public static boolean saisirConfirmation(String message) {
        int reponse = JOptionPane.showConfirmDialog(null, message, "Confirmation", JOptionPane.YES_NO_OPTION);
        return (reponse == JOptionPane.YES_OPTION);
    }

    /**
     * Afficher un simple message (procédure)
     */
    public static void afficher(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
}
