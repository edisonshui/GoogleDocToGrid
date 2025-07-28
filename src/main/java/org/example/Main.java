package org.example;

import com.google.api.services.docs.v1.Docs;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        if(args.length != 1) {
            System.out.println("Usage: java Main <Google_Doc_URL>");
            return;
        }

        String inputURL = args[0];
        String documentId = extractDocIdFromUrl(inputURL);

        try {
            Docs service = GoogleDocService.getDocService();

            List<TableEntry> entries = TableFetcher.fetchTable(service, documentId);

            GridPrinter.printGrid(entries);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String extractDocIdFromUrl(String url) {
        String[] parts = url.split("/d/");
        if(parts.length < 2) throw new IllegalArgumentException("Invalid URL");
        return parts[1].split("/")[0];
    }
}