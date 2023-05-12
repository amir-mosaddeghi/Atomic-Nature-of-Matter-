package org.example;

import java.awt.Color;
import java.util.Arrays;

public class BeadFinder {


    private int blobcount;
    private int beadcount;
    private Blob[] collection;

    private boolean[][] visited;


    //  finds all blobs in the specified picture using luminance threshold tau
    public BeadFinder(Picture picture, double tau) {
        blobcount = 0;

        collection = new Blob[480 * 640 / 2];

        visited = new boolean[480][640];

        for (int row = 0; row < picture.height(); row++)
            for (int col = 0; col < picture.width(); col++) {
                visited[row][col] = false;
            }


        for (int row = 0; row < picture.height(); row++)
            for (int col = 0; col < picture.width(); col++) {

                Color color = picture.get(col, row);

                double lum = Luminance.intensity(color);

                if (lum > tau && !visited[row][col]) {
                    Blob found = new Blob();

                    collection[blobcount] = (Finding(col, row, tau, picture, found));

                    blobcount += 1;
                }
            }
    }

    private Blob Finding(int col, int row, double tau, Picture picture, Blob find) {

        if (col < 640 && col >= 0 && row < 480 && row >= 0) {
            Color color = picture.get(col, row);

            double lum = Luminance.intensity(color);

            if (lum >= tau && !visited[row][col]) {

                visited[row][col] = true;

                find.add(col, row);

                Finding(col, row + 1, tau, picture, find);

                Finding(col + 1, row, tau, picture, find);

                Finding(col, row - 1, tau, picture, find);

                Finding(col - 1, row, tau, picture, find);
            }
        }
        return find;
    }

    //  returns all beads (blobs with >= min pixels)
    public Blob[] getBeads(int min) {
        beadcount = 0;
        Blob[] beads = new Blob[1];


        for (int i = 0; i < blobcount; i++) {

            if (collection[i].mass() >= 25) {
                beads = Arrays.copyOf(beads, beads.length + 1);
                beads[beads.length - 1] = collection[i];
                beadcount = beadcount + 1;
            }
        }

        return beads;
    }

    //  test client, as described below
    public static void main(String[] args) {
        int min = Integer.parseInt(args[0]);
        double tau = Float.parseFloat(args[1]);
        Picture name = new Picture(args[2]);

        BeadFinder aka = new BeadFinder(name, tau);

        Blob[] myBeads = aka.getBeads(min);

        for (int i = 1; i < aka.beadcount + 1; i++) {
            StdOut.println(myBeads[i]);
        }

    }
}
