import org.example.Downloader;
import org.junit.jupiter.api.*;
import java.nio.file.*;

import static org.junit.jupiter.api.Assertions.*;

public class Tests {

    @Test
    void testDownloadFile() throws Exception {
        String fileUrl = "http://example.com/example.txt";
        String targetDir = "/path/to/download";

        Downloader.downloadFile(fileUrl, targetDir);

        Path destinationPath = Paths.get(targetDir, fileUrl.substring(fileUrl.lastIndexOf("/") + 1));

        assertTrue(Files.exists(destinationPath));

        String expectedContent = "Это тестовый контент";
        String actualContent = new String(Files.readAllBytes(destinationPath));
        assertEquals(expectedContent, actualContent);

        String titleLine = Files.lines(destinationPath)
                .filter(line -> line.contains("<title>"))
                .findFirst()
                .orElse("");
        assertNotNull(titleLine);
    }


    @Test
    void testGetUserInput() {
        String expectedUrls = "http://example.com/file1.txt http://example.com/file2.txt";
        String expectedDirectory = "/path/to/download";

        String[] result = Downloader.getUserInput();

        assertEquals(expectedUrls, result[0]);
        assertEquals(expectedDirectory, result[1]);
    }
}

