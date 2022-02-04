package engine;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Element {

    public Shape view() {
        Rectangle view = new Rectangle(5, 5);
        GridPane.setHalignment(view, HPos.CENTER);
        GridPane.setValignment(view, VPos.CENTER);
        return view;
    }
}
