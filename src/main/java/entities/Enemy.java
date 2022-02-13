package entities;

import engine.Menu;
import engine.Movable;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import world.Position;

import java.util.Iterator;

public class Enemy extends Movable {
    int health, damage, reward;

    double progress = 0;

    Iterator<Position> path;

    Menu menu;

    public Enemy(Iterator<Position> path, int speed, Menu menu) {
        this.path = path;
        position = path.next();
        target = path.next();

        this.speed = speed;
        direction = calculateDirection();


        this.health = 100;
        this.damage = 20;
        this.reward = 20;

        size = new Position(20, 20);

        this.menu = menu;

        view = new Rectangle(position.x, position.y, size.x, size.y);
        view.setFill(Color.rgb(255, 0, 100));

        addView();
    }

    public void update() {
        progress += speed;

        if (!move()) {
            if (path.hasNext()) {
                position = target;
                target = path.next();
                direction = calculateDirection();
            }
            else {
                menu.bleed(damage);
                kill();
                return;
            }
        }
    }

    public void bleed(int damage) {
        health -= damage;

        if (health <= 0) {
            kill();
        }
    }

    private void kill() {
        menu.pay(-reward);
        delete();
    }

    public double getProgress() {
        return progress;
    }

}
