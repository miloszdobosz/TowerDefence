package world;

import enemies.Enemy;

import java.util.ArrayList;

public class Path {
    ArrayList<Direction> directions = new ArrayList<>();
    ArrayList<Enemy> enemies = new ArrayList<>();

    Path() {
//        Wylosuj kierunki
    }

    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    public void update() {
        for (Enemy enemy : enemies) {
            enemy.move(new Position(0, 0));
        }
    }
}
