package entities;

import engine.App;
import engine.Element;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import world.Direction;
import world.Path;
import world.World;
import world.Position;

public class Enemy extends Element {
    int damage;
    int health;
    int speed;
    int reward;

    int pathSegment = 0;
    int distanceToNext;

    int progress = 0;

    World world;
    Path path;

    Rectangle view;

    Direction direction;

    public Enemy(World world, Path path, int speed, Group parentView) {
        this.world = world;
        this.path = path;
        updateSegment();
        this.speed = speed;
        this.health = 100;

        view = new Rectangle(position.x, position.y, 20, 20);
        view.setFill(Color.rgb(255, 0, 100));
        parentView.getChildren().add(view);
    }

    public void move() {
        position = position.add(direction.toPositionChange(speed));
        distanceToNext -= speed;
        progress += speed;
        if (distanceToNext <= 0) {
            pathSegment++;
            updateSegment();
        }

        view.setX(position.x);
        view.setX(position.y);
    }

    private void updateSegment() {
        position = path.getPosition(pathSegment);
        direction = path.getDirection(pathSegment);
        distanceToNext = path.getDistance(pathSegment);

        if (distanceToNext == 0) {
            world.kill(this);
        }
    }

    public void bleed(int damage) {
        health -= damage;

        if (health <= 0) {
            world.kill(this);
        }
    }

    public int getProgress() {
        return progress;
    }
}
