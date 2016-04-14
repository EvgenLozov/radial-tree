package com.example;

import java.util.List;
import java.util.Optional;

/**
 * @author Yevhen
 */
public class VectorAlgebra {

    public static Vector create(double radius, double angle)
    {
        double x = radius * Math.cos(angle);
        double y = radius * Math.sin(angle);

        return new Vector(x, y);
    }

    public static double tangentAngle(double inRadius, double outRadius)
    {
        return 2 * Math.acos(inRadius/outRadius);
    }

    public static Vector rotateVector(Vector vector, double angle)
    {
        double x = vector.getX() * Math.cos(angle) - vector.getY() * Math.sin(angle);
        double y = vector.getX() * Math.sin(angle) + vector.getY() * Math.cos(angle);

        return new Vector(x, y);
    }

    public static Vector multiply(Vector vector, double scalar)
    {
        return new Vector(vector.getX() * scalar, vector.getY() * scalar);
    }

    public static double scalarProd(Vector first, Vector second)
    {
        return first.getX() * second.getX() + first.getY() * second.getY();
    }

    public static double vectorProd(Vector first, Vector second)
    {
        return first.getX() * second.getY() - first.getY() * second.getX();
    }

    public static double angle(Vector first, Vector second)
    {
        double vectorProd = vectorProd(first, second);
        double scalarProd = scalarProd(first, second);

        if (vectorProd == 0 && scalarProd == -1)
            return Math.PI;

        double angle = Math.acos(scalarProd / length(first) / length(second)) * Math.signum(vectorProd);

        return angle >= 0 ? angle : Math.PI * 2 + angle;
    }

    public static double length(Vector vector){
        return Math.sqrt(scalarProd(vector, vector));
    }

    public static Optional<Vector> getNeighbor(Vector vector, List<Vector> candidates, boolean direction)
    {
        double minAngle = Double.MAX_VALUE;
        Optional<Vector> neighbor = Optional.empty();

        for (Vector candidate : candidates) {
            double angle = angle(vector, candidate);
            angle = direction ? angle : 2 * Math.PI - angle;

            if (angle > 0 && angle < minAngle) {
                minAngle = angle;
                neighbor = Optional.of(candidate);
            }
        }

        return neighbor;
    }

    public static Optional<Double> getNeighborAngle(Vector vector, List<Vector> candidates, boolean direction)
    {
        double minAngle = Double.MAX_VALUE;
        boolean found = false;

        for (Vector candidate : candidates) {
            double angle = angle(vector, candidate);
            angle = direction ? angle : 2 * Math.PI - angle;

            if (angle > 0 && angle < minAngle) {
                minAngle = angle;
                found = true;
            }
        }

        return found ? Optional.of(minAngle) : Optional.<Double>empty();
    }
}
