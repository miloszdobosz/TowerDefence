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
    ArrayList<Integer> distances = new ArrayList<>();

    ArrayList<HashSet<Enemy>> enemies = new ArrayList<>();

    Path(Group parentView) {
//        Wylosuj kierunki
        positions.add(new Position(0, 100));
        directions.add(Direction.E);
        distances.add(400);

        positions.add(new Position(400, 100));
        directions.add(Direction.S);
        distances.add(300);

        positions.add(new Position(400, 400));
        directions.add(Direction.W);
        distances.add(400);

        positions.add(new Position(0, 400));
        directions.add(Direction.W);
        distances.add(0);

//        positions.forEach((p1, p2) -> parentView.getChildren().add(new Line(p1.x, p1.y, p2.x, p2.y)));
        for (int i = 0; i < positions.size() - 1; i++) {
            parentView.getChildren().add(new Line(positions.get(i).x, positions.get(i).y, positions.get(i + 1).x, positions.get(i + 1).y));
        }
    }

    public Position getPosition(int i) {
        return positions.get(i);
    }

    public Direction getDirection(int i) {
        return directions.get(i);
    }

    public int getDistance(int i) {
        return distances.get(i);
    }

    public Group view() {
        Group group = new Group();
        for (int i = 0; i < positions.size() - 1; i++) {
            group.getChildren().add(new Line(positions.get(i).x, positions.get(i).y, positions.get(i + 1).x, positions.get(i + 1).y));
        }
        return group;
    }
}
