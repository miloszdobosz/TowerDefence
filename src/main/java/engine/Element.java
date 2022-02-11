package engine;

import javafx.scene.Group;
import javafx.scene.Node;
import world.Position;

public abstract class Element {
    protected Position position;

    public Position getPosition() {
        return position;
    }
}
