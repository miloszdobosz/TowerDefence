package world;

import engine.App;
import entities.Entity;
import engine.Engine;
import entities.Enemy;
import entities.Entities;
import entities.Tower;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.HashSet;

public class World extends Entity {
    public static Position worldSize = new Position(700, 700);
    public static Image[] views = {
            new Image("world/1.png", worldSize.x, worldSize.y, false, true)
    };
    public static int fieldSize = 50;

    Path path;

    Entities<Tower> towers = new Entities(new ArrayList<>());
    Entities<Enemy> enemies = new Entities(new HashSet<>());

    HashSet<Position> occupied = new HashSet<>();

    public int placingTower = -1;

    int enemyCount = 100;

    Menu menu;

    public World() {
        view = new ImageView(views[0]);
        view.setOnMouseClicked(event -> {
            if (placingTower != -1) {
                addTower(new Position((int) event.getX(), (int) event.getY()), placingTower);
                placingTower = -1;
            }
        });

        addView();

        path = new Path(this);
        menu = new Menu(this);

        activateEnemies();
        activate();
    }

    private void activate() {
        Thread engine = new Thread(() -> {
            while (menu.alive) {
                try {
                    Thread.sleep(Engine.period);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.update();
            }
        });
        engine.start();
    }

    private void activateEnemies() {
        Thread enemyGenerator = new Thread(() -> {
            for (int i = 0; i < enemyCount; i++) {
                try {
                    Thread.sleep((long) (Math.random() * 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                enemies.add(new Enemy(path.iterator(), 2 + i * Math.random() * 0.1, menu));
            }
            App.win();
        });
        enemyGenerator.start();
    }

    public boolean addTower(Position position, int type) {
        position = position.floor(fieldSize);
        if (occupiedAny(position, Tower.sizes[type])) {
            return false;
        }
        Tower tower = new Tower(enemies, position, type);
        towers.add(tower);
        menu.pay(tower.getPrice());
        occupyAll(position, Tower.sizes[type]);
        return true;
    }

    public void occupy(Position position) {
        occupied.add(position);
    }

    public boolean occupiedAny(Position position, Position size) {
        for (double x = 0; x < size.x; x += World.fieldSize) {
            for (double y = 0; y < size.y; y += World.fieldSize) {
                if (occupied.contains(position.add(new Position(x, y)))) return true;
            }
        }

        return false;
    }

    public void occupyAll(Position position, Position size) {
        for (double x = 0; x < size.x; x += World.fieldSize) {
            for (double y = 0; y < size.y; y += World.fieldSize) {
                occupy(position.add(new Position(x, y)));
            }
        }
    }


    public void update() {
        enemies.update();
        towers.update();
    }

    public void setPlacingTower(int type) {
        this.placingTower = type;
    }
}
