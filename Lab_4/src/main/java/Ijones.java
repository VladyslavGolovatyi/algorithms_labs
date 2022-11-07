package Lab_4.src.main.java;

import java.util.ArrayList;

public class Ijones {

    public static int getNumberOfPaths(char[][] tiles) {
        int rows = tiles.length;
        int columns = tiles[0].length;
        int result = 0;

        if (rows > 1) {
            result += getNumberOfPathsToTarget(0, columns - 1, tiles);
        }
        result += getNumberOfPathsToTarget(rows - 1, columns - 1, tiles);
        return result;
    }

    private static int getNumberOfPathsToTarget(int targetRowPos, int targetColPos, char[][] tiles) {
        if (targetColPos == 0) {
            return 1;
        } else {
            int result = 0;
            ArrayList<Tile> tilesForJump = getPossibleTilesToJumpFrom(targetRowPos, targetColPos, tiles);

            for (Tile tile : tilesForJump) {
                result += getNumberOfPathsToTarget(tile.rowPos, tile.colPos, tiles);
            }

            if (tiles[targetRowPos][targetColPos] != tiles[targetRowPos][targetColPos - 1]) {
                result += getNumberOfPathsToTarget(targetRowPos, targetColPos - 1, tiles);
            }

            return result;
        }

    }

    private static ArrayList<Tile> getPossibleTilesToJumpFrom(int targetRowPos, int targetColPos, char[][] tiles) {
        ArrayList<Tile> resultList = new ArrayList<>();
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < targetColPos; j++) {
                if (tiles[i][j] == tiles[targetRowPos][targetColPos]) {
                    resultList.add(new Tile(i,j));
                }
            }
        }

        return resultList;
    }

}

class Tile {
    int rowPos;
    int colPos;

    public Tile(int rowPos, int colPos) {
        this.rowPos = rowPos;
        this.colPos = colPos;
    }

}
