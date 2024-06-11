package Interface;

import java.io.File;
import java.io.IOException;

public interface FileWriter {
    void write(File file, boolean[][] data) throws IOException;
}
