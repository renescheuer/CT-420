import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoverageTestMMBU {
    @Test
    void testMMBÜArrayA() {
        // a)
        boolean[][] testArrayA = {
                {false, false, false, true},
                {true, false, false, false},
                {false, true, false, false},
                {true, true, false, false}, // nicht benötigt für MMBÜ
                {false, false, true, true},
                {true, false, true, false},
                {false, true, true, true},
                {true, true, true, false}
        };

        // a) Expected
        boolean[][] expectedTestArrayA = {
                {false, false, false, true},
                {true, false, false, false},
                {false, true, false, false},
                {false, false, true, true},
                {true, false, true, false},
                {false, true, true, true},
                {true, true, true, false}
        };

        boolean[][] mmbuTestCases = MMBU.analyze(testArrayA);
        assertArrayEquals(expectedTestArrayA, mmbuTestCases);
    }
    @Test
    void testMMBÜArrayB() {
        // b)
        boolean[][] testArrayB = {
                {false, false, false, false}, // nicht benötigt für MMBÜ
                {false, false, true, false},
                {false, true, false, false},
                {false, true, true, false}, // nicht benötigt für MMBÜ
                {true, false, false, false},
                {true, false, true, true},
                {true, true, false, true},
                {true, true, true, false}
        };

        // b) Expected
        boolean[][] expectedTestArrayB = {
                {false, false, true, false},
                {false, true, false, false},
                {true, false, false, false},
                {true, false, true, true},
                {true, true, false, true},
                {true, true, true, false}
        };
        boolean[][] mmbuTestCases = MMBU.analyze(testArrayB);
        assertArrayEquals(expectedTestArrayB, mmbuTestCases);
    }
    @Test
    void testMMBÜexercise1_md() throws Exception {
        String fileName = "exercise1.md";
        boolean [][] readedArray = FileReaderFromFile.readFile(fileName);

        // expected result of exercise1.md
        boolean[][] expectedTestArrayExercise1 = {
                {false, false, false, true},
                {true, false, false, false},
                {false, true, false, false},
                {false, false, true, true},
                {true, false, true, false},
                {false, true, true, true},
                {true, true, true, false}
        };

        boolean[][] mmbuTestCases = MMBU.analyze(readedArray);
        assertArrayEquals(expectedTestArrayExercise1, mmbuTestCases);
    }

    @Test
    void testMMBÜexercise2_md() throws Exception {
        String fileName = "exercise2.md";
        boolean [][] readedArray = FileReaderFromFile.readFile(fileName);

        // expected result of exercise2.md
        boolean[][] expectedTestArrayExercise2 = {
                {true, false, false, false},
                {false, true, false, false},
                {false, false, true, false},
                {true, false, true, true},
                {false, true, true, true},
                {true, true, true, false}
        };

        boolean[][] mmbuTestCases = MMBU.analyze(readedArray);
        assertArrayEquals(expectedTestArrayExercise2, mmbuTestCases);
    }
    @Test
    void testMMBÜex0() throws Exception {
        String fileName = "exercises\\exercises\\ex0.md";
        boolean [][] readedArray = FileReaderFromFile.readFile(fileName);

        // expected result of ex0.md
        boolean[][] expectedTestArrayEx0 = {
                {false, false, false, true},
                {true, false, false, true},
                {false, true, false, true},
                {true, true, false, false},
                {false, false, true, false},
                {true, false, true, true},
                {false, true, true, false},
                {true, true, true, false}
        };

        boolean[][] mmbuTestCases = MMBU.analyze(readedArray);
        assertArrayEquals(expectedTestArrayEx0, mmbuTestCases);
    }

    @Test
    void testMMBÜex1() throws Exception {
        String fileName = "exercises\\exercises\\ex1.md";
        boolean [][] readedArray = FileReaderFromFile.readFile(fileName);

        // expected result of ex1.md
        boolean[][] expectedTestArrayEx1 = {
                {false, false, false, true},
                {true, false, false, false},
                {false, true, false, true},
                {true, true, false, false},
                {false, false, true, false},
                {true, false, true, true},
                {false, true, true, true},
                {true, true, true, false}
        };

        boolean[][] mmbuTestCases = MMBU.analyze(readedArray);

        assertArrayEquals(expectedTestArrayEx1, mmbuTestCases);
    }

    @Test
    void testMMBÜex2() throws Exception {
        String fileName = "exercises\\exercises\\ex2.md";
        boolean [][] readedArray = FileReaderFromFile.readFile(fileName);

        // expected result of ex2.md
        boolean[][] expectedTestArrayEx2 = {
                {false, false, false, true},
                {true, false, false, false},
                {false, true, false, true},
                {true, true, false, true},
                {false, false, true, true},
                {true, false, true, false},
                {false, true, true, false},
                {true, true, true, false}
        };

        boolean[][] mmbuTestCases = MMBU.analyze(readedArray);
        assertArrayEquals(expectedTestArrayEx2, mmbuTestCases);
    }

    @Test
    void testMMBÜex3() throws Exception {
        String fileName = "exercises\\exercises\\ex3.md";

        assertThrows(IllegalArgumentException.class, () -> {
            boolean [][] readedArray = FileReaderFromFile.readFile(fileName);
            MMBU.analyze(readedArray);
        });
    }

    @Test
    void testMMBÜex4() throws Exception {
        String fileName = "exercises\\exercises\\ex4.md";
        boolean [][] readedArray = FileReaderFromFile.readFile(fileName);

        // expected result of ex4.md
        boolean[][] expectedTestArrayEx4 = {
                {false, false, false, false},
                {true, false, false, true},
                {true, true, false, false},
                {false, false, true, true},
                {false, true, true, false},
                {true, true, true, true}
        };

        boolean[][] mmbuTestCases = MMBU.analyze(readedArray);
        assertArrayEquals(expectedTestArrayEx4, mmbuTestCases);
    }

    @Test
    void testMMBÜex5() throws Exception {
        String fileName = "exercises\\exercises\\ex5.md";
        boolean [][] readedArray = FileReaderFromFile.readFile(fileName);

        // expected result of ex5.md
        boolean[][] expectedTestArrayEx5 = {
                {false, false, false, false},
                {true, false, false, true},
                {false, true, false, false},
                {true, true, false, true},
                {false, false, true, true},
                {true, false, true, false},
                {false, true, true, true},
                {true, true, true, true}
        };

        boolean[][] mmbuTestCases = MMBU.analyze(readedArray);
        assertArrayEquals(expectedTestArrayEx5, mmbuTestCases);
    }

    @Test
    void testMMBÜex6() throws Exception {
        String fileName = "exercises\\exercises\\ex6.md";
        boolean [][] readedArray = FileReaderFromFile.readFile(fileName);

        // expected result of ex6.md
        boolean[][] expectedTestArrayEx6 = {
                {false, false, false, true},
                {true, false, false, false},
                {false, true, false, false},
                {false, false, true, false},
                {true, false, true, true},
                {false, true, true, true},
                {true, true, true, false}
        };

        boolean[][] mmbuTestCases = MMBU.analyze(readedArray);
        assertArrayEquals(expectedTestArrayEx6, mmbuTestCases);
    }

    @Test
    void testMMBÜex7() throws Exception {
        String fileName = "exercises\\exercises\\ex7.md";
        boolean [][] readedArray = FileReaderFromFile.readFile(fileName);

        // expected result of ex7.md
        boolean[][] expectedTestArrayEx7 = {
                {false, false, false, false},
                {true, false, false, false},
                {false, false, true, true},
                {true, false, true, true},
                {false, true, true, false},
                {true, true, true, false}
        };

        boolean[][] mmbuTestCases = MMBU.analyze(readedArray);
        assertArrayEquals(expectedTestArrayEx7, mmbuTestCases);
    }

    @Test
    void testMMBÜCustomEx1() throws Exception {
        String fileName = "exercises\\exercises\\customEx1.md";
        boolean [][] readedArray = FileReaderFromFile.readFile(fileName);

        // expected result of customCustomEx1.md
        boolean[][] expectedTestArrayCustomEx1 = {};

        boolean[][] mmbuTestCases = MMBU.analyze(readedArray);
        assertArrayEquals(expectedTestArrayCustomEx1, mmbuTestCases);
    }

    @Test
    void testMMBÜCustomEx2() throws Exception {
        String fileName = "exercises\\exercises\\customEx2.md";
        boolean [][] readedArray = FileReaderFromFile.readFile(fileName);

        // expected result of customCustomEx1.md
        boolean[][] expectedTestArrayCustomEx1 = {};

        boolean[][] mmbüTestCases = MMBU.analyze(readedArray);
        assertArrayEquals(expectedTestArrayCustomEx1, mmbüTestCases);
    }

    @Test
    void testMMBÜCustomEx3() {
        String fileName = "exercises\\exercises\\customEx3.md";

        assertThrows(Exception.class, () -> {
            boolean [][] readedArray = FileReaderFromFile.readFile(fileName);
            MMBU.analyze(readedArray);
        });
    }

    @Test
    void testMMBÜCustomEx4() {
        String fileName = "exercises\\exercises\\customEx4.md";

        assertThrows(Exception.class, () -> {
            boolean [][] readedArray = FileReaderFromFile.readFile(fileName);
            MMBU.analyze(readedArray);
        });
    }

    @Test
    void testMMBÜCustomEx5() throws Exception {
        String fileName = "exercises\\exercises\\customEx5.csv";
        boolean[][] readedArray = FileReaderFromFile.readFile(fileName);

        // expected result of customCustomEx5.csv
        boolean[][] expectedTestArrayCustomEx5 = {
                {false, false, false, true},
                {true, false, false, true},
                {false, true, false, true},
                {true, true, false, false},
                {false, false, true, false},
                {true, false, true, true},
                {false, true, true, false},
                {true, true, true, false}
        };
        boolean[][] mmbuTestCases = MMBU.analyze(readedArray);
        assertArrayEquals(expectedTestArrayCustomEx5, mmbuTestCases);
    }

    @Test
    void testMMBÜCustomEx6() throws Exception {
        String fileName = "exercises\\exercises\\customEx6.csv";
        boolean[][] readedArray = FileReaderFromFile.readFile(fileName);

        // expected result of customCustomEx6.md
        boolean[][] expectedTestArrayCustomEx6 = {};

        boolean[][] mmbuTestCases = MMBU.analyze(readedArray);
        assertArrayEquals(expectedTestArrayCustomEx6, mmbuTestCases);
    }

    @Test
    void testMMBÜCustomEx7() {
        String fileName = "exercises\\exercises\\customEx7.csv";

        assertThrows(Exception.class, () -> {
            boolean [][] readedArray = FileReaderFromFile.readFile(fileName);
            MMBU.analyze(readedArray);
        });
    }

    @Test
    void testMMBÜCustomEx8() throws Exception {
        String fileName = "exercises\\exercises\\customEx8.csv";
        boolean [][] readedArray = FileReaderFromFile.readFile(fileName);

        // expected result of customEx8.csv
        boolean[][] expectedTestArrayEx8 = {
                {false, false, false, true},
                {true, false, false, true},
                {false, true, false, true},
                {true, true, false, false},
                {false, false, true, false},
                {true, false, true, true},
                {false, true, true, false},
                {true, true, true, false}
        };

        boolean[][] mmbuTestCases = MMBU.analyze(readedArray);
        assertArrayEquals(expectedTestArrayEx8, mmbuTestCases);
    }

    @Test
    void testMMBÜCustomEx9() {
        String fileName = "exercises\\exercises\\customEx9.md";

        assertThrows(Exception.class, () -> {
            boolean [][] readedArray = FileReaderFromFile.readFile(fileName);
            MMBU.analyze(readedArray);
        });
    }

    @Test
    void testMMBÜCustomEx10() {
        String fileName = "exercises\\exercises\\customEx10.md";

        assertThrows(Exception.class, () -> {
            boolean [][] readedArray = FileReaderFromFile.readFile(fileName);
            MMBU.analyze(readedArray);
        });
    }

    @Test
    void testMMBÜCustomEx11() {
        String fileName = "exercises\\exercises\\customEx11.csv";

        assertThrows(Exception.class, () -> {
            boolean [][] readedArray = FileReaderFromFile.readFile(fileName);
            MMBU.analyze(readedArray);
        });
    }

    @Test
    void testMMBÜCustomEx12() {
        String fileName = "exercises\\exercises\\customEx12.csv";

        assertThrows(Exception.class, () -> {
            boolean [][] readedArray = FileReaderFromFile.readFile(fileName);
            MMBU.analyze(readedArray);
        });
    }

    @Test
    void testMMBÜCustomEx13() throws Exception {
        String fileName = "exercises\\exercises\\customEx13.csv";
        boolean [][] readedArray = FileReaderFromFile.readFile(fileName);

        // expected result of customEx13.csv
        boolean[][] expectedTestArrayEx13 = {
                {true, true, true},
                {true, false, false},
                {false, true, false},
        };

        boolean[][] mmbuTestCases = MMBU.analyze(readedArray);
        assertArrayEquals(expectedTestArrayEx13, mmbuTestCases);
    }

    @Test
    void testMMBÜCustomEx14() throws Exception {
        String fileName = "exercises\\exercises\\customEx14.md";
        boolean[][] readedArray = FileReaderFromFile.readFile(fileName);

        // expected result of customCustomEx14.md
        boolean[][] expectedTestArrayCustomEx14 = {};

        boolean[][] mmbuTestCases = MMBU.analyze(readedArray);
        assertArrayEquals(expectedTestArrayCustomEx14, mmbuTestCases);
    }

    @Test
    void testMMBÜCustomEx15() throws Exception {
        String fileName = "exercises\\exercises\\customEx15.csv";
        boolean[][] readedArray = FileReaderFromFile.readFile(fileName);

        // expected result of customEx15.csv
        boolean[][] expectedTestArrayEx15 = {
                {false, false, false, false, false, false, false, false, true},
                {true, false, false, false, false, false, false, false, false},
                {false, false, true, false, false, false, false, false, false},
                {false, false, false, true, false, false, false, false, false}
        };

        boolean[][] mmbuTestCases = MMBU.analyze(readedArray);
        assertArrayEquals(expectedTestArrayEx15, mmbuTestCases);
    }
}