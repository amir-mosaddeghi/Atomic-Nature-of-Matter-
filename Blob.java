package org.example;

public class Blob {
     int mass;
     double x_sum;
     double y_sum;

    public Blob() {
        mass = 0;
        x_sum = 0;
        y_sum = 0;
    }

    public void add(int x, int y) {
        mass = mass + 1;
        x_sum = x_sum + x;
        y_sum = y_sum + y;
    }

    public int mass() {
        return mass;
    }

    public double distanceTo(Blob that) {
        double xCenter = x_sum / mass;
        double yCenter = y_sum / mass;

        double xCenter2 = that.x_sum / that.mass();
        double yCenter2 = that.y_sum / that.mass();

        double distance = Math.sqrt((xCenter2 - xCenter) * (xCenter2 - xCenter) + (yCenter2 - yCenter) * (yCenter2 - yCenter));

        return distance;
    }

    public String toString() {
        double xCenter = x_sum / mass;
        double yCenter = y_sum / mass;
        String text = String.format("%2d (%8.4f, %8.4f)", mass, xCenter, yCenter);
        return text;
    }

    public static void main(String[] args) {
        Blob point = new Blob();

        //point.add(2, 2);
        //point.add(4, 4);

        //StdOut.println(point.toString());
    }
}
