import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class CoverageTestMCDC {
    @Test
    void testMCDC() {
        // Truth table: 4 tests with 3 conditions and a decision
        boolean[][] truthTable = {
                {true, true, true, true},
                {true, true, false, false},
                {true, false, true, false},
                {true, false, false, false},
                {false, true, true, false},
                {false, true, false, false},
                {false, false, true, false},
                {false, false, false, false}
        };

        boolean[][] selectedTests = MCDC.analyze(truthTable);

        // Expected selected tests (according to MCDC)
        boolean[][] expectedSelectedTests = {
                {true, true, true, true},
                {false, true, true, false},
                {true, false, true, false},
                {true, true, false, false},
        };

        // Sort the-dimensional arrays
        Arrays.sort(selectedTests, (a, b) -> Arrays.compare(a, b));
        Arrays.sort(expectedSelectedTests, (a, b) -> Arrays.compare(a, b));

        assertArrayEquals(expectedSelectedTests, selectedTests);
    }

    @Test
    void testMCDCArrayB() {
        // b)
        boolean[][] testArrayB = {
                {false, false, false, false},
                {false, false, true, false},
                {false, true, false, false},
                {false, true, true, false},
                {true, false, false, false},
                {true, false, true, true},
                {true, true, false, true},
                {true, true, true, false}
        };

        // b) Expected
        boolean[][] expectedTestArrayB = {
                {false,false,true,false},
                {true,false,false,false},
                {true,false,true,true},
                {true,true,false,true}
        };

        boolean[][] mcdcTestCases = MCDC.analyze(testArrayB);

        // Sort the-dimensional arrays
        Arrays.sort(mcdcTestCases, (a, b) -> Arrays.compare(a, b));
        Arrays.sort(expectedTestArrayB, (a, b) -> Arrays.compare(a, b));

        System.out.println();
        assertArrayEquals(expectedTestArrayB, mcdcTestCases);
    }

    @Test
    void testMCDCexercise1_md() throws Exception {
        String fileName = "exercise1.md";

        boolean [][] readedArray = FileReaderFromFile.readFile(fileName);

        // exercise1.md Expected
        boolean[][] expectedTestArrayExercise1 = {
                {false, false, false, true},
                {true, false, false, false},
                {false, true, false, false},
                {false, true, true, true}
        };

        boolean[][] mcdcTestCases = MCDC.analyze(readedArray);

        // Sort the-dimensional arrays
        Arrays.sort(mcdcTestCases, (a, b) -> Arrays.compare(a, b));
        Arrays.sort(expectedTestArrayExercise1, (a, b) -> Arrays.compare(a, b));

        assertArrayEquals(expectedTestArrayExercise1, mcdcTestCases);
    }

    @Test
    void testMCDCexercise2_md() throws Exception {
        String fileName = "exercise2.md";

        boolean [][] readedArray = FileReaderFromFile.readFile(fileName);

        // exercise2.md Expected
        boolean[][] expectedTestArray = {
                {true, false, false, false},
                {false, false, true, false},
                {true, false, true, true},
                {false, true, true, true}
        };

        boolean[][] mcdcTestCases = MCDC.analyze(readedArray);

        // Sort the-dimensional arrays
        Arrays.sort(mcdcTestCases, (a, b) -> Arrays.compare(a, b));
        Arrays.sort(expectedTestArray, (a, b) -> Arrays.compare(a, b));

        assertArrayEquals(expectedTestArray, mcdcTestCases);
    }
    @Test
    void testMCDCexercise3_md() throws Exception {
        String fileName = "exercise3.md";

        boolean[][] readedArray = FileReaderFromFile.readFile(fileName);
        System.out.println("Read Array from File:");
        for (boolean[] row : readedArray) {
            System.out.println(Arrays.toString(row));
        }

        boolean[][] expectedTestArrayExercise3 = {
                {false, false, false, true},
                {false, false, true, true},
                {false, true, false, true},
                {false, true, true, true},
                {true, false, false, true},
                {true, false, true, true},
                {true, true, false, true},
                {true, true, true, true}
        };

        boolean[][] mcdcTestCases = MCDC.analyze(readedArray);

        // Sort the 2-dimensional arrays
        Arrays.sort(mcdcTestCases, new Comparator<boolean[]>() {
            @Override
            public int compare(boolean[] a, boolean[] b) {
                for (int i = 0; i < a.length; i++) {
                    if (a[i] != b[i]) {
                        return Boolean.compare(a[i], b[i]);
                    }
                }
                return 0;
            }
        });

        Arrays.sort(expectedTestArrayExercise3, new Comparator<boolean[]>() {
            @Override
            public int compare(boolean[] a, boolean[] b) {
                for (int i = 0; i < a.length; i++) {
                    if (a[i] != b[i]) {
                        return Boolean.compare(a[i], b[i]);
                    }
                }
                return 0;
            }
        });

        System.out.println("Expected Test Array:");
        for (boolean[] row : expectedTestArrayExercise3) {
            System.out.println(Arrays.toString(row));
        }

        System.out.println("MCDC Test Cases:");
        for (boolean[] row : mcdcTestCases) {
            System.out.println(Arrays.toString(row));
        }

        assertArrayEquals(expectedTestArrayExercise3, mcdcTestCases);
    }


    @Test
    void testMCDCex0() throws Exception {
        String fileName = "exercises\\exercises\\ex0.md";

        boolean [][] readedArray = FileReaderFromFile.readFile(fileName);

        // Expected
        boolean[][] expectedTestArray = {
                {false, true, false, true},
                {true, true, false, false},
                {false, true, true, false},
                {true, false, false, true}
        };

        boolean[][] mcdcTestCases = MCDC.analyze(readedArray);
        // Sort the-dimensional arrays
        Arrays.sort(mcdcTestCases, (a, b) -> Arrays.compare(a, b));
        Arrays.sort(expectedTestArray, (a, b) -> Arrays.compare(a, b));

        assertArrayEquals(expectedTestArray, mcdcTestCases);
    }

    @Test
    void testMCDCex1() throws Exception {
        String fileName = "exercises\\exercises\\ex1.md";

        boolean [][] readedArray = FileReaderFromFile.readFile(fileName);

        // Expected
        boolean[][] expectedTestArray = {
                {false, false, true, false},
                {false, false, false, true},
                {true, false, false, false},
                {false, true, true, true}
        };

        boolean[][] mcdcTestCases = MCDC.analyze(readedArray);


        // Sort the-dimensional arrays
        Arrays.sort(mcdcTestCases, (a, b) -> Arrays.compare(a, b));
        Arrays.sort(expectedTestArray, (a, b) -> Arrays.compare(a, b));

        assertArrayEquals(expectedTestArray, mcdcTestCases);
    }

    @Test
    void testMCDCex2() throws Exception {
        String fileName = "exercises\\exercises\\ex2.md";

        boolean [][] readedArray = FileReaderFromFile.readFile(fileName);

        // Expected
        boolean[][] expectedTestArray = {
                {false, false, false, true},
                {true, false, false, false},
                {true, true, true, false},
                {true, true, false, true}
        };

        boolean[][] mcdcTestCases = MCDC.analyze(readedArray);


        // Sort the-dimensional arrays
        Arrays.sort(mcdcTestCases, (a, b) -> Arrays.compare(a, b));
        Arrays.sort(expectedTestArray, (a, b) -> Arrays.compare(a, b));

        assertArrayEquals(expectedTestArray, mcdcTestCases);
    }

    @Test
    void testMCDCex3() throws Exception {
        String fileName = "exercises\\exercises\\ex3.md";

        assertThrows(IllegalArgumentException.class, () -> {
            boolean [][] readedArray = FileReaderFromFile.readFile(fileName);
            MCDC.analyze(readedArray);
        });
    }

    @Test
    void testMCDCex4() throws Exception {
        String fileName = "exercises\\exercises\\ex4.md";

        boolean [][] readedArray = FileReaderFromFile.readFile(fileName);

        // Expected
        boolean[][] expectedTestArray = {
                {false, false, true, true},
                {false, false, false, false},
                {true, false, false, true},
                {true, true, false, false}
        };

        boolean[][] mcdcTestCases = MCDC.analyze(readedArray);

        // Sort the-dimensional arrays
        Arrays.sort(mcdcTestCases, (a, b) -> Arrays.compare(a, b));
        Arrays.sort(expectedTestArray, (a, b) -> Arrays.compare(a, b));

        assertArrayEquals(expectedTestArray, mcdcTestCases);
    }

    @Test
    void testMCDCex5() throws Exception {
        String fileName = "exercises\\exercises\\ex5.md";

        boolean [][] readedArray = FileReaderFromFile.readFile(fileName);

        // Expected
        boolean[][] expectedTestArray = {
                {true, false, true, false},
                {false, false, false, false},
                {true, false, false, true},
                {true, true, true, true}
        };

        boolean[][] mcdcTestCases = MCDC.analyze(readedArray);

        // Sort the-dimensional arrays
        Arrays.sort(mcdcTestCases, (a, b) -> Arrays.compare(a, b));
        Arrays.sort(expectedTestArray, (a, b) -> Arrays.compare(a, b));

        assertArrayEquals(expectedTestArray, mcdcTestCases);
    }

    @Test
    void testMCDCex6() throws Exception {
        String fileName = "exercises\\exercises\\ex6.md";

        boolean [][] readedArray = FileReaderFromFile.readFile(fileName);

        // Expected
        boolean[][] expectedTestArray = {
                {false, false, true, false},
                {false, false, false, true},
                {true, false, false, false},
                {false, true, false, false}
        };

        boolean[][] mcdcTestCases = MCDC.analyze(readedArray);

        // Sort the-dimensional arrays
        Arrays.sort(mcdcTestCases, (a, b) -> Arrays.compare(a, b));
        Arrays.sort(expectedTestArray, (a, b) -> Arrays.compare(a, b));

        assertArrayEquals(expectedTestArray, mcdcTestCases);
    }

    //TODO Nachfragen weil unsicher wenn bei SignA kein partner gefunden
    @Test
    void testMCDCex7() throws Exception {
        String fileName = "exercises\\exercises\\ex7.md";

        boolean [][] readedArray = FileReaderFromFile.readFile(fileName);

        // Expected
        boolean[][] expectedTestArray = {
                {false, false, false, false},
                {true, false, false, false},
                {false, true, false, false},
                {true, true, false, false},
                {false, false, true, true},
                {true, false, true, true},
                {false, true, true, false},
                {true, true, true, false}
        };

        boolean[][] mcdcTestCases = MCDC.analyze(readedArray);

        // Sort the-dimensional arrays
        Arrays.sort(mcdcTestCases, (a, b) -> Arrays.compare(a, b));
        Arrays.sort(expectedTestArray, (a, b) -> Arrays.compare(a, b));

        assertArrayEquals(expectedTestArray, mcdcTestCases);
    }

    @Test
    void testMCDCCustomEx1() throws Exception {
        String fileName = "exercises\\exercises\\customEx1.md";
        boolean [][] readedArray = FileReaderFromFile.readFile(fileName);

        // expected result of customCustomEx1.md
        boolean[][] expectedTestArrayCustomEx1 = {
                {false, false, false, false},
                {false, false, false, false},
                {false, false, false, false},
                {false, false, false, false}
        };

        boolean[][] mcdcTestCases = MCDC.analyze(readedArray);
        assertArrayEquals(expectedTestArrayCustomEx1, mcdcTestCases);
    }

    @Test
    void testMCDCCustomEx2() throws Exception {
        String fileName = "exercises\\exercises\\customEx2.md";
        boolean [][] readedArray = FileReaderFromFile.readFile(fileName);

        // expected result of customCustomEx1.md
        boolean[][] expectedTestArrayCustomEx1 = {
                {true, true},
                {true, true},
                {true, true},
                {true, true}
        };
        boolean[][] mcdcTestCases = MCDC.analyze(readedArray);
        assertArrayEquals(expectedTestArrayCustomEx1, mcdcTestCases);
    }

    @Test
    void testMCDCCustomEx3() {
        String fileName = "exercises\\exercises\\customEx3.md";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            boolean[][] readedArray = FileReaderFromFile.readFile(fileName);
            System.out.println("Read Array from File:");
            for (boolean[] row : readedArray) {
                System.out.println(Arrays.toString(row));
            }
            MCDC.analyze(readedArray);
        });

        System.out.println("Exception message: " + exception.getMessage());
    }


    @Test
    void testMCDCCustomEx4() {
        String fileName = "exercises\\exercises\\customEx4.md";

        assertThrows(Exception.class, () -> {
            boolean [][] readedArray = FileReaderFromFile.readFile(fileName);
            MCDC.analyze(readedArray);
        });
    }

    @Test
    void testMCDCCustomEx5() throws Exception {
        String fileName = "exercises\\exercises\\customEx5.csv";
        boolean[][] readedArray = FileReaderFromFile.readFile(fileName);

        boolean[][] mcdcTestCases = MCDC.analyze(readedArray);

        Arrays.sort(mcdcTestCases, (a, b) -> Arrays.compare(a, b));

        boolean[][] expectedTestArray = {
                {true, false, false, true},
                {false, true, true, false},
                {false, true, false, true},
                {true, true, false, false}
        };

        Arrays.sort(expectedTestArray, (a, b) -> Arrays.compare(a, b));

        System.out.println("Expected Test Array:");
        for (boolean[] row : expectedTestArray) {
            System.out.println(Arrays.toString(row));
        }

        System.out.println("MCDC Test Cases:");
        for (boolean[] row : mcdcTestCases) {
            System.out.println(Arrays.toString(row));
        }

        assertArrayEquals(expectedTestArray, mcdcTestCases);
    }

    @Test
    void testMCDCCustomEx6() throws Exception {
        String fileName = "exercises\\exercises\\customEx6.csv";
        boolean[][] readedArray = FileReaderFromFile.readFile(fileName);

        // expected result of customCustomEx6.md
        boolean[][] expectedTestArrayCustomEx6 = {
                {false,false,false,false},
                {false,false,false,false},
                {false,false,false,false},
                {false,false,false,false}
        };

        boolean[][] mcdcTestCases = MCDC.analyze(readedArray);
        assertArrayEquals(expectedTestArrayCustomEx6, mcdcTestCases);
    }

    @Test
    void testMCDCCustomEx7() {
        String fileName = "exercises\\exercises\\customEx7.csv";

        assertThrows(Exception.class, () -> {
            boolean [][] readedArray = FileReaderFromFile.readFile(fileName);
            MCDC.analyze(readedArray);
        });
    }

    @Test
    void testMCDCCustomEx8() throws Exception {
        String fileName = "exercises\\exercises\\customEx8.csv";
        boolean [][] readedArray = FileReaderFromFile.readFile(fileName);

        // expected result of customEx8.csv
        boolean[][] expectedTestArray = {
                {true, false, false, true},
                {false, true, false, true},
                {true, true, false, false},
                {false, true, true, false}
        };

        boolean[][] mcdcTestCases = MCDC.analyze(readedArray);
        // Sort the-dimensional arrays
        Arrays.sort(mcdcTestCases, (a, b) -> Arrays.compare(a, b));
        Arrays.sort(expectedTestArray, (a, b) -> Arrays.compare(a, b));

        assertArrayEquals(expectedTestArray, mcdcTestCases);
    }

    @Test
    void testMCDCCustomEx9() {
        String fileName = "exercises\\exercises\\customEx9.md";

        assertThrows(Exception.class, () -> {
            boolean [][] readedArray = FileReaderFromFile.readFile(fileName);
            MCDC.analyze(readedArray);
        });
    }

    @Test
    void testMCDCCustomEx10() {
        String fileName = "exercises\\exercises\\customEx10.md";
        assertThrows(Exception.class, () -> {
            boolean [][] readedArray = FileReaderFromFile.readFile(fileName);
            MCDC.analyze(readedArray);
        });
    }

    @Test
    void testMCDCCustomEx11() {
        String fileName = "exercises\\exercises\\customEx11.csv";

        assertThrows(Exception.class, () -> {
            boolean [][] readedArray = FileReaderFromFile.readFile(fileName);
            MCDC.analyze(readedArray);
        });
    }

    @Test
    void testMCDCCustomEx12() {
        String fileName = "exercises\\exercises\\customEx12.csv";

        assertThrows(Exception.class, () -> {
            boolean [][] readedArray = FileReaderFromFile.readFile(fileName);
            MCDC.analyze(readedArray);
        });
    }

    @Test
    void testMCDCCustomEx13() throws Exception {
        String fileName = "exercises\\exercises\\customEx13.csv";

        boolean [][] readedArray = FileReaderFromFile.readFile(fileName);

        // expected result of customCustomEx13.md
        boolean[][] expectedTestArrayCustomEx13 = {
                {false,true,false},
                {true,false,false},
                {true,true,true}
        };

        boolean[][] mcdcTestCases = MCDC.analyze(readedArray);
        assertArrayEquals(expectedTestArrayCustomEx13, mcdcTestCases);
    }

    @Test
    void testMCDCCustomEx14() throws Exception {
        String fileName = "exercises\\exercises\\customEx14.md";

        boolean [][] readedArray = FileReaderFromFile.readFile(fileName);

        // expected result of customCustomEx14.md
        boolean[][] expectedTestArrayCustomEx14 = {
                {false,false,false,false},
                {false,false,false,false}
        };

        boolean[][] mcdcTestCases = MCDC.analyze(readedArray);
        assertArrayEquals(expectedTestArrayCustomEx14, mcdcTestCases);
    }
    @Test
    void testMCDCCustomEx16() {
        String fileName = "exercises\\exercises\\customEx16.md";

        assertThrows(Exception.class, () -> {
            boolean [][] readedArray = FileReaderFromFile.readFile(fileName);
            MCDC.analyze(readedArray);
        });
    }
    @Test
    void testAnalyzeWithNullInput() {
        assertThrows(IllegalArgumentException.class, () -> {
            MCDC.analyze(null);
        });
    }
    @Test
    void testAnalyzeWithEmptyArray() {
        boolean[][] emptyArray = {};
        assertThrows(IllegalArgumentException.class, () -> {
            MCDC.analyze(emptyArray);
        });
    }

    @Test
    void testAnalyzeWithUnequalColumns() {
        boolean[][] unequalColumns = {
                {true, false},
                {true}
        };
        assertThrows(IllegalArgumentException.class, () -> {
            MCDC.analyze(unequalColumns);
        });
    }



}