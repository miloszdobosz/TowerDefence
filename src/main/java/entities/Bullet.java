package entities;

import engine.Element;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import world.Position;

public class Bullet extends Element {
    Position source, target;

    double progress = 0;
    int period = 50;

    Circle view;
    Group parentView;

    public Bullet(Position source, Position target, Group parentView) {
        this.source = source;
        this.target = target;

        view = new Circle(source.x, source.y, 4);
        view.setFill( Color.rgb(100, 100, 100));

        this.parentView = parentView;

        Platform.runLater(() -> parentView.getChildren().add(view));
    }

    public void update() {
        if (progress >= 1) {
            Platform.runLater(() -> parentView.getChildren().remove(view));
            return;
        }
        progress += 0.05;
        Position position = target.times(progress).add(source.times(1 - progress));
//        System.out.println(position);

//        Position position = source.add(new Position(progress, progress));

        Platform.runLater(() -> view.relocate(position.x, position.y));
    }
}
