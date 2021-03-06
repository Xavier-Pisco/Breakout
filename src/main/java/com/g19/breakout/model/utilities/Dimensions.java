package com.g19.breakout.model.utilities;

import java.util.Objects;

public class Dimensions {
    protected double x, y;

    public Dimensions(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getDiscreteX() {
        return (int) Math.round(x);
    }

    public int getDiscreteY() {
        return (int) Math.round(y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dimensions dimensions = (Dimensions) o;
        return Double.compare(dimensions.x, x) == 0 &&
                Double.compare(dimensions.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
