package Ressources;


import java.util.Arrays;

public class Pair {
    private boolean[] first;
    private boolean[] second;

    public Pair(boolean[] first, boolean[] second) {
        this.first = first;
        this.second = second;
    }

    public boolean[] getFirst(){
        return first;
    }

    public boolean[] getSecond(){
        return second;
    }

    @Override
    public String toString() {
        return "(" + Arrays.toString(first) + ", " + Arrays.toString(second) + ")";
    }
}
