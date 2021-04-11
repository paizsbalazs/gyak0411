package hu.nive.ujratervezes.kepesitovizsga;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class UpperCaseLetters {

    public int getNumberOfUpperCase(String filename) {

        Path path = Path.of("C:/gyak0411/" + filename);

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            return new UpperCaseLetters().readLines(reader);
        } catch (IOException ioe) {
            throw new IllegalStateException("Can not read file", ioe);
        }
    }

    public int readLines(BufferedReader reader) throws IOException {
        int result = 0;
        String line;

        while ((line = reader.readLine())  != null) {
            System.out.println(line);
          for (Character c: line.toCharArray()) {
              if (Character.isUpperCase(c)) {
                 result = result + 1;
              }
          }
        }
        return result;
    }
}
