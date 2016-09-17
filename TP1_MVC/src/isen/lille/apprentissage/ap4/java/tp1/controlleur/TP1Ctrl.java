package isen.lille.apprentissage.ap4.java.tp1.controlleur;

import isen.lille.apprentissage.ap4.java.tp1.modele.Constantes;
import isen.lille.apprentissage.ap4.java.tp1.modele.Histogramme;
import isen.lille.apprentissage.ap4.java.tp1.modele.Orientation;
import java.util.Scanner;

public class TP1Ctrl {

    public static void lectureClavier(Histogramme h) {
        Scanner scan = new Scanner(System.in);
        String ligneLue;

        System.out.println("Saisie manuelle des valeurs :");
        do {
            ligneLue = scan.nextLine();
            if (!ligneLue.isEmpty()) {
                try {
                    int entierLu = Integer.parseInt(ligneLue);
                    h.addArgsInt(entierLu);
                } catch (NumberFormatException ex) {
                    // On passe la valeur non reconnue comme entière
                }
            }
        } while (!ligneLue.isEmpty());
    }

    public static void gestionOptions(String[] args, Histogramme h) {
        boolean choixSymbole = false;
        boolean choixOrientation = false;

        for (String arg : args) {
            if (choixSymbole) {
                h.setSymbole(arg);
                choixSymbole = false;
            } else if (choixOrientation) {
                h.setOrientation(Orientation.getOrientation(arg));
                choixOrientation = false;
            } else {
                switch (arg) {
                    case Constantes.OPTION_SYMBOLE:
                        choixSymbole = true;
                        break;
                    case Constantes.OPTION_ORIENTATION:
                        choixOrientation = true;
                        break;
                    default:
                        int valeur;
                        try {
                            valeur = Integer.parseInt(arg);
                            h.addArgsInt(valeur);
                        } catch (NumberFormatException ex) {
                            // On passe la valeur non reconnue comme entière
                        }
                        break;
                }
            }
        }
    }
}
