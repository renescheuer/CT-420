package CSV;

import Interface.FileReader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class CSVFileReader implements FileReader {
    @Override
    public boolean[][] read(File file) throws IOException {
        List<String> lines = Files.readAllLines(file.toPath());
        if (lines.isEmpty()) {
            throw new IllegalArgumentException("Invalid CSV file format: No data found");
        }
        boolean hasHeader = !lines.get(0).matches("^[0-1,\\s]+$");

        if (hasHeader){
            lines.remove(0);
        }

        int rows = lines.size();
        int cols = lines.get(0).split(",").length;
        boolean[][] data = new boolean[rows][cols];
        for (int rowIndex = 0; rowIndex < rows; rowIndex++) {
            String[] row = lines.get(rowIndex).split(",");
            if (row.length != cols) {
                throw new IllegalArgumentException("Invalid CSV file format: Column mismatch in line " + (rowIndex + 1));
            }
            for (int colIndex = 0; colIndex < cols; colIndex++) {
                String cellContent = row[colIndex].trim();
                if (!cellContent.equals("1") && !cellContent.equals("0")) {
                    throw new IllegalArgumentException("Invalid content in line " + (rowIndex + 1) +
                            ", column " + (colIndex + 1) + ": Expected 1 or 0");
                }
                data[rowIndex][colIndex] = cellContent.equals("1");
            }
        }
        return data;
    }
}
