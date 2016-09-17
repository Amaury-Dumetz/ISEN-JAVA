package isen.lille.apprentissage.ap4.java.tp1.modele;

import java.util.LinkedList;

public class Histogramme {

    private String symbole;
    private Orientation orientation;
    private final LinkedList<Integer> argsInt;

    public String getSymbole() {
        return symbole;
    }

    public void setSymbole(String symbole) {
        if (symbole == null) {
            this.symbole = "*";
        } else {
            this.symbole = symbole;
        }
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        if (orientation == null) {
            this.orientation = Orientation.HORIZONTAL;
        } else {
            this.orientation = orientation;
        }
    }

    public LinkedList<Integer> getArgsInt() {
        return argsInt;
    }

    public void addArgsInt(int arg) {
        argsInt.addFirst(arg);
        argsInt.sort(null);
    }

    public Histogramme(String symbole, Orientation orientation, LinkedList<Integer> argsInt) {
        if (symbole == null) {
            this.symbole = "*";
        } else {
            this.symbole = symbole;
        }

        if (orientation == null) {
            this.orientation = Orientation.HORIZONTAL;
        } else {
            this.orientation = orientation;
        }

        if (argsInt == null) {
            this.argsInt = new LinkedList();
        } else {
            argsInt.sort(null);
            this.argsInt = argsInt;
        }
    }
}
