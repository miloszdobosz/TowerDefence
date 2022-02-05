package world;

import entities.Enemy;
import entities.Tower;

import java.util.ArrayList;
import java.util.HashSet;

public class Map {
    Path path;

    ArrayList<Tower> towers = new ArrayList<>();
    HashSet<Enemy> enemies = new HashSet<>();
    HashSet<Position> occupiedFields = new HashSet<>();

    Map() {

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

}
