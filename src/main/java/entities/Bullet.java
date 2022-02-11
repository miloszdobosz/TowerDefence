package entities;

import engine.Element;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import world.Position;

public class Bullet extends Element {
    Position source, target;

    int progress = 0;

    Circle view;

    public Bullet(Position source, Position target, Group parentView) {
        this.source = source;
        this.target = target;

        view = new Circle(source.x, source.y, 4, Color.rgb(100, 100, 100));
    }

//    @Override
//    public void view(Group parent) {
//        progress += 10;
//        Position position = target.times(progress).add(source);
//        parent.getChildren().add(new Circle(position.x, position.y, 4, Color.rgb(100, 100, 100)));
//    }
}
