package Interface;

import java.io.File;
import java.io.IOException;

public interface FileReader {
    boolean[][] read(File file) throws IOException;
}
