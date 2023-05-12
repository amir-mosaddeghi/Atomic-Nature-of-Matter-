package org.example;

public class BeadTracker {
    public static void main(String[] args) {
        int min = Integer.parseInt(args[0]);
        double tau = Double.parseDouble(args[1]);
        double delta = Double.parseDouble(args[2]);

        // Cycles through different pictures
        for (int i = 3; i < 202; i++) {

            BeadFinder again = new BeadFinder(new Picture(args[i+1]), tau);
            Blob[] repeat = again.getBeads(min);

            BeadFinder second = new BeadFinder(new Picture(args[i]), tau);
            Blob[] recur = second.getBeads(min);

            double complete = 0;

            // Goes through each bead in later frame
            for (int j = 1; j < repeat.length; j++) {
                // Goes through each bead in previous frame
                complete = delta + 1;

                for (int k = 1; k < recur.length; k++) {

                    double distance = repeat[j].distanceTo(recur[k]);
                    if (distance < complete) {
                        complete = distance;
                    }
                }

                if (complete <= delta) {
                    StdOut.println(String.format("%.4f", complete));
                }
            }
        }
    }
}