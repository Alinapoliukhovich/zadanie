package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.example.Downloader.*;

public class Main {
    public static void main(String[] args) {
        String[] input = getUserInput();
        String urlsString = input[0];
        String directoryString = input[1];

        List<String> urls = Arrays.asList(urlsString.split(";"));

        ExecutorService executor = Executors.newFixedThreadPool(urls.size());

        urls.forEach(url -> executor.execute(() -> downloadFile(url, directoryString)));

        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }

        System.out.println("\nЗагрузка завершена.");
    }
}