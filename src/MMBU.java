import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MMBU {

    public static boolean[][] analyze(boolean[][] testArray) {
        if (testArray == null || testArray.length == 0 || testArray[0].length < 2) {
            throw new IllegalArgumentException();
        }

        System.out.println("Input Array:");
        for (boolean[] row : testArray) {
            if (row.length != testArray[0].length) {
                throw new IllegalArgumentException();
            }
            System.out.println(Arrays.toString(row));
        }

        List<boolean[]> mmbu = new ArrayList<>();

        // Array wird zeilenweise durchgagnen
        for (int i = 0; i < testArray.length; i++) {
            boolean[] currentRow = testArray[i];
            boolean hasNeighborWithDifferentResult = false;

            // Zweites mal durch das Array, die entnommene Zeile wird mit allen anderen verglichen
            for (int j = 0; j < testArray.length; j++) {
                if (i != j) {
                    boolean[] neighborRow = testArray[j];
                    // Anforderungen der MMBÜ wird überprüft
                    if (checkNeighbor(currentRow, neighborRow)) {
                        hasNeighborWithDifferentResult = true;
                        break;
                    }
                }
            }
            // Sobald die Anforderungen dem der MMBÜ entsprechen, wirds in die Liste hinzugefügt
            if (hasNeighborWithDifferentResult) {
                mmbu.add(currentRow);
            }
        }

        // Überprüfen, ob die Liste mmbu leer ist, bevor das Array result erstellt wird
        // sonst Gefahr out of bounds zu gehen
        if (mmbu.isEmpty()) {
            return new boolean[0][0];
        }

        boolean[][] result = new boolean[mmbu.size()][mmbu.get(1).length];
        for (int i=0; i<mmbu.size();i++){
            result[i] = mmbu.get(i);
        }

        return result;
    }

    // Methode zur Überprüfungen, ob die Zeile den Anforderungen der MMBÜ entspricht
    public static boolean checkNeighbor(boolean[] row1, boolean[] row2) {
        int differences = 0;
        for (int i = 0; i < row1.length - 1; i++) {
            if (row1[i] != row2[i]) {
                differences++;
            }
            // Sobald mehr als eine Spalte unterschiedliche Zahlen hat, ist es kein Nachbar mehr
            if (differences > 1) {
                return false;
            }
        }

        // Genau eine Zahl (ausgeschlossen des Ergebnisses) unterscheidet sich
        // UND das Ergebnis unterscheidet sich
        return differences == 1 && row1[row1.length - 1] != row2[row2.length - 1];
    }

}