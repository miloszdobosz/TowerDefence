package engine;

import entities.Entities;
import javafx.application.Platform;
import javafx.scene.shape.Shape;
import world.Position;

public abstract class Element {
    protected Position position;
    protected Position size;

    protected Entities parentCollection;

    protected Shape view;

    public Position getPosition() {
        return position;
    }
    public Position getSize() {return size;}
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

    public void addView() {
        Platform.runLater(() -> App.parentView.getChildren().add(view));
    }

    public void update() {
    }
}
