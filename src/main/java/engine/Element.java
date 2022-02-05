package engine;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import world.Position;

public class Element {
    protected Position position = new Position(50, 50);

    public Shape view() {
        Rectangle view = new Rectangle(position.x, position.y, 50, 50);
        return view;
    }
}
