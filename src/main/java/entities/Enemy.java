package entities;

import world.Menu;
import javafx.scene.image.Image;
import world.Position;

import java.util.Iterator;

public class Enemy extends Movable {
    public static Image[] views = new Image[103];

    int health, damage, reward;

    double progress = 0;

    Iterator<Position> path;

    Menu menu;

    public Enemy(Iterator<Position> path, double speed, Menu menu) {
        this.path = path;
        position = path.next();
        target = path.next();

        this.speed = speed;

        this.health = 100;
        this.damage = 10;
        this.reward = 20;

        direction = calculateDirection();

        size = new Position(20, 20);

        this.menu = menu;

        setView(views[(int) (Math.random() * views.length)]);

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

    public static void loadViews() {
        for (int i = 1; i < 103; i++) {
            views[i] = new Image("enemies/" + i + ".png");
        }
    }
}
