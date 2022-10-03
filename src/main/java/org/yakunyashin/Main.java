package org.yakunyashin;

import org.yakunyashin.ratiograph.service.RatioGraphParseService;
import org.yakunyashin.ratiograph.service.RegexpGraphParseService;
import org.yakunyashin.solve.DoubleValuesGraphSolveService;
import org.yakunyashin.solve.GraphSolveService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner SCANNER = new Scanner( System.in );
    private static final String START_CALCULATION_COMMAND = "calculate";

    private static final String INFO_MSG = """
            Insert known ratios as a V = b W where V and W is value names
            Insert unknown rations as a V = ? W
            Type "calculate" to calculate unknown ratios""";

    public static void main(String[] args) {
        List<String> input = new ArrayList<>();
        boolean flag = true;
        System.out.println(INFO_MSG);
        while (flag) {
            String line = SCANNER.nextLine();
            if (line.equalsIgnoreCase(START_CALCULATION_COMMAND)) {
                SCANNER.close();
                RatioGraphParseService<Double> graphParser = new RegexpGraphParseService(input);
                GraphSolveService solver = new DoubleValuesGraphSolveService(graphParser.getParsedGraph());
                System.out.println(solver.getSolution());
                flag = false;
            } else {
                input.add(line);
            }
        }
    }
}