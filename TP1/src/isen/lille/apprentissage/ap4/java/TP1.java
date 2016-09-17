package isen.lille.apprentissage.ap4.java;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class TP1 {

    private static String symbole = "*";
    private static final String optionSymbole = "-s";
    private static Orientation orientation = Orientation.HORIZONTAL;
    private static final String optionOrientation = "-o";

    private static void lectureClavier(LinkedList<Integer> argsInt) {
        Scanner scan = new Scanner(System.in);
        String ligneLue;

        System.out.println("Saisie manuelle des valeurs :");
        do {
            ligneLue = scan.nextLine();
            if (!ligneLue.isEmpty()) {
                try {
                    int entierLu = Integer.parseInt(ligneLue);
                    argsInt.addFirst(entierLu);
                } catch (NumberFormatException ex) {
                    // On passe la valeur non reconnue comme entière
                }
            }
        } while (!ligneLue.isEmpty());
    }

    private static LinkedList<Integer> gestionOptions(String[] args) {
        LinkedList<Integer> argsInt = new LinkedList();
        boolean choixSymbole = false;
        boolean choixOrientation = false;

        for (String arg : args) {
            if (choixSymbole) {
                symbole = arg;
                choixSymbole = false;
            } else if (choixOrientation) {
                orientation = Orientation.getOrientation(arg);
                choixOrientation = false;
            } else {
                switch (arg) {
                    case optionSymbole:
                        choixSymbole = true;
                        break;
                    case optionOrientation:
                        choixOrientation = true;
                        break;
                    default:
                        int valeur;
                        try {
                            valeur = Integer.parseInt(arg);
                            argsInt.addFirst(valeur);
                        } catch (NumberFormatException ex) {
                            // On passe la valeur non reconnue comme entière
                        }
                        break;
                }
            }
        }

        return argsInt;
    }

    private static void affichageLigneHorizontale(int valeur, int compteur) {
        System.out.print(valeur + " ");
        for (int i = 0; i < compteur; i++) {
            System.out.print(symbole);
        }
        System.out.println();
    }

    private static void affichageHorizontal(LinkedList<Integer> argsInt) {
        int valeur = -1, compteur = -1;

        Iterator<Integer> it = argsInt.iterator();
        while (it.hasNext()) {
            int itValeur = it.next();
            if (compteur == -1) {
                valeur = itValeur;
                compteur = 1;
            } else {
                if (valeur != itValeur) {
                    affichageLigneHorizontale(valeur, compteur);
                    for (int i = valeur + 1; i < itValeur; i++) {
                        affichageLigneHorizontale(i, 0);
                    }
                    valeur = itValeur;
                    compteur = 1;
                } else {
                    compteur++;
                }
            }
        }
        affichageLigneHorizontale(valeur, compteur);
    }

    private static void affichageVertical(LinkedList<Integer> argsInt) {
        int min = argsInt.getFirst();
        int[] occ = new int[argsInt.getLast() - min + 1];
        int maxOcc = -1;

        // Stockage du nombre d'occurences par valeur
        Iterator<Integer> it = argsInt.iterator();
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
                    System.out.print(symbole + " ");
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

    public static void main(String[] args) {
        LinkedList<Integer> argsInt = gestionOptions(args);

        if (argsInt.isEmpty()) {
            lectureClavier(argsInt);
        }

        argsInt.sort(null);

        if (orientation == Orientation.HORIZONTAL) {
            affichageHorizontal(argsInt);
        } else if (orientation == Orientation.VERTICAL) {
            affichageVertical(argsInt);
        }
    }

}
