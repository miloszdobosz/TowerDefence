package entities;

import engine.Movable;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import world.Position;

public class Bullet extends Movable {

    public Bullet(Position source, Position target) {
        this.position = source;
        this.target = target;
        this.speed = 30;
        this.direction = calculateDirection();

        view = new Circle(source.x, source.y, 4);
        view.setFill( Color.rgb(100, 100, 100));

        addView();
    }

    public void update() {
        if (!move()) {
            delete();
        }
    }
}
