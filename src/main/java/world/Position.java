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

    public Position upperRight(Position other) {
        double x = Math.max(this.x, other.x);
        double y = Math.max(this.y, other.y);

        return new Position(x, y);
    }

    public Position lowerLeft(Position other) {
        double x = Math.min(this.x, other.x);
        double y = Math.min(this.y, other.y);

        return new Position(x, y);
    }

    public Position add(Position other) {
        return new Position(this.x + other.x, this.y + other.y);
    }

    public Position subtract(Position other) {
        return new Position(this.x - other.x, this.y - other.y);
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