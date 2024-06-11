import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class
MainTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    private final String basePath = "src\\";

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    void testMainWithMCDCAnalysis() throws Exception {
        Path tempFile = Path.of(basePath + "md\\data1.md");
        if (Files.exists(tempFile)) {
            Files.delete(tempFile);
        }
        Files.createFile(tempFile);
        Files.writeString(tempFile, "| A | B | C |\n|---|---|---|\n| 1 | 0 | 1 |");

        String outputFilePath = basePath + "output\\output_data1.md";
        String[] args = {tempFile.toString(), "MCDC"};
        Main.main(args);

        File outputFile = new File(outputFilePath);
        assertTrue(outputFile.exists(), "Output file should exist");
    }

    @Test
    void testMainWithMMBÜAnalysis() throws Exception {
        Path tempFile = Path.of(basePath + "md\\data2.md");
        if (Files.exists(tempFile)) {
            Files.delete(tempFile);
        }
        Files.createFile(tempFile);
        Files.writeString(tempFile, "| A | B | C |\n|---|---|---|\n| 1 | 0 | 1 |");

        String outputFilePath = basePath + "output\\output_data2.md";
        String[] args = {tempFile.toString(), "MMBÜ"};
        Main.main(args);

        File outputFile = new File(outputFilePath);
        assertTrue(outputFile.exists(), "Output file should exist");
    }

    @Test
    void testMainWithCSVFiles() throws Exception {
        Path tempFile = Path.of(basePath + "md\\data3.md");
        if (Files.exists(tempFile)) {
            Files.delete(tempFile);
        }
        Files.createFile(tempFile);
        Files.writeString(tempFile, "| A | B | C |\n|---|---|---|\n| 1 | 0 | 1 |");

        String outputFilePath = basePath + "output\\output_data3.md";
        String[] args = {tempFile.toString(), "MCDC"};
        Main.main(args);

        File outputFile = new File(outputFilePath);
        assertTrue(outputFile.exists(), "Output file should exist");
    }

}
