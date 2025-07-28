package org.example;

import java.util.List;

public class GridPrinter {
    public static void printGrid(List<TableEntry> entries) {
        // find max x and y
        int maxX = 0, maxY = 0;
        for(TableEntry e : entries) {
            if(e.x > maxX) maxX = e.x;
            if(e.y > maxY) maxY = e.y;
        }

        // initialize grid
        String[][] grid = new String[maxY + 1][maxX + 1];
        for(int y = 0; y <= maxY; y++) {
            for(int x = 0; x <= maxX; x++) {
                grid[y][x] = " ";
            }
        }

        // fill grid
        for (TableEntry e : entries) {
            String cleanChar = e.character.replaceAll("\\s+", "").trim();
            grid[e.y][e.x] = cleanChar;
        }

        // print grid
        for(int y = maxY; y >= 0; y--) {
            for(int x = 0; x <= maxX; x++) {
                System.out.print(grid[y][x]);
            }
            System.out.println();
        }
    }
}
