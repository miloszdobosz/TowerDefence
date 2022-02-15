package world;

import engine.App;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Iterator;

public class Path {
    public static Image[] views = {
            new Image("path/1.png", World.fieldSize, 0, true, true)
    };

    ArrayList<Position> positions = new ArrayList<>();

    Path(World world) {
        positions.add(new Position(100, -50));
        positions.add(new Position(100, 250));
        positions.add(new Position(250, 250));
        positions.add(new Position(250, 100));
        positions.add(new Position(450, 100));
        positions.add(new Position(450, 350));
        positions.add(new Position(600, 350));
        positions.add(new Position(600, 600));
        positions.add(new Position(300, 600));
        positions.add(new Position(300, 450));
        positions.add(new Position(-50, 450));

        for (int i = 0; i < positions.size() - 1; i++) {
            Position p1 = positions.get(i);
            Position p2 = positions.get(i + 1);

            Position direction = p2.subtract(p1).normalize(World.fieldSize);
            while (p1.distanceTo(p2) >= direction.length()) {
                ImageView view = new ImageView(views[0]);
                view.relocate(p1.x, p1.y);
                Platform.runLater(() -> App.parentView.getChildren().add(view));
                world.occupy(p1);
                p1 = p1.add(direction);
            }
        }
    }

    public Iterator<Position> iterator() {
        return positions.iterator();
    }
}
