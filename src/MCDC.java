import Ressources.Pair;
import java.util.*;

public class MCDC {

    public static boolean[][] analyze(boolean[][] testArray) {
        if (testArray == null || testArray.length == 0 || testArray[0].length < 2) {
            throw new IllegalArgumentException("Invalid input array: The array must have at least one row and two columns.");
        }

        System.out.println("Input Array:");
        for (boolean[] row : testArray) {
            if (row.length != testArray[0].length) {
                throw new IllegalArgumentException("Invalid input array: All rows must have the same number of columns.");
            }
            System.out.println(Arrays.toString(row));
        }

        List[] signLists = new ArrayList[testArray[0].length - 1];
        for (int sign = 0; sign < testArray[0].length - 1; sign++) {
            List<Pair> signPairs = new ArrayList<>();
            for (boolean[] testCase : testArray) {
                boolean[] invertNeighbor = searchDirectNeighbor(testArray, testCase, sign);
                if (invertNeighbor != null) {
                    if (compareResultNotEqual(invertNeighbor, testCase)) {
                        signPairs.add(new Pair(invertNeighbor, testCase));
                    }
                }
            }
            signLists[sign] = signPairs;
        }

        if (signLists.length > 0) {
            List<boolean[]> result = generateCombinations(signLists);

            if (!result.isEmpty()) {
                boolean[][] finalResult = new boolean[result.size()][result.get(0).length];
                for (int i = 0; i < result.size(); i++) {
                    finalResult[i] = result.get(i);
                }
                System.out.println("Final Result:");
                for (boolean[] row : finalResult) {
                    System.out.println(Arrays.toString(row));
                }
                return finalResult;
            }
        }
        return testArray;
    }


    public static boolean[] searchDirectNeighbor(boolean[][] testArray, boolean[] testCase, int sign) {
        boolean[] invert = new boolean[testCase.length - 1];
        for (int i = 0; i < testCase.length - 1; i++) {
            if (i == sign) {
                invert[i] = !testCase[i];
            } else {
                invert[i] = testCase[i];
            }
        }

        for (boolean[] PotNeighbor : testArray) {
            if (compareTest(PotNeighbor, invert)) {
                return PotNeighbor;
            }
        }

        return null;
    }

    public static boolean compareTest(boolean[] PotNeighbor, boolean[] invert) {
        boolean[] shortPotNeighbor = new boolean[PotNeighbor.length - 1];
        for (int i = 0; i < PotNeighbor.length - 1; i++) {
            shortPotNeighbor[i] = PotNeighbor[i];
        }
        return Arrays.equals(shortPotNeighbor, invert);
    }

    public static boolean compareResultNotEqual(boolean[] Neighbor, boolean[] testCase) {
        return Neighbor[Neighbor.length - 1] != testCase[testCase.length - 1];
    }

    public static List<boolean[]> generateCombinations(List<Pair>[] signLists) {
        List<boolean[]> result = new ArrayList<>();
        if (signLists.length == 0) {
            return result;
        }

        generateCombinationsRecursive(signLists, 0, new HashSet<boolean[]>(), result);
        return result;
    }

    private static void generateCombinationsRecursive(List<Pair>[] signLists, int depth, Set<boolean[]> currentSet, List<boolean[]> result) {
        if (depth == signLists.length) {
            if (result.isEmpty() || currentSet.size() < result.size()) {
                result.clear();
                result.addAll(currentSet);
            }
            return;
        }

        for (Pair pair : signLists[depth]) {
            Set<boolean[]> newSet = new HashSet<>(currentSet);
            newSet.add(pair.getFirst());
            newSet.add(pair.getSecond());

            generateCombinationsRecursive(signLists, depth + 1, newSet, result);
        }
    }
}
