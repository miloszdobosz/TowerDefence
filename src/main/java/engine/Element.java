package engine;

import world.Position;

public abstract class Element {
    protected Position position;
    protected Position size;

    public Position getPosition() {
        return position;
    }
    public Position getSize() {return size;}
    public Position getCenter() {
        return position.add(size.times(0.5));
    }
}
