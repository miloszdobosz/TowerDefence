package entities;

import engine.Element;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import world.Position;
import world.World;

import java.util.ArrayList;
import java.util.Optional;

public class Tower extends Element {
    int price;
    int damage;
    int range;
    int period;

    World world;

    Entities<Bullet> bullets = new Entities<>(new ArrayList<>());

    public Tower(World world, Position position) {
        this.world = world;
        this.position = position;
        this.range = 200;
        this.damage = 15;
        this.period = 500;
        this.price = 100;

        this.size = new Position(50, 50);

        view = new Rectangle(position.x, position.y, getSize().x, getSize().y);
        view.setFill(Color.rgb(0, 100, 255));

        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(period);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (world) {
                    attack();
                }
            }
        });
        thread.start();

        addView();
    }

    public void attack() {
        Optional<Enemy> enemy = world.getTarget(this);
        if (enemy.isPresent()) {
            bullets.add(new Bullet(getCenter(), enemy.get().getCenter()));
            enemy.get().bleed(damage);
        }
    }

    public void update() {
        bullets.update();
    }

    public boolean inRange(Enemy enemy) {
        return position.distanceTo(enemy.getPosition()) <= range;
    }

    public int getPrice() {
        return price;
    }
}
