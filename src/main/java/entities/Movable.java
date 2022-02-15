package engine;

import javafx.application.Platform;
import world.Position;

public abstract class Movable extends Element {
    protected double speed;

    protected Position target;
    protected Position direction;

    public boolean move() {
        if (target.subtract(position).length() < speed) {
            // Koniec ruchu
            return false;
        }
        // Rusz obiekt
        position = position.add(direction);
        // rusz obraz
        Platform.runLater(() -> view.relocate(position.x, position.y));
        return true;
    }

    public Position calculateDirection() {
        return target.subtract(position).normalize(speed);
    }
}
