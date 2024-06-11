import CSV.CSVFileReader;
import CSV.CSVFileWriter;
import Interface.FileReader;
import Interface.FileWriter;
import Markdown.MarkdownFileReader;
import Markdown.MarkdownFileWriter;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FileReaderWriterTest {

    @Test
    void testMarkdownReaderWriter() throws IOException {
        // System.out.println("Testing Markdown Reader and Writer...");

        String markdownContent1 =
                "| A | B | C |\n" +
                "|---|---|---|\n" +
                "| 1 | 0 | 1 |\n" +
                "| 0 | 1 | 0 |\n" +
                "| 1 | 1 | 1 |\n";

        String markdownContent2 =
                "A | B | C\n" +
                "--- | --- | ---\n" +
                "1 | 0 | 1\n" +
                "0 | 1 | 0\n" +
                "1 | 1 | 1\n";


        Path tempInputFile1 = Files.createTempFile("test_input1", ".md");
        Path tempOutputFile1 = Files.createTempFile("test_output1", ".md");
        Files.writeString(tempInputFile1, markdownContent1);

        Path tempInputFile2 = Files.createTempFile("test_input2", ".md");
        Path tempOutputFile2 = Files.createTempFile("test_output2", ".md");
        Files.writeString(tempInputFile2, markdownContent2);

        FileReader markdownReader = new MarkdownFileReader();
        FileWriter markdownWriter = new MarkdownFileWriter();

        boolean[][] truthTable1 = markdownReader.read(tempInputFile1.toFile());
        markdownWriter.write(tempOutputFile1.toFile(), truthTable1);

        boolean[][] truthTable2 = markdownReader.read(tempInputFile2.toFile());
        markdownWriter.write(tempOutputFile2.toFile(), truthTable2);

        boolean[][] expected = {
                {true, false, true},
                {false, true, false},
                {true, true, true}
        };

        System.out.println("Input Markdown File 1:");
        Files.lines(tempInputFile1).forEach(System.out::println);

        System.out.println("Output Markdown File 1:");
        Files.lines(tempOutputFile1).forEach(System.out::println);

        System.out.println("Input Markdown File 2:");
        Files.lines(tempInputFile2).forEach(System.out::println);

        System.out.println("Output Markdown File 2:");
        Files.lines(tempOutputFile2).forEach(System.out::println);

        System.out.println("Verifying the data...");
        assertArrayEquals(truthTable1, expected);
        assertArrayEquals(truthTable2, expected);

//        if (Arrays.deepEquals(truthTable1, expected) && Arrays.deepEquals(truthTable2, expected)) {
//            System.out.println("Markdown Reader and Writer test passed.");
//        } else {
//            System.out.println("Markdown Reader and Writer test failed.");
//        }
    }

    @Test
    void testCSVReaderWriter() throws IOException {
        System.out.println("Testing CSV Reader and Writer...");

        String csvContent =
                "1,0,1\n" +
                "0,1,0\n" +
                "1,1,1\n";


        Path tempInputFile = Files.createTempFile("test_input", ".csv");
        Path tempOutputFile = Files.createTempFile("test_output", ".csv");
        Files.writeString(tempInputFile, csvContent);

        FileReader csvReader = new CSVFileReader();
        FileWriter csvWriter = new CSVFileWriter();

        boolean[][] truthTable = csvReader.read(tempInputFile.toFile());
        csvWriter.write(tempOutputFile.toFile(), truthTable);

        boolean[][] expected = {
                {true, false, true},
                {false, true, false},
                {true, true, true}
        };

        System.out.println("Input CSV File:");
        Files.lines(tempInputFile).forEach(System.out::println);

        System.out.println("Output CSV File:");
        Files.lines(tempOutputFile).forEach(System.out::println);

        System.out.println("Verifying the data...");
        assertArrayEquals(truthTable, expected);
//        if (Arrays.deepEquals(truthTable, expected)) {
//            System.out.println("CSV Reader and Writer test passed.");
//        } else {
//            System.out.println("CSV Reader and Writer test failed.");
//        }
    }
    @Test
    void testCSVnoContent() throws IOException {
        System.out.println("Testing CSV Reader and Writer...");

        String csvContent =
                "";


        Path tempInputFile = Files.createTempFile("test_input", ".csv");
        Path tempOutputFile = Files.createTempFile("test_output", ".csv");
        Files.writeString(tempInputFile, csvContent);

        FileReader csvReader = new CSVFileReader();
        FileWriter csvWriter = new CSVFileWriter();

        assertThrows(IllegalArgumentException.class, () -> csvReader.read(tempInputFile.toFile()));

    }
    @Test
    void testCSVnoCol() throws IOException {
        System.out.println("Testing CSV Reader and Writer...");

        String csvContent =
                "1, 1, 0\n" +
                        "0,1";


        Path tempInputFile = Files.createTempFile("test_input", ".csv");
        Path tempOutputFile = Files.createTempFile("test_output", ".csv");
        Files.writeString(tempInputFile, csvContent);

        FileReader csvReader = new CSVFileReader();
        FileWriter csvWriter = new CSVFileWriter();

        assertThrows(IllegalArgumentException.class, () -> csvReader.read(tempInputFile.toFile()));

    }
    @Test
    void testnonvalidformat() throws Exception{
        String fileName = "exercise3.txt";
        assertThrows(IllegalArgumentException.class, () -> FileReaderFromFile.readFile(fileName));
    }

    @Test
    void testfilenotfound() throws Exception{
        String fileName = "nonexistent.md";
        assertThrows(IllegalArgumentException.class, () -> FileReaderFromFile.readFile(fileName));
    }


    @Test
    void testReadWithTooFewLines() throws IOException {
        MarkdownFileReader reader = new MarkdownFileReader();
        Path tempFile = Files.createTempFile("test_too_few_lines", ".md");
        Files.writeString(tempFile, "A | B | C\n--- | --- | ---");

        // Test, ob eine Ausnahme geworfen wird, wenn die Datei zu wenige Zeilen hat
        assertThrows(IllegalArgumentException.class, () -> {
            reader.read(tempFile.toFile());
        });
    }

    @Test
    void testReadWithColumnMismatch() throws IOException {
        MarkdownFileReader reader = new MarkdownFileReader();
        Path tempFile = Files.createTempFile("test_column_mismatch", ".md");
        Files.writeString(tempFile, "A | B | C\n--- | --- | ---\n1 | 0 | 1\n0 | 1");

        // Test, ob eine Ausnahme geworfen wird, wenn die Spaltenanzahl nicht übereinstimmt
        assertThrows(IllegalArgumentException.class, () -> {
            reader.read(tempFile.toFile());
        });
    }

    @Test
    void testReadWithInvalidContent() throws IOException {
        MarkdownFileReader reader = new MarkdownFileReader();
        Path tempFile = Files.createTempFile("test_invalid_content", ".md");
        Files.writeString(tempFile, "A | B | C\n--- | --- | ---\n1 | 0 | 1\n0 | 1 | invalid");

        // Test, ob eine Ausnahme geworfen wird, wenn der Inhalt nicht 1 oder 0 ist
        assertThrows(IllegalArgumentException.class, () -> {
            reader.read(tempFile.toFile());
        });
    }

    @Test
    void testReadWithValidFormatAndPipeSyntax() throws IOException {
        MarkdownFileReader reader = new MarkdownFileReader();
        Path tempFile = Files.createTempFile("test_valid_with_pipes", ".md");
        Files.writeString(tempFile, "| A | B | C |\n|---|---|---|\n| 1 | 0 | 1 |\n| 0 | 1 | 0 |\n| 1 | 1 | 1 |");

        boolean[][] result = reader.read(tempFile.toFile());
        boolean[][] expected = {
                {true, false, true},
                {false, true, false},
                {true, true, true}
        };

        // Test, ob die Datei korrekt gelesen wird, wenn sie Pipes enthält
        assertArrayEquals(expected, result);
    }

    @Test
    void testReadWithValidFormatWithoutPipeSyntax() throws IOException {
        MarkdownFileReader reader = new MarkdownFileReader();
        Path tempFile = Files.createTempFile("test_valid_without_pipes", ".md");
        Files.writeString(tempFile, "A | B | C\n--- | --- | ---\n1 | 0 | 1\n0 | 1 | 0\n1 | 1 | 1");

        boolean[][] result = reader.read(tempFile.toFile());
        boolean[][] expected = {
                {true, false, true},
                {false, true, false},
                {true, true, true}
        };

        // Test, ob die Datei korrekt gelesen wird, wenn sie keine Pipes enthält
        assertArrayEquals(expected, result);
    }

}