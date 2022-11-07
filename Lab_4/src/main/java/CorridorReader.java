package Lab_4.src.main.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CorridorReader {
    public static char[][] getTiles(String path) throws IOException {
        int rows;
        int columns;

        BufferedReader reader = new BufferedReader(new FileReader(path));
        String header = reader.readLine();
        columns = Integer.parseInt(header.split(" ")[0]);
        rows = Integer.parseInt(header.split(" ")[1]);
        if (columns < 1) {
            throw new IllegalStateException("Corridor width should be higher than 0");
        }
        if (rows > 2000) {
            throw new IllegalStateException("Corridor height should be lower than 2001");
        }
        char[][] tiles = new char[rows][columns];

        for (int i = 0; i < rows; ++i) {
            tiles[i] = reader.readLine().toCharArray();
        }

        return tiles;
    }

}
