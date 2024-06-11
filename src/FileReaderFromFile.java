import CSV.CSVFileReader;
import Interface.FileReader;
import Markdown.MarkdownFileReader;

import java.io.File;
import java.util.Arrays;

public class FileReaderFromFile {

    public static boolean[][] readFile(String fileName) throws Exception {
        MCDC mcdc = new MCDC();
        MMBU mmbü = new MMBU();

        String pathFile = "bin\\";
        File file = new File(pathFile + fileName);
        if (!file.exists()) {
            throw new IllegalArgumentException("File not found: " + fileName);
        }

        FileReader reader;

        if (fileName.endsWith(".md")) {
            reader = new MarkdownFileReader();
        } else if (fileName.endsWith(".csv")) {
            reader = new CSVFileReader();
        } else {
            throw new IllegalArgumentException("\"Unsupported file format: " + fileName);
        }

        boolean[][] truthTable = reader.read(file);

        System.out.println(Arrays.deepToString(truthTable));
        return truthTable;
    }
}