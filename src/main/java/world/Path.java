package world;

import engine.Element;
import entities.Enemy;
import javafx.scene.Group;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.HashSet;

public class Path{
    ArrayList<Position> positions = new ArrayList<>();
    ArrayList<Direction> directions = new ArrayList<>();
    ArrayList<HashSet<Enemy>> enemies = new ArrayList<>();

    Path() {
//        Wylosuj kierunki
        positions.add(new Position(0, 100));
        positions.add(new Position(400, 100));
        positions.add(new Position(400, 400));
        positions.add(new Position(0, 400));
    }

    public Group view() {
        Group group = new Group();
        for (int i = 0; i < positions.size() - 1; i++) {
            group.getChildren().add(new Line(positions.get(i).x, positions.get(i).y, positions.get(i + 1).x, positions.get(i + 1).y));
        }
        return group;
    }
}
