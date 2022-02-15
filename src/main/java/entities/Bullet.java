package entities;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Bullet extends Movable {
    Enemy enemy;

    int damage;

    public Bullet(Tower tower, Enemy enemy, int damage) {
        this.position = tower.getCenter();
        this.target = enemy.getCenter();
        this.speed = 30;
        this.direction = calculateDirection();

        this.enemy = enemy;

        this.damage = damage;

        view = new Circle(position.x, position.y, 5, Color.rgb(150, 0, 0));
        addView();
    }

    public void update() {
        if (!move()) {
            enemy.bleed(damage);
            delete();
        }
    }
}
