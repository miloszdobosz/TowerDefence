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
    int size;
    int price;
    int damage;
    int range;
    int period;

    Position position;

    World world;

    ArrayList<Bullet> bullets = new ArrayList<>();

    Rectangle view;
    Group parentView;

    public Tower(World world, Position position, Group parentView) {
        this.world = world;
        this.position = position;
        this.range = 100;
        this.damage = 10;
        this.period = 50;

        this.parentView = parentView;

        view = new Rectangle(position.x, position.y, 50, 50);
        view.setFill(Color.rgb(0, 100, 255));
        parentView.getChildren().add(view);

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
            bullets.add(new Bullet(position, enemy.get().getPosition(), parentView));
            enemy.get().bleed(damage);
        }
    }

    public boolean inRange(Enemy enemy) {
        return position.distanceTo(enemy.getPosition()) <= range;
    }


//    @Override
//    public void view(Group parent) {
//        parent.getChildren().add(view);
//        bullets.forEach(bullet -> bullet.view(parent));
//    }
}
