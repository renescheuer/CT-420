package Markdown;

import Interface.FileReader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class MarkdownFileReader implements FileReader {
    @Override
    public boolean[][] read(File file) throws IOException {
        List<String> lines = Files.readAllLines(file.toPath());
        if (lines.size() < 3) {
            throw new IllegalArgumentException("Invalid markdown file format: Too few lines");
        }

        lines.remove(0);
        lines.remove(0);

        int rows = lines.size();
        int cols;


        // zwei Schreibweisen möglich, siehe hier https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet
        String firstDataRow = lines.get(0).trim();
        if (firstDataRow.startsWith("|")) {
            cols = firstDataRow.split("\\|").length - 1;
        } else {
            cols = firstDataRow.split("\\s*\\|\\s*").length;
        }
        boolean[][] data = new boolean[rows][cols];
        int rowIndex = 0;

        for (String line : lines) {
            if (!line.trim().startsWith("|") && !line.trim().endsWith("|")) {
                line = "|" + line + "|";
            }
            String[] row = line.split("\\|");
            if (row.length != cols + 1) {
                throw new IllegalArgumentException("Invalid markdown file format: Column mismatch in line " + (rowIndex + 3));
            }
            for (int colIndex = 1; colIndex <= cols; colIndex++) {
                if (!row[colIndex].trim().isEmpty()) {
                    String zellenInhalt = row[colIndex].trim();
                    if (!zellenInhalt.equals("1") && !zellenInhalt.equals("0")) {
                        throw new IllegalArgumentException("Ungültiger Inhalt in Zeile " + (rowIndex + 3) +
                                ", Spalte " + colIndex + ": Erwartet wurde 1 oder 0");
                    }
                    data[rowIndex][colIndex - 1] = zellenInhalt.equals("1");
                }
            }
            rowIndex++;
        }
        return data;
    }
}

