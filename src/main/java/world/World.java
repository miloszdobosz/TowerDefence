package world;

import engine.Menu;
import entities.Enemy;
import entities.Tower;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Optional;

public class World {
    Path path;

    ArrayList<Tower> towers = new ArrayList<>();
    ArrayList<Tower> newTowers = new ArrayList<>();

    HashSet<Enemy> enemies = new HashSet<>();
    ArrayList<Enemy> dead = new ArrayList<>();
    ArrayList<Enemy> newBorn = new ArrayList<>();

//    HashSet<Position> occupiedFields = new HashSet<>();

    Rectangle view;
    Group parentView;

    boolean placingTower = false;

    Rectangle placingView;

    int enemyCount = 100;

    Menu menu;

    public World(Group parentView) {
        view = new Rectangle(0, 0, 500, 500);
        view.setFill(Color.rgb(100, 200, 50));

//        view.setOnMouseClicked(event -> addTower(new Position((int) event.getX(), (int) event.getY())));
        view.setOnMouseClicked(event -> {
            if (placingTower) {
                addTower(new Position((int) event.getX(), (int) event.getY()));
                setPlacingTower(false);
                Platform.runLater(() -> {
                    parentView.getChildren().remove(placingView);
                });
            }
        });

//        view.setOnMouseEntered(event -> {
//            if (placingTower) {
//                Platform.runLater(() -> {
//                    placingView = new Rectangle(event.getX(), event.getY(), 50, 50);
//                    placingView.setFill(Color.rgb(255, 0, 0, 0.5));
//                    parentView.getChildren().add(placingView);
//                });
//            }
//        });
//
//        view.setOnMouseMoved(event -> {
//            if (placingTower) {
//                Platform.runLater(() -> {
//                    placingView.relocate(event.getX(), event.getY());
//                });
//            }
//        });

        this.parentView = parentView;
        parentView.getChildren().add(view);

        path = new Path(parentView);

        menu = new Menu(this, parentView);

        Thread thread = new Thread(() -> {
            for (int i = 0; i < enemyCount; i++) {
                try {
                    Thread.sleep((long) (Math.random() * 1000 + 500));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(i);
                newBorn.add(new Enemy(this, path, 2, parentView, menu));
            }
        });
        thread.start();
    }

    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    public void removeEnemy(Enemy enemy) {
        enemies.remove(enemy);
    }

    public void kill(Enemy enemy) {
        dead.add(enemy);
    }

    public Menu getMenu() {
        return menu;
    }

    public void addTower(Position position) {
        position = position.floor(50);
//        if (occupiedFields.contains(position)) return;
//        Tower tower = new Tower(this, position, this.parentView);
        newTowers.add(new Tower(this, position, this.parentView));
//        occupiedFields.add(position);
    }

    public Optional<Enemy> getTarget(Tower tower) {
        return enemies.stream().filter(tower::inRange).max(Comparator.comparingInt(Enemy::getProgress));
    }

    public void update() {
        enemies.forEach(enemy -> enemy.update());

        enemies.removeAll(dead);
        enemies.addAll(newBorn);
        dead = new ArrayList<>();
        newBorn = new ArrayList<>();

        towers.forEach(tower -> tower.update());

        towers.addAll(newTowers);
        newTowers = new ArrayList<>();
    }

    public void setPlacingTower(boolean placingTower) {
        this.placingTower = placingTower;
    }
}
