package fr.esic.library;

import javax.swing.JOptionPane;

public class OutInPut {
	public static void afficher(String msg) {
		JOptionPane.showMessageDialog(null,  msg);
	}
	
	public static String saisirTexte(String message) {
		return JOptionPane.showInputDialog(message);
	}
	
	public static String saisir(String message) {
		return JOptionPane.showInputDialog(message);
	}
	
	public static int saisirEntier(String message) {
		String input = JOptionPane.showInputDialog(message);
		return Integer.parseInt(input);
	}
	
	public static double saisirDouble(String message) {
		String input = JOptionPane.showInputDialog(message);
		return Double.parseDouble(input);
	}
}
