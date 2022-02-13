package engine;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import world.World;

public class Menu {
    int health = 100;
    int money;

    Group parentView;
    Rectangle view;

    public Menu(World world, Group parentView) {
        view = new Rectangle(500, 0, 200, 500);
        view.setFill(Color.rgb(100,100,100));
        view.setOnMouseClicked(event -> world.setPlacingTower(true));

        this.parentView = parentView;

        parentView.getChildren().add(view);
    }

    public void bleed(int damage) {
        health -= damage;

        if (health <= 0) {
            kill();
        }
    }

    public void kill() {
        System.out.println("GAME OVER!");
    }
}
