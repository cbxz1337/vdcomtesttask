package org.yakunyashin;

import org.yakunyashin.graph.services.GraphParseService;
import org.yakunyashin.graph.services.RegexpGraphParseService;

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
                GraphParseService<Double> graphParser = new RegexpGraphParseService(input);
                GraphSolveService solver = new GraphSolveService(graphParser.getParsedGraph());
                System.out.println(solver.getSolution());
                flag = false;
            } else {
                input.add(line);
            }
        }
    }
}