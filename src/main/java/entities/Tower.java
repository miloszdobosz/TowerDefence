package entities;

import engine.Element;
import javafx.application.Platform;
import javafx.scene.Group;
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

    ArrayList<Bullet> bullets = new ArrayList<>();

    Rectangle view;
    Group parentView;

    public Tower(World world, Position position, Group parentView) {
        this.world = world;
        this.position = position;
        this.range = 1000;
        this.damage = 10;
        this.period = 500;

        this.size = new Position(50, 50);

        this.parentView = parentView;

//        System.out.println(getCornerPosition());

        view = new Rectangle(position.x, position.y, getSize().x, getSize().y);
        view.setFill(Color.rgb(0, 100, 255));

        Platform.runLater(() -> parentView.getChildren().add(view));

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
    }

    public void attack() {
        Optional<Enemy> enemy = world.getTarget(this);
        if (enemy.isPresent()) {
            bullets.add(new Bullet(getCenter(), enemy.get().getCenter(), parentView));
            enemy.get().bleed(damage);
        }
    }

    public void update() {
        bullets.forEach(bullet -> bullet.update());
    }

    public boolean inRange(Enemy enemy) {
        return position.distanceTo(enemy.getPosition()) <= range;
    }
}
