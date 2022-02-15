package entities;

import engine.App;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import world.Position;
import world.World;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;

public class Tower extends Entity {
    public static final Image[] views = {
            new Image("towers/1.png", 50, 0, true, true),
            new Image("towers/2.png", 50, 0, true, true),
            new Image("towers/3.png", 50, 0, true, true),
            new Image("towers/4.png", 100, 0, true, true)
    };
    public static final Position[] sizes = {
            new Position(World.fieldSize, World.fieldSize),
            new Position(World.fieldSize, World.fieldSize),
            new Position(World.fieldSize, World.fieldSize * 3),
            new Position(World.fieldSize * 2, World.fieldSize * 2),
    };
    public static final int[] prices = {100, 200, 200, 400};
    public static final int[] ranges = {150, 150, 600, 300};
    public static final int[] periods = {500, 1000, 500, 1000};

    int price;
    int range;
    int period;

    Entities<Enemy> enemies;

    Entities<Bullet> bullets = new Entities(new ArrayList<>());

    boolean exploding;

    public Tower(Entities<Enemy> enemies, Position position, int type) {
        this.enemies = enemies;
        this.position = position;

        this.range = ranges[type];
        this.period = periods[type];
        this.price = prices[type];
        this.size = sizes[type];
        this.exploding = type == 1 || type == 3;

        setView(views[type]);

        Circle rangeView = new Circle(getCenter().x, getCenter().y, range, Color.rgb(100, 100, 100, 0.5));
        view.setOnMouseClicked(event -> {
            Platform.runLater(() -> App.parentView.getChildren().add(rangeView));

            rangeView.setOnMouseClicked(e -> {
                Platform.runLater(() -> App.parentView.getChildren().remove(rangeView));
            });
        });

        addView();
        activate(position);
    }

    public void activate(Position position) {
        this.position = position;
        view.relocate(position.x, position.y);

        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(period);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                attack();
            }
        });
        thread.start();
    }

    public void attack() {
        Optional<Enemy> optionalEnemy = aim();

        if (optionalEnemy.isPresent()) {
            if (exploding) {
                bullets.add(new ExplodingBullet(this, optionalEnemy.get(), 20, 100, enemies));
            }
            else {
                bullets.add(new Bullet(this, optionalEnemy.get(), 20));
            }
        }
    }

    public void update() {
        bullets.update();
    }

    public int getPrice() {
        return price;
    }

    private Optional<Enemy> aim() {
        return enemies.stream().filter(this::inRange).max(Comparator.comparingDouble(Enemy::getProgress));
    }

    private boolean inRange(Enemy enemy) {
        return position.distanceTo(enemy.getPosition()) <= range;
    }
}
