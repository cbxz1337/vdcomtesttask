package org.example;

import org.example.graphutils.RatioGraphParser;
import org.example.graphutils.RatioGroupValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner SCANNER = new Scanner( System.in );
    private static final String START_CALCULATION_COMMAND = "calculate";

    public static void main(String[] args) {
        List<String> input = new ArrayList<>();
        boolean flag = true;
        while (flag) {
            String line = SCANNER.nextLine();
            if (line.equalsIgnoreCase(START_CALCULATION_COMMAND)) {
                SCANNER.close();
                RatioGraphParser ratioGraphParser = new RatioGraphParser(input);
                Solver solver = new Solver(ratioGraphParser.getParsedGraph());
                System.out.println(solver.getSolution());
                flag = false;
            } else if (RatioGroupValidator.validInputString(line)) {
                input.add(line);
            } else {
                throw new IllegalArgumentException(String.format("Cannot validate line %s", line));
            }

        }
    }
}