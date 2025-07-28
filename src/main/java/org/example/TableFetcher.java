package org.example;


import com.google.api.services.docs.v1.Docs;
import com.google.api.services.docs.v1.model.*;

import java.util.ArrayList;
import java.util.List;

public class TableFetcher {
    public static List<TableEntry> fetchTable(Docs service, String documentId) throws Exception {
        Document doc = service.documents().get(documentId).execute();
        List<TableEntry> entries = new ArrayList<>();

        for(StructuralElement element : doc.getBody().getContent()) {
            if(element.getTable() != null) {
                Table table = element.getTable();
                List<TableRow> rows = table.getTableRows();

                for (int i = 1; i < rows.size(); i++) {
                    TableRow row = rows.get(i);
                    List<TableCell> cells = row.getTableCells();
                    if (cells.size() >= 3) {
                        int x = Integer.parseInt(getText(cells.get(0)));
                        String character = getText(cells.get(1));
                        int y = Integer.parseInt(getText(cells.get(2)));
                        entries.add(new TableEntry(x, y, character));
                    }
                }
            }
        }
        return entries;
    }
    private static String getText(TableCell cell) {
        StringBuilder sb = new StringBuilder();
        for (StructuralElement se : cell.getContent()) {
            if (se.getParagraph() != null) {
                for (ParagraphElement e : se.getParagraph().getElements()) {
                    if (e.getTextRun() != null && e.getTextRun().getContent() != null) {
                        sb.append(e.getTextRun().getContent());
                    }
                }
            }
        }
        return sb.toString().trim();
    }
}
