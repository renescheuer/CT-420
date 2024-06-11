package CSV;

import Interface.FileWriter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class CSVFileWriter implements FileWriter {
    @Override
    public void write(File file, boolean[][] data) throws IOException {
        if (data.length < 1) {
            Files.write(file.toPath(), new ArrayList<>(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            return;
        }

        List<String> lines = new ArrayList<>();
        for (boolean[] row : data) {
            StringBuilder line = new StringBuilder();
            for (int colIndex = 0; colIndex < row.length; colIndex++) {
                line.append(row[colIndex] ? "1" : "0");
                if (colIndex < row.length - 1) {
                    line.append(",");
                }
            }
            lines.add(line.toString());
        }
        Files.write(file.toPath(), lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }
}
