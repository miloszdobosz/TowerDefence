package world;

public enum Direction {
    N, E, S, W;

    public Position toPositionChange(int size) {
        return switch (this) {
            case N -> new Position(0, -size);
            case E -> new Position(size, 0);
            case S -> new Position(0, size);
            case W -> new Position(-size, 0);
        };
    }

    public Direction add(Direction other) {
        return this.values()[(this.ordinal() + other.ordinal()) % 4];
    }
}
