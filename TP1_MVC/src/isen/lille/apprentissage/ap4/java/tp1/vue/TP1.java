package isen.lille.apprentissage.ap4.java.tp1.vue;

import isen.lille.apprentissage.ap4.java.tp1.controlleur.TP1Ctrl;
import isen.lille.apprentissage.ap4.java.tp1.modele.Histogramme;
import isen.lille.apprentissage.ap4.java.tp1.modele.Orientation;
import java.util.Iterator;

public class TP1 {

    private static void affichageLigneHorizontale(Histogramme h, int valeur, int compteur) {
        System.out.print(valeur + " ");
        for (int i = 0; i < compteur; i++) {
            System.out.print(h.getSymbole());
        }
        System.out.println();
    }

    private static void affichageHorizontal(Histogramme h) {
        int valeur = -1, compteur = -1;

        Iterator<Integer> it = h.getArgsInt().iterator();
        while (it.hasNext()) {
            int itValeur = it.next();
            if (compteur == -1) {
                valeur = itValeur;
                compteur = 1;
            } else {
                if (valeur != itValeur) {
                    affichageLigneHorizontale(h, valeur, compteur);
                    for (int i = valeur + 1; i < itValeur; i++) {
                        affichageLigneHorizontale(h, i, 0);
                    }
                    valeur = itValeur;
                    compteur = 1;
                } else {
                    compteur++;
                }
            }
        }
        affichageLigneHorizontale(h, valeur, compteur);
    }

    private static void affichageVertical(Histogramme h) {
        int min = h.getArgsInt().getFirst();
        int[] occ = new int[h.getArgsInt().getLast() - min + 1];
        int maxOcc = -1;

        // Stockage du nombre d'occurences par valeur
        Iterator<Integer> it = h.getArgsInt().iterator();
        while (it.hasNext()) {
            int itValeur = it.next();
            occ[itValeur - min]++;
        }
        // -------
        // Recherche du nombre maximum d'occurences
        for (int i = 0; i < occ.length; i++) {
            if (-1 == maxOcc) {
                maxOcc = occ[i];
            } else if (occ[i] > maxOcc) {
                maxOcc = occ[i];
            }
        }
        // -------

        for (int y = maxOcc; y > 0; y--) {
            for (int x = 0; x < occ.length; x++) {
                if (occ[x] >= y) {
                    System.out.print(h.getSymbole() + " ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
        for (int x = min; x < min + occ.length; x++) {
            System.out.print(x + " ");
        }
        System.out.println();
    }

    public static void affichage(Histogramme h) {
        if (h.getOrientation() == Orientation.HORIZONTAL) {
            affichageHorizontal(h);
        } else if (h.getOrientation() == Orientation.VERTICAL) {
            affichageVertical(h);
        }
    }

    public static void main(String[] args) {
        Histogramme h = new Histogramme(null, null, null);

        TP1Ctrl.gestionOptions(args, h);
        if (h.getArgsInt().isEmpty()) {
            TP1Ctrl.lectureClavier(h);
        }

        affichage(h);
    }
}
