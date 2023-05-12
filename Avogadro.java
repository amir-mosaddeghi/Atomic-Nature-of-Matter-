package org.example;

public class Avogadro {
    public static void main(String[] args) {

        double sum = 0;

        String[] input = StdIn.readAllStrings();

        for (int i = 0; i < input.length; i++) {
            sum = sum + (Double.parseDouble(input[i]) * Double.parseDouble(input[i]) * 0.175 * Math
                    .pow(10, -6) * 0.175 * Math.pow(10, -6));
        }
        
        double diffusion = sum / (2 * input.length);

        double temp = 297;
        double eta = 9.135 * Math.pow(10, -4);
        double rho = 0.5 * Math.pow(10, -6);

        double k = (diffusion * 6 * Math.PI * eta * rho) / temp;

        StdOut.println("Boltzmann = " + String.format("%6.4e", k));

        double gas = 8.31446;

        double avogadro = gas / k;

        StdOut.println("Avogadro = " + String.format("%6.4e", avogadro));
    }
}
