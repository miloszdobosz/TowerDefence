package entities;

import engine.Element;
import engine.Menu;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import world.Direction;
import world.Path;
import world.Position;
import world.World;

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

    Menu menu;

    Rectangle view;
    Group parentView;

    Direction direction;

    public Enemy(World world, Path path, int speed, Group parentView, Menu menu) {
        this.world = world;
        this.path = path;
        updateSegment();
        this.speed = speed;
        this.health = 100;
        this.damage = 20;

        position = path.getPosition(0);
        size = new Position(20, 20);
        this.parentView = parentView;

        this.menu = menu;

        view = new Rectangle(position.x, position.y, size.x, size.y);
        view.setFill(Color.rgb(255, 0, 100));

        Platform.runLater(() -> parentView.getChildren().add(view));
    }

    public void update() {
        position = position.add(direction.toPositionChange(speed));
        distanceToNext -= speed;
        progress += speed;
        if (distanceToNext <= 0) {
            pathSegment++;
            updateSegment();
        }

        Platform.runLater(() -> view.relocate(position.x, position.y));
    }

    private void updateSegment() {
        position = path.getPosition(pathSegment);
        direction = path.getDirection(pathSegment);
        distanceToNext = path.getDistance(pathSegment);

        if (distanceToNext == 0) {
            world.getMenu().bleed(damage);
            kill();
        }
    }

    public void bleed(int damage) {
        health -= damage;

        if (health <= 0) {
            kill();
        }
    }

    private void kill() {
        world.kill(this);
        Platform.runLater(() -> parentView.getChildren().remove(view));
    }

    public int getProgress() {
        return progress;
    }
}
