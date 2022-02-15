package world;

import java.util.Objects;

public class Position implements Comparable<Position>{
    public final double x;
    public final double y;

    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }


    public String toString() {
        return "(" + x + "," + y + ")";
    }

    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (!(other instanceof Position that))
            return false;

        return this.x == that.x && this.y == that.y;
    }


    public boolean precedes(Position other) {
        return this.x <= other.x && this.y <= other.y;
    }

    public boolean follows(Position other) {
        return this.x >= other.x && this.y >= other.y;
    }

    public Position add(Position other) {
        return new Position(this.x + other.x, this.y + other.y);
    }

    public Position subtract(Position other) {
        return new Position(this.x - other.x, this.y - other.y);
    }

    public Position times(double scalar) {
        return new Position((int) (this.x * scalar), (int) (this.y * scalar));
    }

    public Position floor(double scalar) {
        return new Position((int) (x / scalar) * scalar, (int) (y / scalar) * scalar);
    }

    public Position normalize(double scalar) {
        double length = this.length();
        return new Position((x * scalar) / length, (y * scalar) / length);
    }

    public double distanceTo(Position other) {
        return this.subtract(other).length();
    }

    public double length() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }


    @Override
    public int hashCode() {
        return Objects.hash(this.x, this.y);
    }

    @Override
    public int compareTo(Position other) {
        if (this.precedes(other)) return -1;
        if (this.follows(other)) return 1;
        return 0;
    }
}