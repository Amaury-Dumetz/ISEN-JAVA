package isen.lille.apprentissage.ap4.java;

public enum Orientation {

    HORIZONTAL("h"),
    VERTICAL("v"),
    UNKNOWN(null);

    private final String option;

    Orientation(String option) {
        this.option = option;
    }

    public static Orientation getOrientation(String motif) {
        if (motif.equals(Orientation.HORIZONTAL.option)) {
            return Orientation.HORIZONTAL;
        } else if (motif.equals(Orientation.VERTICAL.option)) {
            return Orientation.VERTICAL;
        } else {
            return Orientation.UNKNOWN;
        }
    }
}
