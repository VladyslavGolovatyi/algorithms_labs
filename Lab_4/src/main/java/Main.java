package Lab_4.src.main.java;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        char [][] tiles = CorridorReader.getTiles("src/main/Lab_5.resources/ijones.in1.txt");
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("ijones.out.txt"))){
            writer.write(String.valueOf(Ijones.getNumberOfPaths(tiles)));
        }
    }
}
