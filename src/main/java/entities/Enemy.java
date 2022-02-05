package entities;

import engine.Element;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import world.Direction;
import world.Map;
import world.Position;

public class Enemy extends Element {
    int damage;
    int health;
    int speed;
    int reward;

    Map map;
    Direction direction;

    Enemy(Map map, Position position) {
        this.map = map;
        this.position = position;
    }

    public void move() {
        position = position.add(direction.toPositionChange(speed));
    }

    public void bleed(int damage) {
        health -= damage;

        if (health <= 0) {
            kill();
        }
    }

    public void kill() {
        map.removeEnemy(this);
    }

    @Override
    public Shape view() {
        Shape shape = super.view();
        shape.setFill(Color.rgb(100, 200, 0));
        return shape;
    }
}
