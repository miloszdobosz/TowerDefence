package world;

import engine.App;
import javafx.application.Platform;
import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.Iterator;

public class Path {
    ArrayList<Position> positions = new ArrayList<>();

    Path() {
//        Wylosuj kierunki

        positions.add(new Position(100, 0));
        positions.add(new Position(100, 200));
        positions.add(new Position(200, 200));
        positions.add(new Position(200, 100));
        positions.add(new Position(400, 100));
        positions.add(new Position(400, 400));
        positions.add(new Position(0, 400));


//        positions.forEach((p1, p2) -> parentView.getChildren().add(new Line(p1.x, p1.y, p2.x, p2.y)));
        for (int i = 0; i < positions.size() - 1; i++) {
            Position p1 = positions.get(i);
            Position p2 = positions.get(i + 1);
            Platform.runLater(() -> App.parentView.getChildren().add(new Line(p1.x, p1.y, p2.x, p2.y)));
        }
    }

    public Iterator<Position> iterator() {
        return positions.iterator();
    }
}
