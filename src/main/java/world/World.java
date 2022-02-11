package world;

import entities.Enemy;
import entities.Tower;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Optional;

public class World {
    Path path;

    ArrayList<Tower> towers = new ArrayList<>();
    HashSet<Enemy> enemies = new HashSet<>();
    HashSet<Position> occupiedFields = new HashSet<>();

    ArrayList<Enemy> dead = new ArrayList<>();

    Rectangle view;
    Group parentView;

    public World(Group parentView) {
        view = new Rectangle(0, 0, 500, 500);
        view.setFill(Color.rgb(100, 200, 50));
        parentView.getChildren().add(view);

        path = new Path(parentView);

//        for (int i = 0; i < 10; i++) {
            enemies.add(new Enemy(this, path, 2, parentView));
//        }
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

    public void addTower(Tower tower) { towers.add(tower); }

    public Optional<Enemy> getTarget(Tower tower) {
        return enemies.stream().filter(tower::inRange).max(Comparator.comparingInt(Enemy::getProgress));
    }

    public void update() {
        enemies.forEach(enemy -> enemy.move());
        dead.forEach(enemy -> enemies.remove(enemy));
        dead = new ArrayList<>();
    }

//    public void view(Group parent) {
//
////        Group group = new Group(view, path.view());
//        parent.getChildren().addAll(view, path.view());
//        enemies.forEach(enemy -> enemy.view(parent));
//        towers.forEach(tower -> tower.view(parent));
////        return group;
//    }
}
