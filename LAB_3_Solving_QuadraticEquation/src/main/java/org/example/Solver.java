package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solver
{
    private final double[][] coefficients;
    private double[] results;
    private boolean isNext;

    public Solver(double[][] coefficients)
    {
        this.coefficients = coefficients;
    }

    private double[] getResults()
    {
        for (double[] el: coefficients)
        {
            //todo solving all lines
        }

        return null;
    }

    private List<Double> Solve(double[] cof)
    {
        // ax^2 = 0
        if(cof[2] == 0 && cof[1] == 0)
        {
            return Arrays.asList(0.0, null);
        }

        // bx + c = 0
        if(cof[0] == 0 && cof[1] != 0)
        {
            double res = (-cof[2]/cof[1]);
            return Arrays.asList(0.0, null);
        }

        // c = 0
        if(cof[0] == 0 && cof[1] == 0)
        {
            return Arrays.asList(null, null);
        }

        return Arrays.asList(
                (-cof[1] + Math.sqrt(cof[1] - 4 * cof[0] * cof[2])) / (2 * cof[0]),
                (-cof[1] - Math.sqrt(cof[1] - 4 * cof[0] * cof[2])) / (2 * cof[0]));
    }
}
