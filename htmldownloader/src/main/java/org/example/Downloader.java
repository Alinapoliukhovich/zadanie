package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Downloader {
    public static void downloadFile(String fileUrl, String targetDir) {
        try {
            URL url = new URL(fileUrl);
            Path destinationPath = Paths.get(targetDir, fileUrl.substring(fileUrl.lastIndexOf("/") + 1));
            Files.copy(url.openStream(), destinationPath);
            System.out.println("Файл " + fileUrl + " успешно загружен.");

            String titleLine = Files.lines(destinationPath)
                    .filter(line -> line.contains("<title>"))
                    .findFirst()
                    .orElse("");
            System.out.println("Строка с <title>: " + titleLine);
        } catch (IOException e) {
            System.err.println("Ошибка загрузки файла " + fileUrl + ": " + e.getMessage());
        }
    }

    public static String[] getUserInput() {
        try (BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.print("Введите ссылки на файлы: ");
            String urls = inputReader.readLine();
            System.out.print("Введите директорию для скачивания: ");
            String directory = inputReader.readLine();
            return new String[] { urls, directory };
        } catch (IOException e) {
            System.err.println("Ошибка ввода: " + e.getMessage());
            return new String[] { "", "" };
        }
    }

}
