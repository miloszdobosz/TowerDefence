package world;

import engine.App;
import engine.Element;
import engine.Menu;
import entities.Enemy;
import entities.Entities;
import entities.Tower;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Optional;

public class World extends Element{
    Path path;

    Entities<Tower> towers = new Entities(new ArrayList<>());
    Entities<Enemy> enemies = new Entities(new HashSet<>());

    boolean placingTower = false;

//    Rectangle placingView;

    int enemyCount = 100;

    Menu menu;

    public World() {

        view = new Rectangle(0, 0, 500, 500);
        view.setFill(Color.rgb(100, 200, 50));

//        view.setOnMouseClicked(event -> addTower(new Position((int) event.getX(), (int) event.getY())));
        view.setOnMouseClicked(event -> {
            if (placingTower) {
                addTower(new Position((int) event.getX(), (int) event.getY()));
                setPlacingTower(false);
//                Platform.runLater(() -> {
//                    parentView.getChildren().remove(placingView);
//                });
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

//        App.parentView.getChildren().add(view);
        addView();

        path = new Path();

        menu = new Menu(this);

        Thread thread = new Thread(() -> {
            for (int i = 0; i < enemyCount; i++) {
                try {
                    Thread.sleep((long) (Math.random() * 1000 + 500));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                enemies.add(new Enemy(path.iterator(), 2, menu));
            }
        });
        thread.start();


        Thread thread2 = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.update();
            }
        });
        thread2.start();
    }

//    public Menu getMenu() {
//        return menu;
//    }

    public void addTower(Position position) {
        position = position.floor(50);
        Tower tower = new Tower(this, position);
        towers.add(tower);
        menu.pay(tower.getPrice());
    }

    public Optional<Enemy> getTarget(Tower tower) {
        return enemies.stream().filter(tower::inRange).max(Comparator.comparingDouble(Enemy::getProgress));
    }

    public void update() {
        enemies.update();
        towers.update();
    }

    public void setPlacingTower(boolean placingTower) {
        this.placingTower = placingTower;
    }
}
