package engine;

import javafx.application.Platform;
import world.Position;

public abstract class Movable extends Element {
    protected int speed;

    protected Position target;
    protected Position direction;

    public boolean move() {
        if (target.subtract(position).length() < speed) {
            return false;
//            moved();
        }
        position = position.add(direction);
        Platform.runLater(() -> view.relocate(position.x, position.y));
        return true;
    }

    public Position calculateDirection() {
        return target.subtract(position).normalize(speed);
    }

//    protected void moved() {}
}
