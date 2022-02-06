package world;

import engine.Element;
import entities.Enemy;
import entities.Tower;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.HashSet;

public class Map{
    Path path;

    ArrayList<Tower> towers = new ArrayList<>();
    HashSet<Enemy> enemies = new HashSet<>();
    HashSet<Position> occupiedFields = new HashSet<>();

    public Map() {
        path = new Path();
    }

    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    public void removeEnemy(Enemy enemy) {
        enemies.remove(enemy);
    }

    public void update() {
        for (Enemy enemy : enemies) {
            enemy.move();
        }
    }

    public Group view() {
        Rectangle view = new Rectangle(0, 0, 500, 500);
        view.setFill(Color.rgb(100, 200, 50));
        Group group = new Group(view, path.view());
        return group;
    }
}
