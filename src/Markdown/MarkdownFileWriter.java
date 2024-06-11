package Markdown;

import Interface.FileWriter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class MarkdownFileWriter implements FileWriter {
    @Override
    public void write(File file, boolean[][] data) throws IOException {
        if (data.length < 1) {
            Files.write(file.toPath(), new ArrayList<>(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            return;
        }

        List<String> lines = new ArrayList<>();

        StringBuilder header = new StringBuilder();
        for (int i = 0; i < data[0].length; i++) {
            header.append(" A").append(i + 1);
            if (i < data[0].length - 1) {
                header.append(" |");
            }
        }
        lines.add(header.toString());

        StringBuilder delimiter = new StringBuilder();
        for (int i = 0; i < data[0].length; i++) {
            delimiter.append("---");
            if (i < data[0].length - 1) {
                delimiter.append("|");
            }
        }
        lines.add(delimiter.toString());

        for (boolean[] row : data) {
            StringBuilder line = new StringBuilder();
            for (int i = 0; i < row.length; i++) {
                line.append(" ").append(row[i] ? "1" : "0");
                if (i < row.length - 1) {
                    line.append(" |");
                }
            }
            lines.add(line.toString());
        }

        Files.write(file.toPath(), lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }
}