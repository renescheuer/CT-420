import CSV.CSVFileReader;
import CSV.CSVFileWriter;
import Interface.FileReader;
import Interface.FileWriter;
import Markdown.MarkdownFileReader;
import Markdown.MarkdownFileWriter;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws Exception {
        if (args.length % 2 != 0) {
            System.err.println("Usage: java Main <file1> <criterion1> <file2> <criterion2> ...");
            System.exit(1);
        }

        MCDC mcdc = new MCDC();
        MMBU mmbü = new MMBU();

        try {
            for (int i = 0; i < args.length; i += 2) {
                String fileName = args[i];
                String criterion = args[i + 1];

                String pathFile = new File("").getAbsolutePath();
                File file = new File(pathFile + "\\bin\\" + fileName);
                if (!file.exists()) {
                    System.err.println("File not found: " + fileName);
                    continue;
                }

                FileReader reader;
                FileWriter writer;

                if (fileName.endsWith(".md")) {
                    reader = new MarkdownFileReader();
                    writer = new MarkdownFileWriter();
                } else if (fileName.endsWith(".csv")) {
                    reader = new CSVFileReader();
                    writer = new CSVFileWriter();
                } else {
                    System.err.println("Unsupported file format: " + fileName);
                    continue;
                }

                boolean[][] truthTable = reader.read(file);
                boolean[][] resultTable = truthTable;

                try{
                    if (criterion.equalsIgnoreCase("MCDC")) {
                        resultTable = mcdc.analyze(truthTable);
                    } else if (criterion.equalsIgnoreCase("MMBÜ")) {
                        resultTable = mmbü.analyze(truthTable);
                    }
                }
                catch (Exception e){
                    throw new Exception("Falscher Criterion");
                }

                File outputFile = new File(pathFile + "\\src\\output", "output_" + file.getName());
                writer.write(outputFile, resultTable);
                System.out.println("Processed: " + fileName);
                System.out.println("Output: " + outputFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

