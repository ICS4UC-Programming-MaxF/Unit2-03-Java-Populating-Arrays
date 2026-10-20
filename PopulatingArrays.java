/*
 * Created by: MF-ROB
 * Created on: Oct 20th 2026
 * This program reads a file (input.txt) with integers on each line,
 * calculates the sum of each line using a loop, and writes the sums
 * to output.txt. If a line is empty or contains something that is not
 * an integer, an error message is written to output.txt instead.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
            PrintWriter writer = new PrintWriter(new FileWriter("output.txt"))) {

            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                line = line.trim();

                // A blank line has no integers, so treat it as an error
                if (line.isEmpty()) {
                    writer.println("Line " + lineNumber + ": Error - empty line");
                    continue;
                }

                String[] parts = line.split("\\s+");
                int sum = 0;
                String badValue = null;

                for (String part : parts) {
                    try {
                        sum += Integer.parseInt(part);
                    } catch (NumberFormatException e) {
                        badValue = part;
                        break;
                    }
                }

                if (badValue != null) {
                    writer.println("Line " + lineNumber + ": Error - \"" + badValue + "\" is not an integer");
                } else {
                    writer.println("Line " + lineNumber + ": Sum = " + sum);
                }
            }
        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        }
    }
}