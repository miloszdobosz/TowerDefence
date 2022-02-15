package entities;

import engine.App;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import world.Position;

public abstract class Entity {
    protected Position position;
    protected Position size;

    protected Entities parentCollection;

    protected Node view;

    public Position getPosition() {
        return position;
    }

    public Position getCenter() {
        return position.add(size.times(0.5));
    }

    public void setParentCollection(Entities parentCollection) {
        this.parentCollection = parentCollection;
    }

    public void delete() {
        parentCollection.remove(this);
        Platform.runLater(() -> App.parentView.getChildren().remove(this.view));
    }

    public void setView(Image image) {
        view = new ImageView(image);
        Platform.runLater(() -> view.relocate(position.x, position.y));
    }

    public void addView() {
        Platform.runLater(() -> App.parentView.getChildren().add(view));
    }

    public void update() {
    }
}
