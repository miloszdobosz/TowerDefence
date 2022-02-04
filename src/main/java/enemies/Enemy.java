package enemies;

import engine.Element;
import world.Path;
import world.Position;

public class Enemy extends Element {
    Path path;
    Position position;

    Enemy(Path path, Position position) {
        this.path = path;
        this.position = position;
    }

    public void move(Position change) {
        position = position.add(change);
    }
}
